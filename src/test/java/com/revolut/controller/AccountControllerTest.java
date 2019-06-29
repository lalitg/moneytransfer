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



public class AccountControllerTest extends JerseyTest {

 @Override
 public Application configure() {
     enable(TestProperties.LOG_TRAFFIC);
     enable(TestProperties.DUMP_ENTITY);
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
  Form form = new Form();
  form.param("custId", "1");
  form.param("balance", "1000");
  Response output = target("/account/add").request().post(Entity.form(form));
  System.out.println(output.getStatus());
  assertEquals("Should return status 200", 200, output.getStatus());
 }

}
