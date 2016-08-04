package ru.vitali.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.vitali.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Vitali", "Lucenco", null, null, null, "test1"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
  }
}
