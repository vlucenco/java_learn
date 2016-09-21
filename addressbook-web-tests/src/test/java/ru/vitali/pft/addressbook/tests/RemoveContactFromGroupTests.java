package ru.vitali.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.vitali.pft.addressbook.model.ContactData;
import ru.vitali.pft.addressbook.model.Contacts;
import ru.vitali.pft.addressbook.model.GroupData;
import ru.vitali.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTests extends TestBase {

  @Test
  public void testRemoveContactFromGroup() {
    Contacts before = app.db().contacts();
    ContactData contactWithGroup = app.contact().getContactWithGroup();
    GroupData group = contactWithGroup.getGroups().iterator().next();
    ContactData contactWithoutGroup = app.contact().removeContactFromGroup(contactWithGroup, group);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(contactWithGroup).withAdded(contactWithoutGroup)));
  }
}
