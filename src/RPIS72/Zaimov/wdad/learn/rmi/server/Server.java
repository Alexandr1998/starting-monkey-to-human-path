package RPIS72.Zaimov.wdad.learn.rmi.server;
import RPIS72.Zaimov.wdad.PreferencesConstantManager;
import RPIS72.Zaimov.wdad.data.managers.PreferencesManager;
import RPIS72.Zaimov.wdad.learn.rmi.XmlDataManager;
import RPIS72.Zaimov.wdad.utils.Author;
import RPIS72.Zaimov.wdad.utils.Book;
import RPIS72.Zaimov.wdad.utils.Genre;
import RPIS72.Zaimov.wdad.utils.Reader;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.crypto.dsig.TransformException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Server {
    public static void main(String[] args) throws Exception {
        PreferencesManager prefManager = PreferencesManager.getInstance();

        Registry reg= LocateRegistry.createRegistry(Integer.parseInt(prefManager.getProperty(PreferencesConstantManager.REGISTRYPORT)));
        reg.rebind("XmlDataManager", new XmlDataManagerImpl());
        prefManager.addBindedObject("XmlDataManager",XmlDataManager.class.getCanonicalName());
        System.out.println("Running server");
    }
}

class XmlDataManagerImpl extends UnicastRemoteObject implements XmlDataManager {

