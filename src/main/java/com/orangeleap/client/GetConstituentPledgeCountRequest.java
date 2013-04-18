
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
 *         &lt;element name="constituentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "constituentId",
    "includeDeletedRecords"
})
@XmlRootElement(name = "GetConstituentPledgeCountRequest")
public class GetConstituentPledgeCountRequest {

    protected long constituentId;
    protected Boolean includeDeletedRecords;

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
