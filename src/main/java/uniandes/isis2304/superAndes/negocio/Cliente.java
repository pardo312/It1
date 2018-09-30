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


public class Cliente implements VOCliente
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private long id;	
	
	private int puntosDeCompra;
	
	private int cedulaCliente;
	
	private String NITCliente;


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Cliente() 
	{
		this.id = 0;
		this.puntosDeCompra = 0;
		this.cedulaCliente = 0;
		this.NITCliente = "";
	}

	public Cliente(long id, String NITCliente, int cedulaCliente, int puntosDeCompra) 
	{
		this.id = id;
		this.NITCliente = NITCliente;
		this.cedulaCliente = cedulaCliente;
		this.puntosDeCompra = puntosDeCompra;
	}

	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}
	

	public String getNITCliente() 
	{
		return NITCliente;
	}

	public void setNITCliente(String NITCliente) 
	{
		this.NITCliente = NITCliente;
	}
	
	
	public int getPuntosDeCompra() 
	{
		return puntosDeCompra;
	}
	
	public void setPuntosDeCompra(int puntosDeCompra) 
	{
		this.puntosDeCompra = puntosDeCompra;
	}
	
	
	public int getCedulaCliente() 
	{
		return cedulaCliente;
	}
	
	
	public void setCedulaCliente(int cedulaCliente) 
	{
		this.cedulaCliente = cedulaCliente;
	}
	
	

	@Override
	public String toString() 
	{
		String resp = "Categoria [id=" + id + ", puntosDeCompra=" + puntosDeCompra + ", NITCliente=" + NITCliente + ", cedulaCliente=" + cedulaCliente
				 + "]";
		
		return resp;
		
	}

}
