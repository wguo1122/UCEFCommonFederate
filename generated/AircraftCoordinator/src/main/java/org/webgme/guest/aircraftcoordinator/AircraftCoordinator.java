package org.webgme.guest.aircraftcoordinator;

import org.webgme.guest.aircraftcoordinator.rti.*;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.*;


// Define the AircraftCoordinator type of federate for the federation.

public class AircraftCoordinator extends AircraftCoordinatorBase {
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;
    private boolean updateDayahead = false;
    private int timeScale = 5; // mins per timestep
    private int simulationDay = 0;
    private int simulationHour = 0;
    private int simulationMin = 0;
    

    
    private int flightIndex = 0;
    
    // Create Hash Map of <index, FlightInfo>
    private Map<String, RealTimeArriSche> rtArriveMap = new HashMap<String, RealTimeArriSche>(); 
    private Map<String, RealTimeDepSche> rtDepartureMap = new HashMap<String, RealTimeDepSche>(); 
    
    // Create array of [flightIndex, flightID, actualTime, arrive/departure]
    private ArrayList<ArrayList<String>> flightBoardList =  new ArrayList<ArrayList<String>>(); 

    // Create Hash Map of <FlightID, index in flightBoardList>
    private Map<String, Integer> flightIDMap = new HashMap<String, Integer>();

        

    
    ////////////////////////////////////////////////////////////////////////
    // TODO instantiate objects that must be sent every logical time step //
    ////////////////////////////////////////////////////////////////////////
    // RealTimeDepSche realTimeDepSche =
    //     new RealTimeDepSche();
    // RealTimeArriSche realTimeArriSche =
    //     new RealTimeArriSche();

    public AircraftCoordinator(FederateConfig params) throws Exception {
        super(params);

        //////////////////////////////////////////////////////
        // TODO register object instances after super(args) //
        //////////////////////////////////////////////////////
        // realTimeDepSche.registerObject(getLRC());
        // realTimeArriSche.registerObject(getLRC());

        // Register 10 Objects for both arrive and departure
        // Register realtime schedule at RTI

        // for (int i=0; i<numberOfRegistedObject; i++){

        //     RealTimeDepSche realTimeDepSche = new RealTimeDepSche();
        //     realTimeDepSche.registerObject(getLRC());
        //     rtDepartureMap.put(String.valueOf(i),realTimeDepSche);

        //     RealTimeArriSche realTimeArriSche = new RealTimeArriSche();
        //     realTimeArriSche.registerObject(getLRC());
        //     rtArriveMap.put(String.valueOf(i),realTimeArriSche);

        // }
    }

    private void checkReceivedSubscriptions() {
        InteractionRoot interaction = null;
        
        while ((interaction = getNextInteractionNoWait()) != null) {
            
            if (interaction instanceof RealtimeArrivalRequest) {
                handleInteractionClass((RealtimeArrivalRequest) interaction);
                log.info("New realtime Arrival Request interaction");
            }
            else if (interaction instanceof DepartureRequest) {
                handleInteractionClass((DepartureRequest) interaction);
                log.info("New Departure Arrival Request interaction");
            }
            else if (interaction instanceof DayaheadSchedule) {
                handleInteractionClass((DayaheadSchedule) interaction);
                log.info("New Day Ahead Schedule interaction");
            }
            else if (interaction instanceof Aircraft) {
                handleInteractionClass((Aircraft) interaction);
                log.info("New Aircraft interaction");
            }
            else {
                log.debug("unhandled interaction: {}", interaction.getClassName());
            }
        }
    }

