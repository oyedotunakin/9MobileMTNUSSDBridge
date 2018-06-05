
package org.csapi.schema.parlayx.ussd.send.v1_0.local;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendUssd complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendUssd"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="msgType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="senderCB" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="receiveCB" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ussdOpType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="msIsdn" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="serviceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codeScheme" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ussdString" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="endPoint" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "sendUssd", propOrder = {
    "msgType",
    "senderCB",
    "receiveCB",
    "ussdOpType",
    "msIsdn",
    "serviceCode",
    "codeScheme",
    "ussdString",
    "endPoint",
    "extenionInfo"
})
public class SendUssd {

    protected int msgType;
    @XmlElement(required = true)
    protected String senderCB;
    @XmlElement(required = true)
    protected String receiveCB;
    protected int ussdOpType;
    @XmlElement(required = true)
    protected String msIsdn;
    protected String serviceCode;
    protected int codeScheme;
    @XmlElement(required = true)
    protected String ussdString;
    protected String endPoint;
    protected NamedParameterList extenionInfo;

    /**
     * Gets the value of the msgType property.
     * 
     */
    public int getMsgType() {
        return msgType;
    }

    /**
     * Sets the value of the msgType property.
     * 
     */
    public void setMsgType(int value) {
        this.msgType = value;
    }

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
     * Gets the value of the ussdOpType property.
     * 
     */
    public int getUssdOpType() {
        return ussdOpType;
    }

    /**
     * Sets the value of the ussdOpType property.
     * 
     */
    public void setUssdOpType(int value) {
        this.ussdOpType = value;
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
     * Gets the value of the endPoint property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndPoint() {
        return endPoint;
    }

    /**
     * Sets the value of the endPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndPoint(String value) {
        this.endPoint = value;
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
