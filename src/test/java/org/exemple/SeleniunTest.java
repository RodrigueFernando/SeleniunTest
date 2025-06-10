import io.github.bonigarcia.wdm.WebDriverManager;
import org.exemple.Home;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniunTest {
    private WebDriver driver;
    private Home home;

    @BeforeEach
    void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();  // <-- Inicializando o driver
        home = new Home(driver);
    }

    @AfterEach
    void tearDown(){
        if (driver != null) {

            driver.quit();
        }
    }


}
