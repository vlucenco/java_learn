package ru.vitali.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.vitali.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test(enabled = false)
  public void testContactModification() {
    app.goTo().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Vitali", "Lucenco", null, null, null, "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contactToModify = before.get(before.size() - 1);
    app.getContactHelper().initContactModification(contactToModify);
    ContactData contact =
            new ContactData(contactToModify.getId(), contactToModify.getFirstName(), contactToModify.getLastName(),
                    null, null, "Кишинёв", contactToModify.getGroup());
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(before.size(), after.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
