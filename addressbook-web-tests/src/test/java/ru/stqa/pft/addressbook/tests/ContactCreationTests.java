package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("testfirstname2", "testlastname2", "testaddress2", "testhomephone2", "testemail2"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returntoHomePage();
    }

}
