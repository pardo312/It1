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
import java.util.LinkedList;
import java.util.List;

/**
 * Clase para modelar el concepto BEBEDOR del negocio de los Parranderos
 *
 * @author Germán Bravo
 */
public class Categoria implements VOCategoria
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private long id;	
	
	private String nombreCategoria;
	
	private char perecedero;
	


	//codigo de barras del producto
	private String codigoDeBarrasProducto;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Categoria() 
	{
		this.id = 0;
		this.nombreCategoria = "";
		this.perecedero = 0;
		this.codigoDeBarrasProducto = "";
	}

	/**
	 * Constructor con valores
	 * @param id - El id del bebedor
	 * @param nombreCategoria - El nombreCategoria del bebedor
	 * @param perecedero - La perecedero del bebedor
	 * @param fechaVencimiento - El fechaVencimiento del bebedor (ALTO, MEDIO, BAJO)
	 */
	public Categoria(long id, String nombreCategoria, char perecedero,String codigoDeBarrasProducto) 
	{
		this.id = id;
		this.nombreCategoria = nombreCategoria;
		this.perecedero = perecedero;
		this.codigoDeBarrasProducto = codigoDeBarrasProducto;
	}

	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}
	

	public String getNombreCategoria() 
	{
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) 
	{
		this.nombreCategoria = nombreCategoria;
	}
	
	
	public char getPerecedero() 
	{
		return perecedero;
	}
	
	public void setPerecedero(char perecedero) 
	{
		this.perecedero = perecedero;
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
		String resp = "Categoria [id=" + id + ", nombreCategoria=" + nombreCategoria + ", perecedero=" + perecedero + ", codigoDeBarrasProducto=" + codigoDeBarrasProducto + "]";
		
		return resp;
		
	}

}
