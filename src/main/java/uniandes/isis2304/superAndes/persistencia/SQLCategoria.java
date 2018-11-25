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

import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Categoria;
import uniandes.isis2304.superAndes.negocio.Proveedor;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLCategoria 
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
	public SQLCategoria (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long registrarCategoria (PersistenceManager pm,long id,	
			
			 String nombreCategoria,
			
			 char perecedero,

			
			 String codigoDeBarrasProducto) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + "CATEGORIA" + "( id,nombreCategoria,perecedero,codigoDeBarrasProducto) values ("+id+",'"+nombreCategoria+"',"+perecedero+",'"+ codigoDeBarrasProducto + "')");                          
        
        return (long) q.executeUnique();
	}
	
	public List<Categoria> darCategorias (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + "CATEGORIA");
		q.setResultClass(Categoria.class);
		return (List<Categoria>) q.executeList();
	}
	
	public long generarDatos (PersistenceManager pm) 
	{ 	
		long w = 123123123;
		for(int i= 0; i<8800;i++){
			
			String[] p = nextvalNITCliente ();
			if(p[0] != null)
			{
			Query q = pm.newQuery(SQL, "INSERT INTO CARRITODECOMPRA (IDCARRITO,USADO,NITCLIENTE) values (" + nextval() + "," + nextvalUSADO() + ",'" + p[0]+"')");  
			w = (long) q.executeUnique();
			System.out.println(i);
				
			}
			else{
				Query q = pm.newQuery(SQL, "INSERT INTO CARRITODECOMPRA (IDCARRITO,USADO,CEDULA) values (" + nextval() + "," + nextvalUSADO() + "," +p[1]+")");  
				w = (long) q.executeUnique();
				System.out.println(i);
			}
			
			
		}
                               
		
		return w;
      
	}
	
	private String nextval ()
	{
		long resp =(int) (Math.random() * 1000000000) + 20;
		return ""+resp;
		
	}
	
	private int nextvalUSADO()
	{
		if(Math.random() < 0.5)
		{
			return 1;
		}
		else{
			return 0;
		}
		
	}
	
	private String[] nextvalNITCliente ()
	{
		String [] array = new String[2] ;
		if(Math.random() < 0.5)
		{
			long resp =(int) (Math.random() * 19) + 1;;
			if(resp < 10)
			{
				array[0] = "0000"+resp;
			}
			else
			{
				array[0] =  "000"+resp;
			}
		}
		else{
			long resp =(int) (Math.random() * 19) + 1;;
			if(resp < 10)
			{
				array[1] = "100"+resp;
			}
			else
			{
				array[1] =  "10"+resp;
			}
		}
		return array;
		
	}

	
	
}
