package org.webgme.guest.atc.rti;

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
* Implements InteractionRoot.C2WInteractionRoot.ArrivalConfirm
*/
public class ArrivalConfirm extends C2WInteractionRoot {

    private static final Logger logger = LogManager.getLogger();

    /**
    * Creates an instance of the ArrivalConfirm interaction class with default parameter values.
    */
    public ArrivalConfirm() {}

    private static int _actualLogicalGenerationTime_handle;
    private static int _arrivalConf_handle;
    private static int _arrivalTime_handle;
    private static int _federateFilter_handle;
    private static int _originFed_handle;
    private static int _realTimeSchedule_handle;
    private static int _runwayNum_handle;
    private static int _sourceFed_handle;

    private static boolean _isInitialized = false;

    private static int _handle;

    /**
    * Returns the handle (RTI assigned) of the ArrivalConfirm interaction class.
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
    * Returns the fully-qualified (dot-delimited) name of the ArrivalConfirm interaction class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return the name of the class pertaining to the reference,
    * rather than the name of the class for the instance referred to by the reference.
    * For the polymorphic version of this method, use {@link #getClassName()}.
    *
    * @return the fully-qualified HLA class path for this interaction class
    */
    public static String get_class_name() {
        return "InteractionRoot.C2WInteractionRoot.ArrivalConfirm";
    }

    /**
    * Returns the simple name (the last name in the dot-delimited fully-qualified
    * class name) of the ArrivalConfirm interaction class.
    *
    * @return the name of this interaction class
    */
    public static String get_simple_class_name() {
        return "ArrivalConfirm";
    }

    private static Set< String > _datamemberNames = new HashSet< String >();
    private static Set< String > _allDatamemberNames = new HashSet< String >();

