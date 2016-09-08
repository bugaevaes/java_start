package test.addressbook.tests;

import org.testng.annotations.Test;
import test.addressbook.model.GroupData;

public class TestModifyGroup extends TestBase {

    @Test
    public void modifyGroup() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "fixed2", "fixed3"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }

    ;

}
