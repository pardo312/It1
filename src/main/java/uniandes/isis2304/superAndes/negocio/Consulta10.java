/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * UniversidClientead	de	los	Andes	(Bogotá	- Colombia)
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
public class Consulta10 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/



	private int idCliente;

	private String NITCliente;

	private int puntosDeCompra;

	private int numProductos;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	public Consulta10() 
	{
		this.idCliente = 0;
		this.NITCliente = "";
		this.puntosDeCompra = 0;
		this.numProductos = 0;
	}




	public Consulta10(int idCliente, String NITCliente, int puntosDeCompra, int numProductos) {
		this.idCliente = idCliente;
		this.NITCliente = NITCliente;
		this.puntosDeCompra = puntosDeCompra;
		this.numProductos = numProductos;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNITCliente() {
		return NITCliente;
	}

	public void setNITCliente(String NITCliente) {
		this.NITCliente = NITCliente;
	}

	public int getPuntosDeCompra() {
		return puntosDeCompra;
	}

	public void setPuntosDeCompra(int puntosDeCompra) {
		this.puntosDeCompra = puntosDeCompra;
	}

	public int getNumProductos() {
		return numProductos;
	}

	public void setNumProductos(int numProductos) {
		this.numProductos = numProductos;
	}




	@Override
	public String toString() 
	{
		String resp = "Consulta8 [idCliente=" + idCliente + ", NITCliente=" + NITCliente + 
				", puntosDeCompra=" + puntosDeCompra +
				", numProductos=" + numProductos  +
				"]";

		return resp;

	}



}
