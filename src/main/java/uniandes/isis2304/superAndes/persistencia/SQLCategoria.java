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

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

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
        Query q = pm.newQuery(SQL, "UPDATE " + "FACTURA" + " SET IDCLIENTE = "+nextvalClien()+"where NUMEROFACTURA = " +2 );                          
        
        return (long) q.executeUnique();
	}
	
	public List<Categoria> darCategorias (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + "CATEGORIA");
		q.setResultClass(Categoria.class);
		return (List<Categoria>) q.executeList();
	}

	
	
	public long generarDatos(PersistenceManager pm) 
	{ 	
		long w = 123123123;
		try{
			
	
			
				 // Query q = pm.newQuery(SQL, "INSERT INTO Categoria (ID,NOMBRECATEGORIA,PERECEDERO,CODIGODEBARRASPRODUCTO,FECHAVENCIMIENTO) values ("+nextvalIdInt()+",'"nextvalNombreCategoria() "',"(int)Math.random()",'"nextvalIdPedido()"',TO_DATE('"+nextvalAnio()+"-"+nextvalMes()+"-"+nextvalDia()+"', 'YYYY-MM-DD'))");  
			//	w = (long) q.executeUnique();
		return w; 
		}
		catch(Exception e){
			generarDatos(pm);
			return w;
		}
		 
	}
	
	// CATEGORIA 
	private int nextvalIdInt()
	{
		Random r = new Random();
		int low = 21;
		int high = 50000;
		int resp = r.nextInt(high-low) + low;
		
		return resp;
			
	}	
	
	private String nextvalNombreCategoria()
	{
	
		String[ ] categoria = {"OTROS", "TECNOLOGIA","ASEO","ORGANICOS"}; 
		String nombre = categoria[(int)Math.random() * 4];

		return nombre;
			
	}	
	
	
	
	
	
//PARA PEDIDO
	
	//IdPedido
		private String nextvalIdPedido()
		{
			Random r = new Random();
			int low = 21;
			int high = 50000;
			int resp = r.nextInt(high-low) + low;
			
			return"0000"+ resp;
			
			
		}
		//IdPedido
				private String nextvalFechaesperada()
				{
					Random r = new Random();
					int low = 21;
					int high = 50000;
					int resp = r.nextInt(high-low) + low;
					
					return"0000"+ resp;
					
					
				}
				//IdPedido
				private String nextvalFechaEntrega()
				{
					Random r = new Random();
					int low = 21;
					int high = 50000;
					int resp = r.nextInt(high-low) + low;
					
					return"0000"+ resp;
					
					
				}
				
				//IdPedido
				private String nextvalEvaluacionCantidad()
				{
					Random r = new Random();
					int low = 21;
					int high = 50000;
					int resp = r.nextInt(high-low) + low;
					
					return"0000"+ resp;
					
					
				}
				//IdPedido
				private String nextvalEvaluacionCalidad()
				{
					Random r = new Random();
					int low = 21;
					int high = 50000;
					int resp = r.nextInt(high-low) + low;
					
					return"0000"+ resp;
					
					
				}
				//IdPedido
				private String nextvalCalificacion()
				{
					Random r = new Random();
					int low = 21;
					int high = 50000;
					int resp = r.nextInt(high-low) + low;
					
					return"0000"+ resp;
					
					
				}
				//IdPedido
				private String nextvalFinalizado()
				{
					Random r = new Random();
					int low = 21;
					int high = 50000;
					int resp = r.nextInt(high-low) + low;
					
					return"0000"+ resp;
					
					
				}
				//IdPedido
				private String nextvalNitProveedor()
				{
					Random r = new Random();
					int low = 21;
					int high = 50000;
					int resp = r.nextInt(high-low) + low;
					
					return"0000"+ resp;
					
					
				}
				
	
	
	
	//----------------------------------
				
				//Para Proveedor
				
				//Id
				private String nextvalIdProve()
				{
					Random r = new Random();
					int low = 22;
					int high = 50000;
					int resp = r.nextInt(high-low) + low;
					
					return"1000"+ resp;
					
					
				}
				
				//Nombreproveedor
				public String generarNombreProveedor()
				{
					Faker faker = new Faker();

					String name = faker.name().fullName();

					return name;
				}
				
	//------------------------------------
	//Direccion
	public String generarDir()
	{
		Faker faker = new Faker();

		Address address = faker.address();
		
		return address.streetAddress();
	}
	//Nombre
	public String generarNombre()
	{
		Faker faker = new Faker();

		String name = faker.name().fullName();

		return name;
	}
	//Nombre
		public String generarDescripcion()
		{
			Faker faker = new Faker();

			String name = faker.lorem().fixedString(40);
			
			return name;
		}
	//Email
public String generarEmail()
{
	Faker faker = new Faker();

	String name = faker.name().firstName();
	
	return "'"+name + "@correo.com'";
}
	//Idcliente	
	private String nextvalClien()
	{
		Random r = new Random();
		int low = 21;
		int high = 50000;
		int resp = r.nextInt(high-low) + low;
		
		return""+ resp;
		
		
	}
	//IdPromo
		private String nextvalpromo()
		{
			Random r = new Random();
			int low = 50000;
			int high = 60000;
			int resp = r.nextInt(high-low) + low;
			
			return"0000"+ resp;
			
			
		}
	
	//Cedula
	private String nextvalCedu()
	{
		Random r = new Random();
		int low = 1;
		int high = 30000;
		int resp = r.nextInt(high-low) + low;
		
		return"100"+ resp;
		
		
	}
	//NIT
		private String nextvalNIT()
		{
			Random r = new Random();
			int low = 1;
			int high = 20;
			int resp = r.nextInt(high-low) + low;
			
			return""+ resp;
			
			
		}
	//Ptos de compra
		private String nextvalPtos()
		{
			Random r = new Random();
			int low = 1;
			int high = 50000;
			int resp = r.nextInt(high-low) + low;
			
			return ""+resp;
			
			
		}
	//Super
	
	private String nextvalPromo()
	{
		Random r = new Random();
		int low = 1;
		int high = 60000;
		int resp = r.nextInt(high-low) + low;
		
		return ""+resp;
		
	}
	//Precio	
		private String nextvalPrecio()
		{
			Random r = new Random();
			int low = 2000;
			int high = 1000000;
			int resp = r.nextInt(high-low) + low;
			
			return ""+resp;
			
			
		}
	private String nextvalx()
	{
		Random r = new Random();
		int low = 1;
		int high = 60000;
		int resp = r.nextInt(high-low) + low;
		
		return ""+resp;
		
	}
	private String nextvaly()
	{
		Random r = new Random();
		int low = 1;
		int high = 100;
		int resp = r.nextInt(high-low) + low;
		
		return ""+resp;
		
	}
	//Super
	
			private String nextvalSuper()
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
	private int nextvalAnio()
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
