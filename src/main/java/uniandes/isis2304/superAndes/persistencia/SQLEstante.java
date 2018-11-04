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

import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.Estante;
import uniandes.isis2304.superAndes.negocio.TipoProducto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBIDA de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLEstante 
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
	public SQLEstante (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	
	public long registrarEstante(PersistenceManager pm, int id, int nivelAbastecimiento, int idSucursales) 
	{
		
		try{ 
        Query q = pm.newQuery(SQL, "INSERT INTO " + "ESTANTE" + "(id, nivelAbastecimiento, idSucursales) values ("+id+","+ nivelAbastecimiento+","+ idSucursales+")");

        return (long) q.executeUnique(); 
		}
		catch (Exception e)
		{	
			e.printStackTrace();
			return 0;
		}
	}
	public List<Estante> darEstantes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + "Estante ");
		q.setResultClass(Estante.class);
		List<Estante> w = (List<Estante>) q.executeList();
		return w;
	}
	public List<Estante> darEstante (PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + "Estante WHERE ID =" + id);
		q.setResultClass(Estante.class);
		List<Estante> w = (List<Estante>) q.executeList();
		return w;
	}
	public long eliminarEstante(PersistenceManager pm, long id) {
		 Query q = pm.newQuery(SQL, "DELETE FROM " + "Estante "+ "WHERE id = "+id);
	        return (long) q.executeUnique();  
	}


	

}
