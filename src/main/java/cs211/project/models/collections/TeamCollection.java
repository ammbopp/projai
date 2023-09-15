package cs211.project.models.collections;
import cs211.project.models.UserTeam;

import java.util.ArrayList;
public class TeamCollection {
    private ArrayList<UserTeam> userTeams;
    public TeamCollection() {
        userTeams = new ArrayList<>();
    }

    public void addNewUserToTeam(String name, String userName) {
        userName = userName.trim();
        name = name.trim();
        if (!userName.equals("") && !name.equals("")) {
            UserTeam exist = findUserInTeam(userName);
            if (exist == null) {
                //add user to team
                userTeams.add(new UserTeam(name,userName));
            }
        }
    }
    public void addNewUserToTeam(String teamName, int maxMember, String startDate, String dueDate, String name, String userName) {
        userName = userName.trim();
        name = name.trim();
        if (!userName.equals("") && !name.equals("")) {
            UserTeam exist = findTeamName(teamName);
            if (exist == null) {
               //create new team csv
                userTeams.add(new UserTeam(teamName,maxMember,startDate,dueDate));
            }
            else{
                //add user to team
                userTeams.add(new UserTeam(name,userName));
            }
        }
    }

    public UserTeam findTeamName(String teamName) {
        for (UserTeam userTeam : userTeams) {
            if (userTeam.isTeamName(teamName)) {
                return userTeam;
            }
        }
        return null;
    }

    public UserTeam findUserInTeam(String userName) {
        for (UserTeam userTeam : userTeams) {
            if (userTeam.isThisUserinThisteam(userName)) {
                return userTeam;
            }
        }
        return null;
    }


    public ArrayList<UserTeam> getUserTeams() {
        return (ArrayList<UserTeam>) userTeams;
    }
}
