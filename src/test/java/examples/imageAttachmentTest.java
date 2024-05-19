package examples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class imageAttachmentTest {


    @Test
    public void imageAttachment() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://ilcarro.web.app/let-car-work");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By
                    .xpath("//input[@id='photos']"), 0));

            WebElement addPhoto =
                    driver.findElement(By
                            .xpath("//input[@id='photos']"));
            ScrollToTheElement(driver,addPhoto);
            addPhoto.sendKeys("C:\\Users\\esawe\\Desktop\\123.png");
            Thread.sleep(3000);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public static void ScrollToTheElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
