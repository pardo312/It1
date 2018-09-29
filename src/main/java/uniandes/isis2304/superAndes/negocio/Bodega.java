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
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 */
public class Bodega implements VOBodega
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private long id;
	
	private float capacidadVolumen;

	private float capacidadPeso;
	
	private String unidadesPeso;
	
	private String unidadesVolumen;
	
	private int idSucursal;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Bodega() 
    {
    	this.id = 0;
		this.capacidadVolumen = 0;
		this.capacidadPeso = 0;
		this.unidadesPeso = "";
		this.unidadesVolumen = "";
		this.idSucursal = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - El id del bart
	 * @param nombre - El nombre del bar
	 * @param ciudad - La ciudad del bar
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param cantSedes - Las sedes del bar (Mayor que 0)
	 */
    public Bodega(long id, float capacidadVolumen, float capacidadPeso, String unidadesPeso, String unidadesVolumen, int idSucursal) 
    {
    	this.id = id;
		this.capacidadVolumen = capacidadVolumen;
		this.capacidadPeso = capacidadPeso;
		this.unidadesPeso = unidadesPeso;
		this.unidadesVolumen = unidadesVolumen;
		this.idSucursal = idSucursal;
	}

    /**
	 * @return El id del bar
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id del bar
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return el nombre del bar
	 */
	public float getCapacidadVolumen() 
	{
		return capacidadVolumen;
	}
	
	/**
	 * @param nombre El nuevo nombre del bar
	 */
	public void setCapacidadVolumen(float capacidadVolumen) 
	{
		this.capacidadVolumen = capacidadVolumen;
	}
	
	/**
	 * @return la ciudad del bar
	 */
	public float getCapacidadPeso() 
	{
		return capacidadPeso;
	}
	
	/**
	 * @param ciudad - La nueva ciudad del bar
	 */
	public void setCapacidadPeso(float capacidadPeso) 
	{
		this.capacidadPeso = capacidadPeso;
	}
	
	/**
	 * @return El presupuesto del bar
	 */
	public String getUnidadesPeso() 
	{
		return unidadesPeso;
	}
	
	/**
	 * @param presupuesto - El nuevo presupuesto del bar (ALTO, MEDIO, BAJOO)
	 */
	public void setunidadesPeso(String unidadesPeso) 
	{
		this.unidadesPeso = unidadesPeso;
	}
	
	/**
	 * @return la cantSedes del bar
	 */
	public String getUnidadesVolumen() 
	{
		return unidadesVolumen;
	}
	
	public void setUnidadesVolumen(String unidadesVolumen) 
	{
		this.unidadesVolumen = unidadesVolumen;
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
		return "Bar [id=" + id + ", capacidadVolumen=" + capacidadVolumen + ", capacidadPeso=" + capacidadPeso + ", unidadesPeso=" + unidadesPeso
				+ ", unidadesVolumen=" + unidadesVolumen + ", idSucursal=" + idSucursal+ "]";
	}
	
	

}
