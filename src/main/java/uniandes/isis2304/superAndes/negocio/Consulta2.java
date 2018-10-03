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
import java.sql.Timestamp;
import java.util.List;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 */
public class Consulta2 implements VOConsulta2
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/


	private int id;

	private String descripcion;

	private Date fechaVenta;
	
	private int cantidadVecesUsada;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	public Consulta2() 
	{
		this.id = 0;
		this.descripcion = "";
		this.fechaVenta = null;
		this.cantidadVecesUsada = 0;
	}

	public Consulta2(int id, String descripcion, Date fechaVenta, int cantidadVecesUsada) {
		this.id = id;
		this.descripcion = descripcion;
		this.fechaVenta = fechaVenta;
		this.cantidadVecesUsada = cantidadVecesUsada;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getCantidadVecesUsada() {
		return cantidadVecesUsada;
	}

	public void setCantidadVecesUsada(int cantidadVecesUsada) {
		this.cantidadVecesUsada= cantidadVecesUsada;
	}
	
	public Date getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}


	@Override
	public String toString() 
	{
		String resp = "Consulta1 [id=" + id + ", cantidadVecesUsada=" + cantidadVecesUsada + ", fechaVenta=" + fechaVenta +", descripcion=" + descripcion+"]";

		return resp;

	}


}
