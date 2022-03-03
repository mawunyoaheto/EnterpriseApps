package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stunt")
public class Stunt extends Person{
    private double height;
    private double weight;

    public Stunt() {
    }

    public Stunt(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    public Stunt(String name, int age, Address address, double height, double weight) {
        super(name, age, address);
        this.height = height;
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Stunt{id=" +super.getId()+", name="+
                super.getName()+", age="+super.getAge()+", "+
                "height=" + height +
                ", weight=" + weight +", address="+super.getAddress()+
                '}';
    }
}