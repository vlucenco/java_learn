package ru.vitali.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.vitali.pft.mantis.model.MailMessage;

import java.util.List;

public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public void resetPassword(String user, String resetPasswordLink, String newPassword) {
    wd.get(resetPasswordLink);
    type(By.name("password"), newPassword);
    type(By.name("password_confirm"), newPassword);
    click(By.cssSelector("input[value='Update User']"));
  }

  public String findResetPasswordLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }


}
