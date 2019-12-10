package RPIS72.Zaimov.wdad.learn.xml;

import RPIS72.Zaimov.wdad.utils.Book;
import RPIS72.Zaimov.wdad.utils.Library;
import RPIS72.Zaimov.wdad.utils.Reader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlTask {
    Library library;
    File file;

    public XmlTask(File file, Library library){
        this.file = file;
        this.library = library;
    }

    void saveXML(File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Library.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(library,file );

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(library, System.out );
    }

    public void save(){
        try {
            saveXML(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    void loadXML() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Library.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        library = (Library) unmarshaller.unmarshal(file);
    }

    public List<Reader> negligentReaders(){
        ArrayList<Reader> r = new ArrayList<>();
        for (int i=0; i<library.readersAmount();i++) {
            if (library.isDebtor(library.getReader(i))) {
                r.add(library.getReader(i));
            }
        }
        return r;
    }

    public boolean removeBook(Reader reader, Book book) throws JAXBException {
        if (library.getReader( reader.getFirstName(),
                reader.getSecondName()) == null)
            return false;
        library.getReader(
                reader.getFirstName(),
                reader.getSecondName()
        ).returnBook(book);
        saveXML(file);
        return true;
    }

    public void addBook (Reader reader, Book book) throws JAXBException {
        if(library.getReader( reader.getFirstName(),reader.getSecondName()) == null){
            library.openLibraryCard(reader);
        }
        library.getReader(
                reader.getFirstName(),
                reader.getSecondName()
        ).takeBook(book);
        saveXML(file);
    }

    public List<Book> debtBooks(Reader reader) {
        if (reader == null) {
            throw new NullPointerException();
        }
        return reader.getTakenBooks();
    }
}
