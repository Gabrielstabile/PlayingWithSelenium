package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Web {
    public static WebDriver createChrome(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\707923\\Driver\\chromedriver.EXE");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        navegador.get("http://www.juliodelima.com.br/taskit/");

        return navegador;
    }
}
