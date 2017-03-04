package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.HelperBase;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("contactname", "contactlastname", "contactaddress", "contacthomephone", "contactemail", "testname1"));
        }
        List<ContactData> before = app.getContactHelper().getConactList();
        app.getContactHelper().initContactModification();
        app.getContactHelper().deletedSelectionContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getConactList();
        Assert.assertEquals(after.size(), before.size() - 1);
    }
}
