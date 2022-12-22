package org.webgme.guest.airline1manager.rti;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cpswt.utils.CpswtUtils;

import hla.rti.FederateNotExecutionMember;
import hla.rti.InteractionClassNotDefined;
import hla.rti.InteractionClassNotPublished;
import hla.rti.InteractionClassNotSubscribed;
import hla.rti.LogicalTime;
import hla.rti.NameNotFound;
import hla.rti.RTIambassador;
import hla.rti.ReceivedInteraction;

import org.cpswt.hla.*;

/**
* Implements InteractionRoot.C2WInteractionRoot.DayaheadSchedule
*/
public class DayaheadSchedule extends C2WInteractionRoot {

    private static final Logger logger = LogManager.getLogger();

    /**
    * Creates an instance of the DayaheadSchedule interaction class with default parameter values.
    */
    public DayaheadSchedule() {}

    private static int _InboundOutbound_handle;
    private static int _actualLogicalGenerationTime_handle;
    private static int _aircraft_handle;
    private static int _airline_handle;
    private static int _booked_handle;
    private static int _capacity_handle;
    private static int _federateFilter_handle;
    private static int _flightNum_handle;
    private static int _flightStatus_handle;
    private static int _gateNum_handle;
    private static int _originFed_handle;
    private static int _scheduledTime_handle;
    private static int _sourceFed_handle;
    private static int _transfer_handle;

    private static boolean _isInitialized = false;

    private static int _handle;

    /**
    * Returns the handle (RTI assigned) of the DayaheadSchedule interaction class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return the handle of the class pertaining to the reference,
    * rather than the handle of the class for the instance referred to by the reference.
    * For the polymorphic version of this method, use {@link #getClassHandle()}.
    *
    * @return the RTI assigned integer handle that represents this interaction class
    */
    public static int get_handle() {
        return _handle;
    }

    /**
    * Returns the fully-qualified (dot-delimited) name of the DayaheadSchedule interaction class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return the name of the class pertaining to the reference,
    * rather than the name of the class for the instance referred to by the reference.
    * For the polymorphic version of this method, use {@link #getClassName()}.
    *
    * @return the fully-qualified HLA class path for this interaction class
    */
    public static String get_class_name() {
        return "InteractionRoot.C2WInteractionRoot.DayaheadSchedule";
    }

    /**
    * Returns the simple name (the last name in the dot-delimited fully-qualified
    * class name) of the DayaheadSchedule interaction class.
    *
    * @return the name of this interaction class
    */
    public static String get_simple_class_name() {
        return "DayaheadSchedule";
    }

    private static Set< String > _datamemberNames = new HashSet< String >();
    private static Set< String > _allDatamemberNames = new HashSet< String >();

    /**
    * Returns a set containing the names of all of the non-hidden parameters in the
    * DayaheadSchedule interaction class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return a set of parameter names pertaining to the reference,
    * rather than the parameter names of the class for the instance referred to by
    * the reference.  For the polymorphic version of this method, use
    * {@link #getParameterNames()}.
    *
    * @return a modifiable set of the non-hidden parameter names for this interaction class
    */
    public static Set< String > get_parameter_names() {
        return new HashSet< String >(_datamemberNames);
    }

    /**
    * Returns a set containing the names of all of the parameters in the
    * DayaheadSchedule interaction class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return a set of parameter names pertaining to the reference,
    * rather than the parameter names of the class for the instance referred to by
    * the reference.  For the polymorphic version of this method, use
    * {@link #getParameterNames()}.
    *
    * @return a modifiable set of the parameter names for this interaction class
    */
    public static Set< String > get_all_parameter_names() {
        return new HashSet< String >(_allDatamemberNames);
    }

