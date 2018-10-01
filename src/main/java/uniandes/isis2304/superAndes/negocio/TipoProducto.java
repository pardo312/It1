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
 * @author Germán Bravo
 */
public class TipoProducto implements VOTipoProducto
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private long id;

	private String nombre;
	
	private String  metodoAlmacenamiento;
	
	private long IDCategoria;
	private long IDContenedor;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public TipoProducto() 
	{
		this.id = 0;
		this.nombre = "";
		this.metodoAlmacenamiento = "";
		this.IDCategoria = 0;
		this.IDCategoria = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - El identificador del tipo de bebida
	 * @param nombre - El nombre del tipo de bebida
	 */
	public TipoProducto(long id, String nombre,long IDCategoria,
	 long IDContenedor) 
	{
		this.id = id;
		this.nombre = nombre;
		this.IDCategoria =IDCategoria;
		this.IDContenedor = IDContenedor;
	}

	/**
	 * @return El id del tipo de bebida
	 */
	public long getId() 
	{
		return id;
	}
	public void setId(long id) 
	{
		this.id = id;
	}
	
	

	/**
	 * @return El nombre del tipo de bebida
	 */
	public String getNombre() 
	{
		return nombre;
	}

	/**
	 * @param nombre - El nuevo nombre del tipo de bebida
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getMetodoAlmacenamiento() 
	{
		return metodoAlmacenamiento;
	}

	public void setMetodoAlmacenamiento(String metodoAlmacenamiento) 
	{
		this.metodoAlmacenamiento = metodoAlmacenamiento;
	}
	
	public long getIDCategoria() 
	{
		return IDCategoria;
	}
	public void setIDCategoria(long IDCategoria) 
	{
		this.IDCategoria = IDCategoria;
	}
	
	public long getIDContenedor() 
	{
		return IDContenedor;
	}
	public void setIDContenedor(long IDContenedor) 
	{
		this.IDContenedor = IDContenedor;
	}
	

	
	@Override
	public String toString() 
	{
		return "TipoProducto [id=" + id + ", nombre=" + nombre +
				", metodoAlmacenamiento=" + metodoAlmacenamiento + 
				", IDCategoria=" + IDCategoria +
				", IDContenedor=" + IDContenedor +"]";
	}


	public boolean equals(Object tipo) 
	{
		TipoProducto tb = (TipoProducto) tipo;
		return id == tb.id && nombre.equalsIgnoreCase (tb.nombre);
	}

}
