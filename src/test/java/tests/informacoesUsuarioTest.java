package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class informacoesUsuarioTest {
    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\707923\\Driver\\chromedriver.EXE");
        WebDriver navegador = new ChromeDriver();
        navegador.get("http://www.juliodelima.com.br/taskit/");

        assertEquals(1,1 );
    }
}
