package cs211.project.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import cs211.project.services.FXRouter;
import cs211.project.models.collections.UserList;
import cs211.project.models.User;
import cs211.project.services.Datasource;
import cs211.project.services.UserListFileDatasource;

import java.io.IOException;

public class LogInController {
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    private UserList userList;

    private Datasource<UserList> datasource;


    @FXML
    public void initialize() {
        Image backgroundimage = new Image(getClass().getResource("/images/mainpage.png").toString());  // แบบที่ 1
        backgroundImageView.setImage(backgroundimage);

        errorLabel.setText("");

        datasource = new UserListFileDatasource("data", "user.csv");
        userList = datasource.readData();
    }

    @FXML
    public void backToMainPage(){

        try{
            FXRouter.goTo("mainPage");

        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void ButtonToLogin() {
//        String username = usernameTextField.getText();
//        String password = passwordField.getText();
//
//        User user = userList.findUserByUsername(username);
//        if (user != null && user.getPassword().equals(password)) {
//            try{
//                FXRouter.goTo("searchEvent");
//            } catch(IOException e){
//                throw new RuntimeException(e);
//            }
//        } else {
//            errorLabel.setText("username or password has wrong");
//        }


        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (!username.equals("") && !password.equals("")) {
            User user = userList.findUserByUsername(username);
            if (user == null || !password.equals(user.getPassword())) {
                errorLabel.setText("username or password has wrong");
            } else {
                if(user.getRoll().equals("user")) {
                    try {
                        FXRouter.goTo("searchEvent", user.getUsername());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    user.setLoginTime();
                    datasource.writeData(userList);
                } else {
                    try {
                        FXRouter.goTo("amdin", user.getUsername());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        } else {
            errorLabel.setText("please fill out the field");
        }
    }
}
