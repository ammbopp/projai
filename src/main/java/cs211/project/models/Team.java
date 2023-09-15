package cs211.project.models;


public class Team {
    private String teamName;
    private String maxMember;
    private String startDate;
    private String  dueDate;
    private String userName;
    private String eventName;

    public Team(String teamName,String userName){
        this.teamName=teamName;
        this.userName=userName;
    }

    public Team(String teamName,String maxMember,String startDate,String dueDate,String eventName){
        this.teamName=teamName;
        this.maxMember=maxMember;
        this.startDate=startDate;
        this.dueDate=dueDate;
        this.eventName=eventName;
    }

    public String getTeamName(){
        return this.teamName;
    }

    public String getMaxMember(){
        return this.maxMember;
    }
    public String getEventName(){return  this.eventName;}

    public String getStartDate(){
        return this.startDate;
    }

    public String getDueDate(){
        return this.dueDate;
    }

}
