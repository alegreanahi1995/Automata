package automata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;




public class GestorAutomata {
	List<List<List<String>> > tabla=new ArrayList<List<List<String>>>();
	List<List<String> > tablanueva=new ArrayList<List<String>>();
	
	String[] alfabeto;
	String[] estadosFinales;
	Set<String> estadosFinalesNuevos=new HashSet<String>();
	ArrayList<String[]> defTransiciones=new ArrayList<String[]>();
	int cantidadEstados=0;
	ArrayList<Estado>estados=new ArrayList<Estado>();
	ArrayList<Transicion>transiciones;
	boolean esDeterministico=true;
	ArrayList<Estado> nuevoEstados2;

	ArrayList<String[]> defTransicionesAFD=new ArrayList<String[]>();
	


	
	//lee el archivo input
	public  String  leerArchivoInput(String archivo) throws InterruptedException, FileNotFoundException {

		int nroLinea=0;	  	
		FileReader f = new FileReader(archivo);

		System.out.println("Intentando leer archivo "+archivo);
		String resultado=null;    
		String strLinea=null;

		// Creamos el Buffer de Lectura
		BufferedReader buffer = new BufferedReader(f); 

		// Leer el archivo linea por linea
		try {
			while ((strLinea = buffer.readLine()) != null)   {
				// Imprimimos la línea por pantalla
				if(resultado==null){
					resultado=strLinea;
				}else{
					resultado=resultado+" "+strLinea;
				}

				nroLinea++;		
				crearEstructura(nroLinea,strLinea);





			}
			cargarEstados();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			//	e.printStackTrace();
			System.out.println("Error : " +e.getMessage());
		}
		// Cerramos el archivo
		try {
			System.out.println("Fin de lectura de archivo y set de parámetros");
			buffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Error : " +e.getMessage());
		}    			
		return resultado;
	}
	

	//verifica que contenga elementos validos y luego inicia el proceso para
	//ver si acepta el string el automata
	public boolean ejecutar2(String palabra) {
		if(!contieneElementosValidos(palabra)) {
			System.out.println("Ejecutar automata, todos los caracteres no pertenecen al lenguaje");
		return false;
		}
	
		return this.contieneCaracter(this.nuevoEstados2.get(0).nombre, palabra);
		
	}

	
	//va verificando segun la construccion del automata AFD en recorrer el automata para 
	//ver si acepta el string.
	public boolean contieneCaracter(String estado, String palabra)
	{
		

		int index=nuevoEstados2.indexOf(new Estado(estado,this.estadosFinalesNuevos.contains(estado)));
		if(index==-1) return false;
			for(int j=0;j<tablanueva.get(index).size();j++)
			{


			if(Character.toString(palabra.charAt(0)).compareTo(alfabeto[j])==0 && tablanueva.get(index).get(j).compareTo("0")!=0)
			{

				if(palabra.length()==1) 
				{

					if(this.estadosFinalesNuevos.contains(this.tablanueva.get(index).get(j)))
					return true;
					else return false;
				}
				
				else
				{
					return contieneCaracter(tablanueva.get(index).get(j),palabra.substring(1,palabra.length()));
				}
			}

			}
		
		return false;
					
					
				
	}
	
	//verifica si contiene caracteres validos segundo el alfabeto 
	private boolean contieneElementosValidos(String palabra) {
		int i=0;
		boolean resultado=false;
		while(i!=palabra.length()) {
			resultado=contieneAlfabeto(String.valueOf(palabra.charAt(i)));
			if(resultado==false) {
				System.out.println("El caracter "+String.valueOf(palabra.charAt(i))+" en la posición "+i+" no pertenece al lenguaje, no se ejecutará el autómata");
				break;
			}
			i++;
		}
		return resultado;
	}
	
