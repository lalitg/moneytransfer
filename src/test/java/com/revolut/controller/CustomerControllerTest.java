package com.revolut.controller;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import com.revolut.model.Account;
import com.revolut.model.Customer;



public class CustomerControllerTest extends JerseyTest {

 @Override
 public Application configure() {
  return new ResourceConfig(CustomerController.class);
 }

 @Test
 public void testGetById() {
  Response output = target("/customer/1").request().get();
  assertEquals("Should return status 200", 200, output.getStatus());
  assertNotNull("Should return user object as json", output.getEntity());
  System.out.println(output.getStatus());
  System.out.println(output.readEntity(String.class));
 }

 @Test
 public void testCreate() {
	  Form form = new Form();
	  form.param("name", "lalit");
	  Response output = target("/customer/add").request().post(Entity.form(form));
	  System.out.println(output.getStatus());
	  assertEquals("Should return status 200", 200, output.getStatus());
 }

}
