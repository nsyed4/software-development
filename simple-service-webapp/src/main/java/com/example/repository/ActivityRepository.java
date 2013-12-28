package com.example.repository;

import java.util.List;

import com.example.model.Activity;

public interface ActivityRepository {

	List<Activity> findAllActivities();

	Activity findActivity(String activityId);

	void create(Activity activity);

	Activity update(Activity activity);

	void delete(String activityId);

}