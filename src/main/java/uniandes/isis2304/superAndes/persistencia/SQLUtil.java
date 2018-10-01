/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: SuperAndes Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto  de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLUtil
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
	public SQLUtil (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqSuperAndes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas Proveedor, Sucursal, Promociones, Producto,
	 * Cliente, Categoria y , respectivamente
	 */
	public long [] limpiarSuperAndes (PersistenceManager pm)
	{
        Query qProveedor = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor ());          
        Query qSucursal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursal ());
        Query qPromociones = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromociones ());
        Query qProducto = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto ());
        Query qCliente = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente ());
        Query qCategoria = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCategoria ());
        

        long ProveedorEliminados = (long) qProveedor.executeUnique ();
        long SucursalEliminados = (long) qSucursal.executeUnique ();
        long PromocionesEliminadas = (long) qPromociones.executeUnique ();
        long ProductosEliminadas = (long) qProducto.executeUnique ();
        long tiposProductoEliminados = (long) qCliente.executeUnique ();
        long CategoriaesEliminados = (long) qCategoria.executeUnique ();
       
        return new long[] {ProveedorEliminados, SucursalEliminados, PromocionesEliminadas, ProductosEliminadas, 
        		tiposProductoEliminados, CategoriaesEliminados};
	}

}
