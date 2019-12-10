package RPIS72.Zaimov.wdad.utils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Book {
    @XmlElement
    private  Author author;
    @XmlElement
    private String name;
    @XmlElement
    private String printYear;
    @XmlAttribute
    private Genre genre;

    private Book(){
        name = "";
        author = null;
        genre = null;
        printYear = null;
    }

    public Book(Author author, String name, String printyear, Genre genre, String takeDate) {
        this.author = author;
        this.name = name;
        this.printYear = printyear;
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getPrintYear() {
        return printYear;
    }

    public Genre getGenre() {
        return genre;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(author.toString());
        sb.append("\n").append(name).append("\n").append(genre).append(" ,").append(printYear);
        return sb.toString();
    }


}