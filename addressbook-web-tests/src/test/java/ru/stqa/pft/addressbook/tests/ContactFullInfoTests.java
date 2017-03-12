package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactFullInfoTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("contactname").withLastname("contactlastname"));
        }
    }

    @Test
    public void testContactFullInfo() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactData contactInfoFromFullInfoForm = app.contact().infoFromFullInfoForm(contact);

        ContactData newFullInfo = new ContactData().withId(contact.getId()).
                withFullInfo(mergeNames(contactInfoFromEditForm) + "\n" +
                        contactInfoFromEditForm.getAddress() + "\n" + "\n" +
                        mergePhones(contactInfoFromEditForm) + "\n" + "\n" +
                        mergeEmails(contactInfoFromEditForm));

        assertThat(contactInfoFromFullInfoForm.getFullInfo(), equalTo(newFullInfo.getFullInfo()));
    }


    private String mergeNames(ContactData contactInfoFromEditForm) {
        return Arrays.asList(contactInfoFromEditForm.getFirstname(), contactInfoFromEditForm.getLastname())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining(" "));
    }

    private String mergePhones(ContactData contactInfoFromEditForm) {
        String home = "H: " + cleanedPhone(contactInfoFromEditForm.getHomephone());
        String mobile = "M: " + cleanedPhone(contactInfoFromEditForm.getMobilephone());
        String work = "W: " + cleanedPhone(contactInfoFromEditForm.getWorkphone());
        return Arrays.asList(home, mobile, work)
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedPhone(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");

    }

    private String mergeEmails(ContactData contactInfoFromEditForm) {
        return Arrays.asList(contactInfoFromEditForm.getEmail(), contactInfoFromEditForm.getEmail2(),
                contactInfoFromEditForm.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public static String concatEmails(String email) {
        String replace = email.replaceAll(".*@(.*)", "(www.$1)");
        return email.concat(" " + replace);


    }
}
