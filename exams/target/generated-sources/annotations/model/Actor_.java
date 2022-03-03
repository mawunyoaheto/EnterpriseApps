package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Gender;

@Generated(value="EclipseLink-2.7.8.v20201217-rNA", date="2022-03-01T00:51:04")
@StaticMetamodel(Actor.class)
public class Actor_ extends Person_ {

    public static volatile SingularAttribute<Actor, Gender> gender;
    public static volatile SingularAttribute<Actor, Integer> experience;

}