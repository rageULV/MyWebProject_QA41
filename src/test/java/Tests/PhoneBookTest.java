package Tests;

import Config.BaseTest;
import Helpers.*;
import Pages.AddPage;
import Pages.ContactsPage;
import io.qameta.allure.Allure;
import Pages.LoginPage;
import Pages.MainPage;
import io.qameta.allure.Description;
import model.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PhoneBookTest extends BaseTest {

    @Test
    @Description("user allready exixts. Login and add contact")
    public void loginOfAnExistingUserAddContact() throws InterruptedException
    {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage lpage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());

        lpage.fillEmailField(PropertiesReader.getProperties("existingUserEmail")).
                fillPasswordField(PropertiesReader.getProperties("existingUserPassword")).clickLoginButton();

        mainPage.openTopMenu(TopMenuItem.ADD.toString());
        AddPage addPage = new AddPage(getDriver());
        Contact newContact = new Contact
                (
                  NameAndLastNameGenerator.generateName(), NameAndLastNameGenerator.generateLastName(),
                  PhoneNumberGenerator.generatePhoneNumber(), EmailGenerator.generateEmail(5,5,2),
                  AddressGenerator.generateAddress(),"something"
                );
        newContact.toString();
        addPage.fillFormAndSave(newContact);
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertTrue(contactsPage.getDataFromContactList(newContact));
        TakeScreen.takeScreenshot("contact_created");
        Thread.sleep(3000);
    }

    @Test
    public void phoneBookTest_001() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        loginPage.fillEmailField("sherk@mail.com").clickRegistrationButton();
        Thread.sleep(3000);
    }

    @Test(description = "The test checks the empty field warning declaration.")
    @Parameters("browser")
    public void registrationWithoutPassword(@Optional("chrome") String browser) throws InterruptedException {
        Allure.description("User already exist. Login and add contact.!");

        MainPage mainPage = new MainPage(getDriver());
        Allure.step("Click by Login button");
        LoginPage loginPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        Allure.step("Click by Reg button");


        String expectedString = "Wrong";// this thing should be in diffrent place but for now we have putted it here
        Alert alert = loginPage.fillEmailField("sherk5@mail.com").clickRegistrationButton();// fill email field..... press reg button......
        boolean isAlertHandled = AlertHandler.handlerAlert(alert, expectedString); // creating true/false, that asks if alert was handled(alert, expected string it is the thing we wrote here for now)
        Assert.assertTrue(isAlertHandled);//assert its assert ...........

    }
}