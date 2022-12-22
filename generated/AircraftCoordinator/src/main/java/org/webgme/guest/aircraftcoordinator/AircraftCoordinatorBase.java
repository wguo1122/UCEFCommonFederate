package org.webgme.guest.aircraftcoordinator;

import org.webgme.guest.aircraftcoordinator.rti.*;

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


public class AircraftCoordinatorBase extends SynchronizedFederate {
    private SubscribedInteractionFilter _subscribedInteractionFilter =
        new SubscribedInteractionFilter();

    // constructor
    public AircraftCoordinatorBase(FederateConfig config) throws Exception {
        super(config);
        super.createLRC();
        super.joinFederation();
        enableTimeConstrained();
        enableTimeRegulation(getLookAhead());
        enableAsynchronousDelivery();

        // interaction pubsub
        ArrivalConfirm.publish(getLRC());
        Aircraft.publish(getLRC());
        DepartureRequest.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           DepartureRequest.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);
        RealtimeArrivalRequest.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           RealtimeArrivalRequest.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);
        DayaheadSchedule.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           DayaheadSchedule.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);
        Aircraft.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           Aircraft.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);

        // object pubsub
        RealTimeDepSche.publish_aircraft();
        RealTimeDepSche.publish_airline();
        RealTimeDepSche.publish_booked();
        RealTimeDepSche.publish_capacity();
        RealTimeDepSche.publish_checkedIn();
        RealTimeDepSche.publish_depStatus();
        RealTimeDepSche.publish_estimated();
        RealTimeDepSche.publish_flightNumber();
        RealTimeDepSche.publish_gate();
        RealTimeDepSche.publish_scheduledDepTime();
        RealTimeDepSche.publish_transfer();
        RealTimeDepSche.publish(getLRC());
        RealTimeArriSche.publish_aircraft();
        RealTimeArriSche.publish_airline();
        RealTimeArriSche.publish_arrivalStatus();
        RealTimeArriSche.publish_booked();
        RealTimeArriSche.publish_capacity();
        RealTimeArriSche.publish_checkedIn();
        RealTimeArriSche.publish_estimatedArrivalTime();
        RealTimeArriSche.publish_flightNumber();
        RealTimeArriSche.publish_gate();
        RealTimeArriSche.publish_passNum();
        RealTimeArriSche.publish_scheduledArrivalTime();
        RealTimeArriSche.publish_transferOut();
        RealTimeArriSche.publish(getLRC());
    }

    public ArrivalConfirm create_ArrivalConfirm() {
        ArrivalConfirm interaction = new ArrivalConfirm();
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
