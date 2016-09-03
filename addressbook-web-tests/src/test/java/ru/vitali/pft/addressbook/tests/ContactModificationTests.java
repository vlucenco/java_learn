package ru.vitali.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vitali.pft.addressbook.model.ContactData;
import ru.vitali.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withLastName("Vitali").withLastName("Lucenco").withGroup("test1"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData contactToModify = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(contactToModify.getId())
            .withFirstName(contactToModify.getFirstName())
            .withLastName(contactToModify.getLastName())
            .withMobilePhone(contactToModify.getMobilePhone())
            .withHomePhone(contactToModify.getHomePhone())
            .withWorkPhone(contactToModify.getWorkPhone())
            .withEmail1(contactToModify.getEmail1())
            .withEmail2(contactToModify.getEmail2())
            .withEmail3(contactToModify.getEmail3())
            .withAddress("Кишинёв")
            .withGroup(contactToModify.getGroup());
    app.goTo().homePage();
    app.contact().modify(contact);
    assertEquals(app.contact().count(), before.size());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo((before.without(contactToModify).withAdded(contact))));
  }


}
