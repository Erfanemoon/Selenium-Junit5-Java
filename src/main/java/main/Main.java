package main;

import model.User;
import selenium.Selenium;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        User user = new User("khebre", "itlay", "genova", "22506565", "sogol", null);
        Selenium selenium = new Selenium(user);
        selenium.addUser();
        List<User> userList = selenium.getUserList();
        int i = random.nextInt(userList.size());
        Integer userId = userList.get(i).getUserId();
        selenium.checkUserInfo(userId);
        selenium.findUser(userList.get(i));
        //selenium.editUser(userId);
        selenium.addPet(userList.get(i));
    }
}
