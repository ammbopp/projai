package cs211.project.controllers;

import cs211.project.services.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ConquerorMemberController {
    @FXML
    private ImageView profilePangImageView;
    @FXML
    private ImageView profileAmmImageView;
    @FXML
    private ImageView profileMookImageView;
    @FXML
    private ImageView profileJapanImageView;
    @FXML
    private ImageView backgroundImageView;

    @FXML
    public void initialize() {

        Image profilePang = new Image(getClass().getResource("/images/defaultpic.jpg").toString());
        profilePangImageView.setImage(profilePang);
        Image profileAmm = new Image(getClass().getResource("/images/defaultpic.jpg").toString());
        profileAmmImageView.setImage(profileAmm);
        Image profileMook = new Image(getClass().getResource("/images/defaultpic.jpg").toString());
        profileMookImageView.setImage(profileMook);
        Image profileJapan = new Image(getClass().getResource("/images/defaultpic.jpg").toString());
        profileJapanImageView.setImage(profileJapan);
        Image backgroundimage = new Image(getClass().getResource("/images/horizontal background.png").toString());  // แบบที่ 1
        backgroundImageView.setImage(backgroundimage);
    }

    @FXML
    public void backToMainPage(){

        try{
            FXRouter.goTo("mainPage");

        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
