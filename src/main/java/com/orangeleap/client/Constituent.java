
package com.orangeleap.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for constituent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="constituent">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.orangeleap.com/orangeleap/typesv3_1}abstractCustomizableEntity">
 *       &lt;sequence>
 *         &lt;element name="constituentId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="accountNumber" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="suffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maritalStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addresses" type="{http://www.orangeleap.com/orangeleap/typesv3_1}address" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="emails" type="{http://www.orangeleap.com/orangeleap/typesv3_1}email" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="phones" type="{http://www.orangeleap.com/orangeleap/typesv3_1}phone" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="primaryAddress" type="{http://www.orangeleap.com/orangeleap/typesv3_1}address" minOccurs="0"/>
 *         &lt;element name="primaryEmail" type="{http://www.orangeleap.com/orangeleap/typesv3_1}email" minOccurs="0"/>
 *         &lt;element name="primaryPhone" type="{http://www.orangeleap.com/orangeleap/typesv3_1}phone" minOccurs="0"/>
 *         &lt;element name="organizationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="constituentAttributes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="constituentIndividualRoles" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="constituentOrganizationRoles" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="constituentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferredPhoneType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="site" type="{http://www.orangeleap.com/orangeleap/typesv3_1}site" minOccurs="0"/>
 *         &lt;element name="inactive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="byPassDuplicateDetection" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "constituent", namespace = "http://www.orangeleap.com/orangeleap/typesv3_1", propOrder = {
    "constituentId",
    "accountNumber",
    "firstName",
    "middleName",
    "lastName",
    "suffix",
    "title",
    "maritalStatus",
    "addresses",
    "emails",
    "phones",
    "primaryAddress",
    "primaryEmail",
    "primaryPhone",
    "organizationName",
    "constituentAttributes",
    "constituentIndividualRoles",
    "constituentOrganizationRoles",
    "constituentType",
    "preferredPhoneType",
    "site",
    "inactive",
    "deleted",
    "byPassDuplicateDetection"
})
public class Constituent
    extends AbstractCustomizableEntity
{

    protected Long constituentId;
    protected Long accountNumber;
    @XmlElement(required = true)
    protected String firstName;
    protected String middleName;
    @XmlElement(required = true)
    protected String lastName;
    protected String suffix;
    protected String title;
    protected String maritalStatus;
    @XmlElement(nillable = true)
    protected List<Address> addresses;
    @XmlElement(nillable = true)
    protected List<Email> emails;
    @XmlElement(nillable = true)
    protected List<Phone> phones;
    protected Address primaryAddress;
    protected Email primaryEmail;
    protected Phone primaryPhone;
    protected String organizationName;
    protected String constituentAttributes;
    protected String constituentIndividualRoles;
    protected String constituentOrganizationRoles;
    protected String constituentType;
    protected String preferredPhoneType;
    protected Site site;
    protected boolean inactive;
    protected boolean deleted;
    protected boolean byPassDuplicateDetection;

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
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAccountNumber(Long value) {
        this.accountNumber = value;
    }

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
     * Gets the value of the middleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleName(String value) {
        this.middleName = value;
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
     * Gets the value of the suffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Sets the value of the suffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuffix(String value) {
        this.suffix = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the maritalStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Sets the value of the maritalStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaritalStatus(String value) {
        this.maritalStatus = value;
    }

    /**
     * Gets the value of the addresses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addresses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddresses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Address }
     * 
     * 
     */
    public List<Address> getAddresses() {
        if (addresses == null) {
            addresses = new ArrayList<Address>();
        }
        return this.addresses;
    }

    /**
     * Gets the value of the emails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the emails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Email }
     * 
     * 
     */
    public List<Email> getEmails() {
        if (emails == null) {
            emails = new ArrayList<Email>();
        }
        return this.emails;
    }

    /**
     * Gets the value of the phones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the phones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Phone }
     * 
     * 
     */
    public List<Phone> getPhones() {
        if (phones == null) {
            phones = new ArrayList<Phone>();
        }
        return this.phones;
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
     * Gets the value of the constituentAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConstituentAttributes() {
        return constituentAttributes;
    }

    /**
     * Sets the value of the constituentAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConstituentAttributes(String value) {
        this.constituentAttributes = value;
    }

    /**
     * Gets the value of the constituentIndividualRoles property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConstituentIndividualRoles() {
        return constituentIndividualRoles;
    }

    /**
     * Sets the value of the constituentIndividualRoles property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConstituentIndividualRoles(String value) {
        this.constituentIndividualRoles = value;
    }

    /**
     * Gets the value of the constituentOrganizationRoles property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConstituentOrganizationRoles() {
        return constituentOrganizationRoles;
    }

    /**
     * Sets the value of the constituentOrganizationRoles property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConstituentOrganizationRoles(String value) {
        this.constituentOrganizationRoles = value;
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
     * Gets the value of the preferredPhoneType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferredPhoneType() {
        return preferredPhoneType;
    }

    /**
     * Sets the value of the preferredPhoneType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferredPhoneType(String value) {
        this.preferredPhoneType = value;
    }

    /**
     * Gets the value of the site property.
     * 
     * @return
     *     possible object is
     *     {@link Site }
     *     
     */
    public Site getSite() {
        return site;
    }

    /**
     * Sets the value of the site property.
     * 
     * @param value
     *     allowed object is
     *     {@link Site }
     *     
     */
    public void setSite(Site value) {
        this.site = value;
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
     * Gets the value of the byPassDuplicateDetection property.
     * 
     */
    public boolean isByPassDuplicateDetection() {
        return byPassDuplicateDetection;
    }

    /**
     * Sets the value of the byPassDuplicateDetection property.
     * 
     */
    public void setByPassDuplicateDetection(boolean value) {
        this.byPassDuplicateDetection = value;
    }

}
