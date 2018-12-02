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
public class Consulta13 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/



	

	private String NITCliente;

	private int puntosDeCompra;

	private int cedulaCliente;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	public Consulta13() 
	{
		
		this.NITCliente = "";
		this.puntosDeCompra = 0;
		this.cedulaCliente = 0;
	}




	public Consulta13( String NITCliente, int puntosDeCompra, int cedulaCliente) {
		
		this.NITCliente = NITCliente;
		this.puntosDeCompra = puntosDeCompra;
		this.cedulaCliente = cedulaCliente;
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

	public int getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(int cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}




	@Override
	public String toString() 
	{
		String resp = "Consulta13 [NITCliente=" + NITCliente + 
				", puntosDeCompra=" + puntosDeCompra +
				", cedulaCliente=" + cedulaCliente  +
				"]";

		return resp;

	}



}
