///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.jodao.etisalattextng.ussddaemon;
//
//import java.util.Calendar;
//import java.util.concurrent.TimeUnit;
//
///**
// *
// * @author ayeola
// */
//public class EnquiryLinkResponse extends Thread{
//
//    private long nextHandShakeTime;
//    private long terminateTime;
//    private static final int WAIT_LENGTH_SECS = 60;
//    private TelcoConnectionMonitor telcoMonitor;
//
//    public EnquiryLinkResponse(){
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.SECOND, WAIT_LENGTH_SECS);
//        nextHandShakeTime = cal.getTimeInMillis();
//        terminateTime = cal.getTimeInMillis();
//    }
//
//    public void run(){
//        while(USSDDaemon.isBound){
//            System.out.println("We are sending response back to the USSDC Server");
//        String handShakeResponse = "140000008400000000000000ffffffffffffffff";
//
//        try {
//            byte[] UssdUnBindBytes = Utilities.hexStringToByteArray(handShakeResponse);
//            USSDDaemon.writeMsgToServer(UssdUnBindBytes,telcoMonitor );
//
//            System.out.println("Size of the UssdHand Shake Response : " + UssdUnBindBytes.length);
//
//            System.out.println("Ussd Hand Shake Response Sent to the USSDC Server !!!");
//        } catch (Exception ex1) {
//            System.out.println("Hand Shake Response : " + ex1.getMessage());
//        }
//        try {
//            TimeUnit.SECONDS.sleep(60 * 10);
//        } catch (Exception ex) {
//            System.out.println("Error in termination the session : " + ex.getMessage());
//        }
//        }
//    }
//}
