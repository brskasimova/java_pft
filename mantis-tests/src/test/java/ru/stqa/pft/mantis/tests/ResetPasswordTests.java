package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
            app.mail().start();
        }

    @Test
    public void testChangeUserPasswordByAdmin() throws IOException, MessagingException {
        String newPassword = "passwordnew";
        app.getNavigationHelper().goToLoginPage();
        app.getUserHelper().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPass"));
        app.getNavigationHelper().goToManageUserPage();

        UserData changedUser = app.getUserHelper().getAnyUserFromBD();
        app.getNavigationHelper().goToUserPage(changedUser.getId());
        app.getUserHelper().startResetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, changedUser.getEmail());
        app.registration().finish(confirmationLink, newPassword);

        UserData user = app.getUserHelper().getUserByIdFromBD(changedUser.getId());

        HttpSession sessionUser = app.newSession();
        assertTrue(sessionUser.login(user.getUsername(), newPassword));
        assertTrue(sessionUser.isLoggedInAs(user.getUsername()));
        }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
            app.mail().stop();
        }
 }