/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jodao.etisalattextng.ussddaemon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ayeola
 */
public class SessionMonitor {
    
    private String sessionId;
    private String servicecode;
    



    public SessionMonitor(){
        
    }

    public void writeSessionIdToFile(String sessionId,String Url,String msisdn){
        FileOutputStream outStream = null;
        try {

            Properties property = new Properties();
            property.setProperty(sessionId, Url+"|"+msisdn+"|"+sessionId);

            File filename = new File("/home/abiodun/USSDGenius/sessionmonitor.properties");
            outStream = new FileOutputStream(filename,true);
            property.store(outStream, sessionId);

           // outStream.write(reqBytes, 0, reqLen);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                outStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String loadSession(String sessionId){
        FileInputStream inputFile = null;
        String sessiondata = null;
        try {
            Properties property = new Properties();
            inputFile = new FileInputStream("/home/abiodun/USSDGenius/sessionmonitor.properties");
            property.load(inputFile);
             sessiondata = property.getProperty(sessionId);

           // System.out.println("Session Data : " +sessiondata);
        } catch (IOException ex) {
            ex.printStackTrace();
        }  finally {
            try {
                inputFile.close();
            } catch (IOException ex) {
                Logger.getLogger(SessionMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return sessiondata;
    }

    public boolean validateSession(String sessionId){
        boolean exist = false;
        FileInputStream inputFile = null;
        try {
            Properties property = new Properties();
            inputFile = new FileInputStream("/home/abiodun/USSDGenius/sessionmonitor.properties");
            property.load(inputFile);
             if(property.containsKey(sessionId)){
                 exist = true;
             }else{
                exist = false;
             }

        } catch (IOException ex) {
            ex.printStackTrace();
        }  finally {
            try {
                inputFile.close();
            } catch (IOException ex) {
                Logger.getLogger(SessionMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return exist;
    }

    public void removeSession(String sessionId){
        FileInputStream inputFile = null;
        try {
            Properties property = new Properties();
            inputFile = new FileInputStream("/home/abiodun/USSDGenius/sessionmonitor.properties");
            property.load(inputFile);
             property.remove(sessionId);

        } catch (IOException ex) {
            ex.printStackTrace();
        }  finally {
            try {
                inputFile.close();
            } catch (IOException ex) {
                Logger.getLogger(SessionMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    public static void main(String[] args){
//        //new SessionMonitor().writeSessionIdToFile();
//        //new SessionMonitor().loadSession("session2");
//        new SessionMonitor().loadSession("session2");
//    }

}
