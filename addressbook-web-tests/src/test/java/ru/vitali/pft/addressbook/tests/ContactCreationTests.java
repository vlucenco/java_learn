package ru.vitali.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.vitali.pft.addressbook.model.ContactData;
import ru.vitali.pft.addressbook.model.Contacts;
import ru.vitali.pft.addressbook.model.GroupData;
import ru.vitali.pft.addressbook.model.Groups;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader =
                 new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType()); //2nd param means the same as List<ContactData>.class
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact) {
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("group1"));
    }
    Groups groups = app.db().groups();
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    contact.inGroup(groups.iterator().next());
    app.contact().create(contact);
    assertEquals(app.contact().count(), before.size() + 1);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadContactCreation() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData contact = new ContactData()
            .withFirstName("Анд'рей")
            .withLastName("Ива'нов")
            .withAddress("");
    app.contact().create(contact);
    assertEquals(app.contact().count(), before.size());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before));
  }
}
