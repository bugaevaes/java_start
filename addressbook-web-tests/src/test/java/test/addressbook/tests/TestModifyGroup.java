package test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class TestModifyGroup extends TestBase {

    @Test
    public void modifyGroup() {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAnyGroup())
            app.getGroupHelper().createGroup(new GroupData("test1", null, "test3"));

        List<GroupData> before = app.getGroupHelper().getGroupList();

        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        GroupData group = new GroupData(before.get(before.size() - 1).getId(), "test123", "fixed2", null);
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(group);

        before.sort((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
        after.sort((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));

        Assert.assertEquals(before, after);
        //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));



    }

    ;

}
