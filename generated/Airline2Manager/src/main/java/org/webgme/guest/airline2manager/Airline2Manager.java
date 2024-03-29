package org.webgme.guest.airline2manager;

import org.webgme.guest.airline2manager.rti.*;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.base.ObjectReflector;
import org.cpswt.hla.ObjectRoot;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.*; 




// Define the Airline2Manager type of federate for the federation.

public class Airline2Manager extends Airline2ManagerBase {
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;
    private boolean newUpdate = false;
    private boolean newRemoval = false;
    private int timeScale = 5; // mins per timestep
    private int simulationDay = 0;
    private int simulationHour = 0;
    private int simulationMin = 0;
    
    private String path = "";
    private String line = "";
    private boolean inboundFlight = false;

    // Create Hash Map of <index, FlightInfo>
    private Map<String, RealTimeArriSche> rtArriveMap = new HashMap<String, RealTimeArriSche>(); 
    private Map<String, RealTimeDepSche> rtDepartureMap = new HashMap<String, RealTimeDepSche>(); 
    
    // Create array of [flightIndex, flightID, actualTime, arrive/departure]
    private ArrayList<ArrayList<String>> flightBoardList =  new ArrayList<ArrayList<String>>(); 

    // Create Hash Map of <FlightID, index in flightBoardList>
    private Map<String, Integer> flightIDMap = new HashMap<String, Integer>();

    public Airline2Manager(FederateConfig params) throws Exception {
        super(params);
    }

