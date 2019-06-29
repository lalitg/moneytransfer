package com.revolut.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.revolut.model.Account;
import com.revolut.repository.AccountRepository;
import com.revolut.repository.AccountRepositoryStub;

@Path("account")
public class AccountController {

	private AccountRepository repo = new AccountRepositoryStub();
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String createCustomer(MultivaluedMap<String, String> formParams) {
		Account acc = new Account();
		acc.setCustId(Long.parseLong(formParams.getFirst("custId")));
		acc.setBalance(Double.parseDouble(formParams.getFirst("balance")));
		System.out.println(acc.getCustId());
		System.out.println(acc.getBalance());
        long id = repo.save(acc);	
        System.out.println(id);
		return ""+id;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Account getCustomer(@PathParam("id") String id){
		return repo.get(Long.parseLong(id));
	}


}
