/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jodao.etisalattextng.wcs.soap;

import com.jodao.etisalattextng.ussd.ConnectClient;
import com.jodao.etisalattextng.ussd.WCSResponseWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import org.csapi.schema.parlayx.ussd.send.v1_0.local.NamedParameterList;
import org.json.simple.parser.ParseException;

/**
 *
 * @author akin.oyedotun
 */
//@WebService(serviceName = "SendUssdService", portName = "SendUssd", endpointInterface = "org.csapi.wsdl.parlayx.ussd.send.v1_0.service.SendUssd", targetNamespace = "http://www.csapi.org/wsdl/parlayx/ussd/send/v1_0/service"/**
//* , wsdlLocation = "WEB-INF/wsdl/SendUssd/osg_ussd_send_service_1_0.wsdl"*
// */
//)
@WebService(name = "SendUssdServices", targetNamespace = "http://www.csapi.org/wsdl/parlayx/ussd/send/v1_0/local")
public class SendUssd {

    @WebMethod
    @WebResult(name = "result", targetNamespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local")
    @RequestWrapper(localName = "sendUssd", targetNamespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local", className = "org.csapi.schema.parlayx.ussd.send.v1_0.local.SendUssd")
    @ResponseWrapper(localName = "sendUssdResponse", targetNamespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local", className = "org.csapi.schema.parlayx.ussd.send.v1_0.local.SendUssdResponse")
    public String sendUssd(
            @WebParam(name = "msgType", targetNamespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local") int msgType,
            @WebParam(name = "senderCB", targetNamespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local") String senderCB,
            @WebParam(name = "receiveCB", targetNamespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local") String receiveCB,
            @WebParam(name = "ussdOpType", targetNamespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local") int ussdOpType,
            @WebParam(name = "msIsdn", targetNamespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local") String msIsdn,
            @WebParam(name = "serviceCode", targetNamespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local") String serviceCode,
            @WebParam(name = "codeScheme", targetNamespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local") int codeScheme,
            @WebParam(name = "ussdString", targetNamespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local") String ussdString,
            @WebParam(name = "endPoint", targetNamespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local") String endPoint,
            @WebParam(name = "extenionInfo", targetNamespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local") NamedParameterList extenionInfo) {

        Logger.getLogger(SendUssd.class.getName()).log(Level.INFO, "Send USSD Request recieved :: \n {0}/{1}/{2}/{3}/{4}", new Object[]{ussdString, msgType, senderCB, msIsdn, ussdOpType});

        HashMap<String,String> h = new HashMap();
        h.put("sender_cb", senderCB);
        h.put("msisdn", msIsdn);
        h.put("message", ussdString);
        h.put("msg_type", String.valueOf(msgType));
        h.put("ussd_op_type",String.valueOf(ussdOpType));
        WCSResponseWriter.getInstance().getResponseQ().add(h);
        Logger.getLogger(SendUssd.class.getName()).log(Level.INFO, "Just added this {0}", WCSResponseWriter.getInstance().getResponseQ().size());
////        try {
//        ConnectClient.getInstance().setWcsResponse(ussdString);
//            ConnectClient.getInstance().respondToUSSDC(msgType, senderCB, msIsdn,"Testing again in English");// ussdString);
//        } catch (ParseException ex) {
//            Logger.getLogger(SendUssd.class.getName()).log(Level.SEVERE, null, ex);
//        }

        return "0";
    }
}
