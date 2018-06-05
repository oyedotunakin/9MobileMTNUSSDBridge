
package org.csapi.schema.parlayx.ussd.send.v1_0.local;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendUssdAbort complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendUssdAbort"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="senderCB" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="receiveCB" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="abortReason" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
@XmlType(name = "sendUssdAbort", propOrder = {
    "senderCB",
    "receiveCB",
    "abortReason",
    "extenionInfo"
})
public class SendUssdAbort {

    @XmlElement(required = true)
    protected String senderCB;
    @XmlElement(required = true)
    protected String receiveCB;
    @XmlElement(required = true)
    protected String abortReason;
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
     * Gets the value of the abortReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbortReason() {
        return abortReason;
    }

    /**
     * Sets the value of the abortReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbortReason(String value) {
        this.abortReason = value;
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
