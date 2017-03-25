package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("contactname").withLastname("contactlastname"));
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("group1"));
        }
    }

    @Test
    public void testAddToGroupTest() {
        Contacts contact = app.db().contacts();
        Groups group = app.db().groups();
        ContactData contactForAddToGroup = contact.iterator().next();
        GroupData groupForAddContact = group.iterator().next();
        Groups contactInGroupsBeforeAdded = app.db().contactInGroups();
        app.contact().addContactToGroup(contactForAddToGroup.getId(), groupForAddContact.getName());
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(contact.size()));
        Groups contactInGroupsAfterAdded = app.db().contactInGroups();
        assertThat((contactInGroupsAfterAdded), equalTo(new Groups(contactInGroupsBeforeAdded.withAdded(groupForAddContact))));
    }
}