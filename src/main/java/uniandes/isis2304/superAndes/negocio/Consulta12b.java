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
public class Consulta12b 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/



	private int proveedor;

	private int numeroDeSolicitudes;


	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	public Consulta12b() 
	{
		this.proveedor = 0;
		this.numeroDeSolicitudes = 0;
	}

	public Consulta12b(int proveedor, int numeroDeSolicitudes) {
		this.proveedor = proveedor;
		this.numeroDeSolicitudes = numeroDeSolicitudes;
	}

	public long getProveedor() {
		return proveedor;
	}

	public void setProveedor(int proveedor) {
		this.proveedor = proveedor;
	}

	public int getNumeroDeSolicitudes() {
		return numeroDeSolicitudes;
	}

	public void setNumeroDeSolicitudes(int numeroDeSolicitudes) {
		this.numeroDeSolicitudes = numeroDeSolicitudes;
	}






	@Override
	public String toString() 
	{
		String resp = "Consulta10 [proveedor=" + proveedor +
				", numeroDeSolicitudes=" + numeroDeSolicitudes +
				"]";

		return resp;

	}

}
