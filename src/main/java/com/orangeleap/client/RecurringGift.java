
package com.orangeleap.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for recurringGift complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="recurringGift">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.orangeleap.com/orangeleap/typesv3_1}commitment">
 *       &lt;sequence>
 *         &lt;element name="autoPay" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="nextRunDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="recurringGiftStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recurringGift", namespace = "http://www.orangeleap.com/orangeleap/typesv3_1", propOrder = {
    "autoPay",
    "nextRunDate",
    "recurringGiftStatus"
})
public class RecurringGift
    extends Commitment
{

    protected boolean autoPay;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar nextRunDate;
    protected String recurringGiftStatus;

    /**
     * Gets the value of the autoPay property.
     * 
     */
    public boolean isAutoPay() {
        return autoPay;
    }

    /**
     * Sets the value of the autoPay property.
     * 
     */
    public void setAutoPay(boolean value) {
        this.autoPay = value;
    }

    /**
     * Gets the value of the nextRunDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNextRunDate() {
        return nextRunDate;
    }

    /**
     * Sets the value of the nextRunDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNextRunDate(XMLGregorianCalendar value) {
        this.nextRunDate = value;
    }

    /**
     * Gets the value of the recurringGiftStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecurringGiftStatus() {
        return recurringGiftStatus;
    }

    /**
     * Sets the value of the recurringGiftStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecurringGiftStatus(String value) {
        this.recurringGiftStatus = value;
    }

}
