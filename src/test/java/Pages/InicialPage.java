package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InicialPage extends BasePage {


    public InicialPage(WebDriver navegador) {
        super(navegador);
    }

    public MePage clicarEmMe(){
        navegador.findElement(By.className("me")).click();

        return new MePage(navegador);
    }
}
