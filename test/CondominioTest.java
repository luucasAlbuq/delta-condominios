import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import model.condominio.Condominio;
import model.condominio.Endereco;
import model.condominio.Predio;
import model.condominio.TipoCondominio;

import org.junit.Before;
import org.junit.Test;


public class CondominioTest {
	
	private Condominio condTest1;
	private Condominio condTest2;
	private Endereco endTest1;
	private Endereco endTest2;
	private Predio predTest1;
	private Predio predTest2;

	@Before
	public void init() {
		condTest1 = new Condominio((double)5000, "Condomínio Teste 01", TipoCondominio.VERTICAL);
		condTest1.setId((long)1);
		endTest1 = new Endereco("Rua de endereço de teste 01", "01", "11111111");
		condTest1.setEndereco(endTest1);
		predTest1 = new Predio("Predio Teste 01", "Bloco Teste 1",100, 10);
		predTest2 = new Predio("Predio Teste 02", "Bloco Teste 2",500, 25);
		Set<Predio> predios = new HashSet<Predio>();
		predios.add(predTest1);
		condTest1.setPredios(predios);
		
		condTest2 = new Condominio((double)1000, "Condomínio Teste 02", TipoCondominio.VERTICAL);
		condTest2.setId((long)2);
		endTest2 = new Endereco("Rua de endereço de teste 02", "02", "22222222");
		condTest2.setEndereco(endTest2);
	}
	
	@Test
	public void CondominioConstrutorTest() {
		condTest1 = null;
		assertNull(condTest1);
		condTest1 = new Condominio((double) 1000,"Teste Construtor de Condomínio", TipoCondominio.VERTICAL);
	}
	
	@Test
	public void getNomeTest() {
		assertNotNull("getNome não funciona, retornando null", condTest1.getNome());
		String expected = "Condomínio Teste 01";
		String actual = condTest1.getNome();
		assertEquals("getName() não funciona, esperado: " + expected + ", mas foi: " + actual, expected, actual);
	}
	
	@Test
	public void setNomeTest() {
		condTest1.setNome("Novo Nome");
		String expected = "Novo Nome";
		String actual = condTest1.getNome();
		assertEquals("setName() não funciona, esperado: " + expected + ", mas foi: " + actual, expected, actual);
	}
	
	@Test
	public void getEnderecoTest() {
		assertNotNull("getEndereco não funciona, retornando null", condTest1.getEndereco());
		assertEquals("getEndereco não funciona, retornando objeto não esperado", Endereco.class, condTest1.getEndereco().getClass());

		String expected = "Rua de endereço de teste 01";
		String actual = condTest1.getEndereco().getNomeRua();
		assertEquals("getEndereco() não funciona, esperado: " + expected + ", mas foi: " + actual ,expected, actual);
		
		expected = "01";
		actual = condTest1.getEndereco().getNumero();
		assertEquals("getEndereco() não funciona, esperado: " + expected + ", mas foi: " + actual, expected, actual);
		
		expected = "11111111";
		actual = condTest1.getEndereco().getCep();
		assertEquals("getEndereco() não funciona, esperado: " + expected + ", mas foi: " + actual, expected, actual);
	}
	
	@Test
	public void setEnderecoTest() {
		condTest1.setEndereco(endTest2);
		
		String expected = "Rua de endereço de teste 02";
		String actual = condTest1.getEndereco().getNomeRua();
		assertEquals("setEndereco() não funciona, esperado: " + expected + ", mas foi: " + actual ,expected, actual);
		
		expected = "02";
		actual = condTest1.getEndereco().getNumero();
		assertEquals("setEndereco() não funciona, esperado: " + expected + ", mas foi: " + actual, expected, actual);
		
		expected = "22222222";
		actual = condTest1.getEndereco().getCep();
		assertEquals("setEndereco() não funciona, esperado: " + expected + ", mas foi: " + actual, expected, actual);
	}
	
	@Test
	public void getAreaTest() {
		assertNotNull("getArea não funciona, retornando null", condTest1.getArea());
		double expected = 5000;
		double actual = condTest1.getArea();
		assertEquals("getArea() não funciona, esperado: " + expected + ", mas foi: " + actual, expected, actual, 0.001);
	}
	
	@Test
	public void setAreaTest() {
		condTest1.setArea((double)30000);
		double expected = 30000;
		double actual = condTest1.getArea();
		assertEquals("setArea() não funciona, esperado: " + expected + ", mas foi: " + actual, expected, actual, 0.001);
	}
	
	@Test
	public void getTipoTest() {
		assertNotNull("getTipo não funciona, retornando null", condTest1.getTipo());
		String expected = "Vertical";
		String actual = condTest1.getTipo().toString();
		assertEquals("getTipo() não funciona, esperado: " + expected + ", mas foi: " + actual, expected, actual);
	}
	
	@Test
	public void setTipoTest() {
		condTest1.setTipo(TipoCondominio.HORIZONTAL);
		String expected = "Horizontal";
		String actual = condTest1.getTipo().toString();
		assertEquals("setTipo() não funciona, esperado: " + expected + ", mas foi: " + actual, expected, actual);
	}
	
	@Test
	public void getIdTest() {
		assertNotNull("getId não funciona, retornando null", condTest1.getId());
		Long expected = (long) 1;
		Long actual = condTest1.getId();
		assertEquals("getId não funciona, esperado: " + expected + ", mas foi: " + actual, expected, actual);
	}
	
	@Test
	public void setIdTest() {
		condTest1.setId((long) 1);
		Long expected = (long) 1;
		Long actual = condTest1.getId();
		assertEquals("setId não funciona, esperado: " + expected + ", mas foi: " + actual, expected, actual);
	}
	
	@Test
	public void getPrediosTest() {
		assertNotNull("getPredios não funciona, retornando null", condTest1.getPredios());
		int expected = 1;
		int actual = condTest1.getPredios().size();
		assertEquals("getPredios não funciona, esperado: " + expected + ", mas foi: " + actual, expected, actual);
	}
	
	@Test
	public void setPrediosTest() {
		Set<Predio> predios = new HashSet<Predio>();
		predios.add(predTest1);
		predios.add(predTest2);
		condTest1.setPredios(predios);
		
		int expected = 2;
		int actual = condTest1.getPredios().size();
		assertEquals("setPredios não funciona, esperado: " + expected + ", mas foi: " + actual, expected, actual);
	}
	
	@Test
	public void hashCodeTest() {
		assertNotNull("hashCode não funciona, retornando null", condTest1.getPredios());
	}
	
	//TODO Definir o equals da entidade Condominio
	@Test
	public void equalsTest() {
		assertFalse("equals não funciona, esperado: false, mas foi true", condTest1.equals(condTest2));
		assertFalse("equals não funciona, esperado: false, mas foi true", condTest1.equals(null));
		assertTrue("equals não funciona, esperado: true, mas foi false", condTest1.equals(condTest1));
	}
	
	@Test
	public void addPredioTest() {
		int expected = 1;
		int actual = condTest1.getPredios().size();
		assertEquals("addPredio não funciona, esperado: " + expected + ", mas foi: " + actual, expected, actual);
		condTest1.addPredio(predTest2);
		expected = 2;
		actual = condTest1.getPredios().size();
		assertEquals("addPredio não funciona, esperado: " + expected + ", mas foi: " + actual, expected, actual);
	}
	
}
