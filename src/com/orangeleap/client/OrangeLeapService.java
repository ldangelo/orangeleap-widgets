
/*
 * 
 */

package com.orangeleap.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.1.9
 * Tue May 18 14:09:23 CDT 2010
 * Generated source version: 2.1.9
 * 
 */


@WebServiceClient(name = "OrangeLeapService", 
                  wsdlLocation = "http://localhost:8080/orangeleap/services2.0/orangeleap.wsdl",
                  targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/") 
public class OrangeLeapService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://www.orangeleap.com/orangeleap/services2.0/", "OrangeLeapService");
    public final static QName OrangeLeapPort = new QName("http://www.orangeleap.com/orangeleap/services2.0/", "OrangeLeapPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/orangeleap/services2.0/orangeleap.wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://localhost:8080/orangeleap/services2.0/orangeleap.wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public OrangeLeapService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public OrangeLeapService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OrangeLeapService() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns OrangeLeap
     */
    @WebEndpoint(name = "OrangeLeapPort")
    public OrangeLeap getOrangeLeapPort() {
        return super.getPort(OrangeLeapPort, OrangeLeap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OrangeLeap
     */
    @WebEndpoint(name = "OrangeLeapPort")
    public OrangeLeap getOrangeLeapPort(WebServiceFeature... features) {
        return super.getPort(OrangeLeapPort, OrangeLeap.class, features);
    }

}
