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
}