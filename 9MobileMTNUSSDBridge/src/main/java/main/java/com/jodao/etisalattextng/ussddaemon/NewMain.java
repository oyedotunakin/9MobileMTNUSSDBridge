/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jodao.etisalattextng.ussddaemon;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author ayeola
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        // TODO code application logic here
//        String hexString1 = "1f0000006700000000000000ffffffffffffffff7465737400000000000000";
//        String hexString2 = "1400000084000000000000000000000000000000";
//        String hexString3 = "140000008300000000000000ffffffffffffffff";
//        Utilities utils = new Utilities();
//        byte[] byteArray = utils.hexStringToByteArray(hexString3);
//        System.out.println("HexToString : " + new String(byteArray));//+ utils.fromHex(hexString2));
//
//
//        int commandLength = 20;
//        int commandId = 0x00000083;
//        int commandStatus = 0;
//        int senderCB = 0xFFFFFFFF;
//        int receiverCB = 0xFFFFFFFF;
//
//         String username = "cellul";
//        String password = "cellul";
//        String phoneNumber = "2348153652377";
//        String UssdString = "*124#";
//
//        String user = utils.convertStringToHex(username);
//        String pass = utils.convertStringToHex(password);
//        String phone = utils.convertStringToHex(phoneNumber);
//        String ussdStr = utils.convertStringToHex(UssdString);
//
//        //Ussd HandShake Request
//        String handShakeRequest =  "140000008300000000000000ffffffffffffffff";
//
//        //Ussd Hand Shake Response
//        String handShakeResponse = "140000008400000000000000ffffffffffffffff";
//
//        //Ussd Bind Request
//
//        String ussdBindRequest = "390000006500000000000000ffffffffffffffff63656c6c756c000000000063656c6c756c0000000000000000000000000000000010000000";
//
//        //Ussd Bind Response
//        String ussdBindResponse = "1f0000006700000000000000ffffffffffffffff63656c6c756c0000000000";
//        //1f0000006700000000000000ffffffffffffffff63656c6c756c0000000000
//
//        //Ussd UnBind Request
//
//        String UssdUnBindRequest = "140000006600000000000000ffffffffffffffff";
//        //Ussd UnBind Response
//
//        String UssdUnBindResponse = "140000006800000000000000ffffffffffffffff";
//
//        String serviceCodeHex = utils.convertStringToHex("360")+"00";
//
//        String newUssdStr = utils.convertStringToHex("*360");
//
//        System.out.println("New Ussd String: " +newUssdStr);
//
//        System.out.println("Hexadecimal Value To String Value : " + utils.fromHex("33363000"));
//
//
//
//        System.out.println("Service Code : " +serviceCodeHex+"00");
//
//        StringBuilder buffer = new StringBuilder();
//        buffer.append("1. Stock Price");
//        buffer.append("2. Stock Analysis");
//        buffer.append("3. City Life");
//        buffer.append("4. Lottery");
////        buffer.append("5. Living Handbook");
////        buffer.append("6. Fashion Life");
////        buffer.append("7. Entertainment");
////        buffer.append("8. Public Information");
////        buffer.append("9. Wonderful World");
//
//        String ussdInformationHex = utils.stringToHex(buffer.toString());
//
//        String atHex = utils.convertStringToHex("@");
//
//        System.out.println("@Hex : " +atHex);
//
//
//        String ussdStringHex = atHex;//+serviceCodeHex+atHex+ussdInformationHex;
//
//        System.out.println("Ussd String : " +ussdStringHex);
//        //Ussd Begin Request Message
//        String ussdBegin = "de0000006f0000000000000001000003ffffffff2010"+phone+"00000000000000000000"+serviceCodeHex+"0f"+ussdStringHex+"";
//
//        String ussdBegiMO =  "350000006f000000000000002900AB12ffffffff2010"+phone+"00000000000000000000"+serviceCodeHex+"44"+newUssdStr;
//        System.out.println("UssdBegin Request when a Mobile phone Initiate Session : " +ussdBegiMO);
//        System.out.println("Ussd Begin Size : " + utils.hexStringToByteArray(ussdBegiMO).length);
//
//        System.out.println("Ussd Begin : " +ussdBegin);
//
//        System.out.println("UssdBegin : " +utils.hexStringToByteArray(ussdBegin));
//
//        System.out.println("UssdBegin Size : " +utils.hexStringToByteArray(ussdBegin).length);
//
//
//        String ussdContinue = "31000000700000000000000001000003010000032030"+phone+"00000000000000000000"+serviceCodeHex+"44"+UssdString+"";
//
//        String msisdn = null;
//        //String serviceCodeHex = utils.convertStringToHex("360")+"00";
//       String ussdContinue1 = "de0000007000000000000000010000052900AB122010"+msisdn+"00000000000000000000"+serviceCodeHex+"0f"+UssdString;
//
//       String ussdContinue_response = "3100000070000000000000002900AB120100000530"+msisdn+"00000000000000000000"+serviceCodeHex+"44"+UssdString;
//
//       String end_response = "800000007100000000000000010000052900AB122030"+phone+"00000000000000000000"+serviceCodeHex+"0f"+UssdString;
//
       Integer intValue = new Integer(10);
        String hexValue = Integer.toHexString(intValue);
