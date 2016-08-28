package test.addressbook.tests;

import org.testng.annotations.Test;
import test.addressbook.model.ContactData;

public class TestCreateContact extends TestBase {

    @Test
    public void createContact() {
        app.initContactCreation();
        app.fillContactData(new ContactData("Анатолий", "Тестовый", "Тестовая улица, 1", "123456", "anatoliy@test.com"));
        app.submitContactCreation();
        app.goToHomePage();
    }
}