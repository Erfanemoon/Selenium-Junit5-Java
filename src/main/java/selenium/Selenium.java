package selenium;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class Selenium {

    User user;

    public Selenium(User user) {
        this.user = user;
        SeleniumMgr.initializeDriver();
    }

    public String getTitle() {
        return SeleniumMgr.fireFoxWebDriver.getTitle();
    }

    public User addUser() {
        SeleniumMgr.connectTo("http://localhost:8080/owners/new");
        return SeleniumMgr.addUser(user);
    }

    public boolean checkUserInfo(int userId) {
        SeleniumMgr.connectTo("http://localhost:8080/owners/" + userId);
        String userName = user.getFirstName() + " " + user.getLastName();
        return SeleniumMgr.checkUserInfo(userName);
    }

    public User findUser(User user) {
        SeleniumMgr.connectTo("http://localhost:8080/owners/find");
        return SeleniumMgr.findUser(user);
    }

    public List<User> getUserList() {
        SeleniumMgr.connectTo("http://localhost:8080/owners?lastName=" + user.getLastName());
        return SeleniumMgr.getUserList();
    }

    public User editUser(Integer userId) {
        SeleniumMgr.connectTo("http://localhost:8080/owners/" + userId + "/edit");
        User user = new User("hamid", "delshadi", "itlay", "genova", "9121969533", userId);
        return SeleniumMgr.editUserInfo(user);
    }

    public User addPet(User user) {
        SeleniumMgr.connectTo("http://localhost:8080/owners/" + user.getUserId() + "/pets/new");
        return SeleniumMgr.addPet(user);
    }
}
