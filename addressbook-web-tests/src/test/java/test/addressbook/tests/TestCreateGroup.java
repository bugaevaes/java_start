package test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.addressbook.model.GroupData;

import java.util.List;

public class TestCreateGroup extends TestBase {

    @Test
    public void createGroup() {

        app.getNavigationHelper().goToGroupPage();
        int before =  app.getGroupHelper().getGroupCount();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", null, "test3"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
        int after =  app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
    }

}
