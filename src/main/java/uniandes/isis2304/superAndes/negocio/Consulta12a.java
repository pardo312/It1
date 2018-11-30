/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universproductoad	de	los	Andes	(Bogotá	- Colombia)
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
public class Consulta12a 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/


	private String semana;
	
	private String producto;

	private int ventas;


	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	public Consulta12a() 
	{
		this.semana = "";
		this.producto = "";
		this.ventas = 0;
	}

	public Consulta12a(String producto, int ventas,String semana) {
		this.semana = semana;
		this.producto = producto;
		this.ventas = ventas;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public int getVentas() {
		return ventas;
	}

	public void setVentas(int ventas) {
		this.ventas = ventas;
	}
	public String getSemana() {
		return semana;
	}

	public void setSemana(String semana) {
		this.semana = semana;
	}





	@Override
	public String toString() 
	{
		String resp = "Consulta12 [semana ="+semana +"producto=" + producto +
				", ventas=" + ventas +
				"]";

		return resp;

	}



}
