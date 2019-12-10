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
    "createregistry",
    "registryaddress",
    "registryport"
})
@XmlRootElement(name = "registry")
public class Registry {

    @XmlElement(required = true)
    protected String createregistry;
    @XmlElement(required = true)
    protected String registryaddress;
    @XmlElement(required = true)
    protected String registryport;

    /**
     * Gets the value of the createregistry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateregistry() {
        return createregistry;
    }

    /**
     * Sets the value of the createregistry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateregistry(String value) {
        this.createregistry = value;
    }

    /**
     * Gets the value of the registryaddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistryaddress() {
        return registryaddress;
    }

    /**
     * Sets the value of the registryaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistryaddress(String value) {
        this.registryaddress = value;
    }

    /**
     * Gets the value of the registryport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistryport() {
        return registryport;
    }

    /**
     * Sets the value of the registryport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistryport(String value) {
        this.registryport = value;
    }

}
