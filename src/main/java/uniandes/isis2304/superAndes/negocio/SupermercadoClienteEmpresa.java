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

import java.sql.Timestamp;
import java.util.List;


public class SupermercadoClienteEmpresa implements VOSupermercadoClienteEmpresa
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
		

	private long NITSupermercado;
	
	private String NITCliente;


	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public SupermercadoClienteEmpresa() 
    {
    	this.NITSupermercado = 0;
		this.NITCliente = "";
	}


    public SupermercadoClienteEmpresa( long NITSupermercado, String NITCliente)
	 
    {
    	this.NITSupermercado =NITSupermercado;
		this.NITCliente = NITCliente;
	}

   
    public long getNITSupermercado() 
	{
		return NITSupermercado;
	}

	public void setNITSupermercado(long NITSupermercado) 
	{
		this.NITSupermercado = NITSupermercado;
	}
	
	public String getNITCliente() 
	{
		return NITCliente;
	}

	public void setNITCliente(String NITCliente) 
	{
		this.NITCliente = NITCliente;
	}
		
	@Override
	public String toString() 
	{
		String resp = "SupermercadoClienteEmpresa [NITSupermercado=" + NITSupermercado + ", NITCliente=" + NITCliente + "]";
		return resp;	
	}
	

}
