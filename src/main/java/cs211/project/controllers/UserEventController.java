package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.collections.EventCollection;
import cs211.project.services.Datasource;
import cs211.project.services.EventCollectionFileDatasource;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import cs211.project.services.FXRouter;

import java.io.IOException;

public class UserEventController {
    @FXML
    private ImageView bgPic;
    @FXML
    private ImageView eventSample;
    @FXML
    private Label DetailLabel;

    @FXML
    private Label DueDateLabel;

    @FXML
    private Label StartDateLabel;
    @FXML
    private Label neLabel;
    private Datasource<EventCollection> datasource;
    private EventCollection eventCollection;
    private Event event;
    @FXML
    public void initialize() {
        Image bg = new Image(getClass().getResourceAsStream("/images/mainpage.png"));
        bgPic.setImage(bg);

        Image eventPic = new Image(getClass().getResourceAsStream("/images/eventdefaultpic.png"));
        eventSample.setImage(eventPic);

        datasource = new EventCollectionFileDatasource("data","event.csv");
        eventCollection = datasource.readData();

        String eventName = (String) FXRouter.getData();

        if(eventName != null) {
            event = eventCollection.findEventByEventName(eventName);
        }

        showEvent(event);
    }

    private void showEvent(Event event) {
        neLabel.setText(event.getEventName());
        DetailLabel.setText(event.getEventDetail());
        StartDateLabel.setText(event.getStartDate());
        DueDateLabel.setText(event.getDueDate());
    }
    @FXML
    public void goToActivity(){
        try{
            FXRouter.goTo("userActivity");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void goToEventButtonClick(){
        try{
            FXRouter.goTo("myProfile");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void goToSearchEvent(){
        try{
            FXRouter.goTo("searchEvent");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void toJoinTeamButtonClick(){
        try{
            FXRouter.goTo("joinTeam");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void toProfileButtonClick(){
        try{
            FXRouter.goTo("myProfile");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
