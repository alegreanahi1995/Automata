package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import automata.GestorAutomata;

class Test1 {

	@Test
	void test() throws FileNotFoundException, InterruptedException {
		GestorAutomata gestor=new GestorAutomata();
		gestor.leerArchivoInput(".\\Archivos\\automata.txt");
		
		gestor.crearTablaAFNDe();

		gestor.crearTablaAFD1();
		assertTrue(gestor.ejecutar2("abbbbb"));

		assertTrue(gestor.ejecutar2("d"));
	}

	
	
	@Test
  void test2() throws FileNotFoundException, InterruptedException {
		GestorAutomata gestor=new GestorAutomata();
		gestor.leerArchivoInput(".\\Archivos\\automata.txt");
		
		gestor.crearTablaAFNDe();

	    gestor.crearTablaAFD1();
		assertTrue(gestor.ejecutar2("abbbbbbbb"));

	    assertTrue(gestor.ejecutar2("d"));
	}
	
	
	
	
	
	@Test
	void test3() throws FileNotFoundException, InterruptedException {
		GestorAutomata gestor=new GestorAutomata();
		gestor.leerArchivoInput(".\\Archivos\\automata2.txt");
		
		gestor.crearTablaAFNDe();

	    gestor.crearTablaAFD1();
		assertTrue(gestor.ejecutar2("abbbbbbbb"));

	}

		
	@Test
	public void test4() throws FileNotFoundException, InterruptedException {
		GestorAutomata gestor=new GestorAutomata();
		gestor.leerArchivoInput(".\\Archivos\\automata3.txt");
		
		gestor.crearTablaAFNDe();

	    gestor.crearTablaAFD1();

	    assertTrue(gestor.ejecutar2("100101"));

		
	}

	@Test
	public void test5() throws FileNotFoundException, InterruptedException {
		
		
		GestorAutomata gestor=new GestorAutomata();
		gestor.leerArchivoInput(".\\Archivos\\automata3.txt");
		
		gestor.crearTablaAFNDe();

	    gestor.crearTablaAFD1();

	    assertTrue(gestor.ejecutar2("00101"));
	
	}


	@Test
	public void test6() throws FileNotFoundException, InterruptedException {
		
		GestorAutomata gestor=new GestorAutomata();
		gestor.leerArchivoInput(".\\Archivos\\automata3.txt");
		
		gestor.crearTablaAFNDe();

	    gestor.crearTablaAFD1();

	    assertTrue(gestor.ejecutar2("00")==false);
	}
	
	
	
	@Test
	public void test7() throws FileNotFoundException, InterruptedException {
		GestorAutomata gestor=new GestorAutomata();
		gestor.leerArchivoInput(".\\Archivos\\automata3.txt");
		
		gestor.crearTablaAFNDe();

	    gestor.crearTablaAFD1();

	    assertTrue(gestor.ejecutar2("0")==false);
	}

	@Test
	public void test8() throws FileNotFoundException, InterruptedException {
		
		GestorAutomata gestor=new GestorAutomata();
		gestor.leerArchivoInput(".\\Archivos\\automata3.txt");
		
		gestor.crearTablaAFNDe();

	    gestor.crearTablaAFD1();

	    assertTrue(gestor.ejecutar2("1")==false);
	}
	
	@Test
	public void test9() throws FileNotFoundException, InterruptedException {
		
		GestorAutomata gestor=new GestorAutomata();
		gestor.leerArchivoInput(".\\Archivos\\automata3.txt");
		
		gestor.crearTablaAFNDe();

	    gestor.crearTablaAFD1();

	    assertTrue(gestor.ejecutar2("10011")==false);
	}
	
	
	@Test
	public void test10() throws FileNotFoundException, InterruptedException {
		
		GestorAutomata gestor=new GestorAutomata();
		gestor.leerArchivoInput(".\\Archivos\\automata3.txt");
		
		gestor.crearTablaAFNDe();

	    gestor.crearTablaAFD1();

	    assertTrue(gestor.ejecutar2("ab")==false);
	}
	
}
