package test.addressbook.tests;

import org.testng.annotations.Test;
import test.addressbook.model.ContactData;
import test.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCreateContact extends TestBase {

    @Test(enabled = true)
    public void createContact() {
        app.getNavigationHelper().goToHomePage();

        Contacts before = app.getContactHelper().getAllContacts();
        File photo = new File("src/test/resources/enot.png");
        ContactData contact = new ContactData()
                .withName("Борис").withLastname("Тестовый").withAddress("Тестовая улица, 1")
                .withEmail("boris@test.com").withEmail2("boris2@test.com").withEmail3("boris3@test.com")
                .withGroup("test1")
                .withHomePhone("223344").withMobilePhone("+79630000000").withWorkPhone("112233")
                .withPhoto(photo);

        app.getContactHelper().createContact(contact, true);
        app.getNavigationHelper().goToHomePage();

        Contacts after = app.getContactHelper().getAllContacts();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

    @Test(enabled = false)
    public void testCurrentDir(){
        File photo = new File("src/test/resources/enot.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}