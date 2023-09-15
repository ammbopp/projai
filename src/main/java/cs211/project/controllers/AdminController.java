package cs211.project.controllers;
import cs211.project.models.User;
import cs211.project.models.collections.UserList;
import cs211.project.services.Datasource;
import cs211.project.services.FXRouter;
import cs211.project.services.UserListFileDatasource;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class AdminController {
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private ImageView profilepicImageView;
    @FXML
    private TableView userListTableView;

    private UserList userList;
    private Datasource<UserList> datasource;
    String admin;

    @FXML
    public void initialize() {

        Image backgroundimage = new Image(getClass().getResource("/images/IMG_1917.png").toString());  // แบบที่ 1
        backgroundImageView.setImage(backgroundimage);
        Image profilepicimage = new Image(getClass().getResource("/images/defaultpic.jpg").toString());  // แบบที่ 1
        profilepicImageView.setImage(profilepicimage);

        datasource = new UserListFileDatasource("data", "user.csv");
        userList = datasource.readData();
        admin = (String) FXRouter.getData();
        showTable(userList);
    }

    @FXML
    public void changePasswordButtonClick(){
        try{
            FXRouter.goTo("resetPassword",admin);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void toAllEventButtonClick(){
        try{
            FXRouter.goTo("searchEvent");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showTable(UserList userList) {
        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> loginTimeColumn = new TableColumn<>("Latest Login");
        loginTimeColumn.setCellValueFactory(new PropertyValueFactory<>("loginTime"));

        // ล้าง column เดิมทั้งหมดที่มีอยู่ใน table แล้วเพิ่ม column ใหม่
        userListTableView.getColumns().clear();
        userListTableView.getColumns().add(nameColumn);
        userListTableView.getColumns().add(usernameColumn); //add ตามลำดับ
        userListTableView.getColumns().add(loginTimeColumn);

        userListTableView.getItems().clear();

        // ใส่ข้อมูลทั้งหมดจาก userList ไปแสดงใน TableView
        for (User user: userList.getUsers()) {
            if (user.getRoll().equals("user")) {
                userListTableView.getItems().add(user);
            }
        }
    }

}
