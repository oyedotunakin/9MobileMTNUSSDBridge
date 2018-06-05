
package org.csapi.schema.parlayx.common.v2_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TimeMetric complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TimeMetric"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="metric" type="{http://www.csapi.org/schema/parlayx/common/v2_1}TimeMetrics"/&gt;
 *         &lt;element name="units" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeMetric", propOrder = {
    "metric",
    "units"
})
public class TimeMetric {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TimeMetrics metric;
    protected int units;

    /**
     * Gets the value of the metric property.
     * 
     * @return
     *     possible object is
     *     {@link TimeMetrics }
     *     
     */
    public TimeMetrics getMetric() {
        return metric;
    }

    /**
     * Sets the value of the metric property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeMetrics }
     *     
     */
    public void setMetric(TimeMetrics value) {
        this.metric = value;
    }

    /**
     * Gets the value of the units property.
     * 
     */
    public int getUnits() {
        return units;
    }

    /**
     * Sets the value of the units property.
     * 
     */
    public void setUnits(int value) {
        this.units = value;
    }

}
