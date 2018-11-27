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
	
	
	private int id;	

	
	private int nivelAbastecimiento;
				
	private int idSucursales;



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
		this.idSucursales = 0;
	}

	/**
	 * Constructor con valores
	 * @param idBebedor - El identificador del bebedor. Debe exixtir un bebedor con dicho identificador
	 * @param idBebida - El identificador de la bebida. Debe existir una bebida con dicho identificador
	 */
	public Estante(int id ,int nivelAbastecimiento,int idSucursales) 
	{
		this.id = id;
		
		this.nivelAbastecimiento = nivelAbastecimiento;
		this.idSucursales = idSucursales;
	}


	@Override
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	@Override
	public int getNivelAbastecimiento() 
	{
		return nivelAbastecimiento;
	}

	public void setNivelAbastecimiento(int nivelAbastecimiento) 
	{
		this.nivelAbastecimiento = nivelAbastecimiento;
	}


	@Override
	public int getIdSucursales() 
	{
		return idSucursales;
	}

	public void setIdSucursales(int idSucursales) 
	{
		this.idSucursales = idSucursales;
	}
	
	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		String resp = "Estante [id=" + id + ", nivelAbastecimiento=" + nivelAbastecimiento+ ", idSucursales=" + idSucursales+ "]";
	return resp;
	}

	
	
}
