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

import java.util.List;

/**
 * Clase para modelar la relación GUSTAN del negocio de los Parranderos:
 * Cada objeto de esta clase representa el hecho que un bebedor gusta de una bebida y viceversa.
 * Se modela mediante los identificadores del bebedor y de la bebida respectivamente.
 * Debe existir un bebedor con el identificador dado
 * Debe existir una bebida con el identificador dado 
 * 
 * @author Germán Bravo
 */
public class Estante implements VOEstante
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	
	private long id;	

	private float capacidadVolumen;

	private float capacidadPeso;
	
	private String unidadesPeso;
	
	private String unidadesVolumen;
	
	private float nivelAbastecimiento;
	
	private long IDBodega;
	
//	private List<Object []> tiposDeProductosQueGuardan;
//	
//	private List<Object []> productosQueGuardan;


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Estante() 
	{
		this.id = 0;
		this.capacidadVolumen = 0;
		this.capacidadPeso = 0;
		this.unidadesPeso = "";
		this.unidadesVolumen = "";
		this.nivelAbastecimiento = 0;
		this.IDBodega = 0;
	}

	/**
	 * Constructor con valores
	 * @param idBebedor - El identificador del bebedor. Debe exixtir un bebedor con dicho identificador
	 * @param idBebida - El identificador de la bebida. Debe existir una bebida con dicho identificador
	 */
	public Estante(long id ,float capacidadVolumen,float capacidadPeso,String unidadesPeso,String unidadesVolumen, float nivelAbastecimiento,long IDBodega) 
	{
		this.id = id;
		this.capacidadVolumen =capacidadVolumen;
		this.capacidadPeso = capacidadPeso;
		this.unidadesPeso = unidadesPeso;
		this.unidadesVolumen = unidadesVolumen;
		this.nivelAbastecimiento = nivelAbastecimiento;
		this.IDBodega = IDBodega;
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

	public long getIDBodega() 
	{
		return IDBodega;
	}

	public void setIDBodega(long IDBodega) 
	{
		this.IDBodega = IDBodega;
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
//	
//	public List<Object []> getProductosQueGuardan() 
//	{
//		return this.productosQueGuardan;
//	}
//
//	public void setProductosQueGuardan (List<Object []> productosQueGuardan) 
//	{
//		this.productosQueGuardan = productosQueGuardan;
//	}
	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		String resp = "Estante [id=" + id + ", capacidadVolumen=" + capacidadVolumen + ", capacidadPeso=" + capacidadPeso + ", unidadesPeso=" + unidadesPeso
				+ ", unidadesVolumen=" + unidadesVolumen + ", IDBodega=" + IDBodega+ "]";
		
//		resp += "\n --- Tipos De Productos Que Guardan\n";
//		int i = 1;
//		for (Object [] visita : tiposDeProductosQueGuardan)
//		{
//			TipoProducto tipoProducto = (TipoProducto) visita [0];
//			String nombre = (String) visita [1];
//			resp += i++ + ". " + "[" + tipoProducto.toString() + ", nombre=" + nombre + "]\n";;
//		}
		
//		resp += "\n --- Tipos De Productos Que Guardan\n";
//		int i = 1;
//		for (Object [] visita : productosQueGuardan)
//		{
//			Producto tipoProducto = (Producto) visita [0];
//			String nombre = (String) visita [1];
//			String marca = (String) visita [2];
//			float precioUnitario = (float) visita [3];
//			String presentacion = (String) visita [4];
//			float precioPorUnidad = (float) visita [5];
//			float cantidadEnLaPresentacion = (float) visita [6];
//			String unidadesDeMedida = (String) visita [7];
//			String especificacionesDeEmpacado = (String) visita [8];
//			float nivelDeReorden = (float) visita [9];
//			long IDPedido = (long) visita [10];
//			long IDSucursal = (long) visita [11];
//			long IDBodega = (long) visita [12];
//			resp += i++ + ". " + "[" + tipoProducto.toString() + ", nombre=" + nombre + "]\n";;
//		}
		

		
		
		return resp;
	}
	
}
