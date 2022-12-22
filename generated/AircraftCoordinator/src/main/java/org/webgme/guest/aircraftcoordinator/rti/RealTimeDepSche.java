package org.webgme.guest.aircraftcoordinator.rti;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cpswt.utils.CpswtUtils;

import hla.rti.AttributeHandleSet;
import hla.rti.FederateNotExecutionMember;
import hla.rti.LogicalTime;
import hla.rti.NameNotFound;
import hla.rti.ObjectClassNotDefined;
import hla.rti.ObjectClassNotPublished;
import hla.rti.ObjectClassNotSubscribed;
import hla.rti.RTIambassador;
import hla.rti.ReflectedAttributes;
import hla.rti.SuppliedAttributes;

import org.cpswt.hla.*;

/**
* Implements ObjectRoot.RealTimeDepSche
*/
public class RealTimeDepSche extends ObjectRoot {

    private static final Logger logger = LogManager.getLogger();

    /**
    * Creates an instance of the RealTimeDepSche object class with default attribute values.
    */
    public RealTimeDepSche() {}

    private static int _aircraft_handle;
    private static int _airline_handle;
    private static int _booked_handle;
    private static int _capacity_handle;
    private static int _checkedIn_handle;
    private static int _depStatus_handle;
    private static int _estimated_handle;
    private static int _flightNumber_handle;
    private static int _gate_handle;
    private static int _scheduledDepTime_handle;
    private static int _transfer_handle;

    private static boolean _isInitialized = false;

    private static int _handle;

    /**
    * Returns the handle (RTI assigned) of the RealTimeDepSche object class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return the handle of the class pertaining to the reference,
    * rather than the handle of the class for the instance referred to by the reference.
    * For the polymorphic version of this method, use {@link #getClassHandle()}.
    *
    * @return the RTI assigned integer handle that represents this object class
    */
    public static int get_handle() {
        return _handle;
    }

    /**
    * Returns the fully-qualified (dot-delimited) name of the RealTimeDepSche object class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return the name of the class pertaining to the reference,
    * rather than the name of the class for the instance referred to by the reference.
    * For the polymorphic version of this method, use {@link #getClassName()}.
    *
    * @return the fully-qualified HLA class path for this object class
    */
    public static String get_class_name() {
        return "ObjectRoot.RealTimeDepSche";
    }

    /**
    * Returns the simple name (the last name in the dot-delimited fully-qualified
    * class name) of the RealTimeDepSche object class.
    *
    * @return the name of this object class
    */
    public static String get_simple_class_name() {
        return "RealTimeDepSche";
    }

    private static Set< String > _datamemberNames = new HashSet< String >();
    private static Set< String > _allDatamemberNames = new HashSet< String >();

    /**
    * Returns a set containing the names of all of the non-hidden attributes in the
    * RealTimeDepSche object class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return a set of parameter names pertaining to the reference,
    * rather than the parameter names of the class for the instance referred to by
    * the reference.  For the polymorphic version of this method, use
    * {@link #getAttributeNames()}.
    *
    * @return a modifiable set of the non-hidden attribute names for this object class
    */
    public static Set< String > get_attribute_names() {
        return new HashSet< String >(_datamemberNames);
    }

    /**
    * Returns a set containing the names of all of the attributes in the
    * RealTimeDepSche object class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return a set of parameter names pertaining to the reference,
    * rather than the parameter names of the class for the instance referred to by
    * the reference.  For the polymorphic version of this method, use
    * {@link #getAttributeNames()}.
    *
    * @return a modifiable set of the attribute names for this object class
    */
    public static Set< String > get_all_attribute_names() {
        return new HashSet< String >(_allDatamemberNames);
    }

    private static Set< String > _publishAttributeNameSet = new HashSet< String >();
    private static Set< String > _subscribeAttributeNameSet = new HashSet< String >();

    static {
        _classNameSet.add("ObjectRoot.RealTimeDepSche");
        _classNameClassMap.put("ObjectRoot.RealTimeDepSche", RealTimeDepSche.class);

        _datamemberClassNameSetMap.put("ObjectRoot.RealTimeDepSche", _datamemberNames);
        _allDatamemberClassNameSetMap.put("ObjectRoot.RealTimeDepSche", _allDatamemberNames);

        _datamemberNames.add("aircraft");
        _datamemberNames.add("airline");
        _datamemberNames.add("booked");
        _datamemberNames.add("capacity");
        _datamemberNames.add("checkedIn");
        _datamemberNames.add("depStatus");
        _datamemberNames.add("estimated");
        _datamemberNames.add("flightNumber");
        _datamemberNames.add("gate");
        _datamemberNames.add("scheduledDepTime");
        _datamemberNames.add("transfer");

        _datamemberTypeMap.put("aircraft", "String");
        _datamemberTypeMap.put("airline", "String");
        _datamemberTypeMap.put("booked", "int");
        _datamemberTypeMap.put("capacity", "int");
        _datamemberTypeMap.put("checkedIn", "int");
        _datamemberTypeMap.put("depStatus", "String");
        _datamemberTypeMap.put("estimated", "String");
        _datamemberTypeMap.put("flightNumber", "String");
        _datamemberTypeMap.put("gate", "String");
        _datamemberTypeMap.put("scheduledDepTime", "String");
        _datamemberTypeMap.put("transfer", "int");

        _allDatamemberNames.add("aircraft");
        _allDatamemberNames.add("airline");
        _allDatamemberNames.add("booked");
        _allDatamemberNames.add("capacity");
        _allDatamemberNames.add("checkedIn");
        _allDatamemberNames.add("depStatus");
        _allDatamemberNames.add("estimated");
        _allDatamemberNames.add("flightNumber");
        _allDatamemberNames.add("gate");
        _allDatamemberNames.add("scheduledDepTime");
        _allDatamemberNames.add("transfer");

        _classNamePublishAttributeNameMap.put("ObjectRoot.RealTimeDepSche", _publishAttributeNameSet);
        _classNameSubscribeAttributeNameMap.put("ObjectRoot.RealTimeDepSche", _subscribeAttributeNameSet);
    }

