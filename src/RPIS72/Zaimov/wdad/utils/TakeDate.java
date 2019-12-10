package RPIS72.Zaimov.wdad.utils;

import javax.xml.bind.annotation.XmlAttribute;
import java.time.LocalDate;

public class TakeDate {
    @XmlAttribute
    int day;
    @XmlAttribute
    int month;
    @XmlAttribute
    int year;

    TakeDate(){
        this(0,0,0);
    }

    TakeDate(int day, int month, int year){
        this.day=day;
        this.month = month;
        this.year = year;
    }

    TakeDate(LocalDate date){
        this.day = date.getDayOfMonth();
        this.month = date.getMonthValue();
        this.year = date.getYear();
    }

    public long toEpochDay(){
        return LocalDate.of(year, month, day).toEpochDay();
    }
}