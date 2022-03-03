package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "actor")
public class Actor extends Person{
private  Gender gender;
private int experience;

    public Actor() {
    }

    public Actor(Gender gender, int experience, String name, int age, Address address) {
        super(name,age,address);
        this.gender = gender;
        this.experience = experience;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Actor{id=" +super.getId()+", name="+
                super.getName()+", age="+super.getAge()+", "+
                "gender=" + gender +
                ", experience=" + experience +", address="+super.getAddress()+
                '}';
    }
}