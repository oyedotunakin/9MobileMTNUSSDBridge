/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jodao.etisalattextng.ussddaemon;


/**
 *
 * @author ayeola
 */
public class Users {

    private String msisdn;
    private String ussdString;

    public Users() {
    }

    public Users(String msisdn1, String ussdString) {
        this.msisdn = msisdn1;
        this.ussdString = ussdString;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getUssdString() {
        return ussdString;
    }

    public void setUssdString(String ussdString) {
        this.ussdString = ussdString;
    }
}
