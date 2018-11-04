/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * UniversNITad	de	los	Andes	(Bogotá	- Colombia)
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
public class Consulta5 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/



	private long NIT;

	private String nombre;
	
	private String codigoProducto;
	
	private float dineroProveedor;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	public Consulta5() 
	{
		this.NIT = 0;
		this.nombre = "";
		this.codigoProducto = "";
		this.dineroProveedor = 0;
	}




	public Consulta5(long NIT, String nombre, String codigoProducto, int dineroProveedor) {
		this.NIT = NIT;
		this.nombre = nombre;
		this.codigoProducto = codigoProducto;
		this.dineroProveedor = dineroProveedor;
	}

	public long getNIT() {
		return NIT;
	}

	public void setNIT(long NIT) {
		this.NIT = NIT;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public float getDineroProveedor() {
		return dineroProveedor;
	}

	public void setDineroProveedor(float dineroProveedor) {
		this.dineroProveedor = dineroProveedor;
	}
	


	@Override
	public String toString() 
	{
		String resp = "Consulta5 [NIT=" + NIT + ", nombre=" + nombre + 
				", codigoProducto=" + codigoProducto +
				", dineroProveedor=" + dineroProveedor +
				"]";
		return resp;

	}



}
