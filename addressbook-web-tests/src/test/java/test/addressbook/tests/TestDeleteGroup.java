package test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class TestDeleteGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToGroupPage();
        //if (!app.getGroupHelper().isThereAnyGroup())
        if (app.getGroupHelper().getAllGroups().size() == 0)
            app.getGroupHelper().createGroup(new GroupData().withName("test1").withFooter("test3"));
    }

    @Test
    public void testDeleteGroup() {

        Set<GroupData> before = app.getGroupHelper().getAllGroups();
        GroupData deletedGroup = before.iterator().next();
        app.getGroupHelper().deleteGroup(deletedGroup);
        Set<GroupData> after = app.getGroupHelper().getAllGroups();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedGroup);
        Assert.assertEquals(before, after);
    }

}


