package automata;

import java.io.FileNotFoundException;

public class Inicio {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
	    GestorAutomata gestor=new GestorAutomata();
		gestor.leerArchivoInput("C:\\Users\\alegr\\Pictures\\automata.txt.txt");
		gestor.ejecutar("aaaaaaaabaaaaabcbcbcbcbcdbdbdbdbdbcacacacaabcd");
		
		gestor.verificarSiEsAFD();
		gestor.crearTablaAFNDe();

		gestor.crearTablaAFD1();
		System.out.print(gestor.ejecutar2("a"));
		
	

}

}