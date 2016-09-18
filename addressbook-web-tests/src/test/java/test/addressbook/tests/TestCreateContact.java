package test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.addressbook.model.ContactData;
import test.addressbook.model.GroupData;

import java.util.List;

public class TestCreateContact extends TestBase {

    @Test
    public void createContact() {
        app.getNavigationHelper().goToHomePage();

        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().initContactCreation();
        ContactData contact = new ContactData("Анатолий", "Тестовый", "Тестовая улица, 1", null, "anatoliy@test.com", "test1");
        app.getContactHelper().fillContactData(contact, true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().goToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size() + 1);

        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        before.sort((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
        after.sort((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));

        Assert.assertEquals(before, after);
    }
}