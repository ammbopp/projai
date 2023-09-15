package cs211.project.models;

public class UserEvent{
    private String name;
    private String username;
    private String eventName;

    public UserEvent(String name, String username, String eventName) {
        this.name = name;
        this.username = username;
        this.eventName = eventName;
    }

    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }
    public String getEventName() {
        return eventName;
    }
    public void changeEventName(String eventName) {
        if (!eventName.trim().equals("")) {
            this.eventName = eventName;}
    }
    public void changeName(String name) {
        if (!name.trim().equals("")) {
            this.name = name;}
    }
    public boolean isUsername(String username) {
        return this.username.equals(username);
    }
}
