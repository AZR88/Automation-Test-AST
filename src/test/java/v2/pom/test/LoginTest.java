package v2.pom.test;

import helper.WebHelper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import v2.pom.page.LoginPage;

import static helper.WebHelper.driver;

public class LoginTest {

    @BeforeMethod
    public void setup() {
        WebHelper.startDriver("chrome");
        driver.get("https://www.demoblaze.com/");
    }

    @Test(priority = 1)
    public void testLoginValid() {
        LoginPage.clickLoginButton(driver);
        LoginPage.inputUsername(driver, "Beta123");
        LoginPage.inputPassword(driver, "123");
        LoginPage.clickSubmitButton(driver);

        String actualUser = LoginPage.getDisplayedUsername(driver);
        Assert.assertNotNull(actualUser, "Username hasil tidak boleh null");
        Assert.assertFalse(actualUser.isEmpty(), "Username hasil tidak boleh kosong");
        Assert.assertEquals(actualUser, "Beta123", "Username tidak sesuai!");
    }

    @Test(priority = 2)
    public void testLoginInvalidUser() {
        LoginPage.clickLoginButton(driver);
        LoginPage.inputUsername(driver, "UserSalah");
        LoginPage.inputPassword(driver, "123");
        LoginPage.clickSubmitButton(driver);

        String alertMsg = LoginPage.getAlertText(driver);
        Assert.assertNotNull(alertMsg, "Alert message tidak boleh null");
        Assert.assertFalse(alertMsg.isEmpty(), "Alert message tidak boleh kosong");
        Assert.assertEquals(alertMsg, "User does not exist.");
    }

    @Test(priority = 3)
    public void testLoginEmptyFields() {
        LoginPage.clickLoginButton(driver);
        LoginPage.inputUsername(driver, "");
        LoginPage.inputPassword(driver, "");
        LoginPage.clickSubmitButton(driver);

        String alertMsg = LoginPage.getAlertText(driver);
        Assert.assertNotNull(alertMsg, "Alert message tidak boleh null");
        Assert.assertFalse(alertMsg.isEmpty(), "Alert message tidak boleh kosong");
        Assert.assertEquals(alertMsg, "Please fill out Username and Password.");
    }

    @Test(priority = 4)
    public void testLoginInvalidPassword() {
        LoginPage.clickLoginButton(driver);
        LoginPage.inputUsername(driver, "Beta123");
        LoginPage.inputPassword(driver, "222");
        LoginPage.clickSubmitButton(driver);

        String alertMsg = LoginPage.getAlertText(driver);
        Assert.assertNotNull(alertMsg, "Alert message tidak boleh null");
        Assert.assertFalse(alertMsg.isEmpty(), "Alert message tidak boleh kosong");
        Assert.assertEquals(alertMsg, "Wrong password.");
    }

    @AfterMethod
    public void tearDown() {
        WebHelper.tearDown();
    }
}
