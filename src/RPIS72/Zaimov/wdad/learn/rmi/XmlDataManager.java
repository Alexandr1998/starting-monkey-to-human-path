package RPIS72.Zaimov.wdad.learn.rmi;

import RPIS72.Zaimov.wdad.utils.Book;
import RPIS72.Zaimov.wdad.utils.Reader;

import javax.xml.crypto.dsig.TransformException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface XmlDataManager extends Remote {
    List<Reader> negligentReaders() throws RemoteException, TransformException;
    void addBook(Reader reader, Book book) throws  RemoteException, TransformException;
    void removeBook (Reader reader, Book book) throws RemoteException, TransformException;
    List<Book> listOfBooksSetReader(Reader reader) throws RemoteException, TransformException;
    HashMap<Book, Date> hashMap(Reader reader) throws RemoteException, TransformException;
}