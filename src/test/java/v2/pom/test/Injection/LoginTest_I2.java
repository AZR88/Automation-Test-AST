package v2.pom.test;

import helper.WebHelper;
import v2.pom.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static helper.WebHelper.driver;

public class LoginTest_I2 {

    @BeforeMethod
    public void setup() {
        WebHelper.startDriver("chrome");
        driver.get("https://www.demoblaze.com/");
    }

    @Test(priority = 1)
    public void testLoginValid() {
        // Menggunakan abstraksi dari LoginPage
        LoginPage.clickLoginButton(driver);
        LoginPage.inputUsername(driver, "Beta123");
        LoginPage.inputPassword(driver, "123");
        LoginPage.clickSubmitButton(driver);

        String actualUser = LoginPage.getDisplayedUsername(driver);
        Assert.assertEquals(actualUser, "Beta123", "Username tidak sesuai!");
    }

    @Test(priority = 2)
    public void testLoginInvalidUser() {
        LoginPage.clickLoginButton(driver);
        LoginPage.inputUsername(driver, "UserSalah");
        LoginPage.inputPassword(driver, "123");
        LoginPage.clickSubmitButton(driver);

        String alertMsg = LoginPage.getAlertText(driver);
        Assert.assertEquals(alertMsg, "Wrong credentials!");
    }

    @Test(priority = 3)
    public void testLoginEmptyFields() {
        LoginPage.clickLoginButton(driver);
        LoginPage.inputUsername(driver, "");
        LoginPage.inputPassword(driver, "");
        LoginPage.clickSubmitButton(driver);

        String alertMsg = LoginPage.getAlertText(driver);
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
