package RPIS72.Zaimov.wdad.data.managers;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: exampl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Server }
     * 
     */
    public Server createServer() {
        return new Server();
    }

    /**
     * Create an instance of {@link Registry }
     * 
     */
    public Registry createRegistry() {
        return new Registry();
    }

    /**
     * Create an instance of {@link Bindedobject }
     * 
     */
    public Bindedobject createBindedobject() {
        return new Bindedobject();
    }

    /**
     * Create an instance of {@link Appconfig }
     * 
     */
    public Appconfig createAppconfig() {
        return new Appconfig();
    }

    /**
     * Create an instance of {@link Rmi }
     * 
     */
    public Rmi createRmi() {
        return new Rmi();
    }

    /**
     * Create an instance of {@link Client }
     * 
     */
    public Client createClient() {
        return new Client();
    }

}
