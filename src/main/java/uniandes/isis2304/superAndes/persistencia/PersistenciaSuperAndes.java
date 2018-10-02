

package uniandes.isis2304.superAndes.persistencia;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.negocio.Categoria;
import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.Estante;
import uniandes.isis2304.superAndes.negocio.Pedido;
import uniandes.isis2304.superAndes.negocio.TipoProducto;
import uniandes.isis2304.superAndes.negocio.Producto;
import uniandes.isis2304.superAndes.negocio.Proveedor;


public class PersistenciaSuperAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaSuperAndes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaSuperAndes instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;
	
	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaSuperAndes
	 */
	private SQLUtil sqlUtil;
	
	/**
	 * Atributo para el acceso a la tabla TIPOBEBIDA de la base de datos
	 */
	private SQLPromociones sqlPromociones;
	
	/**
	 * Atributo para el acceso a la tabla BEBIDA de la base de datos
	 */
	private SQLProveedor sqlProveedor;
	
	/**
	 * Atributo para el acceso a la tabla BAR de la base de datos
	 */
	private SQLCategoria sqlCategoria;
	
	/**
	 * Atributo para el acceso a la tabla BEBIDA de la base de datos
	 */
	private SQLCliente sqlCliente;
	
	/**
	 * Atributo para el acceso a la tabla GUSTAN de la base de datos
	 */
	private SQLSucursal sqlSucursal;
	
	/**
	 * Atributo para el acceso a la tabla SIRVEN de la base de datos
	 */
	private SQLProducto sqlProducto;
	
	private SQLTipoProducto sqlTipoProducto;
	


	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaSuperAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("SuperAndes");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("SuperAndes_sequence"); //0
		tablas.add ("PROVEEDOR");//1
		tablas.add ("CLIENTE");//2
		tablas.add ("CLIENTEEMPRESA");//3
		tablas.add ("CLIENTENATURAL");//4
		tablas.add ("CONTENEDOR");//5
		tablas.add ("DESCUENTODELXPORCIENTO");//6
		tablas.add ("ESTANTE");//7
		tablas.add ("FACTURA");//8
		tablas.add ("FACTURAPRODUCTO");//9
		tablas.add ("PAGUE1LLEVESEGUNDOAXPORCIENTO");//10
		tablas.add ("PAGUEXCANTIDADLLEVEY");//11
		tablas.add ("PAGUEXUNIDADESLLEVEY");//12
		tablas.add ("PEDIDO");//13
		tablas.add ("PRODUCTO");//14
		tablas.add ("PRODUCTOPROVEEDOR");//15
		tablas.add ("PROMOCION");//16
		tablas.add ("CATEGORIA");//17
		tablas.add ("SUCURSAL");//18
		tablas.add ("SUCURSALPROVEEDOR");//19
		tablas.add ("SUPERANDES");//20
		tablas.add ("SUPERMERCADO");//21
		tablas.add ("SUPERMERCADOCLIENTE");//22
		tablas.add ("TIPOPRODUCTO");//23
	}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaSuperAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaSuperAndes existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperAndes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaSuperAndes existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperAndes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	

	private void crearClasesSQL ()
	{
		sqlPromociones = new SQLPromociones(this);
		sqlProveedor = new SQLProveedor(this);
		sqlCategoria = new SQLCategoria(this);
		sqlCliente = new SQLCliente(this);
		sqlSucursal = new SQLSucursal(this);
		sqlProducto = new SQLProducto (this);		
		sqlUtil = new SQLUtil(this);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */
	public String darSeqSuperAndes ()
	{
		return tablas.get (0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de TipoProducto de parranderos
	 */
	public String darTablaPromociones()
	{
		return tablas.get (16);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebida de parranderos
	 */
	public String darTablaProveedor ()
	{
		return tablas.get (1);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bar de parranderos
	 */
	public String darTablaCategoria ()
	{
		return tablas.get (1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebedor de parranderos
	 */
	public String darTablaCliente ()
	{
		return tablas.get (2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Gustan de parranderos
	 */
	public String darTablaSucursal ()
	{
		return tablas.get (18);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaProducto ()
	{
		return tablas.get (14);
	}

	
	/**
	 * Transacción para el generador de secuencia de SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de SuperAndes
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los proveedores
	 *****************************************************************/

	
	public Proveedor registrarProveedor(String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long NIT = nextval ();
            long tuplasInsertadas = sqlProveedor.registrarProveedor(pm, NIT, nombre);
            tx.commit();
            
            log.trace ("Inserción de tipo de bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Proveedor(NIT, nombre);
        }
        catch (Exception e)
        {
       	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<Proveedor> darProveedores ()
	{
		return sqlProveedor.darProveedores (pmf.getPersistenceManager());
	}
	
	//metodos Categoria
	
	public List<Categoria> darCategorias ()
	{
		return sqlCategoria.darCategorias(pmf.getPersistenceManager());
	}

	
	public long [] limpiarSuperAndes ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarSuperAndes (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1, -1, -1, -1, -1, -1, -1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
	//Metodos Producto
	
	public Producto registrarProducto(String codigoDeBarras,
			
			 String nombre,
			
			 String marca,
			
			 float precioUnitario,
			
			 String presentacion,
			
			 float precioPorUnidad,
			
			 float cantidadEnLaPresentacion,
			
			 String unidadesDeMedida,

			 String especificacionesDeEmpacado,
			
			 float nivelDeReorden,
			
			 long IDPedido,
			
			 long IDSucursal,
			
			 long IDContenedor,
			 int EnStock)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            
            long tuplasInsertadas = sqlProducto.registrarProducto(pm,codigoDeBarras ,nombre,
            		marca,precioUnitario,presentacion,precioPorUnidad,cantidadEnLaPresentacion,
            		unidadesDeMedida,especificacionesDeEmpacado,nivelDeReorden,
            		 IDPedido, IDSucursal, IDContenedor, EnStock);
            tx.commit();
            
            log.trace ("Inserción de Producto: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Producto(codigoDeBarras ,nombre,
            		marca,precioUnitario,presentacion,precioPorUnidad,cantidadEnLaPresentacion,
            		unidadesDeMedida,especificacionesDeEmpacado,nivelDeReorden,
            		 IDPedido, IDSucursal, IDContenedor,EnStock);
        }
        catch (Exception e)
        {
       	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Categoria registrarCategoria (String nombreCategoria, char perecedero, String codigoDeBarras)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlCategoria.registrarCategoria(pm,id, nombreCategoria,perecedero,codigoDeBarras);
            tx.commit();
            
            log.trace ("Inserción de Categoria: " + nombreCategoria + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Categoria(id, nombreCategoria,perecedero,codigoDeBarras);
        }
        catch (Exception e)
        {
       	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public TipoProducto registrarTipoProducto(String nombreTipo, String metodoAlmac, long idCategoria, long idContenedor) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlTipoProducto.registrarTipoProducto(pm, nombreTipo,metodoAlmac,idCategoria,idContenedor);
            tx.commit();
            
            log.trace ("Inserción de Tipo Producto: " + nombreTipo + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new TipoProducto(nombreTipo,metodoAlmac,idCategoria,idContenedor);
        }
        catch (Exception e)
        {
       	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	

 }
