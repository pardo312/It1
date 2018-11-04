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
public class Promocion implements VOPromocion
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	
	private int id;
	
	private String descripcion;

	private String precioPromocion;
	
	private int idSucursal;
	
	
//	private List<Object []> ProductosQueOfrecen;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public Promocion() 
    {
    	this.id = 0;
		this.descripcion = "";
		this.precioPromocion = "";
		this.idSucursal = 0;

	}


    public Promocion( int id, String descripcion,String precioPromocion, int idSucursal)
	 
    {
    	this.id = id;
		this.descripcion = descripcion;
		this.precioPromocion = precioPromocion;
		this.idSucursal = idSucursal;

	}

   
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}
	

	public String getDescripcion() 
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}
	
	
	public String getPrecioPromocion() 
	{
		return precioPromocion;
	}
	
	public void setPrecioPromocion(String precioPromocion) 
	{
		this.precioPromocion = precioPromocion;
	}
	
	
	public int getIdSucursal() 
	{
		return idSucursal;
	}
	
	
	public void setIdSucursal(int idSucursal) 
	{
		this.idSucursal = idSucursal;
	}
	
	@Override
	public String toString() 
	{
		String resp = "Promocion [id=" + id + ", descripcion=" + descripcion + ", precioPromocion=" + precioPromocion + ", idSucursal=" + idSucursal
				+ "]";

		return resp;
		
	}
	

}
