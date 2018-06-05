/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jodao.etisalattextng.ussddaemon;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ayeola
 */
public class TelcoConnectionMonitor extends Thread implements Runnable {

    protected BufferedReader networkReader;
    protected DataOutputStream outputSream;
    protected int mainCommandsPos = -1;
    protected String connectionName = "NONE";
    private USSDDaemon ussdDaemon;
    private Socket telcoSocket;
    //protected boolean keepRunning = true;
    private long nextHandShakeTime;
    private long terminateTime;
    private static final int WAIT_LENGTH_SECS = 60;
    private DataInputStream inputstream;
    private Utilities utils;
    private dataAccess dBAccess;

    public TelcoConnectionMonitor(Socket telcoSocket, int port, USSDDaemon ussdDaemon) {
        this.ussdDaemon = ussdDaemon;
        this.connectionName = "HOST" + port;
        this.telcoSocket = telcoSocket;



        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, WAIT_LENGTH_SECS);
        nextHandShakeTime = cal.getTimeInMillis();
        terminateTime = cal.getTimeInMillis();
        utils = new Utilities();


        String request = "390000006500000000000000ffffffffffffffff63656c6c756c000000000063656c6c756c0000000000000000000000000000000010000000";

        byte[] msgBytes = utils.hexStringToByteArray(request);

