package models;

import javax.persistence.*;

@Entity
@Table(name = "car")
@DiscriminatorValue("CAR")
@NamedNativeQuery(name="getAllCars",query = "SELECT * FROM car",resultClass=Car.class)
public class Car extends  Vehicle{
    private  int millage;

    public Car() {
    }

    public Car(int year, int millage, Factory factory) {
        super(year,factory);
        this.millage = millage;
    }

    public int getMillage() {
        return millage;
    }

    public void setMillage(int millage) {
        this.millage = millage;
    }

    @Override
    public String toString() {
        return "Car{" +
                "millage=" + millage +
                '}';
    }
}