package com.revolut.repository;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revolut.model.Account;

public class AccountRepositoryStub implements AccountRepository {
	public Account get(Long id) {
		SessionFactory sessionfactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionfactory.openSession();
	    Account acc = (Account)session.get(Account.class, id, LockMode.PESSIMISTIC_READ);
	    sessionfactory.close();
	    session.close();
		return acc;
	}

	public long save(Account acc) {
		SessionFactory sessionfactory = HibernateSessionFactory.getSessionFactory();
	    Session session = sessionfactory.openSession();
	    Transaction t = session.beginTransaction();
		try {
        session.save(acc);
        t.commit();
	    sessionfactory.close();
	    session.close();
		} catch (Exception e) {
			t.rollback();
			return -1;
		}
		return acc.getId();
	}
}
