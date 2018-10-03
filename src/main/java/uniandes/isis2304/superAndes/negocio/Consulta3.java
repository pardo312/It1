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
public class Consulta3 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/



	private long id;

	private String nombre;

	private float ocu_estante;

	private float ocu_bodega;

	private String direccion;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	public Consulta3() 
	{
		this.id = 0;
		this.nombre = "";
		this.ocu_estante = 0;
		this.ocu_bodega = 0;
		this.direccion = "";
	}




	public Consulta3(long id, String nombre, float ocu_estante, float ocu_bodega, String direccion) {
		this.id = id;
		this.nombre = nombre;
		this.ocu_estante = ocu_estante;
		this.ocu_bodega = ocu_bodega;
		this.direccion = direccion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getOcu_estante() {
		return ocu_estante;
	}

	public void setOcu_estante(float ocu_estante) {
		this.ocu_estante = ocu_estante;
	}

	public float getOcu_bodega() {
		return ocu_bodega;
	}

	public void setOcu_bodega(float ocu_bodega) {
		this.ocu_bodega = ocu_bodega;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	@Override
	public String toString() 
	{
		String resp = "Consulta3 [id=" + id + ", nombre=" + nombre + 
				", ocu_estante=" + ocu_estante +
				", ocu_bodega=" + ocu_bodega +
				", direccion=" + direccion +
				"]";

		return resp;

	}



}
