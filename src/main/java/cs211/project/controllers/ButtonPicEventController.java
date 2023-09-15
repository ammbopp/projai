package cs211.project.controllers;

import cs211.project.cs211661project.SearchEvent;
import cs211.project.models.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ButtonPicEventController {
    @FXML
    private ImageView eventPic;

    @FXML
    private Label nameEventLabel;

    private Event event;
    private SearchEvent searchEvent;

    @FXML
    private void click(MouseEvent mouseEvent){
        searchEvent.onClickPic(event);
    }



    public void setData(Event event,SearchEvent searchEvent) {
        this.event = event;
        this.searchEvent = searchEvent;
        nameEventLabel.setText(event.getEventName());
        Image image = new Image(getClass().getResourceAsStream(event.getEventPic()));
        eventPic.setImage(image);
    }
}
