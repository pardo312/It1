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

import java.util.List; 

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.Consulta1;
import uniandes.isis2304.superAndes.negocio.Consulta2;
import uniandes.isis2304.superAndes.negocio.Consulta5;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.TipoProducto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBIDA de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author da.leon
 */
class SQLRFC5 
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
	public SQLRFC5 (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public List<Consulta5> consulta5(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL,"SELECT PROVE.NIT ,PROVE.NOMBRE, PROD.CODIGODEBARRAS AS CODIGO_PRODUCTO, SUM(PROD.PRECIOUNITARIO) AS DINERO_PROVEEDOR FROM PROVEEDOR PROVE, SUCURSALPROVEEDOR SUCUPRO, PRODUCTO PROD WHERE PROVE.NIT = SUCUPRO.NITPROVEEDOR AND PROD.IDSUCURSAL = SUCUPRO.IDSUCURSAL group by PROVE.NOMBRE, PROVE.NIT, PROD.CODIGODEBARRAS ORDER BY DINERO_PROVEEDOR DESC");
		q.setResultClass(Consulta5.class);
		List<Consulta5> w = (List<Consulta5>) q.executeList();
		return w;
	}
 
}