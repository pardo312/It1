package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLTipoProducto 
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

	public long registrarTipoProducto (PersistenceManager pm,
			
			 String nombreTipo,
			
			 String metodoAlmac,
			
			 long idCategoria,

			
			 long idContenedor) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " +"TIPOPRODUCTO" + "( nombreTipo,metodoAlmac,idCategoria,idContenedor) values ('"+nombreTipo+"','"+metodoAlmac+"',"+idCategoria+","+idContenedor+")"                                                                      );
       
        return (long) q.executeUnique();
	}


	
}
