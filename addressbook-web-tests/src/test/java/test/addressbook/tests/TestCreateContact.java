package test.addressbook.tests;

import org.testng.annotations.Test;
import test.addressbook.model.ContactData;
import test.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCreateContact extends TestBase {

    @Test(enabled = true)
    public void createContact() {
        app.getNavigationHelper().goToHomePage();

        Contacts before = app.getContactHelper().getAllContacts();

        app.getContactHelper().initContactCreation();
        ContactData contact = new ContactData()
                .withName("Борис").withLastname("Тестовый")
                .withAddress("Тестовая улица, 1").withEmail("boris@test.com").withGroup("test1");
        app.getContactHelper().fillContactData(contact, true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().goToHomePage();

        Contacts after = app.getContactHelper().getAllContacts();

        assertThat(after.size(), equalTo(before.size() + 1));
        //assertThat(after, equalTo(
                //before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

            }
}