/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jodao.etisalattextng.ussd;

import com.jodao.etisalattextng.smpp.HTTPTransport;
import com.jodao.etisalattextng.ussddaemon.USSDDaemon;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

//import static ussddaemon.USSDDaemon.isconnected;
/**
 *
 * @author akin
 */
public class ConnectClient extends Thread {

    private DataInputStream inputstream;
    protected DataOutputStream outputSream;
    private final String prop_path = "9MobileBridge.config";
    private Properties appsProperties = new Properties();
    private static ConnectClient connectClient;
    private static WCSResponseWriter wInstance;
    private String wcsResponse = "";

    public String getWcsResponse() {
        return wcsResponse;
    }

    public void setWcsResponse(String wcsResponse) {
        this.wcsResponse = wcsResponse;
    }

    public ConnectClient() {
        try {
            Logger.getLogger(ConnectClient.class.getName()).info("Loading property file...");
            appsProperties.load(new FileReader(prop_path));
            Logger.getLogger(ConnectClient.class.getName()).info("Property file loaded...");
            Logger.getLogger(ConnectClient.class.getName()).info(appsProperties.toString());

//            smppTransport = new SmppTransport(appsProperties.getProperty("smsc_host"), Integer.parseInt(appsProperties.getProperty("smsc_port")), appsProperties.getProperty("smsc_user"), appsProperties.getProperty("smsc_password"));
        } catch (IOException ex) {
            if (LOG_LEVEL <= 2) {
                Logger.getLogger(ConnectClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static ConnectClient getInstance() {
        if (connectClient == null) {
            connectClient = new ConnectClient();
            wInstance = WCSResponseWriter.getInstance(clientSocket);
            wInstance.start();
            Logger.getLogger(ConnectClient.class.getName()).info("Started WCS Response writer..");
        }
        return connectClient;
    }
    public static boolean isconnected = false;
    public static Socket clientSocket;
    public static boolean keepAliveRunning = false;
    public static int LOG_LEVEL = 0;

    @Override
    public void run() {
        while (!isconnected) {
            try {
//            initializeLocalListener();
                clientSocket = new Socket(appsProperties.getProperty("gateway_url"), Integer.parseInt(appsProperties.getProperty("gateway_port")));
                clientSocket.setKeepAlive(true);
                LOG_LEVEL = Integer.parseInt(appsProperties.getProperty("log_level", "0"));
                isconnected = clientSocket.isConnected();
                if (LOG_LEVEL <= 1) {
                    Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Connection Status : {0}", isconnected);
                }

                inputstream = new DataInputStream(clientSocket.getInputStream());
                if (LOG_LEVEL <= 0) {
                    Logger.getLogger(ConnectClient.class.getName()).info("done...");
                }

                login(clientSocket);
                while (true) {
                    try {
                        byte[] reqBytes = new byte[1024];
                        //try {
                        if (LOG_LEVEL <= 0) {
                            Logger.getLogger(ConnectClient.class.getName()).info("about reading");
                        }
                        inputstream.read(reqBytes);
                        if (LOG_LEVEL <= 0) {
                            Logger.getLogger(ConnectClient.class.getName()).info("read");
                        }
                        String receivedInHex = Utilities.bytesToHex(reqBytes);
                        String length = receivedInHex.substring(0, 2);
                        int iLength = Utilities.HexadecimalToInteger(length);
                        if (LOG_LEVEL <= 1) {
                            Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Length of payload is {0}", iLength);
                        }
                        byte[] d_ = Arrays.copyOfRange(reqBytes, 0, iLength);
                        receivedInHex = Utilities.bytesToHex(d_);
                        if (LOG_LEVEL <= 1) {
                            Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Read bytes {0}", receivedInHex);
                        }

                        String commandID = receivedInHex.substring(8, 10);
                        if (LOG_LEVEL <= 1) {
                            Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "CommandID {0}", commandID);
                        }

                        if (commandID.equals("67")) {
                            String UssdBind_response = receivedInHex.substring(0, 62);
                            if (LOG_LEVEL <= 1) {
                                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "UssdBind response : {0}", UssdBind_response);
                            }
                            if (LOG_LEVEL <= 0) {
                                Logger.getLogger(ConnectClient.class.getName()).info("Login Successful");
                            }
                            //Sending Hand Shake Request to the USSDC Server
                            if (!keepAliveRunning) {
                                if (LOG_LEVEL <= 1) {
                                    Logger.getLogger(ConnectClient.class.getName()).info("starting keep alive");
                                }
                                new KeepAliveMessenger().start();
                            }
                            USSDDaemon.ussdbindResponseReceived = true;
                            continue;
                        }

                        if (commandID.equals("68")) {
                            String UssdUnBind_response = receivedInHex.substring(0, 40);
                            if (LOG_LEVEL <= 1) {
                                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Ussd UnBind response : {0}", UssdUnBind_response);
                            }
                        }

                        if (commandID.equals("76")) {
                        }
                        if (commandID.equals("72")) {
                            String ussdAbortMsg = receivedInHex.substring(0, 40);
                            String commandStatus = receivedInHex.substring(16, 24);
                            String sessionID = receivedInHex.substring(24, 32);

                            if (LOG_LEVEL <= 1) {
                                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Ussd Abort Message : {0}", ussdAbortMsg);
                            }
                            if (LOG_LEVEL <= 0) {
                                Logger.getLogger(ConnectClient.class.getName()).info(length);
                            }
                            if (LOG_LEVEL <= 1) {
                                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Command Status of the USSD  Abort Message : {0}", commandStatus);
                            }
                            if (LOG_LEVEL <= 1) {
                                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "SessionID of the USSD Abort Message : {0}", sessionID);
                            }

                            if (LOG_LEVEL <= 1) {
                                Logger.getLogger(ConnectClient.class.getName()).info("USSD Service Application Aborted for Unknown reason!!!");
                            }
                            if (LOG_LEVEL <= 0) {
                                Logger.getLogger(ConnectClient.class.getName()).info("Session aborted either by the user or USSDC");
                            }
                        }

                        //Sending Hand Shake response back to the USSDC Server
                        if (commandID.equals("83")) {
                            sendKeepAliveHandShake(clientSocket);
                            if (LOG_LEVEL <= 0) {
                                Logger.getLogger(ConnectClient.class.getName()).info("USSD HandShake request received from USSDC");
                            }
                            String ussdHandShakerequestInHex = receivedInHex.substring(0, 40);
                            if (LOG_LEVEL <= 0) {
                                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "USSD HandShake request In Hexadecimal : {0}", ussdHandShakerequestInHex);
                            }
                            continue;

                        }
                        if (commandID.equals("6f") || commandID.equals("70")) {
//                            peekArray(reqBytes);
                            String msisdnInHex = receivedInHex.substring(44, 70);
                            String sessionID = receivedInHex.substring(24, 32);
                            if (LOG_LEVEL <= 1) {
                                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "All from hex : {0}", Utilities.fromHex(receivedInHex));
                            }
                            if (LOG_LEVEL <= 1) {
                                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "MSISDN In Hexadecimal : {0}", msisdnInHex);
                            }

                            String msisdn = Utilities.fromHex(msisdnInHex);

                            if (LOG_LEVEL <= 1) {
                                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Subscriber MSISDN : {0}", msisdn);
                            }

                            String UssdStringInHex = receivedInHex.substring(130);
                            String ussdMessage = (Utilities.convertHexToString(UssdStringInHex)).trim();

                            //6f is a ussd begin type of message
                            HashMap h = new HashMap();
                            h.put("sender_cb", sessionID);
                            h.put("msisdn", msisdn);
                            h.put("message", ussdMessage);
                            h.put("wcs_url", appsProperties.getProperty("wcs_http_url"));
                            if (commandID.equals("6f")) {
                                h.put("receiver_cb", "FFFFFFFF");
                                h.put("msg_type", 0);
                                h.put("service_code", ussdMessage);
                                h.put("ussd_op_type", 1);
                            } else {
                                h.put("receiver_cb", sessionID);
                                h.put("msg_type", 1);
                                h.put("service_code", "358");
                                h.put("ussd_op_type", 3);
                            }
//                            try {
                            if (LOG_LEVEL <= 1) {
                                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Session Id::::::::::::{0}", sessionID);
                            }
                            String response = HTTPTransport.send(h);
                            if (LOG_LEVEL <= 1) {
                                Logger.getLogger(ConnectClient.class.getName()).info(response);
                            }
                            continue;
                        }
                        if (commandID.equals("84")) {
                            String UssdShake_response = receivedInHex.substring(0, 40);
                            if (LOG_LEVEL <= 1) {
                                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Ussd Hand Shake response received : {0}", UssdShake_response);
                            }

                        }

                    } catch (IOException ex) {
                        isconnected = false;
                        if (LOG_LEVEL <= 2) {
                            Logger.getLogger(ConnectClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                }
            } catch (IOException | NumberFormatException ex) {
                isconnected = false;
                if (LOG_LEVEL <= 2) {
                    Logger.getLogger(ConnectClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void sendKeepAliveHandShake(Socket clientSocket) {
        String handShake = "140000008300000000000000ffffffffffffffff";
        try {
            byte[] handShakeBytes = Utilities.hexStringToByteArray(handShake);
            clientSocket.getOutputStream().write(handShakeBytes);
            clientSocket.getOutputStream().flush();
        } catch (IOException ex) {
            if (LOG_LEVEL <= 2) {
                Logger.getLogger(ConnectClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void respondToUSSDC(String sessionID, Socket clientSocket, String msisdnInHex, String json_resp) throws ParseException {

        String data = json_resp;
        boolean endSession = true;
        String userrquestHex = Utilities.convertStringToHex(data);
        int index = userrquestHex.indexOf("a");

        if (index != -1) {
            userrquestHex = userrquestHex.replaceAll("a", "0a");
        }
        try {

//            int padSize = 446;
//            String padStr = "0";
            String response_string = "0000007000000000000000ffffffff  " + sessionID + "2001" + msisdnInHex + "00000000000000002a333630000000000000000000000000000000000044" + userrquestHex;//"0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

            if (endSession) {
                response_string = "0000007100000000000000ffffffff" + sessionID + "2003" + msisdnInHex + "00000000000000002a333630000000000000000000000000000000000044" + userrquestHex;
//                padSize = 256;
            }
            byte[] ussd_resp_string_b = Utilities.hexStringToByteArray(response_string);
            int commandLength = ussd_resp_string_b.length + 1;
            if (LOG_LEVEL <= 1) {
                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Calculated command length {0}", commandLength);
            }
            if (LOG_LEVEL <= 1) {
                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Resp {0}", response_string);
            }
            String cl = Integer.toHexString(commandLength);
            response_string = cl + response_string;
            if (LOG_LEVEL <= 1) {
                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Comm length {0}", cl);
            }
            if (LOG_LEVEL <= 1) {
                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "combined :{0}", response_string);
            }

            byte[] responseInBytes = Utilities.hexStringToByteArray(response_string);
            clientSocket.getOutputStream().write(responseInBytes);
            clientSocket.getOutputStream().flush();
            if (LOG_LEVEL <= 1) {
                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Size of the response sent to the user : {0}", responseInBytes.length);
            }
            if (LOG_LEVEL <= 0) {
                Logger.getLogger(ConnectClient.class.getName()).info("USSD Continue response sent to the subscriber");
            }
            //            log("USSD Continue response sent to the subscriber : " + ussdContinuePadded);
        } catch (IOException ex) {
            if (LOG_LEVEL <= 2) {
                Logger.getLogger(ConnectClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void respondToUSSDC(int msgType, java.lang.String senderCB, java.lang.String msIsdn, java.lang.String ussdString) throws ParseException {
//        boolean endSession = false;
        String endSession = "2001";
        String partA = "0000007000000000000000ffffffff";
        if (msgType == 2) {
            endSession = "2003";
            partA = "0000007100000000000000ffffffff";
        }

        String data = ussdString;

        String userrquestHex = Utilities.convertStringToHex(data);
        int index = userrquestHex.indexOf("a");

        if (index != -1) {
            userrquestHex = userrquestHex.replaceAll("a", "0a");
        }
        try {
            String response_string = partA + senderCB + endSession + Utilities.convertStringToHex(msIsdn) + "00000000000000002a333630000000000000000000000000000000000044" + userrquestHex;//"0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

            byte[] ussd_resp_string_b = Utilities.hexStringToByteArray(response_string);
            int commandLength = ussd_resp_string_b.length + 1;
            Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Calculated command length {0}", commandLength);
            Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Resp {0}", response_string);

            String cl = Integer.toHexString(commandLength);
            response_string = cl + response_string;
            if (LOG_LEVEL <= 1) {
                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Comm length {0}", cl);
            }
            if (LOG_LEVEL <= 1) {
                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "combined :{0}", response_string);
            }

            byte[] responseInBytes = Utilities.hexStringToByteArray(response_string);
            clientSocket.getOutputStream().write(responseInBytes);
            clientSocket.getOutputStream().flush();

            if (LOG_LEVEL <= 1) {
                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Size of the response sent to the user : {0}", responseInBytes.length);
            }

            if (LOG_LEVEL <= 0) {
                Logger.getLogger(ConnectClient.class.getName()).info("USSD Continue response sent to the subscriber");
            }
            //            log("USSD Continue response sent to the subscriber : " + ussdContinuePadded);
        } catch (IOException ex) {
            if (LOG_LEVEL <= 2) {
                Logger.getLogger(ConnectClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void login(Socket clientSocket) {
        String request = "390000006500000000000000ffffffffffffffff54657874396a610000000054657874396a6100000000000000000000000000000010000000";

        byte[] msgBytes = Utilities.hexStringToByteArray(request);

        try {
            OutputStream outputStream = clientSocket.getOutputStream();
//            Logger.getLogger(ConnectClient.class.getName()).info("Message Sent Successfully");
//            Logger.getLogger(ConnectClient.class.getName()).info("Sent Message : " + Arrays.toString(request.getBytes()));
            if (LOG_LEVEL <= 0) {
                Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Message Length : {0}", msgBytes.length);
            }
            outputStream.write(msgBytes);
        } catch (IOException ex) {
            if (LOG_LEVEL <= 2) {
                Logger.getLogger(ConnectClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean socketConnected() {
        if (clientSocket == null) {
            return false;
        }
        return clientSocket.isConnected();
    }

}
