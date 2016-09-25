package test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class TestModifyGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToGroupPage();
        if (app.getGroupHelper().getAllGroups().size() == 0)
            //if (!app.getGroupHelper().isThereAnyGroup())
            app.getGroupHelper().createGroup(new GroupData().withName("test1"));
    }

    @Test
    public void modifyGroup() {
        Set<GroupData> before = app.getGroupHelper().getAllGroups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test123").withHeader("fixed2");
        app.getGroupHelper().modifyGroup(group);

        Set<GroupData> after = app.getGroupHelper().getAllGroups();

        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(before, after);
        //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));


    }

}
;