        try {
            outputSream = new DataOutputStream(telcoSocket.getOutputStream());

            ussdDaemon.writeMsgToServer(msgBytes, this);

            System.out.println("Message Sent Successfully");
            System.out.println("Sent Message : " + request.getBytes());
            System.out.println("Message Length : " + msgBytes.length);

        } catch (IOException ex) {
            System.out.println("Login Error : " + ex.getMessage());
        }
    }

    public void AutoReconnectGateway() {
        try {
            Socket clientSocket = new Socket("10.71.161.79", 4400);
            this.telcoSocket = clientSocket;
            String request = "390000006500000000000000ffffffffffffffff63656c6c756c000000000063656c6c756c0000000000000000000000000000000010000000";

            byte[] msgBytes = utils.hexStringToByteArray(request);
            outputSream = new DataOutputStream(telcoSocket.getOutputStream());

            ussdDaemon.writeMsgToServer(msgBytes, this);
            System.out.println("Auto reconnection successful");
            System.out.println("Sent Message : " + request.getBytes());
            System.out.println("Message Length : " + msgBytes.length);

            USSDDaemon.keepRunning = true;

        } catch (UnknownHostException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            try {
                Thread.sleep(300000);
                AutoReconnectGateway();
            } catch (InterruptedException ex1) {
                ex1.getMessage();
            }
            ex.getMessage();
        }
    }

    public class HandShakeRequest extends Thread {

        public HandShakeRequest() {
        }

        @Override
        public void run() {
            // while (USSDDaemon.keepRunning) {
            // if (nextHandShakeTime < Calendar.getInstance().getTimeInMillis()) {
            try {
                TimeUnit.SECONDS.sleep(30);
                //sleep 30 seconds before responding
                System.out.println("We are sending an HandShake request");
                String handShake = "140000008300000000000000ffffffffffffffff";
                try {
                    byte[] handShakeBytes = utils.hexStringToByteArray(handShake);
                    ussdDaemon.writeMsgToServer(handShakeBytes, TelcoConnectionMonitor.this);
                } catch (Exception ex1) {
                    System.out.println("Broken Pipe Exception : " + ex1.getMessage());
                }
                try {
                    TimeUnit.SECONDS.sleep(WAIT_LENGTH_SECS);
                } catch (Exception ex) {
                    System.out.println("Hand Shake Error : " + ex.getMessage());
                }
                // }
                //}
            } catch (InterruptedException ex) {
                Logger.getLogger(TelcoConnectionMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
            // }
            //}
        }
    }

    public class HandShakeResponse extends Thread {

        String currID;
        int requID;

        public HandShakeResponse() {
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(30);//sleep 30 seconds before responding
                System.out.println("We are sending response back to the USSDC Server");
                String handShakeResponse = "140000008400000000000000ffffffffffffffff";

                try {
                    byte[] UssdUnBindBytes = utils.hexStringToByteArray(handShakeResponse);
                    ussdDaemon.writeMsgToServer(UssdUnBindBytes, TelcoConnectionMonitor.this);

                    System.out.println("Size of the UssdHand Shake Response : " + UssdUnBindBytes.length);

                    System.out.println("Ussd Hand Shake Response Sent to the USSDC Server !!!");
                } catch (Exception ex1) {
                    System.out.println("Hand Shake Response : " + ex1.getMessage());
                }
                try {
                    TimeUnit.SECONDS.sleep(60 * 10);
                } catch (Exception ex) {
                    System.out.println("Error in termination the session : " + ex.getMessage());
                }

            } catch (Exception ex) {
                System.out.println("response error : " + ex.getMessage());
            }
        }
    }

    public void UssdUnBind() {

//            System.out.println("What is goin on here");
//                System.out.println("Keep running");               
        System.out.println("I want to terminate the session");
        String UssdUnBindRequest = "140000006600000000000000ffffffffffffffff";

        utils = new Utilities();
        try {
            byte[] UssdUnBindBytes = utils.hexStringToByteArray(UssdUnBindRequest);
            ussdDaemon.writeMsgToServer(UssdUnBindBytes, TelcoConnectionMonitor.this);

            System.out.println("Size of the UssdUnBind : " + UssdUnBindBytes.length);

            System.out.println("Ussd Service Application has logged out !!!");
        } catch (Exception ex1) {
            System.out.println("Terminated Error : " + ex1.getMessage());
        }
        try {
            TimeUnit.SECONDS.sleep(60 * 10);
        } catch (Exception ex) {
            System.out.println("Error in termination the session : " + ex.getMessage());
        }


    }

    public void UssdAbort() {

        System.out.println("USSD Service Application About to be Aborted");
        String ussdAbort = "1400000072000000360000000100001301000007";

        utils = new Utilities();
        try {
            byte[] UssdAbortBytes = utils.hexStringToByteArray(ussdAbort);
            ussdDaemon.writeMsgToServer(UssdAbortBytes, TelcoConnectionMonitor.this);

            System.out.println("Size of the USSD Abort : " + UssdAbortBytes.length);

            System.out.println("USSD Service Application Aborted for unknown reason !!!");
        } catch (Exception ex1) {
            System.out.println("Aborted Error : " + ex1.getMessage());
        }
        try {
            TimeUnit.SECONDS.sleep(60 * 10);
        } catch (Exception ex) {
            System.out.println("Error in termination the session : " + ex.getMessage());
        }


    }

    public class UssdUnBindRequest extends Thread {

        String currID;
        int requID;

        public UssdUnBindRequest() {
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(30);//sleep 30 seconds before responding
                UssdUnBind();

            } catch (Exception ex) {
                System.out.println("response error : " + ex.getMessage());
            }
        }
    }

    //UssdBegin session terminated msisdnInHex, sessionID, ussdMenuHex
    public void SendUssdBeginTerminateSessionRequest(String msisdnInHex, String sessionID, String ussdMenuHex) {
        System.out.println("Sending the last message and ending the session");
        //Terminating Session
        //String endresponseInHex = "800000007100000000000000ffffffff"+sessionID+"2003"+msisdnInHex+"000000000000000000002a333630000000000000000000000000000000000044"+ussdMenuHex;
        String endresponseInHex = "800000007100000000000000ffffffff" + sessionID + "2003" + msisdnInHex + "00000000000000002a333630000000000000000000000000000000000044" + ussdMenuHex;
        int padSize = 256;
        String padStr = "0";
        String endresponseInHexPadded = ussdDaemon.padding.rightPad(endresponseInHex, padSize, padStr);
        byte[] responseInBytes = utils.hexStringToByteArray(endresponseInHexPadded);
        ussdDaemon.writeMsgToServer(responseInBytes, this);

        ussdDaemon.sessionList.remove("sessiondata");

        System.out.println("Session terminated Successfully");
    }

    //UssdBeging session continue
    public void SendUssdBeginContinueSessionRequest(String msisdn, String sessionID, String ussdString) {
        int padSize = 444;
        String padStr = "0";

        String ussdContinue = "de0000007000000000000000ffffffff" + sessionID + "2001" + msisdn + "00000000000000002a333630000000000000000000000000000000000044" + ussdString;//"0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        String ussdContinuePadded = ussdDaemon.padding.rightPad(ussdContinue, padSize, padStr);

        byte[] response = utils.hexStringToByteArray(ussdContinuePadded);
        ussdDaemon.writeMsgToServer(response, this);

        System.out.println("USSD Continue Request Message Sent to USSDC Server");

    }

    //Sending ussd continue session request
    public void SendUssdContinueSessionRequest(String msisdnInHex, String sessionID, String userrquestHex) {
        int index = userrquestHex.indexOf("a");

        if (index != -1) {
            String userrquestHex1 = userrquestHex.replaceAll("a", "0a");

            int padSize = 446;
            String padStr = "0";

            String ussdContinue = "df0000007000000000000000ffffffff" + sessionID + "2001" + msisdnInHex + "00000000000000002a333630000000000000000000000000000000000044" + userrquestHex1;//"0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
            String ussdContinuePadded = ussdDaemon.padding.rightPad(ussdContinue, padSize, padStr);

            byte[] responseInBytes = utils.hexStringToByteArray(ussdContinuePadded);
            ussdDaemon.writeMsgToServer(responseInBytes, this);
            System.out.println("Size of the response sent to the user : " + responseInBytes.length);

            System.out.println("USSD Continue response sent to the subscriber");

        } else {
            int padSize = 446;
            String padStr = "0";

            String ussdContinue = "df0000007000000000000000ffffffff" + sessionID + "2001" + msisdnInHex + "00000000000000002a333630000000000000000000000000000000000044" + userrquestHex;//"0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
            String ussdContinuePadded = ussdDaemon.padding.rightPad(ussdContinue, padSize, padStr);

            byte[] responseInBytes = utils.hexStringToByteArray(ussdContinuePadded);
            ussdDaemon.writeMsgToServer(responseInBytes, this);
            System.out.println("Size of the response sent to the user : " + responseInBytes.length);

            System.out.println("USSD Continue response sent to the subscriber");

        }
    }

    //Sending the last message and terminate session after series of transaction
    public void SendUssdTerminateSessionRequest(String msisdnInHex, String sessionID, String userrquestHex) {
        System.out.println("Sending last message and ending the session");
        //String endresponseInHex = "800000007100000000000000ffffffff"+sessionID+"2003"+msisdnInHex+"000000000000000000002a333630000000000000000000000000000000000044"+userrquestHex;
        String endresponseInHex = "800000007100000000000000ffffffff" + sessionID + "2003" + msisdnInHex + "00000000000000002a333630000000000000000000000000000000000044" + userrquestHex;

        int padSize = 256;
        String padStr = "0";

        String endresponseInHexPadded = ussdDaemon.padding.rightPad(endresponseInHex, padSize, padStr);
        byte[] responseInBytes = utils.hexStringToByteArray(endresponseInHexPadded);
        ussdDaemon.writeMsgToServer(responseInBytes, this);

        System.out.println("Session ended Successfully");

        ussdDaemon.sessionList.remove("sessiondata");
        return;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public boolean isKeepRunning() {
        return USSDDaemon.keepRunning;
    }

    public DataOutputStream getOutputSream() {
        return outputSream;
    }

    public DataInputStream getInputstream() {
        return inputstream;
    }

    @Override
    public void run() {
        dBAccess = new dataAccess();
        while (USSDDaemon.keepRunning) {
            try {
                inputstream = new DataInputStream(telcoSocket.getInputStream());

                byte[] reqBytes = new byte[1024];
                //try {
                inputstream.read(reqBytes);
//                } catch (java.net.SocketException ex) {
//                    System.out.println("Input Exception : " +ex.getMessage());
//                    USSDDaemon.keepRunning = false;
//                    this.interrupt();
//                    new USSDDaemon().UssdBind();
//                }
                //byte[] reqBytes = IOUtils.toByteArray(inputstream);
                String receivedInHex = utils.bytesToHex(reqBytes);

                //System.out.println("response from USSDC Server : " +responseInHex);

                String commandID = receivedInHex.substring(8, 10);

                System.out.println("CommandID : " + commandID);

                //Receiving UssdBind Response from USSDC Server
                if (commandID.equals("67")) {
                    String UssdBind_response = receivedInHex.substring(0, 62);
                    System.out.println("UssdBind response : " + UssdBind_response);

                    System.out.println("Login Successful");
                    //Sending Hand Shake Request to the USSDC Server
                    new HandShakeRequest().start();

                    USSDDaemon.ussdbindResponseReceived = true;

                    continue;
                }

                //Receiving UssdUnBind Response from USSDC Server
                if (commandID.equals("68")) {
                    String UssdUnBind_response = receivedInHex.substring(0, 40);
                    System.out.println("Ussd UnBind response : " + UssdUnBind_response);
                    continue;
                }


                if (commandID.equals("76")) {
                }
                if (commandID.equals("72")) {
                    String ussdAbortMsg = receivedInHex.substring(0, 40);
                    String commandStatus = receivedInHex.substring(16, 24);
                    String sessionID = receivedInHex.substring(24, 32);

                    System.out.println("Ussd Abort Message : " + ussdAbortMsg);
                    System.out.println("Command Status of the USSD  Abort Message : " + commandStatus);
                    System.out.println("SessionID of the USSD Abort Message : " + sessionID);

                    System.out.println("USSD Service Application Aborted for Unknown reason!!!");
                    System.out.println("Session aborted either by the user or USSDC");

                    ussdDaemon.sessionList.remove("sessiondata");
                }


                //Sending Hand Shake response back to the USSDC Server
                if (commandID.equals("83")) {
                    new HandShakeResponse().start();
                    System.out.println("USSD HandShake request received from USSDC");
                    String ussdHandShakerequestInHex = receivedInHex.substring(0, 40);
                    System.out.println("USSD HandShake request In Hexadecimal : " + ussdHandShakerequestInHex);
                    continue;
                }

                //String url = "http://localhost/USSDMenu/USSDNavigator.php?";
                String uwiniwinUrl = "http://localhost/uwin.php?";
                String walletUrl = "http://localhost/test_app.php?";
                String prepaygoUrl = "http://localhost/prepaygo.php?";
                String boaUrl = "http://localhost/USSDAggregator/USSDBOA/boa.php?";
                String kaizenUrl = "http://localhost/kaizen/kpdemo.php?";
                String kogiUrl = "http://localhost/USSDAggregator/ussdkogi.php?";
                String FinconnektUrl = "http://localhost/USSDAggregator/USSDFinconnekt/Finconnekt.php?";
                String voucherAppUrl = "http://localhost/USSDAggregator/USSDVoucher/VoucherApp.php?";
                String poserAppUrl = "http://localhost/USSDAggregator/poser/poserapp.php?";

                if (commandID.equals("6f") || commandID.equals("70")) {
                    String msisdnInHex = receivedInHex.substring(44, 70);
                    String sessionID = receivedInHex.substring(24, 32);

                    System.out.println("MSISDN In Hexadecimal : " + msisdnInHex);

                    String msisdn = utils.fromHex(msisdnInHex);

                    System.out.println("Subscriber MSISDN : " + msisdn);

                    String UssdStringInHex = receivedInHex.substring(130);
                    String UssdString = (utils.convertHexToString(UssdStringInHex)).trim();


                    //check whether the session alreay exists
                    //if(ussdDaemon.sessionList.containsKey(msisdn)){
                    //if (ussdDaemon.sessionList.containsKey("sessiondata")) {
                    if (ussdDaemon.validateSession(sessionID)) {
                        //byte[] receivedInBytes = utils.hexStringToByteArray(receivedInHex.substring(0, 49));
                        String userresponse = receivedInHex.substring(0, 2048);

                        System.out.println("User Request In Hex : " + userresponse);

                        byte[] userRequestInBytes = utils.hexStringToByteArray(userresponse);
                        System.out.println("User Request In Bytes : " + userRequestInBytes);

                        System.out.println("User Request Size : " + userRequestInBytes.length);

                        //ussdDaemon.sessionList.get(msisdn).Process(sessionID, userresponse);
                        ussdDaemon.sessionList.get("sessiondata").Process(sessionID, userresponse);


                    } else {

                        //It must be a new session
                        //json.put("msisdn", msisdn);
                        //json.put("ussdstring", UssdString);
                        String serviceCodeInHex = (receivedInHex.substring(86, 128));

                        String serviceCode = (utils.fromHex(serviceCodeInHex)).trim() + "#";

                        System.out.println("Service Code : " + serviceCode);

                        String userrequest = receivedInHex.substring(0, 2048);

                        System.out.println("BEGIN Request : " + userrequest);

                        byte[] userRequestInBytes = utils.hexStringToByteArray(userrequest);

                        System.out.println("User Request Size : " + userRequestInBytes.length);

                        System.out.println("UssdString : " + UssdString);


                        //Trying to call the service code *360#
                        //service code in hexadecimal
//                      if(serviceCode.equals("*360#")){
                        if (UssdString.equals("*360#")) {
                            System.out.println("USSD String Cellulant : " + UssdString);
                            SessionMOHandler smh = new SessionMOHandler(msisdn, ussdDaemon, walletUrl, this);
                            smh.setMsisdn(msisdn);
                            smh.setSessionID(sessionID);
                            smh.setUssdString(UssdString);
                            smh.setServerURL(walletUrl);
                            ussdDaemon.sessionList.put("sessiondata", smh);
                            ussdDaemon.writeSessionIdToFile(sessionID, walletUrl, msisdn);
                            //ussdDaemon.ussdStr.put(UssdString, smh);
                            //dBAccess.LogServices(sessionID, UssdString);
                            smh.Process(sessionID, userrequest);
                        } else if (UssdString.equals("*360*300#")) {
                            System.out.println("USSD String UWINIWIN : " + UssdString);
                            SessionMOHandler smh = new SessionMOHandler(msisdn, ussdDaemon, uwiniwinUrl, this);
                            smh.setMsisdn(msisdn);
                            smh.setSessionID(sessionID);
                            smh.setUssdString(UssdString);
                            smh.setServerURL(uwiniwinUrl);
                            ussdDaemon.sessionList.put("sessiondata", smh);
                            ussdDaemon.writeSessionIdToFile(sessionID, uwiniwinUrl, msisdn);
                            //String msg = dBAccess.LogServices(sessionID, UssdString);
                            //System.out.println("Message : " +msg);
                            smh.Process(sessionID, userrequest);
                        } else if (UssdString.equals("*360*100#")) {
                            System.out.println("USSD String Kogi Ussd Service : " + UssdString);
                            SessionMOHandler smh = new SessionMOHandler(msisdn, ussdDaemon, kogiUrl, this);
                            smh.setMsisdn(msisdn);
                            smh.setSessionID(sessionID);
                            smh.setUssdString(UssdString);
                            smh.setServerURL(kogiUrl);
                            ussdDaemon.sessionList.put("sessiondata", smh);
                            ussdDaemon.writeSessionIdToFile(sessionID, kogiUrl, msisdn);
                            //String msg = dBAccess.LogServices(sessionID, UssdString);
                            //System.out.println("Message : " +msg);
                            smh.Process(sessionID, userrequest);
                        } else if (UssdString.equals("*360*200#")) {
                            System.out.println("USSD String Bank Of Agric : " + UssdString);
                            SessionMOHandler smh = new SessionMOHandler(msisdn, ussdDaemon, boaUrl, this);
                            smh.setMsisdn(msisdn);
                            smh.setSessionID(sessionID);
                            smh.setUssdString(UssdString);
                            smh.setServerURL(boaUrl);
                            ussdDaemon.sessionList.put("sessiondata", smh);
                            ussdDaemon.writeSessionIdToFile(sessionID, boaUrl, msisdn);
                            //String msg = dBAccess.LogServices(sessionID, UssdString);
                            //System.out.println("Message : " +msg);
                            smh.Process(sessionID, userrequest);
                        } else if (UssdString.equals("*360*123#")) {
                            System.out.println("USSD String Kaizen : " + UssdString);
                            SessionMOHandler smh = new SessionMOHandler(msisdn, ussdDaemon, kaizenUrl, this);
                            smh.setMsisdn(msisdn);
                            smh.setSessionID(sessionID);
                            smh.setUssdString(UssdString);
                            smh.setServerURL(kaizenUrl);
                            ussdDaemon.sessionList.put("sessiondata", smh);
                            ussdDaemon.writeSessionIdToFile(sessionID, kaizenUrl, msisdn);
                            //String msg = dBAccess.LogServices(sessionID, UssdString);
                            //System.out.println("Message : " +msg);
                            smh.Process(sessionID, userrequest);

                        } else if (UssdString.equals("*360*400#")) {
                            System.out.println("USSD String Kaizen : " + UssdString);
                            SessionMOHandler smh = new SessionMOHandler(msisdn, ussdDaemon, FinconnektUrl, this);
                            smh.setMsisdn(msisdn);
                            smh.setSessionID(sessionID);
                            smh.setUssdString(UssdString);
                            smh.setServerURL(kaizenUrl);
                            ussdDaemon.sessionList.put("sessiondata", smh);
                            ussdDaemon.writeSessionIdToFile(sessionID, kaizenUrl, msisdn);
                            //String msg = dBAccess.LogServices(sessionID, UssdString);
                            //System.out.println("Message : " +msg);
                            smh.Process(sessionID, userrequest);

                        } else if (UssdString.equals("*360*500#")) {
                            System.out.println("USSD String Kaizen : " + UssdString);
                            SessionMOHandler smh = new SessionMOHandler(msisdn, ussdDaemon, voucherAppUrl, this);
                            smh.setMsisdn(msisdn);
                            smh.setSessionID(sessionID);
                            smh.setUssdString(UssdString);
                            smh.setServerURL(kaizenUrl);
                            ussdDaemon.sessionList.put("sessiondata", smh);
                            ussdDaemon.writeSessionIdToFile(sessionID, kaizenUrl, msisdn);
                            //String msg = dBAccess.LogServices(sessionID, UssdString);
                            //System.out.println("Message : " +msg);
                            smh.Process(sessionID, userrequest);

                        } else if (UssdString.equals("*360*600#")) {
                            System.out.println("USSD String Survey : " + UssdString);
                            SessionMOHandler smh = new SessionMOHandler(msisdn, ussdDaemon, poserAppUrl, this);
                            smh.setMsisdn(msisdn);
                            smh.setSessionID(sessionID);
                            smh.setUssdString(UssdString);
                            smh.setServerURL(poserAppUrl);
                            ussdDaemon.sessionList.put("sessiondata", smh);
                            ussdDaemon.writeSessionIdToFile(sessionID, poserAppUrl, msisdn);
                            //String msg = dBAccess.LogServices(sessionID, UssdString);
                            //System.out.println("Message : " +msg);
                            smh.Process(sessionID, userrequest);

                        } else {
                            System.out.println("USSD String UNKNOWN SERVICE CODE  : " + UssdString);
//                            SessionMOHandler smh = new SessionMOHandler(msisdn, ussdDaemon, this);
//                            smh.Process(sessionID, userrequest);
                            String unknownServiceCode = "service code is not allowed";
                            String ussdMenuHex = utils.convertStringToHex(unknownServiceCode);
                            SendUssdBeginTerminateSessionRequest(msisdnInHex, sessionID, ussdMenuHex);

                        }

                    }

                    continue;
                }

                //Sending Hand Shake Request to the USSDC Server
                // new HandShakeRequest().start();

                if (commandID.equals("84")) {
                    String UssdShake_response = receivedInHex.substring(0, 40);
                    System.out.println("Ussd Hand Shake response received : " + UssdShake_response);
                    //new HandShakeResponse().start();
                    new HandShakeRequest().start();

                }

            } catch (SocketException ex) {
                System.out.println("Socket Exception Error  : " + ex.getMessage());
                ex.printStackTrace();

                if (telcoSocket != null) {
                    try {
                        telcoSocket.close();
                    } catch (IOException ex1) {
                        ex1.getMessage();
                    }
                }
                if (outputSream != null) {
                    try {
                        outputSream.close();
                    } catch (IOException ex1) {
                        ex1.getMessage();
                    }
                }
                USSDDaemon.keepRunning = false;
                System.out.println("Reconnecting to the USSD Server");
                //AutoReconnectGateway();
                this.interrupt();
                ussdDaemon.UssdBind();

            } catch (IOException ex1) {
                if (telcoSocket != null) {
                    try {
                        telcoSocket.close();
                    } catch (IOException ex) {
                        ex.getMessage();
                    }
                }
                if (outputSream != null) {
                    try {
                        outputSream.close();
                    } catch (IOException ex) {
                        ex.getMessage();
                    }
                }

                USSDDaemon.keepRunning = false;
                System.out.println("IOException : " + ex1.getMessage());
                System.out.println("Reconnecting to the USSD Server");
                //AutoReconnectGateway();
                this.interrupt();
                ussdDaemon.UssdBind();




            }


        }


    }

    private boolean isOpertationTypeID(String Str) {
        if (mainCommandsPos == -1) {
            return false;
        }
        for (int i = 0; i < Constants.OPERATIONTYPE.length; i++) {
            String operationType = Str.substring(8, 15);
            if (operationType.contains(Constants.OPERATIONTYPE[i])) {
                mainCommandsPos = i;
                return true;
            }
        }
        return false;
    }

    public int toByteArray(DataInputStream inputStream) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(inputStream.toString().getBytes());

        return bais.available();


    }

    public Thread getThreadByName(String threadName) {

        Thread _tmp = null;
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
        for (int i = 0; i < threadArray.length; i++) {
            if (threadArray[i].getName().equals(threadName)) {
                _tmp = threadArray[i];
            }

        }

        return _tmp;
    }

    public void stopAllRunningThreads() {
        for (Thread thread : Thread.getAllStackTraces().keySet()) {
            if (thread.getState() == Thread.State.RUNNABLE) {
                thread.interrupt();
            }
        }
    }
//    public void stopAllThreads(){
//        for(Thread thread : Thread.getAllStackTraces().keySet()){
//            if(thread.getState() == Thread.State.RUNNABLE){
//                thread.interrupt();
//            }
//        }
//
//        for(Thread thread : Thread.getAllStackTraces().keySet()){
//            if(thread.getState() == Thread.State.RUNNABLE){
//                thread.stop();
//            }
//        }
//    }
}
