package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        // Create SessionFactory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Insert records
        Transaction transaction = session.beginTransaction();
        Customer customer1 = new Customer();
        customer1.setName("roshan");
        customer1.setEmail("roshan@example.com");
        customer1.setAge(25);
        customer1.setLocation("New York");

        Customer customer2 = new Customer();
        customer2.setName("eswar");
        customer2.setEmail("eswar@example.com");
        customer2.setAge(30);
        customer2.setLocation("San Francisco");

        session.save(customer1);
        session.save(customer2);
        transaction.commit();

        // HCQL operations
        Criteria criteria = session.createCriteria(Customer.class);

        // 1. Equal
        criteria.add(Restrictions.eq("name", "Alice"));
        List<Customer> eqCustomers = criteria.list();
        System.out.println("Equal Example: " + eqCustomers);

        // 2. Not Equal
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.ne("name", "Alice"));
        List<Customer> neCustomers = criteria.list();
        System.out.println("Not Equal Example: " + neCustomers);

        // 3. Less Than
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.lt("age", 30));
        List<Customer> ltCustomers = criteria.list();
        System.out.println("Less Than Example: " + ltCustomers);

        // 4. Greater Than
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.gt("age", 25));
        List<Customer> gtCustomers = criteria.list();
        System.out.println("Greater Than Example: " + gtCustomers);

        // 5. Between
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.between("age", 20, 30));
        List<Customer> betweenCustomers = criteria.list();
        System.out.println("Between Example: " + betweenCustomers);

        // 6. Like
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.like("location", "%San%"));
        List<Customer> likeCustomers = criteria.list();
        System.out.println("Like Example: " + likeCustomers);

        // Close session
        session.close();
        sessionFactory.close();
    }
}
