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
import java.util.List;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 */
public class Consulta6 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/



	private long id;

	private float venta;


	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	public Consulta6() 
	{
		this.id = 0;
		this.venta = 0;
	}




	public Consulta6(long id, float venta) {
		this.id = id;
		this.venta = venta;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public float getVenta() {
		return venta;
	}

	public void setVenta(float venta) {
		this.venta = venta;
	}


	@Override
	public String toString() 
	{
		String resp = "Consulta6 [id=" + id + ", venta=" + venta +	"]";

		return resp;

	}



}