    static {
        _classNameSet.add("InteractionRoot.C2WInteractionRoot.DayaheadSchedule");
        _classNameClassMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule", DayaheadSchedule.class);

        _datamemberClassNameSetMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule", _datamemberNames);
        _allDatamemberClassNameSetMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule", _allDatamemberNames);

        _datamemberNames.add("InboundOutbound");
        _datamemberNames.add("aircraft");
        _datamemberNames.add("airline");
        _datamemberNames.add("booked");
        _datamemberNames.add("capacity");
        _datamemberNames.add("flightNum");
        _datamemberNames.add("flightStatus");
        _datamemberNames.add("gateNum");
        _datamemberNames.add("scheduledTime");
        _datamemberNames.add("transfer");

        _datamemberTypeMap.put("InboundOutbound", "boolean");
        _datamemberTypeMap.put("aircraft", "String");
        _datamemberTypeMap.put("airline", "String");
        _datamemberTypeMap.put("booked", "int");
        _datamemberTypeMap.put("capacity", "int");
        _datamemberTypeMap.put("flightNum", "String");
        _datamemberTypeMap.put("flightStatus", "String");
        _datamemberTypeMap.put("gateNum", "String");
        _datamemberTypeMap.put("scheduledTime", "String");
        _datamemberTypeMap.put("transfer", "int");

        _allDatamemberNames.add("InboundOutbound");
        _allDatamemberNames.add("actualLogicalGenerationTime");
        _allDatamemberNames.add("aircraft");
        _allDatamemberNames.add("airline");
        _allDatamemberNames.add("booked");
        _allDatamemberNames.add("capacity");
        _allDatamemberNames.add("federateFilter");
        _allDatamemberNames.add("flightNum");
        _allDatamemberNames.add("flightStatus");
        _allDatamemberNames.add("gateNum");
        _allDatamemberNames.add("originFed");
        _allDatamemberNames.add("scheduledTime");
        _allDatamemberNames.add("sourceFed");
        _allDatamemberNames.add("transfer");
    }

