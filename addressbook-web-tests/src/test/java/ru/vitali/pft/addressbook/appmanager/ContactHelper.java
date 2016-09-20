package ru.vitali.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.vitali.pft.addressbook.model.ContactData;
import ru.vitali.pft.addressbook.model.Contacts;
import ru.vitali.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(ApplicationManager app) {
    super(app);
  }

  public void submitNewContactForm() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail1());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("address"), contactData.getAddress());
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(findElement(By.name("new_group")))
                .selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    if (isElementPresent(By.tagName("h1"))
            && findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")
            && isElementPresent(By.name("submit"))) {
      return;
    }
    click(By.linkText("add new"));
  }

  public void selectContactById(int index) {
    findElement(By.cssSelector("td>input[id='" + index + "']")).click();
  }

  public void deleteSelectedContacts() {
    ((JavascriptExecutor) wd).executeScript("confirm = function(message){return true;};");
    click(By.cssSelector(".left>input[value='Delete']"));
  }

  public void initContactModification(ContactData contactToModify) {
    if (isElementPresent(By.tagName("h1"))
            && findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")
            && isElementPresent(By.name("update"))) {
      return;
    }
    click(By.cssSelector("a[href=\"edit.php?id=" + contactToModify.getId() + "\"]"));
  }

  public void initContactModificationById(int id) {
    WebElement checkbox = findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

//    wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
//    wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
//    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = getElementValue("firstname");
    String lastname = getElementValue("lastname");
    String home = getElementValue("home");
    String mobile = getElementValue("mobile");
    String work = getElementValue("work");
    String address = getElementValue("address");
    String email = getElementValue("email");
    String email2 = getElementValue("email2");
    String email3 = getElementValue("email3");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address)
            .withEmail1(email).withEmail2(email2).withEmail3(email3);
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void returnToHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public int count() {
    return wd.findElements(By.cssSelector("input[name='selected[]']")).size();
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitNewContactForm();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModification(contact);
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    contactCache = null;
    returnToHomePage();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      String address = cells.get(3).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      contactCache.add(new ContactData()
              .withId(id)
              .withFirstName(firstName)
              .withLastName(lastName)
              .withAddress(address)
              .inGroup(new GroupData().withName("test0"))
              .withAllPhones(allPhones)
              .withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }

  public boolean isContactWithDetailedInfoPresent() {
    try {
      findElement(By.xpath("//td[3][contains(text(), 'Тест')]")).isDisplayed();
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }

  public String getDetailedInfo(ContactData contact) {
    click(By.cssSelector("a[href=\"view.php?id=" + contact.getId() + "\"]"));
    return findElement(By.id("content")).getText();
  }

  public ContactData contactInfoFromHomePage(ContactData contact) {
    String allEmails = findElement(By.xpath(".//*[@id='" + contact.getId() + "']/../../td[5]")).getText();
    String allPhones = findElement(By.xpath(".//*[@id='" + contact.getId() + "']/../../td[6]")).getText();
    return contact.withAllEmails(allEmails).withAllPhones(allPhones);
  }

  public ContactData addToGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    new Select(findElement(By.name("to_group"))).selectByVisibleText(group.getName());
    click(By.cssSelector(".right>input[type='submit']"));
    click(By.linkText("group page \"" + group.getName() + "\""));
    return contact;
  }

  public ContactData removeContact(ContactData contact, GroupData group) {
    viewGroupMembers(group);
    selectContactById(contact.getId());
    click(By.name("remove"));
    returnToHomePage();
    return contact;
  }
}


