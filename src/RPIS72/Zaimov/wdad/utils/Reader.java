package RPIS72.Zaimov.wdad.utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDate;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class Reader {
    @XmlAttribute
    private String firstName;
    @XmlAttribute
    private String secondName;
    @XmlElement(name = "book")
    private ArrayList<Book> takenBooks;
    @XmlElement(name = "takedate")
    private ArrayList<TakeDate> takeDates;

    public Reader(String firstName, String secondName) {
        this.firstName=firstName;
        this.secondName = secondName;
        takenBooks = new ArrayList<>();
        takeDates = new ArrayList<>();
    }

    Reader(){
        this(null,null);
    }

    public void takeBook(Book book){
        takenBooks.add(book);
        takeDates.add(new TakeDate(LocalDate.now()));
    }

    public boolean returnBook(Book book){
        for (Book b : takenBooks){
            if(b.getName().equals(book.getName()) &&  b.getAuthor().equals(book.getAuthor())){
                takeDates.remove(takenBooks.indexOf(b));
                takenBooks.remove(b);
                return true;
            }
        }
        return false;
    }

    public boolean isDebtor(){
        for (TakeDate date : takeDates) {
            if ((int)(LocalDate.now().toEpochDay() - date.toEpochDay() )> 14)
                return true;
        }
        return false;
    }


    public ArrayList<Book> getTakenBooks() {
        return takenBooks;
    }
    public void setTakenBooks(ArrayList<Book> takenBooks) {
        this.takenBooks = takenBooks;
    }

    public ArrayList<TakeDate> getTakeDates() {
        return takeDates;
    }

    public void setTakeDates(ArrayList<TakeDate> takeDates) {
        this.takeDates = takeDates;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        if (takenBooks.isEmpty()) {
            sb.append(",no debts");
        } else {
            sb.append("\nbook(s) debt: ").append(takenBooks.size()).append("\n");
            for (int i = 0; i < takenBooks.size(); i++) {
                sb.append(takenBooks.get(i).toString()).append("\n");
            }
        }
        return sb.toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}