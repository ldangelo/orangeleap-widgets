
package com.orangeleap.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for picklist complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="picklist">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.orangeleap.com/orangeleap/typesv3_1}abstractCustomizableEntity">
 *       &lt;sequence>
 *         &lt;element name="picklistNameId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="picklistName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="picklistDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="multiselect" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="picklistItems" type="{http://www.orangeleap.com/orangeleap/typesv3_1}picklistItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "picklist", namespace = "http://www.orangeleap.com/orangeleap/typesv3_1", propOrder = {
    "picklistNameId",
    "picklistName",
    "picklistDesc",
    "multiselect",
    "picklistItems",
    "deleted"
})
public class Picklist
    extends AbstractCustomizableEntity
{

    @XmlElement(required = true)
    protected String picklistNameId;
    @XmlElement(required = true)
    protected String picklistName;
    @XmlElement(required = true)
    protected String picklistDesc;
    protected boolean multiselect;
    protected List<PicklistItem> picklistItems;
    protected boolean deleted;

    /**
     * Gets the value of the picklistNameId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPicklistNameId() {
        return picklistNameId;
    }

    /**
     * Sets the value of the picklistNameId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPicklistNameId(String value) {
        this.picklistNameId = value;
    }

    /**
     * Gets the value of the picklistName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPicklistName() {
        return picklistName;
    }

    /**
     * Sets the value of the picklistName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPicklistName(String value) {
        this.picklistName = value;
    }

    /**
     * Gets the value of the picklistDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPicklistDesc() {
        return picklistDesc;
    }

    /**
     * Sets the value of the picklistDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPicklistDesc(String value) {
        this.picklistDesc = value;
    }

    /**
     * Gets the value of the multiselect property.
     * 
     */
    public boolean isMultiselect() {
        return multiselect;
    }

    /**
     * Sets the value of the multiselect property.
     * 
     */
    public void setMultiselect(boolean value) {
        this.multiselect = value;
    }

    /**
     * Gets the value of the picklistItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the picklistItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPicklistItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PicklistItem }
     * 
     * 
     */
    public List<PicklistItem> getPicklistItems() {
        if (picklistItems == null) {
            picklistItems = new ArrayList<PicklistItem>();
        }
        return this.picklistItems;
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
