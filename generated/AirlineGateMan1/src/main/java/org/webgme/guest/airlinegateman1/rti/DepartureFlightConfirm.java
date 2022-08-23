package org.webgme.guest.airlinegateman1.rti;

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
* Implements InteractionRoot.C2WInteractionRoot.DepartureFlightConfirm
*/
public class DepartureFlightConfirm extends C2WInteractionRoot {

    private static final Logger logger = LogManager.getLogger();

    /**
    * Creates an instance of the DepartureFlightConfirm interaction class with default parameter values.
    */
    public DepartureFlightConfirm() {}

    private static int _actualLogicalGenerationTime_handle;
    private static int _deptConfirm_handle;
    private static int _federateFilter_handle;
    private static int _gateAvailable_handle;
    private static int _gateNum_handle;
    private static int _originFed_handle;
    private static int _sourceFed_handle;

    private static boolean _isInitialized = false;

    private static int _handle;

    /**
    * Returns the handle (RTI assigned) of the DepartureFlightConfirm interaction class.
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
    * Returns the fully-qualified (dot-delimited) name of the DepartureFlightConfirm interaction class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return the name of the class pertaining to the reference,
    * rather than the name of the class for the instance referred to by the reference.
    * For the polymorphic version of this method, use {@link #getClassName()}.
    *
    * @return the fully-qualified HLA class path for this interaction class
    */
    public static String get_class_name() {
        return "InteractionRoot.C2WInteractionRoot.DepartureFlightConfirm";
    }

    /**
    * Returns the simple name (the last name in the dot-delimited fully-qualified
    * class name) of the DepartureFlightConfirm interaction class.
    *
    * @return the name of this interaction class
    */
    public static String get_simple_class_name() {
        return "DepartureFlightConfirm";
    }

    private static Set< String > _datamemberNames = new HashSet< String >();
    private static Set< String > _allDatamemberNames = new HashSet< String >();

