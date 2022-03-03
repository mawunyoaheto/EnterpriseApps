package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Address;

@Generated(value="EclipseLink-2.7.8.v20201217-rNA", date="2022-03-01T00:51:04")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, Address> address;
    public static volatile SingularAttribute<Person, String> name;
    public static volatile SingularAttribute<Person, Integer> id;
    public static volatile SingularAttribute<Person, Integer> age;

}