	//verifica si en el alfabeto se encuentra tal caracter
	private boolean contieneAlfabeto(String caracter) {
		boolean resultado = false;
		for(int i=0;i<alfabeto.length;i++) {
			if(!caracter.equals(alfabeto[i].trim())) {
				resultado= false;
			}else {
				resultado=true;
				break;
			}
		}
		return resultado;
	}
		
		
	
	//crea la estructura
	private void crearEstructura(int lineaArchivo,String linea) {

		switch(lineaArchivo) {
		case 1: System.out.println("Alfabeto "+linea);linea+=", E";alfabeto=linea.split(", ");break; 
		case 2: System.out.println("Cantidad Estados "+linea);cantidadEstados=Integer.parseInt(linea);break;
		case 3: System.out.println("Estados finales "+linea); estadosFinales=linea.split(",");break;
		default : System.out.println("Transiciones "+linea);cargarDefTransiciones(lineaArchivo-2,linea);break;
		
		}


	
    }
	
	//carga los estados del automata AFNDE ingresado
	private void cargarEstados() {

		for(int i=0;i<cantidadEstados;i++)
		{
			estados.add(new Estado(Integer.toString(i+1),Arrays.asList(estadosFinales).contains(Integer.toString(i+1))));
		}
	}

	
	//carga definicion de transiciones
	private void cargarDefTransiciones(int i, String linea) {
		defTransiciones.add(linea.split(","));	

	}
	
	
	
	
	//devuelve los estados a los que va si se encuentra en el estado actual y en el string sigue
	//tal caracter
	public List<String> devolverTransiciones(String estadodesde, String caracter)
	{
		List<String>estados2=new ArrayList<String> ();
		boolean tienetransicion=false;
		for(String[] a: defTransiciones)
		{
		
			if(a[0].compareTo(estadodesde)==0 && a[1].split("->")[0].replaceAll(" ","").compareTo(caracter)==0)
			{
				estados2.add(a[1].split("->")[1].replaceAll(" ",""));
				tienetransicion=true;
				
			}
			
				
			
		}
		if(!tienetransicion)
		{
			estados2.add("0");
		}
			
		return estados2;
	}
	
	
	
	//calculas las clausuras de todos los estados
	//del automata AFNDE para construir luego el automata AFD
	public List<String> obtenerClausurasE()
	{
		List<String> clausuras=new ArrayList<String>();
		for(Estado e: estados)
		{
			String clausura2 = "";
			String clausura=obtenerClausuraEdelEstado(e.nombre);
			if(clausura.compareTo("")==0)
			  clausura2+=e.nombre;
			else clausura2=e.nombre+clausura;
			clausuras.add(clausura2);
		}
		
		return clausuras;
	}
	
	
	//obtiene la clasura de cierto estado
	private String obtenerClausuraEdelEstado(String e) {
		String clausura="";
		
		for(String[] a: defTransiciones)
		{

			if(a[1].split("->")[1].replaceAll(" ","").compareTo(e)!=0 &&
			a[0].compareTo(e)==0 && a[1].split("->")[0].replaceAll(" ","").compareTo("E")==0)
			{
				
			String estado=a[1].split("->")[1].replaceAll(" ","");
			clausura+=","+estado+obtenerClausuraEdelEstado(estado);
			
			}
		}
return clausura;
	}
	
	
	//la funcion inicial que permite crear la tabla AFD
	public void crearTablaAFD1()
	{
		List<String> clausuras=obtenerClausurasE();
		String estadoInicial=clausuras.get(0);
		nuevoEstados2=new ArrayList<Estado>();
		nuevoEstados2.add(new Estado(estadoInicial,false));
		ArrayList<Estado>nuevoEstados3=new ArrayList<Estado>();
		nuevoEstados3.add(new Estado(estadoInicial,false));
	   
	    tablanueva=new ArrayList<List<String>> ();
		crearTablaAFD(estadoInicial,nuevoEstados3,nuevoEstados2,tablanueva);
		
		
		 for (int i=0;i<tablanueva.size();i++) {
				for(int j=0;j<tablanueva.get(i).size();j++)
				{
					String caracterTemp=defTransiciones.get(i)[1].substring(0,defTransiciones.get(i)[1].indexOf("->"));
					String estadoTemp=defTransiciones.get(i)[1].substring(defTransiciones.get(i)[1].indexOf("->")+2,defTransiciones.get(i)[1].length());

					String[] transicion= new String[2];
					transicion[0]= nuevoEstados2.get(i).nombre;
					transicion[1]=" "+alfabeto[j]+" -> "+tablanueva.get(i).get(j);
					
		
					defTransicionesAFD.add(transicion);
					
				
				
				}
				
	
			}
		 
		 String caracterTemp="";
		 String estadoTemp="";
		 
		 
		 for(int i=0;i<this.defTransicionesAFD.size();i++)
		 {
			 
			caracterTemp=defTransicionesAFD.get(i)[1].substring(0,defTransicionesAFD.get(i)[1].indexOf("->"));
			estadoTemp=defTransicionesAFD.get(i)[1].substring(defTransicionesAFD.get(i)[1].indexOf("->")+2,defTransicionesAFD.get(i)[1].length());

				System.out.println(
						"Estado "+defTransicionesAFD.get(i)[0]+
						"Transicion "+defTransicionesAFD.get(i)[1]+
						"Caracter "+caracterTemp.trim()+" ");
						//"Estado al que va al estado "+estadoTemp.stripLeading());
			
		 }
		 
		 
		 crearNuevosEstadosFinales();
		 
		
	}

	
	//va viendo cuales estados eran finales en el automata AFNDE luego segun eso va tomando en 
	//verifica cual debe ser estado final en la nueva lista de estados finales del automata AFD
	
