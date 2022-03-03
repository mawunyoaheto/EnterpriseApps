package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "factory")
public class Factory {
    @Id
    @GeneratedValue
    private int id;
    private int year;
    private int total_emp;
    @OneToOne(mappedBy = "factory")
//    private List<Vehicle> vehicles;
    private Vehicle vehicle;
    @OneToOne(cascade = CascadeType.PERSIST)
    private  InsuranceCompany insuranceCompany;

    public Factory() {
    }

    public Factory(int year, int total_emp) {
        this.year = year;
        this.total_emp = total_emp;
//        this.vehicle=vehicle;
    }

    public Factory(int year, int total_emp, InsuranceCompany insuranceCompany) {
        this.year = year;
        this.total_emp = total_emp;
        this.insuranceCompany = insuranceCompany;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotal_emp() {
        return total_emp;
    }

    public void setTotal_emp(int total_emp) {
        this.total_emp = total_emp;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        if(this.vehicle != vehicle){
            this.vehicle=vehicle;
            this.vehicle.setFactory(this);
        }
    }


//    public List<Vehicle> getVehicles() {
//        return vehicles;
//    }
//
//    public void setVehicles(List<Vehicle> vehicles) {
//        this.vehicles = vehicles;
//    }

    public InsuranceCompany getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    @Override
    public String toString() {
        return "Factory{" +
                "id=" + id +
                ", year=" + year +
                ", total_emp=" + total_emp +
                ", vehicle=" + vehicle +
                ", insuranceCompany=" + insuranceCompany +
                '}';
    }
}