package com.example.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.model.Activity;

public class ActivityClient {

	private Client client;

	public ActivityClient() {
		client = ClientBuilder.newClient();
	}

	public Activity get(String activityId) {
		// http://localhost:8080/simple-service-webapp/webapi/activities/1234
		WebTarget target = client
				.target("http://localhost:8080/simple-service-webapp/webapi/");
		// Get me the default response type back whether it be json or xml and
		// bind it to Activity class

		// Activity response = target.path("activities/" + activityId).request()
		// .get(Activity.class);

		// String response1 = target.path("activities/" + activityId)
		// .request(MediaType.APPLICATION_JSON).get(String.class);
		// System.out.println(response1);

		Response response = target.path("activities/" + activityId).request()
				.get(Response.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus()
					+ " : there was an error");
		}

		return response.readEntity(Activity.class);

	}

	public List<Activity> getActivities() {
		// http://localhost:8080/simple-service-webapp/webapi/activities
		WebTarget target = client
				.target("http://localhost:8080/simple-service-webapp/webapi/");

		List<Activity> response = target.path("activities")
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Activity>>() {
				});
		System.out.println(response);

		return response;
	}

	public Activity create(Activity activity) {

		// http://localhost:8080/simple-service-webapp/webapi/activities/activity
		WebTarget target = client
				.target("http://localhost:8080/simple-service-webapp/webapi/");

		Response response = target.path("activities/activity")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(activity, MediaType.APPLICATION_JSON));

		System.out.println(response);

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus()
					+ " : there was an error on the server");
		}

		return response.readEntity(Activity.class);
	}

	
	public Activity update(Activity activity) {
		// http://localhost:8080/simple-service-webapp/webapi/activities/activity
		WebTarget target = client
				.target("http://localhost:8080/simple-service-webapp/webapi/");

		Response response = target.path("activities/"+activity.getId())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(activity, MediaType.APPLICATION_JSON));

		System.out.println(response);

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus()
					+ " : there was an error on the server");
		}

		return response.readEntity(Activity.class);
	}

	public void delete(String activityId) {
		
		WebTarget target = client
				.target("http://localhost:8080/simple-service-webapp/webapi/");

		Response response = target.path("activities/"+activityId)
				.request(MediaType.APPLICATION_JSON)
				.delete();

		System.out.println(response);

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus()
					+ " : there was an error on the server");
		}

	}

}
