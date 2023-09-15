package cs211.project.controllers;

import cs211.project.models.UserEvent;
import cs211.project.models.collections.UserEventCollection;
import cs211.project.services.Datasource;
import cs211.project.services.FXRouter;
import cs211.project.services.UserEventCollectionFileDatasource;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

import java.io.IOException;

public class MemberController {
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private ImageView profilepicImageView;
    @FXML
    TableView memberTableView;
    @FXML Label userNameLabel;
    @FXML Label nameLabel;

    private Datasource<UserEventCollection> datasource;
    private UserEventCollection userEventCollection;
    private UserEvent selectedUserEvent;
    private UserEvent userEvent;
    private String currentUser;

    @FXML
    public void initialize() {
        clearUserInfo();

        Image backgroundImage = new Image(getClass().getResource("/images/halfBg.png").toString());
        backgroundImageView.setImage(backgroundImage);
        Image profilePicImage = new Image(getClass().getResource("/images/defaultpic.jpg").toString());
        profilepicImageView.setImage(profilePicImage);

        datasource = new UserEventCollectionFileDatasource("data","user_event.csv");
        userEventCollection = datasource.readData();

        String eventName = (String) FXRouter.getData(); // รับค่าที่ส่งมาจากหน้า CreatorEvent
        UserEventCollection filteredUserEventCollection = filterUserEvents(userEventCollection, eventName);
        // ให้กรองเอาเฉพาะ user ที่จอยอีเว้นที่รับค่ามาเท่านั้น
        showTable(filteredUserEventCollection);

        /* ChangeListener เกิดเมื่อคลิกในลิสแล้วเปลี่ยนที่ลาเบล */
        memberTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserEvent>() {
            @Override
            public void changed(ObservableValue observable, UserEvent oldValue, UserEvent newValue) {
                if (newValue == null) {
                    clearUserInfo();
                    selectedUserEvent = null;
                } else {
                    showUserInfo(newValue);
                    selectedUserEvent = newValue;
                }
            }
        });

    }
    private UserEventCollection filterUserEvents(UserEventCollection userEventCollection, String eventName) {
        UserEventCollection filteredCollection = new UserEventCollection();

        for (UserEvent userEvent : userEventCollection.getUserEvents()) {
            if (userEvent.getEventName().equals(eventName)) {
                // เทียบชื่ออีเว้น ถ้าเหมือนก็แอดผู้ใช้เข้าเลย
                filteredCollection.addUserEvent(userEvent);
            }
        }
        return filteredCollection;
    }

    @FXML
    public void showTable(UserEventCollection userEventCollection){
        TableColumn<UserEvent, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<UserEvent, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<UserEvent, String> eventNameColumn = new TableColumn<>("Event Name");
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));

        memberTableView.getColumns().clear();
        memberTableView.getColumns().add(nameColumn);
        memberTableView.getColumns().add(usernameColumn);
        memberTableView.getColumns().add(eventNameColumn);

        memberTableView.getItems().clear();

        for(UserEvent userEvent: userEventCollection.getUserEvents()){
            memberTableView.getItems().add(userEvent);
        }

    }
    private void clearUserInfo() {
        userNameLabel.setText("");
        nameLabel.setText("");
    }
    private void showUserInfo(UserEvent userEvent){
        nameLabel.setText(userEvent.getName());
        userNameLabel.setText(userEvent.getUsername());
    }


    @FXML
    public void backToCreatorEvent(){
        try{
            FXRouter.goTo("creatorEvent");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void banPerson(){
        try{
            FXRouter.goTo("member");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
