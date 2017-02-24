package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        if (isElementPresent(By.tagName("h1"))
                && Objects.equals(wd.findElement(By.tagName("h1")).getText(), "Groups")
                && isElementPresent(By.name("new"))) {
            return;
        } else {
            click(By.linkText("groups"));
        }
    }

    public void gotoHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        } else {
            click(By.linkText("home"));
        }
    }
}
