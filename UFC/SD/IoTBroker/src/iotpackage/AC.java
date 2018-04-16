/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotpackage;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Observer;

/**
 *
 * @author davi
 */
public class AC extends IoTDevice{

    private int desiredTemp; //The desired temperature
    OnACValueChange ACcallback;

    public AC(String deviceName, String deviceDescription) {
        super(IoTDevice.AC ,deviceName, deviceDescription);
        desiredTemp = 22;
    }  

    
    public int getDesiredTemp() {
        return desiredTemp;
    }

    public void setDesiredTemp(int desiredTemp) {
        this.desiredTemp = desiredTemp;
        ACcallback.temperatureChanged(desiredTemp);
    }
    
    @Override
    public boolean performAction(IoTAction action, ObjectOutputStream out ) throws IOException {
        int actionCode = action.getAction();
        int params = action.getParam();
        switch(actionCode){
            case IoTAction.SET_DESIRE_TEMPERATURE:{
                setDesiredTemp(params);
                out.writeObject(new Message("SET_DESIRE_TEMPERATURE","Set to " + params));
                return true;
            }
            case IoTAction.REPORT:{
                out.writeObject(report());
                return true;
            }
            default:{
                return super.performAction(action, out);
            }
        }
    }
   
                
    @Override
    public Message report(){
        String resp =  new String("Device type: AC" + " "
                + "Device Name: " + deviceName + " "
                + "Desired Temperature is " + desiredTemp + " degrees.");
        return new Message("REPORT from ",resp);
    }

    public void setCallback(OnACValueChange callback) {
        this.ACcallback = callback;
        super.deviceCallback = callback;
    }
    

}
