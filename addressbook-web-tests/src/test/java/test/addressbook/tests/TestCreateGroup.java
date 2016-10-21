package test.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
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

public class TestCreateGroup extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {

       try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))){
           String xml = "";
           String line = reader.readLine();
           while (line != null) {
               xml += line;
               line = reader.readLine();
           }
           XStream xstream = new XStream();
           xstream.processAnnotations(GroupData.class);
           List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
           return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
       }
    }


    @Test(dataProvider = "validGroups")
    public void createGroup(GroupData group) {

        app.getNavigationHelper().goToGroupPage();

        Groups before = app.getGroupHelper().getAllGroups();
        app.getGroupHelper().createGroup(group);

        Groups after = app.getGroupHelper().getAllGroups();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}



