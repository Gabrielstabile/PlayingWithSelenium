package tests;

import Pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import suporte.Web;

public class InformacoesUsuariosPageObjectsTest {
    private WebDriver navegador;

    @Before
    public void setUp(){
        navegador = Web.createChrome();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(){
        new LoginPage(navegador)
                .clickSignIn()
                .realizarLogin("julio0001", "123456")
                .clicarEmMe()
                .clicarMoreDataAboutYou()
                .clicarBotaoAddMoreData()
        ;
    }

    @After
    public void tearDown(){
        navegador.quit();
    }
}
