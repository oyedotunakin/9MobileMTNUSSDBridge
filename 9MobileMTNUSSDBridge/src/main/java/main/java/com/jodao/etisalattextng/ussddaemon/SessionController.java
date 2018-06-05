/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jodao.etisalattextng.ussddaemon;

import com.jodao.etisalattextng.ussd.Padding;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ayeola
 */
public class SessionController extends SessionMonitor {

    protected String sessionname = "SessionParent";
    private static final JSONParser PARSER = new JSONParser();
    private long initTime;
    protected boolean endSession = false;
    protected String msisdn = "";
    protected String ussdString = "";
    protected String sessionID = "";
    protected String sessiondata = "";
    protected USSDDaemon ussdDaemon;
    protected TelcoConnectionMonitor telcoMonitor;
    private Utilities utils;
    private String serverURL = ""; //"http://localhost/USSDMenu/USSDNavigator.php?" ;
    //private String walletUrl = "http://localhost/test_app.php?";
    private boolean reply;
    private String url;
    private Padding padding;
    private String serviceCode = "";
    private String sessionId = "";
    private dataAccess dBAccess;

    /**
     * Sessions are tracked using their msisdn. When the system receives a
     * message, it first checks if there's a registered SessionController with
     * the msisdn. If there is, it hands the message over to the Client to
     * process at will. However, if there isn't, a new SessionController's
     * created and then the message is handed over to it. SessionController's
     * should be smart enough to determine what the appropriate response is.
     *
     * @param string
     */
    public SessionController(TelcoConnectionMonitor telcoMonitor) {
        this.initTime = Calendar.getInstance().getTimeInMillis();
        this.telcoMonitor = telcoMonitor;
        utils = new Utilities();
    }

    public SessionController(String msisdn, USSDDaemon ussdDaemon, TelcoConnectionMonitor telcoMonitor) {
        this.ussdDaemon = ussdDaemon;
        this.msisdn = msisdn;
        this.telcoMonitor = telcoMonitor;
        this.initTime = Calendar.getInstance().getTimeInMillis();
    }

    public SessionController(String msisdn, USSDDaemon ussdDaemon, String url, TelcoConnectionMonitor telcoMonitor) {
        this.msisdn = msisdn;
        this.ussdDaemon = ussdDaemon;
        this.telcoMonitor = telcoMonitor;
        this.serverURL = url;
    }

    protected void EndSession() {
        ussdDaemon.sessionList.remove(msisdn);
        System.out.println("Session " + msisdn + "ended");
    }


