
package com.orangeleap.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 						Orange Leap uses the Site object to specify which "organizations" data
 * 						model you are accessing. For example
 * 						if you use site.name="sandbox" you will access the data model
 * 						associated with our sandbox. One important note is
 * 						that the site.name is validated against the credentials that you log
 * 						in
 * 						as... So if you authenticate with demo@sandbox your site
 * 						name must be sandbox or you will receive a SoapFault.
 * 				
 * 
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "site")
public class Site {

    @XmlAttribute(required = true)
    protected String name;

    /**
     * 
     * 							The name of the site that you wish to connect too. This will
     * 							generate an Exception
     * 							if your authentication credentials do not match your sitename.
     * 
     * 							For example if you try to connect to demo@sandbox and use "demo" as
     * 							your site name.
     * 							You will receive a SoapFault exception with a reason "Site name
     * 							does not match supplied credentials"
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
