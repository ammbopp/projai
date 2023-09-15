package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.collections.EventCollection;
import cs211.project.models.collections.UserTeamCollection;
import cs211.project.services.Datasource;
import cs211.project.services.EventCollectionFileDatasource;
import cs211.project.services.FXRouter;
import cs211.project.services.TeamCollectionFileDatasource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.time.LocalDate;

public class CreateTeamController {
    @FXML
    private ImageView backgroundcreateteamImageView;

    @FXML
    private Label eventNameLabel;

    @FXML
    private TextField teamNameTextField;

    @FXML
    private TextField maxMemberTextField;

    @FXML
    private DatePicker startAcceptDatePicker;

    @FXML
    private DatePicker dueAcceptDatePicker;

    @FXML
    private Label errorTeamNameLabel;

    private Datasource<UserTeamCollection> Teamdatasource;
    private UserTeamCollection userTeamCollection;
    private String eventName;

    @FXML
    public void initialize() {
        Image backgroundcreateimage = new Image(getClass().getResource("/images/IMG_1917.png").toString());
        backgroundcreateteamImageView.setImage(backgroundcreateimage);

        Teamdatasource = new TeamCollectionFileDatasource("data", "teams.csv");
        userTeamCollection = Teamdatasource.readData();


        eventName = (String) FXRouter.getData();

        eventNameLabel.setText(eventName);

    }
    @FXML
    void backToCreatorEventButton() {
        try{
            FXRouter.goTo("creatorEvent",eventName);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    private String startDate;
    private String dueDate;
    @FXML
    void dueAcceptSelectDate(ActionEvent event) {
        LocalDate myDate = dueAcceptDatePicker.getValue();
        if (myDate != null) {
            dueDate = myDate.toString();
        }
    }

    @FXML
    void startAcceptSelectDate(ActionEvent event) {
        LocalDate myDate = startAcceptDatePicker.getValue();
        if (myDate != null) {
            startDate = myDate.toString();
        }
    }


    @FXML
    void toTeamPageButton(ActionEvent event) {
        String teamName = teamNameTextField.getText();
        String maxMember = maxMemberTextField.getText();

        if (startDate == null || dueDate == null || teamName.isEmpty() || maxMember.isEmpty()) {
            errorTeamNameLabel.setText("Please fill in all fields.");
        } else {
            try{
                if (userTeamCollection.findTeamByTeamName(teamName) == null) {
                    userTeamCollection.addNewTeam(teamName, maxMember, startDate, dueDate,eventName);
                    Teamdatasource.writeData(userTeamCollection);

                    try {
                        FXRouter.goTo("team",teamName);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    errorTeamNameLabel.setText("This team-name is already exists.\nPlease change your team-name");
                }
            } catch ( RuntimeException e){
                errorTeamNameLabel.setText("Error loading data.");
            }
        }
    }



}
