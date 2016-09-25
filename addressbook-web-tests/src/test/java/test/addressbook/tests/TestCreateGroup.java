package test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;


public class TestCreateGroup extends TestBase {

    @Test
    public void createGroup() {

        app.getNavigationHelper().goToGroupPage();

        Set<GroupData> before = app.getGroupHelper().getAllGroups();
        GroupData group = new GroupData().withName("test1").withFooter("test3");

        app.getGroupHelper().createGroup(group);

        Set<GroupData> after = app.getGroupHelper().getAllGroups();

        Assert.assertEquals(after.size(), before.size() + 1);

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before, after);
    }
}



