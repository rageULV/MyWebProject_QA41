package Tests;

import Helpers.*;
import Pages.AddPage;
import Pages.ContactsPage;
import Pages.LoginPage;
import Pages.MainPage;
import model.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import static Config.BaseTest.getDriver;

public class PBtestTry {
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
