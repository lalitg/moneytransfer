package com.revolut.repository;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revolut.model.Account;
import com.revolut.model.MoneyTransaction;

public class TransactionRepositoryStub implements TransactionRepository {
	public MoneyTransaction get(Long id) {
		SessionFactory sessionfactory = HibernateSessionFactory.getSessionFactory();
	    Session session = sessionfactory.openSession();
	    MoneyTransaction trans = (MoneyTransaction)session.get(MoneyTransaction.class, id);
	    sessionfactory.close();
	    session.close();
		return trans;
	}

	public long save(MoneyTransaction mt) {
		SessionFactory sessionfactory = HibernateSessionFactory.getSessionFactory();
	    Session session = sessionfactory.openSession();
	    Transaction t = session.beginTransaction();
		try {
        session.save(mt);
        t.commit();
	    sessionfactory.close();
	    session.close();
		} catch (Exception e) {
			t.rollback();
			return -1;
		}
		return mt.getId();
	}
}
