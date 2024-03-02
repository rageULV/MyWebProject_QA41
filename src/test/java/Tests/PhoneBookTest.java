package Tests;

import Config.BaseTest;
import Helpers.TopMenuItem;
import Pages.AboutPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.MainPage;
import org.testng.annotations.Test;

public class PhoneBookTest extends BaseTest {

    @Test
    public void phoneBookTest_001() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        loginPage.fillEmailField("sherk@mail.com").clickRegistrationButton();
        Thread.sleep(3000);
    }
    @Test
    public void phoneBookTest_002() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver());
        HomePage homePage = mainPage.openTopMenu(TopMenuItem.HOME.toString());
        Thread.sleep(3000);
    }
    @Test
    public void phoneBookTest_003() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver());
        AboutPage aboutPage = mainPage.openTopMenu(TopMenuItem.ABOUT.toString());
        Thread.sleep(3000);
    }
}
