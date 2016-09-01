package test.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {


    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        click(By.linkText("groups"));
        wd.findElement(By.linkText("groups")).click();
    }

    public void goToHomePage() {
        click (By.linkText("home page"));
    }
}
