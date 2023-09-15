package cs211.project.models;

public class Event {
    private String eventName;
    private String eventDetail;
    private int seatFull;
    private String startDate;
    private String dueDate;
    private String creatorUsername;
    private String eventPic = "/images/event/eventdefaultpic.png";

    public Event(String eventName, String eventDetail, String startDate, String dueDate) {
        this.eventName = eventName;
        this.eventDetail = eventDetail;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    public Event(String eventName, String eventDetail, int seatFull, String startDate, String dueDate) {
        this.eventName = eventName;
        this.eventDetail = eventDetail;
        this.seatFull = seatFull;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }
    public Event(String eventName, String eventDetail, int seatFull, String startDate,
                 String dueDate, String creatorUsername, String eventPic) {
        this.eventName = eventName;
        this.eventDetail = eventDetail;
        this.seatFull = seatFull;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.creatorUsername = creatorUsername;
        this.eventPic = eventPic;
    }

    public String getEventName() { return eventName; }
    public String getEventDetail() {
        return eventDetail;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getDueDate() {
        return dueDate;
    }
    public int getSeatFull() {
        return seatFull;
    }
    public String getCreatorUsername() {
        return creatorUsername;
    }
    public String getEventPic() {
        return eventPic;
    }

    public void changeEventName(String eventName) {
        if (!eventName.trim().equals("")) {
            this.eventName = eventName.trim();
        }
    }
    public void changeEventDetail(String eventDetail) {
        if (!eventDetail.trim().equals("")) {
            this.eventDetail = eventDetail.trim();
        }
    }
    public void changeStartDate(String startDate) {
        if (!startDate.trim().equals("")) {
            this.startDate = startDate.trim();
        }
    }
    public void changeDueDate(String dueDate) {
        if (!dueDate.trim().equals("")) {
            this.dueDate = dueDate.trim();
        }
    }
    public void changeSeatFull(int seatFull) {
        if (seatFull>0) {
            this.seatFull = seatFull;
        }
    }
    public void changeEventPic(String eventPic) {
        if (!eventPic.trim().equals("")) {
            this.eventPic = eventPic.trim();
        }
    }
    public boolean isEventName(String eventName) {
        return this.eventName.equals(eventName);
    }
    public boolean isCreatorUsername(String creatorUsername){
        return this.creatorUsername.equals(creatorUsername);
    }
    @Override
    public String toString() {
        return  eventName;
    }
}
