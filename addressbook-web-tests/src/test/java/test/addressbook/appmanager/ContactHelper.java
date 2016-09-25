package test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import test.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactData(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getPhone());
        type(By.name("email"), contactData.getEmail());


        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }

        //if (isElementPresent(By.name("new_group"))) {
        //new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());}
        else {
            Assert.assertFalse(isElementPresent(By.name("new-group")));

        }
    }


    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    //public void viewContactDetails(int index) {
    //wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a/img")).get(index).click();}

    public void viewContactDetails(int index) {
        wd.findElements(By.cssSelector("img[alt=\"Details\"]")).get(index).click();
        }


    public void initContactModification() {
        click(By.name("modifiy"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void createContact(ContactData contact, boolean creation) {
        initContactCreation();
        fillContactData(contact, creation);
        submitContactCreation();
    }


    public boolean isThereAnyContact() {
        return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=\"entry\"]"));
        for (WebElement element : elements) {
            String firstName = element.findElements(By.cssSelector("td")).get(2).getText();
            String lastName = element.findElements(By.cssSelector("td")).get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withName(firstName).withLastname(lastName);
            contacts.add(contact);
        }
        return contacts;
    }
}
