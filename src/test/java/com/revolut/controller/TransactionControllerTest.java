package com.revolut.controller;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import com.revolut.enums.MoneyTransactionStatus;
import com.revolut.model.Account;
import com.revolut.model.MoneyTransaction;



public class TransactionControllerTest extends JerseyTest {

 @Override
 public Application configure() {
  return new ResourceConfig(TransactionController.class);
 }

 @Test
 public void testGetById() {
  Response output = target("/moneytransaction/1").request().get();
  assertEquals("Should return status 204", 204, output.getStatus());
  assertNotNull("Should return user object as json", output.getEntity());
  System.out.println(output.getStatus());
  System.out.println(output.readEntity(String.class));
 }

 @Test
 public void testCreate() { 
  Form form = new Form();
  form.param("amount", "1");
  form.param("id2", "1");
  form.param("id1","2");
  Response output = target("/moneytransaction/sendmoney").request().post(Entity.form(form));
  System.out.println(output.getStatus());
  assertEquals("Should return status 200", 200, output.getStatus());
 }

}
