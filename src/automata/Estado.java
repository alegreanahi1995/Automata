package automata;


public class Estado {
	String nombre;
	boolean esFinal;
	public Estado(String nombre, boolean esFinal)
	{
		this.nombre=nombre;
		this.esFinal=esFinal;
	}
	
	 public int hashCode() {
	        final int prime = 31;
	        int result = 1;

	        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
	        result = prime * result + ( this.esFinal ? 1 : 0);
	        return result;
	    }
	 
	public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Estado other = (Estado)obj;

        if (other.nombre == null) return false;
       // if (this.nombre.compareTo(other.nombre)!=0) return false;
        if(this.esFinal!=other.esFinal) return false;
        

        String []p=this.nombre.split(",");
        
        for(int i=0;i<p.length;i++)
		{
			if(!other.nombre.contains(p[i]))
				return false;
				
		}
        
        String []t=other.nombre.split(",");
        
        for(int i=0;i<t.length;i++)
      		{
      			if(!this.nombre.contains(t[i]))
      				return false;
      				
      		}
        return true;
    }

}
