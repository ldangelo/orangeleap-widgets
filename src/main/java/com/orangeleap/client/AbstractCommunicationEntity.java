
package com.orangeleap.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for abstractCommunicationEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="abstractCommunicationEntity">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.orangeleap.com/orangeleap/typesv3_1}abstractCustomizableEntity">
 *       &lt;sequence>
 *         &lt;element name="activationStatus" type="{http://www.orangeleap.com/orangeleap/typesv3_1}activationType" minOccurs="0"/>
 *         &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="throughDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="inactive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="primary" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="receiveCorrespondence" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="seasonalEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="seasonalStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="temporaryEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="temporaryStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="undeliverable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="userCreated" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "abstractCommunicationEntity", namespace = "http://www.orangeleap.com/orangeleap/typesv3_1", propOrder = {
    "activationStatus",
    "comments",
    "effectiveDate",
    "throughDate",
    "inactive",
    "deleted",
    "primary",
    "receiveCorrespondence",
    "seasonalEndDate",
    "seasonalStartDate",
    "temporaryEndDate",
    "temporaryStartDate",
    "undeliverable",
    "userCreated"
})
@XmlSeeAlso({
    Phone.class,
    Email.class,
    Address.class
})
public abstract class AbstractCommunicationEntity
    extends AbstractCustomizableEntity
{

    protected ActivationType activationStatus;
    protected String comments;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar effectiveDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar throughDate;
    protected boolean inactive;
    protected boolean deleted;
    protected boolean primary;
    protected boolean receiveCorrespondence;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar seasonalEndDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar seasonalStartDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar temporaryEndDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar temporaryStartDate;
    protected boolean undeliverable;
    protected boolean userCreated;

    /**
     * Gets the value of the activationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ActivationType }
     *     
     */
    public ActivationType getActivationStatus() {
        return activationStatus;
    }

    /**
     * Sets the value of the activationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivationType }
     *     
     */
    public void setActivationStatus(ActivationType value) {
        this.activationStatus = value;
    }

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComments(String value) {
        this.comments = value;
    }

    /**
     * Gets the value of the effectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the value of the effectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEffectiveDate(XMLGregorianCalendar value) {
        this.effectiveDate = value;
    }

    /**
     * Gets the value of the throughDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getThroughDate() {
        return throughDate;
    }

    /**
     * Sets the value of the throughDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setThroughDate(XMLGregorianCalendar value) {
        this.throughDate = value;
    }

    /**
     * Gets the value of the inactive property.
     * 
     */
    public boolean isInactive() {
        return inactive;
    }

    /**
     * Sets the value of the inactive property.
     * 
     */
    public void setInactive(boolean value) {
        this.inactive = value;
    }

    /**
     * Gets the value of the deleted property.
     * 
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Sets the value of the deleted property.
     * 
     */
    public void setDeleted(boolean value) {
        this.deleted = value;
    }

    /**
     * Gets the value of the primary property.
     * 
     */
    public boolean isPrimary() {
        return primary;
    }

    /**
     * Sets the value of the primary property.
     * 
     */
    public void setPrimary(boolean value) {
        this.primary = value;
    }

    /**
     * Gets the value of the receiveCorrespondence property.
     * 
     */
    public boolean isReceiveCorrespondence() {
        return receiveCorrespondence;
    }

    /**
     * Sets the value of the receiveCorrespondence property.
     * 
     */
    public void setReceiveCorrespondence(boolean value) {
        this.receiveCorrespondence = value;
    }

    /**
     * Gets the value of the seasonalEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSeasonalEndDate() {
        return seasonalEndDate;
    }

    /**
     * Sets the value of the seasonalEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSeasonalEndDate(XMLGregorianCalendar value) {
        this.seasonalEndDate = value;
    }

    /**
     * Gets the value of the seasonalStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSeasonalStartDate() {
        return seasonalStartDate;
    }

    /**
     * Sets the value of the seasonalStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSeasonalStartDate(XMLGregorianCalendar value) {
        this.seasonalStartDate = value;
    }

    /**
     * Gets the value of the temporaryEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTemporaryEndDate() {
        return temporaryEndDate;
    }

    /**
     * Sets the value of the temporaryEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTemporaryEndDate(XMLGregorianCalendar value) {
        this.temporaryEndDate = value;
    }

    /**
     * Gets the value of the temporaryStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTemporaryStartDate() {
        return temporaryStartDate;
    }

    /**
     * Sets the value of the temporaryStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTemporaryStartDate(XMLGregorianCalendar value) {
        this.temporaryStartDate = value;
    }

    /**
     * Gets the value of the undeliverable property.
     * 
     */
    public boolean isUndeliverable() {
        return undeliverable;
    }

    /**
     * Sets the value of the undeliverable property.
     * 
     */
    public void setUndeliverable(boolean value) {
        this.undeliverable = value;
    }

    /**
     * Gets the value of the userCreated property.
     * 
     */
    public boolean isUserCreated() {
        return userCreated;
    }

    /**
     * Sets the value of the userCreated property.
     * 
     */
    public void setUserCreated(boolean value) {
        this.userCreated = value;
    }

}
