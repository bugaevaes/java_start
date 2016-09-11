package test.addressbook.tests;

import org.testng.annotations.Test;
import test.addressbook.model.GroupData;

public class TestDeleteGroup extends TestBase {

    @Test
    public void testDeleteGroup() {

        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAnyGroup())
            app.getGroupHelper().createGroup(new GroupData("test1", null, "test3"));
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupPage();
    }

}
