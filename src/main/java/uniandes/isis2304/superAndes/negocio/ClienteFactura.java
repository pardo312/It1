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

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 */
public class ClienteFactura implements VOClienteFactura
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/


	private String numeroFactura;
	
	private long IDCliente;


	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public ClienteFactura() 
    {
    	this.numeroFactura = "";
		this.IDCliente = 0;
	}


    public ClienteFactura( String numeroFactura, long IDCliente)
	 
    {
    	this.numeroFactura =numeroFactura;
		this.IDCliente = IDCliente;
	}

   
    public String getNumeroFactura() 
	{
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) 
	{
		this.numeroFactura = numeroFactura;
	}
	
	public long getIDCliente() 
	{
		return IDCliente;
	}

	public void setIDCliente(long IDCliente) 
	{
		this.IDCliente = IDCliente;
	}
		
	@Override
	public String toString() 
	{
		String resp = "ClienteFactura [IDCliente=" + IDCliente + ", numeroFactura=" + numeroFactura + "]";

		return resp;
		
	}
	

}
