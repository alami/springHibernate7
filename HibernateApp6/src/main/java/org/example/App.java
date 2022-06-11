package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            /*Person person = session.get(Person.class, 3);
            System.out.println(person);

            List<Item> items = person.getItems();
            //System.out.println(items);
            for (Item item : items)
                System.out.println(item);

            Item item = session.get(Item.class, 5);
            System.out.println(item);
            Person person1 = item.getOwner();
            System.out.println(person1);*/

//            добавим новй товар для него
//            Item newItem = new Item("FromHibernate", person1);
//            person1.getItems().add(newItem);  //т.е.кеширется Hibernatom :)))
//            session.save(newItem);

            Person person = new Person("Test Cascading", 60);
            Item item = new Item("Test Cascading Item1",person);
            person.setItems(new ArrayList<Item>(Collections.singletonList(item)));
            session.persist(person);

            session.getTransaction().commit();



        } finally {
            sessionFactory.close();
        }
    }
}