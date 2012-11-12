
package com.orangeleap.client;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="tablename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="offset" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="limit">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="filters" type="{http://www.orangeleap.com/orangeleap/services3.0/}filter" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dateFilters" type="{http://www.orangeleap.com/orangeleap/services3.0/}dateFilter" maxOccurs="unbounded" minOccurs="0"/>
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
    "tablename",
    "offset",
    "limit",
    "filters",
    "dateFilters"
})
@XmlRootElement(name = "GetCustomTableRowsRequest")
public class GetCustomTableRowsRequest {

    @XmlElement(required = true)
    protected String tablename;
    protected long offset;
    protected int limit;
    protected List<Filter> filters;
    protected List<DateFilter> dateFilters;

    /**
     * Gets the value of the tablename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTablename() {
        return tablename;
    }

    /**
     * Sets the value of the tablename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTablename(String value) {
        this.tablename = value;
    }

    /**
     * Gets the value of the offset property.
     * 
     */
    public long getOffset() {
        return offset;
    }

    /**
     * Sets the value of the offset property.
     * 
     */
    public void setOffset(long value) {
        this.offset = value;
    }

    /**
     * Gets the value of the limit property.
     * 
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Sets the value of the limit property.
     * 
     */
    public void setLimit(int value) {
        this.limit = value;
    }

    /**
     * Gets the value of the filters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Filter }
     * 
     * 
     */
    public List<Filter> getFilters() {
        if (filters == null) {
            filters = new ArrayList<Filter>();
        }
        return this.filters;
    }

    /**
     * Gets the value of the dateFilters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dateFilters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDateFilters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DateFilter }
     * 
     * 
     */
    public List<DateFilter> getDateFilters() {
        if (dateFilters == null) {
            dateFilters = new ArrayList<DateFilter>();
        }
        return this.dateFilters;
    }

}
