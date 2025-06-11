package org.exemple;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
        String nome = faker.name().fullName();
        String nacionalidade = faker.country().name();
        String[] equipes = {"Ferrari", "Red Bull", "Mercedes", "McLaren"};
        String equipeAleatoria = equipes[faker.random().nextInt(equipes.length)];
        String titulos = String.valueOf(faker.number().numberBetween(0, 8));

        // Preenchendo os campos com Faker

        synchronized(this) {
            campoNome.sendKeys(nome);
            campoNacionalidade.sendKeys(nacionalidade);
            new Select(selectEquipe).selectByVisibleText(equipeAleatoria);
            campoTitulos.clear();
            campoTitulos.sendKeys(titulos);

            wait(1000);
        }

    }

    public void clicarBotaoCadastrar() throws InterruptedException {

        WebElement botaoCadastrar = driver.findElement(By.cssSelector("button.botao-principal"));
        botaoCadastrar.click();
    }

}
