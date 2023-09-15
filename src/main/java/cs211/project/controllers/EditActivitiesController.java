package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.UserEvent;
import cs211.project.models.collections.EventCollection;
import cs211.project.models.collections.UserEventCollection;
import cs211.project.services.Datasource;
import cs211.project.services.FXRouter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import cs211.project.models.Activity;
import cs211.project.models.collections.ActivityCollection;
import cs211.project.services.ActivityCollectionFileDatasource;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.time.LocalDate;

public class EditActivitiesController {
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private TableView<Activity> activitiesTableView;
    @FXML
    TextField activityNameText;
    @FXML
    TextField activityDetailText;
    @FXML
    DatePicker dueDatePicker;
    @FXML
    DatePicker startDatePicker;
    @FXML
    private TableColumn<Activity, String> activityNameColumn;
    @FXML
    private TableColumn<Activity, String> detailColumn;
    @FXML
    private TableColumn<Activity, String> startDateColumn;
    @FXML
    private TableColumn<Activity, String> dueDateColumn;

    private Datasource<ActivityCollection> datasource;
    private ActivityCollection activityCollection;
    private String eventName;

    @FXML
    public void initialize() {
        Image backgroundimage = new Image(getClass().getResource("/images/horizontal background.png").toString());  // แบบที่ 1
        backgroundImageView.setImage(backgroundimage);

        datasource = new ActivityCollectionFileDatasource("data","activity.csv");
        activityCollection = datasource.readData();

        eventName = (String) FXRouter.getData();
        ActivityCollection filteredActivities = filterActivities(activityCollection, eventName);

        activityNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        detailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        startDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dueDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        initializeEditableCells();
        showTable(filteredActivities);

    }


    private void showTable(ActivityCollection activityCollection) {
        activitiesTableView.getColumns().clear();
        activitiesTableView.getColumns().add(activityNameColumn);
        activitiesTableView.getColumns().add(detailColumn);
        activitiesTableView.getColumns().add(startDateColumn);
        activitiesTableView.getColumns().add(dueDateColumn);

        activityNameColumn.setCellValueFactory(new PropertyValueFactory<>("activityName"));
        detailColumn.setCellValueFactory(new PropertyValueFactory<>("activityDetail"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("activityStartDate"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("activityDueDate"));

        activitiesTableView.getItems().clear();
        for (Activity activity: activityCollection.getActivities()) {
            activitiesTableView.getItems().add(activity);
        }
        // อัพเดทข้อมูลในไฟล์
        datasource.writeData(activityCollection);
    }

    private ActivityCollection filterActivities(ActivityCollection activityCollection, String eventName) {
        ActivityCollection filteredCollection = new ActivityCollection();

        for (Activity activity : activityCollection.getActivities()) {
            if (activity.getEventName().equals(eventName) && activity.getTeamName().equals("-")) {
                filteredCollection.addActivity(activity);
            }
        }
        return filteredCollection;
    }

    @FXML
    private void addData(ActionEvent event){
        String activityName = activityNameText.getText();
        String activityDetail = activityDetailText.getText();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate dueDate = dueDatePicker.getValue();

        if (!activityName.isEmpty() && !activityDetail.isEmpty() && dueDate != null) {
            String startDateStr = startDate.toString();
            String dueDateStr = dueDate.toString();
            String eventName = (String) FXRouter.getData();

            Activity newData = new Activity(activityName, activityDetail, startDateStr, dueDateStr, eventName);
            activityCollection.addActivity(newData);
            datasource.writeData(activityCollection);

            activitiesTableView.getItems().add(newData);

            activityNameText.clear();
            activityDetailText.clear();
            startDatePicker.getEditor().clear(); // เคลียร์ค่าใน DatePicker
            dueDatePicker.getEditor().clear(); // เคลียร์ค่าใน DatePicker
        } else {
            System.out.println("Fields should not be empty.");
        }
    }
    @FXML
    private void deleteData(ActionEvent event){
        TableView.TableViewSelectionModel<Activity> selectionModel = activitiesTableView.getSelectionModel();
        if(selectionModel.isEmpty()){
            System.out.println("You need select before deleting.");
            return;
        }

        ObservableList<Activity> selectedItems = selectionModel.getSelectedItems();
        activityCollection.getActivities().removeAll(selectedItems);

        datasource.writeData(activityCollection);

        activitiesTableView.getItems().removeAll(selectedItems);
    }
    private void initializeEditableCells() {
        // activityName
        activityNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        activityNameColumn.setOnEditCommit(event -> {
            Activity activity = event.getTableView().getItems().get(event.getTablePosition().getRow());
            activity.changeActivityName(event.getNewValue());
            datasource.writeData(activityCollection);
        });

        // detail
        detailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        detailColumn.setOnEditCommit(event -> {
            Activity activity = event.getTableView().getItems().get(event.getTablePosition().getRow());
            activity.changeActivityDetail(event.getNewValue());
            datasource.writeData(activityCollection);
        });

        // startDate
        startDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        startDateColumn.setOnEditCommit(event -> {
            Activity activity = event.getTableView().getItems().get(event.getTablePosition().getRow());
            activity.changeActivityDueDate(event.getNewValue());
            datasource.writeData(activityCollection);
        });

        // dueDate
        dueDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dueDateColumn.setOnEditCommit(event -> {
            Activity activity = event.getTableView().getItems().get(event.getTablePosition().getRow());
            activity.changeActivityDueDate(event.getNewValue());
            datasource.writeData(activityCollection);
        });
    }


    @FXML
    public void backToCreatorEvent(){
        try{
            FXRouter.goTo("creatorEvent",eventName);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
