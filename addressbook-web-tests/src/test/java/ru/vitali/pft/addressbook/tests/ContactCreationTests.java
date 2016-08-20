package ru.vitali.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.vitali.pft.addressbook.model.ContactData;
import ru.vitali.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstName("Андрей")
            .withLastName("Иванов")
            .withAddress("")
            .withGroup("test1");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
