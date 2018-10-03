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
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.TipoProducto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBIDA de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLRFC2 
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
	public SQLRFC2 (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public List<Consulta2> consulta2(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL,"SELECT* FROM(SELECT PROMO.ID, COUNT(PROMO.ID) AS CANTIDADVECESUSADA, PROMO.DESCRIPCION, FAC.FECHA as FECHA_VENTA FROM PROMOCION PROMO, PRODUCTO PRODU, FACTURA FAC, FACTURAPRODUCTO FACPRO WHERE PRODU.IDPROMOCION = PROMO.ID AND FAC.NUMEROFACTURA = FACPRO.NUMEROFACTURA AND FACPRO.CODIGODEBARRASPRODUCTO = PRODU.CODIGODEBARRAS GROUP BY PROMO.ID, PROMO.DESCRIPCION, FAC.FECHA ORDER BY FAC.FECHA asc, COUNT(PROMO.ID)desc ) WHERE ROWNUM <21 ");
		q.setResultClass(Consulta2.class);
		List<Consulta2> w = (List<Consulta2>) q.executeList();
		return w;
	}
 
}
