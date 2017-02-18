package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    public  void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("modifyfirstname", "modifylastname", "modifyaddress", "modifyhomephone", "modifyemail"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returntoHomePage();
    }
}
