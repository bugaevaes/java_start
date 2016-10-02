package test.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.model.ContactData;
import test.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestModifyContact extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToHomePage();
        if (app.getContactHelper().getAllContacts().size() == 0)
            app.getContactHelper().createContact(new ContactData()
                    .withName("Борис").withLastname("Тестовый").withAddress("Тестовая улица, 1")
                    .withEmail("boris@test.com").withEmail2("boris2@test.com").withEmail3("boris3@test.com")
                    .withGroup("test1")
                    .withHomePhone("223344").withMobilePhone("+79630000000").withWorkPhone("112233"), true);
        app.getNavigationHelper().goToHomePage();
    }

    @Test(enabled = true)
    public void modifyContact() {
        Contacts before = app.getContactHelper().getAllContacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("Енотик").withLastname("Тестовый")
                .withHomePhone("556677").withMobilePhone("89630000000").withWorkPhone("998877")
                .withEmail("enot@test.com").withEmail2("enot2@test.com").withEmail3("enot3@test.com");
        app.getContactHelper().modifyContact(contact, false);
        app.getNavigationHelper().goToHomePage();

        Contacts after = app.getContactHelper().getAllContacts();

        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    }
}
