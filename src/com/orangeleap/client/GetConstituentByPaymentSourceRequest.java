
package com.orangeleap.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetConstituentByPaymentSourceRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetConstituentByPaymentSourceRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paymentsource" type="{http://www.orangeleap.com/orangeleap/services2.0/}paymentSource"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetConstituentByPaymentSourceRequest", propOrder = {
    "paymentsource"
})
public class GetConstituentByPaymentSourceRequest {

    @XmlElement(required = true)
    protected PaymentSource paymentsource;

    /**
     * Gets the value of the paymentsource property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentSource }
     *     
     */
    public PaymentSource getPaymentsource() {
        return paymentsource;
    }

    /**
     * Sets the value of the paymentsource property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentSource }
     *     
     */
    public void setPaymentsource(PaymentSource value) {
        this.paymentsource = value;
    }

}