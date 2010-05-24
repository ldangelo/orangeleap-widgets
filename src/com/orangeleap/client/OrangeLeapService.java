
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
 * Fri May 21 16:17:36 CDT 2010
 * Generated source version: 2.1.9
 * 
 */


@WebServiceClient(name = "OrangeLeapService", 
                  wsdlLocation = "file:/var/folders/9z/9zAPZiWLE1a4+mqPUd09Y++++TI/-Tmp-/tempdir4259373096172662354.tmp/orangeleap_1.wsdl",
                  targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/") 
public class OrangeLeapService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://www.orangeleap.com/orangeleap/services3.0/", "OrangeLeapService");
    public final static QName OrangeLeapSoap11 = new QName("http://www.orangeleap.com/orangeleap/services3.0/", "OrangeLeapSoap11");
    static {
        URL url = null;
        try {
            url = new URL("file:/var/folders/9z/9zAPZiWLE1a4+mqPUd09Y++++TI/-Tmp-/tempdir4259373096172662354.tmp/orangeleap_1.wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:/var/folders/9z/9zAPZiWLE1a4+mqPUd09Y++++TI/-Tmp-/tempdir4259373096172662354.tmp/orangeleap_1.wsdl");
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
