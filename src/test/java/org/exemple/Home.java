package org.exemple;

import org.openqa.selenium.WebDriver;

public class Home {

    private WebDriver driver;

    // Construtor para receber o driver
    public Home(WebDriver driver) {
        this.driver = driver;
    }

    public void AbreNavegador() {

        driver.get("https://f1-cadastro-tc1.vercel.app/");
    }
}
