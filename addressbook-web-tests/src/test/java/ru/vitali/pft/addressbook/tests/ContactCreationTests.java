package ru.vitali.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.vitali.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(
            new ContactData("Vitali", "Lucenco", "+37369602222", "lucenco.v@gmail.com", null, "test1"), true);
    app.getContactHelper().submitNewContactForm();
    app.getContactHelper().returnToHomePage();
  }
}