    private void execute() throws Exception {
        if(super.isLateJoiner()) {
            log.info("turning off time regulation (late joiner)");
            currentTime = super.getLBTS() - super.getLookAhead();
            super.disableTimeRegulation();
        }

        /////////////////////////////////////////////
        // TODO perform basic initialization below //
        /////////////////////////////////////////////

        AdvanceTimeRequest atr = new AdvanceTimeRequest(currentTime);
        putAdvanceTimeRequest(atr);

        if(!super.isLateJoiner()) {
            log.info("waiting on readyToPopulate...");
            readyToPopulate();
            log.info("...synchronized on readyToPopulate");
        }

        ///////////////////////////////////////////////////////////////////////
        // TODO perform initialization that depends on other federates below //
        ///////////////////////////////////////////////////////////////////////

        if(!super.isLateJoiner()) {
            log.info("waiting on readyToRun...");
            readyToRun();
            log.info("...synchronized on readyToRun");
        }

        startAdvanceTimeThread();
        log.info("started logical time progression");

        while (!exitCondition) {
            atr.requestSyncStart();
            enteredTimeGrantedState();

            // Simulaiton Day/Hour/Min Calculation
            simulationDay = (int)(currentTime*timeScale)/(60*24);
            simulationHour = ((int)(currentTime*timeScale)%(60*24))/(60);
            simulationMin = ((int)(currentTime*timeScale)%(60*24))%(60);

            System.out.println("Current time step :"+ currentTime);
            System.out.println("Simulation Day: "+ simulationDay + " Hour: "+ simulationHour + " Min: "+simulationMin);

            ////////////////////////////////////////////////////////////
            // TODO send interactions that must be sent every logical //
            // time step below                                        //
            ////////////////////////////////////////////////////////////

            // Set the interaction's parameters.
            //
            //    ArrivalConfirm arrivalConfirm = create_ArrivalConfirm();
            //    arrivalConfirm.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_airline( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_arrivalTime( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_federateFilter( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_flightNum( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_gateNum( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_originFed( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_runwayNum( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_sourceFed( < YOUR VALUE HERE > );
            //    arrivalConfirm.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    Aircraft aircraft = create_Aircraft();
            //    aircraft.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    aircraft.set_airline( < YOUR VALUE HERE > );
            //    aircraft.set_federateFilter( < YOUR VALUE HERE > );
            //    aircraft.set_flightNumber( < YOUR VALUE HERE > );
            //    aircraft.set_originFed( < YOUR VALUE HERE > );
            //    aircraft.set_sourceFed( < YOUR VALUE HERE > );
            //    aircraft.sendInteraction(getLRC(), currentTime + getLookAhead());

            ////////////////////////////////////////////////////////////
            // TODO objects that must be sent every logical time step //
            ////////////////////////////////////////////////////////////
            //    realTimeDepSche.set_aircraft(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_airline(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_booked(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_capacity(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_checkedIn(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_depStatus(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_estimated(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_flightNumber(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_gate(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_scheduledDepTime(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_transfer(<YOUR VALUE HERE >);
            //    realTimeDepSche.updateAttributeValues(getLRC(), currentTime + getLookAhead());
            //    realTimeArriSche.set_aircraft(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_airline(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_arrivalStatus(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_booked(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_capacity(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_checkedIn(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_estimatedArrivalTime(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_flightNumber(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_gate(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_passNum(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_scheduledArrivalTime(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_transferOut(<YOUR VALUE HERE >);
            //    realTimeArriSche.updateAttributeValues(getLRC(), currentTime + getLookAhead());

            checkReceivedSubscriptions();
            

            if (updateDayahead) {
                
                Comparator<ArrayList<String>> arrayListComparator = new Comparator<ArrayList<String>>() {
                    @Override
                    public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                    return Double. valueOf(o1.get(2).replace(":","")).compareTo(Double. valueOf(o2.get(2).replace(":","")));
                    //return o1[2].compareTo(o2[2]);
                    }
                };

                Collections.sort(flightBoardList, arrayListComparator);
                System.out.println("FlightBoard after sort:");

                for (int i = 0; i <flightBoardList.size(); i++) { 
                    System.out.println(flightBoardList.get(i)); 
                    flightIDMap.put(flightBoardList.get(i).get(1),i);
                }

                System.out.println("FlightIndex Map:"+ flightIDMap);

                //    Reset all the Index for the next day
                updateDayahead = false;
                flightIndex = 0;
            }


            //Update all realtime schedule objects
            for (RealTimeArriSche rtas : rtArriveMap.values()){
                rtas.updateAttributeValues(getLRC(),currentTime+getLookAhead());
            }
            for (RealTimeDepSche rtds : rtDepartureMap.values()){
                rtds.updateAttributeValues(getLRC(),currentTime+getLookAhead());
            }

            ////////////////////////////////////////////////////////////////////
            // TODO break here if ready to resign and break out of while loop //
            ////////////////////////////////////////////////////////////////////

            if (!exitCondition) {
                currentTime += super.getStepSize();
                AdvanceTimeRequest newATR =
                    new AdvanceTimeRequest(currentTime);
                putAdvanceTimeRequest(newATR);
                atr.requestSyncEnd();
                atr = newATR;
            }
        }

