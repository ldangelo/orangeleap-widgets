
package com.orangeleap.client;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for picklistItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="picklistItem">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.orangeleap.com/orangeleap/typesv3_1}abstractCustomizableEntity">
 *       &lt;sequence>
 *         &lt;element name="itemName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="defaultDisplayValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="longDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="referenceValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="itemOrder" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="inactive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="readOnly" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="picklistId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "picklistItem", namespace = "http://www.orangeleap.com/orangeleap/typesv3_1", propOrder = {
    "itemName",
    "defaultDisplayValue",
    "longDescription",
    "detail",
    "referenceValue",
    "itemOrder",
    "inactive",
    "readOnly",
    "picklistId"
})
public class PicklistItem
    extends AbstractCustomizableEntity
{

    @XmlElement(required = true)
    protected String itemName;
    @XmlElement(required = true)
    protected String defaultDisplayValue;
    @XmlElement(required = true)
    protected String longDescription;
    @XmlElement(required = true)
    protected String detail;
    @XmlElement(required = true)
    protected String referenceValue;
    @XmlElement(required = true)
    protected BigInteger itemOrder;
    protected boolean inactive;
    protected boolean readOnly;
    protected long picklistId;

    /**
     * Gets the value of the itemName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the value of the itemName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemName(String value) {
        this.itemName = value;
    }

    /**
     * Gets the value of the defaultDisplayValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultDisplayValue() {
        return defaultDisplayValue;
    }

    /**
     * Sets the value of the defaultDisplayValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultDisplayValue(String value) {
        this.defaultDisplayValue = value;
    }

    /**
     * Gets the value of the longDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * Sets the value of the longDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongDescription(String value) {
        this.longDescription = value;
    }

    /**
     * Gets the value of the detail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Sets the value of the detail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetail(String value) {
        this.detail = value;
    }

    /**
     * Gets the value of the referenceValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceValue() {
        return referenceValue;
    }

    /**
     * Sets the value of the referenceValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceValue(String value) {
        this.referenceValue = value;
    }

    /**
     * Gets the value of the itemOrder property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getItemOrder() {
        return itemOrder;
    }

    /**
     * Sets the value of the itemOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setItemOrder(BigInteger value) {
        this.itemOrder = value;
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
     * Gets the value of the readOnly property.
     * 
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * Sets the value of the readOnly property.
     * 
     */
    public void setReadOnly(boolean value) {
        this.readOnly = value;
    }

    /**
     * Gets the value of the picklistId property.
     * 
     */
    public long getPicklistId() {
        return picklistId;
    }

    /**
     * Sets the value of the picklistId property.
     * 
     */
    public void setPicklistId(long value) {
        this.picklistId = value;
    }

}
