package cs211.project.controllers;

import cs211.project.services.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.io.IOException;

public class EditTeamsActivityController {

    @FXML
    private ImageView backgroundImageView;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    void dueSelectDate(ActionEvent event) {

    }

    @FXML
    void startSelectDate(ActionEvent event) {

    }

    @FXML
    public void initialize() {

        Image backgroundimage = new Image(getClass().getResource("/images/IMG_1917.png").toString());  // แบบที่ 1
        backgroundImageView.setImage(backgroundimage);

    }
    @FXML
    public void backtolistofteam(){
        try{
            FXRouter.goTo("listteams");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
