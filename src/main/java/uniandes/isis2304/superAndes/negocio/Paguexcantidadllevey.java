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
public class Paguexcantidadllevey implements VOPaguexunidadesllevey
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	
	private long id;
	
	private int x;

	private int y;
	
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public Paguexcantidadllevey() 
    {
    	this.id = 0;
		this.x = 0;
		this.y = 0;


	}


    public Paguexcantidadllevey( long id, int x,int y)
	 
    {
    	this.id = id;
    	this.x = x;
		this.y = y;

	}

   
	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}
	

	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}
	
	
	public int getY() 
	{
		return y;
	}
	
	public void setY(int y) 
	{
		this.y = y;
	}
	
	

	
	@Override
	public String toString() 
	{
		String resp = "Paguexcantidadllevey [id=" + id + ", x=" + x + ", y=" + y + 
				"]";

		return resp;
		
	}
	

}
