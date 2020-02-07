package com.mvelyka.finleap.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UserListPage {

  private String employeeListContainerSelector = "//ul[@id='employee-list']/li";
  private ElementsCollection employeeList = $$(By.xpath(employeeListContainerSelector));
  private SelenideElement addUserButton = $(By.xpath("//a[@id='bAdd']"));
  private SelenideElement editUserButton = $(By.xpath("//a[@id='bEdit']"));
  private SelenideElement deleteUserButton = $(By.xpath("//a[@id='bDelete']"));
  private String userLocatorTemplate = "//ul[@id='employee-list']/li[contains(text(),'%s')]";

  private SelenideElement firstNameField = $(By.xpath("//input[@type='text'] [@ng-model='selectedEmployee.firstName']"));
  private SelenideElement lastNameField = $(By.xpath("//input[@type='text'] [@ng-model='selectedEmployee.lastName']"));
  private SelenideElement startDateField = $(By.xpath("//input[@type='text'] [@ng-model='selectedEmployee.startDate']"));
  private SelenideElement emailField = $(By.xpath("//input[@type='email'] [@ng-model='selectedEmployee.email']"));

  public UserListPage() {
    $(By.xpath(employeeListContainerSelector)).waitUntil(visible, 10000);
  }

  public Integer getEmployeeCount() {
    return employeeList.size();
  }

  public ManageUserPage addUser() {
    addUserButton.click();
    return new ManageUserPage();
  }

  public ManageUserPage editUser() {
    editUserButton.click();
    return new ManageUserPage();
  }

  public UserListPage deleteUser() {
    deleteUserButton.click();
    return new UserListPage();
  }

  public boolean isUserInList(String username) {
    String userLocator = String.format(userLocatorTemplate, username);
    return $(By.xpath(userLocator)).isDisplayed();
  }

  public void selectUser(String username) {
    String userLocator = String.format(userLocatorTemplate, username);
    $(By.xpath(userLocator)).click();
  }

  public void openUserDetails(String nameInList) {
    String userLocator = String.format(userLocatorTemplate, nameInList);
    $(By.xpath(userLocator)).doubleClick();
  }

  public boolean userExists(String username) {
    return $(By.xpath(String.format(userLocatorTemplate, username))).is(exist);
  }

  public String getExitingUserFirstName() {
    return firstNameField.getValue();
  }

  public String getExitingUserLastName() {
    return lastNameField.getValue();
  }

  public String getExitingUserStartDate() {
    return startDateField.getValue();
  }

  public String getExitingUserEmail() {
    return emailField.getValue();
  }

}
