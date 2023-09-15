package cs211.project.controllers;

import cs211.project.services.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class JoinTeamController {
    @FXML
    private ImageView backgroundImageView;

    @FXML
    public void initialize() {
        Image backgroundimage = new Image(getClass().getResource("/images/mainpage.png").toString());  // แบบที่ 1
        backgroundImageView.setImage(backgroundimage);
    }

    @FXML
    public void BackToUserEvent(){

        try{
            FXRouter.goTo("userEvent");

        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void ButtonToTeam(){

        try{
            FXRouter.goTo("team");

        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}