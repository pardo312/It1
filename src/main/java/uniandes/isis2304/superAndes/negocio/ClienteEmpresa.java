

package uniandes.isis2304.superAndes.negocio;


public class ClienteEmpresa implements VOClienteEmpresa
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	
	private String NIT;
	
	private String direccion;
	


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public ClienteEmpresa() 
	{
		this.NIT = "0";
		this.direccion = "";
		
	}

	public ClienteEmpresa(  String NIT,
	
	 String direccion
	) 
	{
		this.NIT = NIT;
		this.direccion = direccion;
	}

	public String getNIT() 
	{
		return NIT;
	}

	public void setNIT(String NIT) 
	{
		this.NIT = NIT;
	}
	

	public String getDireccion() 
	{
		return direccion;
	}

	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}	

	@Override
	public String toString() 
	{
		String resp = "ClienteEmpresa [ NIT=" + NIT + ", direccion=" + direccion 
				 + "]";
		
		return resp;
		
	}

}
