package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Person;

@Generated(value="EclipseLink-2.7.8.v20201217-rNA", date="2022-03-01T00:51:04")
@StaticMetamodel(Movie.class)
public class Movie_ { 

    public static volatile ListAttribute<Movie, Person> persons;
    public static volatile SingularAttribute<Movie, Integer> year;
    public static volatile SingularAttribute<Movie, Integer> id;
    public static volatile SingularAttribute<Movie, String> title;

}