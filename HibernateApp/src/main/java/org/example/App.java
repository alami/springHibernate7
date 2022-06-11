package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            /*Person person = session.get(Person.class, 1);
            System.out.println(person.getName());
            System.out.println(person.getAge());*/

            /*Person person = new Person("Mike", 30);
            Person person1 = new Person("Luna", 20);
            Person person2 = new Person("Mila", 45);
            session.save(person);
            session.save(person1);
            session.save(person2);*/

            /*Person person = session.get(Person.class, 2);
            //person.setName("Newest Name");
            session.delete(person);*/

            /*Person person2 = new Person("Mila", 45);
            session.save(person2);
            System.out.println(person2.getId()); //-> easy get new ID from DB !
            */

            //List<Person> people = session.createQuery("FROM Person where age>30").getResultList();
            List<Person> people = session.createQuery("FROM Person where name like 'M%'").getResultList();
            for (Person person : people)
                System.out.println(person.getName());

            session.createQuery("UPDATE Person set name='Test' where age>30").executeUpdate();


            session.getTransaction().commit();



        } finally {
            sessionFactory.close();
        }
    }
}