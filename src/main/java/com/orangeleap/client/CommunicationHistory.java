
package com.orangeleap.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 * 						CommunicationHistory is used to update/add any type of
 * 						communication with a
 * 						constituent. It will map to the
 * 						"touch points"
 * 						screen within the orange leap API. Because orange leap is
 * 						extensible you can track much more
 * 						than just e-mails and calls with
 * 						our CommunicationHistory.
 * 
 * 						Some examples of implementations we have
 * 						done track things like
 * 						events, website visits, volunteer hours,
 * 						etc... using
 * 						"touch points"/Communication History.
 * 
 * 						CommunicationHistory objects are tied to constituents but can
 * 						alternately reference
 * 						Gifts, Pledges, etc...
 * 
 * 						Like all entities on
 * 						orange leap CommunicationHistory can be
 * 						customized. For example an
 * 						EventName could be added to all
 * 						"touch points" that have a type of
 * 						"Event". These custom fields are
 * 						accessed through the
 * 						customFieldMap of the
 * 						abstractCustomizableEntity
 * 						that we inherit
 * 						from.
 * 				
 * 
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "communicationHistory", namespace = "http://www.orangeleap.com/orangeleap/typesv3_1")
public class CommunicationHistory
    extends AbstractCustomizableEntity
{

    @XmlAttribute
    protected String comments;
    @XmlAttribute(required = true)
    protected String communicationHistoryType;
    @XmlAttribute
    protected String entryType;
    @XmlAttribute
    protected Long giftId;
    @XmlAttribute(required = true)
    protected long constituentId;
    @XmlAttribute
    protected Long pledgeId;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar recordDate;
    @XmlAttribute
    protected Long recurringGiftId;
    @XmlAttribute(required = true)
    protected boolean deleted;

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
     * Gets the value of the communicationHistoryType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommunicationHistoryType() {
        return communicationHistoryType;
    }

    /**
     * Sets the value of the communicationHistoryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommunicationHistoryType(String value) {
        this.communicationHistoryType = value;
    }

    /**
     * Gets the value of the entryType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntryType() {
        return entryType;
    }

    /**
     * Sets the value of the entryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntryType(String value) {
        this.entryType = value;
    }

    /**
     * Gets the value of the giftId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getGiftId() {
        return giftId;
    }

    /**
     * Sets the value of the giftId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setGiftId(Long value) {
        this.giftId = value;
    }

    /**
     * Gets the value of the constituentId property.
     * 
     */
    public long getConstituentId() {
        return constituentId;
    }

    /**
     * Sets the value of the constituentId property.
     * 
     */
    public void setConstituentId(long value) {
        this.constituentId = value;
    }

    /**
     * Gets the value of the pledgeId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPledgeId() {
        return pledgeId;
    }

    /**
     * Sets the value of the pledgeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPledgeId(Long value) {
        this.pledgeId = value;
    }

    /**
     * Gets the value of the recordDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRecordDate() {
        return recordDate;
    }

    /**
     * Sets the value of the recordDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRecordDate(XMLGregorianCalendar value) {
        this.recordDate = value;
    }

    /**
     * Gets the value of the recurringGiftId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRecurringGiftId() {
        return recurringGiftId;
    }

    /**
     * Sets the value of the recurringGiftId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRecurringGiftId(Long value) {
        this.recurringGiftId = value;
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

}
