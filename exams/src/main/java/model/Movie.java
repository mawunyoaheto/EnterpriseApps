package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private int year;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Person> persons = new ArrayList<>();

    public Movie() {
    }

    public Movie(String title, int year, List<Person> persons) {
        this.title = title;
        this.year = year;
        this.persons = persons;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", persons=" + persons +
                '}';
    }
}