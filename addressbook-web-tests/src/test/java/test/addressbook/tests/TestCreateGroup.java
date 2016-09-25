package test.addressbook.tests;

import org.testng.annotations.Test;
import test.addressbook.model.GroupData;
import test.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestCreateGroup extends TestBase {

    @Test
    public void createGroup() {

        app.getNavigationHelper().goToGroupPage();

        Groups before = app.getGroupHelper().getAllGroups();
        GroupData group = new GroupData().withName("test1").withFooter("test3");

        app.getGroupHelper().createGroup(group);

        //Groups after = app.getGroupHelper().getAllGroups();

        //assertThat(after.size(), equalTo(before.size() + 1));

        //assertThat(after, equalTo(
                //before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}



