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
            if (interaction instanceof RealtimePeopleArrival) {
                handleInteractionClass((RealtimePeopleArrival) interaction);
            }
            else if (interaction instanceof ArrivalTransferPass2) {
                handleInteractionClass((ArrivalTransferPass2) interaction);
            }
            else if (interaction instanceof PM1ToPM2Realtime) {
                handleInteractionClass((PM1ToPM2Realtime) interaction);
            }
            else if (interaction instanceof PM1ToPM2DayAhead) {
                handleInteractionClass((PM1ToPM2DayAhead) interaction);
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
            //    PM2ToPM1Realtime pM2ToPM1Realtime = create_PM2ToPM1Realtime();
            //    pM2ToPM1Realtime.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    pM2ToPM1Realtime.set_federateFilter( < YOUR VALUE HERE > );
            //    pM2ToPM1Realtime.set_originFed( < YOUR VALUE HERE > );
            //    pM2ToPM1Realtime.set_sourceFed( < YOUR VALUE HERE > );
            //    pM2ToPM1Realtime.set_transfPassNum( < YOUR VALUE HERE > );
            //    pM2ToPM1Realtime.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    TransferPassComfirm2 transferPassComfirm2 = create_TransferPassComfirm2();
            //    transferPassComfirm2.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    transferPassComfirm2.set_federateFilter( < YOUR VALUE HERE > );
            //    transferPassComfirm2.set_originFed( < YOUR VALUE HERE > );
            //    transferPassComfirm2.set_sourceFed( < YOUR VALUE HERE > );
            //    transferPassComfirm2.set_transferPassNum( < YOUR VALUE HERE > );
            //    transferPassComfirm2.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    PM2ToPM1DayAhead pM2ToPM1DayAhead = create_PM2ToPM1DayAhead();
            //    pM2ToPM1DayAhead.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    pM2ToPM1DayAhead.set_federateFilter( < YOUR VALUE HERE > );
            //    pM2ToPM1DayAhead.set_originFed( < YOUR VALUE HERE > );
            //    pM2ToPM1DayAhead.set_sourceFed( < YOUR VALUE HERE > );
            //    pM2ToPM1DayAhead.set_transfPassNum( < YOUR VALUE HERE > );
            //    pM2ToPM1DayAhead.sendInteraction(getLRC(), currentTime + getLookAhead());

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

    private void handleInteractionClass(ArrivalTransferPass2 interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    private void handleInteractionClass(PM1ToPM2Realtime interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    private void handleInteractionClass(PM1ToPM2DayAhead interaction) {
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
