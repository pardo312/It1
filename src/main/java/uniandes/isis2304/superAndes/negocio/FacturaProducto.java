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
public class FacturaProducto implements VOFacturaProducto
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/


	private String numeroFactura;
	
	private String codigoDeBarrasProducto;


	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public FacturaProducto() 
    {
    	this.numeroFactura = "";
		this.codigoDeBarrasProducto = "";
	}


    public FacturaProducto( String numeroFactura, String codigoDeBarrasProducto)
	 
    {
    	this.numeroFactura =numeroFactura;
		this.codigoDeBarrasProducto = codigoDeBarrasProducto;
	}

   
    @Override
	public String getNumeroFactura() 
	{
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) 
	{
		this.numeroFactura = numeroFactura;
	}
	
	@Override
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
		String resp = "FacturaProducto [numeroFactura=" + numeroFactura + ", codigoDeBarrasProducto=" + codigoDeBarrasProducto + "]";

		return resp;
		
	}
	

}
