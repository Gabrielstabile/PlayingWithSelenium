package Pages;

import org.kohsuke.rngom.parse.host.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MePage extends BasePage {

    public MePage(WebDriver navegador) {
        super(navegador);
    }

    public MePage clicarMoreDataAboutYou(){
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU" )).click();

        return this;
    }

    public AddContactPage clicarBotaoAddMoreData(){
        navegador.findElement(By.xpath("//div[@id=\"moredata\"]//button[@data-target=\"addmoredata\"]")).click();

        return new AddContactPage(navegador);
    }
}
