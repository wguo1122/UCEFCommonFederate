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



// Define the Airline1Manager type of federate for the federation.

public class Airline1Manager extends Airline1ManagerBase {
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;

    public Airline1Manager(FederateConfig params) throws Exception {
        super(params);
    }

    private void checkReceivedSubscriptions() {
        InteractionRoot interaction = null;
        while ((interaction = getNextInteractionNoWait()) != null) {
            if (interaction instanceof FlightRemovalNotification) {
                handleInteractionClass((FlightRemovalNotification) interaction);
            }
            else if (interaction instanceof Airline1DeparturReady) {
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

    private void handleInteractionClass(FlightRemovalNotification interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
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
