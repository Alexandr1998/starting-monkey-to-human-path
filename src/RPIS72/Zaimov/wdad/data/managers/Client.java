package RPIS72.Zaimov.wdad.data.managers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "policypath",
    "usecodebaseonly"
})
@XmlRootElement(name = "client")
public class Client {

    @XmlElement(required = true)
    protected String policypath;
    @XmlElement(required = true)
    protected String usecodebaseonly;

    /**
     * Gets the value of the policypath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicypath() {
        return policypath;
    }

    /**
     * Sets the value of the policypath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicypath(String value) {
        this.policypath = value;
    }

    /**
     * Gets the value of the usecodebaseonly property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsecodebaseonly() {
        return usecodebaseonly;
    }

    /**
     * Sets the value of the usecodebaseonly property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsecodebaseonly(String value) {
        this.usecodebaseonly = value;
    }

}
