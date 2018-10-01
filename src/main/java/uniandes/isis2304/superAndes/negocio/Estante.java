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

	
	private float nivelAbastecimiento;
	
	private long idSucursal;
	
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
		
		this.nivelAbastecimiento = 0;
		this.idSucursal = 0;
	}

	/**
	 * Constructor con valores
	 * @param idBebedor - El identificador del bebedor. Debe exixtir un bebedor con dicho identificador
	 * @param idBebida - El identificador de la bebida. Debe existir una bebida con dicho identificador
	 */
	public Estante(long id ,float nivelAbastecimiento,long idSucursal) 
	{
		this.id = id;
		
		this.nivelAbastecimiento = nivelAbastecimiento;
		this.idSucursal = idSucursal;
	}


	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}

	public float getNivelAbastecimiento() 
	{
		return nivelAbastecimiento;
	}

	public void setNnivelAbastecimiento(float nivelAbastecimiento) 
	{
		this.nivelAbastecimiento = nivelAbastecimiento;
	}


	public long getIdSucursal() 
	{
		return idSucursal;
	}

	public void setIdSucursal(long idSucursal) 
	{
		this.idSucursal = idSucursal;
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
		String resp = "Estante [id=" + id + ", idSucursal=" + idSucursal+ "]";
		
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
