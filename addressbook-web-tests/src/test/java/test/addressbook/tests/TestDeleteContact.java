package test.addressbook.tests;

import org.testng.annotations.Test;
import test.addressbook.model.ContactData;

public class TestDeleteContact extends TestBase{

    @Test
    public void deleteContact() {
        if (!app.getContactHelper().isThereAnyContact())
            app.getContactHelper().createContact(new ContactData("Анатолий", "Тестовый", "Тестовая улица, 1", null, "anatoliy@test.com", "test1"), true);
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().viewContactDetails();
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().goToHomePage();
    }
}
