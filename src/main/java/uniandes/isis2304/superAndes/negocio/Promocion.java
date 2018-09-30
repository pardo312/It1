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

//	ID int Primary Key ,
//	descripcion varchar(10),
//	precioPromocion VARCHAR(10),
//	--LLave Foranea de Estante
//	IDEstante int,
//	FOREIGN KEY (IDEstante) REFERENCES Estante(ID)
	
	private long id;
	
	private String descripcion;

	private String precioPromocion;
	
	private long IDEstante;
	

//	private List<Object []> tiposDeProductosQueGuardan;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public Promocion() 
    {
    	this.id = 0;
		this.descripcion = "";
		this.precioPromocion = "";
		this.IDEstante = 0;

	}


    public Promocion( long id, String descripcion,String precioPromocion, int IDEstante)
	 
    {
    	this.id = id;
		this.descripcion = descripcion;
		this.precioPromocion = precioPromocion;
		this.IDEstante = IDEstante;

	}

   
	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
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
	
	
	public long getIDEstante() 
	{
		return IDEstante;
	}
	
	
	public void setIDEstante(long IDEstante) 
	{
		this.IDEstante = IDEstante;
	}
	
	@Override
	public String toString() 
	{
		String resp = "Bodega [id=" + id + ", descripcion=" + descripcion + ", precioPromocion=" + precioPromocion + ", IDEstante=" + IDEstante
				+ "]";

		return resp;
		
	}
	

}
