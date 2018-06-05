/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jodao.etisalattextng.ussd;

/**
 *
 * @author akin
 */
public class Connect {

    public static void main(String[] args) throws Exception {
        //Start the server to listen on WCS responses.
        AppServer appServer = new AppServer();
        appServer.start();

//        Start the thread that connects and listens to etisalat.
        ConnectClient etConnectClient = ConnectClient.getInstance();
        etConnectClient.start();
    }
}
