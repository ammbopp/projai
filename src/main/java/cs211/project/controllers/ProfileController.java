package cs211.project.controllers;

import cs211.project.models.User;
import cs211.project.models.collections.EventCollection;
import cs211.project.models.collections.UserList;
import cs211.project.services.FXRouter;
import cs211.project.services.UserListFileDatasource;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import cs211.project.models.Event;

import cs211.project.services.EventCollectionFileDatasource;
import cs211.project.services.Datasource;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class ProfileController {
    @FXML
    private ImageView profilepicImageView;
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private ListView<Event> eventJoinListView;
    @FXML
    private ListView<Event> eventCreateListView;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label nameLabel;

    private Datasource<EventCollection> datasource;
    private EventCollection eventCollection;
    private Event selectedEvent;

    private User user;
    private UserList userList;
    private String currentUser;
    private Datasource<UserList> userDatasource;


    @FXML
    public void initialize() {
        Image backgroundimage = new Image(getClass().getResource("/images/IMG_1917.png").toString());  // แบบที่ 1
        backgroundImageView.setImage(backgroundimage);
//        Image profilepicimage = new Image(getClass().getResource("/images/defaultpic.jpg").toString());  // แบบที่ 1
//        profilepicImageView.setImage(profilepicimage);

        datasource = new EventCollectionFileDatasource("data","event.csv");
        eventCollection = datasource.readData();
        showList(eventCollection);

        userDatasource = new UserListFileDatasource("data","user.csv");
        userList = userDatasource.readData();
        currentUser = (String) FXRouter.getData();
        user = userList.findUserByUsername(currentUser);
        usernameLabel.setText(user.getUsername());
        nameLabel.setText(user.getName());

        Image profilepicimage = new Image("file:"+user.getImage(), true);
        profilepicImageView.setImage(profilepicimage);

        eventCreateListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue<? extends Event> observable, Event oldValue, Event newValue) {
                if (newValue == null) {
                    selectedEvent = null;
                } else {
                    selectedEvent = newValue;
                    try{
                        FXRouter.goTo("creatorEvent",newValue.getEventName());
                    } catch(IOException e){
                        throw new RuntimeException(e);
                    }
                }
                /* newValue คือตัวใหม่ที่ถูกเลือก อีกอันคืออันเก่า */
            }
        });

    }
    private void showList(EventCollection eventCollection) {
        eventCreateListView.getItems().clear();
        currentUser = (String) FXRouter.getData();

        for (Event events: eventCollection.getEvents()) {
            String creatorUsername = events.getCreatorUsername();
            if (creatorUsername != null && creatorUsername.equals(currentUser)) {
                eventCreateListView.getItems().add(events);
            }
        }
    }


    @FXML
    public void toChangePasswordButton(){
        try{
            FXRouter.goTo("resetPassword", currentUser);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void toAllEventPage(){
        try{
            FXRouter.goTo("searchEvent");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void logOutButton(){
        try{
            FXRouter.goTo("mainPage");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void editProfilePicture(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));

        Node source = (Node) event.getSource();
        File file = chooser.showOpenDialog(source.getScene().getWindow());
        if (file != null){
            try {
                // CREATE FOLDER IF NOT EXIST
                File destDir = new File("images/user");
                if (!destDir.exists()) destDir.mkdirs();
                // RENAME FILE
                String[] fileSplit = file.getName().split("\\.");
                String filename = LocalDate.now() + "_"+System.currentTimeMillis() + "."
                        + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath()+System.getProperty("file.separator")+filename
                );
                // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );

                profilepicImageView.setImage(new Image(target.toUri().toString()));
                user.setImage(destDir + "/" + filename);
                userDatasource.writeData(userList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
