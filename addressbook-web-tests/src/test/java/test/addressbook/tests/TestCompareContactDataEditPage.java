package test.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCompareContactDataEditPage extends TestBase {

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

    @Test
    public void testCompareDataEditPage() {
        app.getNavigationHelper().goToHomePage();
        ContactData contact = app.getContactHelper().getAllContacts().iterator().next();
        ContactData contactInfoFromEditPage = app.getContactHelper().infoFromEditPage(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditPage)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditPage)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditPage.getAddress()));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(TestCompareContactDataEditPage::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String data) {
        return data.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
