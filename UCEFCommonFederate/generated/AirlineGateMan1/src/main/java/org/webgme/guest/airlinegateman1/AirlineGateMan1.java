package org.webgme.guest.airlinegateman1;

import org.webgme.guest.airlinegateman1.rti.*;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



// Define the AirlineGateMan1 type of federate for the federation.

public class AirlineGateMan1 extends AirlineGateMan1Base {
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;   
    String flightNumber = "UA1234";
    String depTime = "08-22-2022 8:01am";
    String modelNum = "A320-200";
    double fuelLevel = 23430.00;
    int passengerNum = 100;
    String deptReq = "1";
    String destination = "New York, NY";

    public AirlineGateMan1(FederateConfig params) throws Exception {
        super(params);
    }

    private void checkReceivedSubscriptions() {
        InteractionRoot interaction = null;
        while ((interaction = getNextInteractionNoWait()) != null) {
            if (interaction instanceof DayaheadScheduleConfirm) {
                handleInteractionClass((DayaheadScheduleConfirm) interaction);
            }
            else if (interaction instanceof DepartureFlightConfirm) {
                handleInteractionClass((DepartureFlightConfirm) interaction);
            }
            else if (interaction instanceof TransferPassConfirm1) {
                handleInteractionClass((TransferPassConfirm1) interaction);
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
            //    DayaheadFlightInfo dayaheadFlightInfo = create_DayaheadFlightInfo();
            //    dayaheadFlightInfo.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.set_depTime( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.set_destination( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.set_federateFilter( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.set_flightNumber(flightNumber);
            //    dayaheadFlightInfo.set_fuelLevel( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.set_modelNum( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.set_originFed( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.set_passengerNum( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.set_sourceFed( < YOUR VALUE HERE > );
            //    dayaheadFlightInfo.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    ArrivalTransferPass1 arrivalTransferPass1 = create_ArrivalTransferPass1();
            //    arrivalTransferPass1.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    arrivalTransferPass1.set_arrivalNotification( < YOUR VALUE HERE > );
            //    arrivalTransferPass1.set_federateFilter( < YOUR VALUE HERE > );
            //    arrivalTransferPass1.set_originFed( < YOUR VALUE HERE > );
            //    arrivalTransferPass1.set_sourceFed( < YOUR VALUE HERE > );
            //    arrivalTransferPass1.set_transferPassNum( < YOUR VALUE HERE > );
            //    arrivalTransferPass1.sendInteraction(getLRC(), currentTime + getLookAhead());
                DepartureFlightInfo departureFlightInfo = create_DepartureFlightInfo();
            //    departureFlightInfo.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
                departureFlightInfo.set_depTime(depTime);
                departureFlightInfo.set_deptReq(deptReq);
                departureFlightInfo.set_destination(destination);
            //    departureFlightInfo.set_federateFilter( < YOUR VALUE HERE > );
                departureFlightInfo.set_flightNumber(flightNumber);
                departureFlightInfo.set_fuelLevel( fuelLevel);
                departureFlightInfo.set_modelNum( modelNum);
            //    departureFlightInfo.set_originFed( < YOUR VALUE HERE > );
                departureFlightInfo.set_passengerNum( passengerNum);
            //    departureFlightInfo.set_sourceFed( < YOUR VALUE HERE > );
                departureFlightInfo.sendInteraction(getLRC(), currentTime + getLookAhead());
                System.out.println("The flight number is: " + flightNumber);
                System.out.println("The departure time is: " + depTime);
                System.out.println("The flight model number is: " + modelNum);
                System.out.println("The fuel level is: " + String.valueOf(fuelLevel));              
                System.out.println("The total passenger number: " + String.valueOf(passengerNum));
                System.out.println("The departure request is: " + deptReq);
                System.out.println("The detination is: " + destination);
                
               

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

    private void handleInteractionClass(DepartureFlightConfirm interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////

    }

    private void handleInteractionClass(TransferPassConfirm1 interaction) {
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
            AirlineGateMan1 federate =
                new AirlineGateMan1(federateConfig);
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
