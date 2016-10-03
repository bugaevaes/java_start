package test.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCompareContactDataViewPage extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToHomePage();
        if (app.getContactHelper().getAllContacts().size() == 0)
            app.getContactHelper().createContact(new ContactData()
                    .withName("Борис").withLastname("Тестовый")
                    .withAddress("Тестовая улица, 1").withEmail("boris@test.com").withGroup("test1")
                    .withHomePhone("223344").withMobilePhone("+79630000000").withWorkPhone("112233"), true);
        app.getNavigationHelper().goToHomePage();
    }

    @Test(enabled = true)
    public void testCompareDataViewPage() {
        ContactData contact = app.getContactHelper().getAllContacts().iterator().next();
        ContactData contactInfoFromViewPage = app.getContactHelper().infoFromViewPage(contact);

        assertThat(contactInfoFromViewPage.getFullNameAndAddress(), equalTo(mergeFullNameAndAddress(contact)));
        assertThat(contactInfoFromViewPage.getAllEmails(), equalTo(contact.getAllEmails()));
        assertThat(contactInfoFromViewPage.getAllPhones(), equalTo(contact.getAllPhones()));
    }

    private String mergeFullNameAndAddress(ContactData contact) {
        return Arrays.asList(contact.getName() + " " + contact.getLastname(), contact.getAddress())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}

