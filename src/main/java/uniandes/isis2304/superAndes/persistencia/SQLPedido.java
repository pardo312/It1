package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Categoria;
import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.Proveedor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBEDOR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLPedido 
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
	public SQLPedido (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un BEBEDOR a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idBebedor - El identificador del bebedor
	 * @param nombre - El nombre del bebedor
	 * @param ciudad - La ciudad del bebedor
	 * @param presupuesto - El presupuesto del bebedor (ALTO, MEDIO, BAJO)
	 * @return EL número de tuplas insertadas
	 */
	public long registrarCliente (PersistenceManager pm,int idCliente, int puntosDeCompra,String NITCliente,int cedulaCliente) 
	{
		Query s = pm.newQuery(SQL, "INSERT INTO " + "CLIENTEEMPRESA" + "(NIT,DIRECCION) values ('00022', 'direccion 22' )");
		s.executeUnique();
		
		Query r = pm.newQuery(SQL, "INSERT INTO " + "CLIENTENATURAL" + "(CEDULA, NOMBRE, EMAIL)VALUES (1020,'JUAN PEREZ','CLIENTE21@CORREO.COM')");
		r.executeUnique();
        
		Query q = pm.newQuery(SQL, "INSERT INTO " + "CLIENTE" + "(idCliente,puntosDeCompra,NITCliente,cedulaCliente) values ("+idCliente+", "+puntosDeCompra+", "+NITCliente+", "+cedulaCliente+" )");
        q.setParameters( idCliente, puntosDeCompra, NITCliente,cedulaCliente);
        return (long) q.executeUnique();
	}

	/**
	 * lista de todos los clientes
	 * @param pm
	 * @return la lista de todos los clientes
	 */
	public List<Cliente> darClientes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + "CLIENTE");
		q.setResultClass(Proveedor.class);
		return (List<Cliente>) q.executeList();
	}
	

}
