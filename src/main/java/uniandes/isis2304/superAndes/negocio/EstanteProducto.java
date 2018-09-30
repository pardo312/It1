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
public class EstanteProducto implements VOEstanteProducto
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/


	private long IDEstante;
	
	private String codigoDeBarrasProducto;


	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public EstanteProducto() 
    {
    	this.IDEstante = 0;
		this.codigoDeBarrasProducto = "";
	}


    public EstanteProducto( long IDEstante, String codigoDeBarrasProducto)
	 
    {
    	this.IDEstante =IDEstante;
		this.codigoDeBarrasProducto = codigoDeBarrasProducto;
	}

   
    public long getIDEstante() 
	{
		return IDEstante;
	}

	public void setIDEstante(long IDEstante) 
	{
		this.IDEstante = IDEstante;
	}
	
	public String getCodigoDeBarrasProducto() 
	{
		return codigoDeBarrasProducto;
	}

	public void setCodigoDeBarrasProducto(String codigoDeBarrasProducto) 
	{
		this.codigoDeBarrasProducto = codigoDeBarrasProducto;
	}
		
	@Override
	public String toString() 
	{
		String resp = "EstanteProducto [IDEstante=" + IDEstante + ", codigoDeBarrasProducto=" + codigoDeBarrasProducto + "]";
		return resp;	
	}
	

}
