package test.addressbook.tests;

import org.testng.annotations.Test;

public class TestDeleteContact extends TestBase{

    @Test
    public void deleteContact() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().viewContactDetails();
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().goToHomePage();
    }
}
