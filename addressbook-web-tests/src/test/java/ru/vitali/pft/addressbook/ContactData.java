package ru.vitali.pft.addressbook;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String mobilePhoneNumber;
  private final String email;

  public ContactData(String firstName, String lastName, String mobilePhoneNumber, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.mobilePhoneNumber = mobilePhoneNumber;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getMobilePhoneNumber() {
    return mobilePhoneNumber;
  }

  public String getEmail() {
    return email;
  }
}
