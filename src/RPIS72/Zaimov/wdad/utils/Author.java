package RPIS72.Zaimov.wdad.utils;

public class Author {
    private String firstName;
    private String secondName;

    public Author(String firstName, String secondName){
        this.firstName = firstName;
        this.secondName = secondName;
    }
    Author(){
        this(null,null);
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(firstName);
        sb.append(" ").append(secondName);
        return sb.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if  (obj==null && !this.getClass().equals(obj.getClass())) return false;
        Author a = (Author) obj;
        return (a.getFirstName().equals(this.getFirstName()) && a.getSecondName().equals(this.getSecondName()));
    }


}