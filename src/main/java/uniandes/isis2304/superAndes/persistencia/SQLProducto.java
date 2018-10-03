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

import uniandes.isis2304.superAndes.negocio.Pedido;
import uniandes.isis2304.superAndes.negocio.Producto;
import uniandes.isis2304.superAndes.negocio.Proveedor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto SIRVEN de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLProducto 
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
	public SQLProducto (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un SIRVEN a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param iDPromocion 
	 * @param idBar - El identificador del bar
	 * @param idBebida - El identificador de la bebida
	 * @param horario - El horario en que el bar sirve la bebida (DIURNO, NOCTURNO, TDOOS)
	 * @return EL número de tuplas insertadas
	 */
	public long registrarProducto (PersistenceManager pm,
			String codigoDeBarras,
			
			 String nombre,
			
			 String marca,
			
			 float precioUnitario,
			
			 String presentacion,
			
			 float precioPorUnidad,
			
			 float cantidadEnLaPresentacion,
			
			 String unidadesDeMedida,

			 String especificacionesDeEmpacado,
			
			 float nivelDeReorden,
			
			 long IDPedido,
			
			 long IDSucursal,
			
			 long IDContenedor,
			 int EnStock, long iDPromocion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " +"PRODUCTO"+
        		"(codigoDeBarras,nombre,marca,precioUnitario,presentacion,precioPorUnidad,cantidadEnLaPresentacion,unidadDeMedida,especificacionesDeEmpacado,nivelDeReorden,IDPedido, IDSucursal, IDContenedor, EnStock,IDPromocion) values ("+codigoDeBarras+ ",'"+nombre+"','"+marca+"',"+precioUnitario+",'"+presentacion+"',"+precioPorUnidad+","+cantidadEnLaPresentacion+",'"+unidadesDeMedida+"','"+especificacionesDeEmpacado+"',"+nivelDeReorden+","+ IDPedido    +","+IDSucursal+","+ IDContenedor +","+ EnStock +","+ iDPromocion +")");
        q.setParameters(codigoDeBarras,nombre,
        		marca,precioUnitario,presentacion,precioPorUnidad,cantidadEnLaPresentacion,
        		unidadesDeMedida,especificacionesDeEmpacado,nivelDeReorden,
        		 IDPedido, IDSucursal, IDContenedor,EnStock,iDPromocion);
        return (long)q.executeUnique();            
	}
	
	public List<Producto> darProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT IDCONTENEDOR FROM " + "PRODUCTO");
		q.setResultClass(Producto.class);		
		return  (List<Producto>)q.executeList() ;
		
	}
	
	


}
