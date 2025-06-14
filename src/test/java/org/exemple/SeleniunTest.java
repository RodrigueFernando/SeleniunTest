import io.github.bonigarcia.wdm.WebDriverManager;
import org.exemple.Home;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniunTest {
    private WebDriver driver;
    private Home home;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

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
    //este de aceitação
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

    @Test
    @DisplayName("Exibir erros de campos obrigatórios ao cadastrar piloto com formulário vazio")
    void deveExibirErrosQuandoFormularioEstiverVazio() throws InterruptedException {
        home.clicarBotaoCadastrar();

        WebElement mensagemNome = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[contains(text(),'Nome obrigatório')]"))
                );

        WebElement mensagemIdade = driver.findElement(
                By.xpath("//*[contains(text(),'Escolha sua equipe')]")
        );

        WebElement mensagemNacionalidade = driver.findElement(
                By.xpath("//*[contains(text(),'Nacionalidade obrigatória')]")
        );

        Assertions.assertEquals("Nome obrigatório", mensagemNome.getText());
        Assertions.assertEquals("Escolha sua equipe", mensagemIdade.getText());
        Assertions.assertEquals("Nacionalidade obrigatória", mensagemNacionalidade.getText());
    }

}
