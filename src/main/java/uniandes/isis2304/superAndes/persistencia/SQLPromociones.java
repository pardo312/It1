

package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

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

	public long adicionarPromociones (PersistenceManager pm,long id,
			
			 String descripcion,

			 String precioPromocion,
			
			 long idSucursal) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromociones() + "(id,descripcion, precioPromocion,idSucursal) values (?, ,?,??)");
        q.setParameters(id,
        		
        		  descripcion,

        		  precioPromocion,
        		
        		  idSucursal);
        return (long) q.executeUnique();            
	}

	public long eliminarPromocion(PersistenceManager pm, long id) {
		 Query q = pm.newQuery(SQL, "DELETE FROM " + "PROMOCION "+ "WHERE ID = "+id);
	        return (long) q.executeUnique();  
	}

	
}
