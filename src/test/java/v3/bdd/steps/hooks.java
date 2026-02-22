package v3.bdd.steps;

import helper.utils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class hooks {
    // Kita gunakan static agar driver bisa diakses langsung
    // oleh Step Definitions tanpa Dependency Injection yang rumit
    public static WebDriver driver;

    @Before
    public void setUp() {
        // Memanggil DriverFactory netral yang sudah kita buat
        driver = utils.getDriver("chrome");
        driver.get("https://www.demoblaze.com/,");
    }

    @After
    public void tearDown() {
        // Menutup browser setelah skenario selesai
        utils.quitDriver();
    }
}
