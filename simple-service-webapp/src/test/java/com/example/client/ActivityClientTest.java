package com.example.client;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.example.model.Activity;

public class ActivityClientTest {
	
	
	@Test
	public void testDelete() {
		
		ActivityClient client = new ActivityClient();
		client.delete("1234");
	}
	
	@Test
	public void testPut() {
		Activity activity = new Activity();
		activity.setDescription("Running");
		activity.setDuration(60);
		activity.setId("4567");
		
		ActivityClient client = new ActivityClient();
		activity = client.update(activity);
		
		assertNotNull(activity);
		
	}
	
	@Test
	public void testCreate() {
		
		Activity activity = new Activity();
		activity.setDescription("Running");
		activity.setDuration(60);
		
		ActivityClient client = new ActivityClient();
		activity = client.create(activity);
		
		assertNotNull(activity);
	}

	@Test
	public void testGet() {

		ActivityClient client = new ActivityClient();

		Activity activity = client.get("1234");

		System.out.println(activity);

		assertNotNull(activity);
	}

	@Test
	public void testGetList() {

		ActivityClient client = new ActivityClient();

		List<Activity> activities = client.getActivities();

		System.out.println(activities);

		assertNotNull(activities);
	}

	@Test(expected = RuntimeException.class)
	public void testGetWithBadRequest() {
		ActivityClient client = new ActivityClient();
		client.get("123");

	}
	
	@Test(expected = RuntimeException.class)
	public void testGetWithNotFound() {
		ActivityClient client = new ActivityClient();
		client.get("7777");

	}
	
	
	

}
