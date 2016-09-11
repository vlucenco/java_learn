package ru.vitali.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vitali.pft.mantis.model.MailMessage;
import ru.vitali.pft.mantis.model.UserData;

import java.io.IOException;
import java.util.List;

public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException {
    String newPassword = "newPassword";
    UserData user = app.db().users().iterator().next();
    app.admin().resetUserPassword(user.getUsername());
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String resetPasswordLink = app.user().findResetPasswordLink(mailMessages, user.getEmail());
    app.user().resetPassword(user.getUsername(), resetPasswordLink, newPassword);
    Assert.assertTrue(app.newSession().login(user.getUsername(), newPassword));
  }

  @AfterMethod
  public void stopMailServer() {
    app.mail().stop();
  }
}
