package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.collections.EventCollection;
import cs211.project.services.Datasource;
import cs211.project.services.EventCollectionFileDatasource;
import cs211.project.services.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class CreateEventController {
    @FXML
    private ImageView eventpicImagaView;
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private TextField eventNameTextField;
    @FXML
    private TextArea eventDetailTextArea;
    @FXML
    private TextField eventStartDateTextField;
    @FXML
    private TextField eventDueDateTextField;
    private Datasource<EventCollection> datasource;
    private EventCollection eventCollection;
    @FXML
    public void initialize() {
        Image backgroundimage = new Image(getClass().getResource("/images/IMG_1917.png").toString());  // แบบที่ 1
        backgroundImageView.setImage(backgroundimage);
        Image eventimage = new Image(getClass().getResource("/images/eventdefaultpic.png").toString());  // แบบที่ 1
        eventpicImagaView.setImage(eventimage);

        datasource = new EventCollectionFileDatasource("data","event.csv");
        eventCollection = datasource.readData();
    }
    @FXML
    public void enterToMyProfile(){
        try{
            Event event = new Event(eventNameTextField.getText(),eventDetailTextArea.getText(),eventStartDateTextField.getText(),eventDueDateTextField.getText());
            FXRouter.goTo("myProfile");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void cancelToMyProfile(){
        try{
            FXRouter.goTo("searchEvent");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
