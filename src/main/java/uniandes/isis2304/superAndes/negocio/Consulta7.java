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

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 */
public class Consulta7 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/



	private long id;

	private String nombreSucursal;

	private Date fecha;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	public Consulta7() 
	{
		this.id = 0;
		this.nombreSucursal = "";
		this.fecha = new Date();
	}




	public Consulta7(long id, String nombreSucursal, Date fecha) {
		this.id = id;
		this.nombreSucursal = nombreSucursal;
		this.fecha = fecha;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getnombreSucursal() {
		return nombreSucursal;
	}

	public void setnombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public Date getfecha() {
		return fecha;
	}

	public void setfecha(Date fecha) {
		this.fecha = fecha;
	}

	
	@Override
	public String toString() 
	{
		String resp = "Consulta7 [id=" + id + ", nombreSucursal=" + nombreSucursal + ", fecha=" + fecha +"]";

		return resp;

	}



}
