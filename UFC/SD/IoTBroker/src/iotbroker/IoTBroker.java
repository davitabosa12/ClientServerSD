/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotbroker;

import iotpackage.AC;
import iotpackage.IoTAction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author davi
 */
public class IoTBroker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO code application logic here
        Socket acSocket = new Socket("localhost", 9001);
        
        ObjectInputStream inFromAC = new ObjectInputStream(  acSocket.getInputStream() );
        ObjectOutputStream outToAC = new ObjectOutputStream( acSocket.getOutputStream());
        
        IoTAction theAction = new IoTAction(IoTAction.SET_DESIRE_TEMPERATURE, 180);
        outToAC.writeObject(theAction);
        String response = (String) inFromAC.readObject();
        System.out.println(response);
        
        
    }

    
}
