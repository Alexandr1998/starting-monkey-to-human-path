package RPIS72.Zaimov.wdad.data.managers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rmi"
})
@XmlRootElement(name = "appconfig")
public class Appconfig {

    @XmlElement(required = true)
    protected Rmi rmi;

    /**
     * Gets the value of the rmi property.
     * 
     * @return
     *     possible object is
     *     {@link Rmi }
     *     
     */
    public Rmi getRmi() {
        return rmi;
    }

    /**
     * Sets the value of the rmi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Rmi }
     *     
     */
    public void setRmi(Rmi value) {
        this.rmi = value;
    }

}
