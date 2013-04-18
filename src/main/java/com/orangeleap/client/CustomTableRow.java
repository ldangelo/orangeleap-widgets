
package com.orangeleap.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for customTableRow complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="customTableRow">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.orangeleap.com/orangeleap/typesv3_1}abstractCustomizableEntity">
 *       &lt;sequence>
 *         &lt;element name="customTableId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="customTableRowActive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="updatedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isActive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="childCustomTables" type="{http://www.orangeleap.com/orangeleap/typesv3_1}childCustomTable" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customTableRow", namespace = "http://www.orangeleap.com/orangeleap/typesv3_1", propOrder = {
    "customTableId",
    "customTableRowActive",
    "updatedBy",
    "isActive",
    "deleted",
    "childCustomTables"
})
public class CustomTableRow
    extends AbstractCustomizableEntity
{

    protected Long customTableId;
    protected boolean customTableRowActive;
    protected String updatedBy;
    protected boolean isActive;
    protected boolean deleted;
    @XmlElement(nillable = true)
    protected List<ChildCustomTable> childCustomTables;

    /**
     * Gets the value of the customTableId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCustomTableId() {
        return customTableId;
    }

    /**
     * Sets the value of the customTableId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCustomTableId(Long value) {
        this.customTableId = value;
    }

    /**
     * Gets the value of the customTableRowActive property.
     * 
     */
    public boolean isCustomTableRowActive() {
        return customTableRowActive;
    }

    /**
     * Sets the value of the customTableRowActive property.
     * 
     */
    public void setCustomTableRowActive(boolean value) {
        this.customTableRowActive = value;
    }

    /**
     * Gets the value of the updatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Sets the value of the updatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatedBy(String value) {
        this.updatedBy = value;
    }

    /**
     * Gets the value of the isActive property.
     * 
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * Sets the value of the isActive property.
     * 
     */
    public void setIsActive(boolean value) {
        this.isActive = value;
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
     * Gets the value of the childCustomTables property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the childCustomTables property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChildCustomTables().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChildCustomTable }
     * 
     * 
     */
    public List<ChildCustomTable> getChildCustomTables() {
        if (childCustomTables == null) {
            childCustomTables = new ArrayList<ChildCustomTable>();
        }
        return this.childCustomTables;
    }

}
