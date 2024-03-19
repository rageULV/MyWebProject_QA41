package examples;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;



public class findElements extends BasePage {
    // if you are logged in, in https://telranedu.web.app/
    // and you are in contact page the method below will get the list out of them
    // by //div[@class='contact-item_card__2SOIM']
    // cool method to find elements and make list our of them.
    protected List<WebElement> getContactsList(){
        return driver.findElements(By.xpath("//div[@class='contact-item_card__2SOIM']"));
    }
}