	private void crearNuevosEstadosFinales()
	{
		
		List<String>listadeestadosfinalesAFNDE = Arrays.asList(this.estadosFinales);

		for(int i=0;i<this.nuevoEstados2.size();i++)
		{
			Estado estado=this.nuevoEstados2.get(i);
			for(int j=0;j<listadeestadosfinalesAFNDE.size();j++) {

				String p=listadeestadosfinalesAFNDE.get(j);
				p=p.replaceAll("\\s","");
			if(estado.nombre.indexOf(p)!=-1 )
			{
				this.nuevoEstados2.get(i).esFinal=true;
				if(!estadosFinalesNuevos.contains(estado.nombre))
				{
					this.estadosFinalesNuevos.add(estado.nombre);
				}
				
			}
			}
		}
		
	}
	
	///crea la tabla AFD recorriendo recursivamente
	public void crearTablaAFD(String estado,
			ArrayList<Estado> nuevosEstados,ArrayList<Estado> nuevosEstados3,List<List<String>> tablanueva)
	{
		List<String>estadosnuevosdelestadoe=new ArrayList<String>();
		
		List<String>transicionesxcaracter=new ArrayList<String>();
		String estado2="";

		if(!nuevosEstados3.contains(new Estado(estado,false)))
	     {
			nuevosEstados3.add(new Estado(estado,false));
	     }
    	
		for (int i=0;i<alfabeto.length-1;i++) {
			
			estado2="";
			estado2=devolverEstadoAFDdadoelEstadoAFNDe(devolverEstadosHastaDadounConjuntoDesEstados(estado,alfabeto[i]));
	    	transicionesxcaracter.add(estado2);

	    if(!nuevosEstados.contains(new Estado(estado2,false)) && estado2.compareTo("0")!=0)
	     {
	    	
	      estadosnuevosdelestadoe.add(estado2);
	    	nuevosEstados.add(new Estado(estado2,false));
	     }
	    
	    
	    
	   
		 }
		

		tablanueva.add(transicionesxcaracter);
		for(int i=0;i<estadosnuevosdelestadoe.size();i++)
		{

	    	crearTablaAFD(estadosnuevosdelestadoe.get(i),nuevosEstados,nuevosEstados3,tablanueva);
		}
		
		
		
	}
	
	

