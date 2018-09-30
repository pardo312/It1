/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.superAndes.negocio;


public class ClienteEmpresa implements VOClienteEmpresa
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	
	private int NIT;
	
	private String direccion;
	


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public ClienteEmpresa() 
	{
		this.NIT = 0;
		this.direccion = "";
		
	}

	public ClienteEmpresa(  int NIT,
	
	 String direccion
	) 
	{
		this.NIT = NIT;
		this.direccion = direccion;
	}

	public int getNIT() 
	{
		return NIT;
	}

	public void setNIT(int NIT) 
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
