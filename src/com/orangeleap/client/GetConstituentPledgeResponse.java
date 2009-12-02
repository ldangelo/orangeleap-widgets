
package com.orangeleap.client;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="pledge" type="{http://www.orangeleap.com/orangeleap/services/1.0}pledge" maxOccurs="unbounded" minOccurs="0"/>
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
    "pledge"
})
@XmlRootElement(name = "GetConstituentPledgeResponse")
public class GetConstituentPledgeResponse {

    protected List<Pledge> pledge;

    /**
     * Gets the value of the pledge property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pledge property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPledge().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Pledge }
     * 
     * 
     */
    public List<Pledge> getPledge() {
        if (pledge == null) {
            pledge = new ArrayList<Pledge>();
        }
        return this.pledge;
    }

}
