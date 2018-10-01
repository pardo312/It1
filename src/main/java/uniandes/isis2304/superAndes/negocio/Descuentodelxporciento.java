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




/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 */
public class Descuentodelxporciento implements VODescuentodelxporciento
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	
	private long id;
	
	private float porcentaje;
	
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public Descuentodelxporciento() 
    {
    	this.id = 0;
		this.porcentaje = 0;
	
	}


    public Descuentodelxporciento( long id, int porcentaje)
	 
    {
    	this.id = id;
    	this.porcentaje = porcentaje;
	}

   
	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}
	

	public float getPorcentaje() 
	{
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) 
	{
		this.porcentaje = porcentaje;
	}
	
	@Override
	public String toString() 
	{
		String resp = "Descuentodelxporciento [id=" + id + ", porcentaje=" + porcentaje + 
				"]";

		return resp;
		
	}
	

}
