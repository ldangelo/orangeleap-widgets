
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
 *         &lt;element name="importId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="jobId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "importId",
    "jobId"
})
@XmlRootElement(name = "ImportDataResponse")
public class ImportDataResponse {

    protected long importId;
    protected long jobId;

    /**
     * Gets the value of the importId property.
     * 
     */
    public long getImportId() {
        return importId;
    }

    /**
     * Sets the value of the importId property.
     * 
     */
    public void setImportId(long value) {
        this.importId = value;
    }

    /**
     * Gets the value of the jobId property.
     * 
     */
    public long getJobId() {
        return jobId;
    }

    /**
     * Sets the value of the jobId property.
     * 
     */
    public void setJobId(long value) {
        this.jobId = value;
    }

}
