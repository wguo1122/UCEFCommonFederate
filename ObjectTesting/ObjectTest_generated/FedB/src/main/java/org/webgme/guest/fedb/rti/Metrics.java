package org.webgme.guest.fedb.rti;

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
* Implements ObjectRoot.Metrics
*/
public class Metrics extends ObjectRoot {

    private static final Logger logger = LogManager.getLogger();

    /**
    * Creates an instance of the Metrics object class with default attribute values.
    */
    public Metrics() {}

    private static int _DataA_handle;
    private static int _DataB_handle;

    private static boolean _isInitialized = false;

    private static int _handle;

    /**
    * Returns the handle (RTI assigned) of the Metrics object class.
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
    * Returns the fully-qualified (dot-delimited) name of the Metrics object class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return the name of the class pertaining to the reference,
    * rather than the name of the class for the instance referred to by the reference.
    * For the polymorphic version of this method, use {@link #getClassName()}.
    *
    * @return the fully-qualified HLA class path for this object class
    */
    public static String get_class_name() {
        return "ObjectRoot.Metrics";
    }

    /**
    * Returns the simple name (the last name in the dot-delimited fully-qualified
    * class name) of the Metrics object class.
    *
    * @return the name of this object class
    */
    public static String get_simple_class_name() {
        return "Metrics";
    }

    private static Set< String > _datamemberNames = new HashSet< String >();
    private static Set< String > _allDatamemberNames = new HashSet< String >();

    /**
    * Returns a set containing the names of all of the non-hidden attributes in the
    * Metrics object class.
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
    * Metrics object class.
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
        _classNameSet.add("ObjectRoot.Metrics");
        _classNameClassMap.put("ObjectRoot.Metrics", Metrics.class);

        _datamemberClassNameSetMap.put("ObjectRoot.Metrics", _datamemberNames);
        _allDatamemberClassNameSetMap.put("ObjectRoot.Metrics", _allDatamemberNames);

        _datamemberNames.add("DataA");
        _datamemberNames.add("DataB");

        _datamemberTypeMap.put("DataA", "int");
        _datamemberTypeMap.put("DataB", "int");

        _allDatamemberNames.add("DataA");
        _allDatamemberNames.add("DataB");

        _classNamePublishAttributeNameMap.put("ObjectRoot.Metrics", _publishAttributeNameSet);
        _classNameSubscribeAttributeNameMap.put("ObjectRoot.Metrics", _subscribeAttributeNameSet);
    }

    protected static void init(RTIambassador rti) {
        if (_isInitialized) return;
        _isInitialized = true;

        ObjectRoot.init(rti);

        boolean isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _handle = rti.getObjectClassHandle("ObjectRoot.Metrics");
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

        _classNameHandleMap.put("ObjectRoot.Metrics", get_handle());
        _classHandleNameMap.put(get_handle(), "ObjectRoot.Metrics");
        _classHandleSimpleNameMap.put(get_handle(), "Metrics");

        isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _DataA_handle = rti.getAttributeHandle("DataA", get_handle());
                _DataB_handle = rti.getAttributeHandle("DataB", get_handle());
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

        _datamemberNameHandleMap.put("ObjectRoot.Metrics.DataA", _DataA_handle);
        _datamemberNameHandleMap.put("ObjectRoot.Metrics.DataB", _DataB_handle);

        _datamemberHandleNameMap.put(_DataA_handle, "DataA");
        _datamemberHandleNameMap.put(_DataB_handle, "DataB");
    }

    private static boolean _isPublished = false;

    /**
    * Publishes the Metrics object class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void publish(RTIambassador rti) {
        if (_isPublished) return;

        init(rti);

        AttributeHandleSet publishedAttributeHandleSet = _factory.createAttributeHandleSet();
        for(String attributeName : _publishAttributeNameSet) {
            try {
                publishedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.Metrics." + attributeName));
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
    * Unpublishes the Metrics object class for a federate.
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
    * Subscribes a federate to the Metrics object class.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void subscribe(RTIambassador rti) {
        if (_isSubscribed) return;

        init(rti);

        AttributeHandleSet subscribedAttributeHandleSet = _factory.createAttributeHandleSet();
        for(String attributeName : _subscribeAttributeNameSet) {
            try {
                subscribedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.Metrics." + attributeName));
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
    * Unsubscribes a federate from the Metrics object class.
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
    * (that is, the Metrics object class).
    *
    * @param handle handle to compare to the value of the handle (RTI assigned) of
    * this class (the Metrics object class).
    * @return "true" if "handle" matches the value of the handle of this class
    * (that is, the Metrics object class).
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
        if (datamemberHandle == _DataA_handle) return "DataA";
        else if (datamemberHandle == _DataB_handle) return "DataB";
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
                + "DataA:" + get_DataA()
                + "," + "DataB:" + get_DataB()
                + ")";
    }


    /**
    * Publishes the "DataA" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "DataA" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_DataA() {
        _publishAttributeNameSet.add( "DataA" );
    }

    /**
    * Unpublishes the "DataA" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "DataA" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_DataA() {
        _publishAttributeNameSet.remove( "DataA" );
    }

    /**
    * Subscribes a federate to the "DataA" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "DataA" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_DataA() {
        _subscribeAttributeNameSet.add( "DataA" );
    }

    /**
    * Unsubscribes a federate from the "DataA" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "DataA" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_DataA() {
        _subscribeAttributeNameSet.remove( "DataA" );
    }

    /**
    * Publishes the "DataB" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "DataB" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_DataB() {
        _publishAttributeNameSet.add( "DataB" );
    }

    /**
    * Unpublishes the "DataB" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "DataB" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_DataB() {
        _publishAttributeNameSet.remove( "DataB" );
    }

    /**
    * Subscribes a federate to the "DataB" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "DataB" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_DataB() {
        _subscribeAttributeNameSet.add( "DataB" );
    }

    /**
    * Unsubscribes a federate from the "DataB" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "DataB" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_DataB() {
        _subscribeAttributeNameSet.remove( "DataB" );
    }

    protected Attribute< Integer > _DataA =
            new Attribute< Integer >(  new Integer( 0 )  );

    /**
    * Set the value of the "DataA" attribute to "value" for this object.
    *
    * @param value the new value for the "DataA" attribute
    */
    public void set_DataA( int value ) {
        _DataA.setValue( value );
        _DataA.setTime( getTime() );
    }

