package cs211.project.models.collections;

import cs211.project.models.UserEvent;
import java.util.ArrayList;
public class UserEventCollection {
    private ArrayList<UserEvent> userEvents;
    public UserEventCollection() {
        userEvents = new ArrayList<>();
    }
    public UserEvent findUserEventByUsername(String username) {
        for (UserEvent userEvent : userEvents) {
            if (userEvent.isUsername(username)) {
                return userEvent;
            }
        }
        return null;
    }
    public void addUserEvent(String name, String username, String eventName) {
        UserEvent exist = findUserEventByUsername(username);
        if (exist == null) {
            userEvents.add(new UserEvent(name, username, eventName));
        }
    }
    public void addUserEvent(UserEvent userEvent) {
        String username = userEvent.getUsername();
        UserEvent exist = findUserEventByUsername(username);
        if (exist == null) {
            userEvents.add(userEvent);
        }
    }

    public ArrayList<UserEvent> getUserEvents() {
        return (ArrayList<UserEvent>) userEvents;
    }


}
