/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universfechaad	de	los	Andes	(Bogotá	- Colombia)
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
import java.util.Date;
/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 */
public class Consulta11b 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/



	private Date fecha;

	private int productosComprados;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	public Consulta11b() 
	{
		this.fecha = new Date();
		this.productosComprados = 0;
	}




	public Consulta11b(Date fecha, int productosComprados) {
		this.fecha = fecha;
		this.productosComprados = productosComprados;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getProductosComprados() {
		return productosComprados;
	}

	public void setProductosComprados(int productosComprados) {
		this.productosComprados = productosComprados;
	}




	@Override
	public String toString() 
	{
		String resp = "Consulta11 [fecha=" + fecha +
				", productosComprados=" + productosComprados  +
				"]";

		return resp;

	}


}
