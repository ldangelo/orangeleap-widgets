
package com.orangeleap.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for site complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="site">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="achCompanyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="achMerchantId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="achRuleNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="achSiteNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="achTestMode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="jasperPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jasperUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localeString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="majorDonorAccountManagerId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="merchantBin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="merchantNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="merchantTerminalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smtpAccountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smtpFromAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smtpPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smtpServerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timeZoneString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "site", propOrder = {
    "achCompanyName",
    "achMerchantId",
    "achRuleNumber",
    "achSiteNumber",
    "achTestMode",
    "active",
    "createDate",
    "jasperPassword",
    "jasperUserId",
    "localeString",
    "majorDonorAccountManagerId",
    "merchantBin",
    "merchantNumber",
    "merchantTerminalId",
    "name",
    "smtpAccountName",
    "smtpFromAddress",
    "smtpPassword",
    "smtpServerName",
    "timeZoneString",
    "updateDate"
})
public class Site {

    protected String achCompanyName;
    protected Integer achMerchantId;
    protected Integer achRuleNumber;
    protected Integer achSiteNumber;
    protected Integer achTestMode;
    protected boolean active;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    protected String jasperPassword;
    protected String jasperUserId;
    protected String localeString;
    protected Integer majorDonorAccountManagerId;
    protected String merchantBin;
    protected String merchantNumber;
    protected String merchantTerminalId;
    protected String name;
    protected String smtpAccountName;
    protected String smtpFromAddress;
    protected String smtpPassword;
    protected String smtpServerName;
    protected String timeZoneString;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateDate;

    /**
     * Gets the value of the achCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAchCompanyName() {
        return achCompanyName;
    }

    /**
     * Sets the value of the achCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAchCompanyName(String value) {
        this.achCompanyName = value;
    }

    /**
     * Gets the value of the achMerchantId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAchMerchantId() {
        return achMerchantId;
    }

    /**
     * Sets the value of the achMerchantId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAchMerchantId(Integer value) {
        this.achMerchantId = value;
    }

    /**
     * Gets the value of the achRuleNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAchRuleNumber() {
        return achRuleNumber;
    }

    /**
     * Sets the value of the achRuleNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAchRuleNumber(Integer value) {
        this.achRuleNumber = value;
    }

    /**
     * Gets the value of the achSiteNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAchSiteNumber() {
        return achSiteNumber;
    }

    /**
     * Sets the value of the achSiteNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAchSiteNumber(Integer value) {
        this.achSiteNumber = value;
    }

    /**
     * Gets the value of the achTestMode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAchTestMode() {
        return achTestMode;
    }

    /**
     * Sets the value of the achTestMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAchTestMode(Integer value) {
        this.achTestMode = value;
    }

    /**
     * Gets the value of the active property.
     * 
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     */
    public void setActive(boolean value) {
        this.active = value;
    }

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
    }

    /**
     * Gets the value of the jasperPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJasperPassword() {
        return jasperPassword;
    }

    /**
     * Sets the value of the jasperPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJasperPassword(String value) {
        this.jasperPassword = value;
    }

    /**
     * Gets the value of the jasperUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJasperUserId() {
        return jasperUserId;
    }

    /**
     * Sets the value of the jasperUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJasperUserId(String value) {
        this.jasperUserId = value;
    }

    /**
     * Gets the value of the localeString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocaleString() {
        return localeString;
    }

    /**
     * Sets the value of the localeString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocaleString(String value) {
        this.localeString = value;
    }

    /**
     * Gets the value of the majorDonorAccountManagerId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMajorDonorAccountManagerId() {
        return majorDonorAccountManagerId;
    }

    /**
     * Sets the value of the majorDonorAccountManagerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMajorDonorAccountManagerId(Integer value) {
        this.majorDonorAccountManagerId = value;
    }

    /**
     * Gets the value of the merchantBin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantBin() {
        return merchantBin;
    }

    /**
     * Sets the value of the merchantBin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantBin(String value) {
        this.merchantBin = value;
    }

    /**
     * Gets the value of the merchantNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantNumber() {
        return merchantNumber;
    }

    /**
     * Sets the value of the merchantNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantNumber(String value) {
        this.merchantNumber = value;
    }

    /**
     * Gets the value of the merchantTerminalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantTerminalId() {
        return merchantTerminalId;
    }

    /**
     * Sets the value of the merchantTerminalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantTerminalId(String value) {
        this.merchantTerminalId = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the smtpAccountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmtpAccountName() {
        return smtpAccountName;
    }

    /**
     * Sets the value of the smtpAccountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmtpAccountName(String value) {
        this.smtpAccountName = value;
    }

    /**
     * Gets the value of the smtpFromAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmtpFromAddress() {
        return smtpFromAddress;
    }

    /**
     * Sets the value of the smtpFromAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmtpFromAddress(String value) {
        this.smtpFromAddress = value;
    }

    /**
     * Gets the value of the smtpPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmtpPassword() {
        return smtpPassword;
    }

    /**
     * Sets the value of the smtpPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmtpPassword(String value) {
        this.smtpPassword = value;
    }

    /**
     * Gets the value of the smtpServerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmtpServerName() {
        return smtpServerName;
    }

    /**
     * Sets the value of the smtpServerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmtpServerName(String value) {
        this.smtpServerName = value;
    }

    /**
     * Gets the value of the timeZoneString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeZoneString() {
        return timeZoneString;
    }

    /**
     * Sets the value of the timeZoneString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeZoneString(String value) {
        this.timeZoneString = value;
    }

    /**
     * Gets the value of the updateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets the value of the updateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateDate(XMLGregorianCalendar value) {
        this.updateDate = value;
    }

}
