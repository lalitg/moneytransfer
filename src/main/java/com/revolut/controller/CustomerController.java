package com.revolut.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.revolut.repository.CustomerRepository;
import com.revolut.repository.CustomerRepositoryStub;
import com.revolut.model.Customer;

@Path("customer")
public class CustomerController {

	private CustomerRepository repo = new CustomerRepositoryStub();
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String createCustomer(MultivaluedMap<String, String> formParams) {
		Customer cust = new Customer();
		cust.setName(formParams.getFirst("name"));
        long id = repo.save(cust);		
		return ""+id;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Customer getCustomer(@PathParam("id") String id){
		return repo.get(Long.parseLong(id));
	}
}
