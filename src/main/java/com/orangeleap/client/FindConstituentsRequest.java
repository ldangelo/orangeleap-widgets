
package com.orangeleap.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="constituentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organizationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryAddress" type="{http://www.orangeleap.com/orangeleap/typesv3_1}address" minOccurs="0"/>
 *         &lt;element name="primaryPhone" type="{http://www.orangeleap.com/orangeleap/typesv3_1}phone" minOccurs="0"/>
 *         &lt;element name="primaryEmail" type="{http://www.orangeleap.com/orangeleap/typesv3_1}email" minOccurs="0"/>
 *         &lt;element name="includeDeletedRecords" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "firstName",
    "lastName",
    "constituentType",
    "organizationName",
    "primaryAddress",
    "primaryPhone",
    "primaryEmail",
    "includeDeletedRecords"
})
@XmlRootElement(name = "FindConstituentsRequest")
public class FindConstituentsRequest {

    protected String firstName;
    protected String lastName;
    protected String constituentType;
    protected String organizationName;
    protected Address primaryAddress;
    protected Phone primaryPhone;
    protected Email primaryEmail;
    protected Boolean includeDeletedRecords;

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the constituentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConstituentType() {
        return constituentType;
    }

    /**
     * Sets the value of the constituentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConstituentType(String value) {
        this.constituentType = value;
    }

    /**
     * Gets the value of the organizationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * Sets the value of the organizationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizationName(String value) {
        this.organizationName = value;
    }

    /**
     * Gets the value of the primaryAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    /**
     * Sets the value of the primaryAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setPrimaryAddress(Address value) {
        this.primaryAddress = value;
    }

    /**
     * Gets the value of the primaryPhone property.
     * 
     * @return
     *     possible object is
     *     {@link Phone }
     *     
     */
    public Phone getPrimaryPhone() {
        return primaryPhone;
    }

    /**
     * Sets the value of the primaryPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link Phone }
     *     
     */
    public void setPrimaryPhone(Phone value) {
        this.primaryPhone = value;
    }

    /**
     * Gets the value of the primaryEmail property.
     * 
     * @return
     *     possible object is
     *     {@link Email }
     *     
     */
    public Email getPrimaryEmail() {
        return primaryEmail;
    }

    /**
     * Sets the value of the primaryEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link Email }
     *     
     */
    public void setPrimaryEmail(Email value) {
        this.primaryEmail = value;
    }

    /**
     * Gets the value of the includeDeletedRecords property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeDeletedRecords() {
        return includeDeletedRecords;
    }

    /**
     * Sets the value of the includeDeletedRecords property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeDeletedRecords(Boolean value) {
        this.includeDeletedRecords = value;
    }

}
