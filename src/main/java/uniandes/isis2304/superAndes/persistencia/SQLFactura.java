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

package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.Factura;
import uniandes.isis2304.superAndes.negocio.Producto;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.TipoProducto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBIDA de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLFactura 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLFactura (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarFactura(PersistenceManager pm,String numeroDeFactura, java.util.Date fecha, long idCliente) 
	{
		
		java.sql.Date fecha1 = convertUtilToSql(fecha);
        Query q = pm.newQuery(SQL, "INSERT INTO " + "Factura" + "(numeroFactura,fecha,idCliente) values ('"+numeroDeFactura+"',TO_DATE('"+fecha1+"', 'YYYY/MM/DD'),'"+ idCliente+"')");
       
        return (long) q.executeUnique();            
	}


	private static java.sql.Date convertUtilToSql(java.util.Date fecha) {

		java.sql.Date sDate = new java.sql.Date(fecha.getTime());

		return sDate;

	}


	public long adicionarFacturaProd(PersistenceManager pm, String numeroDeFactura, String codigoDeBarras) {
		
        Query q = pm.newQuery(SQL, "INSERT INTO " + "FacturaProducto" + "(numeroFactura,codigoDeBarrasProducto) values ('"+numeroDeFactura+"','"+codigoDeBarras+"')");
       
        return (long) q.executeUnique();     
	}
	
	public List<Factura> darFacturas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + "FACTURA");
		q.setResultClass(Factura.class);		
		return  (List<Factura>)q.executeList();
		
	}
	
	public List<Factura> darFactura (PersistenceManager pm, String Factura)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + "FACTURA WHERE NUMEROFACTURA = " + Factura);
		q.setResultClass(Factura.class);
		List<Factura> w = (List<Factura>) q.executeList();
		return w;
	}
	public long eliminarFactura(PersistenceManager pm, String Factura) {
		 Query q = pm.newQuery(SQL, "DELETE FROM " + "FACTURA "+ "WHERE NUMEROFACTURA = "+Factura);
	        return (long) q.executeUnique();  
	}
	
	
	

}
