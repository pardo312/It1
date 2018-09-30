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

/**
 * Clase para modelar la relación SIRVEN del negocio de los Parranderos:
 * Cada objeto de esta clase representa el hecho que un bar sirve una bebida y viceversa.
 * Se modela mediante los identificadores del bar y de la bebida respectivamente
 * Debe existir un bar con el identificador dado
 * Debe existir una bebida con el identificador dado 
 * Adicionalmente contiene el horario (DIURNO, NOCTURNO, TODOS) en el cual el bar sirve la bebida
 * 
 * @author Germán Bravo
 */
public class Pedido implements VOPedido
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private long id;
	
	private Date fechaEsperada;

	private Date fechaEntrega;
	
	private String evaluacionCantidad;
	
	private String evaluacionCalidad;
	
	private float calificacion;
	
	private char finalizado;
	
	private String NITProveedor;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Pedido ()
	{
		this.id = 0;
	this.fechaEsperada = new Date(0);
	this.fechaEntrega = new Date(0);
	this.evaluacionCantidad = "";
	this.evaluacionCalidad = "";
	this.calificacion = 0;
	this.finalizado = 0;
	this.NITProveedor = "";
	}

	/**
	 * Constructor con valores
	 * @param idBar - El identificador del bar. Debe exixtir un bar con dicho identificador
	 * @param idBebida - El identificador de la bebida. Debe existir una bebida con dicho identificador
	 * @param horario - El horario en el que el bar sirve la bebida (DIURNO, NOCTURNO, TODOS)
	 */
	public Pedido ( long id,Date fechaEsperada,	Date fechaEntrega,String evaluacionCantidad,String evaluacionCalidad,float calificacion,char finalizado,String NITProveedor) 
	{
		

		this.id = id;
		this.fechaEsperada = fechaEsperada;
		this.fechaEntrega = fechaEntrega;
		this.evaluacionCantidad = evaluacionCantidad;
		this.evaluacionCalidad = evaluacionCalidad;
		this.calificacion = calificacion;
		this.finalizado = finalizado;
		this.NITProveedor = NITProveedor;
	}


	public long getId() 
	{
		return id;
	}

	public void setIdBar(long id) 
	{
		this.id = id;
	}
	
	public Date getFechaEsperada() 
	{
		return fechaEsperada;
	}

	public void setFechaEsperada(Date fechaEsperada) 
	{
		this.fechaEsperada = fechaEsperada;
	}
	public Date getFechaEntrega()
	{
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) 
	{
		this.fechaEntrega = fechaEntrega;
	}
	
	public String getEvaluacionCantidad()
	{
		return evaluacionCantidad;
	}

	public void setEvaluacionCantidad(String evaluacionCantidad) 
	{
		this.evaluacionCantidad = evaluacionCantidad;
	}
	
	public String getEvaluacionCalidad()
	{
		return evaluacionCalidad;
	}

	public void setEvaluacionCalidad(String evaluacionCalidad) 
	{
		
		
		this.evaluacionCalidad = evaluacionCalidad;
	}
	
	public float getCalificacion() 
	{
		return calificacion;
	}

	public void setCalificacion(float calificacion) 
	{
		this.calificacion = calificacion;
	}
	public char getFinalizado() 
	{
		return finalizado;
	}

	public void setFinalizado(char finalizado) 
	{
		this.finalizado = finalizado;
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
		return "Sirven [id=" + id + ", fechaEsperada=" + fechaEsperada + ", fechaEntrega=" + fechaEntrega
				+ ", evaluacionCantidad=" + evaluacionCantidad + ", evaluacionCalidad=" + evaluacionCalidad + 
				", calificacion=" + calificacion + 
				", finalizado=" + finalizado + 
				", NITProveedor=" + NITProveedor +  "]";
	}

}
