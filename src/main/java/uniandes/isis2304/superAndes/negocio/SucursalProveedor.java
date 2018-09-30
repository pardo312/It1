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


public class SucursalProveedor implements VOSucursalProveedor
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	

	private long IDSucursal;
	
	private String NITProveedor;


	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public SucursalProveedor() 
    {
    	this.IDSucursal = 0;
		this.NITProveedor = "";
	}


    public SucursalProveedor( long IDSucursal, String NITProveedor)
	 
    {
    	this.IDSucursal =IDSucursal;
		this.NITProveedor = NITProveedor;
	}

   
    public long getIDSucursal() 
	{
		return IDSucursal;
	}

	public void setIDSucursal(long IDSucursal) 
	{
		this.IDSucursal = IDSucursal;
	}
	
	public String getNITProveedor() 
	{
		return NITProveedor;
	}

	public void setNITProveedor(String NITProveedor) 
	{
		this.NITProveedor = NITProveedor;
	}
		
	@Override
	public String toString() 
	{
		String resp = "SucursalProducto [IDSucursal=" + IDSucursal + ", NITProveedor=" + NITProveedor + "]";
		return resp;	
	}
	

}
