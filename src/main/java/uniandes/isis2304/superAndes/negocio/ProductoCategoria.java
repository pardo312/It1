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
public class ProductoCategoria implements VOProductoCategoria
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/


	private long IDCategoria;
	
	private String codigoDeBarrasProducto;


	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public ProductoCategoria() 
    {
    	this.IDCategoria = 0;
		this.codigoDeBarrasProducto = "";
	}


    public ProductoCategoria( long IDCategoria, String codigoDeBarrasProducto)
	 
    {
    	this.IDCategoria =IDCategoria;
		this.codigoDeBarrasProducto = codigoDeBarrasProducto;
	}

   
    public long getIDCategoria() 
	{
		return IDCategoria;
	}

	public void setIDCategoria(long IDCategoria) 
	{
		this.IDCategoria = IDCategoria;
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
		String resp = "CategoriaProducto [IDCategoria=" + IDCategoria + ", codigoDeBarrasProducto=" + codigoDeBarrasProducto + "]";
		return resp;	
	}
	

}
