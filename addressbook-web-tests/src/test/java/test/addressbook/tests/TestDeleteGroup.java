package test.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.model.GroupData;
import test.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDeleteGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToGroupPage();
        if (app.getGroupHelper().getAllGroups().size() == 0)
            app.getGroupHelper().createGroup(new GroupData().withName("test1").withFooter("test3"));
    }

    @Test
    public void testDeleteGroup() {

        Groups before = app.getGroupHelper().getAllGroups();
        GroupData deletedGroup = before.iterator().next();
        app.getGroupHelper().deleteGroup(deletedGroup);
        Groups after = app.getGroupHelper().getAllGroups();

        assertThat(after.size(), equalTo(before.size() - 1));

        assertThat(after, equalTo(before.without(deletedGroup)));



    }

}


