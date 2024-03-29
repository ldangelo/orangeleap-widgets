
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
 *         &lt;element name="picklist" type="{http://www.orangeleap.com/orangeleap/typesv3_1}picklist" minOccurs="0"/>
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
    "picklist"
})
@XmlRootElement(name = "AddPickListItemByNameResponse")
public class AddPickListItemByNameResponse {

    protected Picklist picklist;

    /**
     * Gets the value of the picklist property.
     * 
     * @return
     *     possible object is
     *     {@link Picklist }
     *     
     */
    public Picklist getPicklist() {
        return picklist;
    }

    /**
     * Sets the value of the picklist property.
     * 
     * @param value
     *     allowed object is
     *     {@link Picklist }
     *     
     */
    public void setPicklist(Picklist value) {
        this.picklist = value;
    }

}
