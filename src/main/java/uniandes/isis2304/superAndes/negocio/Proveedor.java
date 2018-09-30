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
public class Proveedor implements VOProveedor
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private String NIT;
	
	private String nombre;


	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public Proveedor() 
    {
    	this.NIT = "";
		this.nombre = "";


	}


    public Proveedor( String NIT, String nombre)
	 
    {
    	this.NIT = "";
		this.nombre = "";

	}

   
	public String getNIT() 
	{
		return NIT;
	}

	public void setNIT(String NIT) 
	{
		this.NIT = NIT;
	}
	

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
		
	@Override
	public String toString() 
	{
		String resp = "Proveedor [NIT=" + NIT + ", nombre=" + nombre + "]";

		return resp;
		
	}
	

}
