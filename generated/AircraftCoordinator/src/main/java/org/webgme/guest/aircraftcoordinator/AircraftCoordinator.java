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

    ////////////////////////////////////////////////////////////////////////
    // TODO instantiate objects that must be sent every logical time step //
    ////////////////////////////////////////////////////////////////////////
    // RealTimeDepSche realTimeDepSche =
    //     new RealTimeDepSche();
    // RealTimeArriSche realTimeArriSche =
    //     new RealTimeArriSche();

    public AircraftCoordinator(FederateConfig params) throws Exception {
        super(params);

        //////////////////////////////////////////////////////
        // TODO register object instances after super(args) //
        //////////////////////////////////////////////////////
        // realTimeDepSche.registerObject(getLRC());
        // realTimeArriSche.registerObject(getLRC());
    }

    private void checkReceivedSubscriptions() {
        InteractionRoot interaction = null;
        while ((interaction = getNextInteractionNoWait()) != null) {
            if (interaction instanceof RealtimeArrivalRequest) {
                handleInteractionClass((RealtimeArrivalRequest) interaction);
            }
            else if (interaction instanceof DayaheadSchedule) {
                handleInteractionClass((DayaheadSchedule) interaction);
            }
            else if (interaction instanceof DepartureRequest) {
                handleInteractionClass((DepartureRequest) interaction);
            }
            else if (interaction instanceof Aircraft) {
                handleInteractionClass((Aircraft) interaction);
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
            //    FlightRemovalNotification flightRemovalNotification = create_FlightRemovalNotification();
            //    flightRemovalNotification.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    flightRemovalNotification.set_airline( < YOUR VALUE HERE > );
            //    flightRemovalNotification.set_federateFilter( < YOUR VALUE HERE > );
            //    flightRemovalNotification.set_flightNumber( < YOUR VALUE HERE > );
            //    flightRemovalNotification.set_originFed( < YOUR VALUE HERE > );
            //    flightRemovalNotification.set_sourceFed( < YOUR VALUE HERE > );
            //    flightRemovalNotification.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    ArrivalConfirm arrivalConfirm = create_ArrivalConfirm();
            //    arrivalConfirm.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_airline( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_arrivalTime( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_federateFilter( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_flightNum( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_gateNum( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_originFed( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_runwayNum( < YOUR VALUE HERE > );
            //    arrivalConfirm.set_sourceFed( < YOUR VALUE HERE > );
            //    arrivalConfirm.sendInteraction(getLRC(), currentTime + getLookAhead());
            //    Aircraft aircraft = create_Aircraft();
            //    aircraft.set_actualLogicalGenerationTime( < YOUR VALUE HERE > );
            //    aircraft.set_airline( < YOUR VALUE HERE > );
            //    aircraft.set_federateFilter( < YOUR VALUE HERE > );
            //    aircraft.set_flightNumber( < YOUR VALUE HERE > );
            //    aircraft.set_originFed( < YOUR VALUE HERE > );
            //    aircraft.set_sourceFed( < YOUR VALUE HERE > );
            //    aircraft.sendInteraction(getLRC(), currentTime + getLookAhead());

            ////////////////////////////////////////////////////////////
            // TODO objects that must be sent every logical time step //
            ////////////////////////////////////////////////////////////
            //    realTimeDepSche.set_aircraft(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_airline(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_booked(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_capacity(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_checkedIn(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_depStatus(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_estimated(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_flightNumber(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_gate(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_scheduledDepTime(<YOUR VALUE HERE >);
            //    realTimeDepSche.set_transfer(<YOUR VALUE HERE >);
            //    realTimeDepSche.updateAttributeValues(getLRC(), currentTime + getLookAhead());
            //    realTimeArriSche.set_aircraft(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_airline(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_arrivalStatus(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_booked(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_capacity(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_checkedIn(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_estimatedArrivalTime(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_flightNumber(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_gate(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_passNum(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_scheduledArrivalTime(<YOUR VALUE HERE >);
            //    realTimeArriSche.set_transferOut(<YOUR VALUE HERE >);
            //    realTimeArriSche.updateAttributeValues(getLRC(), currentTime + getLookAhead());

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

    private void handleInteractionClass(RealtimeArrivalRequest interaction) {
        ///////////////////////////////////////////////////////////////
        // TODO implement how to handle reception of the interaction //
        ///////////////////////////////////////////////////////////////
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

    private void handleInteractionClass(Aircraft interaction) {
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
