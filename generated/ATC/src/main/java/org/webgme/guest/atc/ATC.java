package org.webgme.guest.atc;

import org.webgme.guest.atc.rti.*;

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

// Define the ATC type of federate for the federation.

public class ATC extends ATCBase {
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
    double currentTimeValue = 0;

    
    
    // Create Hash Map of <index, FlightInfo>
    private Map<String, RealTimeArriSche> rtArriveMap = new HashMap<String, RealTimeArriSche>(); 
    
    // Create array of [flightIndex, flightID, actualTime, arrive/departure]
    private ArrayList<ArrayList<String>> firstRequestList =  new ArrayList<ArrayList<String>>(); 
    private ArrayList<ArrayList<String>> secondRequestList =  new ArrayList<ArrayList<String>>(); 
    private ArrayList<ArrayList<String>> flightBoardList =  new ArrayList<ArrayList<String>>(); 
    
    // Create Hash Map of <FlightID, index in flightBoardList>
    private Map<String, Integer> flightIDMap = new HashMap<String, Integer>();


    public ATC(FederateConfig params) throws Exception {
        super(params);
    }

    private void checkReceivedSubscriptions() {
        InteractionRoot interaction = null;
        while ((interaction = getNextInteractionNoWait()) != null) {
            if (interaction instanceof FlightRemovalNotification) {
                handleInteractionClass((FlightRemovalNotification) interaction);
            }
            else if (interaction instanceof ArrivalConfirm) {
                handleInteractionClass((ArrivalConfirm) interaction);
            }
            else if (interaction instanceof Aircraft) {
                handleInteractionClass((Aircraft) interaction);
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

            currentTimeValue = 100*simulationHour+simulationMin;

            System.out.println("Current time step :"+ currentTime);
            System.out.println("Simulation Day: "+ simulationDay + " Hour: "+ simulationHour + " Min: "+simulationMin);


            // Read RTArriveSch for the next day
            if (currentTime%((60/timeScale)*24) == 0 ) {
 
                path = "./RealTimeArriveSchdule/"+"RtArrDay"+String.valueOf(simulationDay)+".csv";
                System.out.println("Reading RealTimeArriveSchdule at :"+ path);
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String headerLine = reader.readLine(); // Read Header Line
                System.out.println(headerLine);
                while ((line = reader.readLine()) != null) {
                    // Split the line into separate values
                    String[] values = line.split(",");
                    
                    
                    ArrayList<String> flightInfoRow1 = new ArrayList<String>(Arrays.asList(values[0] , values[1], values[2]));
                    firstRequestList.add(flightInfoRow1); 

                    ArrayList<String> flightInfoRow2 = new ArrayList<String>(Arrays.asList(values[0] , values[1], values[3]));
                    secondRequestList.add(flightInfoRow2); 

                    }
                reader.close();

                Comparator<ArrayList<String>> arrayListComparator = new Comparator<ArrayList<String>>() {
                    @Override
                    public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                    return Double. valueOf(o1.get(2).replace(":","")).compareTo(Double. valueOf(o2.get(2).replace(":","")));
                    //return o1[2].compareTo(o2[2]);
                    }
                };

                Collections.sort(firstRequestList, arrayListComparator);
                System.out.println("firstRequestList after sort:");
                for (int i = 0; i <firstRequestList.size(); i++) { 
                    System.out.println(firstRequestList.get(i)); 
                    
                }

                Collections.sort(secondRequestList, arrayListComparator);
                System.out.println("secondRequestList after sort:");
                for (int i = 0; i <secondRequestList.size(); i++) { 
                    System.out.println(secondRequestList.get(i)); 
                    
                }
                
            }

            // Send 1st Landing Request
            for (int i = 0; i<firstRequestList.size();){
                if (Double.valueOf(firstRequestList.get(0).get(2).replace(":",""))<=currentTimeValue) {
                    RealtimeArrivalRequest realtimeArrivalRequest = create_RealtimeArrivalRequest();
                    realtimeArrivalRequest.set_airline(firstRequestList.get(0).get(0));
                    realtimeArrivalRequest.set_flightNumber(firstRequestList.get(0).get(1));
                    realtimeArrivalRequest.set_landingTime("1ST"+firstRequestList.get(0).get(2));
                    realtimeArrivalRequest.sendInteraction(getLRC(), currentTime + getLookAhead());

                    System.out.println("Sending 1st landing request for "+firstRequestList.get(0).get(0)+firstRequestList.get(0).get(1));
                    firstRequestList.remove(0);
                } else {
                    break;
                }
            }

            // Send 2nd Landing Request
            for (int i = 0; i<secondRequestList.size();){
                if (Double.valueOf(secondRequestList.get(0).get(2).replace(":",""))<=currentTimeValue) {
                    RealtimeArrivalRequest realtimeArrivalRequest = create_RealtimeArrivalRequest();
                    realtimeArrivalRequest.set_airline(secondRequestList.get(0).get(0));
                    realtimeArrivalRequest.set_flightNumber(secondRequestList.get(0).get(1));
                    realtimeArrivalRequest.set_landingTime("2ND"+secondRequestList.get(0).get(2));
                    realtimeArrivalRequest.sendInteraction(getLRC(), currentTime + getLookAhead());

                    System.out.println("Sending 2nd landing request for "+secondRequestList.get(0).get(0)+secondRequestList.get(0).get(1));
                    secondRequestList.remove(0);
                } else {
                    break;
                }
            }

            ////////////////////////////////////////////////////////////
            // TODO send interactions that must be sent every logical //
            // time step below                                        //
            ////////////////////////////////////////////////////////////

            // Set the interaction's parameters.
            //
            //    RealtimeArrivalRequest realtimeArrivalRequest = create_RealtimeArrivalRequest();
            //    realtimeArrivalRequest.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    realtimeArrivalRequest.set_airline( < YOUR VALUE HERE > );
            //    realtimeArrivalRequest.set_federateFilter( < YOUR VALUE HERE > );
            //    realtimeArrivalRequest.set_flightNumber( < YOUR VALUE HERE > );
            //    realtimeArrivalRequest.set_landingTime( < YOUR VALUE HERE > );
            //    realtimeArrivalRequest.set_originFed( < YOUR VALUE HERE > );
            //    realtimeArrivalRequest.set_sourceFed( < YOUR VALUE HERE > );
            //    realtimeArrivalRequest.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    Aircraft aircraft = create_Aircraft();
            //    aircraft.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    aircraft.set_airline( < YOUR VALUE HERE > );
            //    aircraft.set_federateFilter( < YOUR VALUE HERE > );
            //    aircraft.set_flightNumber( < YOUR VALUE HERE > );
            //    aircraft.set_originFed( < YOUR VALUE HERE > );
            //    aircraft.set_sourceFed( < YOUR VALUE HERE > );
            //    aircraft.sendInteraction(getLRC(), currentTime + getLookAhead());

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

                System.out.println("FlightID Map:"+ flightIDMap);

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
        System.out.println("Received new removal Notificaiton for "+FlightID);
        

        if (rtArriveMap.containsKey(FlightID)) { // Arrive case
            (flightBoardList.get(flightIDMap.get(FlightID))).set(2, "-1");
            rtArriveMap.remove(FlightID);
            flightIDMap.remove(FlightID);

            newRemoval = true;

        } else {  //Departure case 
            System.out.println("Ignored new removal Notificaiton for "+FlightID);
        }
        
    }

    private void handleInteractionClass(ArrivalConfirm interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    private void handleInteractionClass(Aircraft interaction) {
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
            
    
    }

    public static void main(String[] args) {
        try {
            FederateConfigParser federateConfigParser =
                new FederateConfigParser();
            FederateConfig federateConfig =
                federateConfigParser.parseArgs(args, FederateConfig.class);
            ATC federate =
                new ATC(federateConfig);
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