    protected static void init(RTIambassador rti) {
        if (_isInitialized) return;
        _isInitialized = true;

        C2WInteractionRoot.init(rti);

        boolean isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _handle = rti.getInteractionClassHandle("InteractionRoot.C2WInteractionRoot.DayaheadSchedule");
                isNotInitialized = false;
            } catch (FederateNotExecutionMember e) {
                logger.error("could not initialize: Federate Not Execution Member", e);
                return;
            } catch (NameNotFound e) {
                logger.error("could not initialize: Name Not Found", e);
                return;
            } catch (Exception e) {
                logger.error(e);
                CpswtUtils.sleepDefault();
            }
        }

        _classNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule", get_handle());
        _classHandleNameMap.put(get_handle(), "InteractionRoot.C2WInteractionRoot.DayaheadSchedule");
        _classHandleSimpleNameMap.put(get_handle(), "DayaheadSchedule");

        isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _InboundOutbound_handle = rti.getParameterHandle("InboundOutbound", get_handle());
                _actualLogicalGenerationTime_handle = rti.getParameterHandle("actualLogicalGenerationTime", get_handle());
                _aircraft_handle = rti.getParameterHandle("aircraft", get_handle());
                _airline_handle = rti.getParameterHandle("airline", get_handle());
                _booked_handle = rti.getParameterHandle("booked", get_handle());
                _capacity_handle = rti.getParameterHandle("capacity", get_handle());
                _federateFilter_handle = rti.getParameterHandle("federateFilter", get_handle());
                _flightNum_handle = rti.getParameterHandle("flightNum", get_handle());
                _flightStatus_handle = rti.getParameterHandle("flightStatus", get_handle());
                _gateNum_handle = rti.getParameterHandle("gateNum", get_handle());
                _originFed_handle = rti.getParameterHandle("originFed", get_handle());
                _scheduledTime_handle = rti.getParameterHandle("scheduledTime", get_handle());
                _sourceFed_handle = rti.getParameterHandle("sourceFed", get_handle());
                _transfer_handle = rti.getParameterHandle("transfer", get_handle());
                isNotInitialized = false;
            } catch (FederateNotExecutionMember e) {
                logger.error("could not initialize: Federate Not Execution Member", e);
                return;
            } catch (InteractionClassNotDefined e) {
                logger.error("could not initialize: Interaction Class Not Defined", e);
                return;
            } catch (NameNotFound e) {
                logger.error("could not initialize: Name Not Found", e);
                return;
            } catch (Exception e) {
                logger.error(e);
                CpswtUtils.sleepDefault();
            }
        }

        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule.InboundOutbound", _InboundOutbound_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule.actualLogicalGenerationTime", _actualLogicalGenerationTime_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule.aircraft", _aircraft_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule.airline", _airline_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule.booked", _booked_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule.capacity", _capacity_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule.federateFilter", _federateFilter_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule.flightNum", _flightNum_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule.flightStatus", _flightStatus_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule.gateNum", _gateNum_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule.originFed", _originFed_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule.scheduledTime", _scheduledTime_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule.sourceFed", _sourceFed_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadSchedule.transfer", _transfer_handle);

        _datamemberHandleNameMap.put(_InboundOutbound_handle, "InboundOutbound");
        _datamemberHandleNameMap.put(_actualLogicalGenerationTime_handle, "actualLogicalGenerationTime");
        _datamemberHandleNameMap.put(_aircraft_handle, "aircraft");
        _datamemberHandleNameMap.put(_airline_handle, "airline");
        _datamemberHandleNameMap.put(_booked_handle, "booked");
        _datamemberHandleNameMap.put(_capacity_handle, "capacity");
        _datamemberHandleNameMap.put(_federateFilter_handle, "federateFilter");
        _datamemberHandleNameMap.put(_flightNum_handle, "flightNum");
        _datamemberHandleNameMap.put(_flightStatus_handle, "flightStatus");
        _datamemberHandleNameMap.put(_gateNum_handle, "gateNum");
        _datamemberHandleNameMap.put(_originFed_handle, "originFed");
        _datamemberHandleNameMap.put(_scheduledTime_handle, "scheduledTime");
        _datamemberHandleNameMap.put(_sourceFed_handle, "sourceFed");
        _datamemberHandleNameMap.put(_transfer_handle, "transfer");
    }

    private static boolean _isPublished = false;

    /**
    * Publishes the DayaheadSchedule interaction class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void publish(RTIambassador rti) {
        if (_isPublished) return;

        init(rti);

        synchronized(rti) {
            boolean isNotPublished = true;
            while(isNotPublished) {
                try {
                    rti.publishInteractionClass(get_handle());
                    isNotPublished = false;
                } catch (FederateNotExecutionMember e) {
                    logger.error("could not publish: Federate Not Execution Member", e);
                    return;
                } catch (InteractionClassNotDefined e) {
                    logger.error("could not publish: Interaction Class Not Defined", e);
                    return;
                } catch (Exception e) {
                    logger.error(e);
                    CpswtUtils.sleepDefault();
                }
            }
        }

        _isPublished = true;
        logger.debug("publish: {}", get_class_name());
    }

    /**
    * Unpublishes the DayaheadSchedule interaction class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void unpublish(RTIambassador rti) {
        if (!_isPublished) return;

        init(rti);

        synchronized(rti) {
            boolean isNotUnpublished = true;
            while(isNotUnpublished) {
                try {
                    rti.unpublishInteractionClass(get_handle());
                    isNotUnpublished = false;
                } catch (FederateNotExecutionMember e) {
                    logger.error("could not unpublish: Federate Not Execution Member", e);
                    return;
                } catch (InteractionClassNotDefined e) {
                    logger.error("could not unpublish: Interaction Class Not Defined", e);
                    return;
                } catch (InteractionClassNotPublished e) {
                    logger.error("could not unpublish: Interaction Class Not Published", e);
                    return;
                } catch (Exception e) {
                    logger.error(e);
                    CpswtUtils.sleepDefault();
                }
            }
        }

        _isPublished = false;
        logger.debug("unpublish: {}", get_class_name());
    }

    private static boolean _isSubscribed = false;

    /**
    * Subscribes a federate to the DayaheadSchedule interaction class.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void subscribe(RTIambassador rti) {
        if (_isSubscribed) return;

        init(rti);

        synchronized(rti) {
            boolean isNotSubscribed = true;
            while(isNotSubscribed) {
                try {
                    rti.subscribeInteractionClass(get_handle());
                    isNotSubscribed = false;
                } catch (FederateNotExecutionMember e) {
                    logger.error("could not subscribe: Federate Not Execution Member", e);
                    return;
                } catch (InteractionClassNotDefined e) {
                    logger.error("could not subscribe: Interaction Class Not Defined", e);
                    return;
                } catch (Exception e) {
                    logger.error(e);
                    CpswtUtils.sleepDefault();
                }
            }
        }

        _isSubscribed = true;
        logger.debug("subscribe: {}", get_class_name());
    }

    /**
    * Unsubscribes a federate from the DayaheadSchedule interaction class.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void unsubscribe(RTIambassador rti) {
        if (!_isSubscribed) return;

        init(rti);

        synchronized(rti) {
            boolean isNotUnsubscribed = true;
            while(isNotUnsubscribed) {
                try {
                    rti.unsubscribeInteractionClass(get_handle());
                    isNotUnsubscribed = false;
                } catch (FederateNotExecutionMember e) {
                    logger.error("could not unsubscribe: Federate Not Execution Member", e);
                    return;
                } catch (InteractionClassNotDefined e) {
                    logger.error("could not unsubscribe: Interaction Class Not Defined", e);
                    return;
                } catch (InteractionClassNotSubscribed e) {
                    logger.error("could not unsubscribe: Interaction Class Not Subscribed", e);
                    return;
                } catch (Exception e) {
                    logger.error(e);
                    CpswtUtils.sleepDefault();
                }
            }
        }

        _isSubscribed = false;
        logger.debug("unsubscribe: {}", get_class_name());
    }

    /**
    * Return true if "handle" is equal to the handle (RTI assigned) of this class
    * (that is, the DayaheadSchedule interaction class).
    *
    * @param handle handle to compare to the value of the handle (RTI assigned) of
    * this class (the DayaheadSchedule interaction class).
    * @return "true" if "handle" matches the value of the handle of this class
    * (that is, the DayaheadSchedule interaction class).
    */
    public static boolean match(int handle) {
        return handle == get_handle();
    }

    /**
    * Returns the handle (RTI assigned) of this instance's interaction class .
    *
    * @return the handle (RTI assigned) if this instance's interaction class
    */
    public int getClassHandle() {
        return get_handle();
    }

    /**
    * Returns the fully-qualified (dot-delimited) name of this instance's interaction class.
    *
    * @return the fully-qualified (dot-delimited) name of this instance's interaction class
    */
    public String getClassName() {
        return get_class_name();
    }

    /**
    * Returns the simple name (last name in its fully-qualified dot-delimited name)
    * of this instance's interaction class.
    *
    * @return the simple name of this instance's interaction class
    */
    public String getSimpleClassName() {
        return get_simple_class_name();
    }

    /**
    * Returns a set containing the names of all of the non-hiddenparameters of an
    * interaction class instance.
    *
    * @return set containing the names of all of the parameters of an
    * interaction class instance
    */
    public Set< String > getParameterNames() {
        return get_parameter_names();
    }

    /**
    * Returns a set containing the names of all of the parameters of an
    * interaction class instance.
    *
    * @return set containing the names of all of the parameters of an
    * interaction class instance
    */
    public Set< String > getAllParameterNames() {
        return get_all_parameter_names();
    }

    @Override
    public String getParameterName(int datamemberHandle) {
        if (datamemberHandle == _InboundOutbound_handle) return "InboundOutbound";
        else if (datamemberHandle == _actualLogicalGenerationTime_handle) return "actualLogicalGenerationTime";
        else if (datamemberHandle == _aircraft_handle) return "aircraft";
        else if (datamemberHandle == _airline_handle) return "airline";
        else if (datamemberHandle == _booked_handle) return "booked";
        else if (datamemberHandle == _capacity_handle) return "capacity";
        else if (datamemberHandle == _federateFilter_handle) return "federateFilter";
        else if (datamemberHandle == _flightNum_handle) return "flightNum";
        else if (datamemberHandle == _flightStatus_handle) return "flightStatus";
        else if (datamemberHandle == _gateNum_handle) return "gateNum";
        else if (datamemberHandle == _originFed_handle) return "originFed";
        else if (datamemberHandle == _scheduledTime_handle) return "scheduledTime";
        else if (datamemberHandle == _sourceFed_handle) return "sourceFed";
        else if (datamemberHandle == _transfer_handle) return "transfer";
        else return super.getParameterName(datamemberHandle);
    }

    /**
    * Publishes the interaction class of this instance of the class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public void publishInteraction(RTIambassador rti) {
        publish(rti);
    }

    /**
    * Unpublishes the interaction class of this instance of this class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public void unpublishInteraction(RTIambassador rti) {
        unpublish(rti);
    }

    /**
    * Subscribes a federate to the interaction class of this instance of this class.
    *
    * @param rti handle to the Local RTI Component
    */
    public void subscribeInteraction(RTIambassador rti) {
        subscribe(rti);
    }

    /**
    * Unsubscribes a federate from the interaction class of this instance of this class.
    *
    * @param rti handle to the Local RTI Component
    */
    public void unsubscribeInteraction(RTIambassador rti) {
        unsubscribe(rti);
    }

    @Override
    public String toString() {
        return getClass().getName() + "("
                + "InboundOutbound:" + get_InboundOutbound()
                + "," + "actualLogicalGenerationTime:" + get_actualLogicalGenerationTime()
                + "," + "aircraft:" + get_aircraft()
                + "," + "airline:" + get_airline()
                + "," + "booked:" + get_booked()
                + "," + "capacity:" + get_capacity()
                + "," + "federateFilter:" + get_federateFilter()
                + "," + "flightNum:" + get_flightNum()
                + "," + "flightStatus:" + get_flightStatus()
                + "," + "gateNum:" + get_gateNum()
                + "," + "originFed:" + get_originFed()
                + "," + "scheduledTime:" + get_scheduledTime()
                + "," + "sourceFed:" + get_sourceFed()
                + "," + "transfer:" + get_transfer()
                + ")";
    }

    private boolean _InboundOutbound = false;
    private String _aircraft = "";
    private String _airline = "";
    private int _booked = 0;
    private int _capacity = 0;
    private String _flightNum = "";
    private String _flightStatus = "";
    private String _gateNum = "";
    private String _scheduledTime = "";
    private int _transfer = 0;

    /**
    * Set the value of the "InboundOutbound" parameter to "value" for this parameter.
    *
    * @param value the new value for the "InboundOutbound" parameter
    */
    public void set_InboundOutbound( boolean value ) {
        _InboundOutbound = value;
    }

    /**
    * Returns the value of the "InboundOutbound" parameter of this interaction.
    *
    * @return the value of the "InboundOutbound" parameter
    */
    public boolean get_InboundOutbound() {
        return _InboundOutbound;
    }
    /**
    * Set the value of the "aircraft" parameter to "value" for this parameter.
    *
    * @param value the new value for the "aircraft" parameter
    */
    public void set_aircraft( String value ) {
        _aircraft = value;
    }

    /**
    * Returns the value of the "aircraft" parameter of this interaction.
    *
    * @return the value of the "aircraft" parameter
    */
    public String get_aircraft() {
        return _aircraft;
    }
    /**
    * Set the value of the "airline" parameter to "value" for this parameter.
    *
    * @param value the new value for the "airline" parameter
    */
    public void set_airline( String value ) {
        _airline = value;
    }

    /**
    * Returns the value of the "airline" parameter of this interaction.
    *
    * @return the value of the "airline" parameter
    */
    public String get_airline() {
        return _airline;
    }
    /**
    * Set the value of the "booked" parameter to "value" for this parameter.
    *
    * @param value the new value for the "booked" parameter
    */
    public void set_booked( int value ) {
        _booked = value;
    }

    /**
    * Returns the value of the "booked" parameter of this interaction.
    *
    * @return the value of the "booked" parameter
    */
    public int get_booked() {
        return _booked;
    }
    /**
    * Set the value of the "capacity" parameter to "value" for this parameter.
    *
    * @param value the new value for the "capacity" parameter
    */
    public void set_capacity( int value ) {
        _capacity = value;
    }

    /**
    * Returns the value of the "capacity" parameter of this interaction.
    *
    * @return the value of the "capacity" parameter
    */
    public int get_capacity() {
        return _capacity;
    }
    /**
    * Set the value of the "flightNum" parameter to "value" for this parameter.
    *
    * @param value the new value for the "flightNum" parameter
    */
    public void set_flightNum( String value ) {
        _flightNum = value;
    }

    /**
    * Returns the value of the "flightNum" parameter of this interaction.
    *
    * @return the value of the "flightNum" parameter
    */
    public String get_flightNum() {
        return _flightNum;
    }
    /**
    * Set the value of the "flightStatus" parameter to "value" for this parameter.
    *
    * @param value the new value for the "flightStatus" parameter
    */
    public void set_flightStatus( String value ) {
        _flightStatus = value;
    }

    /**
    * Returns the value of the "flightStatus" parameter of this interaction.
    *
    * @return the value of the "flightStatus" parameter
    */
    public String get_flightStatus() {
        return _flightStatus;
    }
    /**
    * Set the value of the "gateNum" parameter to "value" for this parameter.
    *
    * @param value the new value for the "gateNum" parameter
    */
    public void set_gateNum( String value ) {
        _gateNum = value;
    }

    /**
    * Returns the value of the "gateNum" parameter of this interaction.
    *
    * @return the value of the "gateNum" parameter
    */
    public String get_gateNum() {
        return _gateNum;
    }
    /**
    * Set the value of the "scheduledTime" parameter to "value" for this parameter.
    *
    * @param value the new value for the "scheduledTime" parameter
    */
    public void set_scheduledTime( String value ) {
        _scheduledTime = value;
    }

    /**
    * Returns the value of the "scheduledTime" parameter of this interaction.
    *
    * @return the value of the "scheduledTime" parameter
    */
    public String get_scheduledTime() {
        return _scheduledTime;
    }
    /**
    * Set the value of the "transfer" parameter to "value" for this parameter.
    *
    * @param value the new value for the "transfer" parameter
    */
    public void set_transfer( int value ) {
        _transfer = value;
    }

    /**
    * Returns the value of the "transfer" parameter of this interaction.
    *
    * @return the value of the "transfer" parameter
    */
    public int get_transfer() {
        return _transfer;
    }

    protected DayaheadSchedule( ReceivedInteraction datamemberMap, boolean initFlag ) {
        super( datamemberMap, false );
        if ( initFlag ) setParameters( datamemberMap );
    }

    protected DayaheadSchedule( ReceivedInteraction datamemberMap, LogicalTime logicalTime, boolean initFlag ) {
        super( datamemberMap, logicalTime, false );
        if ( initFlag ) setParameters( datamemberMap );
    }

    /**
    * Creates an instance of the DayaheadSchedule interaction class, using
    * "datamemberMap" to initialize its parameter values.
    * "datamemberMap" is usually acquired as an argument to an RTI federate
    * callback method, such as "receiveInteraction".
    *
    * @param datamemberMap data structure containing initial values for the
    * parameters of this new DayaheadSchedule interaction class instance
    */
    public DayaheadSchedule( ReceivedInteraction datamemberMap ) {
        this( datamemberMap, true );
    }

    /**
    * Like {@link #DayaheadSchedule( ReceivedInteraction datamemberMap )}, except this
    * new DayaheadSchedule interaction class instance is given a timestamp of
    * "logicalTime".
    *
    * @param datamemberMap data structure containing initial values for the
    * parameters of this new DayaheadSchedule interaction class instance
    * @param logicalTime timestamp for this new DayaheadSchedule interaction class
    * instance
    */
    public DayaheadSchedule( ReceivedInteraction datamemberMap, LogicalTime logicalTime ) {
        this( datamemberMap, logicalTime, true );
    }

    /**
    * Creates a new DayaheadSchedule interaction class instance that is a duplicate
    * of the instance referred to by DayaheadSchedule_var.
    *
    * @param DayaheadSchedule_var DayaheadSchedule interaction class instance of which
    * this newly created DayaheadSchedule interaction class instance will be a
    * duplicate
    */
    public DayaheadSchedule( DayaheadSchedule DayaheadSchedule_var ) {
        super( DayaheadSchedule_var );

        set_InboundOutbound( DayaheadSchedule_var.get_InboundOutbound() );
        set_aircraft( DayaheadSchedule_var.get_aircraft() );
        set_airline( DayaheadSchedule_var.get_airline() );
        set_booked( DayaheadSchedule_var.get_booked() );
        set_capacity( DayaheadSchedule_var.get_capacity() );
        set_flightNum( DayaheadSchedule_var.get_flightNum() );
        set_flightStatus( DayaheadSchedule_var.get_flightStatus() );
        set_gateNum( DayaheadSchedule_var.get_gateNum() );
        set_scheduledTime( DayaheadSchedule_var.get_scheduledTime() );
        set_transfer( DayaheadSchedule_var.get_transfer() );
    }

    /**
    * Returns the value of the parameter whose name is "datamemberName"
    * for this interaction.
    *
    * @param datamemberName name of parameter whose value is to be
    * returned
    * @return value of the parameter whose name is "datamemberName"
    * for this interaction
    */
    public Object getParameter( String datamemberName ) {
        if ( "InboundOutbound".equals(datamemberName) ) return new Boolean(get_InboundOutbound());
        else if ( "aircraft".equals(datamemberName) ) return get_aircraft();
        else if ( "airline".equals(datamemberName) ) return get_airline();
        else if ( "booked".equals(datamemberName) ) return new Integer(get_booked());
        else if ( "capacity".equals(datamemberName) ) return new Integer(get_capacity());
        else if ( "flightNum".equals(datamemberName) ) return get_flightNum();
        else if ( "flightStatus".equals(datamemberName) ) return get_flightStatus();
        else if ( "gateNum".equals(datamemberName) ) return get_gateNum();
        else if ( "scheduledTime".equals(datamemberName) ) return get_scheduledTime();
        else if ( "transfer".equals(datamemberName) ) return new Integer(get_transfer());
        else return super.getParameter( datamemberName );
    }

    protected boolean setParameterAux( String datamemberName, String val ) {
        boolean retval = true;
        if ( "InboundOutbound".equals( datamemberName) ) set_InboundOutbound( Boolean.parseBoolean(val) );
        else if ( "aircraft".equals( datamemberName) ) set_aircraft( val );
        else if ( "airline".equals( datamemberName) ) set_airline( val );
        else if ( "booked".equals( datamemberName) ) set_booked( Integer.parseInt(val) );
        else if ( "capacity".equals( datamemberName) ) set_capacity( Integer.parseInt(val) );
        else if ( "flightNum".equals( datamemberName) ) set_flightNum( val );
        else if ( "flightStatus".equals( datamemberName) ) set_flightStatus( val );
        else if ( "gateNum".equals( datamemberName) ) set_gateNum( val );
        else if ( "scheduledTime".equals( datamemberName) ) set_scheduledTime( val );
        else if ( "transfer".equals( datamemberName) ) set_transfer( Integer.parseInt(val) );
        else retval = super.setParameterAux( datamemberName, val );

        return retval;
    }

    protected boolean setParameterAux( String datamemberName, Object val ) {
        boolean retval = true;
        if ( "InboundOutbound".equals( datamemberName) ) set_InboundOutbound( (Boolean)val );
        else if ( "aircraft".equals( datamemberName) ) set_aircraft( (String)val );
        else if ( "airline".equals( datamemberName) ) set_airline( (String)val );
        else if ( "booked".equals( datamemberName) ) set_booked( (Integer)val );
        else if ( "capacity".equals( datamemberName) ) set_capacity( (Integer)val );
        else if ( "flightNum".equals( datamemberName) ) set_flightNum( (String)val );
        else if ( "flightStatus".equals( datamemberName) ) set_flightStatus( (String)val );
        else if ( "gateNum".equals( datamemberName) ) set_gateNum( (String)val );
        else if ( "scheduledTime".equals( datamemberName) ) set_scheduledTime( (String)val );
        else if ( "transfer".equals( datamemberName) ) set_transfer( (Integer)val );
        else retval = super.setParameterAux( datamemberName, val );

        return retval;
    }

    public void copyFrom( Object object ) {
        super.copyFrom( object );
        if ( object instanceof DayaheadSchedule ) {
            DayaheadSchedule data = (DayaheadSchedule)object;
            _InboundOutbound = data._InboundOutbound;
            _aircraft = data._aircraft;
            _airline = data._airline;
            _booked = data._booked;
            _capacity = data._capacity;
            _flightNum = data._flightNum;
            _flightStatus = data._flightStatus;
            _gateNum = data._gateNum;
            _scheduledTime = data._scheduledTime;
            _transfer = data._transfer;
        }
    }
}

