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

import java.util.Date;

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

	private int id;
	
	private Date fechaEsperada;

	private Date fechaEntrega;
	
	private String evaluacionCantidad;
	
	private String evaluacionCalidad;
	
	private int calificacion;
	
	private int finalizado;
	
	private int NITProveedor;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Pedido ()
	{
		this.id = 0;
	this.fechaEsperada = new java.util.Date(0);
	this.fechaEntrega = new Date(0);
	this.evaluacionCantidad = "";
	this.evaluacionCalidad = "";
	this.calificacion = 0;
	this.finalizado = 0;
	this.NITProveedor = 0;
	}

	/**
	 * 
	 * @param id2
	 * @param fechaEsperada2
	 * @param fechaEntrega2
	 * @param evaluacionCantidad
	 * @param evaluacionCalidad
	 * @param calificacion
	 * @param finalizado
	 * @param NITProveedor
	 */
	public Pedido ( int id2,java.util.Date fechaEsperada2,	java.util.Date fechaEntrega2,String evaluacionCantidad,String evaluacionCalidad,int calificacion, int finalizado, int NITProveedor) 
	{
		

		this.id = id2;
		this.fechaEsperada = fechaEsperada2;
		this.fechaEntrega = fechaEntrega2;
		this.evaluacionCantidad = evaluacionCantidad;
		this.evaluacionCalidad = evaluacionCalidad;
		this.calificacion = calificacion;
		this.finalizado = finalizado;
		this.NITProveedor = NITProveedor;
	}


	public int getId() 
	{
		return id;
	}

	public void setIdBar(int id) 
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
	
	public int getCalificacion() 
	{
		return calificacion;
	}

	public void setCalificacion(int calificacion) 
	{
		this.calificacion = calificacion;
	}
	public int getFinalizado() 
	{
		return finalizado;
	}

	public void setFinalizado(char finalizado) 
	{
		this.finalizado = finalizado;
	}
	
	public int getNITProveedor() 
	{
		return NITProveedor;
	}

	public void setNITProveedor(int NITProveedor) 
	{
		this.NITProveedor = NITProveedor;
	}

	

	
	@Override
	public String toString() 
	{
		return "Pedido [id=" + id + ", fechaEsperada=" + fechaEsperada + ", fechaEntrega=" + fechaEntrega
				+ ", evaluacionCantidad=" + evaluacionCantidad + ", evaluacionCalidad=" + evaluacionCalidad + 
				", calificacion=" + calificacion + 
				", finalizado=" + finalizado + 
				", NITProveedor=" + NITProveedor +  "]";
	}

}
