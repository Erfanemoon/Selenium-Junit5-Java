import exceptions.RepetetiveDataException;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import selenium.Selenium;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {
    Selenium selenium;
    User user;
    Integer randomUserId;

    @BeforeEach
    public void objectUse() {
        user = new User("davoodi", "italy", "genova", "9121969533", "erfan", randomUserId);
        selenium = new Selenium(user);
    }

    @Test
    @DisplayName("add user")
    public void userAdd() {
        User user = selenium.addUser();
        if (user == null)
            fail("failed to add user");
    }

    @Test
    @DisplayName("get users list")
    public void getUserList() {
        List<User> userList = selenium.getUserList();
        Random random = new Random();
        if (userList.size() == 0)
            fail("no user found for the system");
        int i = random.nextInt(userList.size());
        this.randomUserId = userList.get(i).getUserId();

        Set<User> userSet = new HashSet<User>(userList);
        try {
            if (userList.size() != userSet.size())
                throw new RepetetiveDataException("data in list is repeated");
        } catch (RepetetiveDataException e) {
            e.printStackTrace();
            fail("repeated data found in the list");
        }
    }

    @Test
    @DisplayName("check user information")
    public void checkUserInfo() {
        assertTrue(selenium.checkUserInfo(generateRandomUserId()), "user is checked everything is fine");
    }

    @Test
    @DisplayName("find user information")
    public void findUserInfo() {
        User user = selenium.findUser(this.user);
        assertEquals("erfan", user.getFirstName(), "user found");
    }

    private int generateRandomUserId() {
        Random random = new Random();
        List<User> userList = selenium.getUserList();
        int i = random.nextInt(userList.size());
        return userList.get(i).getUserId();
    }

    @Test
    @DisplayName("edit user information")
    public void editUser() {
        User user = selenium.editUser(11);
        assertEquals("erfan", user.getFirstName(), "edit user test passed");
    }

    @Test
    @DisplayName("add pet to user")
    public void addPet() {
        User user = selenium.addPet(this.user);
        if (user.getPet() == null)
            fail("pet not added");
        else
            assertEquals("snow", user.getPet().getName(), "test passed");
    }
}
