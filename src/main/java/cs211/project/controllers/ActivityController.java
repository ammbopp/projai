package cs211.project.controllers;

import cs211.project.models.Activity;
import cs211.project.models.collections.ActivityCollection;
import cs211.project.services.ActivityCollectionFileDatasource;
import cs211.project.services.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ActivityController {
    @FXML
    private ImageView bgPic;
    @FXML
    private TableView<Activity> activitiesTableView;
    @FXML
    private ActivityCollection activityCollection;
    @FXML
    private ActivityCollectionFileDatasource datasource;

    @FXML
    public void initialize() {
        Image bg = new Image(getClass().getResourceAsStream("/images/horizontal background.png"));
        bgPic.setImage(bg);
        datasource = new ActivityCollectionFileDatasource("data","activity.csv");
        activityCollection = datasource.readData();
        showTable(activityCollection);

        activitiesTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Activity>() {
            @Override
            public void changed(ObservableValue observableValue, Activity oldValue, Activity newValue) {

                //@Override
                //public void changed(ObservableValue<? extends Event> observableValue, Event oldValue, Event newValue) {

                /*if (newValue == null) {
                    selectedEvent = null;
                } else {
                    selectedEvent = newValue;
                    try{
                        FXRouter.goTo("userEvent",newValue.getEventName());
                    } catch(IOException e){
                        throw new RuntimeException(e);
                    }
                }*/
                if (newValue != null) {
                    try {
                        FXRouter.goTo("editActivities",newValue.getActivityName());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
    }

    private void showTable(ActivityCollection activityCollection) {
        TableColumn<Activity, String> numActivityColumn = new TableColumn<>("No.");
        numActivityColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<Activity, String> activityNameColumn = new TableColumn<>("Activity name");
        activityNameColumn.setCellValueFactory(new PropertyValueFactory<>("activityName"));

        TableColumn<Activity, String> dueDateColumn = new TableColumn<>("Due date");
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("activityDueDate"));

        TableColumn<Activity, String> detailColumn = new TableColumn<>("Detail");
        detailColumn.setCellValueFactory(new PropertyValueFactory<>("activityDetail"));

        activitiesTableView.getColumns().clear();
        activitiesTableView.getColumns().add(numActivityColumn);
        activitiesTableView.getColumns().add(activityNameColumn);
        activitiesTableView.getColumns().add(dueDateColumn);
        activitiesTableView.getColumns().add(detailColumn);

        activitiesTableView.getItems().clear();
        for (Activity activity: activityCollection.getActivities()) {
            activitiesTableView.getItems().add(activity);
        }
    }
    @FXML
    public void backToUserEvent(){
        try{
            FXRouter.goTo("userEvent");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
