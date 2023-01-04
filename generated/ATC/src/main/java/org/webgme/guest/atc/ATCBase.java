package org.webgme.guest.atc;

import org.webgme.guest.atc.rti.*;

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


public class ATCBase extends SynchronizedFederate {
    private SubscribedInteractionFilter _subscribedInteractionFilter =
        new SubscribedInteractionFilter();

    // constructor
    public ATCBase(FederateConfig config) throws Exception {
        super(config);
        super.createLRC();
        super.joinFederation();
        enableTimeConstrained();
        enableTimeRegulation(getLookAhead());
        enableAsynchronousDelivery();

        // interaction pubsub
        RealtimeArrivalRequest.publish(getLRC());
        Aircraft.publish(getLRC());
        FlightRemovalNotification.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           FlightRemovalNotification.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);
        ArrivalConfirm.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           ArrivalConfirm.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);
        Aircraft.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           Aircraft.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);

        // object pubsub
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

    public RealtimeArrivalRequest create_RealtimeArrivalRequest() {
        RealtimeArrivalRequest interaction = new RealtimeArrivalRequest();
        interaction.set_sourceFed(getFederateId());
        interaction.set_originFed(getFederateId());
        return interaction;
    }
    public Aircraft create_Aircraft() {
        Aircraft interaction = new Aircraft();
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
