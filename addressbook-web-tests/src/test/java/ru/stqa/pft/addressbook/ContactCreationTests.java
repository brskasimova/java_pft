package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        initContactCreation();
        fillContactForm(new ContactData("testfirstname2", "testlastname2", "testaddress2", "testhomephone2", "testemail2"));
        submitContactCreation();
        dotoHomePage();
    }

}
