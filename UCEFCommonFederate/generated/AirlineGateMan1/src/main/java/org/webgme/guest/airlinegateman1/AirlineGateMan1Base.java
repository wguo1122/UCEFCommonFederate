package org.webgme.guest.airlinegateman1;

import org.webgme.guest.airlinegateman1.rti.*;

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


public class AirlineGateMan1Base extends SynchronizedFederate {
    private SubscribedInteractionFilter _subscribedInteractionFilter =
        new SubscribedInteractionFilter();

    // constructor
    public AirlineGateMan1Base(FederateConfig config) throws Exception {
        super(config);
        super.createLRC();
        super.joinFederation();
        enableTimeConstrained();
        enableTimeRegulation(getLookAhead());
        enableAsynchronousDelivery();

        // interaction pubsub
        DayaheadFlightInfo.publish(getLRC());
        ArrivalTransferPass1.publish(getLRC());
        DepartureFlightInfo.publish(getLRC());
        DayaheadScheduleConfirm.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           DayaheadScheduleConfirm.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);
        TransferPassConfirm1.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           TransferPassConfirm1.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);
        DepartureFlightConfirm.subscribe(getLRC());
        _subscribedInteractionFilter.setFedFilters( 
           DepartureFlightConfirm.get_handle(),
           SubscribedInteractionFilter.OriginFedFilter.ORIGIN_FILTER_DISABLED,
           SubscribedInteractionFilter.SourceFedFilter.SOURCE_FILTER_DISABLED);

        // object pubsub
    }

    public DayaheadFlightInfo create_DayaheadFlightInfo() {
        DayaheadFlightInfo interaction = new DayaheadFlightInfo();
        interaction.set_sourceFed(getFederateId());
        interaction.set_originFed(getFederateId());
        return interaction;
    }
    public ArrivalTransferPass1 create_ArrivalTransferPass1() {
        ArrivalTransferPass1 interaction = new ArrivalTransferPass1();
        interaction.set_sourceFed(getFederateId());
        interaction.set_originFed(getFederateId());
        return interaction;
    }
    public DepartureFlightInfo create_DepartureFlightInfo() {
        DepartureFlightInfo interaction = new DepartureFlightInfo();
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
