package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;


public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app) {
        super(app);
    }


    public UserData getAnyUserFromBD() {
        Users users = app.db().getUsersFromBD();
        return users.stream().filter((u) -> u.getAccessLevel() != 90).iterator().next();
    }

    public void startResetPassword() {
        click(By.cssSelector("input[value=\"Reset Password\"]"));
    }

    public void login(String username, String password) {
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value=\"Login\"]"));
    }

    public UserData getUserByIdFromBD(int id) {
        Users users = app.db().getUsersFromBD();
        return users.stream().filter((u) -> u.getId() == id).findFirst().get();
    }
}