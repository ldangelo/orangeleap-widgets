
package com.orangeleap.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="constituent" type="{http://www.orangeleap.com/orangeleap/typesv3_1}constituent"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "constituent"
})
@XmlRootElement(name = "GetConstituentByPaymentSourceResponse")
public class GetConstituentByPaymentSourceResponse {

    @XmlElement(required = true)
    protected Constituent constituent;

    /**
     * Gets the value of the constituent property.
     * 
     * @return
     *     possible object is
     *     {@link Constituent }
     *     
     */
    public Constituent getConstituent() {
        return constituent;
    }

    /**
     * Sets the value of the constituent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Constituent }
     *     
     */
    public void setConstituent(Constituent value) {
        this.constituent = value;
    }

}