    /* process request */
    protected void Process(final String sessionId, final String message) {
//        Thread thread = new Thread(){
//            @Override
//          public void run(){
        try {
            int endOfSession = 0;
            if (endOfSession == 1) {
                endSession = true;
            }
        } catch (Exception ex) {
            System.out.println("Processing Error : " + ex.getMessage());
            logException(ex);
        }
        String commandID = message.substring(8, 10);

        if (commandID.contains("6f")) {
            HandleUSSDRequest(sessionId, message);
        } else if (commandID.contains("70")) {
            try {
                HandleUSSDResponse(sessionId, message);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
//          }
//        };
//
//        thread.start();
    }

    /* MO (Mobile Initiated) Messages */
    protected void HandleUSSDRequest(String sessionID, String request) {

        boolean reply = false;
        String serviceCodeInHex = request.substring(86);
        String UssdOpTypeStr = request.substring(42, 44);
        int UssdOpType = Integer.parseInt(UssdOpTypeStr, 16);
        String response = null;

        switch (UssdOpType) {
            case Constants.USSDREQUEST:
                String userdata = "Press\n1. Account Details\n2. Transfer\n3. Other";
                String userdataInHex = utils.convertStringToHex(userdata);
                response = "de0000007000000000000000010000052900AB122010" + msisdn + "00000000000000000000" + serviceCodeInHex + "0f" + userdataInHex;
                reply = true;
                break;

            case Constants.USSDNOTIFY:
                break;

            case Constants.USSDRESPONSE:
                break;

            case Constants.USSDRELEASE:
                break;
        }


        if (reply) {
            byte[] requestInBytes = utils.hexStringToByteArray(response);
            ussdDaemon.writeMsgToServer(requestInBytes, telcoMonitor);
        }
        if (endSession) {
            EndSession();
        }
    }

    /* MT (Network Initiated/Mobile Terminated) Messages */
    protected void HandleUSSDResponse(String sessionID, String response) throws UnsupportedEncodingException {

        padding = new Padding();
        utils = new Utilities();
        //dBAccess = new dataAccess();

        String UssdStringInHex = (response.substring(130));

        String UssdString = (utils.fromHex(UssdStringInHex)).trim();

        System.out.println("Service Code : " + UssdString);
        String msisdnInHex = "";
        String StrValue = "";
        String received = "";
        String returnUserData = "";
        String userrquestHex = null;

        try {
            String messageBody[] = this.loadSession(sessionID).split("\\|");

            serverURL = messageBody[0];
            msisdn = messageBody[1];
            sessionID = messageBody[2];

            System.out.println("Session Url : " + serverURL);
            System.out.println("Session msisdn : " + msisdn);

            System.out.println("Url from property file : " + serverURL);

            System.out.println("response from subscriber : " + UssdString);
            System.out.println("Phone : " + msisdn);
            System.out.println("SessionID : " + sessionID);

            //System.out.println("USSD String : " +USSDStr);

            //connecting to cellulant wallet server
//        if(USSDStr.equals("*360#")){
//          serverURL = "http://localhost/test_app.php?";
//        }
//        //connecting to uwiniwin server
//        if(USSDStr.equals("*360*200#")){
//            serverURL = "http://localhost/uwin.php?";
//        }
//
//        if(USSDStr.equals("*360*100#")){
//            serverURL = "http://localhost/prepaygo.php?";
//        }
//        if(USSDStr.equals("*360*1#")){
//            serverURL = "http://localhost/boa.php?";
//        }

            System.out.println("response fron subscriber : " + response);
            log("response fron subscriber : " + response);

            String userdata1 = utils.convertHexToString(response.substring(130));

            System.out.println("Selected Menu : " + userdata1);
            log("Selected Menu : " + userdata1);

            //System.out.println("Selected Menu :  " +userdata);
            // String msisdnInHex = response.substring(44, 70);
            msisdnInHex = response.substring(44, 70);

            //System.out.println("MSISDN : " +utils.convertHexToString(msisdnInHex));
            System.out.println("Phone : " + msisdn);

//        String StrValue = "";
//        String received = "";
//        String returnUserData = "";
//        String userrquestHex = null;

            HttpClient httpClient = new DefaultHttpClient();
            try {

                HttpGet httpGet = new HttpGet(serverURL + "msisdn=" + msisdn + "&userdata=" + URLEncoder.encode(userdata1.trim(), "UTF-8") + "&sessionid=" + sessionID);

                System.out.println("user data1 : " + userdata1 + "done");
                System.out.println("user data1 : " + userdata1.trim() + "done");
                HttpResponse httpResponse;
                httpResponse = httpClient.execute(httpGet);
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    try {

                        while ((StrValue = reader.readLine()) != null) {
                            System.out.println("String Value : " + StrValue);
                            received += StrValue;
                        }
                        System.out.println("Received from : " + serverURL + received);
                        log("Received from : " + serverURL + received);

                        try {
                            JSONObject fromClient = (JSONObject) PARSER.parse(received);
                            returnUserData = ((String)fromClient.get("userdata")).replaceAll("\\\\n", "\n");
                            endSession = endSession || (Boolean)fromClient.get("endofsession");


                            userrquestHex = utils.convertStringToHex(returnUserData);

                            System.out.println("USSDMenu In Hexadecimal : " + userrquestHex);
                            log("USSDMenu In Hexadecimal : " + userrquestHex);
                        } catch (ParseException jsonex) {

                            System.out.println("JSONException : " + jsonex);
                            jsonex.printStackTrace();
                            returnUserData = "Sorry, the service you are requesting for is currently not available,try again later";
                            userrquestHex = utils.convertStringToHex(returnUserData);
                            endSession = true;
                            //logException(jsonex);
                            //telcoMonitor.UssdAbort();                        

                        } 
                    } catch (IOException ioex) {
                        System.out.println("IOException Error : " + ioex.getMessage());
                        returnUserData = "Network Failure";
                        userrquestHex = utils.convertStringToHex(returnUserData);
                        endSession = true;
                        //ioex.printStackTrace();
                        //logException(ioex);
                    } finally {
                        try {
                            reader.close();
                        } catch (IOException ioex) {
                            System.out.println("IOException : " + ioex);
                            returnUserData = "Network Failure";
                            userrquestHex = utils.convertStringToHex(returnUserData);
                            endSession = true;
                            //logException(ioex);
                        }
                    }

                }
            } catch (ClientProtocolException clientprotocolEx) {
                System.out.println("Client Protocol Exception : " + clientprotocolEx.getMessage());
                logException(clientprotocolEx);
            } catch (IOException ioex) {
                System.out.println("IOException : " + ioex);
                returnUserData = "Network Failure";
                userrquestHex = utils.convertStringToHex(returnUserData);
                endSession = true;
                logException(ioex);
            } catch (NullPointerException ex) {
                System.out.println("IOException : " + ex.getMessage());
                returnUserData = "Network Failure";
                userrquestHex = utils.convertStringToHex(returnUserData);
                endSession = true;
            }
        } catch (NullPointerException ex) {
            System.out.println("Unsupported Service Code : " + ex.getMessage());
            returnUserData = "Unsupported Service Code";
            userrquestHex = utils.convertStringToHex(returnUserData);
            endSession = true;
        }

        try {
            if (endSession) {

                System.out.println("Sending last message and ending the session");
                //String endresponseInHex = "800000007100000000000000ffffffff"+sessionID+"2003"+msisdnInHex+"000000000000000000002a333630000000000000000000000000000000000044"+userrquestHex;
                String endresponseInHex = "800000007100000000000000ffffffff" + sessionID + "2003" + msisdnInHex + "00000000000000002a333630000000000000000000000000000000000044" + userrquestHex;

                int padSize = 256;
                String padStr = "0";

                String endresponseInHexPadded = padding.rightPad(endresponseInHex, padSize, padStr);
                byte[] responseInBytes = utils.hexStringToByteArray(endresponseInHexPadded);
                ussdDaemon.writeMsgToServer(responseInBytes, telcoMonitor);

                EndSession();

                System.out.println("Session ended Successfully");
                log("USSD Continue response sent to the subscriber : " + endresponseInHexPadded);
                ussdDaemon.sessionList.remove("sessiondata");
                return;
            } else {

                int index = userrquestHex.indexOf("a");

                if (index != -1) {
                    String userrquestHex1 = userrquestHex.replaceAll("a", "0a");

                    int padSize = 446;
                    String padStr = "0";

                    String ussdContinue = "df0000007000000000000000ffffffff" + sessionID + "2001" + msisdnInHex + "00000000000000002a333630000000000000000000000000000000000044" + userrquestHex1;//"0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
                    String ussdContinuePadded = padding.rightPad(ussdContinue, padSize, padStr);

                    byte[] responseInBytes = utils.hexStringToByteArray(ussdContinuePadded);
                    ussdDaemon.writeMsgToServer(responseInBytes, telcoMonitor);
                    System.out.println("Size of the response sent to the user : " + responseInBytes.length);

                    System.out.println("USSD Continue response sent to the subscriber");
                    log("USSD Continue response sent to the subscriber : " + ussdContinuePadded);
                } else {
                    int padSize = 446;
                    String padStr = "0";

                    String ussdContinue = "df0000007000000000000000ffffffff" + sessionID + "2001" + msisdnInHex + "00000000000000002a333630000000000000000000000000000000000044" + userrquestHex;//"0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
                    String ussdContinuePadded = padding.rightPad(ussdContinue, padSize, padStr);

                    byte[] responseInBytes = utils.hexStringToByteArray(ussdContinuePadded);
                    ussdDaemon.writeMsgToServer(responseInBytes, telcoMonitor);
                    System.out.println("Size of the response sent to the user : " + responseInBytes.length);

                    System.out.println("USSD Continue response sent to the subscriber");
                    log("USSD Continue response sent to the subscriber : " + ussdContinuePadded);
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException : " + ex.getMessage());
            logException(ex);
        }
    }

    protected void log(String eventString) {
    }

    protected void logException(Exception ex) {
    }

    protected void endSession() {
        ussdDaemon.sessionList.remove(msisdn);
        log("Session " + msisdn + "ended");
    }

    protected long getInitTime() {
        return initTime;
    }

    protected boolean isEndSession() {
        return endSession;
    }

    protected String getSessionname() {
        return sessionname;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getUssdString() {
        return ussdString;
    }

    public String getSessiondata() {
        return sessiondata;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public void setUssdString(String ussdString) {
        this.ussdString = ussdString;
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }
}