    /**
    * Returns the value of the "DataA" attribute of this object.
    *
    * @return the value of the "DataA" attribute
    */
    public int get_DataA() {
        return _DataA.getValue();
    }

    /**
    * Returns the current timestamp of the "DataA" attribute of this object.
    *
    * @return the current timestamp of the "DataA" attribute
    */
    public double get_DataA_time() {
        return _DataA.getTime();
    }

    protected Attribute< Integer > _DataB =
            new Attribute< Integer >(  new Integer( 0 )  );

    /**
    * Set the value of the "DataB" attribute to "value" for this object.
    *
    * @param value the new value for the "DataB" attribute
    */
    public void set_DataB( int value ) {
        _DataB.setValue( value );
        _DataB.setTime( getTime() );
    }

    /**
    * Returns the value of the "DataB" attribute of this object.
    *
    * @return the value of the "DataB" attribute
    */
    public int get_DataB() {
        return _DataB.getValue();
    }

    /**
    * Returns the current timestamp of the "DataB" attribute of this object.
    *
    * @return the current timestamp of the "DataB" attribute
    */
    public double get_DataB_time() {
        return _DataB.getTime();
    }

    protected Metrics( ReflectedAttributes datamemberMap, boolean initFlag ) {
        super( datamemberMap, false );
        if ( initFlag ) setAttributes( datamemberMap );
    }

    protected Metrics( ReflectedAttributes datamemberMap, LogicalTime logicalTime, boolean initFlag ) {
        super( datamemberMap, logicalTime, false );
        if ( initFlag ) setAttributes( datamemberMap );
    }

    /**
    * Creates an instance of the Metrics object class, using
    * "datamemberMap" to initialize its attribute values.
    * "datamemberMap" is usually acquired as an argument to an RTI federate
    * callback method, such as "receiveInteraction".
    *
    * @param datamemberMap data structure containing initial values for the
    * attributes of this new Metrics object class instance
    */
    public Metrics( ReflectedAttributes datamemberMap ) {
        this( datamemberMap, true );
    }

    /**
    * Like {@link #Metrics( ReflectedAttributes datamemberMap )}, except this
    * new Metrics object class instance is given a timestamp of
    * "logicalTime".
    *
    * @param datamemberMap data structure containing initial values for the
    * attributes of this new Metrics object class instance
    * @param logicalTime timestamp for this new Metrics object class
    * instance
    */
    public Metrics( ReflectedAttributes datamemberMap, LogicalTime logicalTime ) {
        this( datamemberMap, logicalTime, true );
    }

    /**
    * Creates a new Metrics object class instance that is a duplicate
    * of the instance referred to by Metrics_var.
    *
    * @param Metrics_var Metrics object class instance of which
    * this newly created Metrics object class instance will be a
    * duplicate
    */
    public Metrics( Metrics Metrics_var ) {
        super( Metrics_var );

        set_DataA( Metrics_var.get_DataA() );
        set_DataB( Metrics_var.get_DataB() );
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
        if ( "DataA".equals(datamemberName) ) return new Integer(get_DataA());
        else if ( "DataB".equals(datamemberName) ) return new Integer(get_DataB());
        else return super.getAttribute( datamemberName );
    }

    protected boolean setAttributeAux( String datamemberName, String val ) {
        boolean retval = true;
        if ( "DataA".equals( datamemberName) ) set_DataA( Integer.parseInt(val) );
        else if ( "DataB".equals( datamemberName) ) set_DataB( Integer.parseInt(val) );
        else retval = super.setAttributeAux( datamemberName, val );

        return retval;
    }

    protected boolean setAttributeAux( String datamemberName, Object val ) {
        boolean retval = true;
        if ( "DataA".equals( datamemberName) ) set_DataA( (Integer)val );
        else if ( "DataB".equals( datamemberName) ) set_DataB( (Integer)val );
        else retval = super.setAttributeAux( datamemberName, val );

        return retval;
    }

    @Override
    protected SuppliedAttributes createSuppliedDatamembers(boolean force) {
        SuppliedAttributes datamembers = _factory.createSuppliedAttributes();
 
        if (_publishAttributeNameSet.contains("DataA") && _DataA.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("DataA"), getAttribute("DataA").toString().getBytes() );
            _DataA.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("DataB") && _DataB.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("DataB"), getAttribute("DataB").toString().getBytes() );
            _DataB.setHasBeenUpdated();
        }

        return datamembers;
    }

    public void copyFrom( Object object ) {
        super.copyFrom( object );
        if ( object instanceof Metrics ) {
            Metrics data = (Metrics)object;
            _DataA = data._DataA;
            _DataB = data._DataB;
        }
    }
}

