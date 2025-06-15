package org.exemple;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Home {

    private WebDriver driver;
    private Faker faker = new Faker();

    // Construtor para receber o driver
    public Home(WebDriver driver) {
        this.driver = driver;
    }

    public void AbreNavegador() {

        driver.get("https://f1-cadastro-tc1.vercel.app/");
    }
    public void cadastrarPessoas() throws InterruptedException {

        WebElement campoNome = driver.findElement(By.name("nome"));
        WebElement campoNacionalidade = driver.findElement(By.name("nacionalidade"));
        WebElement selectEquipe = driver.findElement(By.name("equipe"));
        WebElement campoTitulos = driver.findElement(By.name("titulos"));

        // Gerando dados aleatórios
        String nome = GeradorDeDados.gerarNome();
        String nacionalidade = GeradorDeDados.gerarNacionalidade();
        String equipeAleatoria = GeradorDeDados.gerarEquipe();
        String titulos = GeradorDeDados.gerarTitulos();

        // Preenchendo os campos com Faker

        synchronized(this) {
            campoNome.sendKeys(nome);
            campoNacionalidade.sendKeys(nacionalidade);
            new Select(selectEquipe).selectByVisibleText(equipeAleatoria);
            campoTitulos.clear();
            campoTitulos.sendKeys(titulos);

            wait(2000);
        }

    }

    public void clicarBotaoCadastrar() throws InterruptedException {
        WebElement botaoCadastrar = driver.findElement(By.cssSelector("button.botao-principal"));
        botaoCadastrar.click();

    }
    public void clicarVerPilotosCadastrados() throws InterruptedException {
        synchronized(this) {
            driver.findElement(By.cssSelector("button.botao-secundario")).click();
            wait(1000);
        }
    }

    public void clicarBotaoVoltar() throws InterruptedException {
        driver.findElement(By.xpath("//button[text()='Voltar']")).click();

    }

    public void clickNoBotaoDeletar() throws InterruptedException {
        driver.findElement(By.xpath("//button[text()='Deletar']")).click();
    }
    public void preencherFormulario(String nome, String nacionalidade, String equipe, String titulos) throws InterruptedException {

        WebElement campoNome = driver.findElement(By.name("nome"));
        WebElement campoNacionalidade = driver.findElement(By.name("nacionalidade"));
        WebElement selectEquipe = driver.findElement(By.name("equipe"));
        WebElement campoTitulos = driver.findElement(By.name("titulos"));


        campoNome.sendKeys(nome);
        campoNacionalidade.sendKeys(nacionalidade);
        new Select(selectEquipe).selectByVisibleText(equipe);
        campoTitulos.sendKeys(titulos);


    }



}
