/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jodao.etisalattextng.ussddaemon;

/**
 *
 * @author ayeola
 */
public class SME {

    private String serviceCode;
    private String esmeName;

    public SME(String serviceCode, String esmeName) {
        this.serviceCode = serviceCode;
        this.esmeName = esmeName;
    }

    public String getEsmeName() {
        return esmeName;
    }

    public void setEsmeName(String esmeName) {
        this.esmeName = esmeName;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    

}
