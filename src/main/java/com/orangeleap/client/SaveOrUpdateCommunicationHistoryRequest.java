
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
 *         &lt;element name="constituentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="communicationHistory" type="{http://www.orangeleap.com/orangeleap/typesv3_1}communicationHistory"/>
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
    "communicationHistory"
})
@XmlRootElement(name = "SaveOrUpdateCommunicationHistoryRequest")
public class SaveOrUpdateCommunicationHistoryRequest {

    protected long constituentId;
    @XmlElement(required = true)
    protected CommunicationHistory communicationHistory;

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
     * Gets the value of the communicationHistory property.
     * 
     * @return
     *     possible object is
     *     {@link CommunicationHistory }
     *     
     */
    public CommunicationHistory getCommunicationHistory() {
        return communicationHistory;
    }

    /**
     * Sets the value of the communicationHistory property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommunicationHistory }
     *     
     */
    public void setCommunicationHistory(CommunicationHistory value) {
        this.communicationHistory = value;
    }

}