    private void checkReceivedSubscriptions() {
        InteractionRoot interaction = null;
        while ((interaction = getNextInteractionNoWait()) != null) {
            if (interaction instanceof FlightRemovalNotification) {
                handleInteractionClass((FlightRemovalNotification) interaction);
            }
            else if (interaction instanceof Airline2DeparturReady) {
                handleInteractionClass((Airline2DeparturReady) interaction);
            }
            else {
                log.debug("unhandled interaction: {}", interaction.getClassName());
            }
        }

        ObjectReflector reflector = null;
        while ((reflector = getNextObjectReflectorNoWait()) != null) {
            reflector.reflect();
            ObjectRoot object = reflector.getObjectRoot();
            if (object instanceof RealTimeArriSche) {
                handleObjectClass((RealTimeArriSche) object);
            }
            else if (object instanceof RealTimeDepSche) {
                handleObjectClass((RealTimeDepSche) object);
            }
            else {
                log.debug("unhandled object reflection: {}", object.getClassName());
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


            // Sending out new Day-ahead Schedule
            if (currentTime%((60/timeScale)*24) == 0 ) {            

                path = "./DayAheadSchdule/"+"BBB"+"Day"+String.valueOf(simulationDay)+".csv";
                System.out.println("Checking DayAheadSchdule at :"+ path);
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String headerLine = reader.readLine(); // Read Header Line
                System.out.println(headerLine);
                while ((line = reader.readLine()) != null) {
                    // Split the line into separate values
                    String[] values = line.split(",");
                    
                    // Send Day ahead Schedule to Aircraft coordinator
                    
                    

                    if (values[2].equals("0")) {
                        inboundFlight = false;
                    } else {
                        inboundFlight = true;
                    }
                    

                    DayaheadSchedule dayaheadSchedule = create_DayaheadSchedule();
                    
                    
                    dayaheadSchedule.set_airline( values [0] );
                    dayaheadSchedule.set_flightNum( values [1] );
                    dayaheadSchedule.set_InboundOutbound( inboundFlight );
                    dayaheadSchedule.set_aircraft( values [3] );
                    dayaheadSchedule.set_scheduledTime( values [4] );
                    dayaheadSchedule.set_capacity( values [5] );
                    dayaheadSchedule.set_booked( values [6]  );
                    dayaheadSchedule.set_transfer( values [7]  );
                    dayaheadSchedule.set_gateNum( values [8] );
                    dayaheadSchedule.set_flightStatus( values [9] );
                    dayaheadSchedule.sendInteraction(getLRC(), currentTime + getLookAhead());

                    System.out.println("Send Interaction with InboundFlight :" + inboundFlight);
                    System.out.println(Arrays.toString(values));
                    }
                reader.close();

                
            }
            ////////////////////////////////////////////////////////////
            // TODO send interactions that must be sent every logical //
            // time step below                                        //
            ////////////////////////////////////////////////////////////

            // Set the interaction's parameters.
            //
            //    ArrivalPass arrivalPass = create_ArrivalPass();
            //    arrivalPass.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    arrivalPass.set_airline( < YOUR VALUE HERE > );
            //    arrivalPass.set_arrivalNotification( < YOUR VALUE HERE > );
            //    arrivalPass.set_federateFilter( < YOUR VALUE HERE > );
            //    arrivalPass.set_originFed( < YOUR VALUE HERE > );
            //    arrivalPass.set_sourceFed( < YOUR VALUE HERE > );
            //    arrivalPass.set_transferPassNum( < YOUR VALUE HERE > );
            //    arrivalPass.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    DepartureRequest departureRequest = create_DepartureRequest();
            //    departureRequest.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    departureRequest.set_airline( < YOUR VALUE HERE > );
            //    departureRequest.set_checkedIn( < YOUR VALUE HERE > );
            //    departureRequest.set_federateFilter( < YOUR VALUE HERE > );
            //    departureRequest.set_flightNum( < YOUR VALUE HERE > );
            //    departureRequest.set_originFed( < YOUR VALUE HERE > );
            //    departureRequest.set_scheduledDeparTime( < YOUR VALUE HERE > );
            //    departureRequest.set_sourceFed( < YOUR VALUE HERE > );
            //    departureRequest.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    DayaheadSchedule dayaheadSchedule = create_DayaheadSchedule();
            //    dayaheadSchedule.set_InboundOutbound( < YOUR VALUE HERE > );
            //    dayaheadSchedule.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    dayaheadSchedule.set_aircraft( < YOUR VALUE HERE > );
            //    dayaheadSchedule.set_airline( < YOUR VALUE HERE > );
            //    dayaheadSchedule.set_booked( < YOUR VALUE HERE > );
            //    dayaheadSchedule.set_capacity( < YOUR VALUE HERE > );
            //    dayaheadSchedule.set_federateFilter( < YOUR VALUE HERE > );
            //    dayaheadSchedule.set_flightNum( < YOUR VALUE HERE > );
            //    dayaheadSchedule.set_flightStatus( < YOUR VALUE HERE > );
            //    dayaheadSchedule.set_gateNum( < YOUR VALUE HERE > );
            //    dayaheadSchedule.set_originFed( < YOUR VALUE HERE > );
            //    dayaheadSchedule.set_scheduledTime( < YOUR VALUE HERE > );
            //    dayaheadSchedule.set_sourceFed( < YOUR VALUE HERE > );
            //    dayaheadSchedule.set_transfer( < YOUR VALUE HERE > );
            //    dayaheadSchedule.sendInteraction(getLRC(), currentTime + getLookAhead());

            checkReceivedSubscriptions();

            if (newUpdate) {
                Comparator<ArrayList<String>> arrayListComparator = new Comparator<ArrayList<String>>() {
                    @Override
                    public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                    return Double. valueOf(o1.get(1).replace(":","")).compareTo(Double. valueOf(o2.get(1).replace(":","")));
                    //return o1[2].compareTo(o2[2]);
                    }
                };

                Collections.sort(flightBoardList, arrayListComparator);
                System.out.println("FlightBoard after sort:");

                for (int i = 0; i <flightBoardList.size(); i++) { 
                    System.out.println(flightBoardList.get(i)); 
                    flightIDMap.put(flightBoardList.get(i).get(0),i);
                }

                System.out.println("FlightIndex Map:"+ flightIDMap);
                //System.out.println("RtArrive Flight List" + rtArriveMap.keySet());
                //System.out.println("RtDep Flight List" + rtDepartureMap.keySet());

                //    Reset 
                newUpdate = false;
                
                
            }

            if (newRemoval){
               
                for (int i = 0; i<flightBoardList.size();){
                    ArrayList<String> flightInfoRow = flightBoardList.get(i);
                    if (flightInfoRow.get(2).equals("-1")) {
                        flightBoardList.remove(i);
                        System.out.println("Remove flight "+flightInfoRow.get(0)+" from flightBoardList!");
                    } else {
                        i = i + 1;
                    }
                }
                System.out.println("New flightBoardList after removal" + flightBoardList);
                System.out.println("New rtArrive Flight after removal" + rtArriveMap.keySet());
                System.out.println("New rtDep Flight after removal" + rtDepartureMap.keySet());

                newRemoval = false;
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

    private void handleInteractionClass(FlightRemovalNotification interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
        String FlightID = interaction.get_airline()+interaction.get_flightNumber();
        
        if (interaction.get_airline().equals("BBB")) {
            System.out.println("Received new removal Notificaiton for "+FlightID);
            newRemoval = true;
            if (((flightBoardList.get(flightIDMap.get(FlightID))).get(2)).equals("1")) { // Arrive case
                (flightBoardList.get(flightIDMap.get(FlightID))).set(2, "-1");
                rtArriveMap.remove(FlightID);
                flightIDMap.remove(FlightID);
            } else {  //Departure case 
                (flightBoardList.get(flightIDMap.get(FlightID))).set(2, "-1");
                rtDepartureMap.remove(FlightID);
                flightIDMap.remove(FlightID);
            }
        } else { 
            System.out.println("Ignored new removal Notificaiton for "+FlightID);
            
        }
    }

    private void handleInteractionClass(Airline2DeparturReady interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    private void handleObjectClass(RealTimeArriSche object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
        String FlightID = object.get_airline()+object.get_flightNumber();
        newUpdate = true;
        
        if (object.get_airline().equals("BBB")) {
            System.out.println("Received new Arr updates of flight "+FlightID);

            if (rtArriveMap.containsKey(FlightID)) { // Check new sche or updates
                rtArriveMap.remove(FlightID); //Removr old record
                rtArriveMap.put(FlightID,object); // Update new record
                flightBoardList.get(flightIDMap.get(FlightID)).set(1,object.get_estimatedArrivalTime());// Update flight Board Info

            } else {
                rtArriveMap.put(FlightID,object); // Update new record

                // Setup the flight infomation board list
                ArrayList<String> flightInfoRow = new ArrayList<String>(
                Arrays.asList(FlightID,object.get_estimatedArrivalTime(),"1"));
                flightBoardList.add(flightInfoRow);

            }
            
            
            
        } else {
            System.out.println("Ignored new Arr updates of flight "+FlightID);
        }
    }

    private void handleObjectClass(RealTimeDepSche object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
        String FlightID = object.get_airline()+object.get_flightNumber();
        newUpdate = true;
        
        if (object.get_airline().equals("BBB")) {
            System.out.println("Received new Dep updates of flight "+FlightID);

            if (rtDepartureMap.containsKey(FlightID)) { // Check new sche or updates
                rtDepartureMap.remove(FlightID); //Removr old record
                rtDepartureMap.put(FlightID,object); // Update new record
                flightBoardList.get(flightIDMap.get(FlightID)).set(1,object.get_estimated());// Update flight Board Info

            } else {
                rtDepartureMap.put(FlightID,object); // Update new record

                // Setup the flight infomation board list
                ArrayList<String> flightInfoRow = new ArrayList<String>(
                Arrays.asList(FlightID,object.get_estimated(),"0"));
                flightBoardList.add(flightInfoRow);

            }
            
            
            
        } else {
            System.out.println("Ignored new Dep updates of flight "+FlightID);
        }
    }

    public static void main(String[] args) {
        try {
            FederateConfigParser federateConfigParser =
                new FederateConfigParser();
            FederateConfig federateConfig =
                federateConfigParser.parseArgs(args, FederateConfig.class);
            Airline2Manager federate =
                new Airline2Manager(federateConfig);
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
