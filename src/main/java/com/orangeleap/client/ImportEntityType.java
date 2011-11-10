
package com.orangeleap.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for importEntityType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="importEntityType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="address"/>
 *     &lt;enumeration value="adjustedgift"/>
 *     &lt;enumeration value="constituent"/>
 *     &lt;enumeration value="email"/>
 *     &lt;enumeration value="customtablerow"/>
 *     &lt;enumeration value="gift"/>
 *     &lt;enumeration value="giftinkind"/>
 *     &lt;enumeration value="phone"/>
 *     &lt;enumeration value="constituent"/>
 *     &lt;enumeration value="picklist"/>
 *     &lt;enumeration value="pledge"/>
 *     &lt;enumeration value="recurringgift"/>
 *     &lt;enumeration value="communicationHistory"/>
 *     &lt;enumeration value="multi"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "importEntityType")
@XmlEnum
public enum ImportEntityType {

    @XmlEnumValue("address")
    ADDRESS("address"),
    @XmlEnumValue("adjustedgift")
    ADJUSTEDGIFT("adjustedgift"),
    @XmlEnumValue("constituent")
    CONSTITUENT("constituent"),
    @XmlEnumValue("email")
    EMAIL("email"),
    @XmlEnumValue("customtablerow")
    CUSTOMTABLEROW("customtablerow"),
    @XmlEnumValue("gift")
    GIFT("gift"),
    @XmlEnumValue("giftinkind")
    GIFTINKIND("giftinkind"),
    @XmlEnumValue("phone")
    PHONE("phone"),
    @XmlEnumValue("picklist")
    PICKLIST("picklist"),
    @XmlEnumValue("pledge")
    PLEDGE("pledge"),
    @XmlEnumValue("recurringgift")
    RECURRINGGIFT("recurringgift"),
    @XmlEnumValue("communicationHistory")
    COMMUNICATION_HISTORY("communicationHistory"),
    @XmlEnumValue("multi")
    MULTI("multi");
    private final String value;

    ImportEntityType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ImportEntityType fromValue(String v) {
        for (ImportEntityType c: ImportEntityType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