    /**
    * Returns a set containing the names of all of the non-hidden parameters in the
    * DepartureFlightConfirm interaction class.
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
    * DepartureFlightConfirm interaction class.
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
        _classNameSet.add("InteractionRoot.C2WInteractionRoot.DepartureFlightConfirm");
        _classNameClassMap.put("InteractionRoot.C2WInteractionRoot.DepartureFlightConfirm", DepartureFlightConfirm.class);

        _datamemberClassNameSetMap.put("InteractionRoot.C2WInteractionRoot.DepartureFlightConfirm", _datamemberNames);
        _allDatamemberClassNameSetMap.put("InteractionRoot.C2WInteractionRoot.DepartureFlightConfirm", _allDatamemberNames);

        _datamemberNames.add("deptConfirm");
        _datamemberNames.add("gateAvailable");
        _datamemberNames.add("gateNum");

        _datamemberTypeMap.put("deptConfirm", "boolean");
        _datamemberTypeMap.put("gateAvailable", "boolean");
        _datamemberTypeMap.put("gateNum", "int");

        _allDatamemberNames.add("actualLogicalGenerationTime");
        _allDatamemberNames.add("deptConfirm");
        _allDatamemberNames.add("federateFilter");
        _allDatamemberNames.add("gateAvailable");
        _allDatamemberNames.add("gateNum");
        _allDatamemberNames.add("originFed");
        _allDatamemberNames.add("sourceFed");
    }

    protected static void init(RTIambassador rti) {
        if (_isInitialized) return;
        _isInitialized = true;

        C2WInteractionRoot.init(rti);

        boolean isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _handle = rti.getInteractionClassHandle("InteractionRoot.C2WInteractionRoot.DepartureFlightConfirm");
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

        _classNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DepartureFlightConfirm", get_handle());
        _classHandleNameMap.put(get_handle(), "InteractionRoot.C2WInteractionRoot.DepartureFlightConfirm");
        _classHandleSimpleNameMap.put(get_handle(), "DepartureFlightConfirm");

        isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _actualLogicalGenerationTime_handle = rti.getParameterHandle("actualLogicalGenerationTime", get_handle());
                _deptConfirm_handle = rti.getParameterHandle("deptConfirm", get_handle());
                _federateFilter_handle = rti.getParameterHandle("federateFilter", get_handle());
                _gateAvailable_handle = rti.getParameterHandle("gateAvailable", get_handle());
                _gateNum_handle = rti.getParameterHandle("gateNum", get_handle());
                _originFed_handle = rti.getParameterHandle("originFed", get_handle());
                _sourceFed_handle = rti.getParameterHandle("sourceFed", get_handle());
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

        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DepartureFlightConfirm.actualLogicalGenerationTime", _actualLogicalGenerationTime_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DepartureFlightConfirm.deptConfirm", _deptConfirm_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DepartureFlightConfirm.federateFilter", _federateFilter_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DepartureFlightConfirm.gateAvailable", _gateAvailable_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DepartureFlightConfirm.gateNum", _gateNum_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DepartureFlightConfirm.originFed", _originFed_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DepartureFlightConfirm.sourceFed", _sourceFed_handle);

        _datamemberHandleNameMap.put(_actualLogicalGenerationTime_handle, "actualLogicalGenerationTime");
        _datamemberHandleNameMap.put(_deptConfirm_handle, "deptConfirm");
        _datamemberHandleNameMap.put(_federateFilter_handle, "federateFilter");
        _datamemberHandleNameMap.put(_gateAvailable_handle, "gateAvailable");
        _datamemberHandleNameMap.put(_gateNum_handle, "gateNum");
        _datamemberHandleNameMap.put(_originFed_handle, "originFed");
        _datamemberHandleNameMap.put(_sourceFed_handle, "sourceFed");
    }

    private static boolean _isPublished = false;

    /**
    * Publishes the DepartureFlightConfirm interaction class for a federate.
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
    * Unpublishes the DepartureFlightConfirm interaction class for a federate.
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
    * Subscribes a federate to the DepartureFlightConfirm interaction class.
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
    * Unsubscribes a federate from the DepartureFlightConfirm interaction class.
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
    * (that is, the DepartureFlightConfirm interaction class).
    *
    * @param handle handle to compare to the value of the handle (RTI assigned) of
    * this class (the DepartureFlightConfirm interaction class).
    * @return "true" if "handle" matches the value of the handle of this class
    * (that is, the DepartureFlightConfirm interaction class).
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
        if (datamemberHandle == _actualLogicalGenerationTime_handle) return "actualLogicalGenerationTime";
        else if (datamemberHandle == _deptConfirm_handle) return "deptConfirm";
        else if (datamemberHandle == _federateFilter_handle) return "federateFilter";
        else if (datamemberHandle == _gateAvailable_handle) return "gateAvailable";
        else if (datamemberHandle == _gateNum_handle) return "gateNum";
        else if (datamemberHandle == _originFed_handle) return "originFed";
        else if (datamemberHandle == _sourceFed_handle) return "sourceFed";
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
                + "actualLogicalGenerationTime:" + get_actualLogicalGenerationTime()
                + "," + "deptConfirm:" + get_deptConfirm()
                + "," + "federateFilter:" + get_federateFilter()
                + "," + "gateAvailable:" + get_gateAvailable()
                + "," + "gateNum:" + get_gateNum()
                + "," + "originFed:" + get_originFed()
                + "," + "sourceFed:" + get_sourceFed()
                + ")";
    }

    private boolean _deptConfirm = false;
    private boolean _gateAvailable = false;
    private int _gateNum = 0;

    /**
    * Set the value of the "deptConfirm" parameter to "value" for this parameter.
    *
    * @param value the new value for the "deptConfirm" parameter
    */
    public void set_deptConfirm( boolean value ) {
        _deptConfirm = value;
    }

    /**
    * Returns the value of the "deptConfirm" parameter of this interaction.
    *
    * @return the value of the "deptConfirm" parameter
    */
    public boolean get_deptConfirm() {
        return _deptConfirm;
    }
    /**
    * Set the value of the "gateAvailable" parameter to "value" for this parameter.
    *
    * @param value the new value for the "gateAvailable" parameter
    */
    public void set_gateAvailable( boolean value ) {
        _gateAvailable = value;
    }

    /**
    * Returns the value of the "gateAvailable" parameter of this interaction.
    *
    * @return the value of the "gateAvailable" parameter
    */
    public boolean get_gateAvailable() {
        return _gateAvailable;
    }
    /**
    * Set the value of the "gateNum" parameter to "value" for this parameter.
    *
    * @param value the new value for the "gateNum" parameter
    */
    public void set_gateNum( int value ) {
        _gateNum = value;
    }

