/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotpackage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author davi
 */
public class Television extends IoTDevice{
    
    private int channel;
    private int volume;
    private OnTVValueChange tvCallback;

    public void setCallback(OnTVValueChange tvCallback) {
        this.tvCallback = tvCallback;   
        super.deviceCallback = tvCallback; //sabia que dava pra fazer isso nao rs
    }

    public Television(String deviceName, String deviceDescription) {
        super(IoTDevice.Television, deviceName, deviceDescription);
        channel = 3;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        
        this.channel = channel;
        tvCallback.channelChanged(channel);
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        tvCallback.volumeChanged(volume);
        this.volume = volume;
    }   
    
    @Override
    public boolean performAction(IoTAction action, ObjectOutputStream out) throws IOException{
        int actionCode = action.getAction();
        int param = action.getParam();
        
        switch(actionCode){
            case IoTAction.SET_CHANNEL:
                setChannel(param);
                out.writeObject(new Message("SET_CHANNEL","Channel is set to " + channel));
                return true;
            case IoTAction.SET_VOLUME:
                setVolume(param);
                out.writeObject(new Message("SET_VOLUME","Volume is set to " + volume));
                return true;
            default:
                return super.performAction(action, out);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Message report(){
        return new Message("REPORT from: ", "TV name: " + deviceName +  " " + deviceDescription);
    }
}
