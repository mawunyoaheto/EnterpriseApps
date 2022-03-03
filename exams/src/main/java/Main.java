import model.*;
import model.Address_;
import model.Movie_;
import model.Person_;


import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static EntityManagerFactory emf = null;
    static EntityManager em = null;
    static EntityTransaction tx = null;
    public static void main(String[] args) {
        try {
            emf = Persistence.createEntityManagerFactory("CS544_PU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            loadData();
            System.out.println();

            System.out.println("#######################GET ALL ACTORS FROM THE MATRIX#############################");
            returnMovieCast("The Matrix");
            System.out.println();

            System.out.println("#######################GET ALL ACTORS OF MATRIX WITH STATE 'CA'#############################");
            getActorsByState("The Matrix","CA");
            System.out.println();

            System.out.println("#######################UPDATE Age of Carrie-Anne Moss to 55#############################");
            updateAge("Carrie-Anne Moss", 55);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }


    public static void loadData() {
        Address keanuAddress = new Address("19601 Wilshire Blvd.", "Bilverly Hills", "CA");
        Address sheaAddress = new Address("1625 Wilshire Blvd.", "Los Angeles", "CA");
        Address carrieAddress = new Address("19601 Wilshire Blvd.", "Bilverly Hills", "CA");
        Address kateAddress = new Address("United AgentsLtd.", "London", "England");
        Address leonardoAddress = new Address("2000 Avenue of The Stars", "Century City", "CA");
        Address bobAddress = new Address("LakeWooBlvd.", "Downey", "CA");

        Person keanu = new Actor(Gender.MALE, 38, "Keanu Reaves", 57, keanuAddress);
        Person carrie = new Actor(Gender.FEMALE, 33, "Carrie-Anne Moss", 50, carrieAddress);
        Person kate = new Actor(Gender.MALE, 31, "Kate Winslet", 46, kateAddress);
        Person leonardo = new Actor(Gender.MALE, 42, "Leonardo DiCaprio", 47, leonardoAddress);
        Person adams = new Stunt("Shea Adams", 35, sheaAddress, 5.6, 150);
        Person bob = new Stunt("Bob Bowles", 28, bobAddress, 5.5, 120);

        List<Person> matrix_cast = new ArrayList<>();
        List<Person> titanic_cast = new ArrayList<>();

        matrix_cast.add(keanu);
        matrix_cast.add(carrie);
        matrix_cast.add(adams);

        titanic_cast.add(kate);
        titanic_cast.add(leonardo);
        titanic_cast.add(bob);

        Movie matrix = new Movie("The Matrix", 1999, matrix_cast);
        Movie titanic = new Movie("Titanic", 1997, titanic_cast);

        em.persist(matrix);
        em.persist(titanic);
    }


    public static void returnMovieCast(String movie_title) {
        List<Person> actors = em.createNamedQuery("getMovieActors", Person.class)
                .setParameter(1, movie_title).getResultList();
        for (Person p : actors) {
            System.out.println(p);
        }
    }


    public static  void getActorsByState(String movie_title,String state){
//           CriteriaBuilder criPersonBuilder = em.getCriteriaBuilder();
//           CriteriaQuery<Person> criPersonQuery = criPersonBuilder.createQuery(Person.class);
//           Root<Person> rootPerson = criPersonQuery.from(Person.class);
 //       criPersonQuery.select(rootPerson);
//           Join<Person,Address> joinAddress = rootPerson.join("address");
//           Predicate address_pred = criPersonBuilder.equal(joinAddress.get("state"),state);
//           criPersonQuery.where(address_pred);
//        List<Person> personList = em.createQuery(criPersonQuery).getResultList();
//        for(Person p: personList) {
//            System.out.println(p);
//        }

           CriteriaBuilder cb = em.getCriteriaBuilder();
           CriteriaQuery<Person> criPersonQuery = cb.createQuery(Person.class);
           Root<Person> rootPersonQry = criPersonQuery.from(Person.class);

           CriteriaQuery<Movie> criMovieQuery = cb.createQuery(Movie.class);
           Root<Movie> rootMovieQry = criMovieQuery.from(Movie.class);
           Predicate movie_title_pred = cb.equal(rootMovieQry.get(Movie_.title),movie_title);

           Join<Person, Address> joinAddress = rootPersonQry.join(Person_.address);

           Predicate address_pred = cb.equal(joinAddress.get(Address_.state),state);
           criPersonQuery.where(address_pred);
           criMovieQuery.where(movie_title_pred);

            Movie result  = em.createQuery((criMovieQuery)).getSingleResult();
        System.out.println(result.getTitle());

        List<Person> personList = result.getPersons();
        for(Person p: personList) {
            System.out.println(p);
        }

    }

    public static void updateAge(String actor_name, int age) {
        String query = "SELECT a  from Person p, Actor a WHERE p.id=a.id and p.name=:name";
        TypedQuery<Actor> actorQry = em.createQuery(query, Actor.class);
        actorQry.setParameter("name", actor_name);
        Actor result = actorQry.getSingleResult();
        System.out.println("################before update######################");
        System.out.println(result);
        result.setAge(age);
        em.merge(result);
        System.out.println("################after update######################");
        System.out.println(result);

    }
}
