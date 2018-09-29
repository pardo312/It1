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

import uniandes.isis2304.superAndes.negocio.TipoProducto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto TIPO DE PRODUCTOde Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLTipoProducto 
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
	public SQLTipoProducto (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un TIPOPRODUCTOa la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idTipoProducto - El identificador del tipo de bebida
	 * @param nombre - El nombre del tipo de bebida
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarTipoProducto (PersistenceManager pm, long idTipoProducto, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTipoProducto  () + "(id, nombre) values (?, ?)");
        q.setParameters(idTipoProducto, nombre);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar TIPOS DE PRODUCTOde la base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreTipoProducto - El nombre del tipo de bebida
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarTipoProductoPorNombre (PersistenceManager pm, String nombreTipoProducto)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoProducto  () + " WHERE nombre = ?");
        q.setParameters(nombreTipoProducto);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar TIPOS DE PRODUCTOde la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idTipoProducto - El identificador del tipo de bebida
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarTipoProductoPorId (PersistenceManager pm, long idTipoProducto)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoProducto  () + " WHERE id = ?");
        q.setParameters(idTipoProducto);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN TIPO DE PRODUCTOde la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idTipoProducto - El identificador del tipo de bebida
	 * @return El objeto TIPOPRODUCTO que tiene el identificador dado
	 */
	public TipoProducto darTipoProductoPorId (PersistenceManager pm, long idTipoProducto) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoProducto  () + " WHERE id = ?");
		q.setResultClass(TipoProducto.class);
		q.setParameters(idTipoProducto);
		return (TipoProducto) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN TIPO DE PRODUCTOde la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreTipoProducto - El nombre del tipo de bebida
	 * @return El objeto TIPOPRODUCTOque tiene el nombre dado
	 */
	public List<TipoProducto> darTiposProductoPorNombre (PersistenceManager pm, String nombreTipoProducto) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoProducto  () + " WHERE nombre = ?");
		q.setResultClass(TipoProducto.class);
		q.setParameters(nombreTipoProducto);
		return (List<TipoProducto>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS TIPOS DE PRODUCTOde la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos TIPOPRODUCTO
	 */
	public List<TipoProducto> darTiposProducto (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoProducto  ());
		q.setResultClass(TipoProducto.class);
		return (List<TipoProducto>) q.executeList();
	}

}
