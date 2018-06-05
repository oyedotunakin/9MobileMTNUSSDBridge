
package org.csapi.schema.parlayx.ussd.send.v1_0.local;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.csapi.schema.parlayx.ussd.send.v1_0.local package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SendUssd_QNAME = new QName("http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local", "sendUssd");
    private final static QName _SendUssdResponse_QNAME = new QName("http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local", "sendUssdResponse");
    private final static QName _SendUssdAbort_QNAME = new QName("http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local", "sendUssdAbort");
    private final static QName _SendUssdAbortResponse_QNAME = new QName("http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local", "sendUssdAbortResponse");
    private final static QName _SendUssdSwitch_QNAME = new QName("http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local", "sendUssdSwitch");
    private final static QName _SendUssdSwitchResponse_QNAME = new QName("http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local", "sendUssdSwitchResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.csapi.schema.parlayx.ussd.send.v1_0.local
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendUssd }
     * 
     */
    public SendUssd createSendUssd() {
        return new SendUssd();
    }

    /**
     * Create an instance of {@link SendUssdResponse }
     * 
     */
    public SendUssdResponse createSendUssdResponse() {
        return new SendUssdResponse();
    }

    /**
     * Create an instance of {@link SendUssdAbort }
     * 
     */
    public SendUssdAbort createSendUssdAbort() {
        return new SendUssdAbort();
    }

    /**
     * Create an instance of {@link SendUssdAbortResponse }
     * 
     */
    public SendUssdAbortResponse createSendUssdAbortResponse() {
        return new SendUssdAbortResponse();
    }

    /**
     * Create an instance of {@link SendUssdSwitch }
     * 
     */
    public SendUssdSwitch createSendUssdSwitch() {
        return new SendUssdSwitch();
    }

    /**
     * Create an instance of {@link SendUssdSwitchResponse }
     * 
     */
    public SendUssdSwitchResponse createSendUssdSwitchResponse() {
        return new SendUssdSwitchResponse();
    }

    /**
     * Create an instance of {@link NamedParameterList }
     * 
     */
    public NamedParameterList createNamedParameterList() {
        return new NamedParameterList();
    }

    /**
     * Create an instance of {@link NamedParameter }
     * 
     */
    public NamedParameter createNamedParameter() {
        return new NamedParameter();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendUssd }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local", name = "sendUssd")
    public JAXBElement<SendUssd> createSendUssd(SendUssd value) {
        return new JAXBElement<SendUssd>(_SendUssd_QNAME, SendUssd.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendUssdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local", name = "sendUssdResponse")
    public JAXBElement<SendUssdResponse> createSendUssdResponse(SendUssdResponse value) {
        return new JAXBElement<SendUssdResponse>(_SendUssdResponse_QNAME, SendUssdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendUssdAbort }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local", name = "sendUssdAbort")
    public JAXBElement<SendUssdAbort> createSendUssdAbort(SendUssdAbort value) {
        return new JAXBElement<SendUssdAbort>(_SendUssdAbort_QNAME, SendUssdAbort.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendUssdAbortResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local", name = "sendUssdAbortResponse")
    public JAXBElement<SendUssdAbortResponse> createSendUssdAbortResponse(SendUssdAbortResponse value) {
        return new JAXBElement<SendUssdAbortResponse>(_SendUssdAbortResponse_QNAME, SendUssdAbortResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendUssdSwitch }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local", name = "sendUssdSwitch")
    public JAXBElement<SendUssdSwitch> createSendUssdSwitch(SendUssdSwitch value) {
        return new JAXBElement<SendUssdSwitch>(_SendUssdSwitch_QNAME, SendUssdSwitch.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendUssdSwitchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csapi.org/schema/parlayx/ussd/send/v1_0/local", name = "sendUssdSwitchResponse")
    public JAXBElement<SendUssdSwitchResponse> createSendUssdSwitchResponse(SendUssdSwitchResponse value) {
        return new JAXBElement<SendUssdSwitchResponse>(_SendUssdSwitchResponse_QNAME, SendUssdSwitchResponse.class, null, value);
    }

}
