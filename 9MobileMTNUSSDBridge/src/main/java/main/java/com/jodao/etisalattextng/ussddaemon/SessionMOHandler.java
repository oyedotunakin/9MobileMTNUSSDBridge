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
import java.util.ArrayList;
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
public class SessionMOHandler extends SessionController{
    String serverURL;// = "http://127.0.0.1:5000/ussdtest/test?";
    private Utilities utils;
    private Padding padding;
    private static final JSONParser PARSER = new JSONParser();


    public SessionMOHandler(String msisdn,USSDDaemon ussdDaemon,TelcoConnectionMonitor telcoMonitor){
        super(msisdn,ussdDaemon,telcoMonitor);
        sessionname = "MobileStationInitiatesSession";
        
    }

    public SessionMOHandler(String msisdn,USSDDaemon ussdDaemon,String url,TelcoConnectionMonitor telcoMonitor){
        super(msisdn,ussdDaemon,telcoMonitor);
        serverURL = url;
        endSession = false;

    }

    @Override
    public void HandleUSSDRequest(String sessionID,String userrequests){
        utils = new Utilities();
        padding = new Padding();
        String returnUserData = "This is a service test";
        String ussdMenuHex = null;
        String serviceCodeInHex = null;
        String serviceCode = null;

        try{

             serviceCodeInHex = userrequests.substring(130);
             serviceCode = (utils.convertHexToString(serviceCodeInHex)).trim();

             System.out.println("Service Code : " + serviceCode);

        }catch(Exception ex){
            ex.printStackTrace();
        }
        

//        if(serviceCode.equals("*360#") || serviceCode.equals("*360*200#") || serviceCode.equals("*360*100#") || serviceCode.equals("*360*1#")
//                || serviceCode.endsWith("*360*123#")){
        ArrayList listService = allServices();

     if(listService.contains(serviceCode)){
        
        System.out.println("User Phone : " +msisdn);
        System.out.println("SessionID : " +sessionID);
        System.out.println("User Request : " +userrequests);

       String userdataInHex = userrequests.substring(130);
       System.out.println("UssdString In Hex : " + userdataInHex);
       log("UssdString In Hex : " + userdataInHex);
       String userdata = null;
        try {
            userdata = (utils.fromHex(userdataInHex)).trim();

            System.out.println("Ussd String : " +userdata);
            log("Ussd String : " +userdata);
        } catch (UnsupportedEncodingException ex) {
            System.out.println("UnsupportedEncodingException : " +ex.getMessage());
            logException(ex);
        }
       
        //Send the stuff to the third party
       String received = "";
       String StrValue = "";
       //String userdata = "*360";
        System.out.println("Sending to ThirdParty : " +serverURL);

        log("Sending to ThirdParty : " +serverURL);
        
        HttpClient httpClient = new DefaultHttpClient();
        try{
            HttpGet httpGet = new HttpGet(serverURL + "msisdn="+msisdn+"&userdata="+URLEncoder.encode(userdata,"UTF-8")+"&sessionid="+sessionID );
            HttpResponse httpResponse;
            httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            if(entity != null){
                BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
                try{
                   
                    while((StrValue = reader.readLine()) != null){
                        System.out.println("String Value : " +StrValue);
                        received += StrValue;
                    }
                    System.out.println("Received from : " +serverURL+ received);

                     log("Received from : " +serverURL+ received);
                     
//                    if(endSession){
//                        EndSession();
//                        return;
//                    }

                    try{
                        JSONObject fromClient = (JSONObject) PARSER.parse(received);
                        returnUserData = ((String)fromClient.get("userdata")).replaceAll("\\\\n", "\n");
                        endSession = endSession || (boolean) fromClient.get("endofsession");
                        ussdMenuHex = utils.convertStringToHex(returnUserData);

                        System.out.println("USSDMenu In Hexadecimal : " +ussdMenuHex);

                        log("USSDMenu In Hexadecimal : " +ussdMenuHex);
                        
                    }catch(ParseException jsonex){
                        System.out.println("JSONException : " +jsonex);
                        returnUserData = "Sorry, the service you are requesting for is currently not available,try again later";
                        ussdMenuHex = utils.convertStringToHex(returnUserData);
                        endSession = true;
                        //telcoMonitor.UssdAbort();
                        logException(jsonex);
                    }
                }catch(IOException ioex){
                       System.out.println("IOException Error : " +ioex.getMessage());
                       returnUserData = "Sorry, the service you are requesting for is currently not available,try again later";
                       ussdMenuHex = utils.convertStringToHex(returnUserData);
                       endSession = true;
                       logException(ioex);
                }catch(IllegalStateException ex){
                    System.out.println("IllegalStateException : " +ex.getMessage());
                    returnUserData = "Sorry, the service you are requesting for is currently not available,try again later";
                    ussdMenuHex = utils.convertStringToHex(returnUserData);
                    endSession = true;
                }finally{
                    try{
                        reader.close();
                    }catch(IOException ioex){
                        System.out.println("IOException : " +ioex);
                        returnUserData = "Network Failure";
                        ussdMenuHex = utils.convertStringToHex(returnUserData);
                        endSession = true;
                        logException(ioex);
                    }
                }

            }
        }catch(ClientProtocolException clientprotocolEx){
            System.out.println("Client Protocol Exception : " +clientprotocolEx.getMessage());
            logException(clientprotocolEx);
        }catch(IOException ioex){
            System.out.println("IOException : " +ioex);
            returnUserData = "Network Failure";
            ussdMenuHex = utils.convertStringToHex(returnUserData);
            endSession = true;
            logException(ioex);
        }catch(IllegalStateException ex){
            System.out.println("IllegalStateException : " +ex.getMessage());
            returnUserData = "Sorry, the service you are requesting for is currently not available,try again later";
            ussdMenuHex = utils.convertStringToHex(returnUserData);
            endSession = true;
        }

        }else{
            returnUserData = "sorry, the service code you dial is incorrect,please contact the customer care";
            ussdMenuHex = utils.convertStringToHex(returnUserData);
            endSession = true;
        }
        //String serviceCodeHex = utils.convertStringToHex("*360");

         String msisdnInHex = userrequests.substring(44, 70);

         String commandID = userrequests.substring(8, 10);
         String  menuHex = utils.convertStringToHex(returnUserData);
         System.out.println("returnUserData : \n" + returnUserData);
         log("returnUserData : \n" + returnUserData);
         try{
         
         if(commandID.equals("70")){             
        
        if(endSession){

            System.out.println("Sending the last message and ending the session");
            //Terminating Session
            //String endresponseInHex = "800000007100000000000000ffffffff"+sessionID+"2003"+msisdnInHex+"000000000000000000002a333630000000000000000000000000000000000044"+ussdMenuHex;
            String endresponseInHex = "800000007100000000000000ffffffff"+sessionID+"2003"+msisdnInHex+"00000000000000002a333630000000000000000000000000000000000044"+ussdMenuHex;
            int padSize = 256;
            String padStr = "0";
            String endresponseInHexPadded = padding.rightPad(endresponseInHex, padSize, padStr);
            byte[] responseInBytes = utils.hexStringToByteArray(endresponseInHexPadded);
            ussdDaemon.writeMsgToServer(responseInBytes, telcoMonitor);

            ussdDaemon.sessionList.remove("sessiondata");

            System.out.println("Session ended Successfully");
            //EndSession();
        }else{
            //Handle continue Request Message

                 int padSize = 444;
                 String padStr = "0";

                 String ussdContinue = "de0000007000000000000000ffffffff"+sessionID+"2001"+msisdnInHex+"00000000000000002a333630000000000000000000000000000000000044"+menuHex;//"0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
                 String ussdContinuePadded = padding.rightPad(ussdContinue, padSize, padStr);
                     
                     byte[] response = utils.hexStringToByteArray(ussdContinuePadded);
                 ussdDaemon.writeMsgToServer(response, telcoMonitor);

            System.out.println("USSD Continue Request Message Sent to USSDC Server");
            log("USSD Continue Request Message Sent to USSDC Server : " +ussdContinuePadded );
            
                 }
        }
         
         if(commandID.equals("6f")){             
             
             //Handle USSD BEGIN Requests Message
            //Sending response back to the Mobile User through USSDC Server
             String ussdMenuHex1 = ussdMenuHex.replaceAll("a", "0a");

            String ussdContinue = "de0000007000000000000000ffffffff"+sessionID+"2001"+msisdnInHex+"00000000000000002a333630000000000000000000000000000000000044"+ussdMenuHex1;//+"0000000000000000000000000000000000000000000000000000000000000000000000";
            
            int padSize = 444;
            
            String padStr = "0";
            String ussdContinuePadded = padding.rightPad(ussdContinue, padSize, padStr);

            byte[] response = utils.hexStringToByteArray(ussdContinuePadded);
            ussdDaemon.writeMsgToServer(response, telcoMonitor);

            System.out.println("USSD Continue In Hexadecimal : " +ussdContinuePadded);
            log("USSD Continue In Hexadecimal : " +ussdContinuePadded);

            System.out.println("USSD Menu Sent to USSDC Server");
            log("USSD Menu Sent to USSDC Server : " +ussdContinuePadded);
            
        }
    }catch(Exception ex1){
        System.out.println("USSDContinue Error : " +ex1.getMessage());
        returnUserData = "Sorry, the service you are requesting for is currently not available";
        endSession = true;
        logException(ex1);
    }

    }


    public ArrayList allServices(){
        ArrayList listService = new ArrayList();
        
        listService.add(0, "*360#");
        listService.add(1, "*360*200#");
        listService.add(2, "*360*100#");
        listService.add(3, "*360*1#");
        listService.add(4, "*360*123#");
        listService.add(5, "*360*400#");
        listService.add(6, "*360*500#");
        listService.add(7, "*360*600#");


        return listService;
    }
    
}
