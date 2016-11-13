package test.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.addressbook.model.ContactData;
import test.addressbook.model.Contacts;
import test.addressbook.model.GroupData;
import test.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCreateContact extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))){
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
            Groups groups = app.db().groups();
        List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
        contacts = contacts.stream().map((g) -> {
            g.inGroup(groups.iterator().next());
            return g;
        }).collect(Collectors.toList());
        return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();}
    }

    @Test(dataProvider = "validContacts")
    public void createContact(ContactData contact) {
        //Groups groups = app.db().groups();
        app.getNavigationHelper().goToHomePage();

//        Contacts before = app.getContactHelper().getAllContacts();
        Contacts before = app.db().contacts();
        //File photo = new File("src/test/resources/enot.png");
        //ContactData contact = new ContactData().withName("Евгений").withLastname("Тестовый").withAddress("Тестовая улица, 1")
        //.withEmail("evgeniy@test.com").withEmail2("evgeniy@test.com").withEmail3("evgeniy3@test.com")
        //.withGroup("test1").withHomePhone("223344").withMobilePhone("+79630000000").withWorkPhone("112233").withPhoto(photo);
        app.getContactHelper().createContact(contact, true);
        app.getNavigationHelper().goToHomePage();

//        Contacts after = app.getContactHelper().getAllContacts();
        Contacts after = app.db().contacts();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

    @Test(enabled = false)
    public void testCurrentDir() {
        File photo = new File("src/test/resources/enot.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}