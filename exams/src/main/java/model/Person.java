package model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person")
@NamedNativeQuery(name = "getMovieActors",query = "SELECT p.* FROM movie_person m_p JOIN Person p ON p.ID = m_p.persons_ID JOIN Movie m ON m.ID = m_p.Movie_ID AND m.TITLE=?1",resultClass = Person.class)
public class Person {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int age;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public Person() {
    }

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}