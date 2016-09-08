package test.addressbook.tests;

import org.testng.annotations.Test;
import test.addressbook.model.ContactData;

public class TestCreateContact extends TestBase {

    @Test
    public void createContact() {
        //app.getNavigationHelper().goToHomePage();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactData(new ContactData("Анатолий", "Тестовый", "Тестовая улица, 1", "123456", "anatoliy@test.com", "test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().goToHomePage();
    }
}