
package com.orangeleap.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for pledge complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pledge">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.orangeleap.com/orangeleap/typesv3}commitment">
 *       &lt;sequence>
 *         &lt;element name="pledgeCancelDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="pledgeCancelReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pledgeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="pledgeStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="projectedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="recurring" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="pledgeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pledge", namespace = "http://www.orangeleap.com/orangeleap/typesv3", propOrder = {
    "pledgeCancelDate",
    "pledgeCancelReason",
    "pledgeDate",
    "pledgeStatus",
    "projectedDate",
    "recurring",
    "pledgeType"
})
public class Pledge
    extends Commitment
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar pledgeCancelDate;
    protected String pledgeCancelReason;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar pledgeDate;
    protected String pledgeStatus;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar projectedDate;
    protected boolean recurring;
    protected String pledgeType;

    /**
     * Gets the value of the pledgeCancelDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPledgeCancelDate() {
        return pledgeCancelDate;
    }

    /**
     * Sets the value of the pledgeCancelDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPledgeCancelDate(XMLGregorianCalendar value) {
        this.pledgeCancelDate = value;
    }

    /**
     * Gets the value of the pledgeCancelReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPledgeCancelReason() {
        return pledgeCancelReason;
    }

    /**
     * Sets the value of the pledgeCancelReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPledgeCancelReason(String value) {
        this.pledgeCancelReason = value;
    }

    /**
     * Gets the value of the pledgeDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPledgeDate() {
        return pledgeDate;
    }

    /**
     * Sets the value of the pledgeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPledgeDate(XMLGregorianCalendar value) {
        this.pledgeDate = value;
    }

    /**
     * Gets the value of the pledgeStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPledgeStatus() {
        return pledgeStatus;
    }

    /**
     * Sets the value of the pledgeStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPledgeStatus(String value) {
        this.pledgeStatus = value;
    }

    /**
     * Gets the value of the projectedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProjectedDate() {
        return projectedDate;
    }

    /**
     * Sets the value of the projectedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProjectedDate(XMLGregorianCalendar value) {
        this.projectedDate = value;
    }

    /**
     * Gets the value of the recurring property.
     * 
     */
    public boolean isRecurring() {
        return recurring;
    }

    /**
     * Sets the value of the recurring property.
     * 
     */
    public void setRecurring(boolean value) {
        this.recurring = value;
    }

    /**
     * Gets the value of the pledgeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPledgeType() {
        return pledgeType;
    }

    /**
     * Sets the value of the pledgeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPledgeType(String value) {
        this.pledgeType = value;
    }

}
