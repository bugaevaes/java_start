package test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.addressbook.model.ContactData;

import java.util.List;

public class TestModifyContact extends TestBase {

    @Test(enabled = true)
    public void modifyContact() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAnyContact())
           app.getContactHelper().createContact(new ContactData()
                   .withName("Борис").withLastname("Тестовый")
                   .withAddress("Тестовая улица, 1").withEmail("boris@test.com").withGroup("test1"), true);


        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().viewContactDetails(before.size() - 1);
        app.getContactHelper().initContactModification();
        ContactData contact = new ContactData().withName("Енотик").withLastname("Тестовый").withPhone("123456")
        .withEmail("enot@test.com");
        app.getContactHelper().fillContactData(contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);

        before.sort((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
        after.sort((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));

        Assert.assertEquals(before, after);
    }
}
