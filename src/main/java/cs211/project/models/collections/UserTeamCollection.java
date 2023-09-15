package cs211.project.models.collections;

import cs211.project.models.Team;
import cs211.project.models.User;
import cs211.project.services.Datasource;
import cs211.project.services.TeamCollectionFileDatasource;

import java.util.ArrayList;

public class UserTeamCollection {
    private ArrayList<Team> userTeams;
    private Datasource<UserTeamCollection> datasource;
    private UserList userList;
    public UserTeamCollection() {
        userTeams = new ArrayList<>();
        userList = new UserList();
        datasource = new TeamCollectionFileDatasource("data", "teams.csv");
    }

    public void addNewTeam(String teamName,String maxMember,String startDate,String dueDate,String eventName) {
        teamName = teamName.trim();
        maxMember = maxMember.trim();
        startDate = startDate.trim();
        dueDate = dueDate.trim();
        eventName=eventName.trim();

        if (!teamName.isEmpty() && !maxMember.isEmpty() && !startDate.isEmpty() && !dueDate.isEmpty()) {
            Team exist = findTeamByTeamName(teamName);
            if (exist == null) {
                userTeams.add(new Team(teamName, maxMember, startDate, dueDate,eventName));
            }
        }
    }

    public Team findTeamByTeamName(String teamName) {
        for (Team team : userTeams) {
            if (team.getTeamName().equals(teamName)) {
                return team;
            }
        }
        return null;
    }
    public void addNewTeamMember(String teamName, String userName) {
        teamName = teamName.trim();
        userName = userName.trim();
        if (!teamName.equals("") && !userName.equals("")) {
            User exist = userList.findUserByUsername(userName);
            if (exist == null) {
                userTeams.add(new Team(teamName, userName));
            }
        }
    }

    public ArrayList<Team> getTeam() {
        return userTeams;
    }

}
