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
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.TipoProducto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBIDA de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLRFC1 
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
	public SQLRFC1 (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}



	public List<Consulta1> consulta1(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL,"SELECT nombre,DineroRecolectado FROM (SELECT SUCU.NOMBRE ,sum(produ.PRECIOUNITARIO) AS DINERORECOLECTADO from producto produ, factura fac, FACTURAPRODUCTO facpro, SUCURSAL SUCU where produ.codigodebarras = facpro.CODIGODEBARRASPRODUCTO and fac.NUMEROFACTURA = facpro.NUMEROFACTURA  AND  (fac.FECHA between TO_DATE('2017-06-05', 'YYYY-MM-DD')  AND TO_DATE('2020-06-05', 'YYYY-MM-DD') OR fac.FECHA between TO_DATE('2018-01-01', 'YYYY-MM-DD') and CURRENT_TIMESTAMP ) AND produ.IDSUCURSAL = SUCU.ID group by SUCU.NOMBRE ORDER BY DINERORECOLECTADO DESC , SUCU.NOMBRE ASC) ");		
		List<Consulta1> w = (List<Consulta1>) q.executeResultList(Consulta1.class);
		return w;
	}

}
