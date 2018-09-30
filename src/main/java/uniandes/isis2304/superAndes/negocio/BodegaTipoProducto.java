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
public class BodegaTipoProducto implements VOBodegaTipoProducto
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/


	private long IDBodega;
	
	private String nombreTipo;


	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public BodegaTipoProducto() 
    {
    	this.IDBodega = 0;
		this.nombreTipo = "";


	}


    public BodegaTipoProducto( long IDBodega, String nombreTipo)
	 
    {
    	this.IDBodega =IDBodega;
		this.nombreTipo = nombreTipo;

	}

   
    public long getIDBodega() 
	{
		return IDBodega;
	}

	public void setIDBodega(long IDBodega) 
	{
		this.IDBodega = IDBodega;
	}
	
	public String getNombreTipo() 
	{
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) 
	{
		this.nombreTipo = nombreTipo;
	}
		
	@Override
	public String toString() 
	{
		String resp = "BodegaTipoProducto [IDBodega=" + IDBodega + ", nombreTipo=" + nombreTipo + "]";

		return resp;
		
	}
	

}
