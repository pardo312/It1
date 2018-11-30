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
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.TipoProducto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBIDA de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLRFC12 
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
	public SQLRFC12 (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	
	public List<Consulta12a> consulta12a(PersistenceManager pm, String masVendido,int i)throws ParseException
	{
	
		String p = "";
		if(masVendido.contains("mas")){
			p = "MAX";
		}
		if(masVendido.contains("menos")){
			p = "MIN";
		}
		
		Query q = pm.newQuery(SQL, "Select PRODUCTO, Ventas From(select "+p+"(numeroDeVentas) as NMOMAX2 "
				+ "from(select codigodebarras as Producto, TO_CHAR(fecha - 7/24,'IW') as semana, Count(fp.numerofactura) as "
				+ "NumeroDeVentas from   factura f ,facturaproducto fp ,producto p  WHERE  f.numerofactura = fp.numerofactura"
				+ " AND p.codigodebarras = fp.codigodebarrasproducto AND TO_CHAR(fecha - 7/24,'IW') = "+ i
				+ " GROUP BY TO_CHAR(fecha - 7/24,'IW'), codigodebarras))t1, (select producto as producto, numeroDeVentas as "
				+ "Ventas from(select codigodebarras as Producto, TO_CHAR(fecha - 7/24,'IW') as semana, Count(fp.numerofactura) "
				+ "as NumeroDeVentas from   factura f ,facturaproducto fp ,producto p WHERE  f.numerofactura = fp.numerofactura "
				+ "AND p.codigodebarras = fp.codigodebarrasproducto AND TO_CHAR(fecha - 7/24,'IW') = " + i
				+ "GROUP BY TO_CHAR(fecha - 7/24,'IW'), codigodebarras) )t2 WHERE Ventas = NMOMAX2");
		q.setResultClass(Consulta12a.class);
		List<Consulta12a> w = (List<Consulta12a>) q.executeList();
		return w;
	}
	public List<Consulta12b> consulta12b(PersistenceManager pm,String masSolicitado,int i)throws ParseException
	{
		String p = "";
		if(masSolicitado.contains("mas")){
			p = "MAX";
		}
		if(masSolicitado.contains("menos")){
			p = "MIN";
		}
		
		Query q = pm.newQuery(SQL, "Select proveedor, NumeroDeSolicitudes from"
				+ "((select  NIT as Proveedor, TO_CHAR(fechaentrega - 7/24,'IW') as semana,  count(ID) as "
				+ "NumeroDeSolicitudes from Pedido ped ,proveedor prov  WHERE ped.NITPROVEEDOR = PROV.NIT "
				+ "AND TO_CHAR(fechaentrega - 7/24,'IW') = "+i+" group by NIT, TO_CHAR(fechaentrega - 7/24,'IW'))) ,"
				+ "(Select "+p+"(NumeroDeSolicitudes) as nmoMax From(select  NIT as Proveedor, TO_CHAR(fechaentrega - 7/24,'IW') as semana,"
				+ " count(ID) as NumeroDeSolicitudes from Pedido ped ,proveedor prov  WHERE ped.NITPROVEEDOR = PROV.NIT "
				+ "AND TO_CHAR(fechaentrega - 7/24,'IW') =  "+i+" group by NIT, TO_CHAR(fechaentrega - 7/24,'IW'))) "
				+ "WHERE NumeroDeSolicitudes = nmoMax");
		q.setResultClass(Consulta12b.class);
		List<Consulta12b> w = (List<Consulta12b>) q.executeList();
		return w;
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
