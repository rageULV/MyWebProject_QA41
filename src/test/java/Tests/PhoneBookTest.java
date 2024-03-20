package Tests;

import Config.BaseTest;
import Helpers.*;
import Pages.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import model.Contact;
import org.openqa.selenium.*;
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
    @Description("HM")
    public void homeworkAssertTrueRegistrated() throws InterruptedException, IOException {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage lpage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());

        String mail = EmailGenerator.generateEmail(6,4,2);
        EmailGenerator.saveGeneratedMailToResources("4",mail);

        lpage.fillEmailField(mail).fillPasswordField(PropertiesReader.getProperties("existingUserPassword"));
        //lpage.fillEmailField(mail).fillPasswordField("1");

        Alert alert = lpage.clickRegistrationButton();
        if (alert == null) {
            String currentUrl = getDriver().getCurrentUrl();

            // checking url
            Assert.assertEquals(currentUrl, "https://telranedu.web.app/contacts");
            TakeScreen.takeScreenshot("Successful Registration");

            // searching element
//            ContactsPage contactsPage = new ContactsPage(getDriver());
//            Assert.assertTrue(contactsPage.isElementPersist(getDriver().
//                    findElement(By.xpath("//button[contains(text(),'Sign Out')]"))));

            Thread.sleep(2000);
        }
        else {
            String expectedString = "Wrong";
            boolean isAlertHandled = AlertHandler.handlerAlert(alert, expectedString); // creating true/false, that asks if alert was handled(alert, expected string it is the thing we wrote here for now)
            Assert.assertTrue(isAlertHandled);//assert its assert ...........
            TakeScreen.takeScreenshot("unSuccessful Registration");
            Thread.sleep(2000);
        }
    }
    @Test
    @Description("user allready exixts. Login and add contact then delete HM")
    public void loginExistingUserAddContactAndDelete() throws InterruptedException, NoSuchElementException
    {
        Allure.description("login exist acc and create new contact");

        Allure.step("go to the site in press login button");
        MainPage mainPage = new MainPage(getDriver());
        LoginPage lpage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());

        Allure.step("fill the fields of email and password by using resources");
        lpage.fillEmailField(PropertiesReader.getProperties("existingUserEmail")).
                fillPasswordField(PropertiesReader.getProperties("existingUserPassword")).clickLoginButton();

        Allure.step("after login in press the (add) button");
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

        Allure.step("after creating new contact gets in (contacts_Page), and checking if the contact is the same as we created AND save");
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertTrue(contactsPage.getDataFromContactList(newContact));
        TakeScreen.takeScreenshot("contact_created");

        Allure.step("go in to contacts");
        mainPage.openTopMenu(TopMenuItem.CONTACTS.toString());

        Allure.step("deleting the contact and checking if hes null");
        String phoneNumber = newContact.getPhone().toString();
        contactsPage.deleteContactByPhoneNumberOrName(phoneNumber);

        Allure.step("takes screenshot");
        TakeScreen.takeScreenshot("contact_deleted");

        Allure.step("making sure the contact deleted - just for fun its not really in need...");
        Assert.assertThrows(NoSuchElementException.class,()->
                BasePage.isElementPresent(getDriver().findElement(By.xpath("//h3[contains(text(),'"+phoneNumber+"')]"))));

        Allure.step("3 seconds and goes sleep");
        Thread.sleep(3000);
    }
    @Test
    public void deleteContactApproachTwo() throws IOException {
        String filename = "contactDataFile.ser";
        MainPage mainPage = new MainPage(getDriver());
        LoginPage lPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        lPage.fillEmailField(PropertiesReader.getProperties("existingUserEmail")).
                fillPasswordField(PropertiesReader.getProperties("existingUserPassword")).
                clickLoginButton();
        mainPage.openTopMenu(TopMenuItem.ADD.toString());
        AddPage addPage = new AddPage(getDriver());
        Contact newContact = new Contact
            (
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(7,5,2),
                AddressGenerator.generateAddress(), "testing"
            );
        addPage.fillFormAndSave(newContact);
        Contact.serializeContact(newContact,filename);
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Contact deserContact = Contact.deserializeContact(filename);
        Assert.assertNotEquals(contactsPage.deleteContactByPhoneNumberOrName(deserContact.getPhone())
                ,contactsPage.getContactsListSize());
    }
}

