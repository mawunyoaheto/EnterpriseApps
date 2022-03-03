import models.*;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            emf = Persistence.createEntityManagerFactory("CAR-DB_PU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            //load data into database
            populateDB(em);

            System.out.println();
            System.out.println("########################2- Write a query to return all the factories built after a certain year, then invoke it with year 1985.####################");
            String queryStr = "SELECT f FROM Factory f WHERE f.year>= :date";
            TypedQuery<Factory> query = em.createQuery(queryStr, Factory.class);
            query.setParameter("date", 1985);
            List<Factory> factories = query.getResultList();
            for(Factory f: factories) {
                System.out.println(f);
            }

            System.out.println("########################3- Write a query to return all people with a car with millage less than 100000 and produced by a factory insured by an insurance company in CA. (using criteria API)####################");
            CriteriaBuilder criPersonBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Person> criPersonQuery = criPersonBuilder.createQuery(Person.class);
            Root<Person> rootPerson = criPersonQuery.from(Person.class);
            criPersonQuery.select(rootPerson);
            Join<Person, Vehicle> joinVehicle = rootPerson.join("vehicles");
            Predicate millage_pred = criPersonBuilder.lessThan(criPersonBuilder.treat(joinVehicle,Car.class).get("millage"),100000);
            Join<Vehicle, Factory> joinFactory = joinVehicle.join("factory");
            Join<Factory, InsuranceCompany> joinInsurance = joinFactory.join("insuranceCompany");
            Join<InsuranceCompany,Address> joinAddress = joinInsurance.join("address");
            Predicate factoryAddress = criPersonBuilder.equal(joinAddress.get("state"), "CA");
            Predicate andPredicate = criPersonBuilder.and(millage_pred, factoryAddress);
            criPersonQuery.where(andPredicate);

            List<Person> personList = em.createQuery(criPersonQuery).getResultList();
            for(Person p: personList) {
                System.out.println(p);
            }

            System.out.println();
            System.out.println("##########4-Write a query to return all the vehicles owned by a person, then invoke it asking for jack. (named queries).###########");
            TypedQuery <Vehicle> vehicleQry = em.createNamedQuery("Person.Vehicles", Vehicle.class);
            vehicleQry.setParameter("owner", "Jack");
            List<Vehicle> listOfPersonVehicles = vehicleQry.getResultList();

            for(Vehicle v: listOfPersonVehicles) {
                System.out.println(v);
            };
            System.out.println();

            System.out.println("########################5- Write a query to return all the Cars in the system. (named native)####################");
            List<Car> cars = em.createNamedQuery("getAllCars").getResultList();
            for(Vehicle c: cars) {
                System.out.println(c);
            }
            System.out.println();
            System.out.println("########################6- Write a query to return all the vehicles in the system (Criteria API)###################");
            CriteriaBuilder criBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Vehicle> criQuery = criBuilder.createQuery(Vehicle.class);
            Root<Vehicle> rootVehicle = criQuery.from(Vehicle.class);
            criQuery.select(rootVehicle);
            List<Vehicle> vehicles = em.createQuery(criQuery).getResultList();
            for(Vehicle v: vehicles) {
                System.out.println(v);
            }
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }
    }

    private static void populateDB(EntityManager em){

        List<Vehicle> jack_vehicles = new ArrayList<>();
        List<Vehicle> jill_vehicles = new ArrayList<>();
        List<Vehicle> john_vehicles = new ArrayList<>();

        InsuranceCompany stateFarm = new InsuranceCompany("statefarm",new Address("1102 E Chapman Ave","Fullerton","CA"));
        InsuranceCompany good_ones = new InsuranceCompany("GoodOnes",new Address("425 N New Ballas Rd UNIT 215","Creve Coeur","MO"));

        Factory factory1 = new Factory(1990,12300,stateFarm);
        Factory factory2 = new Factory(1980,800,stateFarm);
        Factory factory3 = new Factory(2000,20034,good_ones);

        Vehicle jack_car = new Car(2005,18900,factory1);
        Vehicle jack_car2 = new Car(2020,10020,factory3);
        Vehicle john_car = new Car(1999,120398,factory2);
        Vehicle jill_car = new Car(2000,123452,factory1);
        Vehicle jill_van = new Van(2000, Color.RED,factory2);
        Vehicle jack_van = new Van(2003, Color.BLUE,factory3);

        jack_vehicles.add(jack_car);
        jack_vehicles.add(jack_car2);
        jack_vehicles.add(jack_van);
        john_vehicles.add(john_car);
        john_vehicles.add(john_car);
        jill_vehicles.add(jill_car);
        jill_vehicles.add(jill_van);

        Person jack = new Person("Jack", 32,jack_vehicles);
        Person john = new Person("John", 33,john_vehicles);
        Person jill = new Person("Jill", 28,jill_vehicles);

        em.persist(jack);
        em.persist(john);
        em.persist(jill);
    }
}
