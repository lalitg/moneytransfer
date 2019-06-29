package com.revolut.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revolut.enums.MoneyTransactionStatus;
import com.revolut.model.Account;
import com.revolut.model.MoneyTransaction;
import com.revolut.repository.HibernateSessionFactory;

class Task implements Callable<Long>{
    MoneyTransaction trans;
    Task(MoneyTransaction trans){
        this.trans = trans;
    }
    public Long call(){  
		SessionFactory sessionfactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionfactory.openSession();
		Transaction t = session.beginTransaction();
    	try {
	    Account sender = (Account)session.get(Account.class, trans.getSenderId(), LockMode.PESSIMISTIC_WRITE);
	    Account receiver = (Account)session.get(Account.class, trans.getReceiverId(), LockMode.PESSIMISTIC_WRITE);
	    double amount = trans.getAmount();
        sender.setBalance(sender.getBalance()-amount);
        receiver.setBalance(receiver.getBalance()+amount);
        session.update(sender);
        session.update(receiver);
        trans.setStatus(MoneyTransactionStatus.success);
      	session.update(trans);
        t.commit(); sessionfactory.close(); session.close();
        return trans.getId();
    	} catch(Exception e) {
    		System.out.println(e);
    		sessionfactory.close(); 
    		session.close();
    	}

        return trans.getId();
    }
}

public class Schedular /* implements ServletContextListene */{

    /**
     * @param args
     */
	ExecutorService exec = Executors.newFixedThreadPool(10);
    CompletionService<Long> compServ = new ExecutorCompletionService<Long>(exec);
    Map<Long, MoneyTransaction> alltrans = new HashMap<Long, MoneyTransaction>();
	public void addTrans(MoneyTransaction trans) {
		Task task = new Task(trans);
		compServ.submit(task);
		alltrans.put(trans.getId(), trans);
	}
}
