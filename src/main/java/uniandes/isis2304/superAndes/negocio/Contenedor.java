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
public class Contenedor implements VOContenedor
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private long id;
	
	private float capacidadVolumen;

	private float capacidadPeso;
	
	private String unidadesPeso;
	
	private String unidadesVolumen;
	
	private long idBodegaSucursal;

//	private List<Object []> tiposDeProductosQueGuardan;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Contenedor() 
    {
    	this.id = 0;
		this.capacidadVolumen = 0;
		this.capacidadPeso = 0;
		this.unidadesPeso = "";
		this.unidadesVolumen = "";
		this.idBodegaSucursal = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - El id del bart
	 * @param nombre - El nombre del bar
	 * @param ciudad - La ciudad del bar
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param cantSedes - Las sedes del bar (Mayor que 0)
	 */
    public Contenedor(long id, float capacidadVolumen, float capacidadPeso, String unidadesPeso, String unidadesVolumen, long idBodegaSucursal) 
    {
    	this.id = id;
		this.capacidadVolumen = capacidadVolumen;
		this.capacidadPeso = capacidadPeso;
		this.unidadesPeso = unidadesPeso;
		this.unidadesVolumen = unidadesVolumen;
		this.idBodegaSucursal = idBodegaSucursal;
	}

   
	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}
	

	public float getCapacidadVolumen() 
	{
		return capacidadVolumen;
	}

	public void setCapacidadVolumen(float capacidadVolumen) 
	{
		this.capacidadVolumen = capacidadVolumen;
	}
	
	
	public float getCapacidadPeso() 
	{
		return capacidadPeso;
	}
	
	public void setCapacidadPeso(float capacidadPeso) 
	{
		this.capacidadPeso = capacidadPeso;
	}
	
	
	public String getUnidadesPeso() 
	{
		return unidadesPeso;
	}
	
	
	public void setunidadesPeso(String unidadesPeso) 
	{
		this.unidadesPeso = unidadesPeso;
	}
	
	
	public String getUnidadesVolumen() 
	{
		return unidadesVolumen;
	}
	
	public void setUnidadesVolumen(String unidadesVolumen) 
	{
		this.unidadesVolumen = unidadesVolumen;
	}
	
	public long getIdBodegaSucursal() 
	{
		return idBodegaSucursal;
	}
	
	
	public void setIdBodegaSucursal(long idBodegaSucursal) 
	{
		this.idBodegaSucursal = idBodegaSucursal;
	}
	
//	public List<Object []> getTiposDeProductosQueGuardan() 
//	{
//		return tiposDeProductosQueGuardan;
//	}
//
//	public void setTiposDeProductosQueGuardan (List<Object []> tiposDeProductosQueGuardan) 
//	{
//		this.tiposDeProductosQueGuardan = tiposDeProductosQueGuardan;
//	}
	
	
	@Override
	public String toString() 
	{
		String resp = "Contenedor [id=" + id + ", capacidadVolumen=" + capacidadVolumen + ", capacidadPeso=" + capacidadPeso + ", unidadesPeso=" + unidadesPeso
				+ ", unidadesVolumen=" + unidadesVolumen + ", idBodegaSucursal=" + idBodegaSucursal+ "]";
		
//		resp += "\n --- Tipos De Productos Que Guardan\n";
//		int i = 1;
//		for (Object [] visita : tiposDeProductosQueGuardan)
//		{
//			TipoProducto tipoProducto = (TipoProducto) visita [0];
//			String nombre = (String) visita [1];
//			resp += i++ + ". " + "[" + tipoProducto.toString() + ", nombre=" + nombre + "]\n";;
//		}
		return resp;
		
	}
	
	

}
