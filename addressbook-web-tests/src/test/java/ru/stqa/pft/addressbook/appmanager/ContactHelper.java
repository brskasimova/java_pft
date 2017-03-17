package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returntoHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }


    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("email"), contactData.getEmail());
        attach(By.name("photo"), contactData.getPhoto());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void deletedSelectionContact() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact,true);
        submitContactCreation();
        contactCache = null;
        returntoHomePage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact,false);
        submitContactModification();
        contactCache = null;
        returntoHomePage();
    }

    public void delete(ContactData contact) {
        initContactModificationById(contact.getId());
        deletedSelectionContact();
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements) {
            String firstname = element.findElements(By.tagName("td")).get(2).getText();
            String lastname = element.findElements(By.tagName("td")).get(1).getText();
            String address = element.findElements(By.tagName("td")).get(3).getText();
            String allPhones = element.findElements(By.tagName("td")).get(5).getText();
            String allEmails = element.findElements(By.tagName("td")).get(4).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAddress(address).withAllPhones(allPhones).withAllEmails(allEmails));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.tagName("textarea")).getText();
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomephone(home).withMobilephone(mobile).withWorkphone(work).withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public ContactData infoFromFullInfoForm(ContactData contact) {
        initFullInfoContactById(contact.getId());
        String allInfo = wd.findElement(By.id("content")).getText();

        return new ContactData().withId(contact.getId()).withFullInfo(allInfo);
    }

    private void initFullInfoContactById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
    }
}
