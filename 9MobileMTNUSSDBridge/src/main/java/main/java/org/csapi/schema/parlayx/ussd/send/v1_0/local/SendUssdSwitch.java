
package org.csapi.schema.parlayx.ussd.send.v1_0.local;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendUssdSwitch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendUssdSwitch"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="senderCB" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="receiveCB" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SwitchMode" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="msIsdn" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="serviceCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="codeScheme" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ussdString" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="extenionInfo" type="{http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local}NamedParameterList" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendUssdSwitch", propOrder = {
    "senderCB",
    "receiveCB",
    "switchMode",
    "msIsdn",
    "serviceCode",
    "codeScheme",
    "ussdString",
    "extenionInfo"
})
public class SendUssdSwitch {

    @XmlElement(required = true)
    protected String senderCB;
    @XmlElement(required = true)
    protected String receiveCB;
    @XmlElement(name = "SwitchMode")
    protected int switchMode;
    @XmlElement(required = true)
    protected String msIsdn;
    @XmlElement(required = true)
    protected String serviceCode;
    protected int codeScheme;
    @XmlElement(required = true)
    protected String ussdString;
    protected NamedParameterList extenionInfo;

    /**
     * Gets the value of the senderCB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderCB() {
        return senderCB;
    }

    /**
     * Sets the value of the senderCB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderCB(String value) {
        this.senderCB = value;
    }

    /**
     * Gets the value of the receiveCB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiveCB() {
        return receiveCB;
    }

    /**
     * Sets the value of the receiveCB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiveCB(String value) {
        this.receiveCB = value;
    }

    /**
     * Gets the value of the switchMode property.
     * 
     */
    public int getSwitchMode() {
        return switchMode;
    }

    /**
     * Sets the value of the switchMode property.
     * 
     */
    public void setSwitchMode(int value) {
        this.switchMode = value;
    }

    /**
     * Gets the value of the msIsdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsIsdn() {
        return msIsdn;
    }

    /**
     * Sets the value of the msIsdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsIsdn(String value) {
        this.msIsdn = value;
    }

    /**
     * Gets the value of the serviceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * Sets the value of the serviceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceCode(String value) {
        this.serviceCode = value;
    }

    /**
     * Gets the value of the codeScheme property.
     * 
     */
    public int getCodeScheme() {
        return codeScheme;
    }

    /**
     * Sets the value of the codeScheme property.
     * 
     */
    public void setCodeScheme(int value) {
        this.codeScheme = value;
    }

    /**
     * Gets the value of the ussdString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUssdString() {
        return ussdString;
    }

    /**
     * Sets the value of the ussdString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUssdString(String value) {
        this.ussdString = value;
    }

    /**
     * Gets the value of the extenionInfo property.
     * 
     * @return
     *     possible object is
     *     {@link NamedParameterList }
     *     
     */
    public NamedParameterList getExtenionInfo() {
        return extenionInfo;
    }

    /**
     * Sets the value of the extenionInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link NamedParameterList }
     *     
     */
    public void setExtenionInfo(NamedParameterList value) {
        this.extenionInfo = value;
    }

}
