package com.mvelyka.finleap.test;

import com.mvelyka.finleap.pages.LoginPage;
import com.mvelyka.finleap.pages.ManageUserPage;
import com.mvelyka.finleap.pages.UserListPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class UserCrudTest {

  @BeforeMethod
  public void testSetup() {
    open("https://cafetownsend-angular-rails.herokuapp.com/login");
    LoginPage loginPage = new LoginPage();
    loginPage.login();
  }

  @Test
  public void createUserTest() {
    UserListPage userListPage = new UserListPage();
    Integer employeeCountBeforeAdd = userListPage.getEmployeeCount();

    User user = initUniqueUser();
    userListPage = addUser(user);

    Integer employeeCountAfterAdd = userListPage.getEmployeeCount();

    assertThat(employeeCountAfterAdd).isEqualTo(employeeCountBeforeAdd + 1);

    assertThat(userListPage.isUserInList(user.getNameInList())).isTrue();
    userListPage.openUserDetails(user.getNameInList());

    assertThat(userListPage.getExitingUserFirstName()).isEqualTo(user.firstName);
    assertThat(userListPage.getExitingUserLastName()).isEqualTo(user.lastName);
    assertThat(userListPage.getExitingUserStartDate()).isEqualTo(user.startDate);
    assertThat(userListPage.getExitingUserEmail()).isEqualTo(user.email);
  }

  @Test
  public void editUser() {
    User user = initUniqueUser();
    UserListPage userListPage = addUser(user);

    Integer employeeCountBeforeEdit = userListPage.getEmployeeCount();

    userListPage.selectUser(user.getNameInList());
    ManageUserPage manageUserPage = userListPage.editUser();

    String changedFirstName = user.firstName + "changed";
    String changedLastName = user.lastName + "changed";
    String changedStartDate = "2020-10-11";
    String changedEmail = user.email + "changed";
    String changedNameInList = changedFirstName + " " + changedLastName;

    userListPage = manageUserPage
        .setFirstName(changedFirstName)
        .setLastName(changedLastName)
        .setStartDate(changedStartDate)
        .setEmail(changedEmail)
        .submitActionUpdate();

    Integer employeeCountAfterEdit = userListPage.getEmployeeCount();

    assertThat(employeeCountBeforeEdit).isEqualTo(employeeCountAfterEdit);

    assertThat(userListPage.isUserInList(changedNameInList)).isTrue();
    userListPage.openUserDetails(changedNameInList);
    assertThat(userListPage.getExitingUserFirstName()).isEqualTo(changedFirstName);
    assertThat(userListPage.getExitingUserLastName()).isEqualTo(changedLastName);
    assertThat(userListPage.getExitingUserStartDate()).isEqualTo(changedStartDate);
    assertThat(userListPage.getExitingUserEmail()).isEqualTo(changedEmail);
  }

  @Test
  public void deleteUserTest() {
    User user = initUniqueUser();
    UserListPage userListPage = addUser(user);

    Integer employeeCountBeforeDelete = userListPage.getEmployeeCount();
    userListPage.selectUser(user.getNameInList());
    userListPage.deleteUser();

    getWebDriver().switchTo().alert().accept();

    Integer employeeCountAfterDelete = userListPage.getEmployeeCount();

    assertThat(employeeCountBeforeDelete).isEqualTo(employeeCountAfterDelete + 1);
    assertThat(userListPage.userExists(user.getNameInList())).isFalse();
  }

  private UserListPage addUser(User user) {
    UserListPage manageUserPage = new UserListPage();
    return manageUserPage
        .addUser()
        .setFirstName(user.firstName)
        .setLastName(user.lastName)
        .setStartDate(user.startDate)
        .setEmail(user.email)
        .submitActionAdd();
  }

  private User initUniqueUser() {
    long millis = System.currentTimeMillis();
    User user = new User();
    user.firstName = "firstName" + millis;
    user.lastName = "lastName" + millis;
    user.startDate = "2020-10-10";
    user.email = "test@gmail.com";

    return user;
  }

  private static class User {
    private String firstName;
    private String lastName;
    private String startDate;
    private String email;

    private String getNameInList() {
      return firstName + " " + lastName;
    }
  }

}