        // call exitGracefully to shut down federate
        exitGracefully();

        //////////////////////////////////////////////////////////////////////
        // TODO Perform whatever cleanups are needed before exiting the app //
        //////////////////////////////////////////////////////////////////////
    }

    private void handleInteractionClass(RealtimeArrivalRequest interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    private void handleInteractionClass(DepartureRequest interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    private void handleInteractionClass(DayaheadSchedule interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
        updateDayahead = true;
        if (interaction.get_InboundOutbound() == true){
            // New Arrive Day Ahead Schedule
            String FlightID = interaction.get_airline()+interaction.get_flightNum();

            RealTimeArriSche rtas = new RealTimeArriSche();
            rtas.registerObject(getLRC());

            rtas.set_airline(interaction.get_airline());
            rtas.set_flightNumber(interaction.get_flightNum());
            rtas.set_aircraft(interaction.get_aircraft());
            rtas.set_scheduledArrivalTime(interaction.get_scheduledTime());
            rtas.set_estimatedArrivalTime(interaction.get_scheduledTime()); // Temp
            rtas.set_capacity(interaction.get_capacity()); // Type Need Discuss
            rtas.set_booked(interaction.get_booked()); // Type Need Discuss
            rtas.set_passNum(interaction.get_booked()); // Temp
            rtas.set_transferOut(interaction.get_transfer()); // Type
            rtas.set_checkedIn("0"); // Temp
            rtas.set_gate(interaction.get_gateNum());
            rtas.set_arrivalStatus(interaction.get_flightStatus());

            rtArriveMap.put(FlightID,rtas);
            
            System.out.println("Received Arrive Day Ahead Schedule of "+FlightID);

            // Setup the flight infomation board list
            ArrayList<String> flightInfoRow = new ArrayList<String>(
            Arrays.asList(String.valueOf(flightIndex),FlightID,rtas.get_estimatedArrivalTime(),"1"));
            
            flightBoardList.add(flightInfoRow);

            

            //arriveIndex = arriveIndex + 1;
            flightIndex = flightIndex + 1;
        }else{
            // New Departure Day Ahead Schedule
            String FlightID = interaction.get_airline()+interaction.get_flightNum();

            RealTimeDepSche rtds = new RealTimeDepSche();
            rtds.registerObject(getLRC());

            rtds.set_airline(interaction.get_airline());
            rtds.set_flightNumber(interaction.get_flightNum());
            rtds.set_aircraft(interaction.get_aircraft());
            rtds.set_scheduledDepTime(interaction.get_scheduledTime());
            rtds.set_estimated(interaction.get_scheduledTime()); // The attribute name need to change
            rtds.set_capacity(interaction.get_capacity()); // Type Need Discuss
            rtds.set_booked(interaction.get_booked()); // Type Need Discuss
            rtds.set_transfer(interaction.get_transfer()); // Type
            rtds.set_checkedIn("0"); // Temp Check Type
            rtds.set_gate(interaction.get_gateNum());
            rtds.set_depStatus(interaction.get_flightStatus());

            rtDepartureMap.put(FlightID,rtds);
            
            System.out.println("Received Departure Day Ahead Schedule of "+FlightID);
            
            // Setup the flight infomation board list
            ArrayList<String> flightInfoRow = new ArrayList<String>(
            Arrays.asList(String.valueOf(flightIndex),FlightID,rtds.get_estimated(),"0"));
            
            flightBoardList.add(flightInfoRow);

            
            
            //departureIndex = departureIndex + 1;
            flightIndex = flightIndex + 1;

        }
        // System.out.println("Current arrive Index : " + arriveIndex);
        // System.out.println("Current departure Index : " + departureIndex);
        System.out.println("Current flight Index : " + flightIndex);
    }

    private void handleInteractionClass(Aircraft interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    public static void main(String[] args) {
        try {
            FederateConfigParser federateConfigParser =
                new FederateConfigParser();
            FederateConfig federateConfig =
                federateConfigParser.parseArgs(args, FederateConfig.class);
            AircraftCoordinator federate =
                new AircraftCoordinator(federateConfig);
            federate.execute();
            log.info("Done.");
            System.exit(0);
        }
        catch (Exception e) {
            log.error(e);
            System.exit(1);
        }
    }
}
