
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
 * This class was generated by Apache CXF 2.2.4
 * Wed Apr 17 17:11:48 CDT 2013
 * Generated source version: 2.2.4
 * 
 */


@WebServiceClient(name = "OrangeLeapService", 
                  wsdlLocation = "http://10.0.2.62:51680/orangeleap/services3.1/orangeleap.wsdl",
                  targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/") 
public class OrangeLeapService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://www.orangeleap.com/orangeleap/services3.1/", "OrangeLeapService");
    public final static QName OrangeLeapSoap11 = new QName("http://www.orangeleap.com/orangeleap/services3.1/", "OrangeLeapSoap11");
    static {
        URL url = null;
        try {
            url = new URL("http://10.0.2.62:51680/orangeleap/services3.1/orangeleap.wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://10.0.2.62:51680/orangeleap/services3.1/orangeleap.wsdl");
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
    @WebEndpoint(name = "OrangeLeapSoap11")
    public OrangeLeap getOrangeLeapSoap11() {
        return super.getPort(OrangeLeapSoap11, OrangeLeap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OrangeLeap
     */
    @WebEndpoint(name = "OrangeLeapSoap11")
    public OrangeLeap getOrangeLeapSoap11(WebServiceFeature... features) {
        return super.getPort(OrangeLeapSoap11, OrangeLeap.class, features);
    }

}
