/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotpackage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;

/**
 *
 * @author davi
 */
public class LightDevice extends IoTDevice{
 
    
    public LightDevice(String deviceName, String deviceDescription) {
        super(IoTDevice.Light, deviceName, deviceDescription);
    }

    public void setCallback(OnDeviceStateChange lightCallback) {
        this.deviceCallback = lightCallback;
    }
    @Override
    public boolean performAction(IoTAction action, ObjectOutputStream out) throws IOException{
        return super.performAction(action, out);
    }
    
    @Override
    public Message report(){
        String resp = new String("Light name: " + deviceName + " " + deviceDescription);
        return new Message("REPORT from: ", resp);
        
    }
    
}

