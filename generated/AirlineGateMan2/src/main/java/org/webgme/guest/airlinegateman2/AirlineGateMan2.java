package org.webgme.guest.airlinegateman2;

import org.webgme.guest.airlinegateman2.rti.*;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



// Define the AirlineGateMan2 type of federate for the federation.

public class AirlineGateMan2 extends AirlineGateMan2Base {
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;

    public AirlineGateMan2(FederateConfig params) throws Exception {
        super(params);
    }

    private void checkReceivedSubscriptions() {
        InteractionRoot interaction = null;
        while ((interaction = getNextInteractionNoWait()) != null) {
            if (interaction instanceof DayaheadScheduleConfirm) {
                handleInteractionClass((DayaheadScheduleConfirm) interaction);
            }
            else if (interaction instanceof TransferPassComfirm2) {
                handleInteractionClass((TransferPassComfirm2) interaction);
            }
            else if (interaction instanceof ArrivalFlightConfirm) {
                handleInteractionClass((ArrivalFlightConfirm) interaction);
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
            //    DepartureFlightInfo departureFlightInfo = create_DepartureFlightInfo();
            //    departureFlightInfo.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    departureFlightInfo.set_depTime( < YOUR VALUE HERE > );
            //    departureFlightInfo.set_deptReq( < YOUR VALUE HERE > );
            //    departureFlightInfo.set_destination( < YOUR VALUE HERE > );
            //    departureFlightInfo.set_federateFilter( < YOUR VALUE HERE > );
            //    departureFlightInfo.set_flightNumber( < YOUR VALUE HERE > );
            //    departureFlightInfo.set_fuelLevel( < YOUR VALUE HERE > );
            //    departureFlightInfo.set_modelNum( < YOUR VALUE HERE > );
            //    departureFlightInfo.set_originFed( < YOUR VALUE HERE > );
            //    departureFlightInfo.set_passengerNum( < YOUR VALUE HERE > );
            //    departureFlightInfo.set_sourceFed( < YOUR VALUE HERE > );
            //    departureFlightInfo.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    DayaheadFlightInfo dayaheadFlightInfo = create_DayaheadFlightInfo();
            //    dayaheadFlightInfo.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.set_depTime( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.set_destination( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.set_federateFilter( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.set_flightNumber( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.set_fuelLevel( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.set_modelNum( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.set_originFed( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.set_passengerNum( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.set_sourceFed( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    ArrivalTransferPass2 arrivalTransferPass2 = create_ArrivalTransferPass2();
            //    arrivalTransferPass2.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    arrivalTransferPass2.set_arrivalNotification( < YOUR VALUE HERE > );
            //    arrivalTransferPass2.set_federateFilter( < YOUR VALUE HERE > );
            //    arrivalTransferPass2.set_originFed( < YOUR VALUE HERE > );
            //    arrivalTransferPass2.set_sourceFed( < YOUR VALUE HERE > );
            //    arrivalTransferPass2.set_transferPassNum( < YOUR VALUE HERE > );
            //    arrivalTransferPass2.sendInteraction(getLRC(), currentTime + getLookAhead());

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

    private void handleInteractionClass(DayaheadScheduleConfirm interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    private void handleInteractionClass(TransferPassComfirm2 interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    private void handleInteractionClass(ArrivalFlightConfirm interaction) {
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
            AirlineGateMan2 federate =
                new AirlineGateMan2(federateConfig);
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
