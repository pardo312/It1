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


public class SupermercadoClienteNatural implements VOSupermercadoClienteNatural
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
		

	private long NITSupermercado;
	
	private long cedulaCliente;


	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public SupermercadoClienteNatural() 
    {
    	this.NITSupermercado = 0;
		this.cedulaCliente = 0;
	}


    public SupermercadoClienteNatural( long NITSupermercado, long cedulaCliente)
	 
    {
    	this.NITSupermercado =NITSupermercado;
		this.cedulaCliente = cedulaCliente;
	}

   
    @Override
	public long getNITSupermercado() 
	{
		return NITSupermercado;
	}

	public void setNITSupermercado(long NITSupermercado) 
	{
		this.NITSupermercado = NITSupermercado;
	}
	
	@Override
	public long getCedulaCliente() 
	{
		return cedulaCliente;
	}

	public void setCedulaCliente(long cedulaCliente) 
	{
		this.cedulaCliente = cedulaCliente;
	}
		
	@Override
	public String toString() 
	{
		String resp = "SupermercadoClienteNatural [NITSupermercado=" + NITSupermercado + ", cedulaCliente=" + cedulaCliente + "]";
		return resp;	
	}
	

}
