package org.webgme.guest.atc;

import org.webgme.guest.atc.rti.*;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateConfigParser;
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
            if (interaction instanceof DayaheadSchedule) {
                handleInteractionClass((DayaheadSchedule) interaction);
            }
            else if (interaction instanceof DepartureRequest) {
                handleInteractionClass((DepartureRequest) interaction);
            }
            else if (interaction instanceof ArrivalConfirm) {
                handleInteractionClass((ArrivalConfirm) interaction);
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
            //    DepartureConfirm departureConfirm = create_DepartureConfirm();
            //    departureConfirm.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    departureConfirm.set_depConfirm( < YOUR VALUE HERE > );
            //    departureConfirm.set_federateFilter( < YOUR VALUE HERE > );
            //    departureConfirm.set_originFed( < YOUR VALUE HERE > );
            //    departureConfirm.set_runwayNum( < YOUR VALUE HERE > );
            //    departureConfirm.set_sourceFed( < YOUR VALUE HERE > );
            //    departureConfirm.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    ArrivalRequest arrivalRequest = create_ArrivalRequest();
            //    arrivalRequest.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    arrivalRequest.set_federateFilter( < YOUR VALUE HERE > );
            //    arrivalRequest.set_landingReq( < YOUR VALUE HERE > );
            //    arrivalRequest.set_originFed( < YOUR VALUE HERE > );
            //    arrivalRequest.set_sourceFed( < YOUR VALUE HERE > );
            //    arrivalRequest.sendInteraction(getLRC(), currentTime + getLookAhead());

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

    private void handleInteractionClass(DayaheadSchedule interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    private void handleInteractionClass(DepartureRequest interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    private void handleInteractionClass(ArrivalConfirm interaction) {
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
