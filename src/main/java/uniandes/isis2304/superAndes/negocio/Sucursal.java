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
public class Sucursal implements VOSucursal
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private long id;
	
	private String nombre;
	
	private String ciudad;
	
	private String direccion;
	
	private String segmentacionDeMercado;
	
	private String tamanioInstalacion;
	
	private long NITSupermercado;


	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public Sucursal() 
    {
    	this.id = 0;
		this.nombre = "";
		this.ciudad = "";
		this.direccion = "";
		this.segmentacionDeMercado = "";
		this.tamanioInstalacion = "";
		this.NITSupermercado = 0;


	}


    public Sucursal(  long id,
	
	 String nombre,
	
	 String ciudad,
	
	 String direccion,
	
	 String segmentacionDeMercado,
	
	 String tamanioInstalacion,
	
	 long NITSupermercado)
	 
    {
    	this.id = id;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.segmentacionDeMercado = segmentacionDeMercado;
		this.tamanioInstalacion = tamanioInstalacion;
		this.NITSupermercado = NITSupermercado;


	}
    
    public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public String getCiudad() 
	{
		return ciudad;
	}


	public void setCiudad(String ciudad) 
	{
		this.ciudad = ciudad;
	}
	
	public String getDireccion() 
	{
		return direccion;
	}


	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}
	
	public String getSegmentacionDeMercado() 
	{
		return segmentacionDeMercado;
	}


	public void setSegmentacionDeMercado(String segmentacionDeMercado) 
	{
		this.segmentacionDeMercado = segmentacionDeMercado;
	}
	
	public String getTamanioInstalacion() 
	{
		return tamanioInstalacion;
	}


	public void setTamanioInstalacion(String tamanioInstalacion) 
	{
		this.tamanioInstalacion = tamanioInstalacion;
	}
	
	public long getNITSupermercado() 
	{
		return NITSupermercado;
	}


	public void setNITSupermercado(long NITSupermercado) 
	{
		this.NITSupermercado = NITSupermercado;
	}
		
	@Override
	public String toString() 
	{
		String resp = "Sucursal  [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + 
				", direccion=" + direccion + 
				", segmentacionDeMercado=" + segmentacionDeMercado + 
				", tamanioInstalacion=" + tamanioInstalacion + 
				", NITSupermercado=" + NITSupermercado + "]";

		return resp;
		
	}
	

}