    /**
    * Returns the value of the "gateNum" parameter of this interaction.
    *
    * @return the value of the "gateNum" parameter
    */
    public int get_gateNum() {
        return _gateNum;
    }

    protected DepartureFlightConfirm( ReceivedInteraction datamemberMap, boolean initFlag ) {
        super( datamemberMap, false );
        if ( initFlag ) setParameters( datamemberMap );
    }

    protected DepartureFlightConfirm( ReceivedInteraction datamemberMap, LogicalTime logicalTime, boolean initFlag ) {
        super( datamemberMap, logicalTime, false );
        if ( initFlag ) setParameters( datamemberMap );
    }

    /**
    * Creates an instance of the DepartureFlightConfirm interaction class, using
    * "datamemberMap" to initialize its parameter values.
    * "datamemberMap" is usually acquired as an argument to an RTI federate
    * callback method, such as "receiveInteraction".
    *
    * @param datamemberMap data structure containing initial values for the
    * parameters of this new DepartureFlightConfirm interaction class instance
    */
    public DepartureFlightConfirm( ReceivedInteraction datamemberMap ) {
        this( datamemberMap, true );
    }

    /**
    * Like {@link #DepartureFlightConfirm( ReceivedInteraction datamemberMap )}, except this
    * new DepartureFlightConfirm interaction class instance is given a timestamp of
    * "logicalTime".
    *
    * @param datamemberMap data structure containing initial values for the
    * parameters of this new DepartureFlightConfirm interaction class instance
    * @param logicalTime timestamp for this new DepartureFlightConfirm interaction class
    * instance
    */
    public DepartureFlightConfirm( ReceivedInteraction datamemberMap, LogicalTime logicalTime ) {
        this( datamemberMap, logicalTime, true );
    }

    /**
    * Creates a new DepartureFlightConfirm interaction class instance that is a duplicate
    * of the instance referred to by DepartureFlightConfirm_var.
    *
    * @param DepartureFlightConfirm_var DepartureFlightConfirm interaction class instance of which
    * this newly created DepartureFlightConfirm interaction class instance will be a
    * duplicate
    */
    public DepartureFlightConfirm( DepartureFlightConfirm DepartureFlightConfirm_var ) {
        super( DepartureFlightConfirm_var );

        set_deptConfirm( DepartureFlightConfirm_var.get_deptConfirm() );
        set_gateAvailable( DepartureFlightConfirm_var.get_gateAvailable() );
        set_gateNum( DepartureFlightConfirm_var.get_gateNum() );
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
        if ( "deptConfirm".equals(datamemberName) ) return new Boolean(get_deptConfirm());
        else if ( "gateAvailable".equals(datamemberName) ) return new Boolean(get_gateAvailable());
        else if ( "gateNum".equals(datamemberName) ) return new Integer(get_gateNum());
        else return super.getParameter( datamemberName );
    }

    protected boolean setParameterAux( String datamemberName, String val ) {
        boolean retval = true;
        if ( "deptConfirm".equals( datamemberName) ) set_deptConfirm( Boolean.parseBoolean(val) );
        else if ( "gateAvailable".equals( datamemberName) ) set_gateAvailable( Boolean.parseBoolean(val) );
        else if ( "gateNum".equals( datamemberName) ) set_gateNum( Integer.parseInt(val) );
        else retval = super.setParameterAux( datamemberName, val );

        return retval;
    }

    protected boolean setParameterAux( String datamemberName, Object val ) {
        boolean retval = true;
        if ( "deptConfirm".equals( datamemberName) ) set_deptConfirm( (Boolean)val );
        else if ( "gateAvailable".equals( datamemberName) ) set_gateAvailable( (Boolean)val );
        else if ( "gateNum".equals( datamemberName) ) set_gateNum( (Integer)val );
        else retval = super.setParameterAux( datamemberName, val );

        return retval;
    }

    public void copyFrom( Object object ) {
        super.copyFrom( object );
        if ( object instanceof DepartureFlightConfirm ) {
            DepartureFlightConfirm data = (DepartureFlightConfirm)object;
            _deptConfirm = data._deptConfirm;
            _gateAvailable = data._gateAvailable;
            _gateNum = data._gateNum;
        }
    }
}

