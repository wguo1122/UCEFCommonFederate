package org.webgme.guest.airlinegateman2.rti;

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
* Implements InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo
*/
public class DayaheadFlightInfo extends C2WInteractionRoot {

    private static final Logger logger = LogManager.getLogger();

    /**
    * Creates an instance of the DayaheadFlightInfo interaction class with default parameter values.
    */
    public DayaheadFlightInfo() {}

    private static int _actualLogicalGenerationTime_handle;
    private static int _depTime_handle;
    private static int _destination_handle;
    private static int _federateFilter_handle;
    private static int _flightNumber_handle;
    private static int _fuelLevel_handle;
    private static int _modelNum_handle;
    private static int _originFed_handle;
    private static int _passengerNum_handle;
    private static int _sourceFed_handle;

    private static boolean _isInitialized = false;

    private static int _handle;

    /**
    * Returns the handle (RTI assigned) of the DayaheadFlightInfo interaction class.
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
    * Returns the fully-qualified (dot-delimited) name of the DayaheadFlightInfo interaction class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return the name of the class pertaining to the reference,
    * rather than the name of the class for the instance referred to by the reference.
    * For the polymorphic version of this method, use {@link #getClassName()}.
    *
    * @return the fully-qualified HLA class path for this interaction class
    */
    public static String get_class_name() {
        return "InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo";
    }

    /**
    * Returns the simple name (the last name in the dot-delimited fully-qualified
    * class name) of the DayaheadFlightInfo interaction class.
    *
    * @return the name of this interaction class
    */
    public static String get_simple_class_name() {
        return "DayaheadFlightInfo";
    }

    private static Set< String > _datamemberNames = new HashSet< String >();
    private static Set< String > _allDatamemberNames = new HashSet< String >();

    /**
    * Returns a set containing the names of all of the non-hidden parameters in the
    * DayaheadFlightInfo interaction class.
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
    * DayaheadFlightInfo interaction class.
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
        _classNameSet.add("InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo");
        _classNameClassMap.put("InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo", DayaheadFlightInfo.class);

        _datamemberClassNameSetMap.put("InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo", _datamemberNames);
        _allDatamemberClassNameSetMap.put("InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo", _allDatamemberNames);

        _datamemberNames.add("depTime");
        _datamemberNames.add("destination");
        _datamemberNames.add("flightNumber");
        _datamemberNames.add("fuelLevel");
        _datamemberNames.add("modelNum");
        _datamemberNames.add("passengerNum");

        _datamemberTypeMap.put("depTime", "String");
        _datamemberTypeMap.put("destination", "String");
        _datamemberTypeMap.put("flightNumber", "String");
        _datamemberTypeMap.put("fuelLevel", "double");
        _datamemberTypeMap.put("modelNum", "String");
        _datamemberTypeMap.put("passengerNum", "int");

        _allDatamemberNames.add("actualLogicalGenerationTime");
        _allDatamemberNames.add("depTime");
        _allDatamemberNames.add("destination");
        _allDatamemberNames.add("federateFilter");
        _allDatamemberNames.add("flightNumber");
        _allDatamemberNames.add("fuelLevel");
        _allDatamemberNames.add("modelNum");
        _allDatamemberNames.add("originFed");
        _allDatamemberNames.add("passengerNum");
        _allDatamemberNames.add("sourceFed");
    }

    protected static void init(RTIambassador rti) {
        if (_isInitialized) return;
        _isInitialized = true;

        C2WInteractionRoot.init(rti);

        boolean isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _handle = rti.getInteractionClassHandle("InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo");
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

        _classNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo", get_handle());
        _classHandleNameMap.put(get_handle(), "InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo");
        _classHandleSimpleNameMap.put(get_handle(), "DayaheadFlightInfo");

        isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _actualLogicalGenerationTime_handle = rti.getParameterHandle("actualLogicalGenerationTime", get_handle());
                _depTime_handle = rti.getParameterHandle("depTime", get_handle());
                _destination_handle = rti.getParameterHandle("destination", get_handle());
                _federateFilter_handle = rti.getParameterHandle("federateFilter", get_handle());
                _flightNumber_handle = rti.getParameterHandle("flightNumber", get_handle());
                _fuelLevel_handle = rti.getParameterHandle("fuelLevel", get_handle());
                _modelNum_handle = rti.getParameterHandle("modelNum", get_handle());
                _originFed_handle = rti.getParameterHandle("originFed", get_handle());
                _passengerNum_handle = rti.getParameterHandle("passengerNum", get_handle());
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

        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo.actualLogicalGenerationTime", _actualLogicalGenerationTime_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo.depTime", _depTime_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo.destination", _destination_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo.federateFilter", _federateFilter_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo.flightNumber", _flightNumber_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo.fuelLevel", _fuelLevel_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo.modelNum", _modelNum_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo.originFed", _originFed_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo.passengerNum", _passengerNum_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.DayaheadFlightInfo.sourceFed", _sourceFed_handle);

        _datamemberHandleNameMap.put(_actualLogicalGenerationTime_handle, "actualLogicalGenerationTime");
        _datamemberHandleNameMap.put(_depTime_handle, "depTime");
        _datamemberHandleNameMap.put(_destination_handle, "destination");
        _datamemberHandleNameMap.put(_federateFilter_handle, "federateFilter");
        _datamemberHandleNameMap.put(_flightNumber_handle, "flightNumber");
        _datamemberHandleNameMap.put(_fuelLevel_handle, "fuelLevel");
        _datamemberHandleNameMap.put(_modelNum_handle, "modelNum");
        _datamemberHandleNameMap.put(_originFed_handle, "originFed");
        _datamemberHandleNameMap.put(_passengerNum_handle, "passengerNum");
        _datamemberHandleNameMap.put(_sourceFed_handle, "sourceFed");
    }

    private static boolean _isPublished = false;

    /**
    * Publishes the DayaheadFlightInfo interaction class for a federate.
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
    * Unpublishes the DayaheadFlightInfo interaction class for a federate.
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
    * Subscribes a federate to the DayaheadFlightInfo interaction class.
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
    * Unsubscribes a federate from the DayaheadFlightInfo interaction class.
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
    * (that is, the DayaheadFlightInfo interaction class).
    *
    * @param handle handle to compare to the value of the handle (RTI assigned) of
    * this class (the DayaheadFlightInfo interaction class).
    * @return "true" if "handle" matches the value of the handle of this class
    * (that is, the DayaheadFlightInfo interaction class).
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
        else if (datamemberHandle == _depTime_handle) return "depTime";
        else if (datamemberHandle == _destination_handle) return "destination";
        else if (datamemberHandle == _federateFilter_handle) return "federateFilter";
        else if (datamemberHandle == _flightNumber_handle) return "flightNumber";
        else if (datamemberHandle == _fuelLevel_handle) return "fuelLevel";
        else if (datamemberHandle == _modelNum_handle) return "modelNum";
        else if (datamemberHandle == _originFed_handle) return "originFed";
        else if (datamemberHandle == _passengerNum_handle) return "passengerNum";
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
                + "," + "depTime:" + get_depTime()
                + "," + "destination:" + get_destination()
                + "," + "federateFilter:" + get_federateFilter()
                + "," + "flightNumber:" + get_flightNumber()
                + "," + "fuelLevel:" + get_fuelLevel()
                + "," + "modelNum:" + get_modelNum()
                + "," + "originFed:" + get_originFed()
                + "," + "passengerNum:" + get_passengerNum()
                + "," + "sourceFed:" + get_sourceFed()
                + ")";
    }

    private String _depTime = "";
    private String _destination = "";
    private String _flightNumber = "";
    private double _fuelLevel = 0;
    private String _modelNum = "";
    private int _passengerNum = 0;

    /**
    * Set the value of the "depTime" parameter to "value" for this parameter.
    *
    * @param value the new value for the "depTime" parameter
    */
    public void set_depTime( String value ) {
        _depTime = value;
    }

