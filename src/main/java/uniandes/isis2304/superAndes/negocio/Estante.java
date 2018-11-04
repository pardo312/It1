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
	
	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		String resp = "Estante [id=" + id + ", idSucursal=" + idSucursal+ "]";
	return resp;
	}

	
	
}
