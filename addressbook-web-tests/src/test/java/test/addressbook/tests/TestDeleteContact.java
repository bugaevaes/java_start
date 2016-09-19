package test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.addressbook.model.ContactData;

import java.util.List;

public class TestDeleteContact extends TestBase{

    @Test
    public void deleteContact() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAnyContact())
            app.getContactHelper().createContact(new ContactData("Анатолий", "Тестовый", "Тестовая улица, 1", null, "anatoliy@test.com", "test1"), true);

        List<ContactData> before = app.getContactHelper().getContactList();

        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().viewContactDetails(before.size() - 1);
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().goToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);

        Assert.assertEquals(before, after);
    }
}
