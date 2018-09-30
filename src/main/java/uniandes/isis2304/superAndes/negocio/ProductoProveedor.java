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
import java.util.List;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 */
public class ProductoProveedor implements VOProductoProveedor
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/


//ID int Primary Key,
//precioUnidad FLOAT,
//calificacionDeCalidad int,
//fechaDeVencimiento  varchar(50),
//cantidadUnidades int,
//--LLave de Proveedor
//NITProveedor varchar(50),
//FOREIGN KEY (NITProveedor) REFERENCES Proveedor(NIT)
//);

	private long id;
	
	private float precioUnidad;

	private int calificacionDeCalidad;
	
	private String fechaDeVencimiento;
	
	private int cantidadUnidades;
	
	private String NITProveedor ;


	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public ProductoProveedor() 
    {
    	this.id = 0;
		this.precioUnidad = 0;
		this.calificacionDeCalidad = 0;
		this.fechaDeVencimiento = "";
		this.cantidadUnidades = 0;
		this.NITProveedor ="";
		

	}


    public ProductoProveedor(   long id,
	
	 float precioUnidad,

	 int calificacionDeCalidad,
	
	 String fechaDeVencimiento,
	
	 int cantidadUnidades,
	
	 String NITProveedor )
	 
    {
    	this.id = id;
		this.precioUnidad = precioUnidad;
		this.calificacionDeCalidad = calificacionDeCalidad;
		this.fechaDeVencimiento = fechaDeVencimiento;
		this.cantidadUnidades = cantidadUnidades;
		this.NITProveedor = NITProveedor;

	}
    public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}
	
	public float getPrecioUnidad() 
	{
		return precioUnidad;
	}


	public void setPrecioUnidad(float precioUnidad) 
	{
		this.precioUnidad = precioUnidad;
	}
	
	public int getCalificacionDeCalidad() 
	{
		return calificacionDeCalidad;
	}

	public void setCalificacionDeCalidad(int calificacionDeCalidad) 
	{
		this.calificacionDeCalidad = calificacionDeCalidad;
	}
	
	public String getFechaDeVencimiento() 
	{
		return fechaDeVencimiento;
	}

	public void setFechaDeVencimiento(String fechaDeVencimiento) 
	{
		this.fechaDeVencimiento = fechaDeVencimiento;
	}
	
	public int getCantidadUnidades() 
	{
		return cantidadUnidades;
	}

	public void setCantidadUnidades(int cantidadUnidades) 
	{
		this.cantidadUnidades = cantidadUnidades;
	}
	
	public String getNITProveedor() 
	{
		return NITProveedor;
	}

	public void setNITProveedor(String NITProveedor) 
	{
		this.NITProveedor = NITProveedor;
	}
	
	@Override
	public String toString() 
	{
		String resp = "ProductoProveedor [id=" + id + ", precioUnidad=" + precioUnidad
				+
				", calificacionDeCalidad=" + calificacionDeCalidad 
				+
				", fechaDeVencimiento=" + fechaDeVencimiento 
				+
				", cantidadUnidades=" + cantidadUnidades 
				+
				", NITProveedor=" + NITProveedor 
				+ "]";

		return resp;
		
	}
	
	

}