	//devuelve los estados a los que va si viene tal caracter y un conjunto de estados
	//segun la tabla AFNDE 
	public List<String> devolverEstadosHastaDadounConjuntoDesEstados(String estadosnuevos,String caracter)
	{
	List<String>lista=new ArrayList<String>();
	String []estados_n=estadosnuevos.split(",");
		for(int i=0;i<estados_n.length;i++)
		{

			List<String> l=devolverEstadoHasta(estados_n[i],caracter);
			
			if(!l.contains("0"))
				lista.addAll(l);
			
			
		}
			
		if(lista.size()==0)
		{
			lista.add("0");
		
		}
		
		return lista;
	}
	
	//devuelve a los estados a los que puede ir cuando venga tal caracter, estando en cierto estado 
	public List<String>devolverEstadoHasta(String estado,String caracter)
	{
		List<String> lista=new ArrayList<String>();
		 for (int i=0;i<tabla.size();i++) {
				for(int j=0;j<tabla.get(i).size();j++)
				{
					if(caracter.compareTo(alfabeto[j])==0 && estados.get(i).nombre.compareTo(estado)==0)
					{
						lista.addAll(tabla.get(i).get(j));
					}
				}
				
	
				}
				
		return lista;
	}
	
	//devuelve segun los estados a los que va en un AFNDE donde hiria ahora para un automata 
	//deterministico
	public String devolverEstadoAFDdadoelEstadoAFNDe(List<String> estadosAfD)
	{
		String cadena="";
		String cadena2="";
		boolean primero=true;
		for(String estado:estadosAfD ) {
			if(estado.compareTo("0")!=0) {
			 cadena="";
			 cadena+=this.obtenerClausuraEdelEstado(estado);//verificar que saque el estado mismo
			if(primero) {
			 if(cadena.compareTo("")==0)
				  {cadena2+=estado;
				  primero=false;
				  }
			 else {cadena2+=estado+cadena;
			 primero=false;}
			}
			else {
				if(cadena.compareTo("")==0)
					  cadena=estado;
				 else cadena=estado+cadena;
				
				CharSequence [] aux=cadena.split(",");
				String cadenaaux="";
				for(CharSequence  c: aux)
				{
					if(!cadena2.contains(c)) {
					cadenaaux+=","+c;
					}
				}
				 cadena2+=cadenaaux;

			}
					
			}
		
		}
		
		if(cadena2.compareTo("")==0)
		{
			return "0";
		}
		return cadena2;
	}
	
	
	//crea la tabla AFNDE 
	public void crearTablaAFNDe()
	{
	 
	  
	   for(Estado e:estados) {

		   List<List<String>>transicionesxcaracter=new ArrayList<List<String>>();
		for(String a: alfabeto)
		{
			
		transicionesxcaracter.add(this.devolverTransiciones(e.nombre, a));

		
		}
		
			
		tabla.add(transicionesxcaracter);
	   }


		

	}

	private void recorrerdefTransiciones(){
		boolean transicionesValidas;
		String caracterTemp;
		String estadoTemp;
		for(int i=0;i<defTransiciones.size();i++) {
			caracterTemp=defTransiciones.get(i)[1].substring(0,defTransiciones.get(i)[1].indexOf("->"));
			estadoTemp=defTransiciones.get(i)[1].substring(defTransiciones.get(i)[1].indexOf("->")+2,defTransiciones.get(i)[1].length());

			System.out.println(
					"Estado "+defTransiciones.get(i)[0]+
					"Transicion "+defTransiciones.get(i)[1]+
					"Caracter "+caracterTemp.trim()+" "+contieneAlfabeto(caracterTemp.trim())+" ");
					//"Estado al que va al estado "+estadoTemp.stripLeading());
			transicionesValidas=contieneAlfabeto(caracterTemp.trim());
			if(!transicionesValidas) {
				System.out.println("Transicion inválida, caracter no válido en "+defTransiciones.get(i)[1]);
				break;
			}
		}
	}

	


}
