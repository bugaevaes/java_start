package test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import test.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(FirefoxDriver wd)  {
        super(wd);
    }

    public void goToHomePage() {
        click (By.linkText("home page"));
    }

    public void submitContactCreation() {
        click (By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactData(ContactData contactData) {
        type (By.name("firstname"),contactData.getName());
        type (By.name("lastname"),contactData.getLastname());
        type (By.name("address"),contactData.getAddress());
        type (By.name("home"),contactData.getPhone());
        type (By.name("email"),contactData.getEmail());
    }

    public void initContactCreation() {
        click (By.linkText("add new"));
    }
}
