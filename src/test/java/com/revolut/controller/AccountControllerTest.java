package com.revolut.controller;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import com.revolut.model.Account;



public class AccountControllerTest extends JerseyTest {

 @Override
 public Application configure() {
  return new ResourceConfig(AccountController.class);
 }

 @Test
 public void testGetById() {
  Response output = target("/account/1").request().get();
  assertEquals("Should return status 200", 200, output.getStatus());
  assertNotNull("Should return user object as json", output.getEntity());
  System.out.println(output.getStatus());
  System.out.println(output.readEntity(String.class));
 }

 @Test
 public void testCreate() {
  Account acc = new Account();
  acc.setCustId(1L);
  acc.setBalance(1000.0);
  Response output = target("/account/add").request(MediaType.APPLICATION_FORM_URLENCODED).post(Entity.entity(acc, MediaType.APPLICATION_JSON));
  System.out.println(output.getStatus());
  assertEquals("Should return status 201", 201, output.getStatus());
 }

}
