package cs211.project.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import cs211.project.services.FXRouter;

import java.io.IOException;

public class MainPageController {
    @FXML
    private ImageView backgroundImageView;

    @FXML
    public void initialize() {
        Image backgroundimage = new Image(getClass().getResource("/images/mainpage.png").toString());  // แบบที่ 1
        backgroundImageView.setImage(backgroundimage);
    }


    @FXML
    public void ButtonToConquerorMembers(){

        try{
            FXRouter.goTo("conquerorMembers");

        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void ButtonToRegister(){

        try{
            FXRouter.goTo("createAccount");

        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void ButtonToLogIn(){

        try{
            FXRouter.goTo("logIn");

        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
