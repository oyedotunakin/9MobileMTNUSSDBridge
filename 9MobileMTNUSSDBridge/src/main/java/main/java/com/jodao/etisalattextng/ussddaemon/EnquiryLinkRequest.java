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
//public class EnquiryLinkRequest extends Thread{
//
//    private long nextHandShakeTime;
//    private long terminateTime;
//    private static final int WAIT_LENGTH_SECS = 60;
//    private TelcoConnectionMonitor telcoMonitor;
//
//    public EnquiryLinkRequest(){
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.SECOND, WAIT_LENGTH_SECS);
//        nextHandShakeTime = cal.getTimeInMillis();
//        terminateTime = cal.getTimeInMillis();
//    }
//
//    @Override
//    public void run(){
//        while(true){
//             if (nextHandShakeTime < Calendar.getInstance().getTimeInMillis()) {
//                    System.out.println("We are sending an HandShake request");
//                    String handShake = "140000008300000000000000ffffffffffffffff";
//
//                    try {
//                        byte[] handShakeBytes = Utilities.hexStringToByteArray(handShake);
//                        USSDDaemon.writeMsgToServer(handShakeBytes, telcoMonitor);
//                    } catch (Exception ex1) {
//                        System.out.println("Broken Pipe Exception : " + ex1.getMessage());
//                    }
//                    try {
//                        TimeUnit.SECONDS.sleep(WAIT_LENGTH_SECS);
//                    } catch (Exception ex) {
//                        System.out.println("Hand Shake Error : " + ex.getMessage());
//                    }
//                }
//        }
//    }
//
//}
