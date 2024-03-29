package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginFormPage extends BasePage{


    public LoginFormPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginFormPage digitarLogin(String login){
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);

        return this;
    }

    public LoginFormPage digitarSenha(String password){
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);

        return this;
    }

    public InicialPage clicarBotaoSignIn(){
        navegador.findElement(By.linkText("SIGN IN")).click();

        return new InicialPage(navegador);
    }

    public InicialPage realizarLogin(String login, String senha){
        digitarLogin(login);
        digitarSenha(senha);
        clicarBotaoSignIn();

        return new InicialPage(navegador);
    }

}
