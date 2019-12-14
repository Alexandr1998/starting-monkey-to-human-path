package RPIS72.Zaimov.wdad.data.managers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public final class PreferencesManager {
    private Appconfig rootElement;
    private Properties properties;
    private File file;

    protected final static PreferencesManager instance = new PreferencesManager();

    public static PreferencesManager getInstance() {
        return instance;
    }

    public static Object loadXML(File file, Class c) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(file);
    }
    private void saveXML(File file, Class c, Object obj) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(c);
        Marshaller marshaller = context.createMarshaller();
    }

    public void readXml(File file) throws Exception {
        rootElement = (Appconfig) loadXML(file, Rmi.class);
        properties = new Properties(rootElement);
    }
    public void setProperty(String key, String value) throws JAXBException {
        properties.setProperty(key, value);
        saveXML(file,Appconfig.class, rootElement );
    }
    public String getProperty(String key){
        return properties.getProperty(key);
    }

    public void setProperties(Properties prop){
        this.properties = prop;
    }

    public Properties getProperties(){
        return properties;
    }

    public void addBindedObject(String name, String className) {
        var obj = new Bindedobject();
        obj.name = name;
        obj.clazz = className;
        var array = rootElement.rmi.server.registryOrBindedobject;
        array.add(obj);
    }

    public void removeBindedObject(String name) {
        var array = rootElement.rmi.server.registryOrBindedobject;
        var i = array.listIterator();
        while (i.hasNext()) {
            var o = i.next();
            if (o instanceof Registry)
                continue;
            var bo = (Bindedobject) o;
            if (bo.name.equals(name)) {
                i.remove();
                break;
            }
        }
    }

    @Deprecated
    public Rmi getRmi()
    {
        return rootElement.rmi;
    }
    @Deprecated
    public void setRmi(Rmi rmi)
    {
        rootElement.rmi = rmi;
    }
    @Deprecated
    public Server getServer()
    {
        return rootElement.rmi.server;
    }
    @Deprecated
    public void setServer(Server server)
    {
        rootElement.rmi.server = server;
    }
    @Deprecated
    public Client getClient()
    {
        return rootElement.rmi.client;
    }
    @Deprecated
    public void setClient(Client client)
    {
        rootElement.rmi.client = client;
    }
    @Deprecated
    public void setClassprovider(String classprovider)
    {
        rootElement.rmi.classprovider = classprovider;
    }
    @Deprecated
    public String getClassprovider()
    {
        return rootElement.rmi.classprovider;
    }
    @Deprecated
    public List<Object> getRegistryOrBindedObject()
    {
        return rootElement.rmi.server.registryOrBindedobject;
    }
    @Deprecated
    public void setRegistryOrBindedObject(List<Object> objects)
    {
        rootElement.rmi.server.registryOrBindedobject = objects;
    }
    @Deprecated
    public void setPolicyPath(String policyPath)
    {
        rootElement.rmi.client.policypath = policyPath;
    }
    @Deprecated
    public String getPolicyPath()
    {
        return rootElement.rmi.client.policypath;
    }
    @Deprecated
    public void setUseCodeBaseOnly(String useCodeBaseOnly)
    {
        rootElement.rmi.client.usecodebaseonly = useCodeBaseOnly;
    }
    @Deprecated
    public String getUseCodeBaseOnly()
    {
        return rootElement.rmi.client.usecodebaseonly;
    }

    @Deprecated
    public Appconfig getAppconfig() {
        return rootElement;
    }
    @Deprecated
    public void setAppconfig(Appconfig appconfigObject) {
        this.rootElement = appconfigObject;
    }
}