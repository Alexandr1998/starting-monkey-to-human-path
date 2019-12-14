package RPIS72.Zaimov.wdad.learn.rmi.client;
import RPIS72.Zaimov.wdad.PreferencesConstantManager;
import RPIS72.Zaimov.wdad.data.managers.PreferencesManager;
import RPIS72.Zaimov.wdad.learn.rmi.XmlDataManager;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws Exception    {


        PreferencesManager prefManager = PreferencesManager.getInstance();

        Registry reg= LocateRegistry.getRegistry(Integer.parseInt(prefManager.getProperty(PreferencesConstantManager.REGISTRYPORT)));
        XmlDataManager x=(XmlDataManager)reg.lookup("XmlDataManager");

        System.out.println(x.negligentReaders().get(0).getFirstName());
    }
}