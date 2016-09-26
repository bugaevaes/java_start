package test.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.model.GroupData;
import test.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestModifyGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToGroupPage();
        if (app.getGroupHelper().getAllGroups().size() == 0)
            app.getGroupHelper().createGroup(new GroupData().withName("test1"));
    }

    @Test
    public void modifyGroup() {
        Groups before = app.getGroupHelper().getAllGroups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test123").withHeader("fixed2");
        app.getGroupHelper().modifyGroup(group);

        Groups after = app.getGroupHelper().getAllGroups();

        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

   }

}
;


