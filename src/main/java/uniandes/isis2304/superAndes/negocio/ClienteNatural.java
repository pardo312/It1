

package uniandes.isis2304.superAndes.negocio;


public class ClienteNatural implements VOClienteNatural
{/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	
	private int cedula;
	
	private String nombre;
	
	private String email;


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public ClienteNatural() 
	{
		this.cedula = 0;
		this.nombre = "";
		this.email = "";
	}

	public ClienteNatural( int cedula,
	
	 String nombre,
	
	 String email) 
	{
		this.cedula = 0;
		this.nombre = "";
		this.email = "";
	}

	public int getCedula() 
	{
		return cedula;
	}

	public void setCedula(int cedula) 
	{
		this.cedula = cedula;
	}
	

	public String getNombre() 
	{
		return nombre;
	}

	public void setnombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	
	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}
		

	@Override
	public String toString() 
	{
		String resp = "ClienteNatural [cedula=" + cedula + ", nombre=" + nombre + ", email=" + email 
				 + "]";
		
		return resp;
		
	}

}
