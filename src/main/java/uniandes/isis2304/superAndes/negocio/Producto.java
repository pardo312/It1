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

import java.sql.Timestamp;

/**
 * Clase para modelar la relación VISITAN del negocio de los Parranderos:
 * Cada objeto de esta clase representa el hecho que un bebedor visitó un bar y viceversa.
 * Se modela mediante los identificadores del bebedor y del bar respectivamente
 * Debe existir un bebedor con el identificador dado
 * Debe existir un bar con el identificador dado 
 * Adicionalmente contiene la fecha y el horario (DIURNO, NOCTURNO, TODOS) en el cual el bebedor visitó el bar
 * 
 * @author Germán Bravo
 */
public class Producto implements VOProducto
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private String codigoDeBarras;
	
	private String nombre;
	
	private String marca;
	
	private float precioUnitario;
	
	private String presentacion;
	
	private float precioPorUnidad;
	
	private float cantidadEnLaPresentacion;
	
	private String unidadesDeMedida;

	private String especificacionesDeEmpacado;
	
	private float nivelDeReorden;
	
	private long IDPedido;
	
	private long IDSucursal;
	
	private long IDContenedor;
	

	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Producto() 
	{
		this.codigoDeBarras = "";
		this.nombre = "";
		this.marca = "";
		this.precioUnitario = 0;
		this.presentacion = "";
		this.precioPorUnidad = 0;
		this.cantidadEnLaPresentacion = 0;
		this.unidadesDeMedida = "";
		this.especificacionesDeEmpacado ="";
		this.nivelDeReorden = 0;
		this.IDPedido = 0;
		this.IDSucursal = 0;
		this.IDContenedor = 0;
		
	}

	/**
	 * Constructor con valores
	 * @param idBebedor - El identificador del b ebedor. Debe existir un bebedor con dicho identificador
	 * @param idBar - El identificador del bar. Debe exixtir un bar con dicho identificador
	 * @param fechaVisita - La fecha en la cual se realiza la visita
	 * @param horario - El horario en el que el bebedor vista el bar (DIURNO, NOCTURNO, TODOS)
	 */
	public Producto(String codigoDeBarras, String nombre, String marca, float precioUnitario,
			String presentacion,float precioPorUnidad,float cantidadEnLaPresentacion,String unidadesDeMedida, String especificacionesDeEmpacado, 
			float nivelDeReorden,long IDPedido ,long IDSucursal,long IDBodega) 
	{
		this.codigoDeBarras = codigoDeBarras;
		this.nombre =nombre;
		this.marca = marca;
		this.precioUnitario =precioUnitario;
		this.presentacion = presentacion;
		this.precioPorUnidad = precioPorUnidad;
		this.cantidadEnLaPresentacion = cantidadEnLaPresentacion;
		this.unidadesDeMedida = unidadesDeMedida;
		this.especificacionesDeEmpacado =especificacionesDeEmpacado;
		this.nivelDeReorden = nivelDeReorden;
		this.IDPedido =IDPedido;
		this.IDSucursal =IDSucursal;
		this.IDContenedor = IDBodega;
	}


	public String getCodigoDeBarras() 
	{
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(String codigoDeBarras) 
	{
		this.codigoDeBarras = codigoDeBarras;
	}


	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}


	public String getMarca() 
	{
		return marca;
	}


	public void setMarca(String marca) 
	{
		this.marca = marca;
	}

	public float getPrecioUnitario() 
	{
		return precioUnitario;
	}


	public void setPrecioUnitario(float precioUnitario) 
	{
		this.precioUnitario = precioUnitario;
	}
	
	public String getPresentacion() 
	{
		return presentacion;
	}


	public void setPresentacion(String presentacion) 
	{
		
		this.presentacion = presentacion;
	}
	
	public float getPrecioPorUnidad() 
	{
		return precioPorUnidad;
	}


	public void setPrecioPorUnidad(float precioPorUnidad) 
	{
		this.precioPorUnidad = precioPorUnidad;
	}
	
	public float getCantidadEnLaPresentacion() 
	{
		return cantidadEnLaPresentacion;
	}

	public void setCantidadEnLaPresentacion(float cantidadEnLaPresentacion) 
	{
		this.cantidadEnLaPresentacion = cantidadEnLaPresentacion;
	}

	public String getUnidadesDeMedida() 
	{
		return unidadesDeMedida;
	}


	public void setUnidadesDeMedida(String unidadesDeMedida) 
	{		
		this.unidadesDeMedida = unidadesDeMedida;
	}
	
	public String getEspecificacionesDeEmpacado() 
	{
		return especificacionesDeEmpacado;
	}

	public void setEspecificacionesDeEmpacado(String especificacionesDeEmpacado) 
	{
		this.especificacionesDeEmpacado = especificacionesDeEmpacado;
	}
	
	public float getNivelDeReorden() 
	{
		return nivelDeReorden;
	}

	public void setNivelDeReorden(float nivelDeReorden) 
	{
		this.nivelDeReorden = nivelDeReorden;
	}
	
	public long getIDPedido() 
	{
		return IDPedido;
	}

	public void setIDPedido(long IDPedido) 
	{
		this.IDPedido = IDPedido;
	}
	
	public long getIDSucursal() 
	{
		return IDSucursal;
	}

	public void setIDSucursal(long IDSucursal) 
	{
		this.IDSucursal = IDSucursal;
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
		return "Producto [codigoDeBarras=" + codigoDeBarras + ", nombre=" + nombre + ", marca=" + marca + ", precioUnitario="
				+ precioUnitario  + ", presentacion=" + presentacion + ", precioPorUnidad=" + precioPorUnidad + ", cantidadEnLaPresentacion=" + cantidadEnLaPresentacion + 
				", unidadesDeMedida=" + unidadesDeMedida + ", especificacionesDeEmpacado=" + especificacionesDeEmpacado+
				", nivelDeReorden=" + nivelDeReorden +", IDPedido=" + IDPedido +", IDSucursal=" + IDSucursal +", IDBodega=" + IDContenedor +"]";
	}

	

	
}
