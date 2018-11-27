
package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Sucursal;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto GUSTAN de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLSucursal 
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
	public SQLSucursal (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un GUSTAN a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idBebedor - El identificador del bebedor
	 * @param idBebida - El identificador de la bebida
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarSucursal(PersistenceManager pm,int id,String nombre,String ciudad, String direccion,String segmentacionDeMercado, String tamanioInstalacion, long NITSupermercado) 
	{
		try {
        Query q = pm.newQuery(SQL, "INSERT INTO " + "SUCURSAL" + "(id,nombre,ciudad,direccion,segmentacionDeMercado,tamanioInstalacion,NITSupermercado) values (?,?,?,?,?,?,?)");
        q.setParameters( id, nombre, ciudad,direccion,segmentacionDeMercado, tamanioInstalacion,NITSupermercado);
        return (long) q.executeUnique();
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	/**
	 * lista de todos los clientes
	 * @param pm
	 * @return la lista de todos los clientes
	 */
	public List<Sucursal> darSucursales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT ID,NOMBRE,CIUDAD,DIRECCION,SEGMENTACIONDEMERCADO, TAMANIOINSTALACION, NITSUPERMERCADO FROM " + "SUCURSAL");
		q.setResultClass(Sucursal.class);
		List<Sucursal> w = q.executeList();
		return w ;
	}

	public long eliminarSucursal(PersistenceManager pm, int id) {
		 Query q = pm.newQuery(SQL, "DELETE FROM " + "SUCURSAL "+ "WHERE ID = "+id);
	        return (long) q.executeUnique();  
	}
	

}
