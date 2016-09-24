package test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.model.GroupData;

import java.util.List;

public class TestModifyGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToGroupPage();
        if (app.getGroupHelper().getGroupList().size() == 0)
            //if (!app.getGroupHelper().isThereAnyGroup())
            app.getGroupHelper().createGroup(new GroupData().withName("test1"));
    }

    @Test
    public void modifyGroup() {
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int index = before.size() - 1;
        GroupData group = new GroupData().withId(before.get(index).getId()).withName("test123").withHeader("fixed2");
        app.getGroupHelper().modifyGroup(index, group);

        List<GroupData> after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);

        before.sort((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
        after.sort((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));

        Assert.assertEquals(before, after);
        //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));


    }

}
;


