package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int age;
    @OneToMany(mappedBy = "person",cascade = CascadeType.PERSIST)
    private List<Vehicle> vehicles = new ArrayList<>();

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, List<Vehicle> vehicles) {
        this.name = name;
        this.age = age;
        this.vehicles=vehicles;
        this.setVehicles(this.vehicles);
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

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
            this.vehicles = vehicles;
            for (Vehicle v : vehicles) {
                v.setPerson(this);
            }
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}