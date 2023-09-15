package cs211.project.controllers;

import cs211.project.models.Activity;
import cs211.project.models.Team;
import cs211.project.models.collections.ActivityCollection;
import cs211.project.models.collections.TeamCollection;
import cs211.project.models.collections.UserTeamCollection;
import cs211.project.services.Datasource;
import cs211.project.services.FXRouter;
import cs211.project.services.TeamCollectionFileDatasource;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ListTeamsController {


    @FXML
    private ImageView backgroundlistteamImageView;
    private Datasource<UserTeamCollection> Teamdatasource;
    private UserTeamCollection userTeamCollection;

    @FXML
    private TableView<Team> teamTableView;
    private String eventName;

    @FXML
    private Label eventNameLabel;


    @FXML
    public void initialize() {

        Image backgroundlistimage = new Image(getClass().getResource("/images/horizontal background.png").toString());  // แบบที่ 1
        backgroundlistteamImageView.setImage(backgroundlistimage);

        Teamdatasource = new TeamCollectionFileDatasource("data", "teams.csv");
        userTeamCollection = Teamdatasource.readData();

        eventName = (String) FXRouter.getData();
        eventNameLabel.setText(eventName);

        showTable(userTeamCollection);

        teamTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Team>() {
            @Override
            public void changed(ObservableValue observable, Team oldValue, Team newValue) {
                if (newValue != null) {
                    try {
                        // FXRouter.goTo สามารถส่งข้อมูลไปยังหน้าที่ต้องการได้ โดยกำหนดเป็น parameter ที่สอง
                        FXRouter.goTo("team");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
    private void showTable(UserTeamCollection teamCollection) {

        TableColumn<Team, String> teamNameColumn = new TableColumn<>("Team-name");
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        TableColumn<Team, String> maxMemberColumn = new TableColumn<>("Max Member");
        maxMemberColumn.setCellValueFactory(new PropertyValueFactory<>("maxMember"));
        TableColumn<Team, String> startDateColumn = new TableColumn<>("Start Accepting Member Date");
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        TableColumn<Team, String> dueDateColumn = new TableColumn<>("Due Accepting Member Date");
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        teamTableView.getColumns().clear();
        teamTableView.getColumns().add(teamNameColumn);
        teamTableView.getColumns().add(maxMemberColumn);
        teamTableView.getColumns().add(startDateColumn);
        teamTableView.getColumns().add(dueDateColumn);
        teamTableView.getItems().clear();

        for (Team team : teamCollection.getTeam()) {
            teamTableView.getItems().add(team);
        }

    }

    @FXML
    public void toEditTeamEventAcitvityButton(){
        try{
            FXRouter.goTo("editTeamAct");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void backToCreatorEventButton(){
        try{
            FXRouter.goTo("creatorEvent",eventName);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
