
package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.Estante;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.Sucursal;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto GUSTAN de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLContenedor 
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
	public SQLContenedor (PersistenciaSuperAndes pp)
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
	
	public long adicionarContenedor(PersistenceManager pm, int ido,int capacidadVolumen, int capacidadPeso, String unidadesPeso,String unidadesVolumen, int idBodegaSucursal) {
		 Query q = pm.newQuery(SQL, "INSERT INTO " + "CONTENEDOR" + "(id,capacidadVolumen,capacidadPeso,unidadesPeso,unidadesVolumen,idBodegaSucursal) values (?,?,?,?,?,?)");
	        q.setParameters( ido, capacidadVolumen, capacidadPeso,unidadesPeso,unidadesVolumen, idBodegaSucursal);
	        return (long) q.executeUnique();
	}
	
	/**
	 * lista de todos los clientes
	 * @param pm
	 * @return la lista de todos los clientes
	 */
	public List<Sucursal> darContenedores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + "CONTENEDORES");
		q.setResultClass(Proveedor.class);
		return (List<Sucursal>) q.executeList();
	}


	

}
