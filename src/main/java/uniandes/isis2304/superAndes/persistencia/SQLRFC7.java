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
import uniandes.isis2304.superAndes.negocio.Consulta7;
import uniandes.isis2304.superAndes.negocio.Producto;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.TipoProducto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBIDA de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author da.leon
 */
class SQLRFC7 
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
	public SQLRFC7 (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	//TODO
	public List<Consulta7> consulta7(PersistenceManager pm,String unidadTiempo, String tipoProducto )
	{
		if (unidadTiempo.equalsIgnoreCase("semana"))
		{
			String o = "SELECT CATE.NOMBRECATEGORIA , FAC.FECHA AS FECHAMAYORDEMANDA , SUM(PRODU.PRECIOUNITARIO) AS INGRESOS , FAC.FECHA AS FECHAMENORDEMANDA\n" + 
					"FROM CATEGORIA CATE, FACTURA FAC, PRODUCTO PRODU, FACTURAPRODUCTO FACPRO\n" + 
					"WHERE CATE.CODIGODEBARRASPRODUCTO = PRODU.CODIGODEBARRAS AND FAC.NUMEROFACTURA = FACPRO.NUMEROFACTURA AND FACPRO.CODIGODEBARRASPRODUCTO = PRODU.CODIGODEBARRAS \n" + 
					"AND FAC.FECHA BETWEEN TO_DATE('2016-01-01', 'YYYY-MM-DD') and CURRENT_TIMESTAMP AND CATE.NOMBRECATEGORIA = '"+tipoProducto+"'\n" + 
					"group by CATE.NOMBRECATEGORIA, FAC.FECHA, FAC.FECHA\n" + 
					"ORDER BY INGRESOS DESC"  ;
			Query q = pm.newQuery(SQL,o );
			q.setResultClass(Producto.class);
			List<Consulta7> w = (List<Consulta7>) q.executeList();
			return w;




		}
		else if (unidadTiempo.equalsIgnoreCase("mes")){

			String o = "SELECT CATE.NOMBRECATEGORIA , FAC.FECHA AS FECHAMAYORDEMANDA , SUM(PRODU.PRECIOUNITARIO) AS INGRESOS , FAC.FECHA AS FECHAMENORDEMANDA\n" + 
					"FROM CATEGORIA CATE, FACTURA FAC, PRODUCTO PRODU, FACTURAPRODUCTO FACPRO\n" + 
					"WHERE CATE.CODIGODEBARRASPRODUCTO = PRODU.CODIGODEBARRAS AND FAC.NUMEROFACTURA = FACPRO.NUMEROFACTURA AND FACPRO.CODIGODEBARRASPRODUCTO = PRODU.CODIGODEBARRAS \n" + 
					"AND FAC.FECHA BETWEEN TO_DATE('2016-01-01', 'YYYY-MM-DD') and CURRENT_TIMESTAMP AND CATE.NOMBRECATEGORIA = '"+tipoProducto+"'\n" + 
					"group by CATE.NOMBRECATEGORIA, FAC.FECHA, FAC.FECHA\n" + 
					"ORDER BY INGRESOS DESC"  ;
			Query q = pm.newQuery(SQL,o );
			q.setResultClass(Producto.class);
			List<Consulta7> w = (List<Consulta7>) q.executeList();
			return w;


		}
		else {
			String o = "SELECT CATE.NOMBRECATEGORIA , FAC.FECHA AS FECHAMAYORDEMANDA , SUM(PRODU.PRECIOUNITARIO) AS INGRESOS , FAC.FECHA AS FECHAMENORDEMANDA\n" + 
					"FROM CATEGORIA CATE, FACTURA FAC, PRODUCTO PRODU, FACTURAPRODUCTO FACPRO\n" + 
					"WHERE CATE.CODIGODEBARRASPRODUCTO = PRODU.CODIGODEBARRAS AND FAC.NUMEROFACTURA = FACPRO.NUMEROFACTURA AND FACPRO.CODIGODEBARRASPRODUCTO = PRODU.CODIGODEBARRAS \n" + 
					"AND FAC.FECHA BETWEEN TO_DATE('2016-01-01', 'YYYY-MM-DD') and CURRENT_TIMESTAMP AND CATE.NOMBRECATEGORIA = '"+tipoProducto+"'\n" + 
					"group by CATE.NOMBRECATEGORIA, FAC.FECHA, FAC.FECHA\n" + 
					"ORDER BY INGRESOS DESC"  ;
			Query q = pm.newQuery(SQL,o );
			q.setResultClass(Producto.class);
			List<Consulta7> w = (List<Consulta7>) q.executeList();
			return w;


		}


	}

}
