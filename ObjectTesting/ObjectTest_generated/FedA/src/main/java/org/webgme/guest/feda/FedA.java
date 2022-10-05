package org.webgme.guest.feda;

import org.webgme.guest.feda.rti.*;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.base.AdvanceTimeRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.portico.*;



// Define the FedA type of federate for the federation.

public class FedA extends FedABase {
    private final static Logger log = LogManager.getLogger();

    private double currentTime = 0;
    int dataA = -99;

    
    ////////////////////////////////////////////////////////////////////////
    // TODO instantiate objects that must be sent every logical time step //
    ////////////////////////////////////////////////////////////////////////
    Metrics metrics =
        new Metrics();

    public FedA(FederateConfig params) throws Exception {
        super(params);

        //////////////////////////////////////////////////////
        // TODO register object instances after super(args) //
        //////////////////////////////////////////////////////
        metrics.registerObject(getLRC());
    }

    private void execute() throws Exception {
        if(super.isLateJoiner()) {
            log.info("turning off time regulation (late joiner)");
            currentTime = super.getLBTS() - super.getLookAhead();
            super.disableTimeRegulation();
        }

        /////////////////////////////////////////////
        // TODO perform basic initialization below //
        /////////////////////////////////////////////

        AdvanceTimeRequest atr = new AdvanceTimeRequest(currentTime);
        putAdvanceTimeRequest(atr);

        if(!super.isLateJoiner()) {
            log.info("waiting on readyToPopulate...");
            readyToPopulate();
            log.info("...synchronized on readyToPopulate");
        }

        ///////////////////////////////////////////////////////////////////////
        // TODO perform initialization that depends on other federates below //
        ///////////////////////////////////////////////////////////////////////

        if(!super.isLateJoiner()) {
            log.info("waiting on readyToRun...");
            readyToRun();
            log.info("...synchronized on readyToRun");
        }

        startAdvanceTimeThread();
        log.info("started logical time progression");

        while (!exitCondition) {
            atr.requestSyncStart();
            enteredTimeGrantedState();

            dataA = (int)currentTime + 100;

            ////////////////////////////////////////////////////////////
            // TODO objects that must be sent every logical time step //
            ////////////////////////////////////////////////////////////

            metrics.set_DataA((int)currentTime);
  
            metrics.updateAttributeValues(getLRC(), currentTime + getLookAhead());

            if ((int)currentTime % 2 == 0){

               metrics.set_DataA(dataA);
               metrics.set_DataB(dataA);
               metrics.updateAttributeValues(getLRC(), currentTime + getLookAhead());

               System.out.println("Current logic time = "+ currentTime);
               System.out.println("Update DataA value as " + dataA);
               System.out.println("Update DataB value as " + dataA);
            }   


            ////////////////////////////////////////////////////////////////////
            // TODO break here if ready to resign and break out of while loop //
            ////////////////////////////////////////////////////////////////////

            if (!exitCondition) {
                currentTime += super.getStepSize();
                AdvanceTimeRequest newATR =
                    new AdvanceTimeRequest(currentTime);
                putAdvanceTimeRequest(newATR);
                atr.requestSyncEnd();
                atr = newATR;
            }
        }

        // call exitGracefully to shut down federate
        exitGracefully();

        //////////////////////////////////////////////////////////////////////
        // TODO Perform whatever cleanups are needed before exiting the app //
        //////////////////////////////////////////////////////////////////////
    }

    public static void main(String[] args) {
        try {
            FederateConfigParser federateConfigParser =
                new FederateConfigParser();
            FederateConfig federateConfig =
                federateConfigParser.parseArgs(args, FederateConfig.class);
            FedA federate =
                new FedA(federateConfig);
            federate.execute();
            log.info("Done.");
            System.exit(0);
        }
        catch (Exception e) {
            log.error(e);
            System.exit(1);
        }
    }
}
