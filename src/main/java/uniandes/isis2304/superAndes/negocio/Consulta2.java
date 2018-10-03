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
public class Consulta2 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/


	private long id;
	
	private float cantidadVecesUsada;

	private String descripcion;

	private Timestamp fecha_Venta;
	
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	public Consulta2() 
	{
		this.id = 0;
		this.descripcion = "";
		this.fecha_Venta = new Timestamp(0);
		this.cantidadVecesUsada = 0;
	}

	public Consulta2(long id, String descripcion, Timestamp fecha_Venta, long cantidadVecesUsada) {
		this.id = id;
		this.descripcion = descripcion;
		this.fecha_Venta = fecha_Venta;
		this.cantidadVecesUsada = cantidadVecesUsada;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public float getCantidadVecesUsada() {
		return cantidadVecesUsada;
	}

	public void setCantidadVecesUsada(float cantidadVecesUsada) {
		this.cantidadVecesUsada= cantidadVecesUsada;
	}
	
	public Timestamp getFecha_Venta() {
		return fecha_Venta;
	}
	public void setFecha_Venta(Timestamp fecha_Venta) {
		this.fecha_Venta = fecha_Venta;
	}


	@Override
	public String toString() 
	{
		String resp = "Consulta1 [id=" + id + ", cantidadVecesUsada=" + cantidadVecesUsada + ", fecha_Venta=" + fecha_Venta +", descripcion=" + descripcion+"]";

		return resp;

	}


}
