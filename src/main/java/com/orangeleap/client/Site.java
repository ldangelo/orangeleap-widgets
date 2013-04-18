
package com.orangeleap.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smtpServerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smtpAccountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smtpPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smtpFromAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jasperUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jasperPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "site", namespace = "http://www.orangeleap.com/orangeleap/typesv3_1", propOrder = {
    "name",
    "smtpServerName",
    "smtpAccountName",
    "smtpPassword",
    "smtpFromAddress",
    "jasperUserId",
    "jasperPassword"
})
public class Site {

    @XmlElement(required = true)
    protected String name;
    protected String smtpServerName;
    protected String smtpAccountName;
    protected String smtpPassword;
    protected String smtpFromAddress;
    protected String jasperUserId;
    protected String jasperPassword;

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

}
