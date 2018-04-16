/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotpackage;

import java.io.Serializable;

/**
 *
 * @author davi
 */
public class IoTAction implements Serializable {
    
    /*GLOBAL ACTIONS*/
    public static final int TURN_OFF = 0;
    public static final int TURN_ON = 1;
    public static final int TOGGLE = 2;
    public static final int REPORT = 3;
    public static final int SEND_DEVICE_INFO = 7;
    
    
    //AC ACTIONS
    public static final int SET_DESIRE_TEMPERATURE = 4;
    
    //Television Actions
    public static final int SET_CHANNEL = 5;
    public static final int SET_VOLUME = 6;
    
    //fields
    private int action;
    private int param;
    
    public IoTAction(int action){
        this.action = action;
    }
    
    public IoTAction(int action, int param){
        this.action = action;
        this.param = param;
    }

    public int getAction() {
        return action;
    }

    public int getParam() {
        return param;
    }
    
    
    
    
    
    
    
}
