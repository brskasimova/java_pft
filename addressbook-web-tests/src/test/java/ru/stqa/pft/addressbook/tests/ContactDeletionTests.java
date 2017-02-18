package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.HelperBase;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().deletedSelectionContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
