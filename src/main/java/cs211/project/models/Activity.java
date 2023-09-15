package cs211.project.models;

public class Activity {
    private String activityName;
    private String activityDetail;
    private String activityStartDate;
    private String activityDueDate;
    private String eventName;
    private String teamName;

    public Activity( String activityName, String activityDueDate, String activityDetail) {
        this.activityName = activityName;
        this.activityDueDate = activityDueDate;
        this.activityDetail = activityDetail;
    }
    public Activity(String activityName, String activityDetail,String activityStartDate,String activityDueDate, String eventName) {
        this.activityName = activityName;
        this.activityDetail = activityDetail;
        this.activityStartDate = activityStartDate;
        this.activityDueDate = activityDueDate;
        this.eventName =eventName;
        this.teamName = "-";
    }
    public Activity(String activityName, String activityDetail, String activityStartDate,String activityDueDate, String eventName, String teamName) {
        this.activityName = activityName;
        this.activityDetail = activityDetail;
        this.activityStartDate = activityStartDate;
        this.activityDueDate = activityDueDate;
        this.eventName = eventName;
        this.teamName = teamName;
    }

    public String getActivityName() {
        return activityName;
    }
    public String getActivityDueDate() {
        return activityDueDate;
    }
    public String getActivityStartDate() {
        return activityStartDate;
    }
    public String getActivityDetail() {
        return activityDetail;
    }
    public String getEventName() {
        return eventName;
    }
    public String getTeamName() {
        return teamName;
    }

    public void changeActivityName(String activityName) {
        if (!activityName.trim().equals("")) {
            this.activityName = activityName;}
    }
    public void changeActivityDueDate(String activityDueDate) {
        if (!activityDueDate.trim().equals("")) {
            this.activityDueDate = activityDueDate;}
    }
    public void changeActivityStartDate(String activityStartDate) {
        if (!activityStartDate.trim().equals("")) {
            this.activityStartDate = activityStartDate;}
    }
    public void changeActivityDetail(String activityDetail) {
        if (!activityDetail.trim().equals("")) {
            this.activityDetail = activityDetail;}
    }
    public void changeEventName(String eventName) {
        if (!eventName.trim().equals("")) {
            this.eventName = eventName;}
    }

    public boolean isActivityName(String activityName) {
        return this.activityName.equals(activityName);
    }

    @Override
    public String toString() {
        return "{" +
                "activityName:'" + activityName + '\'' +
                ", activityStartDate:'" + activityStartDate + '\'' +
                ", activityDueDate:'" + activityDueDate + '\'' +
                ", activityDetail:'" + activityDetail + '\'' +
                '}';
    }
}
