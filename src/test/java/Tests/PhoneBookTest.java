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
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class PhoneBookTest extends BaseTest {

    @Test
    @Description("user allready exixts. Login and add contact")
    public void loginOfAnExistingUserAddContact() throws InterruptedException
    {
        Allure.description("login exist acc and create new contact");

        Allure.step("go to the site in press login button");
        MainPage mainPage = new MainPage(getDriver());
        LoginPage lpage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());

        Allure.step("fill the fields of email and password by using resources");
        lpage.fillEmailField(PropertiesReader.getProperties("existingUserEmail")).
                fillPasswordField(PropertiesReader.getProperties("existingUserPassword")).clickLoginButton();

        Allure.step("after logining press the (add) button");
        mainPage.openTopMenu(TopMenuItem.ADD.toString());

        Allure.step("while in (addpage) make new contact with generating everything but description");
        AddPage addPage = new AddPage(getDriver());
        Contact newContact = new Contact
                (
                  NameAndLastNameGenerator.generateName(), NameAndLastNameGenerator.generateLastName(),
                  PhoneNumberGenerator.generatePhoneNumber(), EmailGenerator.generateEmail(5,5,2),
                  AddressGenerator.generateAddress(),"something"
                );
        Allure.step("converting the new contact to string and filling the form ");
        newContact.toString();
        addPage.fillFormAndSave(newContact);

        Allure.step("after creating new contact gets in (contacts_Page), and checking if the contact is the same as we created");
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertTrue(contactsPage.getDataFromContactList(newContact));

        Allure.step("takes screenshot and waits 3 seconds");
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
    @Test
    @Description("s")
    public void homeworkAssertTrueRegistrated() throws InterruptedException, IOException {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage lpage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());

        String mail = EmailGenerator.generateEmail(6,4,2);
        EmailGenerator.saveGeneratedMailToResources("2",mail);

        lpage.fillEmailField(mail).fillPasswordField(PropertiesReader.getProperties("existingUserPassword"));
        lpage.clickRegistrationButton();

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl,"https://telranedu.web.app/contacts");
        Thread.sleep(5000);
    }
}