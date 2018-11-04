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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 */
public class Factura 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/


//
//numeroFactura varchar(50) Primary Key ,
//fecha DATE
////);
	
	private String numeroFactura;
	
	private java.util.Date fecha;
	
	private int idCliente;
	
	

	

//	private List<Object []> tiposDeProductosQueGuardan;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public Factura() 
    {
    	this.numeroFactura = "";
		this.fecha = new Date(0);
		this.idCliente = 0;

	}


    public Factura(  String numeroFactura,
	
	 java.util.Date fecha2, int idCliente)
	 
    {
    	this.numeroFactura = numeroFactura;
		this.fecha = fecha2;
		this.idCliente = idCliente;

	}

   
	public String getNumeroFactura() 
	{
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) 
	{
		this.numeroFactura = numeroFactura;
	}
	

	public java.util.Date getfecha() 
	{
		return fecha;
	}

	public void setfecha(Date fecha) 
	{
		this.fecha = fecha;
	}
	
	public int getIdCliente() 
	{
		return idCliente;
	}

	public void setIdCliente(int idCliente) 
	{
		this.idCliente = idCliente;
	}
	
	
	
	
	@Override
	public String toString() 
	{
		String resp = "Factura [numeroFactura=" + numeroFactura + ", fecha=" + fecha + ", idCliente=" + idCliente 
				+ "]";

		return resp;
		
	}
	

}
