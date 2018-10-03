
package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Categoria;
import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.ClienteNatural;
import uniandes.isis2304.superAndes.negocio.Proveedor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBEDOR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author da.leon
 */
class SQLClienteNatural 
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
	public SQLClienteNatural (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * registra un cliente natural
	 * @param pm persistence manager
	 * @param id del cliente
	 * @param cedula del cliente
	 * @param email del cliente
	 * @param nombre del cliente
	 * @return
	 */
	public long registrarClienteNatural (PersistenceManager pm,
			
			 int cedula,
			
			 String email,
			
			 String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + "CLIENTENATURAL"+ "(cedula,nombre,email) values ( ?, ?, ?)");
        q.setParameters( cedula, nombre,  email);
        return (long) q.executeUnique();
	}

	/**
	 * lista de todos los clientes
	 * @param pm
	 * @return la lista de todos los clientes
	 */
	public List<ClienteNatural> darClientesNaturales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + "CLIENTENATURAL");
		q.setResultClass(Proveedor.class);
		return (List<ClienteNatural>) q.executeList();
	}

}
