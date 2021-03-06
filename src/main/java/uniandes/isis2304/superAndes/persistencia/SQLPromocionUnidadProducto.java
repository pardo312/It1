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

import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Categoria;
import uniandes.isis2304.superAndes.negocio.Proveedor;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLPromocionUnidadProducto 
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
	public SQLPromocionUnidadProducto (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	public long registrarPromocionPXCLY(PersistenceManager pm, long id, int x, int y) 
	{
		Query s = pm.newQuery(SQL,"INSERT INTO " + "PROMOCION" + "( id,descripcion, preciopromocion,IDSucursal) values ("+id+","+"'PromocionPXULY'"+","+"'0'"+","+"1"+")"         );
		s.executeUnique();
		Query q = pm.newQuery(SQL,  "INSERT INTO " + "PAGUEXCANTIDADLLEVEY" + "( id,x,y) values ("+id+","+x+","+y+")");                          
		
		   return (long) q.executeUnique();
	}
	
	public long registrarPromocionPXULY(PersistenceManager pm, long id, int x, int y) 
	{
		Query s = pm.newQuery(SQL,"INSERT INTO " + "PROMOCION" + "( id,descripcion, preciopromocion,IDSucursal) values ("+id+","+"'PromocionPXULY'"+","+"'0'"+","+"1"+")"         );
		 Query q = pm.newQuery(SQL, "INSERT INTO " + "PAGUEXUNIDADESLLEVEY" + "( id,x,y) values ("+id+","+x+","+y+")");                          
		 s.executeUnique();
		   return (long) q.executeUnique();
	}

	
	
}
