package RPIS72.Zaimov.wdad.utils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Library {

    private ArrayList<Reader> readers;

    public Library () {
        readers = new ArrayList<>();
    }

    @XmlElement
    public ArrayList<Reader> getReaders(){
        return readers;
    }

    public Library(ArrayList<Reader> readers) {
        if (!readers.isEmpty())
            this.readers = readers;
    }

    public void openLibraryCard(Reader reader){
        readers.add(reader);
    }

    public  int readersAmount () {
        return readers.size();
    }

    public  Reader getReader(int i){
        return readers.get(i);
    }

    public  Reader getReader(String firstName, String secondName){
        for (Reader r: readers) {
            if (r.getFirstName().equals(firstName) && r.getSecondName().equals(secondName)) return r;
        }
        return null;
    }

    public boolean isDebtor (Reader r) {
        return  r.isDebtor();
    }
}