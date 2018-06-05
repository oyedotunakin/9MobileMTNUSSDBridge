/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jodao.etisalattextng.ussddaemon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author ayeola
 */
public class UssdLog {


    public UssdLog(){

    }

        public void log(String eventString) {
        FileWriter writer;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            dateFormat.setTimeZone(TimeZone.getTimeZone("Africa/lagos"));
            Date currentDate = Calendar.getInstance().getTime();
            File directory = new File("USSDLogs");
            File outputFile = new File(directory, "USSDLogs_" + dateFormat.format(currentDate) + ".txt");
            writer = new FileWriter(outputFile, true);
            DateFormat format = DateFormat.getDateTimeInstance();
            format.setTimeZone(TimeZone.getTimeZone("Africa/Lagos"));
            String outputString = format.format(currentDate) + "\t" + eventString + "\n";

            writer.write(outputString);
            writer.flush();
            writer.close();


        } catch (IOException ex) {
            System.out.println("USSDLog Error : " + ex.getMessage());
        }
    }

   
    protected void sendSMS(String StrValue) {
    }
}
