package cs211.project.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {
    private String name;
    private String username;
    private String password;
    private String roll;
    private String image;
    private String loginTime;

    public User(String name,String username, String password, String roll, String image, String loginTime) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.roll = roll;
        this.image = image;
        this.loginTime = loginTime;
    }

    public String getName(){
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRoll(){
        return roll;
    }

    public String getImage(){
        return image;
    }

    public String getLoginTime(){
        return loginTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLoginTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime tempTime = LocalDateTime.now();
        this.loginTime = tempTime.format(format);
    }


    public boolean isUsername(String username) {
        return this.username.equals(username);
    }

    public boolean isPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
//        return "User{" +
//                "name='" + name + '\'' +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                '}';
        return "";
    }
}