    /**
    * Returns a set containing the names of all of the non-hidden parameters in the
    * ArrivalConfirm interaction class.
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
    * ArrivalConfirm interaction class.
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
        _classNameSet.add("InteractionRoot.C2WInteractionRoot.ArrivalConfirm");
        _classNameClassMap.put("InteractionRoot.C2WInteractionRoot.ArrivalConfirm", ArrivalConfirm.class);

        _datamemberClassNameSetMap.put("InteractionRoot.C2WInteractionRoot.ArrivalConfirm", _datamemberNames);
        _allDatamemberClassNameSetMap.put("InteractionRoot.C2WInteractionRoot.ArrivalConfirm", _allDatamemberNames);

        _datamemberNames.add("arrivalConf");
        _datamemberNames.add("arrivalTime");
        _datamemberNames.add("realTimeSchedule");
        _datamemberNames.add("runwayNum");

        _datamemberTypeMap.put("arrivalConf", "boolean");
        _datamemberTypeMap.put("arrivalTime", "String");
        _datamemberTypeMap.put("realTimeSchedule", "String");
        _datamemberTypeMap.put("runwayNum", "int");

        _allDatamemberNames.add("actualLogicalGenerationTime");
        _allDatamemberNames.add("arrivalConf");
        _allDatamemberNames.add("arrivalTime");
        _allDatamemberNames.add("federateFilter");
        _allDatamemberNames.add("originFed");
        _allDatamemberNames.add("realTimeSchedule");
        _allDatamemberNames.add("runwayNum");
        _allDatamemberNames.add("sourceFed");
    }

    protected static void init(RTIambassador rti) {
        if (_isInitialized) return;
        _isInitialized = true;

        C2WInteractionRoot.init(rti);

        boolean isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _handle = rti.getInteractionClassHandle("InteractionRoot.C2WInteractionRoot.ArrivalConfirm");
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

        _classNameHandleMap.put("InteractionRoot.C2WInteractionRoot.ArrivalConfirm", get_handle());
        _classHandleNameMap.put(get_handle(), "InteractionRoot.C2WInteractionRoot.ArrivalConfirm");
        _classHandleSimpleNameMap.put(get_handle(), "ArrivalConfirm");

        isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _actualLogicalGenerationTime_handle = rti.getParameterHandle("actualLogicalGenerationTime", get_handle());
                _arrivalConf_handle = rti.getParameterHandle("arrivalConf", get_handle());
                _arrivalTime_handle = rti.getParameterHandle("arrivalTime", get_handle());
                _federateFilter_handle = rti.getParameterHandle("federateFilter", get_handle());
                _originFed_handle = rti.getParameterHandle("originFed", get_handle());
                _realTimeSchedule_handle = rti.getParameterHandle("realTimeSchedule", get_handle());
                _runwayNum_handle = rti.getParameterHandle("runwayNum", get_handle());
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

        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.ArrivalConfirm.actualLogicalGenerationTime", _actualLogicalGenerationTime_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.ArrivalConfirm.arrivalConf", _arrivalConf_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.ArrivalConfirm.arrivalTime", _arrivalTime_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.ArrivalConfirm.federateFilter", _federateFilter_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.ArrivalConfirm.originFed", _originFed_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.ArrivalConfirm.realTimeSchedule", _realTimeSchedule_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.ArrivalConfirm.runwayNum", _runwayNum_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.ArrivalConfirm.sourceFed", _sourceFed_handle);

        _datamemberHandleNameMap.put(_actualLogicalGenerationTime_handle, "actualLogicalGenerationTime");
        _datamemberHandleNameMap.put(_arrivalConf_handle, "arrivalConf");
        _datamemberHandleNameMap.put(_arrivalTime_handle, "arrivalTime");
        _datamemberHandleNameMap.put(_federateFilter_handle, "federateFilter");
        _datamemberHandleNameMap.put(_originFed_handle, "originFed");
        _datamemberHandleNameMap.put(_realTimeSchedule_handle, "realTimeSchedule");
        _datamemberHandleNameMap.put(_runwayNum_handle, "runwayNum");
        _datamemberHandleNameMap.put(_sourceFed_handle, "sourceFed");
    }

    private static boolean _isPublished = false;

    /**
    * Publishes the ArrivalConfirm interaction class for a federate.
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
    * Unpublishes the ArrivalConfirm interaction class for a federate.
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
    * Subscribes a federate to the ArrivalConfirm interaction class.
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
    * Unsubscribes a federate from the ArrivalConfirm interaction class.
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
    * (that is, the ArrivalConfirm interaction class).
    *
    * @param handle handle to compare to the value of the handle (RTI assigned) of
    * this class (the ArrivalConfirm interaction class).
    * @return "true" if "handle" matches the value of the handle of this class
    * (that is, the ArrivalConfirm interaction class).
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
        else if (datamemberHandle == _arrivalConf_handle) return "arrivalConf";
        else if (datamemberHandle == _arrivalTime_handle) return "arrivalTime";
        else if (datamemberHandle == _federateFilter_handle) return "federateFilter";
        else if (datamemberHandle == _originFed_handle) return "originFed";
        else if (datamemberHandle == _realTimeSchedule_handle) return "realTimeSchedule";
        else if (datamemberHandle == _runwayNum_handle) return "runwayNum";
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
                + "," + "arrivalConf:" + get_arrivalConf()
                + "," + "arrivalTime:" + get_arrivalTime()
                + "," + "federateFilter:" + get_federateFilter()
                + "," + "originFed:" + get_originFed()
                + "," + "realTimeSchedule:" + get_realTimeSchedule()
                + "," + "runwayNum:" + get_runwayNum()
                + "," + "sourceFed:" + get_sourceFed()
                + ")";
    }

    private boolean _arrivalConf = false;
    private String _arrivalTime = "";
    private String _realTimeSchedule = "";
    private int _runwayNum = 0;

    /**
    * Set the value of the "arrivalConf" parameter to "value" for this parameter.
    *
    * @param value the new value for the "arrivalConf" parameter
    */
    public void set_arrivalConf( boolean value ) {
        _arrivalConf = value;
    }

    /**
    * Returns the value of the "arrivalConf" parameter of this interaction.
    *
    * @return the value of the "arrivalConf" parameter
    */
    public boolean get_arrivalConf() {
        return _arrivalConf;
    }
    /**
    * Set the value of the "arrivalTime" parameter to "value" for this parameter.
    *
    * @param value the new value for the "arrivalTime" parameter
    */
    public void set_arrivalTime( String value ) {
        _arrivalTime = value;
    }

    /**
    * Returns the value of the "arrivalTime" parameter of this interaction.
    *
    * @return the value of the "arrivalTime" parameter
    */
    public String get_arrivalTime() {
        return _arrivalTime;
    }
    /**
    * Set the value of the "realTimeSchedule" parameter to "value" for this parameter.
    *
    * @param value the new value for the "realTimeSchedule" parameter
    */
    public void set_realTimeSchedule( String value ) {
        _realTimeSchedule = value;
    }

    /**
    * Returns the value of the "realTimeSchedule" parameter of this interaction.
    *
    * @return the value of the "realTimeSchedule" parameter
    */
    public String get_realTimeSchedule() {
        return _realTimeSchedule;
    }
    /**
    * Set the value of the "runwayNum" parameter to "value" for this parameter.
    *
    * @param value the new value for the "runwayNum" parameter
    */
    public void set_runwayNum( int value ) {
        _runwayNum = value;
    }

