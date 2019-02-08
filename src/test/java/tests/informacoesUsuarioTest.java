package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class informacoesUsuarioTest {
    private WebDriver navegador;

    @Before
    public void SetUp(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\707923\\Driver\\chromedriver.EXE");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.get("http://www.juliodelima.com.br/taskit/");
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(){
        navegador.findElement(By.linkText("Sign in")).click();
        WebElement caixaSignIn = navegador.findElement(By.id("signinbox"));
        caixaSignIn.findElement(By.name("login")).sendKeys("julio0001");
        caixaSignIn.findElement(By.name("password")).sendKeys("123456");
        navegador.findElement(By.linkText("SIGN IN")).click();

        WebElement me = navegador.findElement(By.className("me"));
        String HiJulioText = me.getText();
        assertEquals("Hi, Julio", HiJulioText);
    }

    @After
    public void tearDown(){
    navegador.close();
    }
}
