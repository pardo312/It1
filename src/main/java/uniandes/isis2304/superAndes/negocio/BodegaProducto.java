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
public class BodegaProducto implements VOBodegaProducto
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/


	private long IDBodega;
	
	private String codigoDeBarrasProducto;


	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public BodegaProducto() 
    {
    	this.IDBodega = 0;
		this.codigoDeBarrasProducto = "";
	}


    public BodegaProducto( long IDBodega, String codigoDeBarrasProducto)
	 
    {
    	this.IDBodega =IDBodega;
		this.codigoDeBarrasProducto = codigoDeBarrasProducto;
	}

   
    public long getIDBodega() 
	{
		return IDBodega;
	}

	public void setIDBodega(long IDBodega) 
	{
		this.IDBodega = IDBodega;
	}
	
	public String getCodigoDeBarrasProducto() 
	{
		return codigoDeBarrasProducto;
	}

	public void setCodigoDeBarrasProducto(String codigoDeBarrasProducto) 
	{
		this.codigoDeBarrasProducto = codigoDeBarrasProducto;
	}
		
	@Override
	public String toString() 
	{
		String resp = "BodegaProducto [IDBodega=" + IDBodega + ", codigoDeBarrasProducto=" + codigoDeBarrasProducto + "]";
		return resp;	
	}
	

}
