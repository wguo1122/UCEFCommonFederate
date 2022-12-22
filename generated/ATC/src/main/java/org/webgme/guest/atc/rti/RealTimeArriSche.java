package org.webgme.guest.atc.rti;

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
* Implements ObjectRoot.RealTimeArriSche
*/
public class RealTimeArriSche extends ObjectRoot {

    private static final Logger logger = LogManager.getLogger();

    /**
    * Creates an instance of the RealTimeArriSche object class with default attribute values.
    */
    public RealTimeArriSche() {}

    private static int _aircraft_handle;
    private static int _airline_handle;
    private static int _arrivalStatus_handle;
    private static int _booked_handle;
    private static int _capacity_handle;
    private static int _checkedIn_handle;
    private static int _estimatedArrivalTime_handle;
    private static int _flightNumber_handle;
    private static int _gate_handle;
    private static int _passNum_handle;
    private static int _scheduledArrivalTime_handle;
    private static int _transferOut_handle;

    private static boolean _isInitialized = false;

    private static int _handle;

    /**
    * Returns the handle (RTI assigned) of the RealTimeArriSche object class.
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
    * Returns the fully-qualified (dot-delimited) name of the RealTimeArriSche object class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return the name of the class pertaining to the reference,
    * rather than the name of the class for the instance referred to by the reference.
    * For the polymorphic version of this method, use {@link #getClassName()}.
    *
    * @return the fully-qualified HLA class path for this object class
    */
    public static String get_class_name() {
        return "ObjectRoot.RealTimeArriSche";
    }

    /**
    * Returns the simple name (the last name in the dot-delimited fully-qualified
    * class name) of the RealTimeArriSche object class.
    *
    * @return the name of this object class
    */
    public static String get_simple_class_name() {
        return "RealTimeArriSche";
    }

    private static Set< String > _datamemberNames = new HashSet< String >();
    private static Set< String > _allDatamemberNames = new HashSet< String >();

