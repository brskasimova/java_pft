package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.stylesheets.LinkStyle;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getConactList();
        app.getContactHelper().createContact(new ContactData("contactname", "contactlastname", "contactaddress", "contacthomephone", "contactemail", "testname1"));
        List<ContactData> after = app.getContactHelper().getConactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

}
