
package com.orangeleap.client;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for distributionLine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="distributionLine">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.orangeleap.com/orangeleap/typesv3_1}abstractCustomizableEntity">
 *       &lt;sequence>
 *         &lt;element name="adjustedGiftId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="giftId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="motivationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="other_motivationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="percentage" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="constituentId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="pledgeId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="projectCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recurringGiftId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "distributionLine", namespace = "http://www.orangeleap.com/orangeleap/typesv3_1", propOrder = {
    "adjustedGiftId",
    "amount",
    "giftId",
    "motivationCode",
    "otherMotivationCode",
    "percentage",
    "constituentId",
    "pledgeId",
    "projectCode",
    "recurringGiftId"
})
public class DistributionLine
    extends AbstractCustomizableEntity
{

    protected Long adjustedGiftId;
    protected BigDecimal amount;
    protected Long giftId;
    protected String motivationCode;
    @XmlElement(name = "other_motivationCode")
    protected String otherMotivationCode;
    protected BigDecimal percentage;
    protected Long constituentId;
    protected Long pledgeId;
    protected String projectCode;
    protected Long recurringGiftId;

    /**
     * Gets the value of the adjustedGiftId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAdjustedGiftId() {
        return adjustedGiftId;
    }

    /**
     * Sets the value of the adjustedGiftId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAdjustedGiftId(Long value) {
        this.adjustedGiftId = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
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
     * Gets the value of the motivationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivationCode() {
        return motivationCode;
    }

    /**
     * Sets the value of the motivationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivationCode(String value) {
        this.motivationCode = value;
    }

    /**
     * Gets the value of the otherMotivationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherMotivationCode() {
        return otherMotivationCode;
    }

    /**
     * Sets the value of the otherMotivationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherMotivationCode(String value) {
        this.otherMotivationCode = value;
    }

    /**
     * Gets the value of the percentage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPercentage() {
        return percentage;
    }

    /**
     * Sets the value of the percentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPercentage(BigDecimal value) {
        this.percentage = value;
    }

    /**
     * Gets the value of the constituentId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getConstituentId() {
        return constituentId;
    }

    /**
     * Sets the value of the constituentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setConstituentId(Long value) {
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
     * Gets the value of the projectCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectCode() {
        return projectCode;
    }

    /**
     * Sets the value of the projectCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectCode(String value) {
        this.projectCode = value;
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

}