    /**
    * Returns the value of the "depTime" parameter of this interaction.
    *
    * @return the value of the "depTime" parameter
    */
    public String get_depTime() {
        return _depTime;
    }
    /**
    * Set the value of the "destination" parameter to "value" for this parameter.
    *
    * @param value the new value for the "destination" parameter
    */
    public void set_destination( String value ) {
        _destination = value;
    }

    /**
    * Returns the value of the "destination" parameter of this interaction.
    *
    * @return the value of the "destination" parameter
    */
    public String get_destination() {
        return _destination;
    }
    /**
    * Set the value of the "flightNumber" parameter to "value" for this parameter.
    *
    * @param value the new value for the "flightNumber" parameter
    */
    public void set_flightNumber( String value ) {
        _flightNumber = value;
    }

    /**
    * Returns the value of the "flightNumber" parameter of this interaction.
    *
    * @return the value of the "flightNumber" parameter
    */
    public String get_flightNumber() {
        return _flightNumber;
    }
    /**
    * Set the value of the "fuelLevel" parameter to "value" for this parameter.
    *
    * @param value the new value for the "fuelLevel" parameter
    */
    public void set_fuelLevel( double value ) {
        _fuelLevel = value;
    }

    /**
    * Returns the value of the "fuelLevel" parameter of this interaction.
    *
    * @return the value of the "fuelLevel" parameter
    */
    public double get_fuelLevel() {
        return _fuelLevel;
    }
    /**
    * Set the value of the "modelNum" parameter to "value" for this parameter.
    *
    * @param value the new value for the "modelNum" parameter
    */
    public void set_modelNum( String value ) {
        _modelNum = value;
    }

    /**
    * Returns the value of the "modelNum" parameter of this interaction.
    *
    * @return the value of the "modelNum" parameter
    */
    public String get_modelNum() {
        return _modelNum;
    }
    /**
    * Set the value of the "passengerNum" parameter to "value" for this parameter.
    *
    * @param value the new value for the "passengerNum" parameter
    */
    public void set_passengerNum( int value ) {
        _passengerNum = value;
    }

