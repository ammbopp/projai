package cs211.project.controllers;

import cs211.project.models.User;
import cs211.project.models.collections.UserList;
import cs211.project.services.Datasource;
import cs211.project.services.FXRouter;
import cs211.project.services.UserListFileDatasource;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class PasswordController {
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private PasswordField oldPassText;
    @FXML
    private PasswordField newPassText;
    @FXML
    private PasswordField confirmNewPassText;
    @FXML
    private Label errorLabel;
    @FXML
    private Label errorLabel2;

    private User user;
    private UserList userList;
    private Datasource<UserList> datasource;
    private String currentUser;

    @FXML
    public void initialize() {

        Image backgroundimage = new Image(getClass().getResource("/images/IMG_1917.png").toString());  // แบบที่ 1
        backgroundImageView.setImage(backgroundimage);
        errorLabel.setText("");
        errorLabel2.setText("");

        datasource = new UserListFileDatasource("data", "user.csv");
        userList = datasource.readData();

        currentUser = (String) FXRouter.getData();
        user = userList.findUserByUsername(currentUser);
    }

    @FXML
    public void backToProfileButton(){
        try{
            FXRouter.goTo("myProfile");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void confirmChangePass(){
        String oldPass = oldPassText.getText();
        String newPass = newPassText.getText();
        String confirmNewPass = confirmNewPassText.getText();


        if (oldPass.equals("") || newPass.equals("") || confirmNewPass.equals("")) {
            errorLabel2.setText("Please fill in all the fields.");
        }

        if (!oldPass.equals("") && !newPass.equals("") && !confirmNewPass.equals("")){
            if (!oldPass.equals(user.getPassword())){
                errorLabel.setText("The old password is not correct.");
            } if (oldPass.equals(newPass)){
                errorLabel2.setText("The password cannot be the same.");
            } if (!newPass.equals(confirmNewPass)){
                errorLabel2.setText("The password not match.");
            } if (oldPass.equals(user.getPassword()) && !oldPass.equals(newPass) && newPass.equals(confirmNewPass)){
                user.setPassword(newPass);
                try {
                    datasource.writeData(userList);
                    FXRouter.goTo("myProfile", user.getUsername());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
