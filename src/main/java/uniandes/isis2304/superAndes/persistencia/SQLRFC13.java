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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.Consulta1;
import uniandes.isis2304.superAndes.negocio.Consulta10a;
import uniandes.isis2304.superAndes.negocio.Consulta10b;
import uniandes.isis2304.superAndes.negocio.Consulta12a;
import uniandes.isis2304.superAndes.negocio.Consulta12b;
import uniandes.isis2304.superAndes.negocio.Consulta13;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.TipoProducto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBIDA de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLRFC13 
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
	public SQLRFC13 (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	// asiste una vez al mes
	public List<Consulta13> consulta13a(PersistenceManager pm)throws ParseException
	{
	
		Query q = pm.newQuery(SQL, "SELECT  CLIE.PUNTOSDECOMPRA, CLIE.CEDULACLIENTE, CLIE.NITCLIENTE\n" + 
				"				FROM CLIENTE CLIE, FACTURA FAC, FACTURAPRODUCTO FACPRO, PRODUCTO PROD\n" + 
				"				WHERE  PROD.CODIGODEBARRAS = FACPRO.CODIGODEBARRASPRODUCTO AND FAC.NUMEROFACTURA = FACPRO.NUMEROFACTURA\n" + 
				"				AND FAC.FECHA BETWEEN TO_DATE('2000-01-01', 'YYYY-MM-DD') and CURRENT_TIMESTAMP AND FAC.IDCLIENTE = CLIE.IDCLIENTE \n" + 
				"				GROUP BY CLIE.IDCLIENTE, CLIE.PUNTOSDECOMPRA, CLIE.CEDULACLIENTE, CLIE.NITCLIENTE\n" + 
				"				HAVING COUNT(FAC.FECHA)>48\n" + 
				"				ORDER BY COUNT(FAC.FECHA),CLIE.IDCLIENTE");
		q.setResultClass(Consulta13.class);
		List<Consulta13> w = (List<Consulta13>) q.executeList();
		return w;
	}
	
	// compra tecnologia 
	public List<Consulta13> consulta13b(PersistenceManager pm)throws ParseException
	{
		
		
		Query q = pm.newQuery(SQL, "SELECT  CLIE.PUNTOSDECOMPRA, CLIE.CEDULACLIENTE, CLIE.NITCLIENTE\n" + 
				"				FROM CLIENTE CLIE, FACTURA FAC, FACTURAPRODUCTO FACPRO, PRODUCTO PROD, categoria c\n" + 
				"				WHERE  PROD.CODIGODEBARRAS = FACPRO.CODIGODEBARRASPRODUCTO AND FAC.NUMEROFACTURA = FACPRO.NUMEROFACTURA\n" + 
				"                AND FAC.IDCLIENTE = CLIE.IDCLIENTE and c.CODIGODEBARRASPRODUCTO = PROD.CODIGODEBARRAS and c.NOMBRECATEGORIA = 'TECNOLOGIA'\n" + 
				"                GROUP BY CLIE.IDCLIENTE, CLIE.PUNTOSDECOMPRA, CLIE.CEDULACLIENTE, CLIE.NITCLIENTE\n" + 
				"				\n" + 
				"				ORDER BY COUNT(FAC.FECHA),CLIE.IDCLIENTE");
		q.setResultClass(Consulta13.class);
		List<Consulta13> w = (List<Consulta13>) q.executeList();
		return w;
	}
	// compra costoso
	public List<Consulta13> consulta13c(PersistenceManager pm)throws ParseException
	{
		
		Query q = pm.newQuery(SQL, "SELECT  CLIE.PUNTOSDECOMPRA, CLIE.CEDULACLIENTE, CLIE.NITCLIENTE\n" + 
				"				FROM CLIENTE CLIE, FACTURA FAC, FACTURAPRODUCTO FACPRO, PRODUCTO PROD, categoria c\n" + 
				"				WHERE  PROD.CODIGODEBARRAS = FACPRO.CODIGODEBARRASPRODUCTO AND FAC.NUMEROFACTURA = FACPRO.NUMEROFACTURA\n" + 
				"                AND FAC.IDCLIENTE = CLIE.IDCLIENTE \n" + 
				"                GROUP BY CLIE.IDCLIENTE, CLIE.PUNTOSDECOMPRA, CLIE.CEDULACLIENTE, CLIE.NITCLIENTE, PROD.PRECIOUNITARIO\n" + 
				"                HAVING PROD.PRECIOUNITARIO > 100000\n" + 
				"				ORDER BY COUNT(FAC.FECHA),CLIE.IDCLIENTE");
		q.setResultClass(Consulta13.class);
		List<Consulta13> w = (List<Consulta13>) q.executeList();
		return w;
	}
	
	
	
	
}
