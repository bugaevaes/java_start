package test.addressbook.tests;

import org.testng.annotations.Test;
import test.addressbook.model.ContactData;

public class TestModifyContact extends TestBase {

    @Test
    public void modifyContact() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().viewContactDetails();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactData(new ContactData("Енотик", "Тестовый", "Тестовая улица, 100", "998877", "enot@test.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}
