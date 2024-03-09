package Helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertHandler {
    WebDriver driver;
    public AlertHandler(WebDriver driver){this.driver=driver;}
    public static boolean handlerAlert(Alert alert, String expectedText)
    {
        if(alert !=null)
        {
            String alertText = alert.getText();
            System.out.println("ALERT_TEXT"+alertText);
            alert.accept();
            return alertText.contains(expectedText);
        }
        return false;
    }
}
