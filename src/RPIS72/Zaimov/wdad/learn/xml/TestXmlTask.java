package RPIS72.Zaimov.wdad.learn.xml;

import RPIS72.Zaimov.wdad.utils.*;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBException;

public class TestXmlTask {
    public static void main(String[] args) throws JAXBException {
        File file = new File("file.xml");
        Reader reader = new Reader("lol","kek");
        Author author = new Author("Эрик","Шпикерман");
        Book book = new Book(author,"О шрифте","2010", Genre.NOVEL, "1999-12-12");
        reader.takeBook(book);
        Book book2 = new Book(author,"Пёся","2015",Genre.NOVEL, "2017-12-12");
        reader.takeBook(book2);
        Library library = new Library();
        library.openLibraryCard(reader);
        XmlTask xt = new XmlTask(file,library);
        xt.saveXML(file);
        xt.loadXML();
        System.out.println("Взятые книги");
        List<Book> books = xt.debtBooks(reader);
        System.out.println(books);
        System.out.println( xt.removeBook(reader,book) );
    }
}