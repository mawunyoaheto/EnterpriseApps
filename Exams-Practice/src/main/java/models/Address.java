package models;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue
    private int id;
    private String street;
    private String city;
    private String state;
    @OneToOne(mappedBy = "address")
    private InsuranceCompany insuranceCompany;

    public Address() {
    }

    public Address(String street, String city, String state, InsuranceCompany insuranceCompany) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.insuranceCompany = insuranceCompany;
    }

    public Address(String street, String city, String state) {
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public InsuranceCompany getInsuranceCompany() {
        return insuranceCompany;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}