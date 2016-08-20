package ru.vitali.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vitali.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withLastName("Vitali").withLastName("Lucenco").withGroup("test1"));
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contactToModify = before.get(index);
    ContactData contact = new ContactData()
            .withId(contactToModify.getId())
            .withFirstName(contactToModify.getFirstName())
            .withLastName(contactToModify.getLastName())
            .withAddress("Кишинёв")
            .withGroup(contactToModify.getGroup());
    app.contact().modify(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(before.size(), after.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
