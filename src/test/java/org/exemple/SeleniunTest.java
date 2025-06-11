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
       // WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();  // <-- Inicializando o driver
        home = new Home(driver);
        driver.manage().window().maximize();
        driver.get("https://f1-cadastro-tc1.vercel.app/");
    }

    @AfterEach
    void tearDown(){
        if (driver != null) {

            driver.quit();
        }
    }

    @Test
    @DisplayName("Deve abrir ")
    void abreNavegador() throws InterruptedException {
        home.AbreNavegador();
        Thread.sleep(1500);
    }
    @Test
    @DisplayName("Cadastrar piloto F1 e ver piloto cadastrato")
    void cadastrarPiloto() throws InterruptedException {
        home.cadastrarPessoas();
        home.clicarBotaoCadastrar();
        home.clicarVerPilotosCadastrados();
    }
}
