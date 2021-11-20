package automata;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Inicio {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
	    GestorAutomata gestor=new GestorAutomata();
	/*	gestor.leerArchivoInput(".\\Archivos\\automata2.txt");
		gestor.ejecutar("aaaaaaaabaaaaabcbcbcbcbcdbdbdbdbdbcacacacaabcd");
		
		gestor.verificarSiEsAFD();
		gestor.crearTablaAFNDe();

		gestor.crearTablaAFD1();
		System.out.print(gestor.ejecutar2("abbb"));
*/
		List<Estado> l=new ArrayList<Estado>();
		
		Estado e=new Estado("1,2",false);
	        
		l.add(e);
		Estado p=new Estado("4,2,1",false);
	       System.out.print(l.contains(p));
		
}

}