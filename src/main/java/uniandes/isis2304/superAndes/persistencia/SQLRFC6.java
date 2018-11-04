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
import uniandes.isis2304.superAndes.negocio.Consulta2;
import uniandes.isis2304.superAndes.negocio.Consulta3;
import uniandes.isis2304.superAndes.negocio.Consulta6;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.TipoProducto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBIDA de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author da.leon
 */
class SQLRFC6 
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
	public SQLRFC6 (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public List<Consulta6> consulta6(PersistenceManager pm, String fechaInicial, String fechaFinal) throws ParseException
	{
		java.util.Date  fecha1 =  (java.util.Date ) new SimpleDateFormat("dd/MM/yyyy").parse(fechaInicial);
		java.util.Date  fecha2 =  (java.util.Date ) new SimpleDateFormat("dd/MM/yyyy").parse(fechaFinal);
		
		java.sql.Date fechaSQL1 = convertUtilToSql(fecha1);
		java.sql.Date fechaSQL2 = convertUtilToSql(fecha2);

		
		Query q = pm.newQuery(SQL,"SELECT CLIE.IDCLIENTE, SUM(PRO.PRECIOUNITARIO) AS VENTA\n" + 
				"FROM CLIENTE CLIE, FACTURA FAC, FACTURAPRODUCTO FACPRO, PRODUCTO PRO\n" + 
				"WHERE FAC.IDCLIENTE = CLIE.IDCLIENTE AND FACPRO.CODIGODEBARRASPRODUCTO = PRO.CODIGODEBARRAS AND fac.FECHA between TO_DATE('"+fechaSQL1+"', 'YYYY-MM-DD') and TO_DATE('"+fechaSQL2+"', 'YYYY-MM-DD') \n" + 
				"group by CLIE.IDCLIENTE\n" + 
				"ORDER BY VENTA DESC");
		q.setResultClass(Consulta6.class);
		List<Consulta6> w = (List<Consulta6>) q.executeList();
		return w;
	}
	
	private static java.sql.Date convertUtilToSql(java.util.Date fecha) {

		java.sql.Date sDate = new java.sql.Date(fecha.getTime());

		return sDate;

	}
 
}
