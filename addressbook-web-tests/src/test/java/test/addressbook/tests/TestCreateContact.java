package test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.addressbook.model.ContactData;

import java.util.List;

public class TestCreateContact extends TestBase {

    @Test(enabled = true)
    public void createContact() {
        app.getNavigationHelper().goToHomePage();

        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().initContactCreation();
        ContactData contact = new ContactData()
                .withName("Борис").withLastname("Тестовый")
                .withAddress("Тестовая улица, 1").withEmail("boris@test.com").withGroup("test1");
        app.getContactHelper().fillContactData(contact, true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().goToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        before.sort((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
        after.sort((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));

        Assert.assertEquals(before, after);
    }
}