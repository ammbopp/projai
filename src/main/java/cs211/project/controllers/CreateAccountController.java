package cs211.project.controllers;

import cs211.project.services.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import cs211.project.models.User;
import cs211.project.models.collections.UserList;
import cs211.project.services.Datasource;
import cs211.project.services.UserListFileDatasource;

import java.io.IOException;

public class CreateAccountController {
    @FXML
    private ImageView backgroundcreImageView;
    @FXML
    private ImageView profilepicImageView;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel2;
    @FXML
    private Label errorLabel;

    private Datasource<UserList> datasource;
    private UserList userList;
    private User user;

    @FXML
    public void initialize() {
        Image backgroundcreaccimage = new Image(getClass().getResource("/images/IMG_1917.png").toString());  // แบบที่ 1
        backgroundcreImageView.setImage(backgroundcreaccimage);
//        Image profilepicimage = new Image(getClass().getResource("/images/defaultpic.jpg").toString());  // แบบที่ 1
//        profilepicImageView.setImage(profilepicimage);

        datasource = new UserListFileDatasource("data", "user.csv");
        userList = datasource.readData();

        errorLabel.setText("");
        errorLabel2.setText("");
    }

    @FXML
    public void backToMainPage() {

        try {
            FXRouter.goTo("mainPage");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void ButtonToCreateAccount() {
        String name = nameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String roll = "user";
        String userImage = "images/user/defaultpic.jpg";
        String loginTimeRegister = "00-00-0000 00:00:00";


        if (name.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            errorLabel2.setText("Please fill in all the information.");
        } else {
            if (!password.equals(confirmPassword)) {
                errorLabel2.setText("Password and confirm password do not match.");
                return;
            } if (userList.findUserByUsername(username) != null) {
                errorLabel.setText("This username is already in use.");
                return;
            } else {
                userList.addUser(name, username, password, roll, userImage, loginTimeRegister);
                datasource.writeData(userList);
                try {
                    FXRouter.goTo("logIn");
                } catch (IOException e) {
                    errorLabel.setText("");
                }
            }
        }

    }
}
