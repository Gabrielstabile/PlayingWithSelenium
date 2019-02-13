package tests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "informacoesUsuarioTest.csv")
public class informacoesUsuarioTest {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void SetUp(){
        navegador = Web.createChrome();

        navegador.findElement(By.linkText("Sign in")).click();
        WebElement caixaSignIn = navegador.findElement(By.id("signinbox"));
        caixaSignIn.findElement(By.name("login")).sendKeys("julio0001");
        caixaSignIn.findElement(By.name("password")).sendKeys("123456");
        navegador.findElement(By.linkText("SIGN IN")).click();

        navegador.findElement(By.className("me")).click();
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU" )).click();
    }


    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name="tipo")String tipo, @Param(name="contato")String contato, @Param(name="mensagem")String mensagemEsperada){
        navegador.findElement(By.xpath("//div[@id=\"moredata\"]//button[@data-target=\"addmoredata\"]")).click();

        WebElement popUpMoreData = navegador.findElement(By.id("addmoredata"));
        WebElement campoType = popUpMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);
        popUpMoreData.findElement(By.name("contact")).sendKeys(contato);

        popUpMoreData.findElement(By.linkText("SAVE")).click();

        WebElement mensagemAdicao = navegador.findElement(By.id("toast-container"));
        String mensagemAdicaoTexto = mensagemAdicao.getText();
        assertEquals(mensagemEsperada, mensagemAdicaoTexto);

    }

    //@Test
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
        Screenshot.tirar(navegador, "C:\\Users\\707923\\Desktop\\Projetos Pessoais\\PlayingWithSelenium\\TestReport\\PlayingWithSelenium\\" + test.getMethodName() + ".png");

        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemExclusao));

        navegador.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown(){
    navegador.quit();
    }
}