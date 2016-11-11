package test.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.model.ContactData;
import test.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDeleteContact extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToHomePage();
//        if (app.getContactHelper().getAllContacts().size() == 0)
        if (app.db().contacts().size() == 0)
            app.getContactHelper().createContact(new ContactData()
                    .withName("Борис").withLastname("Тестовый").withAddress("Тестовая улица, 1")
                    .withEmail("boris@test.com").withEmail2("boris2@test.com").withEmail3("boris3@test.com")
                    .withGroup("test1")
                    .withHomePhone("223344").withMobilePhone("+79630000000").withWorkPhone("112233"), true);
        app.getNavigationHelper().goToHomePage();
    }

    @Test(enabled = true)
    public void deleteContact() {

        //        Contacts before = app.getContactHelper().getAllContacts();
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();

        app.getContactHelper().deleteContact(deletedContact);

        app.getNavigationHelper().goToHomePage();

        //        Contacts after = app.getContactHelper().getAllContacts();
        Contacts after = app.db().contacts();

        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(deletedContact)));

    }
}

