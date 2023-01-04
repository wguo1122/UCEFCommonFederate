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



// Define the ATC type of federate for the federation.

public class ATC extends ATCBase {
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;

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
