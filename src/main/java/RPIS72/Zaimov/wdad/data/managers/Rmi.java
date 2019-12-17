
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
    "server",
    "client",
    "classprovider"
})
@XmlRootElement(name = "rmi")
public class Rmi {

    @XmlElement(required = true)
    protected Server server;
    @XmlElement(required = true)
    protected Client client;
    protected String classprovider;

    /**
     * Gets the value of the server property.
     * 
     * @return
     *     possible object is
     *     {@link Server }
     *     
     */
    public Server getServer() {
        return server;
    }

    /**
     * Sets the value of the server property.
     * 
     * @param value
     *     allowed object is
     *     {@link Server }
     *     
     */
    public void setServer(Server value) {
        this.server = value;
    }

    /**
     * Gets the value of the client property.
     * 
     * @return
     *     possible object is
     *     {@link Client }
     *     
     */
    public Client getClient() {
        return client;
    }

    /**
     * Sets the value of the client property.
     * 
     * @param value
     *     allowed object is
     *     {@link Client }
     *     
     */
    public void setClient(Client value) {
        this.client = value;
    }

    /**
     * Gets the value of the classprovider property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassprovider() {
        return classprovider;
    }

    /**
     * Sets the value of the classprovider property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassprovider(String value) {
        this.classprovider = value;
    }

}
