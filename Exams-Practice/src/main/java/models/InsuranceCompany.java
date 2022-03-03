package models;

import javax.persistence.*;

@Entity
@Table(name = "insurance_company")
public class InsuranceCompany {
    @Id@GeneratedValue
    private int id;
    private String name;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    public InsuranceCompany() {
    }

    public InsuranceCompany(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "InsuranceCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}