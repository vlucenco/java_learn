package ru.vitali.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.vitali.pft.addressbook.model.ContactData;
import ru.vitali.pft.addressbook.model.Contacts;
import ru.vitali.pft.addressbook.model.GroupData;
import ru.vitali.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

  @Test
  public void testAddContactToGroup() {
    Contacts before = app.db().contacts();
    ContactData contactWithoutGroup = before.iterator().next();
    Groups groupsForContact = app.db().groupsForContact(contactWithoutGroup);
    GroupData group;
    if (groupsForContact.size() == 0) {
      GroupData groupForContact = new GroupData().withName("groupForContact");
      app.goTo().groupPage();
      app.group().create(groupForContact);
      group = groupForContact;
    } else {
      group = groupsForContact.iterator().next();
    }

    app.goTo().homePage();
    ContactData contactWithGroup = app.contact().addToGroup(contactWithoutGroup, group);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(contactWithoutGroup).withAdded(contactWithGroup)));
  }
}
