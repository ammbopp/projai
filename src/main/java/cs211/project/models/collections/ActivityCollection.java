package cs211.project.models.collections;
import cs211.project.models.Activity;

import java.util.ArrayList;
public class ActivityCollection {
    private ArrayList<Activity> activities;
    public ActivityCollection() {
        activities = new ArrayList<>();
    }
    public Activity findActivityByActivityName(String activityName) {
        for (Activity activity : activities) {
            if (activity.isActivityName(activityName)) {
                return activity;
            }
        }
        return null;
    }
    public void addActivity(String activityName, String activityDetail,
                            String activityStartDate ,String activityDueDate, String eventName, String teamName) {
        Activity exist = findActivityByActivityName(activityName);
        activityName = activityName.trim();
        eventName = eventName.trim();
        if (exist == null) {
            activities.add(new Activity(activityName, activityDetail, activityStartDate, activityDueDate, eventName, teamName));
        }
    }
    public void addActivity(String activityName, String activityDetail,
                            String activityStartDate ,String activityDueDate, String eventName) {
        Activity exist = findActivityByActivityName(activityName);
        activityName = activityName.trim();
        eventName = eventName.trim();
        if (exist == null) {
            activities.add(new Activity(activityName, activityDetail, activityStartDate, activityDueDate, eventName));
        }
    }
    public ArrayList<Activity> getActivities() {
        return (ArrayList<Activity>) activities;
    }

    public void addActivity(Activity activity) {
        String activityName = activity.getActivityName();
        Activity exist = findActivityByActivityName(activityName);
        if (exist == null) {
            activities.add(activity);
        }
    }
}
