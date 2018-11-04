

package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Promocion;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.TipoProducto;



class SQLPromociones 
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
	public SQLPromociones (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long registrarPromocion (PersistenceManager pm,int id,
			
			 String descripcion,

			 String precioPromocion,
			
			 int idSucursal) 
	{	try{
        Query q = pm.newQuery(SQL, "INSERT INTO " + "PROMOCION" + "(ID, DESCRIPCION,PRECIOPROMOCION,IDSUCURSAL) values ("+id+",'"+ descripcion+"','"+ precioPromocion+"',"+ idSucursal+")");
        return (long) q.executeUnique();  
	}
	catch (Exception e)
	{	
		return 0;
	}
	}

	public List<Promocion> darPromociones (PersistenceManager pm)
	{		
			Query q = pm.newQuery(SQL, "SELECT * FROM " + "PROMOCION");
			q.setResultClass(Promocion.class);
			List<Promocion> w = (List<Promocion>) q.executeList();
			return w;	
	}
	
	public List<Promocion> darPromocion(PersistenceManager pm, String id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + "PROMOCION WHERE ID = " + id);
		q.setResultClass(Promocion.class);
		List<Promocion> w = (List<Promocion>) q.executeList();
		return w;
	}
	
	public long eliminarPromocion(PersistenceManager pm, long id) {
		 Query q = pm.newQuery(SQL, "DELETE FROM " + "PROMOCION "+ "WHERE ID = "+id);
	        return (long) q.executeUnique();  
	}

	
}
