package cs211.project.controllers;

import cs211.project.models.User;
import cs211.project.models.collections.UserList;
import cs211.project.services.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import cs211.project.services.EventCollectionFileDatasource;
import cs211.project.services.Datasource;
import cs211.project.models.Event;
import cs211.project.models.collections.EventCollection;

import java.io.IOException;

public class CreatorEventController {
    @FXML
    private ImageView bgPic;
    @FXML
    private ImageView eventPic;
    @FXML
    private Label eventNameLabel;
    @FXML
    private Label eventDetailLabel;
    @FXML
    private Label seatFullLabel;
    @FXML
    private Label startDateLabel;
    @FXML
    private Label dueDateLabel;

    private Datasource<EventCollection> eventCollectionDatasource;
    private EventCollection eventCollection;
    private Event event;
    Image image;

    @FXML
    public void initialize() {
        Image bg = new Image(getClass().getResourceAsStream("/images/mainpage.png"));
        bgPic.setImage(bg);

        eventCollectionDatasource = new EventCollectionFileDatasource("data","event.csv");
        eventCollection = eventCollectionDatasource.readData();

        String eventName = (String) FXRouter.getData();
        if(eventName != null) {
            event = eventCollection.findEventByEventName(eventName);
            image = new Image("file:" + event.getEventPic(), true);
        }
        showEvent(event);
    }

    private void showEvent(Event event){
        eventNameLabel.setText(event.getEventName());
        eventDetailLabel.setText(event.getEventDetail());
        seatFullLabel.setText(String.valueOf(event.getSeatFull()));
        startDateLabel.setText(event.getStartDate());
        dueDateLabel.setText(event.getDueDate());
        eventPic.setImage(image);
    }

    @FXML
    public void backToMyProfile(){
        try{
            FXRouter.goTo("myProfile");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void toEditEvent(){
        try{
            FXRouter.goTo("editEvent", event.getEventName());
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void toListOfMember(){
        try{
            FXRouter.goTo("member", event.getEventName());
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void toListOfTeam(){
        try{
            FXRouter.goTo("listteams",event.getEventName());
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void toCreateTeam(){
        try{
            FXRouter.goTo("createTeam",event.getEventName());
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }@FXML
    public void toEditActivities(){
        try{
            FXRouter.goTo("editActivities", event.getEventName());
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
