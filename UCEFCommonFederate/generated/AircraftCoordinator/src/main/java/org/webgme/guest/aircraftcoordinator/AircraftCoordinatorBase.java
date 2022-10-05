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
        DayaheadSchedule.publish(getLRC());
        DayaheadScheduleConfirm.publish(getLRC());
        DepartureRequest.publish(getLRC());
        DepartureFlightConfirm.publish(getLRC());
        ArrivalConfirm.publish(getLRC());
        ArrivalFlightConfirm.publish(getLRC());
        DepartureConfirm.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           DepartureConfirm.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);
        DayaheadFlightInfo.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           DayaheadFlightInfo.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);
        ArrivalRequest.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           ArrivalRequest.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);
        DepartureFlightInfo.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           DepartureFlightInfo.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);

        // object pubsub
    }

    public DayaheadSchedule create_DayaheadSchedule() {
        DayaheadSchedule interaction = new DayaheadSchedule();
        interaction.set_sourceFed(getFederateId());
        interaction.set_originFed(getFederateId());
        return interaction;
    }
    public DayaheadScheduleConfirm create_DayaheadScheduleConfirm() {
        DayaheadScheduleConfirm interaction = new DayaheadScheduleConfirm();
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
    public DepartureFlightConfirm create_DepartureFlightConfirm() {
        DepartureFlightConfirm interaction = new DepartureFlightConfirm();
        interaction.set_sourceFed(getFederateId());
        interaction.set_originFed(getFederateId());
        return interaction;
    }
    public ArrivalConfirm create_ArrivalConfirm() {
        ArrivalConfirm interaction = new ArrivalConfirm();
        interaction.set_sourceFed(getFederateId());
        interaction.set_originFed(getFederateId());
        return interaction;
    }
    public ArrivalFlightConfirm create_ArrivalFlightConfirm() {
        ArrivalFlightConfirm interaction = new ArrivalFlightConfirm();
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
