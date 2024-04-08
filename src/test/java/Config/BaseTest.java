package Config;

import Pages.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {// Эта строка объявляет начало определения класса BaseTest. Класс является шаблоном или чертежом для создания объектов.

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    ; //Эта строка объявляет статическое приватное поле driverThreadLocal, которое является объектом класса ThreadLocal. Он используется для хранения объектов типа WebDriver в потоке исполнения.

    // ThreadLocal - это класс в Java, который позволяет создавать локальные переменные, специфичные для каждого потока.
    // Каждый поток имеет свою собственную копию переменной, хранящейся в ThreadLocal, и доступ может быть получен только из соответствующего потока.
    // Это полезно, когда вам нужно создать объект, который будет уникальным для каждого потока, и при этом изолировать состояние между потоками.
    public static WebDriver getDriver() {return driverThreadLocal.get();} //Этот метод возвращает экземпляр WebDriver из объекта driverThreadLocal. Он используется для получения веб-драйвера, установленного для текущего потока

    // Эти строки аннотируют метод setUp как метод, который должен быть выполнен перед каждым тестовым методом (@BeforeMethod), и указывают, что он принимает параметр browser из файла конфигурации.
    // Аннотация @Optional("firefox") означает, что значение по умолчанию для browser - это "firefox".
    @BeforeSuite
    @Parameters("browser")
    public void setUp(@Optional("firefox") String browser) {
        // Этот блок кода проверяет, является ли значение параметра browser равным "chrome".
        // Если да, то он настраивает ChromeDriver и добавляет опции для запуска браузера на английском язы
        if (browser.equalsIgnoreCase("chrome")) {
            /*
             * Для использования Хрома версий выше 114, можно попробовать использовать
             * // WebDriverManager.chromedriver().browserVersion("121.0.6167.185").setup();
             * Вместо WebDriverManager.chromedriver().setup(); - Закоментируйте строку, а вместо "121.0.6167.185" подставте версию вашего браузера.
             *
             */
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--land=en");
            options.addArguments("--disable-gpu");
            //options.addArguments("--headless"); //like I understand it just not showing me what happening in the browser...
            driverThreadLocal.set(new ChromeDriver(options));
        }
        // Аналогично предыдущему блоку, но если browser равен "firefox",
        // то настраивается FirefoxDriver и добавляются опции для запуска браузера на английском язык
        else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("intl.accept_languages", "en");
            // options.addArguments("-headless");
            driverThreadLocal.set(new FirefoxDriver(options));
        }
        else if (browser.equalsIgnoreCase("safari")) {
            SafariOptions options = new SafariOptions();
            options.setCapability("language", "en");
            driverThreadLocal.set(new SafariDriver());
        }
        else if (browser.equalsIgnoreCase("edge")) {
            // Настройки для Edge
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.setCapability("language", "en");
            //options.addArguments("--headless");
            driverThreadLocal.set(new EdgeDriver(options));
        }
        else {throw new IllegalArgumentException("invalid browser" + browser);}

        // Этот блок кода получает веб-драйвер с помощью метода getDriver(), максимизирует окно браузера,
        // устанавливает время ожидания загрузки страницы и неявного ожидания, а затем устанавливает этот драйвер для BasePage.
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(20000));//if page doesnt load. generates exception.
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(20000));// waits 20 sec before throw exceptions if element doesn't found instantly
        BasePage.setDriver(driver);//organisation drivers
    }
    @AfterSuite// if you need more explanation about it in (lesson 4 - time 15mins+-)
    public void tearDown(){
        WebDriver driver = getDriver();
        if(driver != null)
        {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

}


