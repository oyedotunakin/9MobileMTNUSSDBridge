/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jodao.etisalattextng.ussd;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author akin
 */
public class DataLayer {

    public static Connection connectDB() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ussd", "root", "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    static void logNewSession(String msisdn, String sessionID, String serviceCode) {
        Connection connection = connectDB();
        String logUser = "INSERT into Services(sessionID,serviceCode,CreatedDate,msisdn)values (?,?,now(),?)";

        try {
            PreparedStatement stat = connection.prepareStatement(logUser);
            System.out.println("Logging  service code");

            stat.setString(1, sessionID);
            stat.setString(2, serviceCode);
            stat.setString(3, msisdn);

            stat.executeUpdate();

            System.out.println("Service Code Logged Successfully");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                }
            }
        }


    }

    static String fetchService(String sessionID) {
        String service = null;
        System.out.println("fetching Service code from local database");
        String sql = "select * from Services where sessionID = ? ";

        Connection connection = connectDB();
        try {
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setString(1, sessionID);
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                service  = rs.getString("serviceCode");
            }
            connection.close();
        } catch (SQLException ex) {
           System.out.println("Select Error  : " +ex.getMessage());
        }

        return service;
    }
}
