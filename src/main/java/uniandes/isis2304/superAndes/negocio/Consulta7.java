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
import java.util.Date;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.output.IndentingUTF8XmlOutput;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 */
public class Consulta7 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/




	private String nombreCategoria;

	private Date fechaMayorDemanda;
	
	private Date fechaMenorDemanda;
	
	private float ingresos;
	
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	public Consulta7() 
	{
		this.nombreCategoria = "";
		this.fechaMayorDemanda = new Date();
		this.fechaMenorDemanda = new Date();
		this.ingresos = 0;
	}




	public Consulta7(String nombreCategoria, Date fechaMayorDemanda, Date fechaMenorDemanda, float ingresos) {
		this.nombreCategoria = nombreCategoria;
		this.fechaMayorDemanda = fechaMayorDemanda;
		this.fechaMenorDemanda = fechaMenorDemanda;
		this.ingresos = ingresos;
	
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public Date getFechaMayorDemanda() {
		return fechaMayorDemanda;
	}

	public void setFechaMayorDemanda(Date fechaMayorDemanda) {
		this.fechaMayorDemanda = fechaMayorDemanda;
	}

	public Date getFechaMenorDemanda() {
		return fechaMenorDemanda;
	}

	public void setfechaMenorDemanda(Date fecha) {
		this.fechaMenorDemanda = fecha;
	}

	
	@Override
	public String toString() 
	{
		String resp = "Consulta7 [nombreCategoria=" + nombreCategoria + ", fechaMayorDemanda=" + fechaMayorDemanda + ", ingresos=" + ingresos +", fechaMenorDemanda=" + fechaMenorDemanda +"]";

		return resp;

	}



}
