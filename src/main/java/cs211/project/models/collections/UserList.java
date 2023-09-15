package cs211.project.models.collections;

import cs211.project.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserList {
    private ArrayList<User> users;

    public UserList() {
        users = new ArrayList<>();

    }


    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.isUsername(username)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(String name,String username, String password, String roll, String image, String loginTime) {
//        User exist = findUserByUsername(username);
//        if (exist == null) {
//            users.add(new User(name, username, password));
//        }
        name = name.trim();
        username = username.trim();
        password = password.trim();
        roll = roll.trim();
        image = image.trim();
        if (!name.equals("") && !username.equals("") && !password.equals("")) {
            User exist = findUserByUsername(username);
            if (exist == null) {
                users.add(new User(name, username, password, roll, image, loginTime));
            }
        }
    }

//    public User login(String username, String password) {
//        User user = findUserByUsername(username);
//        if (user != null && user.isPassword(password)) {
//            return user;
//        }
//        return null;
//    }

    public ArrayList<User> getUsers() {
        return users;
    }

}

