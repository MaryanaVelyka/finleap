package com.mvelyka.finleap.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ManageUserPage {

  private SelenideElement firstNameField = $(By.xpath("//input[@type='text'] [@ng-model='selectedEmployee.firstName']"));
  private SelenideElement lastNameField = $(By.xpath("//input[@type='text'] [@ng-model='selectedEmployee.lastName']"));
  private SelenideElement startDateField = $(By.xpath("//input[@type='text'] [@ng-model='selectedEmployee.startDate']"));
  private SelenideElement emailField = $(By.xpath("//input[@type='email'] [@ng-model='selectedEmployee.email']"));

  private SelenideElement submitButtonAdd = $(By.xpath("//button[@type='submit'] [.='Add']"));
  private SelenideElement submitButtonUpdate = $(By.xpath("//button[@type='submit'] [.='Update']"));

  public ManageUserPage() {
    firstNameField.waitUntil(visible, 10000);
  }

  public ManageUserPage setFirstName(String firstName) {
    firstNameField.setValue(firstName);
    return new ManageUserPage();
  }

  public ManageUserPage setLastName(String lastName) {
    lastNameField.setValue(lastName);
    return new ManageUserPage();
  }

  public ManageUserPage setStartDate(String startDate) {
    startDateField.setValue(startDate);
    return new ManageUserPage();
  }

  public ManageUserPage setEmail(String email) {
    emailField.setValue(email);
    return new ManageUserPage();
  }

  public UserListPage submitActionAdd() {
    submitButtonAdd.click();
    return new UserListPage();
  }

  public UserListPage submitActionUpdate() {
    submitButtonUpdate.click();
    return new UserListPage();
  }

}
