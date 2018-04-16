/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotpackage;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author davi
 */
public class IoTDevice implements Switchable{
    
    public static final int AC = 0;
    public static final int Television = 1;
    public static final int Light = 2;
    
    protected boolean isDeviceOn;
    protected InetAddress address;
    protected int deviceType;
    protected String deviceName;
    protected String deviceDescription;
    protected OnDeviceStateChange deviceCallback;

    protected IoTDevice(int deviceType, String deviceName, String deviceDescription) {
        this.deviceType = deviceType;
        isDeviceOn = true;
        this.deviceName = deviceName;
        this.deviceDescription = deviceDescription;
        this.address = address;
    }
    
    

    @Override
    public void turnOn() {
        isDeviceOn = true;
        try{
            deviceCallback.deviceStateChanged(isDeviceOn);
        } catch(NullPointerException e){
            System.out.println("Device with no ");
        }
    }

    @Override
    public void turnOff() {
        isDeviceOn = false;
        
        try{
            deviceCallback.deviceStateChanged(isDeviceOn);
        } catch(NullPointerException e){
            System.out.println("Device with no ");
        }
    }

    @Override
    public boolean toggle() {
        isDeviceOn = !isDeviceOn;
        try{
            deviceCallback.deviceStateChanged(isDeviceOn);
        } catch(NullPointerException e){
            System.out.println("Device with no ");
        }
        return isDeviceOn;
    }

    @Override
    public boolean isDeviceOn() {
        return isDeviceOn;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceDescription() {
        return deviceDescription;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
    }

    public int getDeviceType() {
        return deviceType;
    }
    
    
    public void sendStatus(BufferedOutputStream out, byte[] data) throws IOException{
        out.write(data);
    }
    
    public Message report(){
        throw new UnsupportedOperationException("Please override this method before using it.");
    }
    
    /**
     *
     * @param action
     * @param out
     * @return
     */
    protected boolean performAction(IoTAction action, ObjectOutputStream out) throws IOException{
        
        switch(action.getAction()){
            case IoTAction.TURN_ON:
                turnOn();
                return true;
            case IoTAction.TURN_OFF:
                turnOff();
                return true;
            case IoTAction.TOGGLE:
                toggle();
                return true;
            case IoTAction.SEND_DEVICE_INFO:
                for(Message m : sendDeviceInfo()){
                    out.writeObject(m);
                    out.flush();
                }
                
                return true;
            default:
                System.err.println("Action not supported.");
                return false;
            }
        }

    public ArrayList<Message> sendDeviceInfo() {
            ArrayList<Message> info = new ArrayList();
            info.add(new Message("DEVICE_TYPE",deviceType + ""));
            info.add(new Message("DEVICE_NAME",deviceName));
            info.add(new Message("DEVICE_NAME",deviceDescription));
            return info;
        }
    }
