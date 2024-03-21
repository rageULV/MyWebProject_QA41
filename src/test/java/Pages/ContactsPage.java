package Pages;

import model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ContactsPage extends BasePage{
    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    WebElement signOutButton;
    public ContactsPage(WebDriver driver){
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
    }
    public boolean isElementPersist(WebElement element){
        // if you want check that element is not present by this method you will have to use assertThrows !!!!
        return isElementPresent(element);
    }
    public void clickRemoveButton() {
        WebElement removeButton = driver.findElement(By.xpath("//button[normalize-space()='Remove']"));
        removeButton.click();
    }
    public LoginPage clickBySignOutButton(){
        signOutButton.click();
        return new LoginPage(driver);
    }
    public int getContactsListSize(){
        return getContactsList().size();
    }
    protected List<WebElement> getContactsList(){
        return driver.findElements(By.xpath("//div[@class='contact-item_card__2SOIM']"));
    }
    public int deleteContactByPhoneNumberOrName(String phoneNumberOrName){
        List<WebElement> contactsList = getContactsList();
        int CListSize = contactsList.size();
        try
        {
            for(WebElement contact: contactsList)
            {
                WebElement phoneNumberOrNameData = contact.findElement(By.
                        xpath("(//h3[text()='"+phoneNumberOrName+"']) | (//h2[contains(text(),'\"+phoneNumberOrName+\"')])"));
                if(phoneNumberOrNameData.isDisplayed())
                {
                    phoneNumberOrNameData.click();
                    clickRemoveButton();
                    break;
                }
            }
        }
        catch(NoSuchElementException NSEE)
        {
            NSEE.fillInStackTrace();
            System.out.println("the phone number-"+phoneNumberOrName+" doesn't exist in the list");
        }

        //below we are want to wait until the list size in the web will be in expected size - (xpath "the List size", expected size)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.numberOfElementsToBe
                (By.xpath("//div[@class='contact-item_card__2SOIM']"),CListSize-1));

        return contactsList.size();
    }
    public boolean getDataFromContactList(Contact contact){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Создается объект класса WebDriverWait,
        // который ожидает видимости элемента на странице в течение 5 секунд.
        WebElement nameInContact = wait.until(ExpectedConditions
                .visibilityOfElementLocated(
                        By.xpath("//h2[contains(text(),'"+contact.getName().toString()+"')]"))); // Ожидается появление элемента с именем контакта, переданного в методе, используя XPath.
        nameInContact.click();

        WebElement editButton = driver.findElement(By.xpath("//button[contains(text(),'Edit')]"));
        editButton.click();

// находим элементы веб-страницы для каждого поля контакта (имя, фамилия, телефон, email, адрес и описание) и получаем их значения с помощью метода getAttribute("value").
        WebElement elementName = driver.findElement(By.xpath("//input[@placeholder='Name']"));
        String elementNameValue = elementName.getAttribute("value");

        WebElement elementLastName = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
        String elementLastNameValue = elementLastName.getAttribute("value");

        WebElement elementPhone = driver.findElement(By.xpath("//input[@placeholder='Phone']"));
        String elementPhoneValue = elementPhone.getAttribute("value");

        WebElement elementEmail = driver.findElement(By.xpath("//input[@placeholder='email']"));
        String elementEmailValue = elementEmail.getAttribute("value");

        WebElement elementAddress = driver.findElement(By.xpath("//input[@placeholder='Address']"));
        String elementAddressValue = elementAddress.getAttribute("value");

        WebElement elementDescription = driver.findElement(By.xpath("//input[@placeholder='desc']"));
        String elementDescriptionValue = elementDescription.getAttribute("value");


        // Создается новый объект Contact, в который записываются полученные значения полей контакта.
        Contact listContact = new Contact();

        listContact.setName(elementNameValue);
        listContact.setLastname(elementLastNameValue);
        listContact.setPhone(elementPhoneValue);
        listContact.setEmail(elementEmailValue);
        listContact.setAddress(elementAddressValue);
        listContact.setDescription(elementDescriptionValue);

        boolean result = listContact.equals(contact);
        return result;
    }




}
