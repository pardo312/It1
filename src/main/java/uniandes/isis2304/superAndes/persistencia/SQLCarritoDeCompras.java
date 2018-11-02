
package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.CarritoDeCompras;
import uniandes.isis2304.superAndes.negocio.Categoria;
import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.ClienteNatural;
import uniandes.isis2304.superAndes.negocio.Proveedor;

/**
 *
 * @author da.leon
 */
class SQLCarritoDeCompras 
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
	public SQLCarritoDeCompras (PersistenciaSuperAndes pp)
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
	public long registrarCarritoDeCompras(PersistenceManager pm, long idCarrito, int cedula, String NITCLIENTE, int usado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + "CARRITODECOMPRA"+ "(idCarrito, USADO,NITCLIENTE,CEDULA) values ( ?, ?, ?, ?)");
        q.setParameters(idCarrito, usado, NITCLIENTE,  cedula);
        return (long) q.executeUnique();
	}

	/**
	 * lista de todos los clientes
	 * @param pm
	 * @return la lista de todos los clientes
	 */
	public List<CarritoDeCompras> darCarritosDeCompra(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + "CARRITODECOMPRA");
		return (List<CarritoDeCompras>) q.executeList();
	}
	
	public long devolverProducto(PersistenceManager pm, long idCarrito) 
	{
		Query q = pm.newQuery(SQL,"UPDATE CARRITODECOMPRA SET USADO = 0 WHERE IDCARRITO = " +idCarrito );
		
        return (long) q.executeUnique();
	}

}
