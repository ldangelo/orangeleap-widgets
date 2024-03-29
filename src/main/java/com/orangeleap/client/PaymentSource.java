
package com.orangeleap.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for paymentSource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paymentSource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="achAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="achAccountNumberDisplay" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="achAccountNumberEncrypted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="achHolderName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="achRoutingNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="achRoutingNumberDisplay" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address" type="{http://www.orangeleap.com/orangeleap/typesv3_1}address" minOccurs="0"/>
 *         &lt;element name="creditCardExpiration" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="creditCardExpirationMonth" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="creditCardExpirationYear" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="creditCardHolderName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditCardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditCardNumberDisplay" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditCardSecurityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditCardType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inactive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lastFourDigits" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentType" type="{http://www.orangeleap.com/orangeleap/typesv3_1}PaymentType"/>
 *         &lt;element name="constituentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="phone" type="{http://www.orangeleap.com/orangeleap/typesv3_1}phone" minOccurs="0"/>
 *         &lt;element name="profile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userCreated" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paymentSource", namespace = "http://www.orangeleap.com/orangeleap/typesv3_1", propOrder = {
    "achAccountNumber",
    "achAccountNumberDisplay",
    "achAccountNumberEncrypted",
    "achHolderName",
    "achRoutingNumber",
    "achRoutingNumberDisplay",
    "address",
    "creditCardExpiration",
    "creditCardExpirationMonth",
    "creditCardExpirationYear",
    "creditCardHolderName",
    "creditCardNumber",
    "creditCardNumberDisplay",
    "creditCardSecurityCode",
    "creditCardType",
    "inactive",
    "deleted",
    "lastFourDigits",
    "paymentType",
    "constituentId",
    "phone",
    "profile",
    "userCreated"
})
public class PaymentSource {

    protected String achAccountNumber;
    protected String achAccountNumberDisplay;
    protected String achAccountNumberEncrypted;
    protected String achHolderName;
    protected String achRoutingNumber;
    protected String achRoutingNumberDisplay;
    protected Address address;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creditCardExpiration;
    protected Integer creditCardExpirationMonth;
    protected Integer creditCardExpirationYear;
    protected String creditCardHolderName;
    protected String creditCardNumber;
    protected String creditCardNumberDisplay;
    protected String creditCardSecurityCode;
    protected String creditCardType;
    protected boolean inactive;
    protected boolean deleted;
    protected String lastFourDigits;
    @XmlElement(required = true)
    protected PaymentType paymentType;
    protected long constituentId;
    protected Phone phone;
    protected String profile;
    protected boolean userCreated;

    /**
     * Gets the value of the achAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAchAccountNumber() {
        return achAccountNumber;
    }

    /**
     * Sets the value of the achAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAchAccountNumber(String value) {
        this.achAccountNumber = value;
    }

    /**
     * Gets the value of the achAccountNumberDisplay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAchAccountNumberDisplay() {
        return achAccountNumberDisplay;
    }

    /**
     * Sets the value of the achAccountNumberDisplay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAchAccountNumberDisplay(String value) {
        this.achAccountNumberDisplay = value;
    }

    /**
     * Gets the value of the achAccountNumberEncrypted property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAchAccountNumberEncrypted() {
        return achAccountNumberEncrypted;
    }

    /**
     * Sets the value of the achAccountNumberEncrypted property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAchAccountNumberEncrypted(String value) {
        this.achAccountNumberEncrypted = value;
    }

    /**
     * Gets the value of the achHolderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAchHolderName() {
        return achHolderName;
    }

    /**
     * Sets the value of the achHolderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAchHolderName(String value) {
        this.achHolderName = value;
    }

    /**
     * Gets the value of the achRoutingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAchRoutingNumber() {
        return achRoutingNumber;
    }

    /**
     * Sets the value of the achRoutingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAchRoutingNumber(String value) {
        this.achRoutingNumber = value;
    }

    /**
     * Gets the value of the achRoutingNumberDisplay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAchRoutingNumberDisplay() {
        return achRoutingNumberDisplay;
    }

    /**
     * Sets the value of the achRoutingNumberDisplay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAchRoutingNumberDisplay(String value) {
        this.achRoutingNumberDisplay = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
    }

    /**
     * Gets the value of the creditCardExpiration property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreditCardExpiration() {
        return creditCardExpiration;
    }

    /**
     * Sets the value of the creditCardExpiration property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreditCardExpiration(XMLGregorianCalendar value) {
        this.creditCardExpiration = value;
    }

    /**
     * Gets the value of the creditCardExpirationMonth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCreditCardExpirationMonth() {
        return creditCardExpirationMonth;
    }

    /**
     * Sets the value of the creditCardExpirationMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCreditCardExpirationMonth(Integer value) {
        this.creditCardExpirationMonth = value;
    }

    /**
     * Gets the value of the creditCardExpirationYear property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCreditCardExpirationYear() {
        return creditCardExpirationYear;
    }

    /**
     * Sets the value of the creditCardExpirationYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCreditCardExpirationYear(Integer value) {
        this.creditCardExpirationYear = value;
    }

    /**
     * Gets the value of the creditCardHolderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardHolderName() {
        return creditCardHolderName;
    }

    /**
     * Sets the value of the creditCardHolderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardHolderName(String value) {
        this.creditCardHolderName = value;
    }

    /**
     * Gets the value of the creditCardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * Sets the value of the creditCardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardNumber(String value) {
        this.creditCardNumber = value;
    }

    /**
     * Gets the value of the creditCardNumberDisplay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardNumberDisplay() {
        return creditCardNumberDisplay;
    }

    /**
     * Sets the value of the creditCardNumberDisplay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardNumberDisplay(String value) {
        this.creditCardNumberDisplay = value;
    }

    /**
     * Gets the value of the creditCardSecurityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardSecurityCode() {
        return creditCardSecurityCode;
    }

    /**
     * Sets the value of the creditCardSecurityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardSecurityCode(String value) {
        this.creditCardSecurityCode = value;
    }

    /**
     * Gets the value of the creditCardType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardType() {
        return creditCardType;
    }

    /**
     * Sets the value of the creditCardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardType(String value) {
        this.creditCardType = value;
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
     * Gets the value of the lastFourDigits property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastFourDigits() {
        return lastFourDigits;
    }

    /**
     * Sets the value of the lastFourDigits property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastFourDigits(String value) {
        this.lastFourDigits = value;
    }

    /**
     * Gets the value of the paymentType property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentType }
     *     
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the value of the paymentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentType }
     *     
     */
    public void setPaymentType(PaymentType value) {
        this.paymentType = value;
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
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link Phone }
     *     
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link Phone }
     *     
     */
    public void setPhone(Phone value) {
        this.phone = value;
    }

    /**
     * Gets the value of the profile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfile() {
        return profile;
    }

    /**
     * Sets the value of the profile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfile(String value) {
        this.profile = value;
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
