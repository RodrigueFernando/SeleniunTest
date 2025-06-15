import io.github.bonigarcia.wdm.WebDriverManager;
import org.exemple.GeradorDeDados;
import org.exemple.Home;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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

    @Test
    @DisplayName("Clicar em Ver cadastro e depois no botão Voltar")
    void deveNavegarParaListaDePilotosEVoltar() throws InterruptedException {
      //  home.cadastrarPessoas();
        home.clicarBotaoCadastrar();
        home.clicarVerPilotosCadastrados();
        home.clicarBotaoVoltar();
    }

    @Test
    @DisplayName("Deleta piloto cadastrado")
    void deveDeletarPilotoCadastrado() throws InterruptedException {
        home.cadastrarPessoas();
        home.clicarBotaoCadastrar();
        home.clicarVerPilotosCadastrados();
        home.clickNoBotaoDeletar();
    }

    @Test
    @DisplayName("Cadastrar múltiplos pilotos e visualizar cadastro")
    void cadastrarMultiplosPilotos() throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            home.clicarBotaoCadastrar();
            home.cadastrarPessoas();
        }

        home.clicarVerPilotosCadastrados();
    }
    @Test
    @DisplayName("Deve cadastrar piloto e verificar dados na listagem")
    void deveCadastrarEPossuirDadosCorretosNaListagem() throws InterruptedException {

        home.cadastrarPessoas();

        String nomeDigitado = driver.findElement(By.name("nome")).getAttribute("value");
        String nacionalidadeDigitada = driver.findElement(By.name("nacionalidade")).getAttribute("value");
        String titulosDigitado = driver.findElement(By.name("titulos")).getAttribute("value");
        Select selectEquipe = new Select(driver.findElement(By.name("equipe")));
        String equipeSelecionada = selectEquipe.getFirstSelectedOption().getText();


        home.clicarBotaoCadastrar();
        home.clicarVerPilotosCadastrados();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[p[b[contains(text(),'Nome:')]]]")));

        // Captura todas as divs dos pilotos cadastrados
        List<WebElement> divsPilotos = driver.findElements(
                By.xpath("//div[p[b[contains(text(),'Nome:')]]]")
        );

        // Flag pra saber se achou o cadastro correto
        boolean cadastroCorretoEncontrado = divsPilotos.stream().anyMatch(div -> {
            String texto = div.getText();
            // Verifica se a div contém todos os dados esperados
            return texto.contains("Nome: " + nomeDigitado)
                    && texto.contains("Nacionalidade: " + nacionalidadeDigitada)
                    && texto.contains("Equipe: " + equipeSelecionada)
                    && texto.contains("Títulos: " + titulosDigitado);
        });

        Assertions.assertTrue(cadastroCorretoEncontrado, "O piloto cadastrado não foi encontrado corretamente na listagem.");


      //  divsPilotos.forEach(div -> System.out.println("\nPiloto cadastrado:\n" + div.getText()));
    }

    @Test
    @DisplayName("Deve cadastrar, visualizar, deletar e verificar remoção de piloto")
    void deveCadastrarVisualizarDeletarEPilotoNaoEstarMaisNaLista() throws InterruptedException {
        // Gerar dados conhecidos
        String nome = GeradorDeDados.gerarNome();
        String nacionalidade = GeradorDeDados.gerarNacionalidade();
        String equipe = GeradorDeDados.gerarEquipe();
        String titulos = GeradorDeDados.gerarTitulos();

        // Cadastrar o piloto
        home.cadastrarPessoas();
        home.clicarBotaoCadastrar();

        // Ir para a tela de listagem
        home.clicarVerPilotosCadastrados();

        List<WebElement> divsPilotos = driver.findElements(
                By.xpath("//div[p[b[contains(text(),'Nome:')]]]")
        );


        Assertions.assertFalse(divsPilotos.isEmpty(), "A lista de pilotos não deve estar vazia");

        home.clickNoBotaoDeletar();


        // Verificar que o piloto não está mais na lista
        List<WebElement> pilotoRemovido = driver.findElements(By.xpath("//div[p[b[contains(text(),'Nome:')]]]"));
        Assertions.assertTrue(pilotoRemovido.isEmpty(), "Piloto deve ter sido removido da lista");


    }




}
