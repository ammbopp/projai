package cs211.project.controllers;

import cs211.project.models.collections.UserTeamCollection;
import cs211.project.services.Datasource;
import cs211.project.services.FXRouter;
import cs211.project.services.TeamCollectionFileDatasource;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TeamController {

    @FXML
    private ImageView backgroundImageView;

    @FXML
    private TextArea commentArea;

    @FXML
    private TextField writecommentTextField;

    private Datasource<UserTeamCollection> Teamdatasource;
    private UserTeamCollection userTeamCollection;

    @FXML
    public void initialize() {
        Image backgroundimage = new Image(getClass().getResource("/images/horizontal background.png").toString());  // แบบที่ 1
        backgroundImageView.setImage(backgroundimage);

        Teamdatasource = new TeamCollectionFileDatasource("data", "teams.csv");
        userTeamCollection = Teamdatasource.readData();

    }

    @FXML
    void sendCommentButton(ActionEvent event) {
        String comment = writecommentTextField.getText();
        commentArea.appendText(comment+"\n");

    }
    @FXML
    public void backToUserEventButton(){
        try{
            FXRouter.goTo("userEvent");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }


}
