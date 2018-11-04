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
import uniandes.isis2304.superAndes.negocio.Consulta3;
import uniandes.isis2304.superAndes.negocio.Consulta8;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.TipoProducto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBIDA de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author da.leon
 */
class SQLRFC8 
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
	public SQLRFC8 (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public List<Consulta8> consulta8(PersistenceManager pm, String idSucursal)
	{
		Query q = pm.newQuery(SQL,"SELECT CLIE.IDCLIENTE , CLIE.PUNTOSDECOMPRA, CLIE.CEDULACLIENTE, CLIE.NITCLIENTE\n" + 
				"FROM CLIENTE CLIE, FACTURA FAC, SUCURSAL SUCU, FACTURAPRODUCTO FACPRO, PRODUCTO PROD\n" + 
				"WHERE  PROD.CODIGODEBARRAS = FACPRO.CODIGODEBARRASPRODUCTO AND FAC.NUMEROFACTURA = FACPRO.NUMEROFACTURA\n" + 
				"AND FAC.FECHA BETWEEN TO_DATE('2016-01-01', 'YYYY-MM-DD') and CURRENT_TIMESTAMP AND FAC.IDCLIENTE = CLIE.IDCLIENTE AND SUCU.ID = PROD.IDSUCURSAL AND SUCU.ID = "+idSucursal+"\n" + 
				"GROUP BY CLIE.IDCLIENTE, CLIE.PUNTOSDECOMPRA, CLIE.CEDULACLIENTE, CLIE.NITCLIENTE\n" + 
				"HAVING COUNT(FAC.FECHA)>2\n" + 
				"ORDER BY CLIE.IDCLIENTE\n" + 
				"");
		q.setResultClass(Consulta8.class);
		List<Consulta8> w = (List<Consulta8>) q.executeList();
		return w;
	}
 
}
