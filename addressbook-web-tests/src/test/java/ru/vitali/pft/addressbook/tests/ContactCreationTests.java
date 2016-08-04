package ru.vitali.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.vitali.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().createContact(new ContactData("Vitali", "Lucenco", null, null, null, "test1"));
  }
}
