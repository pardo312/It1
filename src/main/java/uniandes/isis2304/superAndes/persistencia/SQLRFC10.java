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
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.TipoProducto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBIDA de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLRFC10 
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
	public SQLRFC10 (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	
	public List<Consulta10a> consulta10a(PersistenceManager pm,String fechaInicial, String fechaFinal,String idcliente,String ptoscmpra,String productosComprados)throws ParseException
	{
		java.util.Date  fecha1 =  (java.util.Date ) new SimpleDateFormat("dd/MM/yyyy").parse(fechaInicial);
		java.util.Date  fecha2 =  (java.util.Date ) new SimpleDateFormat("dd/MM/yyyy").parse(fechaFinal);
		
		java.sql.Date fechaSQL1 = convertUtilToSql(fecha1);
		java.sql.Date fechaSQL2 = convertUtilToSql(fecha2);

		
		Query q = pm.newQuery(SQL, "Select idcliente, puntosdecompra, count(codigodebarras) as productoscomprados from "
				+ "(Select c.idcliente,c.puntosdecompra, c.nitcliente, c.cedulacliente, f.numerofactura as numFac  from cliente c , factura f "
				+ "where c.idcliente = f.idcliente AND f.FECHA between TO_DATE('"+fechaSQL1+"', 'YYYY-MM-DD') and TO_DATE('"+fechaSQL2+"', 'YYYY-MM-DD')Order by c.idcliente)t1 , "
				+ "(select f.numerofactura , p.codigodebarras from factura f ,facturaproducto fp ,producto p "
				+ "where  f.numerofactura = fp.numerofactura AND  p.codigodebarras = fp.codigodebarrasproducto)t2 where numfac = numerofactura group by idcliente, puntosdecompra " + ordenamiento10A(idcliente, ptoscmpra, productosComprados));
		q.setResultClass(Consulta10a.class);
		List<Consulta10a> w = (List<Consulta10a>) q.executeList();
		return w;
	}
	public List<Consulta10b> consulta10b(PersistenceManager pm,String fechaInicial, String fechaFinal,String fecha,String productosComprados)throws ParseException
	{
		java.util.Date  fecha1 =  (java.util.Date ) new SimpleDateFormat("dd/MM/yyyy").parse(fechaInicial);
		java.util.Date  fecha2 =  (java.util.Date ) new SimpleDateFormat("dd/MM/yyyy").parse(fechaFinal);
		
		java.sql.Date fechaSQL1 = convertUtilToSql(fecha1);
		java.sql.Date fechaSQL2 = convertUtilToSql(fecha2);

		
		Query q = pm.newQuery(SQL, " Select fecha, count(codigodebarras) as productoscomprados "
				+ "from (Select f.numeroFactura as numfac, f.fecha , count(c.idcliente) as nmoclientes"
				+ " from cliente c , factura f where c.idcliente = f.idcliente AND f.FECHA between TO_DATE('"+fechaSQL1+"', 'YYYY-MM-DD') and TO_DATE('"+fechaSQL2+"', 'YYYY-MM-DD') "
				+ "Group by f.fecha, f.numeroFactura)t1 , "
				+ "(select f.numerofactura , p.codigodebarras from factura f ,facturaproducto fp ,producto p "
				+ "where  f.numerofactura = fp.numerofactura AND p.codigodebarras = fp.codigodebarrasproducto)t2 "
				+ "where numfac = numerofactura Group by fecha " + ordenamiento10B(fecha, productosComprados));
		q.setResultClass(Consulta10b.class);
		List<Consulta10b> w = (List<Consulta10b>) q.executeList();
		return w;
	}
	private static java.sql.Date convertUtilToSql(java.util.Date fecha) {

		java.sql.Date sDate = new java.sql.Date(fecha.getTime());

		return sDate;

	}
	
	public String ordenamiento10A(String idcliente,String ptoscmpra,String productosComprados){
		
		String p= "";
		
		if(idcliente.contains("si")  || ptoscmpra.contains("si") ||productosComprados.contains("si") ){
			p = " Order by ";
			if(idcliente.contains("si") ){
				if(ptoscmpra.contains("si")  || productosComprados.contains("si"))
				{
					if(idcliente.contains("ASC")){
						p += " idcliente ASC ,";
					}
					else if(idcliente.contains("DESC")){
						p += " idcliente DESC,";
					}
					
				}
				else
				{
					if(idcliente.contains("ASC")){
						p += " idcliente ASC";
					}
					else if(idcliente.contains("DESC")){
						p += " idcliente DESC";
					}
				}
				
			}
			if(ptoscmpra.contains("si")){
				if(productosComprados.contains("si"))
				{
					if(ptoscmpra.contains("ASC")){
						p += " puntosdecompra ASC,";
					}
					else if(ptoscmpra.contains("DESC")){
						p += " puntosdecompra DESC,";
					}
					
				}
				else
				{
					if(ptoscmpra.contains("ASC")){
						p += " puntosdecompra ASC";
					}
					else if(ptoscmpra.contains("DESC")){
						p += " puntosdecompra DESC";
					}
				}
				
			}
			if(productosComprados.contains("si")){
				if(productosComprados.contains("ASC")){
					p += " productoscomprados ASC";
				}
				else if(productosComprados.contains("DESC")){
					p += " productoscomprados DESC";
				}
			}
		}
			
		return p;
	}
public String ordenamiento10B(String fecha,String productosComprados){
		
		String p= "";
		
		if(fecha.contains("si")   ||productosComprados.contains("si") ){
			p = " Order by ";
			if(fecha.contains("si") ){
				if(productosComprados.contains("si"))
				{
					if(fecha.contains("ASC")){
						p += " fecha ASC ,";
					}
					else if(fecha.contains("DESC")){
						p += " fecha DESC,";
					}
					
				}
				else{

					if(productosComprados.contains("ASC")){
						p += " fecha ASC";
					}
					else if(productosComprados.contains("DESC")){
						p += " fecha DESC";
					}
				}
			}
		
			if(productosComprados.contains("si"))
				{
					
					if(productosComprados.contains("ASC")){
						p += " productoscomprados ASC";
					}
					else if(productosComprados.contains("DESC")){
						p += " productoscomprados DESC";
					}
				}
		}
		return p;
		}
}