    private org.w3c.dom.Document document;
    private  Reader[] readers;
    public XmlDataManagerImpl() throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(new File("Test.xml"));
        readers = new Reader[document.getElementsByTagName("reader").getLength()];
    }

    @Override
    public List<Reader> negligentReaders() throws RemoteException, TransformException {
        loadReaders();
        List<Reader> list = new ArrayList<>();
        for (int i = 0; i < readers.length; i++) {
            if (getResult(LocalDate.now(),readers[i].getTakedate().toLocalDate())==true)
                list.add(readers[i]);
        }
        return list;
    }

    private boolean getResult(LocalDate firstDate, LocalDate secondDate) {
        Period period = Period.between(secondDate, firstDate);
        if (period.getYears()>0) return true;
        else if (period.getYears()==0 && period.getMonths()>0) return true;
        else if (period.getDays()==0 && period.getMonths()==0 && period.getDays()>14) return true;
        return false;
    }

    @Override
    public void removeBook(Reader reader, Book book) throws RemoteException, TransformException {
        String forename;
        String surname;
        NodeList nodeListReaders = document.getElementsByTagName("reader");
        for (int i = 0; i < nodeListReaders.getLength(); i++)
        {
            Element readers = (Element) nodeListReaders.item(i);
            forename = readers.getAttribute("firstname");
            surname = readers.getAttribute("secondname");

            if (forename.equals(reader.getFirstName()) && //сравниваем имя и фамилие читателя с заданным
                    surname.equals(reader.getSecondName())) {
                NodeList nodeListBook = readers.getElementsByTagName("book");
                NodeList nodeListTakeDate = readers.getElementsByTagName("takedate");
                for (int j = 0; j < nodeListBook.getLength(); j++) {
                    Element books = (Element) nodeListBook.item(j);
                    Element takedate = (Element)  nodeListTakeDate.item(j);
                    NodeList nodeListAuthor = books.getElementsByTagName("author");
                    Element author = (Element) nodeListAuthor.item(0);
                    if (book.getAuthor().getFirstName().equals(author.getElementsByTagName("firstname").item(0).getTextContent()) &&
                            book.getAuthor().getSecondName().equals(author.getElementsByTagName("secondname").item(0).getTextContent()) &&
                            book.getName().equals(books.getElementsByTagName("name").item(0).getTextContent()))
                    {
                        nodeListReaders.item(i).removeChild(books);
                        nodeListReaders.item(i).removeChild(takedate);
                    }
                }
            }
        }
        saveTransformXML();
    }

    @Override
    public void addBook(Reader reader, Book book) throws RemoteException, TransformException {
        String forename;
        String surname;
        NodeList nodeListReaders = document.getElementsByTagName("reader");
        for (int i = 0; i < nodeListReaders.getLength(); i++)
        {
            Element element = (Element) nodeListReaders.item(i);
            forename = element.getAttribute("firstname");
            surname = element.getAttribute("secondname");

            if (forename.equals(reader.getFirstName()) &&
                    surname.equals(reader.getSecondName()))
            {
                Element bookk = document.createElement("book");
                element.appendChild(bookk);

                Element autor = document.createElement("author");
                bookk.appendChild(autor);

                Element firstname = document.createElement("firstname");
                firstname.appendChild(document.createTextNode(book.getAuthor().getFirstName()));
                autor.appendChild(firstname);


                Element secondname = document.createElement("secondname");
                secondname.appendChild(document.createTextNode(book.getAuthor().getSecondName()));
                autor.appendChild(secondname);

                Element name = document.createElement("name");
                name.appendChild(document.createTextNode(book.getName()));
                bookk.appendChild(name);

                Element printyear = document.createElement("printyear");
                printyear.appendChild(document.createTextNode(String.valueOf(book.getPrintYear())));
                bookk.appendChild(printyear);

                Element genre = document.createElement("genre");
                Attr attr = document.createAttribute("value");
                attr.setValue(book.getGenre().name());
                genre.setAttributeNode(attr);
                bookk.appendChild(genre);

                Element takedate = document.createElement("takedate");

                Attr day = document.createAttribute("day");
                day.setValue(String.valueOf(reader.getTakedate().getDayOfMonth()));
                takedate.setAttributeNode(day);


                Attr month = document.createAttribute("month");
                month.setValue(String.valueOf(reader.getTakedate().getMonthValue()));
                takedate.setAttributeNode(month);

                Attr year = document.createAttribute("year");
                year.setValue(String.valueOf(reader.getTakedate().getYear()));
                takedate.setAttributeNode(year);

                element.appendChild(takedate);

                saveTransformXML();
            }
        }
    }

    @Override
    public List<Book> listOfBooksSetReader(Reader reader) throws RemoteException, TransformException {
        List<Book> bookList = new ArrayList<>();
        loadReaders();
        for (int i = 0; i < reader.getBook().length; i++) {
            bookList.add(reader.getBook()[i]);
        }
        return bookList;
    }

    private void saveTransformXML() throws TransformException {
        try {
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("\\starting-monkey-to-human-path\\src\\PO52\\Myhytdinov\\wdad\\learn\\rmi\\Server\\XmlDataStorage.xml"));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
        } catch (TransformerException ex) {
        }
    }

    @Override
    public HashMap<Book, Date> hashMap(Reader reader) throws RemoteException, TransformException {
        loadReaders();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date;
        HashMap<Book, Date> hashMap = new HashMap();//а вдруг у читателя 2 или 3 или более книг
        for (int i = 0; i < readers.length; i++) {
            if (readers[i].equals(reader)) {
                try {
                    date = format.parse(String.valueOf(reader.getTakedate().getDayOfMonth()) + "." +
                            String.valueOf(reader.getTakedate().getMonthValue()) + "." + String.valueOf(reader.getTakedate().getYear()));
                    hashMap.put(reader.getBook()[0], date);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return hashMap;
    }

    private void loadReaders()
    {
        Book[] books;
        String firstname;
        String secondname;
        LocalDateTime data;

        NodeList nodeListReaders = document.getElementsByTagName("reader");
        for (int i = 0; i < nodeListReaders.getLength(); i++) {
            Element reader = (Element) nodeListReaders.item(i);

            firstname = reader.getAttribute("firstname").toString();
            secondname = reader.getAttribute("secondname").toString();

            NodeList nodeListBooks = reader.getElementsByTagName("book");
            books = new Book[nodeListBooks.getLength()];
            for (int j = 0; j < nodeListBooks.getLength(); j++) {
                Element book = (Element) nodeListBooks.item(j);

                NodeList authors = book.getElementsByTagName("author");
                NodeList genres = book.getElementsByTagName("genre");
                NodeList takedatas = reader.getElementsByTagName("takedate");

                Element author = (Element) authors.item(0);
                Element genre = (Element) genres.item(0);
                Element takedata = (Element) takedatas.item(j);

                data = LocalDateTime.of(Integer.valueOf(takedata.getAttribute("year")),Integer.valueOf(takedata.getAttribute("month")),Integer.valueOf(takedata.getAttribute("day")),
                        0,0);

                books[j] = new Book(new Author(author.getElementsByTagName("firstname").item(0).getTextContent(),
                        author.getElementsByTagName("secondname").item(0).getTextContent()),
                        book.getElementsByTagName("name").item(0).getTextContent(),book.getElementsByTagName("printyear").item(0).getTextContent(),
                        Genre.valueOf(genre.getAttribute("value")));

                readers[i] = new Reader(firstname, secondname);
            }

        }

    }
}
