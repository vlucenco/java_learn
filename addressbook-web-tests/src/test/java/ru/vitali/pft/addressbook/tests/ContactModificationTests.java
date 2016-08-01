package ru.vitali.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().initContactModification();
    app.getContactHelper().modifyContactField(By.name("address"), "Кишинёв");
    app.getContactHelper().submitContactModification();
  }
}
