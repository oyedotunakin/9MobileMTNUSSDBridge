/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jodao.etisalattextng.smpp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author akin.oyedotun
 */
public class HTTPTransport implements SOAPHandler<SOAPMessageContext> {

    static final String DATE_TEMPLATE = "yyyyMMddHHmmss";
    static final SimpleDateFormat sf = new SimpleDateFormat(DATE_TEMPLATE);

    private SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
        return message;
    }

    public static String send(HashMap requestMap) {
        String serviceCode = (String) requestMap.get("service_code");
        String text = (String) requestMap.get("message");
        String from = (String) requestMap.get("msisdn");
        int msgType = (int) requestMap.get("msg_type");
        int ussdOpType = (int) requestMap.get("ussd_op_type");
        String senderCb = (String) requestMap.get("sender_cb");
        String receiverCb = (String) requestMap.get("receiver_cb");
//        String serviceId = (String) requestMap.get("serviceId");
        String wcsUrl = (String) requestMap.get("wcs_url");
        String traceUniqueID = String.valueOf(Calendar.getInstance().getTimeInMillis());// "504028755141801161314456568003"; 
        String rs = null, smsg
                = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "   <soapenv:Header>\n"
                + "      <ns1:NotifySOAPHeader xmlns:ns1=\"http://www.huawei.com.cn/schema/common/v2_1\">\n"
                + "         <ns1:spId>2340110001439</ns1:spId>\n"
                + "         <ns1:serviceId>234012000014814</ns1:serviceId>\n"
                + "         <ns1:timeStamp>" + sf.format(new Date()) + "</ns1:timeStamp>\n"
                + "         <ns1:linkid>" + senderCb + "</ns1:linkid>\n"
                + "         <ns1:traceUniqueID>" + traceUniqueID + "</ns1:traceUniqueID>\n"
                + "         <ns1:OperatorID>23401</ns1:OperatorID>\n"
                + "      </ns1:NotifySOAPHeader>\n"
                + "   </soapenv:Header>\n"
                + "   <soapenv:Body>\n"
                + "      <ns2:notifyUssdReception xmlns:ns2=\"http://www.csapi.org/schema/parlayx/ussd/notification/v1_0/local\">\n"
                + "         <ns2:msgType>" + msgType + "</ns2:msgType>\n"
                + "         <ns2:senderCB>" + senderCb + "</ns2:senderCB>\n"
                + "         <ns2:receiveCB>" + receiverCb + "</ns2:receiveCB>\n"
                + "         <ns2:ussdOpType>" + ussdOpType + "</ns2:ussdOpType>\n"
                + "         <ns2:msIsdn>" + from + "</ns2:msIsdn>\n"
                + "         <ns2:serviceCode>"+serviceCode+"</ns2:serviceCode>\n"
                + "         <ns2:codeScheme>68</ns2:codeScheme>\n"
                + "         <ns2:ussdString>" + text + "</ns2:ussdString>\n"
                + "      </ns2:notifyUssdReception>\n"
                + "   </soapenv:Body>\n"
                + "</soapenv:Envelope>";

        try {
            // Create the connection
            SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
            SOAPConnection conn = scf.createConnection();
//            SOAPFactory sOAPFactory = SOAPFactory.newInstance();

            // Create message
            MessageFactory mf = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);//.newInstance();
            SOAPMessage msg = mf.createMessage();

            // Object for message parts
            SOAPPart sp = msg.getSOAPPart();
            StringReader srd = new StringReader(smsg);
            StreamSource prepMsg = new StreamSource(srd);
            sp.setContent(prepMsg);
            MimeHeaders headers = msg.getMimeHeaders();
            headers.addHeader("User-Agent", "9mobile");

            // Save message
            msg.saveChanges();

            
            // View input
            System.out.println("\n Soap request:\n");
            msg.writeTo(System.out);
            System.out.println();

            // Send
//            String urlval = "http://41.206.4.162:8310/SendSmsService/services/SendSms/";
            SOAPMessage rp = conn.call(msg, wcsUrl);

            // View the output
            System.out.println("XML response\n");

            // Create transformer
            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer();

            // Get reply content
            Source sc = rp.getSOAPPart().getContent();

            // Set output transformation
            StreamResult result = new StreamResult(System.out);
            rs = result.toString();
            tf.transform(sc, result);
            System.out.println();
            // Close connection
            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public static void main(String[] args) {
        System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");

        HashMap h = new HashMap();
        h.put("sender_cb", "7785454");
        h.put("msisdn", "2349091785148");
        h.put("message", "this is ");
        h.put("wcs_url", "http://127.0.0.1:8091");
        h.put("receiver_cb", "FFFFFFFF");
        h.put("msg_type", 0);
        h.put("ussd_op_type", 1);
        send(h);
    }

    @Override
    public Set<QName> getHeaders() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void close(MessageContext arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean handleFault(SOAPMessageContext arg0) {
        SOAPMessage message = arg0.getMessage();
        try {
            message.writeTo(System.out);
//            Logger.getLogger(HTTPTransport.class.getName()).log(Level.SEVERE, null, message.toString());
        } catch (SOAPException | IOException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(HTTPTransport.class.getName()).log(Level.SEVERE, null, e);
        }
        // TODO Auto-generated catch block
        return false;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext arg0) {
        SOAPMessage message = arg0.getMessage();
        boolean isOutboundMessage = (Boolean) arg0.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (isOutboundMessage) {
            System.out.println("OUTBOUND MESSAGE\n");

        } else {
            System.out.println("INBOUND MESSAGE\n");
        }
        try {
            message.writeTo(System.out);
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
