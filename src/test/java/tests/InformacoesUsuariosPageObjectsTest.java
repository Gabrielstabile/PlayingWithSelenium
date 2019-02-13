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
                .digitarLogin("julio0001")
                .digitarSenha("123456")
                .clicarBotaoSignIn();
    }

    @After
    public void tearDown(){
        navegador.quit();
    }
}
