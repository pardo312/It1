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
public class EstanteTipoProducto implements VOEstanteTipoProducto
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/


	private long IDEstante;
	
	private String nombreTipo;


	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public EstanteTipoProducto() 
    {
    	this.IDEstante = 0;
		this.nombreTipo = "";
	}


    public EstanteTipoProducto( long IDBodega, String nombreTipo)
	 
    {
    	this.IDEstante =IDBodega;
		this.nombreTipo = nombreTipo;
	}

   
    public long getIDEstante() 
	{
		return IDEstante;
	}

	public void setIDEstante(long IDEstante) 
	{
		this.IDEstante = IDEstante;
	}
	
	public String getNombreTipo() 
	{
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) 
	{
		this.nombreTipo = nombreTipo;
	}
		
	@Override
	public String toString() 
	{
		String resp = "EstanteTipoProducto [IDEstante=" + IDEstante + ", nombreTipo=" + nombreTipo + "]";

		return resp;
		
	}
	

}
