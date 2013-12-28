package com.example.repository;

import java.util.ArrayList;
import java.util.List;
import com.example.model.Activity;
import com.example.model.User;

public class ActivityRepositoryStub implements ActivityRepository {

	/* (non-Javadoc)
	 * @see com.example.repository.ActivityResource#findAllActivities()
	 */
	@Override
	public List<Activity> findAllActivities() {

		List<Activity> activities = new ArrayList<Activity>();

		Activity activity1 = new Activity();
		activity1.setDescription("Swimming");
		activity1.setDuration(10);

		activities.add(activity1);

		Activity activity2 = new Activity();
		activity2.setDescription("Cycling");
		activity2.setDuration(10);

		activities.add(activity1);

		return activities;

	}

	@Override
	public Activity findActivity(String activityId) {
		
		if(activityId.equals("7777")){
			return null;
		}

		Activity activity1 = new Activity();
		activity1.setId("1234");
		activity1.setDescription("Swimming");
		activity1.setDuration(10);
		
		User user = new User();
		user.setId("1234");
		user.setName("Noor");
		
		activity1.setUser(user); 
		
		return activity1;
		
	}

	@Override
	public void create(Activity activity) {
		//should insert into DB
		
		
	}
	
	
	@Override
	public Activity update(Activity activity) {
		// search the database to see if we have an activity of that id.
		//select * from activity where id=?. If rs.size=0, insert into database else update
		return activity;
	}

	@Override
	public void delete(String activityId) {
		
		System.out.println("Deleteing activity Id "+activityId);
		
	}

}



