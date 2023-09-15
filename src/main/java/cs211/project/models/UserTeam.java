package cs211.project.models;


public class UserTeam {
    private String teamName;
    private String userName;
    private String name;

    private int maxMember;
    private String eventName;

    private String startDate;
    private String dueDate;

    public UserTeam(String teamName, int maxMember, String startDate, String dueDate){
        this.teamName = teamName;
        this.maxMember = maxMember;
        this.startDate=startDate;
        this.dueDate=dueDate;
    }

    public UserTeam(String name,String userName){

        this.name=name;
        this.userName = userName;
    }
    public void setStartDate(String date) {
        this.startDate=date;
    }
    public void setDueDate(String date) {
        this.dueDate=date;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getDueDate() {
        return dueDate;
    }
    public String getTeamName() {
        return teamName;
    }
    public int getMaxMember() {
        return maxMember;
    }

    public String getName() {
        return name;
    }
    public String getUserName() {
        return userName;
    }
    public String getEventName() {
        return eventName;
    }
    public boolean isTeamName(String teamname) {
        return this.teamName.equals(teamname);
    }

    public boolean isThisUserinThisteam(String username) {
        return this.userName.equals(username);
    }


}
