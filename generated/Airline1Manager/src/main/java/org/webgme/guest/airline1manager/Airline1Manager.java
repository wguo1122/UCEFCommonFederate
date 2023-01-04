package org.webgme.guest.airline1manager;

import org.webgme.guest.airline1manager.rti.*;

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
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.io.File;  



// Define the Airline1Manager type of federate for the federation.

public class Airline1Manager extends Airline1ManagerBase {
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;
    private boolean updateDayahead = false;
    private int timeScale = 5; // mins per timestep
    private int simulationDay = 0;
    private int simulationHour = 0;
    private int simulationMin = 0;
    
    private String path = "";
    private String line = "";
    private boolean inboundFlight = false;


    public Airline1Manager(FederateConfig params) throws Exception {
        super(params);
    }
    
    private void checkReceivedSubscriptions() {
        InteractionRoot interaction = null;
        while ((interaction = getNextInteractionNoWait()) != null) {
            if (interaction instanceof Airline1DeparturReady) {
                handleInteractionClass((Airline1DeparturReady) interaction);
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

                path = "./DayAheadSchdule/"+"AAA"+"Day"+String.valueOf(simulationDay)+".csv";
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
            //    ArrivalPass arrivalPass = create_ArrivalPass();
            //    arrivalPass.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    arrivalPass.set_airline( < YOUR VALUE HERE > );
            //    arrivalPass.set_arrivalNotification( < YOUR VALUE HERE > );
            //    arrivalPass.set_federateFilter( < YOUR VALUE HERE > );
            //    arrivalPass.set_originFed( < YOUR VALUE HERE > );
            //    arrivalPass.set_sourceFed( < YOUR VALUE HERE > );
            //    arrivalPass.set_transferPassNum( < YOUR VALUE HERE > );
            //    arrivalPass.sendInteraction(getLRC(), currentTime + getLookAhead());
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

    private void handleInteractionClass(Airline1DeparturReady interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    private void handleObjectClass(RealTimeArriSche object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    private void handleObjectClass(RealTimeDepSche object) {
        //////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the object //
        //////////////////////////////////////////////////////////
    }

    public static void main(String[] args) {
        try {
            FederateConfigParser federateConfigParser =
                new FederateConfigParser();
            FederateConfig federateConfig =
                federateConfigParser.parseArgs(args, FederateConfig.class);
            Airline1Manager federate =
                new Airline1Manager(federateConfig);
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
