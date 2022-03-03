package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Person;

@Generated(value="EclipseLink-2.7.8.v20201217-rNA", date="2022-03-01T00:51:04")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, String> city;
    public static volatile SingularAttribute<Address, String> street;
    public static volatile SingularAttribute<Address, Person> person;
    public static volatile SingularAttribute<Address, Integer> id;
    public static volatile SingularAttribute<Address, String> state;

}