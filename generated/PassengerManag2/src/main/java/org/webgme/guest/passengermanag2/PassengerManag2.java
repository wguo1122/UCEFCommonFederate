package org.webgme.guest.passengermanag2;

import org.webgme.guest.passengermanag2.rti.*;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



// Define the PassengerManag2 type of federate for the federation.

public class PassengerManag2 extends PassengerManag2Base {
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;

    public PassengerManag2(FederateConfig params) throws Exception {
        super(params);
    }

    private void checkReceivedSubscriptions() {
        InteractionRoot interaction = null;
        while ((interaction = getNextInteractionNoWait()) != null) {
            if (interaction instanceof TransferPass) {
                handleInteractionClass((TransferPass) interaction);
            }
            else if (interaction instanceof RTPeopleFlow) {
                handleInteractionClass((RTPeopleFlow) interaction);
            }
            else if (interaction instanceof ArrivalPass) {
                handleInteractionClass((ArrivalPass) interaction);
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

            ////////////////////////////////////////////////////////////
            // TODO send interactions that must be sent every logical //
            // time step below                                        //
            ////////////////////////////////////////////////////////////

            // Set the interaction's parameters.
            //
            //    Airline2DeparturReady airline2DeparturReady = create_Airline2DeparturReady();
            //    airline2DeparturReady.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    airline2DeparturReady.set_airline( < YOUR VALUE HERE > );
            //    airline2DeparturReady.set_federateFilter( < YOUR VALUE HERE > );
            //    airline2DeparturReady.set_flightNumber( < YOUR VALUE HERE > );
            //    airline2DeparturReady.set_originFed( < YOUR VALUE HERE > );
            //    airline2DeparturReady.set_sourceFed( < YOUR VALUE HERE > );
            //    airline2DeparturReady.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    TransferPass transferPass = create_TransferPass();
            //    transferPass.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    transferPass.set_federateFilter( < YOUR VALUE HERE > );
            //    transferPass.set_originFed( < YOUR VALUE HERE > );
            //    transferPass.set_sourceFed( < YOUR VALUE HERE > );
            //    transferPass.set_transfPassNum( < YOUR VALUE HERE > );
            //    transferPass.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    RTPeopleFlow rTPeopleFlow = create_RTPeopleFlow();
            //    rTPeopleFlow.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    rTPeopleFlow.set_federateFilter( < YOUR VALUE HERE > );
            //    rTPeopleFlow.set_from( < YOUR VALUE HERE > );
            //    rTPeopleFlow.set_originFed( < YOUR VALUE HERE > );
            //    rTPeopleFlow.set_passengerNumber( < YOUR VALUE HERE > );
            //    rTPeopleFlow.set_sourceFed( < YOUR VALUE HERE > );
            //    rTPeopleFlow.set_to( < YOUR VALUE HERE > );
            //    rTPeopleFlow.sendInteraction(getLRC(), currentTime + getLookAhead());

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

    private void handleInteractionClass(TransferPass interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    private void handleInteractionClass(RTPeopleFlow interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    private void handleInteractionClass(ArrivalPass interaction) {
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
            PassengerManag2 federate =
                new PassengerManag2(federateConfig);
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
