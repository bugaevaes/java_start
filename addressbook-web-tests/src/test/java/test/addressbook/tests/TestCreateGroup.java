package test.addressbook.tests;

import org.testng.annotations.Test;
import test.addressbook.model.GroupData;

public class TestCreateGroup extends TestBase {

    @Test
    public void createGroup() {

        app.goToGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }

}
