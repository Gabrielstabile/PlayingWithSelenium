package tests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class informacoesUsuarioTest {
    private WebDriver navegador;

    @Before
    public void SetUp(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\707923\\Driver\\chromedriver.EXE");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        navegador.get("http://www.juliodelima.com.br/taskit/");
        navegador.findElement(By.linkText("Sign in")).click();
        WebElement caixaSignIn = navegador.findElement(By.id("signinbox"));
        caixaSignIn.findElement(By.name("login")).sendKeys("julio0001");
        caixaSignIn.findElement(By.name("password")).sendKeys("123456");
        navegador.findElement(By.linkText("SIGN IN")).click();

        navegador.findElement(By.className("me")).click();
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU" )).click();
    }


   // @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(){
        navegador.findElement(By.xpath("//div[@id=\"moredata\"]//button[@data-target=\"addmoredata\"]")).click();

        WebElement popUpMoreData = navegador.findElement(By.id("addmoredata"));
        WebElement campoType = popUpMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("Phone");
        popUpMoreData.findElement(By.name("contact")).sendKeys("+55119999911111");

        popUpMoreData.findElement(By.linkText("SAVE")).click();

        WebElement mensagemAdicao = navegador.findElement(By.id("toast-container"));
        String mensagemAdicaoTexto = mensagemAdicao.getText();
        assertEquals("Your contact has been added!", mensagemAdicaoTexto);

    }

    @Test
    public void testRemoverUmTelefoneDeUmUsuario(){
        navegador.findElement(By.xpath("//div[@id=\"moredata\"]//button[@data-target=\"addmoredata\"]")).click();

        WebElement popUpMoreData = navegador.findElement(By.id("addmoredata"));
        WebElement campoType = popUpMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("Phone");
        popUpMoreData.findElement(By.name("contact")).sendKeys("55998985673");

        popUpMoreData.findElement(By.linkText("SAVE")).click();

        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Your contact has been added!", mensagem);

        WebDriverWait aguardarMensagemConfirmacaoAdicao = new WebDriverWait(navegador,10);
        aguardarMensagemConfirmacaoAdicao.until(ExpectedConditions.stalenessOf(mensagemPop));

        navegador.findElement(By.xpath("//span[text()=\"55998985673\"]/following-sibling::a")).click();

        navegador.switchTo().alert().accept();

        WebElement mensagemExclusao = navegador.findElement(By.id("toast-container"));
        String mensagemExclusaoTexto = mensagemExclusao.getText();
        assertEquals("Rest in peace, dear phone!", mensagemExclusaoTexto);

        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemExclusao));

        navegador.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown(){
    navegador.quit();
    }
}