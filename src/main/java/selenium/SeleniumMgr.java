package selenium;

import model.Pet;
import model.PetEnum;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumMgr {
    public static WebDriver fireFoxWebDriver;
    public static List<Integer> userIDs = new ArrayList<>();

    public static void initializeDriver() {
        File directory = new File("./geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", directory.getPath());
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        fireFoxWebDriver = new FirefoxDriver(capabilities);
        connectTo("http://localhost:8080/");
    }

    public static void connectTo(String url) {
        try {
            fireFoxWebDriver.navigate().to(url);
            fireFoxWebDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User addUser(User user) {

        fireFoxWebDriver.findElement(By.cssSelector("#firstName")).sendKeys(user.getFirstName());
        fireFoxWebDriver.findElement(By.cssSelector("#lastName")).sendKeys(user.getLastName());
        fireFoxWebDriver.findElement(By.cssSelector("#address")).sendKeys(user.getAddress());
        fireFoxWebDriver.findElement(By.cssSelector("#city")).sendKeys(user.getCity());
        fireFoxWebDriver.findElement(By.cssSelector("#telephone")).sendKeys(user.getTelephone());
        fireFoxWebDriver.findElement(By.xpath("//button[@type='submit']")).click();
        return user;
    }

    public static boolean checkUserInfo(String userName) {
        boolean isUserAdded = false;
        String ownerInfoTitle = fireFoxWebDriver.findElement(By.cssSelector(".xd-container > h2:nth-child(1)")).getText();
        if (ownerInfoTitle.isEmpty()) {
            isUserAdded = false;
        } else {
            String firstName = fireFoxWebDriver.findElement(By.cssSelector("table.table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > b:nth-child(1)")).getText();
            if (firstName.equals(userName))
                isUserAdded = true;
        }
        return isUserAdded;
    }

    public static User findUser(User user) {

        submitDataByClick("#lastName");
        connectTo("http://localhost:8080/owners/" + user.getUserId());
        String userName = fireFoxWebDriver.findElement(By.cssSelector("table.table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > b:nth-child(1)")).getText();
        String address = fireFoxWebDriver.findElement(By.cssSelector("table.table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2)")).getText();
        String city = fireFoxWebDriver.findElement(By.cssSelector("table.table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2)")).getText();
        String telephone = fireFoxWebDriver.findElement(By.cssSelector("table.table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2)")).getText();
        String[] s = userName.split(" ");
        if (user.getPet() != null)
            return new User(s[0], s[1], address, city, telephone, user.getUserId(), user.getPet());
        return new User(s[0], s[1], address, city, telephone, user.getUserId(), null);
    }

    private static void submitDataByClick(String cssSelectorPath) {
        fireFoxWebDriver.findElement(By.cssSelector(cssSelectorPath)).click();
    }

    public static List<User> getUserList() {
        List<User> users = new ArrayList<>();
        List<WebElement> elements = fireFoxWebDriver.findElements(By.xpath("/html/body/div/div/table/tbody/tr"));
        String href1 = elements.get(0).findElement(By.xpath("/html/body/div/div/table/tbody/tr[1]/td[1]/a")).getAttribute("href");
        String hrefValue = href1.substring(Math.max(href1.length() - 2, 0));
        int counter = Integer.parseInt(hrefValue);
        for (WebElement element : elements) {
            String text = element.getText();
            String[] s = text.split(" ");
            users.add(new User(s[0], s[1], s[2], s[3], s[4], counter, null));
            counter++;
        }
        return users;
    }

    public static User editUserInfo(User user) {
        fireFoxWebDriver.findElement(By.cssSelector("#firstName")).clear();
        fireFoxWebDriver.findElement(By.cssSelector("#lastName")).clear();
        fireFoxWebDriver.findElement(By.cssSelector("#address")).clear();
        fireFoxWebDriver.findElement(By.cssSelector("#city")).clear();
        fireFoxWebDriver.findElement(By.cssSelector("#telephone")).clear();

        fireFoxWebDriver.findElement(By.cssSelector("#firstName")).sendKeys(user.getFirstName());
        fireFoxWebDriver.findElement(By.cssSelector("#lastName")).sendKeys(user.getLastName());
        fireFoxWebDriver.findElement(By.cssSelector("#address")).sendKeys(user.getAddress());
        fireFoxWebDriver.findElement(By.cssSelector("#city")).sendKeys(user.getCity());
        fireFoxWebDriver.findElement(By.cssSelector("#telephone")).sendKeys(user.getTelephone());

        fireFoxWebDriver.findElement(By.cssSelector(".btn")).click();
        return user;
    }

    public static User addPet(User user) {

        String userName = fireFoxWebDriver.findElement(By.cssSelector("div.form-group:nth-child(1) > div:nth-child(2) > span:nth-child(1)")).getText();
        if ((user.getFirstName() + " " + user.getLastName()).equals(userName)) {
            Pet pet = createPet();
            fireFoxWebDriver.findElement(By.cssSelector("#name")).sendKeys(pet.getName());
            fireFoxWebDriver.findElement(By.cssSelector("#birthDate")).sendKeys(pet.getBirthDate());
            fireFoxWebDriver.findElement(By.cssSelector("#type")).sendKeys(pet.getPetEnum().getName());

            user.setPet(pet);
            fireFoxWebDriver.findElement(By.cssSelector(".btn")).click();
        }
        return user;
    }

    private static Pet createPet() {
        return new Pet(PetEnum.DOG, "2012-01-23", "snow");
    }
}
