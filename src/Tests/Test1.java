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
		gestor.ejecutar("aaaaaaaabaaaaabcbcbcbcbcdbdbdbdbdbcacacacaabcd");
		
		gestor.crearTablaAFNDe();

		gestor.crearTablaAFD1();
		assertTrue(gestor.ejecutar2("a"));

		assertTrue(gestor.ejecutar2("d"));
	}

	
	
	@Test
	void test2() throws FileNotFoundException, InterruptedException {
		GestorAutomata gestor=new GestorAutomata();
		gestor.leerArchivoInput(".\\Archivos\\automata.txt");
		gestor.ejecutar("aaaaaaaabaaaaabcbcbcbcbcdbdbdbdbdbcacacacaabcd");
		
		gestor.crearTablaAFNDe();

		gestor.crearTablaAFD1();
		assertTrue(gestor.ejecutar2("a"));

		assertTrue(gestor.ejecutar2("d"));
	}
}
