package test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.addressbook.model.GroupData;

public class TestModifyGroup extends TestBase {

    @Test
    public void modifyGroup() {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAnyGroup())
            app.getGroupHelper().createGroup(new GroupData("test1", null, "test3"));
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().selectGroup(before - 2);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "fixed2", null));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before);
    }

    ;

}
