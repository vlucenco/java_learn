package ru.vitali.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.vitali.pft.addressbook.model.ContactData;
import ru.vitali.pft.addressbook.model.Contacts;
import ru.vitali.pft.addressbook.model.GroupData;
import ru.vitali.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00' ").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public Groups groupsForContact(ContactData contact) {
    Groups groupsForContact = groups();
    Groups invalidGroups = contact.getGroups();
    for (GroupData group : invalidGroups) {
      groupsForContact = groupsForContact.without(group);
    }
    return groupsForContact;
  }

  public List<ContactData> getContactsWithGroup() {
    Contacts contacts = contacts();
    List<ContactData> contactsWithGroup = new ArrayList<>();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() > 0) {
        contactsWithGroup.add(contact);
      }
    }
    return contactsWithGroup;
  }
}
