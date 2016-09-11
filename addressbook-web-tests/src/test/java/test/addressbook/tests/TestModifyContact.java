package test.addressbook.tests;

import org.testng.annotations.Test;
import test.addressbook.model.ContactData;
import test.addressbook.model.GroupData;

public class TestModifyContact extends TestBase {

    @Test
    public void modifyContact() {
        //app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAnyContact())
           app.getContactHelper().createContact(new ContactData("Анатолий", "Тестовый", "Тестовая улица, 1", null, "anatoliy@test.com", "test1"), true);
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().viewContactDetails();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactData(new ContactData("Енотик", "Тестовый", null, "123456", "enot@test.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}
