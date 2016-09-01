package test.addressbook.tests;

import org.testng.annotations.Test;

public class TestDeleteGroup extends TestBase{

    @Test
    public void testDeleteGroup () {

        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupPage();
    }

}
