package com.example;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.model.Activity;
import com.example.model.User;
import com.example.repository.ActivityRepository;
import com.example.repository.ActivityRepositoryStub;

@Path("activities")
// http://localhost:8080/simple-service-webapp/webapi/activities
public class ActivityResource {

	private ActivityRepository activityRepository = new ActivityRepositoryStub();
	
	
	@DELETE
	@Path("{activityId}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response delete(@PathParam("activityId") String activityId) {
		
		System.out.println(activityId);
		activityRepository.delete(activityId);
		
		return Response.ok().build();
	}
	

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Activity> getAllActivities() {

		return activityRepository.findAllActivities();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{activityId}")
	// http://localhost:8080/simple-service-webapp/webapi/activities/1234
	public Response getActivity(@PathParam("activityId") String activityId) {
		
		if(activityId==null || activityId.length()<4){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		Activity activity = activityRepository.findActivity(activityId);
		
		if(activity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(activity).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{activityId}/user")
	// http://localhost:8080/simple-service-webapp/webapi/activities/1234/user
	public User getActivityUser(@PathParam("activityId") String activityId) {

		return activityRepository.findActivity(activityId).getUser();
	}
	
	
	@POST
	@Path("activity")
	// http://localhost:8080/simple-service-webapp/webapi/activities/activity
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Activity createActivityParams(MultivaluedMap<String, String> formParams) {
		
		System.out.println(formParams.getFirst("description"));
		System.out.println(formParams.getFirst("duration"));
		
		Activity activity = new Activity();
		activity.setDescription(formParams.getFirst("description"));
		activity.setDuration(Integer.parseInt(formParams.getFirst("duration")));
		
		activityRepository.create(activity);
		
		return activity;
	}
	
	@POST
	@Path("activity")
	// http://localhost:8080/simple-service-webapp/webapi/activities/activity
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Activity createActivity(Activity activity){
		System.out.println("Received Request "+activity.getDescription()+" "+activity.getDuration());
		activityRepository.create(activity);
		return activity;
	}
	
	
	@PUT
	@Path("{activityId}")
	// http://localhost:8080/simple-service-webapp/webapi/activities/activity
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateActivity(Activity activity){
		System.out.println("Received Request "+ activity.getId()+" "+activity.getDescription()+" "+activity.getDuration());
			
		activityRepository.update(activity);
		
		return Response.ok().entity(activity).build();
		
	}
	
	
}