    /**
    * Returns a set containing the names of all of the non-hidden attributes in the
    * RealTimeArriSche object class.
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
    * RealTimeArriSche object class.
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
        _classNameSet.add("ObjectRoot.RealTimeArriSche");
        _classNameClassMap.put("ObjectRoot.RealTimeArriSche", RealTimeArriSche.class);

        _datamemberClassNameSetMap.put("ObjectRoot.RealTimeArriSche", _datamemberNames);
        _allDatamemberClassNameSetMap.put("ObjectRoot.RealTimeArriSche", _allDatamemberNames);

        _datamemberNames.add("aircraft");
        _datamemberNames.add("airline");
        _datamemberNames.add("arrivalStatus");
        _datamemberNames.add("booked");
        _datamemberNames.add("capacity");
        _datamemberNames.add("checkedIn");
        _datamemberNames.add("estimatedArrivalTime");
        _datamemberNames.add("flightNumber");
        _datamemberNames.add("gate");
        _datamemberNames.add("passNum");
        _datamemberNames.add("scheduledArrivalTime");
        _datamemberNames.add("transferOut");

        _datamemberTypeMap.put("aircraft", "String");
        _datamemberTypeMap.put("airline", "String");
        _datamemberTypeMap.put("arrivalStatus", "String");
        _datamemberTypeMap.put("booked", "String");
        _datamemberTypeMap.put("capacity", "String");
        _datamemberTypeMap.put("checkedIn", "String");
        _datamemberTypeMap.put("estimatedArrivalTime", "String");
        _datamemberTypeMap.put("flightNumber", "String");
        _datamemberTypeMap.put("gate", "String");
        _datamemberTypeMap.put("passNum", "String");
        _datamemberTypeMap.put("scheduledArrivalTime", "String");
        _datamemberTypeMap.put("transferOut", "String");

        _allDatamemberNames.add("aircraft");
        _allDatamemberNames.add("airline");
        _allDatamemberNames.add("arrivalStatus");
        _allDatamemberNames.add("booked");
        _allDatamemberNames.add("capacity");
        _allDatamemberNames.add("checkedIn");
        _allDatamemberNames.add("estimatedArrivalTime");
        _allDatamemberNames.add("flightNumber");
        _allDatamemberNames.add("gate");
        _allDatamemberNames.add("passNum");
        _allDatamemberNames.add("scheduledArrivalTime");
        _allDatamemberNames.add("transferOut");

        _classNamePublishAttributeNameMap.put("ObjectRoot.RealTimeArriSche", _publishAttributeNameSet);
        _classNameSubscribeAttributeNameMap.put("ObjectRoot.RealTimeArriSche", _subscribeAttributeNameSet);
    }

    protected static void init(RTIambassador rti) {
        if (_isInitialized) return;
        _isInitialized = true;

        ObjectRoot.init(rti);

        boolean isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _handle = rti.getObjectClassHandle("ObjectRoot.RealTimeArriSche");
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

        _classNameHandleMap.put("ObjectRoot.RealTimeArriSche", get_handle());
        _classHandleNameMap.put(get_handle(), "ObjectRoot.RealTimeArriSche");
        _classHandleSimpleNameMap.put(get_handle(), "RealTimeArriSche");

        isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _aircraft_handle = rti.getAttributeHandle("aircraft", get_handle());
                _airline_handle = rti.getAttributeHandle("airline", get_handle());
                _arrivalStatus_handle = rti.getAttributeHandle("arrivalStatus", get_handle());
                _booked_handle = rti.getAttributeHandle("booked", get_handle());
                _capacity_handle = rti.getAttributeHandle("capacity", get_handle());
                _checkedIn_handle = rti.getAttributeHandle("checkedIn", get_handle());
                _estimatedArrivalTime_handle = rti.getAttributeHandle("estimatedArrivalTime", get_handle());
                _flightNumber_handle = rti.getAttributeHandle("flightNumber", get_handle());
                _gate_handle = rti.getAttributeHandle("gate", get_handle());
                _passNum_handle = rti.getAttributeHandle("passNum", get_handle());
                _scheduledArrivalTime_handle = rti.getAttributeHandle("scheduledArrivalTime", get_handle());
                _transferOut_handle = rti.getAttributeHandle("transferOut", get_handle());
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

        _datamemberNameHandleMap.put("ObjectRoot.RealTimeArriSche.aircraft", _aircraft_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeArriSche.airline", _airline_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeArriSche.arrivalStatus", _arrivalStatus_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeArriSche.booked", _booked_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeArriSche.capacity", _capacity_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeArriSche.checkedIn", _checkedIn_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeArriSche.estimatedArrivalTime", _estimatedArrivalTime_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeArriSche.flightNumber", _flightNumber_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeArriSche.gate", _gate_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeArriSche.passNum", _passNum_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeArriSche.scheduledArrivalTime", _scheduledArrivalTime_handle);
        _datamemberNameHandleMap.put("ObjectRoot.RealTimeArriSche.transferOut", _transferOut_handle);

        _datamemberHandleNameMap.put(_aircraft_handle, "aircraft");
        _datamemberHandleNameMap.put(_airline_handle, "airline");
        _datamemberHandleNameMap.put(_arrivalStatus_handle, "arrivalStatus");
        _datamemberHandleNameMap.put(_booked_handle, "booked");
        _datamemberHandleNameMap.put(_capacity_handle, "capacity");
        _datamemberHandleNameMap.put(_checkedIn_handle, "checkedIn");
        _datamemberHandleNameMap.put(_estimatedArrivalTime_handle, "estimatedArrivalTime");
        _datamemberHandleNameMap.put(_flightNumber_handle, "flightNumber");
        _datamemberHandleNameMap.put(_gate_handle, "gate");
        _datamemberHandleNameMap.put(_passNum_handle, "passNum");
        _datamemberHandleNameMap.put(_scheduledArrivalTime_handle, "scheduledArrivalTime");
        _datamemberHandleNameMap.put(_transferOut_handle, "transferOut");
    }

    private static boolean _isPublished = false;

    /**
    * Publishes the RealTimeArriSche object class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void publish(RTIambassador rti) {
        if (_isPublished) return;

        init(rti);

        AttributeHandleSet publishedAttributeHandleSet = _factory.createAttributeHandleSet();
        for(String attributeName : _publishAttributeNameSet) {
            try {
                publishedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.RealTimeArriSche." + attributeName));
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
    * Unpublishes the RealTimeArriSche object class for a federate.
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
    * Subscribes a federate to the RealTimeArriSche object class.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void subscribe(RTIambassador rti) {
        if (_isSubscribed) return;

        init(rti);

        AttributeHandleSet subscribedAttributeHandleSet = _factory.createAttributeHandleSet();
        for(String attributeName : _subscribeAttributeNameSet) {
            try {
                subscribedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.RealTimeArriSche." + attributeName));
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
    * Unsubscribes a federate from the RealTimeArriSche object class.
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
    * (that is, the RealTimeArriSche object class).
    *
    * @param handle handle to compare to the value of the handle (RTI assigned) of
    * this class (the RealTimeArriSche object class).
    * @return "true" if "handle" matches the value of the handle of this class
    * (that is, the RealTimeArriSche object class).
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
        else if (datamemberHandle == _arrivalStatus_handle) return "arrivalStatus";
        else if (datamemberHandle == _booked_handle) return "booked";
        else if (datamemberHandle == _capacity_handle) return "capacity";
        else if (datamemberHandle == _checkedIn_handle) return "checkedIn";
        else if (datamemberHandle == _estimatedArrivalTime_handle) return "estimatedArrivalTime";
        else if (datamemberHandle == _flightNumber_handle) return "flightNumber";
        else if (datamemberHandle == _gate_handle) return "gate";
        else if (datamemberHandle == _passNum_handle) return "passNum";
        else if (datamemberHandle == _scheduledArrivalTime_handle) return "scheduledArrivalTime";
        else if (datamemberHandle == _transferOut_handle) return "transferOut";
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
                + "," + "arrivalStatus:" + get_arrivalStatus()
                + "," + "booked:" + get_booked()
                + "," + "capacity:" + get_capacity()
                + "," + "checkedIn:" + get_checkedIn()
                + "," + "estimatedArrivalTime:" + get_estimatedArrivalTime()
                + "," + "flightNumber:" + get_flightNumber()
                + "," + "gate:" + get_gate()
                + "," + "passNum:" + get_passNum()
                + "," + "scheduledArrivalTime:" + get_scheduledArrivalTime()
                + "," + "transferOut:" + get_transferOut()
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
    * Publishes the "arrivalStatus" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "arrivalStatus" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_arrivalStatus() {
        _publishAttributeNameSet.add( "arrivalStatus" );
    }

    /**
    * Unpublishes the "arrivalStatus" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "arrivalStatus" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_arrivalStatus() {
        _publishAttributeNameSet.remove( "arrivalStatus" );
    }

    /**
    * Subscribes a federate to the "arrivalStatus" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "arrivalStatus" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_arrivalStatus() {
        _subscribeAttributeNameSet.add( "arrivalStatus" );
    }

    /**
    * Unsubscribes a federate from the "arrivalStatus" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "arrivalStatus" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_arrivalStatus() {
        _subscribeAttributeNameSet.remove( "arrivalStatus" );
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
    * Publishes the "estimatedArrivalTime" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "estimatedArrivalTime" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_estimatedArrivalTime() {
        _publishAttributeNameSet.add( "estimatedArrivalTime" );
    }

    /**
    * Unpublishes the "estimatedArrivalTime" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "estimatedArrivalTime" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_estimatedArrivalTime() {
        _publishAttributeNameSet.remove( "estimatedArrivalTime" );
    }

    /**
    * Subscribes a federate to the "estimatedArrivalTime" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "estimatedArrivalTime" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_estimatedArrivalTime() {
        _subscribeAttributeNameSet.add( "estimatedArrivalTime" );
    }

    /**
    * Unsubscribes a federate from the "estimatedArrivalTime" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "estimatedArrivalTime" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_estimatedArrivalTime() {
        _subscribeAttributeNameSet.remove( "estimatedArrivalTime" );
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
    * Publishes the "passNum" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "passNum" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_passNum() {
        _publishAttributeNameSet.add( "passNum" );
    }

    /**
    * Unpublishes the "passNum" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "passNum" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_passNum() {
        _publishAttributeNameSet.remove( "passNum" );
    }

    /**
    * Subscribes a federate to the "passNum" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "passNum" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_passNum() {
        _subscribeAttributeNameSet.add( "passNum" );
    }

    /**
    * Unsubscribes a federate from the "passNum" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "passNum" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_passNum() {
        _subscribeAttributeNameSet.remove( "passNum" );
    }

    /**
    * Publishes the "scheduledArrivalTime" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "scheduledArrivalTime" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_scheduledArrivalTime() {
        _publishAttributeNameSet.add( "scheduledArrivalTime" );
    }

    /**
    * Unpublishes the "scheduledArrivalTime" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "scheduledArrivalTime" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_scheduledArrivalTime() {
        _publishAttributeNameSet.remove( "scheduledArrivalTime" );
    }

    /**
    * Subscribes a federate to the "scheduledArrivalTime" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "scheduledArrivalTime" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_scheduledArrivalTime() {
        _subscribeAttributeNameSet.add( "scheduledArrivalTime" );
    }

    /**
    * Unsubscribes a federate from the "scheduledArrivalTime" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "scheduledArrivalTime" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_scheduledArrivalTime() {
        _subscribeAttributeNameSet.remove( "scheduledArrivalTime" );
    }

    /**
    * Publishes the "transferOut" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "transferOut" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_transferOut() {
        _publishAttributeNameSet.add( "transferOut" );
    }

    /**
    * Unpublishes the "transferOut" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "transferOut" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_transferOut() {
        _publishAttributeNameSet.remove( "transferOut" );
    }

    /**
    * Subscribes a federate to the "transferOut" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "transferOut" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_transferOut() {
        _subscribeAttributeNameSet.add( "transferOut" );
    }

    /**
    * Unsubscribes a federate from the "transferOut" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "transferOut" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_transferOut() {
        _subscribeAttributeNameSet.remove( "transferOut" );
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

    protected Attribute< String > _arrivalStatus =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "arrivalStatus" attribute to "value" for this object.
    *
    * @param value the new value for the "arrivalStatus" attribute
    */
    public void set_arrivalStatus( String value ) {
        _arrivalStatus.setValue( value );
        _arrivalStatus.setTime( getTime() );
    }

