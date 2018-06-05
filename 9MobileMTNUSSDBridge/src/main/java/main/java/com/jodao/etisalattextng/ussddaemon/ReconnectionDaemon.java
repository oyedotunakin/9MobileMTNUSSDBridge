/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jodao.etisalattextng.ussddaemon;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author ayeola
 */
public class ReconnectionDaemon extends Thread{

    private USSDDaemon ussdDaemon;

    public ReconnectionDaemon(USSDDaemon ussdDaemon){
        this.ussdDaemon = ussdDaemon;

    }
    @Override
    public void run(){
        int waitTime = 2;
        while(ussdDaemon.isTelcoConnectionDown()){
            if(waitTime < 16){
                waitTime *=2;
                try{
                    TimeUnit.SECONDS.sleep(waitTime);
                    ussdDaemon.UssdBind();

                }catch(Exception ex){
                    System.out.println("Reconnection Error " + ex.getMessage());                    
                }
            }
        }
    }


}
