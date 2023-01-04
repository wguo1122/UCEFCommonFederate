package org.webgme.guest.airline1manager;

import org.webgme.guest.airline1manager.rti.*;

import hla.rti.EventRetractionHandle;
import hla.rti.LogicalTime;
import hla.rti.ReceivedInteraction;

import org.cpswt.hla.C2WInteractionRoot;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.SubscribedInteractionFilter;
import org.cpswt.hla.SynchronizedFederate;

import org.cpswt.config.FederateConfig;
import org.cpswt.utils.CpswtDefaults;

import org.cpswt.*;


public class Airline1ManagerBase extends SynchronizedFederate {
    private SubscribedInteractionFilter _subscribedInteractionFilter =
        new SubscribedInteractionFilter();

    // constructor
    public Airline1ManagerBase(FederateConfig config) throws Exception {
        super(config);
        super.createLRC();
        super.joinFederation();
        enableTimeConstrained();
        enableTimeRegulation(getLookAhead());
        enableAsynchronousDelivery();

        // interaction pubsub
        ArrivalPass.publish(getLRC());
        DayaheadSchedule.publish(getLRC());
        DepartureRequest.publish(getLRC());
        Airline1DeparturReady.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           Airline1DeparturReady.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);
        FlightRemovalNotification.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           FlightRemovalNotification.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);

        // object pubsub
        RealTimeDepSche.subscribe_aircraft();
        RealTimeDepSche.subscribe_airline();
        RealTimeDepSche.subscribe_booked();
        RealTimeDepSche.subscribe_capacity();
        RealTimeDepSche.subscribe_checkedIn();
        RealTimeDepSche.subscribe_depStatus();
        RealTimeDepSche.subscribe_estimated();
        RealTimeDepSche.subscribe_flightNumber();
        RealTimeDepSche.subscribe_gate();
        RealTimeDepSche.subscribe_scheduledDepTime();
        RealTimeDepSche.subscribe_transfer();
        RealTimeDepSche.subscribe(getLRC());
        RealTimeArriSche.subscribe_aircraft();
        RealTimeArriSche.subscribe_airline();
        RealTimeArriSche.subscribe_arrivalStatus();
        RealTimeArriSche.subscribe_booked();
        RealTimeArriSche.subscribe_capacity();
        RealTimeArriSche.subscribe_checkedIn();
        RealTimeArriSche.subscribe_estimatedArrivalTime();
        RealTimeArriSche.subscribe_flightNumber();
        RealTimeArriSche.subscribe_gate();
        RealTimeArriSche.subscribe_passNum();
        RealTimeArriSche.subscribe_scheduledArrivalTime();
        RealTimeArriSche.subscribe_transferOut();
        RealTimeArriSche.subscribe(getLRC());
    }

    public ArrivalPass create_ArrivalPass() {
        ArrivalPass interaction = new ArrivalPass();
        interaction.set_sourceFed(getFederateId());
        interaction.set_originFed(getFederateId());
        return interaction;
    }
    public DayaheadSchedule create_DayaheadSchedule() {
        DayaheadSchedule interaction = new DayaheadSchedule();
        interaction.set_sourceFed(getFederateId());
        interaction.set_originFed(getFederateId());
        return interaction;
    }
    public DepartureRequest create_DepartureRequest() {
        DepartureRequest interaction = new DepartureRequest();
        interaction.set_sourceFed(getFederateId());
        interaction.set_originFed(getFederateId());
        return interaction;
    }

    @Override
    public void receiveInteraction(int interactionClass,
                                   ReceivedInteraction theInteraction,
                                   byte[] userSuppliedTag) {
        InteractionRoot interactionRoot =
            InteractionRoot.create_interaction(interactionClass,
                                               theInteraction);
        if (interactionRoot instanceof C2WInteractionRoot) {
            C2WInteractionRoot c2wInteractionRoot =
                (C2WInteractionRoot)interactionRoot;

            // Filter interaction if src/origin fed requirements (if any)
            // are not met
            if (_subscribedInteractionFilter.filterC2WInteraction
                (getFederateId(), c2wInteractionRoot)) {
                return;
            }
        }
        super.receiveInteraction(interactionClass, theInteraction,
                                 userSuppliedTag);
    }

    @Override
    public void receiveInteraction(int interactionClass,
                                   ReceivedInteraction theInteraction,
                                   byte[] userSuppliedTag,
                                   LogicalTime theTime,
                                   EventRetractionHandle retractionHandle) {
        InteractionRoot interactionRoot =
            InteractionRoot.create_interaction(interactionClass,
                                               theInteraction, theTime);
        if (interactionRoot instanceof C2WInteractionRoot) {
            C2WInteractionRoot c2wInteractionRoot =
                (C2WInteractionRoot)interactionRoot;

            // Filter interaction if src/origin fed requirements (if any)
            // are not met
            if (_subscribedInteractionFilter.filterC2WInteraction
                (getFederateId(), c2wInteractionRoot)) {
                return;
            }
        }
        super.receiveInteraction(interactionClass, theInteraction,
                                 userSuppliedTag, theTime, retractionHandle);
    }
}
