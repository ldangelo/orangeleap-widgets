
package com.orangeleap.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="customTableName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="entity" type="{http://www.orangeleap.com/orangeleap/typesv3}StringMap"/>
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
    "customTableName",
    "entity"
})
@XmlRootElement(name = "SetEntityRequest")
public class SetEntityRequest {

    @XmlElement(required = true)
    protected String customTableName;
    @XmlElement(required = true)
    protected StringMap entity;

    /**
     * Gets the value of the customTableName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomTableName() {
        return customTableName;
    }

    /**
     * Sets the value of the customTableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomTableName(String value) {
        this.customTableName = value;
    }

    /**
     * Gets the value of the entity property.
     * 
     * @return
     *     possible object is
     *     {@link StringMap }
     *     
     */
    public StringMap getEntity() {
        return entity;
    }

    /**
     * Sets the value of the entity property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringMap }
     *     
     */
    public void setEntity(StringMap value) {
        this.entity = value;
    }

}