    /**
    * Returns the value of the "runwayNum" parameter of this interaction.
    *
    * @return the value of the "runwayNum" parameter
    */
    public int get_runwayNum() {
        return _runwayNum;
    }

    protected ArrivalConfirm( ReceivedInteraction datamemberMap, boolean initFlag ) {
        super( datamemberMap, false );
        if ( initFlag ) setParameters( datamemberMap );
    }

    protected ArrivalConfirm( ReceivedInteraction datamemberMap, LogicalTime logicalTime, boolean initFlag ) {
        super( datamemberMap, logicalTime, false );
        if ( initFlag ) setParameters( datamemberMap );
    }

    /**
    * Creates an instance of the ArrivalConfirm interaction class, using
    * "datamemberMap" to initialize its parameter values.
    * "datamemberMap" is usually acquired as an argument to an RTI federate
    * callback method, such as "receiveInteraction".
    *
    * @param datamemberMap data structure containing initial values for the
    * parameters of this new ArrivalConfirm interaction class instance
    */
    public ArrivalConfirm( ReceivedInteraction datamemberMap ) {
        this( datamemberMap, true );
    }

    /**
    * Like {@link #ArrivalConfirm( ReceivedInteraction datamemberMap )}, except this
    * new ArrivalConfirm interaction class instance is given a timestamp of
    * "logicalTime".
    *
    * @param datamemberMap data structure containing initial values for the
    * parameters of this new ArrivalConfirm interaction class instance
    * @param logicalTime timestamp for this new ArrivalConfirm interaction class
    * instance
    */
    public ArrivalConfirm( ReceivedInteraction datamemberMap, LogicalTime logicalTime ) {
        this( datamemberMap, logicalTime, true );
    }

    /**
    * Creates a new ArrivalConfirm interaction class instance that is a duplicate
    * of the instance referred to by ArrivalConfirm_var.
    *
    * @param ArrivalConfirm_var ArrivalConfirm interaction class instance of which
    * this newly created ArrivalConfirm interaction class instance will be a
    * duplicate
    */
    public ArrivalConfirm( ArrivalConfirm ArrivalConfirm_var ) {
        super( ArrivalConfirm_var );

        set_arrivalConf( ArrivalConfirm_var.get_arrivalConf() );
        set_arrivalTime( ArrivalConfirm_var.get_arrivalTime() );
        set_realTimeSchedule( ArrivalConfirm_var.get_realTimeSchedule() );
        set_runwayNum( ArrivalConfirm_var.get_runwayNum() );
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
        if ( "arrivalConf".equals(datamemberName) ) return new Boolean(get_arrivalConf());
        else if ( "arrivalTime".equals(datamemberName) ) return get_arrivalTime();
        else if ( "realTimeSchedule".equals(datamemberName) ) return get_realTimeSchedule();
        else if ( "runwayNum".equals(datamemberName) ) return new Integer(get_runwayNum());
        else return super.getParameter( datamemberName );
    }

    protected boolean setParameterAux( String datamemberName, String val ) {
        boolean retval = true;
        if ( "arrivalConf".equals( datamemberName) ) set_arrivalConf( Boolean.parseBoolean(val) );
        else if ( "arrivalTime".equals( datamemberName) ) set_arrivalTime( val );
        else if ( "realTimeSchedule".equals( datamemberName) ) set_realTimeSchedule( val );
        else if ( "runwayNum".equals( datamemberName) ) set_runwayNum( Integer.parseInt(val) );
        else retval = super.setParameterAux( datamemberName, val );

        return retval;
    }

    protected boolean setParameterAux( String datamemberName, Object val ) {
        boolean retval = true;
        if ( "arrivalConf".equals( datamemberName) ) set_arrivalConf( (Boolean)val );
        else if ( "arrivalTime".equals( datamemberName) ) set_arrivalTime( (String)val );
        else if ( "realTimeSchedule".equals( datamemberName) ) set_realTimeSchedule( (String)val );
        else if ( "runwayNum".equals( datamemberName) ) set_runwayNum( (Integer)val );
        else retval = super.setParameterAux( datamemberName, val );

        return retval;
    }

    public void copyFrom( Object object ) {
        super.copyFrom( object );
        if ( object instanceof ArrivalConfirm ) {
            ArrivalConfirm data = (ArrivalConfirm)object;
            _arrivalConf = data._arrivalConf;
            _arrivalTime = data._arrivalTime;
            _realTimeSchedule = data._realTimeSchedule;
            _runwayNum = data._runwayNum;
        }
    }
}
