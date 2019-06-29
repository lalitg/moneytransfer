package com.revolut.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revolut.model.Account;
import com.revolut.model.Customer;
public class CustomerRepositoryStub implements CustomerRepository {
	public Customer get(Long id) {
		//get Accont from DB
		SessionFactory sessionfactory = HibernateSessionFactory.getSessionFactory();
	    Session session = sessionfactory.openSession();
	    Customer acc = (Customer)session.get(Customer.class, id);
	    sessionfactory.close();
	    session.close();
		return acc;
	}

	public long save(Customer cust) {
		try {
		SessionFactory sessionfactory = HibernateSessionFactory.getSessionFactory();
	    Session session = sessionfactory.openSession();
	    Transaction t = session.beginTransaction();
        session.save(cust);
        t.commit();
	    sessionfactory.close();
	    session.close();
		} catch (Exception e) {
			return -1L;
		}
		return cust.getId();
	}
}
