
package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

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
	public Pedido ( int id,java.util.Date fechaEsperada,	java.util.Date fechaEntrega,String evaluacionCantidad,String evaluacionCalidad,int calificacion, int finalizado, int NITProveedor) 
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
	public Date getFechaEsperada() 
	{
		return fechaEsperada;
	}

	public void setFechaEsperada(Date fechaEsperada) 
	{
		this.fechaEsperada = fechaEsperada;
	}
	@Override
	public Date getFechaEntrega()
	{
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) 
	{
		this.fechaEntrega = fechaEntrega;
	}
	
	@Override
	public String getEvaluacionCantidad()
	{
		return evaluacionCantidad;
	}

	public void setEvaluacionCantidad(String evaluacionCantidad) 
	{
		this.evaluacionCantidad = evaluacionCantidad;
	}
	
	@Override
	public String getEvaluacionCalidad()
	{
		return evaluacionCalidad;
	}

	public void setEvaluacionCalidad(String evaluacionCalidad) 
	{
		this.evaluacionCalidad = evaluacionCalidad;
	}
	
	@Override
	public int getCalificacion() 
	{
		return calificacion;
	}

	public void setCalificacion(int calificacion) 
	{
		this.calificacion = calificacion;
	}
	@Override
	public int getFinalizado() 
	{
		return finalizado;
	}

	public void setFinalizado(char finalizado) 
	{
		this.finalizado = finalizado;
	}
	
	@Override
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
