package models;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name="Person.Vehicles",query="SELECT v FROM Vehicle v join v.person p where p.name=:owner")
public class Vehicle {
    @Id
    @GeneratedValue
    private int id;
    private int year;
    @ManyToOne
    private Person person;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Factory factory;
    @Version
    private int version;


    public Vehicle() {
    }

    public Vehicle(int year) {
        this.year = year;
        //this.person = person;
    }

    public Vehicle(int year,Factory factory) {
        this.year = year;

        setFactory(factory);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        //if(this.factory != factory){
            this.factory=factory;
            this.factory.setVehicle(this);
      //  }
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", year=" + year +
                ", factory=" + factory +
                '}';
    }
}