    /**
    * Returns the value of the "passengerNum" parameter of this interaction.
    *
    * @return the value of the "passengerNum" parameter
    */
    public int get_passengerNum() {
        return _passengerNum;
    }

    protected DayaheadFlightInfo( ReceivedInteraction datamemberMap, boolean initFlag ) {
        super( datamemberMap, false );
        if ( initFlag ) setParameters( datamemberMap );
    }

    protected DayaheadFlightInfo( ReceivedInteraction datamemberMap, LogicalTime logicalTime, boolean initFlag ) {
        super( datamemberMap, logicalTime, false );
        if ( initFlag ) setParameters( datamemberMap );
    }

    /**
    * Creates an instance of the DayaheadFlightInfo interaction class, using
    * "datamemberMap" to initialize its parameter values.
    * "datamemberMap" is usually acquired as an argument to an RTI federate
    * callback method, such as "receiveInteraction".
    *
    * @param datamemberMap data structure containing initial values for the
    * parameters of this new DayaheadFlightInfo interaction class instance
    */
    public DayaheadFlightInfo( ReceivedInteraction datamemberMap ) {
        this( datamemberMap, true );
    }

    /**
    * Like {@link #DayaheadFlightInfo( ReceivedInteraction datamemberMap )}, except this
    * new DayaheadFlightInfo interaction class instance is given a timestamp of
    * "logicalTime".
    *
    * @param datamemberMap data structure containing initial values for the
    * parameters of this new DayaheadFlightInfo interaction class instance
    * @param logicalTime timestamp for this new DayaheadFlightInfo interaction class
    * instance
    */
    public DayaheadFlightInfo( ReceivedInteraction datamemberMap, LogicalTime logicalTime ) {
        this( datamemberMap, logicalTime, true );
    }

    /**
    * Creates a new DayaheadFlightInfo interaction class instance that is a duplicate
    * of the instance referred to by DayaheadFlightInfo_var.
    *
    * @param DayaheadFlightInfo_var DayaheadFlightInfo interaction class instance of which
    * this newly created DayaheadFlightInfo interaction class instance will be a
    * duplicate
    */
    public DayaheadFlightInfo( DayaheadFlightInfo DayaheadFlightInfo_var ) {
        super( DayaheadFlightInfo_var );

        set_depTime( DayaheadFlightInfo_var.get_depTime() );
        set_destination( DayaheadFlightInfo_var.get_destination() );
        set_flightNumber( DayaheadFlightInfo_var.get_flightNumber() );
        set_fuelLevel( DayaheadFlightInfo_var.get_fuelLevel() );
        set_modelNum( DayaheadFlightInfo_var.get_modelNum() );
        set_passengerNum( DayaheadFlightInfo_var.get_passengerNum() );
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
        if ( "depTime".equals(datamemberName) ) return get_depTime();
        else if ( "destination".equals(datamemberName) ) return get_destination();
        else if ( "flightNumber".equals(datamemberName) ) return get_flightNumber();
        else if ( "fuelLevel".equals(datamemberName) ) return new Double(get_fuelLevel());
        else if ( "modelNum".equals(datamemberName) ) return get_modelNum();
        else if ( "passengerNum".equals(datamemberName) ) return new Integer(get_passengerNum());
        else return super.getParameter( datamemberName );
    }

    protected boolean setParameterAux( String datamemberName, String val ) {
        boolean retval = true;
        if ( "depTime".equals( datamemberName) ) set_depTime( val );
        else if ( "destination".equals( datamemberName) ) set_destination( val );
        else if ( "flightNumber".equals( datamemberName) ) set_flightNumber( val );
        else if ( "fuelLevel".equals( datamemberName) ) set_fuelLevel( Double.parseDouble(val) );
        else if ( "modelNum".equals( datamemberName) ) set_modelNum( val );
        else if ( "passengerNum".equals( datamemberName) ) set_passengerNum( Integer.parseInt(val) );
        else retval = super.setParameterAux( datamemberName, val );

        return retval;
    }

    protected boolean setParameterAux( String datamemberName, Object val ) {
        boolean retval = true;
        if ( "depTime".equals( datamemberName) ) set_depTime( (String)val );
        else if ( "destination".equals( datamemberName) ) set_destination( (String)val );
        else if ( "flightNumber".equals( datamemberName) ) set_flightNumber( (String)val );
        else if ( "fuelLevel".equals( datamemberName) ) set_fuelLevel( (Double)val );
        else if ( "modelNum".equals( datamemberName) ) set_modelNum( (String)val );
        else if ( "passengerNum".equals( datamemberName) ) set_passengerNum( (Integer)val );
        else retval = super.setParameterAux( datamemberName, val );

        return retval;
    }

    public void copyFrom( Object object ) {
        super.copyFrom( object );
        if ( object instanceof DayaheadFlightInfo ) {
            DayaheadFlightInfo data = (DayaheadFlightInfo)object;
            _depTime = data._depTime;
            _destination = data._destination;
            _flightNumber = data._flightNumber;
            _fuelLevel = data._fuelLevel;
            _modelNum = data._modelNum;
            _passengerNum = data._passengerNum;
        }
    }
}

