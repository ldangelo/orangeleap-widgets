
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
 *         &lt;element name="jobDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="importEntityType" type="{http://www.orangeleap.com/orangeleap/services3.0/}importEntityType"/>
 *         &lt;element name="customTableName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="csvData" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "jobDescription",
    "importEntityType",
    "customTableName",
    "csvData"
})
@XmlRootElement(name = "ImportRequest")
public class ImportRequest {

    @XmlElement(required = true)
    protected String jobDescription;
    @XmlElement(required = true)
    protected ImportEntityType importEntityType;
    @XmlElement(required = true)
    protected String customTableName;
    @XmlElement(required = true)
    protected String csvData;

    /**
     * Gets the value of the jobDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobDescription() {
        return jobDescription;
    }

    /**
     * Sets the value of the jobDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobDescription(String value) {
        this.jobDescription = value;
    }

    /**
     * Gets the value of the importEntityType property.
     * 
     * @return
     *     possible object is
     *     {@link ImportEntityType }
     *     
     */
    public ImportEntityType getImportEntityType() {
        return importEntityType;
    }

    /**
     * Sets the value of the importEntityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImportEntityType }
     *     
     */
    public void setImportEntityType(ImportEntityType value) {
        this.importEntityType = value;
    }

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
     * Gets the value of the csvData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCsvData() {
        return csvData;
    }

    /**
     * Sets the value of the csvData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCsvData(String value) {
        this.csvData = value;
    }

}
