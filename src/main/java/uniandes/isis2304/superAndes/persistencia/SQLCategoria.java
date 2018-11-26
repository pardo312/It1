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
import java.util.Random;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

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
		try{
			
		
		for(int i= 0; i<5;i++){
			
			
				Query q = pm.newQuery(SQL, "INSERT INTO Factura (NUMEROFACTURA,Fecha,idcliente) values ("+nextvalFac()+",TO_DATE('"+nextvalAño()+"-"+nextvalMes()+"-"+nextvalDia()+"', 'YYYY-MM-DD'),"+ nextvalCliente() +")");  
				w = (long) q.executeUnique();
				System.out.println(i);
			
		}
		return w; 
		}
		catch(Exception e){
			generarDatos(pm);
			return w;
		}
		 
	}
	
	public long generarDatos2 (PersistenceManager pm) 
	{ 	
		long w = 123123123;
		
		
		for(int i= 0; i<2500;i++){
			
			
				Query q = pm.newQuery(SQL, "INSERT INTO FacturaProducto (NUMEROFACTURA,CODIGODEBARRASPRODUCTO) values (TO_DATE('"+nextvalAño()+"-"+nextvalMes()+"-"+nextvalDia()+"', 'YYYY-MM-DD'),"+ nextvalBarras() +")");  
				w = (long) q.executeUnique();
				System.out.println(i);
			
		}	                    		
		return w;      
	}

	//NumFact
	private String nextvalFac()
	{
		Random r = new Random();
		int low = 13;
		int high = 50000;
		int resp = r.nextInt(high-low) + low;
		
		return"100"+ resp;
		
		
	}
	//IdCliente
	
		private String nextvalCliente()
		{
			Random r = new Random();
			int low = 1;
			int high = 20;
			int resp = r.nextInt(high-low) + low;
			
			return ""+resp;
			
		}
	
	//CodigoBarras
	
	private String nextvalBarras()
	{
		long resp =(int) (Math.random() * 19) + 1;;
		if(resp < 10)
		{
			return "00000"+resp;
		}
		else
		{
			return"0000"+resp;
		}
		
	}
	//FEchas
	private int nextvalAño()
	{
		Random r = new Random();
		int low = 2010;
		int high = 2018;
		int resp = r.nextInt(high-low) + low;
		
		return resp;
		
	}

	private int nextvalMes()
	{
		Random r = new Random();
	int low = 1;
	int high = 12;
	int resp = r.nextInt(high-low) + low;
		return resp;
		
	}

	private int nextvalDia()
	{
		Random r = new Random();
	int low = 1;
	int high = 28;
	int resp = r.nextInt(high-low) + low;
		return resp;
		
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
