/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jodao.etisalattextng.ussddaemon;

import com.jodao.etisalattextng.ussd.Padding;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author ayeola
 */
public class USSDDaemon extends SessionMonitor{

    /**
     * @param args the command line arguments
     */
    private static byte[] compressedData;
    //requests
    private ServerSocket serverSocket;
    private static final int PORT = 4400;
    private TelcoConnectionMonitor telcoMonitor;
    private boolean firstlogin = true;
    private Utilities utils;
    public HashMap<String, SessionController> sessionList;
    public HashMap<String, UWINIWINSessionController> uwiniwinsessionList;
    public Vector<SessionController> clientList;
    private int timeout = 1000 * 60 * 60 * 24;
    public static boolean keepRunning = false;
    //public HashMap<String,SessionController> ussdStr;
    //private static OctetStreamData username1 = "";
//    private static OctetString usernameString = new OctetString("cellul");
//    private static OctetString passwordString = new OctetString("cellul");
//    private static OctetString systemTypeString = new OctetString("0");
    private static USSDDaemon tcp;
    public static boolean isconnected = false;
    public static boolean enquiryLinkRequestReceived = false;
    public static boolean enquiryLinkResponseReceived = false;
    public static boolean ussdbindResponseReceived = false;
    public Padding padding;
    

    public USSDDaemon() {
        sessionList = new HashMap<String, SessionController>();
        uwiniwinsessionList = new HashMap<String, UWINIWINSessionController>();
        clientList = new Vector<SessionController>();
        utils = new Utilities();
        padding = new Padding();
    }

    public boolean isTelcoConnectionDown() {
        if (telcoMonitor == null) {
            System.out.println("telcoMonitor is running");
            return true;
        } else if (!keepRunning) {
            System.out.println("telcoMonitor is not running");
        }

        System.out.println("Connections are up and running");
        return false;
    }

    public boolean setupServer() {

        // Make sure we can be communicated with
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException ex) {
            System.out.println("ServerSocket Setup");
            //ex.printStackTrace();
            //logException(ex, "ServerSocket Setup");
            return false;
        }
        Thread serverThread = new Thread() {

            @Override
            public void run() {
                boolean keepRunning = true;
                while (keepRunning) {
                    Socket tmpSock;
                    try {
                        tmpSock = serverSocket.accept();
                        tmpSock.setKeepAlive(true);

                        //handleClient(tmpSock);
                        new Thread(new SessionMTHandler(tmpSock, USSDDaemon.this, telcoMonitor)).start();
                    } catch (IOException ex) {
                        keepRunning = false;
                        //logException(ex, "Server Socket Exception");
                    }
                }
            }
        };
        serverThread.start();
        return true;
    }

    public boolean UssdBind() {

        try {
            if(keepRunning == false){
                    Socket clientSocket = new Socket("10.71.161.79", 4400);
                   // Socket clientSocket = new Socket("localhost", 8888);
                    System.out.println("starting Telco Monitor...");                   
                    clientSocket.setKeepAlive(true);                   
                    isconnected = clientSocket.isConnected();
                    
                    System.out.println("Connection Status : " +isconnected);
                                      
                        System.out.println("Calling TelcoMonitor Class\n");
                        telcoMonitor = new TelcoConnectionMonitor(clientSocket, PORT, this);
                        System.out.println("Starting a New TelcoMonitor Thread \n");
                        keepRunning = true;
                        telcoMonitor.start();
                        System.out.println("TelcoMonitor Thread Started Successfully\n");
            }

        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            // System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Application not connected to the remote server retry after 5minutes");
            
                keepRunning = false;                
            try {
                Thread.sleep(300000);
                UssdBind();
            } catch (InterruptedException ex1) {
                ex1.printStackTrace();
            }
                
                   
        }

        return isconnected;

    }

    public  void writeHeaderToServer(TelcoConnectionMonitor monitor) {
        try {
            String handShake = "140000008300000000000000ffffffffffffffff";
            

            byte[] msgHeader = utils.hexStringToByteArray(handShake);

            monitor.getOutputSream().write(msgHeader);
        } catch (IOException ioEx) {
            ioEx.getMessage();
        } catch (BufferOverflowException bufferEx) {
            bufferEx.getMessage();

        } catch (BufferUnderflowException bufferEx1) {
            bufferEx1.getMessage();

        }

    }

    public  String writeMsgToServer(byte[] request, TelcoConnectionMonitor telcoMonitor) {

        try {
            telcoMonitor.outputSream.write(request);

            System.out.println("Size : " + request.length);

            telcoMonitor.outputSream.flush();

        } catch (Exception ioEx) {
            System.out.println("Broken Pipe Error : " + ioEx.getMessage());
            System.out.println("Restarting the Ussd Service Application");
                
                keepRunning = false;
                TelcoConnectionMonitor.interrupted();
                new USSDDaemon().UssdBind();

                System.out.println("Ussd Service Application Restarted Successfully");

        }

        return "";
    }



    protected int Generator() {
        Random generator = new Random();
        int min = 9999;
        int max = 999999999;
        int randomInteger = generator.nextInt(max - min) + min;

        return randomInteger;
    }    

    protected String getSessionID(byte[] request, TelcoConnectionMonitor telcoMonitor) {
        writeMsgToServer(request, telcoMonitor);
        return "";
    }

    protected void Shutdown() {
        try {
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec("shutdown");

        } catch (Exception ex) {
        }
    }

    @SuppressWarnings("static-access")
    public static void main(String argv[]) {

        InstanceManager manager = new InstanceManager();
        if (!manager.registerInstance()) {
            //instance already running
            System.out.println("Another instance of this application is running. Exiting.");
            System.exit(0);
        }
        manager.setInstanceListener(new InstanceListener() {

            public void newInstanceCreated() {
                System.out.println("New instance detected..");
            }
        });

        USSDDaemon tcpClient = new USSDDaemon();
        //tcpClient.UssdBind();
        if (tcpClient.setupServer()) {
            tcpClient.UssdBind();            
        } else {
            System.out.println("Could not connect to the USSD Center.\nExiting");
        }

       
    }
}

