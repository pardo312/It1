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
public class Consulta8 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/



	private int id;

	private String NIT;

	private int puntosDeCompra;

	private int cedula;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	public Consulta8() 
	{
		this.id = 0;
		this.NIT = "";
		this.puntosDeCompra = 0;
		this.cedula = 0;
	}




	public Consulta8(int id, String NIT, int puntosDeCompra, int cedula) {
		this.id = id;
		this.NIT = NIT;
		this.puntosDeCompra = puntosDeCompra;
		this.cedula = cedula;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNIT() {
		return NIT;
	}

	public void setNIT(String NIT) {
		this.NIT = NIT;
	}

	public int getPuntosDeCompra() {
		return puntosDeCompra;
	}

	public void setPuntosDeCompra(int puntosDeCompra) {
		this.puntosDeCompra = puntosDeCompra;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}




	@Override
	public String toString() 
	{
		String resp = "Consulta8 [id=" + id + ", NIT=" + NIT + 
				", puntosDeCompra=" + puntosDeCompra +
				", cedula=" + cedula  +
				"]";

		return resp;

	}



}
