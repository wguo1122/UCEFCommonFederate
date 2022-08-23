package org.webgme.guest.aircraftcoordinator;

import org.webgme.guest.aircraftcoordinator.rti.*;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



// Define the AircraftCoordinator type of federate for the federation.

public class AircraftCoordinator extends AircraftCoordinatorBase {
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;
    String flightNumber = new String ("ini");
    String depTime = new String("");
    String modelNum = new String("");
    double fuelLevel = 0;
    int passengerNum = 0;
    String deptReq = "1";
    String destination = new String("");


    public AircraftCoordinator(FederateConfig params) throws Exception {
        super(params);
    }

    private void checkReceivedSubscriptions() {
        InteractionRoot interaction = null;
        while ((interaction = getNextInteractionNoWait()) != null) {
            if (interaction instanceof DepartureFlightInfo) {
                handleInteractionClass((DepartureFlightInfo) interaction);
            }
            else if (interaction instanceof DayaheadFlightInfo) {
                handleInteractionClass((DayaheadFlightInfo) interaction);
            }
            else if (interaction instanceof DepartureConfirm) {
                handleInteractionClass((DepartureConfirm) interaction);
            }
            else if (interaction instanceof ArrivalRequest) {
                handleInteractionClass((ArrivalRequest) interaction);
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
            //    DayaheadSchedule dayaheadSchedule = create_DayaheadSchedule();
            //    dayaheadSchedule.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    dayaheadSchedule.set_federateFilter( < YOUR VALUE HERE > );
            //    dayaheadSchedule.set_originFed( < YOUR VALUE HERE > );
            //    dayaheadSchedule.set_slotSchedule( < YOUR VALUE HERE > );
            //    dayaheadSchedule.set_sourceFed( < YOUR VALUE HERE > );
            //    dayaheadSchedule.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    DayaheadScheduleConfirm dayaheadScheduleConfirm = create_DayaheadScheduleConfirm();
            //    dayaheadScheduleConfirm.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    dayaheadScheduleConfirm.set_dayAheadSchedule( < YOUR VALUE HERE > );
            //    dayaheadScheduleConfirm.set_federateFilter( < YOUR VALUE HERE > );
            //    dayaheadScheduleConfirm.set_originFed( < YOUR VALUE HERE > );
            //    dayaheadScheduleConfirm.set_sourceFed( < YOUR VALUE HERE > );
            //    dayaheadScheduleConfirm.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    DepartureRequest departureRequest = create_DepartureRequest();
            //    departureRequest.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    departureRequest.set_federateFilter( < YOUR VALUE HERE > );
            //    departureRequest.set_originFed( < YOUR VALUE HERE > );
            //    departureRequest.set_realTimeSchedule( < YOUR VALUE HERE > );
            //    departureRequest.set_sourceFed( < YOUR VALUE HERE > );
            //    departureRequest.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    DepartureFlightConfirm departureFlightConfirm = create_DepartureFlightConfirm();
            //    departureFlightConfirm.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    departureFlightConfirm.set_deptConfirm( < YOUR VALUE HERE > );
            //    departureFlightConfirm.set_federateFilter( < YOUR VALUE HERE > );
            //    departureFlightConfirm.set_gateAvailable( < YOUR VALUE HERE > );
            //    departureFlightConfirm.set_gateNum( < YOUR VALUE HERE > );
            //    departureFlightConfirm.set_originFed( < YOUR VALUE HERE > );
            //    departureFlightConfirm.set_sourceFed( < YOUR VALUE HERE > );
            //    departureFlightConfirm.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    ArrivalConfirm arrivalConfirm = create_ArrivalConfirm();
            //    arrivalConfirm.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_arrivalConf( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_arrivalTime( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_federateFilter( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_originFed( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_realTimeSchedule( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_runwayNum( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_sourceFed( < YOUR VALUE HERE > );
            //    arrivalConfirm.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    ArrivalFlightConfirm arrivalFlightConfirm = create_ArrivalFlightConfirm();
            //    arrivalFlightConfirm.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    arrivalFlightConfirm.set_federateFilter( < YOUR VALUE HERE > );
            //    arrivalFlightConfirm.set_flightID( < YOUR VALUE HERE > );
            //    arrivalFlightConfirm.set_flightModel( < YOUR VALUE HERE > );
            //    arrivalFlightConfirm.set_fuelLevel( < YOUR VALUE HERE > );
            //    arrivalFlightConfirm.set_originFed( < YOUR VALUE HERE > );
            //    arrivalFlightConfirm.set_passengerNum( < YOUR VALUE HERE > );
            //    arrivalFlightConfirm.set_sourceFed( < YOUR VALUE HERE > );
            //    arrivalFlightConfirm.sendInteraction(getLRC(), currentTime + getLookAhead());
            
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

    private void handleInteractionClass(DepartureFlightInfo interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
        flightNumber = interaction.get_flightNumber();
        depTime = interaction.get_depTime();
        modelNum = interaction.get_modelNum();
        fuelLevel = interaction.get_fuelLevel();
        passengerNum = interaction.get_passengerNum();
        deptReq = interaction.get_deptReq();
        destination = interaction.get_destination();

    }

    private void handleInteractionClass(DayaheadFlightInfo interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
    }

    private void handleInteractionClass(DepartureConfirm interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////

    }

    private void handleInteractionClass(ArrivalRequest interaction) {
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
            AircraftCoordinator federate =
                new AircraftCoordinator(federateConfig);
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
