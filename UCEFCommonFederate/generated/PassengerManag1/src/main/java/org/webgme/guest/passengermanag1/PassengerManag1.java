package org.webgme.guest.passengermanag1;

import org.webgme.guest.passengermanag1.rti.*;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



// Define the PassengerManag1 type of federate for the federation.

public class PassengerManag1 extends PassengerManag1Base {
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;

    public PassengerManag1(FederateConfig params) throws Exception {
        super(params);
    }

    private void checkReceivedSubscriptions() {
        InteractionRoot interaction = null;
        while ((interaction = getNextInteractionNoWait()) != null) {
            if (interaction instanceof RealtimePeopleArrival) {
                handleInteractionClass((RealtimePeopleArrival) interaction);
            }
            else if (interaction instanceof ArrivalTransferPass1) {
                handleInteractionClass((ArrivalTransferPass1) interaction);
            }
            else if (interaction instanceof PM2ToPM1Realtime) {
                handleInteractionClass((PM2ToPM1Realtime) interaction);
            }
            else if (interaction instanceof PM2ToPM1DayAhead) {
                handleInteractionClass((PM2ToPM1DayAhead) interaction);
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
            //    DayAheadPeopleInfo dayAheadPeopleInfo = create_DayAheadPeopleInfo();
            //    dayAheadPeopleInfo.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    dayAheadPeopleInfo.set_federateFilter( < YOUR VALUE HERE > );
            //    dayAheadPeopleInfo.set_originFed( < YOUR VALUE HERE > );
            //    dayAheadPeopleInfo.set_passengerNum( < YOUR VALUE HERE > );
            //    dayAheadPeopleInfo.set_sourceFed( < YOUR VALUE HERE > );
            //    dayAheadPeopleInfo.set_transferPassNum( < YOUR VALUE HERE > );
            //    dayAheadPeopleInfo.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    TransferPassConfirm1 transferPassConfirm1 = create_TransferPassConfirm1();
            //    transferPassConfirm1.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    transferPassConfirm1.set_federateFilter( < YOUR VALUE HERE > );
            //    transferPassConfirm1.set_originFed( < YOUR VALUE HERE > );
            //    transferPassConfirm1.set_sourceFed( < YOUR VALUE HERE > );
            //    transferPassConfirm1.set_transferPassNum( < YOUR VALUE HERE > );
            //    transferPassConfirm1.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    PM1ToPM2Realtime pM1ToPM2Realtime = create_PM1ToPM2Realtime();
            //    pM1ToPM2Realtime.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    pM1ToPM2Realtime.set_federateFilter( < YOUR VALUE HERE > );
            //    pM1ToPM2Realtime.set_originFed( < YOUR VALUE HERE > );
            //    pM1ToPM2Realtime.set_sourceFed( < YOUR VALUE HERE > );
            //    pM1ToPM2Realtime.set_transfPassNum( < YOUR VALUE HERE > );
            //    pM1ToPM2Realtime.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    PM1ToPM2DayAhead pM1ToPM2DayAhead = create_PM1ToPM2DayAhead();
            //    pM1ToPM2DayAhead.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    pM1ToPM2DayAhead.set_federateFilter( < YOUR VALUE HERE > );
            //    pM1ToPM2DayAhead.set_originFed( < YOUR VALUE HERE > );
            //    pM1ToPM2DayAhead.set_sourceFed( < YOUR VALUE HERE > );
            //    pM1ToPM2DayAhead.set_transfPassNum( < YOUR VALUE HERE > );
            //    pM1ToPM2DayAhead.sendInteraction(getLRC(), currentTime + getLookAhead());

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

    private void handleInteractionClass(RealtimePeopleArrival interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    private void handleInteractionClass(ArrivalTransferPass1 interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    private void handleInteractionClass(PM2ToPM1Realtime interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    private void handleInteractionClass(PM2ToPM1DayAhead interaction) {
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
            PassengerManag1 federate =
                new PassengerManag1(federateConfig);
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
