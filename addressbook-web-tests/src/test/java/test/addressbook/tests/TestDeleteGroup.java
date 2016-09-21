package test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.model.GroupData;

import java.util.List;

public class TestDeleteGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToGroupPage();
        //if (!app.getGroupHelper().isThereAnyGroup())
        if (app.getGroupHelper().getGroupList().size() == 0)
            app.getGroupHelper().createGroup(new GroupData("test1", null, "test3"));
    }

    @Test
    public void testDeleteGroup() {

        List<GroupData> before = app.getGroupHelper().getGroupList();
        int index = before.size() - 1;
        app.getGroupHelper().deleteGroup(index);
        List<GroupData> after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}


