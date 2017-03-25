package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupTests extends TestBase {

    @BeforeMethod
    public void esurePreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("contactname").withLastname("contactlastname"));
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testRemoveContactFromGroup() {
        Contacts contact = app.db().contacts();
        Groups group = app.db().groups();
        ContactData contactForRemoveFromGroup = contact.iterator().next();
        GroupData groupFromContactRemove = group.iterator().next();
        Groups contactInGroupsBeforeAdded = app.db().contactInGroups();

        if (contactInGroupsBeforeAdded.size() == 0) {
            app.contact().addContactToGroup(contactForRemoveFromGroup.getId(), groupFromContactRemove.getName());
            contactInGroupsBeforeAdded = app.db().contactInGroups();
        }

        app.contact().removeFromGroup(contactForRemoveFromGroup.getId(), groupFromContactRemove.getName());
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(contact.size()));
        Groups contactInGroupsAfterRemove = app.db().contactInGroups();
        assertThat((contactInGroupsAfterRemove), equalTo(new Groups(contactInGroupsBeforeAdded.without(groupFromContactRemove))));
}
}