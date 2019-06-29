package com.revolut.controller;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.revolut.enums.MoneyTransactionStatus;
import com.revolut.model.MoneyTransaction;
import com.revolut.repository.TransactionRepository;
import com.revolut.repository.TransactionRepositoryStub;
import com.revolut.service.Schedular;

@Path("moneytransaction")
public class TransactionController {

	private TransactionRepository transRepo = new TransactionRepositoryStub();
	Schedular schd = new Schedular();
    	
	@POST
	@Path("sendmoney")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String sendMoney(MultivaluedMap<String, String> formParams) {
		String from = formParams.getFirst("id1");
		String to = formParams.getFirst("id2");
		double amount = Double.parseDouble(formParams.getFirst("amount"));
		MoneyTransaction trans =  new MoneyTransaction(); 
		trans.setAmount(amount); 
		trans.setReceiverId(Long.parseLong(from));
		trans.setSenderId(Long.parseLong(to));
		trans.setStatus(MoneyTransactionStatus.pending); 
		trans.setTimeStamp(new Date()); 
		long id = transRepo.save(trans);
		schd.addTrans(trans);
		return ""+id;
}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public MoneyTransaction getTransaction(@PathParam("id") String id){
		return transRepo.get(Long.parseLong(id));
	}
}
