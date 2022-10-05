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
        DepartureConfirm.publish(getLRC());
        ArrivalRequest.publish(getLRC());
        DayaheadSchedule.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           DayaheadSchedule.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);
        DepartureRequest.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           DepartureRequest.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);
        ArrivalConfirm.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           ArrivalConfirm.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);

        // object pubsub
    }

    public DepartureConfirm create_DepartureConfirm() {
        DepartureConfirm interaction = new DepartureConfirm();
        interaction.set_sourceFed(getFederateId());
        interaction.set_originFed(getFederateId());
        return interaction;
    }
    public ArrivalRequest create_ArrivalRequest() {
        ArrivalRequest interaction = new ArrivalRequest();
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
