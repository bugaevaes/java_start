package test.addressbook.tests;

import org.testng.annotations.Test;
import test.addressbook.model.ContactData;

public class TestCreateContact extends TestBase {

    @Test
    public void createContact() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactData(new ContactData("Олег", "Тестовый", "Тестовая улица, 1", "123456", "anatoliy@test.com"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().goToHomePage();
    }
}