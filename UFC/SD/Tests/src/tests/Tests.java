/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;

/**
 *
 * @author davi
 */
public class Tests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        MulticastSocket discoverSocket = new MulticastSocket();
        InetAddress group = InetAddress.getByName("239.2.3.9");
        discoverSocket.joinGroup(group);
        discoverSocket.setTimeToLive(16);
        discoverSocket.setSoTimeout(10000);
        byte[] buffer = new byte[2000];
        String s = "HADOUKEN";
        buffer = s.getBytes();
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length, group, 50135);
        discoverSocket.send(dp);

        for (int i = 0; i < 10; i++) {
            buffer = new byte[2000];
            dp = new DatagramPacket(buffer, buffer.length);
            try {
                discoverSocket.receive(dp);
                buffer = dp.getData();
                String received = new String(buffer);
                System.out.println(received);
            } catch (SocketTimeoutException e) {
                System.out.println("Socket timed out");
            }

        }

    }
}
