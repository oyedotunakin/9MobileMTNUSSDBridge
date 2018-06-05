/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jodao.etisalattextng.ussddaemon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ayeola
 */
public class SessionMTHandler  extends SessionController implements Runnable{

    private Socket server;
    private boolean  keepRunning = false;
    private BufferedReader thirdPartyReader;
    private PrintWriter thirdPartyWriter;
    private Utilities utils;
    private static final JSONParser PARSER = new JSONParser();
    



    public SessionMTHandler(Socket server,USSDDaemon ussdDaemon,TelcoConnectionMonitor telcoMonitor){
        super(telcoMonitor);
        this.ussdDaemon = ussdDaemon;
        this.server = server;
        sessionname = "ServiceApplicationInitiatesSession";
        
        try{
            thirdPartyReader = new BufferedReader(new InputStreamReader(server.getInputStream()));
            thirdPartyWriter = new PrintWriter(server.getOutputStream());
        }catch(IOException ioex){
            System.out.println("IOException Error : " +ioex.getMessage());
        }

    }

    public void HandleUSSDResponse(String sessionID,String userrequest){
        utils = new Utilities();
       String userdata = userrequest.substring(96) ;
       String userdataInHex = utils.convertHexToString(userdata);
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("userdata", userdataInHex);
        jsonobject.put("msisdn", this.msisdn);
        jsonobject.put("complete", this.isEndSession() ? true:false);
        thirdPartyWriter.println(jsonobject.toString());
        thirdPartyWriter.flush();
        if(this.isEndSession()){
            EndSession();
        }


    }

   
    public void run() {
        while(keepRunning){
            try{
                String thirdPartyStr = thirdPartyReader.readLine();
                System.out.println("Received : " +thirdPartyStr);
                JSONObject jsonobject = (JSONObject) PARSER.parse(thirdPartyStr);
                msisdn = (String)jsonobject.get("msisdn");
                String msisdnInHex = utils.convertStringToHex(msisdn);
                String userdata = (String)jsonobject.get("userdata");
                String userdataInHex = utils.convertStringToHex(userdata);

                
                boolean complete = (Boolean)jsonobject.get("endofsession");                

                byte[] outputdata = null;

                if(complete){
                    
                }
                else{

                }

                System.out.println("outputdata ==> " + outputdata);

                if(ussdDaemon.sessionList.containsKey("msisdn")){
                    ussdDaemon.writeMsgToServer(outputdata,telcoMonitor );
                }
                else{
                    sessionID = ussdDaemon.getSessionID(outputdata, telcoMonitor);
                    ussdDaemon.sessionList.put(msisdn, this);
                    System.out.println("Added " + msisdn + "to sessionList");

                }

                if(complete){
                    EndSession();
                }
            }catch(IOException ioex){
                keepRunning = false;
                break;
            }
            catch(ParseException jsonex){
                System.out.println("JSONException : " +jsonex);
                keepRunning = false;
                break;
            }
        }
    }

    @Override
    public void EndSession(){
        try{
            keepRunning = false;
            thirdPartyReader.close();
            thirdPartyWriter.close();
            server.close();
            super.EndSession();
        }catch(IOException ioex){
            System.out.println("Service Application end the session");
        }
    }



}
