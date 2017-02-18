package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("contactname", "contactlastname", "contactaddress", "contacthomephone", "contactemail"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returntoHomePage();
    }

}
