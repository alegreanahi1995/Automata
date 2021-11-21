package automata;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inicio {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
	    GestorAutomata gestor=new GestorAutomata();

		 String entradaTeclado = "";

	     System.out.println ("Por favor introduzca la ubicación del archivo:");
	     
	     Scanner entradaEscaner = new Scanner (System.in);

	     entradaTeclado = entradaEscaner.nextLine (); 

		gestor.leerArchivoInput(entradaTeclado);
		//gestor.ejecutar("aaaaaaaabaaaaabcbcbcbcbcdbdbdbdbdbcacacacaabcd");
		
		gestor.verificarSiEsAFD();
		gestor.crearTablaAFNDe();

		gestor.crearTablaAFD1();
		
	     entradaTeclado = "";
	     System.out.println ("Por favor introduzca la cadena:");
	     
	     entradaEscaner = new Scanner (System.in);

	     entradaTeclado = entradaEscaner.nextLine (); 
		if(gestor.ejecutar2(entradaTeclado))
			System.out.print("ACEPTA EL STRING");
		else System.out.print("NO ACEPTA EL STRING");

		
}

}