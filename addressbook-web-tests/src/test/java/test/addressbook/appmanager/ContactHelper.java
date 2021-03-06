package test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import test.addressbook.model.ContactData;
import test.addressbook.model.Contacts;

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
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        //attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            } else {
                Assert.assertFalse(isElementPresent(By.name("new-group")));
            }
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    //public void viewContactDetails(int index) {
    //wd.findElement(By.cssSelector("img[alt=\"Details\"]")).click();    }

    private void viewContactDetailsById(int id) {
        wd.findElement(By.xpath("//a[@href = 'view.php?id=" + id + "' ]")).click();
    }

    public void initContactModification() {
        click(By.name("modifiy"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void removeContact() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void createContact(ContactData contact, boolean creation) {
        initContactCreation();
        fillContactData(contact, creation);
        submitContactCreation();
    }

    public void modifyContact(ContactData contact, boolean creation) {
        viewContactDetailsById(contact.getId());
        initContactModification();
        fillContactData(contact, false);
        submitContactModification();
    }

    public void deleteContact(ContactData contact) {
        viewContactDetailsById(contact.getId());
        initContactModification();
        removeContact();
    }

    public boolean isThereAnyContact() {
        return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a"));
    }

    public Contacts getAllContacts() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=\"entry\"]"));
        for (WebElement element : elements) {
            String firstName = element.findElements(By.cssSelector("td")).get(2).getText();
            String lastName = element.findElements(By.cssSelector("td")).get(1).getText();
            String allPhones = element.findElements(By.cssSelector("td")).get(5).getText();
            String allEmails = element.findElements(By.cssSelector("td")).get(4).getText();
            String address = element.findElements(By.cssSelector("td")).get(3).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withName(firstName).withLastname(lastName)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return contacts;
    }

    public ContactData infoFromEditPage(ContactData contact) {
        viewContactDetailsById(contact.getId());
        initContactModification();
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(firstName).withLastname(lastName)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone)
                .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);

    }

    public ContactData infoFromViewPage(ContactData contact) {
        viewContactDetailsById(contact.getId());
        String blockData = wd.findElement(By.id("content")).getText();
        String[] contactDataBlock= blockData.split("\\n\\n");

        return new ContactData()
                .withFullNameAndAddress(contactDataBlock[0])
                .withAllPhones(contactDataBlock[1].replaceAll("[H:MW ]", ""))
                .withAllEmails(contactDataBlock[2].replace(" (www.test.com)", ""));

    }
}
