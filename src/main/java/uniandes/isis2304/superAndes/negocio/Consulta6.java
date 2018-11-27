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
public class Consulta6 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/



	private int idCliente;

	private int venta;


	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	public Consulta6() 
	{
		this.idCliente = 0;
		this.venta = 0;
	}




	public Consulta6(int idCliente, int venta) {
		this.idCliente = idCliente;
		this.venta = venta;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	

	public int getVenta() {
		return venta;
	}

	public void setVenta(int venta) {
		this.venta = venta;
	}


	@Override
	public String toString() 
	{
		String resp = "Consulta6 [idCliente=" + idCliente + ", venta=" + venta +	"]";

		return resp;

	}



}
