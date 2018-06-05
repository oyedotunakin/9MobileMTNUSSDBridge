/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jodao.etisalattextng.ussd;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author akin
 */
public class KeepAliveMessenger extends Thread {
//    private final Socket clientSocket;

    public KeepAliveMessenger() {
//    this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("Starting new keep alive thread");
        while (ConnectClient.isconnected) {
            String handShake = "140000008300000000000000ffffffffffffffff";
            try {
                System.out.println("Sending keep alive message...");
                byte[] handShakeBytes = Utilities.hexStringToByteArray(handShake);
                ConnectClient.clientSocket.getOutputStream().write(handShakeBytes);
                ConnectClient.clientSocket.getOutputStream().flush();
                ConnectClient.keepAliveRunning = true;
            } catch (Exception ex1) {
                ex1.printStackTrace();
                ConnectClient.keepAliveRunning = false;
                Thread.currentThread().interrupt();
                return;
            }
            try {
                Thread.sleep(120000);
            } catch (InterruptedException ex) {
                ConnectClient.keepAliveRunning = false;
                ex.printStackTrace();
                Logger.getLogger(KeepAliveMessenger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
