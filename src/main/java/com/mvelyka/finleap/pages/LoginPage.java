package com.mvelyka.finleap.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

  private SelenideElement userNameField = $(By.xpath("//input[@ng-model='user.name']"));
  private SelenideElement passwordField = $(By.xpath("//input[@ng-model='user.password']"));
  private SelenideElement submitButtonLogin = $(By.xpath("//button[@type='submit']"));

  public LoginPage() {
    userNameField.waitUntil(Condition.visible, 10000);
  }

  public void login() {
    userNameField.setValue("Luke");
    passwordField.setValue("Skywalker");
    submitButtonLogin.click();
  }
}
