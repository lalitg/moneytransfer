package com.revolut.sample; 
  
import java.util.Date;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.revolut.enums.MoneyTransactionStatus;
import com.revolut.model.Account;
import com.revolut.model.Customer;
import com.revolut.model.MoneyTransaction;
import com.revolut.repository.AccountRepository;
import com.revolut.repository.AccountRepositoryStub;
import com.revolut.repository.CustomerRepository;
import com.revolut.repository.CustomerRepositoryStub;
import com.revolut.service.Schedular;

  
public class HibernateSessionFactorySample {  
	
public static SessionFactory getSessionFactory() {
	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
    SessionFactory factory = meta.getSessionFactoryBuilder().build();
    return factory;
}
public static void main(String[] args) {  
    
		
		 SessionFactory factory = getSessionFactory(); Session session =
		  factory.openSession(); Transaction t = session.beginTransaction();
		 
    Account acc = new Account();
    acc.setBalance(1000);
    acc.setCustId(2000L);
    AccountRepository accrepo = new AccountRepositoryStub();
    System.out.println(accrepo.get(1L));
    accrepo.save(acc);
    System.out.println(" account id "+acc.getId());
    acc = new Account();
    acc.setBalance(1000);
    acc.setCustId(2001L);
    accrepo.save(acc);
		  Customer cust = new Customer(); 
		  cust.setName("lalitgera"); 
		  //session.save(cust);
		  CustomerRepository custrepo = new CustomerRepositoryStub();
		  custrepo.save(cust);
		  System.out.println(" customer id "+custrepo.get(1L)); 
		/*
		 * MoneyTransaction trans = new MoneyTransaction(); trans.setAmount(10);
		 * trans.setReceiverId(1L); trans.setSenderId(2L); trans.setState("Pending");
		 * trans.setTimeStamp(new Date()); Schedular schd = new Schedular();
		 * schd.addTrans(trans); MoneyTransaction trans1 = new MoneyTransaction();
		 * trans.setAmount(10); trans.setReceiverId(1L); trans.setSenderId(2L);
		 * trans.setState("Pending"); trans.setTimeStamp(new Date());
		 * schd.addTrans(trans1); MoneyTransaction trans2 = new MoneyTransaction();
		 * trans.setAmount(10); trans.setReceiverId(1L); trans.setSenderId(2L);
		 * trans.setState("Pending"); trans.setTimeStamp(new Date());
		 * schd.addTrans(trans2);
		 */
		  Schedular schd = new Schedular();
		  for(int i=0; i<100; i++) {
			  MoneyTransaction trans =  new MoneyTransaction(); 
			  trans.setAmount(1); 
			  trans.setReceiverId(1L);
			  trans.setSenderId(2L);
			  trans.setStatus(MoneyTransactionStatus.pending); 
			  trans.setTimeStamp(new Date()); 
			  schd.addTrans(trans);
			  session.save(trans);
		  }
		  t.commit();factory.close(); session.close();
		  //schd.startTrans();
		  //session.save(trans);
		//  TransactionRepository transrepo = new TransactionRepositoryStub();
		//  transrepo.save(trans);
		//  System.out.println(" trans id "+transrepo.get(1L));
		 
		/*
		 * t.commit(); factory.close(); session.close();
		 */ 
      
}  
}  