package ru.vitali.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.vitali.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().gotoAddNewContactPage();
    app.getContactHelper().fillNewContactForm(
            new ContactData("Vitali", "Lucenco", "+37369602222", "lucenco.v@gmail.com"));
    app.getContactHelper().submitNewContactForm();
  }
}
