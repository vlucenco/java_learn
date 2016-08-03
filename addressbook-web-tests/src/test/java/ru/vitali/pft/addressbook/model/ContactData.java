package ru.vitali.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String mobilePhoneNumber;
  private final String email;
  private String address;
  private String group;

  public ContactData(String firstName, String lastName, String mobilePhoneNumber, String email, String address, String group) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.mobilePhoneNumber = mobilePhoneNumber;
    this.email = email;
    this.address = address;
    this.group = group;
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

  public String getAddress() {
    return address;
  }

  public String getGroup() {
    return group;
  }
}