//
        //System.out.println("Command Length : " +hexValue);

        System.out.println("HexadecimalToString : "+new Utilities().HexadecimalToInteger("10"));
        
//
//
//        String statusValue = Integer.toHexString(0);
//
//        System.out.println("Command Status : " +statusValue);
//
//        String testRequest = "460000006f0000000000000085576419ffffffff20013233343831383837323034333800000000000000002a333630000000000000";
//
//        String msisdntest = testRequest.substring(44, 70);
//
//      String testPhone =  utils.fromHex(msisdntest);
//        System.out.println("Test Phone1 : " + testPhone );
//
//        String testServiceCode = testRequest.substring(86, 94);
//
//        System.out.println("Service Code : " + utils.fromHex(testServiceCode)+"#");
//
//        String sessionIdTest = testRequest.substring(24, 32);
//
//        System.out.println("SessionID : " + sessionIdTest);
//
//        String ussdAbort = "1400000072000000360000000100001301000007";
//
//
//
//
////        int value = utils.HexadecimalToInteger("46");
////        System.out.println("Integer Value : " +value);
//
//
//        System.out.println("User Data : " + utils.fromHex("33363000"));
//
//        String request = "460000006f0000000000000004c28b19ffffffff20013233343831383837323034333800000000000000002a333630000000000000";
//
//        String userdataInHex = request.substring(86, 94);
//
//        String userdata1 = utils.fromHex(userdataInHex);
//
//        System.out.println("Perform Test : " +userdata1);
//
//
//        System.out.println("Hexadecimal username : " +user);
//        System.out.println("Hexadecimal password : " +pass);
//        System.out.println("Hexadecimal phone : " +phone);
//        System.out.println("Hexadecimal Ussd String : " +ussdStr);
//
//        String menu =   "Cellulant Wallet Menu\n1.To Get Balance\n2.Mini Statement\n3.Transfer Money\n4.Buy Airtime\n5.Pay DSTV\n6.Withdrawal\n7.Change Pin\n8.Exit";
//    String menuHex = utils.convertStringToHex(menu);
//
//    System.out.println("USSD Menu : " +menuHex);
//
//    int menuSize = utils.hexStringToByteArray(menuHex).length;
//
//    System.out.println("USSD Menu Size : " +menuSize);
//
//    String msisdnInHex = utils.convertStringToHex(phoneNumber);
//
//    String sessionID = "e6ca8c19";
//
//    String ussdContinueTest = "de0000007000000000000000e6ca8c19e6ca8c192001"+msisdnInHex+"0000000000000000333630000f"+menuHex+"00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//
//    int ussdContinueBytes = utils.hexStringToByteArray(ussdContinueTest).length;
//
//    System.out.println("USSD Continue Request Message Menu Size: " +ussdContinueBytes);
//
//    String USSDOPType = Integer.toHexString(3);
//
//   System.out.println("USSDOPType : " +USSDOPType );
//
//
//   String endresponseInHex = "800000007100000000000000"+sessionID+""+sessionID+"2003"+msisdnInHex+"0000000000000000333630000f"+menuHex;
//
//    System.out.println("USSD End Requests : " +utils.hexStringToByteArray(endresponseInHex).length);
//        //System.out.println("Command Status : " + utils.fromHex("e2070000"));
//
//    String ussdContinueTest1 = "df0000007000000000000000"+sessionID+""+sessionID+"2001"+msisdnInHex+"0000000000000000333630000f"+menuHex+"0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//
//    System.out.println("USSD Continue Request Message Size : " +utils.hexStringToByteArray(ussdContinueTest1).length);
//
//        //String hexString = "43656c6c756c616e742057616c6c6574204d656e75313e546f204765742042616c616e6365323e4d696e692053746174656d656e74333e5472616e73666572204d6f6e6579343e4275792041697274696d65353e5061792044535456363e5769746864726177616c373e4368616e67652050696e383e45786974000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//
//        String hexString = "43656c6c756c616e742057616c6c6574204d656e75a313e546f204765742042616c616e6365a323e4d696e692053746174656d656e74a333e5472616e73666572204d6f6e6579a343e4275792041697274696d65a353e5061792044535456a363e5769746864726177616ca373e4368616e67652050696ea383e45786974000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//        System.out.println("Test Code : " + utils.convertHexToString(hexString));
//
//        String userRequest = "460000006f00000000000000de93b219ffffffff20013233343830393036323239333900000000000000002a333630000000000000";
//        String UssdOpType = userRequest.substring(42, 44);
//
//        System.out.println("USSDOPType : " +UssdOpType);
//
//        int ussdOptype = Integer.parseInt(UssdOpType, 16);
//
//        System.out.println("Operation Type : " +ussdOptype);
//
//        //System.out.println("Convert HexToInteger : " +utils.HexadecimalToInteger("42000000"));
//
//
//        String protocol = "43656c6c756c616e742057616c6c6574204d656e75a312e546f204765742042616c616e6365a322e4d696e692053746174656d656e74a332e5472616e73666572204d6f6e6579a342e4275792041697274696d65a352e5061792044535456a362e5769746864726177616ca372e4368616e67652050696ea382e45786974";
//
//        String protocol2 = "43656c6c756c616e742057616c6c6574204d656e75";
//        System.out.println("USSD String : " +utils.convertHexToString(protocol2));
//
//        String getBalance1 = "312e546f204765742042616c616e6365";
//
//        System.out.println("Get Balance : " +utils.convertHexToString(getBalance1));
//
//        System.out.println("New Line In Hex : " + utils.convertHexToString("a"));
//
//
//        String ussdcontinue = "de0000007000000000000000ffffffff52e4f51920013233343831383837323034333800000000000000002a33363000000000000000000000000000000000004443656c6c756c616e742057616c6c6574204d656e75a313e546f204765742042616c616e6365a323e4d696e692053746174656d656e74a333e5472616e73666572204d6f6e6579a343e4275792041697274696d65a353e5061792044535456a363e5769746864726177616ca373e4368616e67652050696ea383e4578697400000000000000000000000000000000000000000000000000000000000000";
//
//        String ussdMenu = ussdcontinue.substring(129);
//
//        System.out.println("Menu : " + utils.convertHexToString(ussdMenu));
//
//        String heading = ussdcontinue.substring(130, 172);
//
//        System.out.println("heading : " +utils.convertHexToString(heading));
//
//        String getBalance = ussdcontinue.substring(173, 205);
//
//        System.out.println("Get Balance : " +utils.convertHexToString(getBalance));
//
//        String miniStatement = ussdcontinue.substring(206, 238);
//
//        System.out.println("MiniStatement : " +utils.convertHexToString(miniStatement));
//
//        String transferMoney = ussdcontinue.substring(239, 271);
//
//        System.out.println("Transfer Money : " +utils.convertHexToString(transferMoney));
//
//        String buyAirtime = ussdcontinue.substring(272, 298);
//
//        System.out.println("Buy Airtime : " +utils.convertHexToString(buyAirtime));
//
//        String payDstv = ussdcontinue.substring(299, 319);
//
//        System.out.println("Pay DSTV : " + utils.convertHexToString(payDstv));
//
//        String withdrawal = ussdcontinue.substring(320, 344);
//
//        System.out.println("Withdrawal : " +utils.convertHexToString(withdrawal));
//
//        String changePin = ussdcontinue.substring(345, 369);
//
//        System.out.println("Change Pin : " + utils.convertHexToString(changePin));
//
//        String Exit = ussdcontinue.substring(370);
//
//        System.out.println("Exit : " +utils.convertHexToString(Exit));
//
//        System.out.println("DotInHex : " + utils.convertStringToHex("."));
//
//        System.out.println("> In Hex : " + utils.convertStringToHex(">"));
//
//
//        int myNumber = 654321;
//       // String stringNumber = String.format("%09d", myNumber);
//       // System.out.println("Number with leading zeros : " +stringNumber);
//
//        Padding pad = new Padding();
//        String str = "welcome";
//        String padStr = "0";
//        int size = 12;
//
//        System.out.println("Padding : " +pad.rightPad(str, size, padStr));
//
//        String getLength = "de0000007000000000000000ffffffff3f94fc1920013233343831383837323034333800000000000000002a33363000000000000000000000000000000000004443656c6c756c616e742057616c6c6574204d656e75312e546f204765742042616c616e6365322e4d696e692053746174656d656e74332e5472616e73666572204d6f6e6579342e4275792041697274696d65352e5061792044535456362e5769746864726177616c372e4368616e67652050696e382e45786974";
//        System.out.println("getLength : " +getBalance.length());
//
//
//        //System.out.println("Pay Dstv : " +utils.fromHex("456e74657220416d6f756e74a3e").replaceAll("a", ""));
//
//        String tests = "456e74657220416d6f756e74a3e".replaceAll("a", "");
//
//        System.out.println("Test : " +utils.convertStringToHex("test"));
//
//        String ussdResponse = "43656c6c756c616e742057616c6c6574204d656e75a313e546f204765742042616c616e6365a323e4d696e692053746174656d656e74a333e5472616e73666572204d6f6e6579a343e4275792041697274696d65a353e5061792044535456a363e5769746864726177616ca373e4368616e67652050696ea383e45786974000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//        String nextLine = utils.convertStringToHex("/n");
//
//        System.out.println("Netx Line In Hex : " +nextLine);
//        String response = ussdResponse.replaceAll("a", "0a");
//
//        System.out.println(response);
//
//        System.out.println("Response : " +utils.convertHexToString(response));
//        System.out.println("Next Line  : " +utils.convertHexToString("a"));
//
//
//        String testStr = "4200000070000000000000005192121affffffff2003323334383138383732303433380000000000000000000000000000000000000000000000000000000000443700000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//       String result = utils.convertHexToString(testStr.substring(130));
//        System.out.println("Test String : " + result.trim() + "welcome");
//
//        String testValue = "43656c6c756c616e742057616c6c6574204d656e750a312e546f204765742042616c616e63650a322e4d696e692053746174656d656e740a332e5472616e73666572204d6f6e65790a342e4275792041697274696d650a352e50617920445354560a362e5769746864726177616c0a372e4368616e67652050696e0a382e45786974000000000000000000000000000000000000000000000000000000";
//
//        System.out.println("testValue : " +utils.convertHexToString(testValue));
//
//     System.out.println("End Session : " +utils.HexadecimalToInteger("80000000"));
//
//     System.out.println("Airtel Password : " +utils.convertHexToString("63656c6c756c616e7440313233"));
//
//     System.out.println("USSDString : " + utils.convertHexToString("2a3336302a3230302300000"));
//
//     String test = "3037300000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//
//     System.out.println("View This : " +utils.convertHexToString(test));
//
//     HashMap<String,String> testService = new HashMap<String, String>();
//     testService.put("firstname", "Ayeola");
//     testService.put("lastname", "Abiodun");
//
//     dataAccess dB = new dataAccess();
//
//     //String logServices = dB.LogServices(sessionID + "daf", serviceCodeHex + "mnaj");
//
//     //System.out.println("Response from local dB : " +logServices);
//
//     //String  testService1 = dB.fetchServices(sessionID);
//
//     //System.out.println("Service Code Test : " + testService1);
//
//
//
//     String msisdn1 = "08153652377";
//     String welcome = "*360#";
//     HashMap<String,Users> test1 = new HashMap<String, Users>();
//     Users user1 = new Users();
//
//     user1.setMsisdn(msisdn1);
//     user1.setUssdString(welcome);
//
//     test1.put("user", user1);
//
//
//
//     JSONObject fromclient = new JSONObject(test1);
//     JSONObject jsonobject = fromclient.getJSONObject("user");
//
//     System.out.println("jsonobject : " + jsonobject);
//
//     System.out.println("mobilenumber : " + jsonobject.getString("msisdn"));
//     System.out.println("ussdstring : " + jsonobject.getString("ussdString"));
//
//
//     System.out.println("New Test : " +utils.convertHexToString("80000015"));
//
//
//    System.out.println("Convert MSISDN from Hexadecimal to Readable String : " + utils.convertHexToString("32333438303530303031303039"));
//

        
        

        SME sme1 = new SME("cellulantwallet","*360#");
        SME sme2 = new SME("uwiniwin","*360*200#");
        SME sme3 = new SME("prepaygo","*360*100#");
        SME sme4 = new SME("boa","*360*1#");
        SME sme5 = new SME("kaizen","*360*123#");


//        ArrayList listService = new ArrayList();
//        HashMap map = new HashMap();
//        listService.add(0, "*360#");
//        listService.add(1, "*360*200#");
//        listService.add(2, "*360*100#");
//        listService.add(3, "*360*1#");
//        listService.add(4, "*360*123#");
//
//        Iterator iterator = listService.listIterator();

        SessionMOHandler smh = new SessionMOHandler(null, null, null);

        //while(iterator.hasNext()){
            //System.out.println("List Services : "+iterator.next());
        //}

        ArrayList listService = smh.allServices();
        String servicecode = "*360*123#";
            if(listService.contains(servicecode)){
               System.out.println("Service Code : " +listService.indexOf(servicecode));
            }else{
             System.out.println("Invalid Service Code");
            }

     
    }



}
