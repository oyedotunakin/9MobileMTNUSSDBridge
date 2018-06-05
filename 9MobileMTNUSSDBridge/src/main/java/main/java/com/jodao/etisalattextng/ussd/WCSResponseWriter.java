/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jodao.etisalattextng.ussd;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

/**
 *
 * @author akin.oyedotun
 */
public class WCSResponseWriter extends Thread {

    public LinkedList<HashMap> responseQ;

    public LinkedList<HashMap> getResponseQ() {
        return responseQ;
    }

    public void setResponseQ(LinkedList<HashMap> responseQ) {
        this.responseQ = responseQ;
    }
    private Socket clientSocket;
    private static WCSResponseWriter wCSResponseWriter;

    /**
     *
     * @param clientSocket
     */
    public WCSResponseWriter(Socket clientSocket) {
        responseQ = new LinkedList<>();
        this.clientSocket = clientSocket;
    }

    public WCSResponseWriter() {
        responseQ = new LinkedList<>();
    }

    public static WCSResponseWriter getInstance() {
        if (wCSResponseWriter == null) {
            wCSResponseWriter = new WCSResponseWriter();
        }
        return wCSResponseWriter;
    }

    public static WCSResponseWriter getInstance(Socket clientSocket) {
        if (wCSResponseWriter == null) {
            wCSResponseWriter = new WCSResponseWriter(clientSocket);
        }
        return wCSResponseWriter;
    }

    @Override
    public void run() {
        Logger.getLogger(ConnectClient.class.getName()).info("WCS Response Writer running...");
        while (true) {
            /* if (clientSocket == null) {
                System.out.print("Socket is null....");
                continue;
            }*/
//            Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Connection Status : {0}", clientSocket.isConnected());
           
             
            while (responseQ.size() > 0) {// && clientSocket != null && clientSocket.isConnected()) {
                HashMap<String, String> responseMap = responseQ.removeFirst();
                Logger.getLogger(WCSResponseWriter.class.getName()).log(Level.INFO, "Picked up a message {0}", responseMap);
                try {
                    ConnectClient.getInstance().respondToUSSDC(Integer.parseInt(responseMap.get("msg_type")), responseMap.get("sender_cb"), responseMap.get("msisdn"), responseMap.get("message"));
                } catch (ParseException ex) {
                    Logger.getLogger(WCSResponseWriter.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            try {
//                 Logger.getLogger(WCSResponseWriter.class.getName()).log(Level.INFO, "Queue size '{'0'}'{0}", responseQ.size());
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(WCSResponseWriter.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Comm length {0}", cl);
            Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "combined :{0}", response_string);

            byte[] responseInBytes = Utilities.hexStringToByteArray(response_string);
            clientSocket.getOutputStream().write(responseInBytes);
            clientSocket.getOutputStream().flush();

            Logger.getLogger(ConnectClient.class.getName()).log(Level.INFO, "Size of the response sent to the user : {0}", responseInBytes.length);

            Logger.getLogger(ConnectClient.class.getName()).info("USSD Continue response sent to the subscriber");
            //            log("USSD Continue response sent to the subscriber : " + ussdContinuePadded);
        } catch (IOException ex) {
            Logger.getLogger(ConnectClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addResponse(HashMap response) {
        Logger.getLogger(ConnectClient.class.getName()).info("Adding the message");
        responseQ.add(response);
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
}
