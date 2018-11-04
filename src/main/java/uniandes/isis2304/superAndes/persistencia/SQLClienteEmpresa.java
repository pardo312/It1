
package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Categoria;
import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.ClienteEmpresa;
import uniandes.isis2304.superAndes.negocio.Proveedor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBEDOR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author da.leon
 */
class SQLClienteEmpresa 
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
	public SQLClienteEmpresa (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	
	 /**
	  * 
	  * @param pm pensistence manager
	  * @param NIT nir del cliente empresa
	  * @param direccion del cliente empresa
	  * @return
	  */
	public long registrarClienteEmpresa (PersistenceManager pm,
			
			 String NIT,
			
			 String direccion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + "CLIENTEEMPRESA" + "(NIT,direccion) values ( ?, ?)");
        q.setParameters( NIT,  direccion);
        return (long) q.executeUnique();
	}
	/**
	 * lista de todos los clientes
	 * @param pm
	 * @return la lista de todos los clientes
	 */
	public List<ClienteEmpresa> darClientesEmpresa (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + "CLIENTEEMPRESA");
		q.setResultClass(Proveedor.class);
		return (List<ClienteEmpresa>) q.executeList();
	}


	public long eliminarClienteEmpresa(PersistenceManager pm, String NIT) {
		 Query q = pm.newQuery(SQL, "DELETE FROM " + "ClienteEmpresa "+ "WHERE NIT = '"+NIT+"'");
	        return (long) q.executeUnique();  
	}

}
