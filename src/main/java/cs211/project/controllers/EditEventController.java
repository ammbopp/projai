package cs211.project.controllers;

import cs211.project.services.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import cs211.project.services.EventCollectionFileDatasource;
import cs211.project.services.Datasource;
import cs211.project.models.Event;
import cs211.project.models.collections.EventCollection;

import javafx.stage.FileChooser;
import java.io.File;
import cs211.project.models.UserEvent;
import cs211.project.models.collections.UserEventCollection;
import cs211.project.services.*;
import cs211.project.models.Activity;
import cs211.project.models.collections.ActivityCollection;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class EditEventController {
    @FXML
    private ImageView bgPic;
    @FXML
    private ImageView eventPic;
    @FXML
    TextField eventNameTextField;
    @FXML
    TextArea eventDetailTextField;
    @FXML
    TextField seatFullTextField;
    @FXML
    DatePicker startDatePicker;
    @FXML
    DatePicker dueDatePicker;

    private Datasource<EventCollection> datasourceEvent;
    private EventCollection eventCollection;
    private Event aEvent;

    private Datasource<ActivityCollection> datasourceActivity;
    private ActivityCollection activityCollection;

    private Datasource<UserEventCollection> datasourceUserEvent;
    private UserEventCollection userEventCollection;


    @FXML
    public void initialize() {
        Image bg = new Image(getClass().getResourceAsStream("/images/mainpage.png"));
        bgPic.setImage(bg);

        datasourceEvent = new EventCollectionFileDatasource("data", "event.csv");
        eventCollection = datasourceEvent.readData();

        datasourceActivity = new ActivityCollectionFileDatasource("data", "activity.csv");
        activityCollection = datasourceActivity.readData();

        datasourceUserEvent = new UserEventCollectionFileDatasource("data", "user_event.csv");
        userEventCollection = datasourceUserEvent.readData();

        String eventName = (String) FXRouter.getData();
        if (eventName != null) {
            aEvent = eventCollection.findEventByEventName(eventName);

            eventNameTextField.setText(aEvent.getEventName());
            eventDetailTextField.setText(aEvent.getEventDetail());
            seatFullTextField.setText(String.valueOf(aEvent.getSeatFull()));

            LocalDate startDate = LocalDate.parse(aEvent.getStartDate());
            LocalDate dueDate = LocalDate.parse(aEvent.getDueDate());
            startDatePicker.setValue(startDate);
            dueDatePicker.setValue(dueDate);

            Image image = new Image("file:" + aEvent.getEventPic(), true);
            eventPic.setImage(image);
        }

    }

    @FXML
    public void enterEditToCreatorEvent() {
        String eventNameString = eventNameTextField.getText().trim();
        String eventDetailString = eventDetailTextField.getText().trim();
        String seatFullString = seatFullTextField.getText();
        LocalDate startDateValue = startDatePicker.getValue();
        LocalDate dueDateValue = dueDatePicker.getValue();

        try {
            String eventName = eventNameString;
            String eventDetail = eventDetailString;
            String seatFull = seatFullString;
            String startDate = startDateValue != null ? startDateValue.toString() : "";
            String dueDate = dueDateValue != null ? dueDateValue.toString() : "";

            if (eventName.isEmpty() || eventDetail.isEmpty() || seatFull.isEmpty() || startDate.isEmpty() || dueDate.isEmpty()) {
                FXRouter.goTo("creatorEvent");
                return;
                // ถ้าไม่แก้อะไร
            }

            if (aEvent != null) {
                if (!eventName.isEmpty()) {
                    // หากมีการเปลี่ยนแปลงชื่อกิจกรรม
                    String oldEventName = aEvent.getEventName();
                    aEvent.changeEventName(eventName);

                    // อัปเดตชื่อกิจกรรมใน eventCollection
                    eventCollection.updateEvent(aEvent);

                    // ทำการอัปเดตชื่อกิจกรรมใน activityCollection
                    for (Activity activity : activityCollection.getActivities()) {
                        if (activity.getEventName().equals(oldEventName)) {
                            activity.changeEventName(eventName);
                        }
                    }
                    for (UserEvent userEvent : userEventCollection.getUserEvents()) {
                        if (userEvent.getEventName().equals(oldEventName)) {
                            userEvent.changeEventName(eventName);
                        }
                    }
                }
                if (!eventDetail.isEmpty()) {
                    aEvent.changeEventDetail(eventDetail);
                }
                if (!seatFull.isEmpty()) {
                    aEvent.changeSeatFull(Integer.parseInt(seatFull));
                }
                if (startDateValue != null) {
                    aEvent.changeStartDate(startDate);
                }
                if (dueDateValue != null) {
                    aEvent.changeDueDate(dueDate);
                }
                // หลังจากแก้ไข event ให้ทำการอัปเดตข้อมูลใน eventCollection
                eventCollection.updateEvent(aEvent);
                System.out.println(userEventCollection.getUserEvents());
                // จากนั้นทำการเขียนข้อมูล eventCollection ลงไฟล์
                datasourceEvent.writeData(eventCollection);
                datasourceActivity.writeData(activityCollection);
                datasourceUserEvent.writeData(userEventCollection);
            }
            FXRouter.goTo("creatorEvent", aEvent.getEventName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void cancelToCreatorEvent() {
        try {
            FXRouter.goTo("creatorEvent", aEvent.getEventName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void changePic(javafx.event.ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir"))); // Change to user.dir
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));

        File file = chooser.showOpenDialog(null);
        if (file != null) {
            try {
                // CREATE FOLDER IF NOT EXIST
                File destDir = new File("images/event");
                if (!destDir.exists()) destDir.mkdirs();
                // RENAME FILE
                String[] fileSplit = file.getName().split("\\.");
                String filename = LocalDate.now() + "_" + System.currentTimeMillis() + "."
                        + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath() + System.getProperty("file.separator") + filename
                );
                // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);

                if (event != null) {
                    // SET NEW FILE PATH TO IMAGE
                    Image newImage = new Image(target.toUri().toString());
                    eventPic.setImage(newImage);

                    // Update the event's picture path
                    aEvent.changeEventPic(destDir + "/" + filename);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