    /**
    * Returns the value of the "arrivalStatus" attribute of this object.
    *
    * @return the value of the "arrivalStatus" attribute
    */
    public String get_arrivalStatus() {
        return _arrivalStatus.getValue();
    }

    /**
    * Returns the current timestamp of the "arrivalStatus" attribute of this object.
    *
    * @return the current timestamp of the "arrivalStatus" attribute
    */
    public double get_arrivalStatus_time() {
        return _arrivalStatus.getTime();
    }

    protected Attribute< String > _booked =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "booked" attribute to "value" for this object.
    *
    * @param value the new value for the "booked" attribute
    */
    public void set_booked( String value ) {
        _booked.setValue( value );
        _booked.setTime( getTime() );
    }

    /**
    * Returns the value of the "booked" attribute of this object.
    *
    * @return the value of the "booked" attribute
    */
    public String get_booked() {
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

    protected Attribute< String > _capacity =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "capacity" attribute to "value" for this object.
    *
    * @param value the new value for the "capacity" attribute
    */
    public void set_capacity( String value ) {
        _capacity.setValue( value );
        _capacity.setTime( getTime() );
    }

    /**
    * Returns the value of the "capacity" attribute of this object.
    *
    * @return the value of the "capacity" attribute
    */
    public String get_capacity() {
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

    protected Attribute< String > _checkedIn =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "checkedIn" attribute to "value" for this object.
    *
    * @param value the new value for the "checkedIn" attribute
    */
    public void set_checkedIn( String value ) {
        _checkedIn.setValue( value );
        _checkedIn.setTime( getTime() );
    }

    /**
    * Returns the value of the "checkedIn" attribute of this object.
    *
    * @return the value of the "checkedIn" attribute
    */
    public String get_checkedIn() {
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

    protected Attribute< String > _estimatedArrivalTime =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "estimatedArrivalTime" attribute to "value" for this object.
    *
    * @param value the new value for the "estimatedArrivalTime" attribute
    */
    public void set_estimatedArrivalTime( String value ) {
        _estimatedArrivalTime.setValue( value );
        _estimatedArrivalTime.setTime( getTime() );
    }

    /**
    * Returns the value of the "estimatedArrivalTime" attribute of this object.
    *
    * @return the value of the "estimatedArrivalTime" attribute
    */
    public String get_estimatedArrivalTime() {
        return _estimatedArrivalTime.getValue();
    }

    /**
    * Returns the current timestamp of the "estimatedArrivalTime" attribute of this object.
    *
    * @return the current timestamp of the "estimatedArrivalTime" attribute
    */
    public double get_estimatedArrivalTime_time() {
        return _estimatedArrivalTime.getTime();
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

    protected Attribute< String > _passNum =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "passNum" attribute to "value" for this object.
    *
    * @param value the new value for the "passNum" attribute
    */
    public void set_passNum( String value ) {
        _passNum.setValue( value );
        _passNum.setTime( getTime() );
    }

    /**
    * Returns the value of the "passNum" attribute of this object.
    *
    * @return the value of the "passNum" attribute
    */
    public String get_passNum() {
        return _passNum.getValue();
    }

    /**
    * Returns the current timestamp of the "passNum" attribute of this object.
    *
    * @return the current timestamp of the "passNum" attribute
    */
    public double get_passNum_time() {
        return _passNum.getTime();
    }

    protected Attribute< String > _scheduledArrivalTime =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "scheduledArrivalTime" attribute to "value" for this object.
    *
    * @param value the new value for the "scheduledArrivalTime" attribute
    */
    public void set_scheduledArrivalTime( String value ) {
        _scheduledArrivalTime.setValue( value );
        _scheduledArrivalTime.setTime( getTime() );
    }

    /**
    * Returns the value of the "scheduledArrivalTime" attribute of this object.
    *
    * @return the value of the "scheduledArrivalTime" attribute
    */
    public String get_scheduledArrivalTime() {
        return _scheduledArrivalTime.getValue();
    }

    /**
    * Returns the current timestamp of the "scheduledArrivalTime" attribute of this object.
    *
    * @return the current timestamp of the "scheduledArrivalTime" attribute
    */
    public double get_scheduledArrivalTime_time() {
        return _scheduledArrivalTime.getTime();
    }

    protected Attribute< String > _transferOut =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "transferOut" attribute to "value" for this object.
    *
    * @param value the new value for the "transferOut" attribute
    */
    public void set_transferOut( String value ) {
        _transferOut.setValue( value );
        _transferOut.setTime( getTime() );
    }

    /**
    * Returns the value of the "transferOut" attribute of this object.
    *
    * @return the value of the "transferOut" attribute
    */
    public String get_transferOut() {
        return _transferOut.getValue();
    }

    /**
    * Returns the current timestamp of the "transferOut" attribute of this object.
    *
    * @return the current timestamp of the "transferOut" attribute
    */
    public double get_transferOut_time() {
        return _transferOut.getTime();
    }

    protected RealTimeArriSche( ReflectedAttributes datamemberMap, boolean initFlag ) {
        super( datamemberMap, false );
        if ( initFlag ) setAttributes( datamemberMap );
    }

    protected RealTimeArriSche( ReflectedAttributes datamemberMap, LogicalTime logicalTime, boolean initFlag ) {
        super( datamemberMap, logicalTime, false );
        if ( initFlag ) setAttributes( datamemberMap );
    }

    /**
    * Creates an instance of the RealTimeArriSche object class, using
    * "datamemberMap" to initialize its attribute values.
    * "datamemberMap" is usually acquired as an argument to an RTI federate
    * callback method, such as "receiveInteraction".
    *
    * @param datamemberMap data structure containing initial values for the
    * attributes of this new RealTimeArriSche object class instance
    */
    public RealTimeArriSche( ReflectedAttributes datamemberMap ) {
        this( datamemberMap, true );
    }

    /**
    * Like {@link #RealTimeArriSche( ReflectedAttributes datamemberMap )}, except this
    * new RealTimeArriSche object class instance is given a timestamp of
    * "logicalTime".
    *
    * @param datamemberMap data structure containing initial values for the
    * attributes of this new RealTimeArriSche object class instance
    * @param logicalTime timestamp for this new RealTimeArriSche object class
    * instance
    */
    public RealTimeArriSche( ReflectedAttributes datamemberMap, LogicalTime logicalTime ) {
        this( datamemberMap, logicalTime, true );
    }

    /**
    * Creates a new RealTimeArriSche object class instance that is a duplicate
    * of the instance referred to by RealTimeArriSche_var.
    *
    * @param RealTimeArriSche_var RealTimeArriSche object class instance of which
    * this newly created RealTimeArriSche object class instance will be a
    * duplicate
    */
    public RealTimeArriSche( RealTimeArriSche RealTimeArriSche_var ) {
        super( RealTimeArriSche_var );

        set_aircraft( RealTimeArriSche_var.get_aircraft() );
        set_airline( RealTimeArriSche_var.get_airline() );
        set_arrivalStatus( RealTimeArriSche_var.get_arrivalStatus() );
        set_booked( RealTimeArriSche_var.get_booked() );
        set_capacity( RealTimeArriSche_var.get_capacity() );
        set_checkedIn( RealTimeArriSche_var.get_checkedIn() );
        set_estimatedArrivalTime( RealTimeArriSche_var.get_estimatedArrivalTime() );
        set_flightNumber( RealTimeArriSche_var.get_flightNumber() );
        set_gate( RealTimeArriSche_var.get_gate() );
        set_passNum( RealTimeArriSche_var.get_passNum() );
        set_scheduledArrivalTime( RealTimeArriSche_var.get_scheduledArrivalTime() );
        set_transferOut( RealTimeArriSche_var.get_transferOut() );
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
        else if ( "arrivalStatus".equals(datamemberName) ) return get_arrivalStatus();
        else if ( "booked".equals(datamemberName) ) return get_booked();
        else if ( "capacity".equals(datamemberName) ) return get_capacity();
        else if ( "checkedIn".equals(datamemberName) ) return get_checkedIn();
        else if ( "estimatedArrivalTime".equals(datamemberName) ) return get_estimatedArrivalTime();
        else if ( "flightNumber".equals(datamemberName) ) return get_flightNumber();
        else if ( "gate".equals(datamemberName) ) return get_gate();
        else if ( "passNum".equals(datamemberName) ) return get_passNum();
        else if ( "scheduledArrivalTime".equals(datamemberName) ) return get_scheduledArrivalTime();
        else if ( "transferOut".equals(datamemberName) ) return get_transferOut();
        else return super.getAttribute( datamemberName );
    }

    protected boolean setAttributeAux( String datamemberName, String val ) {
        boolean retval = true;
        if ( "aircraft".equals( datamemberName) ) set_aircraft( val );
        else if ( "airline".equals( datamemberName) ) set_airline( val );
        else if ( "arrivalStatus".equals( datamemberName) ) set_arrivalStatus( val );
        else if ( "booked".equals( datamemberName) ) set_booked( val );
        else if ( "capacity".equals( datamemberName) ) set_capacity( val );
        else if ( "checkedIn".equals( datamemberName) ) set_checkedIn( val );
        else if ( "estimatedArrivalTime".equals( datamemberName) ) set_estimatedArrivalTime( val );
        else if ( "flightNumber".equals( datamemberName) ) set_flightNumber( val );
        else if ( "gate".equals( datamemberName) ) set_gate( val );
        else if ( "passNum".equals( datamemberName) ) set_passNum( val );
        else if ( "scheduledArrivalTime".equals( datamemberName) ) set_scheduledArrivalTime( val );
        else if ( "transferOut".equals( datamemberName) ) set_transferOut( val );
        else retval = super.setAttributeAux( datamemberName, val );

        return retval;
    }

    protected boolean setAttributeAux( String datamemberName, Object val ) {
        boolean retval = true;
        if ( "aircraft".equals( datamemberName) ) set_aircraft( (String)val );
        else if ( "airline".equals( datamemberName) ) set_airline( (String)val );
        else if ( "arrivalStatus".equals( datamemberName) ) set_arrivalStatus( (String)val );
        else if ( "booked".equals( datamemberName) ) set_booked( (String)val );
        else if ( "capacity".equals( datamemberName) ) set_capacity( (String)val );
        else if ( "checkedIn".equals( datamemberName) ) set_checkedIn( (String)val );
        else if ( "estimatedArrivalTime".equals( datamemberName) ) set_estimatedArrivalTime( (String)val );
        else if ( "flightNumber".equals( datamemberName) ) set_flightNumber( (String)val );
        else if ( "gate".equals( datamemberName) ) set_gate( (String)val );
        else if ( "passNum".equals( datamemberName) ) set_passNum( (String)val );
        else if ( "scheduledArrivalTime".equals( datamemberName) ) set_scheduledArrivalTime( (String)val );
        else if ( "transferOut".equals( datamemberName) ) set_transferOut( (String)val );
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

        if (_publishAttributeNameSet.contains("arrivalStatus") && _arrivalStatus.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("arrivalStatus"), getAttribute("arrivalStatus").toString().getBytes() );
            _arrivalStatus.setHasBeenUpdated();
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

        if (_publishAttributeNameSet.contains("estimatedArrivalTime") && _estimatedArrivalTime.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("estimatedArrivalTime"), getAttribute("estimatedArrivalTime").toString().getBytes() );
            _estimatedArrivalTime.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("flightNumber") && _flightNumber.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("flightNumber"), getAttribute("flightNumber").toString().getBytes() );
            _flightNumber.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("gate") && _gate.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("gate"), getAttribute("gate").toString().getBytes() );
            _gate.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("passNum") && _passNum.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("passNum"), getAttribute("passNum").toString().getBytes() );
            _passNum.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("scheduledArrivalTime") && _scheduledArrivalTime.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("scheduledArrivalTime"), getAttribute("scheduledArrivalTime").toString().getBytes() );
            _scheduledArrivalTime.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("transferOut") && _transferOut.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("transferOut"), getAttribute("transferOut").toString().getBytes() );
            _transferOut.setHasBeenUpdated();
        }

        return datamembers;
    }

    public void copyFrom( Object object ) {
        super.copyFrom( object );
        if ( object instanceof RealTimeArriSche ) {
            RealTimeArriSche data = (RealTimeArriSche)object;
            _aircraft = data._aircraft;
            _airline = data._airline;
            _arrivalStatus = data._arrivalStatus;
            _booked = data._booked;
            _capacity = data._capacity;
            _checkedIn = data._checkedIn;
            _estimatedArrivalTime = data._estimatedArrivalTime;
            _flightNumber = data._flightNumber;
            _gate = data._gate;
            _passNum = data._passNum;
            _scheduledArrivalTime = data._scheduledArrivalTime;
            _transferOut = data._transferOut;
        }
    }
}

