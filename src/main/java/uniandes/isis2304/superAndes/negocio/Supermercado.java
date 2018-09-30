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
public class Supermercado implements VOSupermercado
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/


	private long NITSupermercado;
	
	private String nombre;
	
	private String correoElectronico;
	
	private String direccion;



	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public Supermercado() 
    {
    	this.NITSupermercado = 0;
		this.nombre = "";
		this.correoElectronico = "";
		this.direccion = "";

	}


    public Supermercado(  long NITSupermercado,
	
	 String nombre,
	
	 String correoElectronico,
	
	 String direccion
	
	 )
	 
    {
    	this.NITSupermercado = NITSupermercado;
		this.nombre = nombre;
		this.correoElectronico = correoElectronico;
		this.direccion = direccion;


	}
    
    public long getNITSupermercado() 
	{
		return NITSupermercado;
	}


	public void setNITSupermercado(long NITSupermercado) 
	{
		this.NITSupermercado = NITSupermercado;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public String getCorreoElectronico() 
	{
		return correoElectronico;
	}


	public void setCorreoElectronico(String correoElectronico) 
	{
		this.correoElectronico = correoElectronico;
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
		String resp = "Sucursal  [NITSupermercado=" + NITSupermercado + ", nombre=" + nombre + ", correoElectronico=" + correoElectronico + 
				", direccion=" + direccion + 
				 "]";

		return resp;
		
	}
	

}
