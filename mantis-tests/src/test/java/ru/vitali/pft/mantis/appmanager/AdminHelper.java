package ru.vitali.pft.mantis.appmanager;

import org.openqa.selenium.By;

import java.io.IOException;

public class AdminHelper extends HelperBase {

  public AdminHelper(ApplicationManager app) {
    super(app);
  }

  public void resetUserPassword(String user) throws IOException {
    login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    click(By.className("manage-menu-link"));
    click(By.linkText("Manage Users"));
    click(By.linkText(user));
    click(By.cssSelector("input[value='Reset Password']"));
  }


}
