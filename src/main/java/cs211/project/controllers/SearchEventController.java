package cs211.project.controllers;
import cs211.project.cs211661project.SearchEvent;
import cs211.project.models.Event;
import cs211.project.models.collections.EventCollection;
import cs211.project.services.Datasource;
import cs211.project.services.EventCollectionFileDatasource;
import cs211.project.services.FXRouter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchEventController implements Initializable {
    @FXML
    private ImageView bgPic;

    @FXML
    private VBox chosenEventCard;

    @FXML
    private Label dueDateLabel;

    @FXML
    private Label seatFullLabel;

    @FXML
    private Label eventNameLabel;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    private Datasource<EventCollection> datasource;

    private EventCollection eventCollection;

    private String currentUser;

    private Event event;

    private Image image;

    private SearchEvent searchEvent;

    private List<Event> events = new ArrayList<>();

    private List<Event> getData(){
        List<Event> events = new ArrayList<>();
        Event event;

        for (int i = 0 ; i < 20 ; i++) {
            //event = new Event();
            //event.getEventName();
            //event.add(event);
        }
        return events;
    }

    private void setChosenEventCard(Event event) {
        eventNameLabel.setText(event.getEventName());
        seatFullLabel.setText(String.valueOf(event.getSeatFull()));
        dueDateLabel.setText(event.getDueDate());
        image = new Image(getClass().getResourceAsStream(event.getEventPic()));

    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        events.addAll(getData());

        if(events.size()>0){
            setChosenEventCard(events.get(0));
            searchEvent = new SearchEvent() {
                @Override
                public void onClickPic(Event event) {
                    setChosenEventCard(event);
                }
            };
        }
        Image bg = new Image(getClass().getResourceAsStream("/images/mainpage.png"));
        bgPic.setImage(bg);
        datasource = new EventCollectionFileDatasource("data", "event.csv");
        eventCollection = datasource.readData();

        currentUser = (String) FXRouter.getData();

        int column = 0, row = 1;
        try {
            for (int i = 0 ; i < events.size() ; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/button-pic-event.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ButtonPicEventController buttonPicEventController = fxmlLoader.getController();
                buttonPicEventController.setData(events.get(i),searchEvent);

                if(column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane,column++, row);
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane,new Insets(10));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void toCreateEvent(){
        try{
            FXRouter.goTo("createEvent");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void buttonToMyProfile(){
        try{
            FXRouter.goTo("myProfile", currentUser);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