    protected static void init(RTIambassador rti) {
        if (_isInitialized) return;
        _isInitialized = true;

        ObjectRoot.init(rti);

        boolean isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _handle = rti.getObjectClassHandle("ObjectRoot.RealTimeDepSche");
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

        _classNameHandleMap.put("ObjectRoot.RealTimeDepSche", get_handle());
        _classHandleNameMap.put(get_handle(), "ObjectRoot.RealTimeDepSche");
        _classHandleSimpleNameMap.put(get_handle(), "RealTimeDepSche");

        isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _aircraft_handle = rti.getAttributeHandle("aircraft", get_handle());
                _airline_handle = rti.getAttributeHandle("airline", get_handle());
                _booked_handle = rti.getAttributeHandle("booked", get_handle());
                _capacity_handle = rti.getAttributeHandle("capacity", get_handle());
                _checkedIn_handle = rti.getAttributeHandle("checkedIn", get_handle());
                _depStatus_handle = rti.getAttributeHandle("depStatus", get_handle());
                _estimated_handle = rti.getAttributeHandle("estimated", get_handle());
                _flightNumber_handle = rti.getAttributeHandle("flightNumber", get_handle());
                _gate_handle = rti.getAttributeHandle("gate", get_handle());
                _scheduledDepTime_handle = rti.getAttributeHandle("scheduledDepTime", get_handle());
                _transfer_handle = rti.getAttributeHandle("transfer", get_handle());
                isNotInitialized = false;
            } catch (FederateNotExecutionMember e) {
                logger.error("could not initialize: Federate Not Execution Member", e);
                return;
            } catch (ObjectClassNotDefined e) {
                logger.error("could not initialize: Object Class Not Defined", e);
                return;
            } catch (NameNotFound e) {
                logger.error("could not initialize: Name Not Found", e);
                return;
            } catch (Exception e) {
                logger.error(e);
                CpswtUtils.sleepDefault();
            }
        }

        _datamemberNameHandleMap.put("ObjectRoot.RealTimeDepSche.aircraft", _aircraft_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeDepSche.airline", _airline_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeDepSche.booked", _booked_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeDepSche.capacity", _capacity_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeDepSche.checkedIn", _checkedIn_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeDepSche.depStatus", _depStatus_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeDepSche.estimated", _estimated_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeDepSche.flightNumber", _flightNumber_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeDepSche.gate", _gate_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeDepSche.scheduledDepTime", _scheduledDepTime_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeDepSche.transfer", _transfer_handle);

        _datamemberHandleNameMap.put(_aircraft_handle, "aircraft");
        _datamemberHandleNameMap.put(_airline_handle, "airline");
        _datamemberHandleNameMap.put(_booked_handle, "booked");
        _datamemberHandleNameMap.put(_capacity_handle, "capacity");
        _datamemberHandleNameMap.put(_checkedIn_handle, "checkedIn");
        _datamemberHandleNameMap.put(_depStatus_handle, "depStatus");
        _datamemberHandleNameMap.put(_estimated_handle, "estimated");
        _datamemberHandleNameMap.put(_flightNumber_handle, "flightNumber");
        _datamemberHandleNameMap.put(_gate_handle, "gate");
        _datamemberHandleNameMap.put(_scheduledDepTime_handle, "scheduledDepTime");
        _datamemberHandleNameMap.put(_transfer_handle, "transfer");
    }

    private static boolean _isPublished = false;

    /**
    * Publishes the RealTimeDepSche object class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void publish(RTIambassador rti) {
        if (_isPublished) return;

        init(rti);

        AttributeHandleSet publishedAttributeHandleSet = _factory.createAttributeHandleSet();
        for(String attributeName : _publishAttributeNameSet) {
            try {
                publishedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.RealTimeDepSche." + attributeName));
                logger.trace("publish {}:{}", get_class_name(), attributeName);
            } catch (Exception e) {
                logger.error("could not publish \"" + attributeName + "\" attribute.", e);
            }
        }

        synchronized(rti) {
            boolean isNotPublished = true;
            while(isNotPublished) {
                try {
                    rti.publishObjectClass(get_handle(), publishedAttributeHandleSet);
                    isNotPublished = false;
                } catch (FederateNotExecutionMember e) {
                    logger.error("could not publish: Federate Not Execution Member", e);
                    return;
                } catch (ObjectClassNotDefined e) {
                    logger.error("could not publish: Object Class Not Defined", e);
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
    * Unpublishes the RealTimeDepSche object class for a federate.
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
                    rti.unpublishObjectClass(get_handle());
                    isNotUnpublished = false;
                } catch (FederateNotExecutionMember e) {
                    logger.error("could not unpublish: Federate Not Execution Member", e);
                    return;
                } catch (ObjectClassNotDefined e) {
                    logger.error("could not unpublish: Object Class Not Defined", e);
                    return;
                } catch (ObjectClassNotPublished e) {
                    logger.error("could not unpublish: Object Class Not Published", e);
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
    * Subscribes a federate to the RealTimeDepSche object class.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void subscribe(RTIambassador rti) {
        if (_isSubscribed) return;

        init(rti);

        AttributeHandleSet subscribedAttributeHandleSet = _factory.createAttributeHandleSet();
        for(String attributeName : _subscribeAttributeNameSet) {
            try {
                subscribedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.RealTimeDepSche." + attributeName));
                logger.trace("subscribe {}:{}", get_class_name(), attributeName);
            } catch (Exception e) {
                logger.error("could not subscribe to \"" + attributeName + "\" attribute.", e);
            }
        }

        synchronized(rti) {
            boolean isNotSubscribed = true;
            while(isNotSubscribed) {
                try {
                    rti.subscribeObjectClassAttributes(get_handle(), subscribedAttributeHandleSet);
                    isNotSubscribed = false;
                } catch (FederateNotExecutionMember e) {
                    logger.error("could not subscribe: Federate Not Execution Member", e);
                    return;
                } catch (ObjectClassNotDefined e) {
                    logger.error("could not subscribe: Object Class Not Defined", e);
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
    * Unsubscribes a federate from the RealTimeDepSche object class.
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
                    rti.unsubscribeObjectClass(get_handle());
                    isNotUnsubscribed = false;
                } catch (FederateNotExecutionMember e) {
                    logger.error("could not unsubscribe: Federate Not Execution Member", e);
                    return;
                } catch (ObjectClassNotDefined e) {
                    logger.error("could not unsubscribe: Object Class Not Defined", e);
                    return;
                } catch (ObjectClassNotSubscribed e) {
                    logger.error("could not unsubscribe: Object Class Not Subscribed", e);
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
    * (that is, the RealTimeDepSche object class).
    *
    * @param handle handle to compare to the value of the handle (RTI assigned) of
    * this class (the RealTimeDepSche object class).
    * @return "true" if "handle" matches the value of the handle of this class
    * (that is, the RealTimeDepSche object class).
    */
    public static boolean match(int handle) {
        return handle == get_handle();
    }

    /**
    * Returns the handle (RTI assigned) of this instance's object class .
    *
    * @return the handle (RTI assigned) if this instance's object class
    */
    public int getClassHandle() {
        return get_handle();
    }

    /**
    * Returns the fully-qualified (dot-delimited) name of this instance's object class.
    *
    * @return the fully-qualified (dot-delimited) name of this instance's object class
    */
    public String getClassName() {
        return get_class_name();
    }

    /**
    * Returns the simple name (last name in its fully-qualified dot-delimited name)
    * of this instance's object class.
    *
    * @return the simple name of this instance's object class
    */
    public String getSimpleClassName() {
        return get_simple_class_name();
    }

    /**
    * Returns a set containing the names of all of the non-hiddenattributes of an
    * object class instance.
    *
    * @return set containing the names of all of the attributes of an
    * object class instance
    */
    public Set< String > getAttributeNames() {
        return get_attribute_names();
    }

    /**
    * Returns a set containing the names of all of the attributes of an
    * object class instance.
    *
    * @return set containing the names of all of the attributes of an
    * object class instance
    */
    public Set< String > getAllAttributeNames() {
        return get_all_attribute_names();
    }

    @Override
    public String getAttributeName(int datamemberHandle) {
        if (datamemberHandle == _aircraft_handle) return "aircraft";
        else if (datamemberHandle == _airline_handle) return "airline";
        else if (datamemberHandle == _booked_handle) return "booked";
        else if (datamemberHandle == _capacity_handle) return "capacity";
        else if (datamemberHandle == _checkedIn_handle) return "checkedIn";
        else if (datamemberHandle == _depStatus_handle) return "depStatus";
        else if (datamemberHandle == _estimated_handle) return "estimated";
        else if (datamemberHandle == _flightNumber_handle) return "flightNumber";
        else if (datamemberHandle == _gate_handle) return "gate";
        else if (datamemberHandle == _scheduledDepTime_handle) return "scheduledDepTime";
        else if (datamemberHandle == _transfer_handle) return "transfer";
        else return super.getAttributeName(datamemberHandle);
    }

    /**
    * Publishes the object class of this instance of the class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public void publishObject(RTIambassador rti) {
        publish(rti);
    }

    /**
    * Unpublishes the object class of this instance of this class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public void unpublishObject(RTIambassador rti) {
        unpublish(rti);
    }

    /**
    * Subscribes a federate to the object class of this instance of this class.
    *
    * @param rti handle to the Local RTI Component
    */
    public void subscribeObject(RTIambassador rti) {
        subscribe(rti);
    }

    /**
    * Unsubscribes a federate from the object class of this instance of this class.
    *
    * @param rti handle to the Local RTI Component
    */
    public void unsubscribeObject(RTIambassador rti) {
        unsubscribe(rti);
    }

    @Override
    public String toString() {
        return getClass().getName() + "("
                + "aircraft:" + get_aircraft()
                + "," + "airline:" + get_airline()
                + "," + "booked:" + get_booked()
                + "," + "capacity:" + get_capacity()
                + "," + "checkedIn:" + get_checkedIn()
                + "," + "depStatus:" + get_depStatus()
                + "," + "estimated:" + get_estimated()
                + "," + "flightNumber:" + get_flightNumber()
                + "," + "gate:" + get_gate()
                + "," + "scheduledDepTime:" + get_scheduledDepTime()
                + "," + "transfer:" + get_transfer()
                + ")";
    }


    /**
    * Publishes the "aircraft" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "aircraft" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_aircraft() {
        _publishAttributeNameSet.add( "aircraft" );
    }

    /**
    * Unpublishes the "aircraft" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "aircraft" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_aircraft() {
        _publishAttributeNameSet.remove( "aircraft" );
    }

    /**
    * Subscribes a federate to the "aircraft" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "aircraft" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_aircraft() {
        _subscribeAttributeNameSet.add( "aircraft" );
    }

    /**
    * Unsubscribes a federate from the "aircraft" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "aircraft" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_aircraft() {
        _subscribeAttributeNameSet.remove( "aircraft" );
    }

    /**
    * Publishes the "airline" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "airline" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_airline() {
        _publishAttributeNameSet.add( "airline" );
    }

    /**
    * Unpublishes the "airline" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "airline" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_airline() {
        _publishAttributeNameSet.remove( "airline" );
    }

    /**
    * Subscribes a federate to the "airline" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "airline" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_airline() {
        _subscribeAttributeNameSet.add( "airline" );
    }

    /**
    * Unsubscribes a federate from the "airline" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "airline" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_airline() {
        _subscribeAttributeNameSet.remove( "airline" );
    }

    /**
    * Publishes the "booked" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "booked" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_booked() {
        _publishAttributeNameSet.add( "booked" );
    }

    /**
    * Unpublishes the "booked" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "booked" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_booked() {
        _publishAttributeNameSet.remove( "booked" );
    }

    /**
    * Subscribes a federate to the "booked" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "booked" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_booked() {
        _subscribeAttributeNameSet.add( "booked" );
    }

    /**
    * Unsubscribes a federate from the "booked" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "booked" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_booked() {
        _subscribeAttributeNameSet.remove( "booked" );
    }

    /**
    * Publishes the "capacity" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "capacity" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_capacity() {
        _publishAttributeNameSet.add( "capacity" );
    }

    /**
    * Unpublishes the "capacity" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "capacity" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_capacity() {
        _publishAttributeNameSet.remove( "capacity" );
    }

    /**
    * Subscribes a federate to the "capacity" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "capacity" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_capacity() {
        _subscribeAttributeNameSet.add( "capacity" );
    }

    /**
    * Unsubscribes a federate from the "capacity" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "capacity" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_capacity() {
        _subscribeAttributeNameSet.remove( "capacity" );
    }

    /**
    * Publishes the "checkedIn" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "checkedIn" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_checkedIn() {
        _publishAttributeNameSet.add( "checkedIn" );
    }

    /**
    * Unpublishes the "checkedIn" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "checkedIn" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_checkedIn() {
        _publishAttributeNameSet.remove( "checkedIn" );
    }

    /**
    * Subscribes a federate to the "checkedIn" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "checkedIn" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_checkedIn() {
        _subscribeAttributeNameSet.add( "checkedIn" );
    }

    /**
    * Unsubscribes a federate from the "checkedIn" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "checkedIn" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_checkedIn() {
        _subscribeAttributeNameSet.remove( "checkedIn" );
    }

    /**
    * Publishes the "depStatus" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "depStatus" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_depStatus() {
        _publishAttributeNameSet.add( "depStatus" );
    }

    /**
    * Unpublishes the "depStatus" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "depStatus" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_depStatus() {
        _publishAttributeNameSet.remove( "depStatus" );
    }

    /**
    * Subscribes a federate to the "depStatus" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "depStatus" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_depStatus() {
        _subscribeAttributeNameSet.add( "depStatus" );
    }

    /**
    * Unsubscribes a federate from the "depStatus" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "depStatus" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_depStatus() {
        _subscribeAttributeNameSet.remove( "depStatus" );
    }

    /**
    * Publishes the "estimated" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "estimated" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_estimated() {
        _publishAttributeNameSet.add( "estimated" );
    }

    /**
    * Unpublishes the "estimated" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "estimated" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_estimated() {
        _publishAttributeNameSet.remove( "estimated" );
    }

    /**
    * Subscribes a federate to the "estimated" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "estimated" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_estimated() {
        _subscribeAttributeNameSet.add( "estimated" );
    }

    /**
    * Unsubscribes a federate from the "estimated" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "estimated" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_estimated() {
        _subscribeAttributeNameSet.remove( "estimated" );
    }

    /**
    * Publishes the "flightNumber" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "flightNumber" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_flightNumber() {
        _publishAttributeNameSet.add( "flightNumber" );
    }

    /**
    * Unpublishes the "flightNumber" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "flightNumber" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_flightNumber() {
        _publishAttributeNameSet.remove( "flightNumber" );
    }

    /**
    * Subscribes a federate to the "flightNumber" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "flightNumber" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_flightNumber() {
        _subscribeAttributeNameSet.add( "flightNumber" );
    }

    /**
    * Unsubscribes a federate from the "flightNumber" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "flightNumber" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_flightNumber() {
        _subscribeAttributeNameSet.remove( "flightNumber" );
    }

    /**
    * Publishes the "gate" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "gate" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_gate() {
        _publishAttributeNameSet.add( "gate" );
    }

    /**
    * Unpublishes the "gate" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "gate" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_gate() {
        _publishAttributeNameSet.remove( "gate" );
    }

    /**
    * Subscribes a federate to the "gate" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "gate" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_gate() {
        _subscribeAttributeNameSet.add( "gate" );
    }

    /**
    * Unsubscribes a federate from the "gate" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "gate" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_gate() {
        _subscribeAttributeNameSet.remove( "gate" );
    }

    /**
    * Publishes the "scheduledDepTime" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "scheduledDepTime" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_scheduledDepTime() {
        _publishAttributeNameSet.add( "scheduledDepTime" );
    }

    /**
    * Unpublishes the "scheduledDepTime" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "scheduledDepTime" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_scheduledDepTime() {
        _publishAttributeNameSet.remove( "scheduledDepTime" );
    }

    /**
    * Subscribes a federate to the "scheduledDepTime" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "scheduledDepTime" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_scheduledDepTime() {
        _subscribeAttributeNameSet.add( "scheduledDepTime" );
    }

    /**
    * Unsubscribes a federate from the "scheduledDepTime" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "scheduledDepTime" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_scheduledDepTime() {
        _subscribeAttributeNameSet.remove( "scheduledDepTime" );
    }

    /**
    * Publishes the "transfer" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "transfer" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_transfer() {
        _publishAttributeNameSet.add( "transfer" );
    }

    /**
    * Unpublishes the "transfer" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "transfer" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_transfer() {
        _publishAttributeNameSet.remove( "transfer" );
    }

    /**
    * Subscribes a federate to the "transfer" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "transfer" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_transfer() {
        _subscribeAttributeNameSet.add( "transfer" );
    }

    /**
    * Unsubscribes a federate from the "transfer" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "transfer" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_transfer() {
        _subscribeAttributeNameSet.remove( "transfer" );
    }

    protected Attribute< String > _aircraft =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "aircraft" attribute to "value" for this object.
    *
    * @param value the new value for the "aircraft" attribute
    */
    public void set_aircraft( String value ) {
        _aircraft.setValue( value );
        _aircraft.setTime( getTime() );
    }

    /**
    * Returns the value of the "aircraft" attribute of this object.
    *
    * @return the value of the "aircraft" attribute
    */
    public String get_aircraft() {
        return _aircraft.getValue();
    }

    /**
    * Returns the current timestamp of the "aircraft" attribute of this object.
    *
    * @return the current timestamp of the "aircraft" attribute
    */
    public double get_aircraft_time() {
        return _aircraft.getTime();
    }

    protected Attribute< String > _airline =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "airline" attribute to "value" for this object.
    *
    * @param value the new value for the "airline" attribute
    */
    public void set_airline( String value ) {
        _airline.setValue( value );
        _airline.setTime( getTime() );
    }

    /**
    * Returns the value of the "airline" attribute of this object.
    *
    * @return the value of the "airline" attribute
    */
    public String get_airline() {
        return _airline.getValue();
    }

    /**
    * Returns the current timestamp of the "airline" attribute of this object.
    *
    * @return the current timestamp of the "airline" attribute
    */
    public double get_airline_time() {
        return _airline.getTime();
    }

    protected Attribute< Integer > _booked =
            new Attribute< Integer >(  new Integer( 0 )  );

    /**
    * Set the value of the "booked" attribute to "value" for this object.
    *
    * @param value the new value for the "booked" attribute
    */
    public void set_booked( int value ) {
        _booked.setValue( value );
        _booked.setTime( getTime() );
    }

    /**
    * Returns the value of the "booked" attribute of this object.
    *
    * @return the value of the "booked" attribute
    */
    public int get_booked() {
        return _booked.getValue();
    }

    /**
    * Returns the current timestamp of the "booked" attribute of this object.
    *
    * @return the current timestamp of the "booked" attribute
    */
    public double get_booked_time() {
        return _booked.getTime();
    }

    protected Attribute< Integer > _capacity =
            new Attribute< Integer >(  new Integer( 0 )  );

    /**
    * Set the value of the "capacity" attribute to "value" for this object.
    *
    * @param value the new value for the "capacity" attribute
    */
    public void set_capacity( int value ) {
        _capacity.setValue( value );
        _capacity.setTime( getTime() );
    }

    /**
    * Returns the value of the "capacity" attribute of this object.
    *
    * @return the value of the "capacity" attribute
    */
    public int get_capacity() {
        return _capacity.getValue();
    }

    /**
    * Returns the current timestamp of the "capacity" attribute of this object.
    *
    * @return the current timestamp of the "capacity" attribute
    */
    public double get_capacity_time() {
        return _capacity.getTime();
    }

    protected Attribute< Integer > _checkedIn =
            new Attribute< Integer >(  new Integer( 0 )  );

    /**
    * Set the value of the "checkedIn" attribute to "value" for this object.
    *
    * @param value the new value for the "checkedIn" attribute
    */
    public void set_checkedIn( int value ) {
        _checkedIn.setValue( value );
        _checkedIn.setTime( getTime() );
    }

    /**
    * Returns the value of the "checkedIn" attribute of this object.
    *
    * @return the value of the "checkedIn" attribute
    */
    public int get_checkedIn() {
        return _checkedIn.getValue();
    }

    /**
    * Returns the current timestamp of the "checkedIn" attribute of this object.
    *
    * @return the current timestamp of the "checkedIn" attribute
    */
    public double get_checkedIn_time() {
        return _checkedIn.getTime();
    }

    protected Attribute< String > _depStatus =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "depStatus" attribute to "value" for this object.
    *
    * @param value the new value for the "depStatus" attribute
    */
    public void set_depStatus( String value ) {
        _depStatus.setValue( value );
        _depStatus.setTime( getTime() );
    }

    /**
    * Returns the value of the "depStatus" attribute of this object.
    *
    * @return the value of the "depStatus" attribute
    */
    public String get_depStatus() {
        return _depStatus.getValue();
    }

    /**
    * Returns the current timestamp of the "depStatus" attribute of this object.
    *
    * @return the current timestamp of the "depStatus" attribute
    */
    public double get_depStatus_time() {
        return _depStatus.getTime();
    }

    protected Attribute< String > _estimated =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "estimated" attribute to "value" for this object.
    *
    * @param value the new value for the "estimated" attribute
    */
    public void set_estimated( String value ) {
        _estimated.setValue( value );
        _estimated.setTime( getTime() );
    }

    /**
    * Returns the value of the "estimated" attribute of this object.
    *
    * @return the value of the "estimated" attribute
    */
    public String get_estimated() {
        return _estimated.getValue();
    }

    /**
    * Returns the current timestamp of the "estimated" attribute of this object.
    *
    * @return the current timestamp of the "estimated" attribute
    */
    public double get_estimated_time() {
        return _estimated.getTime();
    }

    protected Attribute< String > _flightNumber =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "flightNumber" attribute to "value" for this object.
    *
    * @param value the new value for the "flightNumber" attribute
    */
    public void set_flightNumber( String value ) {
        _flightNumber.setValue( value );
        _flightNumber.setTime( getTime() );
    }

    /**
    * Returns the value of the "flightNumber" attribute of this object.
    *
    * @return the value of the "flightNumber" attribute
    */
    public String get_flightNumber() {
        return _flightNumber.getValue();
    }

    /**
    * Returns the current timestamp of the "flightNumber" attribute of this object.
    *
    * @return the current timestamp of the "flightNumber" attribute
    */
    public double get_flightNumber_time() {
        return _flightNumber.getTime();
    }

    protected Attribute< String > _gate =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "gate" attribute to "value" for this object.
    *
    * @param value the new value for the "gate" attribute
    */
    public void set_gate( String value ) {
        _gate.setValue( value );
        _gate.setTime( getTime() );
    }

    /**
    * Returns the value of the "gate" attribute of this object.
    *
    * @return the value of the "gate" attribute
    */
    public String get_gate() {
        return _gate.getValue();
    }

    /**
    * Returns the current timestamp of the "gate" attribute of this object.
    *
    * @return the current timestamp of the "gate" attribute
    */
    public double get_gate_time() {
        return _gate.getTime();
    }

    protected Attribute< String > _scheduledDepTime =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "scheduledDepTime" attribute to "value" for this object.
    *
    * @param value the new value for the "scheduledDepTime" attribute
    */
    public void set_scheduledDepTime( String value ) {
        _scheduledDepTime.setValue( value );
        _scheduledDepTime.setTime( getTime() );
    }

    /**
    * Returns the value of the "scheduledDepTime" attribute of this object.
    *
    * @return the value of the "scheduledDepTime" attribute
    */
    public String get_scheduledDepTime() {
        return _scheduledDepTime.getValue();
    }

    /**
    * Returns the current timestamp of the "scheduledDepTime" attribute of this object.
    *
    * @return the current timestamp of the "scheduledDepTime" attribute
    */
    public double get_scheduledDepTime_time() {
        return _scheduledDepTime.getTime();
    }

    protected Attribute< Integer > _transfer =
            new Attribute< Integer >(  new Integer( 0 )  );

    /**
    * Set the value of the "transfer" attribute to "value" for this object.
    *
    * @param value the new value for the "transfer" attribute
    */
    public void set_transfer( int value ) {
        _transfer.setValue( value );
        _transfer.setTime( getTime() );
    }

    /**
    * Returns the value of the "transfer" attribute of this object.
    *
    * @return the value of the "transfer" attribute
    */
    public int get_transfer() {
        return _transfer.getValue();
    }

    /**
    * Returns the current timestamp of the "transfer" attribute of this object.
    *
    * @return the current timestamp of the "transfer" attribute
    */
    public double get_transfer_time() {
        return _transfer.getTime();
    }

    protected RealTimeDepSche( ReflectedAttributes datamemberMap, boolean initFlag ) {
        super( datamemberMap, false );
        if ( initFlag ) setAttributes( datamemberMap );
    }

    protected RealTimeDepSche( ReflectedAttributes datamemberMap, LogicalTime logicalTime, boolean initFlag ) {
        super( datamemberMap, logicalTime, false );
        if ( initFlag ) setAttributes( datamemberMap );
    }

    /**
    * Creates an instance of the RealTimeDepSche object class, using
    * "datamemberMap" to initialize its attribute values.
    * "datamemberMap" is usually acquired as an argument to an RTI federate
    * callback method, such as "receiveInteraction".
    *
    * @param datamemberMap data structure containing initial values for the
    * attributes of this new RealTimeDepSche object class instance
    */
    public RealTimeDepSche( ReflectedAttributes datamemberMap ) {
        this( datamemberMap, true );
    }

    /**
    * Like {@link #RealTimeDepSche( ReflectedAttributes datamemberMap )}, except this
    * new RealTimeDepSche object class instance is given a timestamp of
    * "logicalTime".
    *
    * @param datamemberMap data structure containing initial values for the
    * attributes of this new RealTimeDepSche object class instance
    * @param logicalTime timestamp for this new RealTimeDepSche object class
    * instance
    */
    public RealTimeDepSche( ReflectedAttributes datamemberMap, LogicalTime logicalTime ) {
        this( datamemberMap, logicalTime, true );
    }

    /**
    * Creates a new RealTimeDepSche object class instance that is a duplicate
    * of the instance referred to by RealTimeDepSche_var.
    *
    * @param RealTimeDepSche_var RealTimeDepSche object class instance of which
    * this newly created RealTimeDepSche object class instance will be a
    * duplicate
    */
    public RealTimeDepSche( RealTimeDepSche RealTimeDepSche_var ) {
        super( RealTimeDepSche_var );

        set_aircraft( RealTimeDepSche_var.get_aircraft() );
        set_airline( RealTimeDepSche_var.get_airline() );
        set_booked( RealTimeDepSche_var.get_booked() );
        set_capacity( RealTimeDepSche_var.get_capacity() );
        set_checkedIn( RealTimeDepSche_var.get_checkedIn() );
        set_depStatus( RealTimeDepSche_var.get_depStatus() );
        set_estimated( RealTimeDepSche_var.get_estimated() );
        set_flightNumber( RealTimeDepSche_var.get_flightNumber() );
        set_gate( RealTimeDepSche_var.get_gate() );
        set_scheduledDepTime( RealTimeDepSche_var.get_scheduledDepTime() );
        set_transfer( RealTimeDepSche_var.get_transfer() );
    }

    /**
    * Returns the value of the attribute whose name is "datamemberName"
    * for this object.
    *
    * @param datamemberName name of attribute whose value is to be
    * returned
    * @return value of the attribute whose name is "datamemberName"
    * for this object
    */
    public Object getAttribute( String datamemberName ) {
        if ( "aircraft".equals(datamemberName) ) return get_aircraft();
        else if ( "airline".equals(datamemberName) ) return get_airline();
        else if ( "booked".equals(datamemberName) ) return new Integer(get_booked());
        else if ( "capacity".equals(datamemberName) ) return new Integer(get_capacity());
        else if ( "checkedIn".equals(datamemberName) ) return new Integer(get_checkedIn());
        else if ( "depStatus".equals(datamemberName) ) return get_depStatus();
        else if ( "estimated".equals(datamemberName) ) return get_estimated();
        else if ( "flightNumber".equals(datamemberName) ) return get_flightNumber();
        else if ( "gate".equals(datamemberName) ) return get_gate();
        else if ( "scheduledDepTime".equals(datamemberName) ) return get_scheduledDepTime();
        else if ( "transfer".equals(datamemberName) ) return new Integer(get_transfer());
        else return super.getAttribute( datamemberName );
    }

    protected boolean setAttributeAux( String datamemberName, String val ) {
        boolean retval = true;
        if ( "aircraft".equals( datamemberName) ) set_aircraft( val );
        else if ( "airline".equals( datamemberName) ) set_airline( val );
        else if ( "booked".equals( datamemberName) ) set_booked( Integer.parseInt(val) );
        else if ( "capacity".equals( datamemberName) ) set_capacity( Integer.parseInt(val) );
        else if ( "checkedIn".equals( datamemberName) ) set_checkedIn( Integer.parseInt(val) );
        else if ( "depStatus".equals( datamemberName) ) set_depStatus( val );
        else if ( "estimated".equals( datamemberName) ) set_estimated( val );
        else if ( "flightNumber".equals( datamemberName) ) set_flightNumber( val );
        else if ( "gate".equals( datamemberName) ) set_gate( val );
        else if ( "scheduledDepTime".equals( datamemberName) ) set_scheduledDepTime( val );
        else if ( "transfer".equals( datamemberName) ) set_transfer( Integer.parseInt(val) );
        else retval = super.setAttributeAux( datamemberName, val );

        return retval;
    }

    protected boolean setAttributeAux( String datamemberName, Object val ) {
        boolean retval = true;
        if ( "aircraft".equals( datamemberName) ) set_aircraft( (String)val );
        else if ( "airline".equals( datamemberName) ) set_airline( (String)val );
        else if ( "booked".equals( datamemberName) ) set_booked( (Integer)val );
        else if ( "capacity".equals( datamemberName) ) set_capacity( (Integer)val );
        else if ( "checkedIn".equals( datamemberName) ) set_checkedIn( (Integer)val );
        else if ( "depStatus".equals( datamemberName) ) set_depStatus( (String)val );
        else if ( "estimated".equals( datamemberName) ) set_estimated( (String)val );
        else if ( "flightNumber".equals( datamemberName) ) set_flightNumber( (String)val );
        else if ( "gate".equals( datamemberName) ) set_gate( (String)val );
        else if ( "scheduledDepTime".equals( datamemberName) ) set_scheduledDepTime( (String)val );
        else if ( "transfer".equals( datamemberName) ) set_transfer( (Integer)val );
        else retval = super.setAttributeAux( datamemberName, val );

        return retval;
    }

    @Override
    protected SuppliedAttributes createSuppliedDatamembers(boolean force) {
        SuppliedAttributes datamembers = _factory.createSuppliedAttributes();
 
        if (_publishAttributeNameSet.contains("aircraft") && _aircraft.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("aircraft"), getAttribute("aircraft").toString().getBytes() );
            _aircraft.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("airline") && _airline.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("airline"), getAttribute("airline").toString().getBytes() );
            _airline.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("booked") && _booked.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("booked"), getAttribute("booked").toString().getBytes() );
            _booked.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("capacity") && _capacity.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("capacity"), getAttribute("capacity").toString().getBytes() );
            _capacity.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("checkedIn") && _checkedIn.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("checkedIn"), getAttribute("checkedIn").toString().getBytes() );
            _checkedIn.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("depStatus") && _depStatus.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("depStatus"), getAttribute("depStatus").toString().getBytes() );
            _depStatus.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("estimated") && _estimated.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("estimated"), getAttribute("estimated").toString().getBytes() );
            _estimated.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("flightNumber") && _flightNumber.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("flightNumber"), getAttribute("flightNumber").toString().getBytes() );
            _flightNumber.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("gate") && _gate.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("gate"), getAttribute("gate").toString().getBytes() );
            _gate.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("scheduledDepTime") && _scheduledDepTime.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("scheduledDepTime"), getAttribute("scheduledDepTime").toString().getBytes() );
            _scheduledDepTime.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("transfer") && _transfer.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("transfer"), getAttribute("transfer").toString().getBytes() );
            _transfer.setHasBeenUpdated();
        }

        return datamembers;
    }

    public void copyFrom( Object object ) {
        super.copyFrom( object );
        if ( object instanceof RealTimeDepSche ) {
            RealTimeDepSche data = (RealTimeDepSche)object;
            _aircraft = data._aircraft;
            _airline = data._airline;
            _booked = data._booked;
            _capacity = data._capacity;
            _checkedIn = data._checkedIn;
            _depStatus = data._depStatus;
            _estimated = data._estimated;
            _flightNumber = data._flightNumber;
            _gate = data._gate;
            _scheduledDepTime = data._scheduledDepTime;
            _transfer = data._transfer;
        }
    }
}

