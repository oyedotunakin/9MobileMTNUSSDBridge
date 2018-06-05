package com.jodao.etisalattextng.ussddaemon;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

/**
 *
 * @author Henry
 */
public final class Log
{
    public static Log l =new Log();
    String propsFile="/etc/agency.properties";
//    String propsFile="C:/Users/Akin/SmsPusherScheduler.properties";
    
    String INFO_LOG_FILE, ERROR_LOG_FILE,FATAL_LOG_FILE, MAX_LOGFILE_SIZE = "100MB",INFO_LOG_LEVEL, ERROR_LOG_LEVEL,FATAL_LOG_LEVEL,
    DB_NAME,DB_DRIVER,DB_URL,DB_USERNAME,DB_PASSWORD,GATEWAY_USERNAME,GATEWAY_PASSWORD,GATEWAYHOST,GATEWAYPORT, GATEWAY_APP_CONTEXT, dlr_url;
    public  int MAX_NUM_LOGFILES,NO_OF_THREAD,BUCKET_SIZE,PENDING_STATUS,PROCESSING_STATUS,COMPLETED_STATUS,PICKED_STATUS,FAILED_STATUS,FAILED_STATUS2,SUCCESS_STATUS,SLEEP_TIME;
    public Logger infoLog, errorLog,fatalLog;
    private Properties props;

    public Log()
    {
     loadProperties();
     initializeLoggers();
    }
    /**
     * @param args the command line arguments
     */
   public void loadProperties() {
		try {
			props = new Properties();            
                        props.load(new FileInputStream(propsFile));                     
			/* Extract the values for the values in the configuration file */
			INFO_LOG_LEVEL = props.getProperty("INFO_LOG_LEVEL");
			ERROR_LOG_LEVEL = props.getProperty("ERROR_LOG_LEVEL");
                        FATAL_LOG_LEVEL = props.getProperty("FATAL_LOG_LEVEL");
                        MAX_NUM_LOGFILES=Integer.parseInt(props.getProperty("MAX_NUM_LOGFILES"));
                        NO_OF_THREAD=Integer.parseInt(props.getProperty("NO_OF_THREAD"));
                        BUCKET_SIZE=Integer.parseInt(props.getProperty("BUCKET_SIZE"));
                        MAX_LOGFILE_SIZE=props.getProperty("MAX_LOGFILE_SIZE");			
                        INFO_LOG_FILE=props.getProperty("INFO_LOG_FILE");
                        ERROR_LOG_FILE=props.getProperty("ERROR_LOG_FILE"); 
                        FATAL_LOG_FILE=props.getProperty("FATAL_LOG_FILE");
                        DB_NAME=props.getProperty("DB_NAME"); 
                        DB_DRIVER=props.getProperty("DB_DRIVER"); 
                        DB_URL=props.getProperty("DB_URL"); 
                        DB_USERNAME=props.getProperty("DB_USERNAME"); 
                        DB_PASSWORD=props.getProperty("DB_PASSWORD");
                        GATEWAY_APP_CONTEXT=props.getProperty("GATEWAY_APP_CONTEXT");
                        GATEWAY_USERNAME=props.getProperty("GATEWAY_USERNAME");
                        GATEWAY_PASSWORD=props.getProperty("GATEWAY_PASSWORD");
                        GATEWAYHOST=props.getProperty("GATEWAYHOST");
                        GATEWAYPORT=props.getProperty("GATEWAYPORT");
                        //dlr_url=props.getProperty("dlr_url");
//                        PENDING_STATUS=Integer.parseInt(props.getProperty("PENDING_STATUS"));
//                        PICKED_STATUS=Integer.parseInt(props.getProperty("PICKED_STATUS"));
//                        PROCESSING_STATUS=Integer.parseInt(props.getProperty("PROCESSING_STATUS"));
//                        COMPLETED_STATUS=Integer.parseInt(props.getProperty("COMPLETED_STATUS"));
//                        FAILED_STATUS=Integer.parseInt(props.getProperty("FAILED_STATUS"));
//                        FAILED_STATUS2=Integer.parseInt(props.getProperty("FAILED_STATUS2"));
//                        SUCCESS_STATUS=Integer.parseInt(props.getProperty("SUCCESS_STATUS"));
//                        SLEEP_TIME=Integer.parseInt(props.getProperty("SLEEP_TIME"));
   
		} catch (IOException ioe)
                {
                    System.err.println("caught IOException attempting to loadProperties "+ ioe);
		}
	}

    public void initializeLoggers() {
        infoLog = Logger.getLogger("infoLog");
        errorLog = Logger.getLogger("errorLog");
        fatalLog=Logger.getLogger("fatalLog");

        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern("%d{yyyy MMM dd HH:mm:ss,SSS}: %p : %m%n");
        try
        {
            RollingFileAppender rfaINFO_LOG = new RollingFileAppender(layout,INFO_LOG_FILE, true);
            rfaINFO_LOG.setMaxFileSize(MAX_LOGFILE_SIZE);
            rfaINFO_LOG.setMaxBackupIndex(MAX_NUM_LOGFILES);

            RollingFileAppender rfaERROR_LOG = new RollingFileAppender(layout,ERROR_LOG_FILE, true);
            rfaERROR_LOG.setMaxFileSize(MAX_LOGFILE_SIZE);
            rfaERROR_LOG.setMaxBackupIndex(MAX_NUM_LOGFILES);

            RollingFileAppender rfaFATAL_LOG = new RollingFileAppender(layout,FATAL_LOG_FILE, true);
            rfaERROR_LOG.setMaxFileSize(MAX_LOGFILE_SIZE);
            rfaERROR_LOG.setMaxBackupIndex(MAX_NUM_LOGFILES);

            infoLog.addAppender(rfaINFO_LOG);
            errorLog.addAppender(rfaERROR_LOG);
            fatalLog.addAppender(rfaFATAL_LOG);
            
        } catch (Exception e) {
            System.err.println("initiliazeLog --- EXCEPTION THROWN WHILE CREATING A RollingFileAppender"
                    + e);
            e.printStackTrace();
        }

        infoLog.setLevel(Level.toLevel(INFO_LOG_LEVEL));
        errorLog.setLevel(Level.toLevel(ERROR_LOG_LEVEL));
        fatalLog.setLevel(Level.toLevel(FATAL_LOG_LEVEL));
    }

}
