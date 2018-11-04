

package uniandes.isis2304.superAndes.persistencia;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
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

import uniandes.isis2304.superAndes.negocio.CarritoDeCompras;
import uniandes.isis2304.superAndes.negocio.Categoria;
import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.ClienteEmpresa;
import uniandes.isis2304.superAndes.negocio.ClienteNatural;
import uniandes.isis2304.superAndes.negocio.Contenedor;
import uniandes.isis2304.superAndes.negocio.Descuentodelxporciento;
import uniandes.isis2304.superAndes.negocio.Estante;
import uniandes.isis2304.superAndes.negocio.Factura;
import uniandes.isis2304.superAndes.negocio.FacturaProducto;
import uniandes.isis2304.superAndes.negocio.Pague1llevesegundoaxporciento;
import uniandes.isis2304.superAndes.negocio.Paguexcantidadllevey;
import uniandes.isis2304.superAndes.negocio.Paguexunidadesllevey;
import uniandes.isis2304.superAndes.negocio.Pedido;
import uniandes.isis2304.superAndes.negocio.Sucursal;
import uniandes.isis2304.superAndes.negocio.TipoProducto;
import uniandes.isis2304.superAndes.negocio.VODescuentodelxporciento;
import uniandes.isis2304.superAndes.negocio.VOPaguexunidadesllevey;
import uniandes.isis2304.superAndes.negocio.Producto;
import uniandes.isis2304.superAndes.negocio.Promocion;
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
	 * Atributo para el acceso a la tabla CLIENTE de la base de datos
	 */
	private SQLCliente sqlCliente;
	/**
	 * Atributo para el acceso a la tabla CLIENTENATURAL de la base de datos
	 */
	private SQLClienteNatural sqlClienteNatural;
	/**
	 * Atributo para el acceso a la tabla CLIENTEEMPRESA de la base de datos
	 */
	private SQLClienteEmpresa sqlClienteEmpresa;


	/**
	 * Atributo para el acceso a la tabla SUCURSAL de la base de datos
	 */
	private SQLSucursal sqlSucursal;

	/**
	 * atributo para al acceso de la tabla contenedor 
	 */
	private SQLContenedor sqlContenedor;

	/**
	 * atributo para al acceso de la tabla pedido 
	 */
	private SQLPedido sqlPedido;


	/**
	 * Atributo para el acceso a la tabla SIRVEN de la base de datos
	 */
	private SQLProducto sqlProducto;

	private SQLPromociones sqlPromocion;

	private SQLTipoProducto sqlTipoProducto;

	private SQLPromocionPorcentaje sqlPromocionPorcentaje;


	private SQLPromocionUnidadProducto sqlPromocionUnidadProducto;

	private SQLEstante sqlEstante;

	private SQLCarritoDeCompras sqlCarritoDeCompras;

	private SQLRFC1 sqlRFC1;

	private SQLRFC2 sqlRFC2;

	private SQLRFC3 sqlRFC3;

	private SQLRFC4 sqlRFC4;

	private SQLRFC5 sqlRFC5;

	private SQLRFC6 sqlRFC6;

	private SQLRFC7 sqlRFC7;

	private SQLRFC8 sqlRFC8;

	private SQLFactura sqlFactura;
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
		tablas.add ("CARRITODECOMPRA"); //24
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
		sqlClienteEmpresa= new SQLClienteEmpresa(this);
		sqlClienteNatural = new SQLClienteNatural(this);
		sqlContenedor = new SQLContenedor(this);
		sqlPedido = new SQLPedido(this);
		sqlEstante = new SQLEstante(this);
		sqlSucursal = new SQLSucursal(this);
		sqlProducto = new SQLProducto (this);	
		sqlTipoProducto = new SQLTipoProducto (this);	
		sqlPromocion = new SQLPromociones(this);
		sqlPromocionPorcentaje = new SQLPromocionPorcentaje(this);
		sqlPromocionUnidadProducto = new SQLPromocionUnidadProducto(this);
		sqlFactura= new SQLFactura(this);
		sqlCarritoDeCompras = new SQLCarritoDeCompras(this);
		sqlRFC1 = new SQLRFC1(this);
		sqlRFC2 = new SQLRFC2(this);
		sqlRFC3 = new SQLRFC3(this);
		sqlRFC4 = new SQLRFC4(this);
		sqlRFC5 = new SQLRFC5(this);
		sqlRFC6 = new SQLRFC6(this);
		sqlRFC7 = new SQLRFC7(this);
		sqlRFC8 = new SQLRFC8(this);
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
	 * da la tabla cliente empresa
	 * @return
	 */
	public String darTablaClienteEmpresa()
	{
		return tablas.get(3);
	}

	/**
	 * da la tabla cliente natural
	 * @return
	 */
	public String darTablaClienteNatural()
	{
		return tablas.get(4);
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
	private int nextvalInt ()
	{
		int resp = (int)sqlUtil.nextval (pmf.getPersistenceManager());
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
	 * 			Requerimiento 1
	 *****************************************************************/
	private int nextvalNITProveedor ()
	{
		long resp =(int) (Math.random() * 100) + 20;;
		if(resp < 10)
		{
			return Integer.parseInt("1000"+resp);
		}
		else
		{
			return Integer.parseInt("100"+resp);
		}

	}

	public Proveedor registrarProveedor(String nombre, int opcion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		if(opcion == 0)
		{
			try
			{	
				tx.begin();
				long NIT = nextvalNITProveedor();
				long tuplasInsertadas = sqlProveedor.registrarProveedor(pm, NIT, nombre);
				tx.commit();

				log.trace ("Inserción de proveedor: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

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
		else {

			tx.begin();
			long tuplasInsertadas = sqlProveedor.registrarProveedor(pm, 10007, nombre);
			tx.commit();

			log.trace ("Inserción de proveedor: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			if(tuplasInsertadas == 0){
				return null;
			}
			else{
				return new Proveedor(10007, nombre);
			}



		}
	}

	public List<Proveedor> darProveedores ()
	{
		return sqlProveedor.darProveedores (pmf.getPersistenceManager());
	}
	public List<Producto> darProductos() {


		return sqlProducto.darProductos(pmf.getPersistenceManager());
	}

	public List<CarritoDeCompras> darCarritos() {


		return sqlCarritoDeCompras.darCarritosDeCompra(pmf.getPersistenceManager());
	}
	public List<Factura> darFacturas() {


		return sqlFactura.darFacturas(pmf.getPersistenceManager());
	}


	/* ****************************************************************
	 * 			Requerimiento 2
	 *****************************************************************/

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
			int EnStock,
			long IDPromocion,int volumen
			, long IDCarrito)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();

			long tuplasInsertadas = sqlProducto.registrarProducto(pm,codigoDeBarras ,nombre,
					marca,precioUnitario,presentacion,precioPorUnidad,cantidadEnLaPresentacion,
					unidadesDeMedida,especificacionesDeEmpacado,nivelDeReorden,
					IDPedido, IDSucursal, IDContenedor, EnStock,IDPromocion ,volumen, IDCarrito);
			tx.commit();

			log.trace ("Inserción de Producto: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			if(tuplasInsertadas == 0){
				return null;
			}
			else{
				return new Producto(codigoDeBarras ,nombre,
						marca,precioUnitario,presentacion,precioPorUnidad,cantidadEnLaPresentacion,
						unidadesDeMedida,especificacionesDeEmpacado,nivelDeReorden,
						IDPedido, IDSucursal, IDContenedor,EnStock,IDPromocion,volumen, IDCarrito);
			}

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

	public List<Categoria> darCategorias ()
	{
		return sqlCategoria.darCategorias(pmf.getPersistenceManager());
	}


	/* ****************************************************************
	 * 			RF3 clientes 
	 *****************************************************************/


	public Cliente registrarCliente ( int idCliente, int puntosDeCompra,  String nitCliente, int cedulaCliente)
	{

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();

			long tuplasInsertadas = sqlCliente.registrarCliente(pm, idCliente, puntosDeCompra, nitCliente, cedulaCliente );
			tx.commit();

			log.trace ("Inserción de cliente: " + cedulaCliente + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Cliente(idCliente,puntosDeCompra,nitCliente,cedulaCliente);

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

			darClientes();
		}
	}



	public ClienteNatural registrarClienteNatural ( int cedula, String nombre, String email, int a)
	{
		if (a == 1)
		{

			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx=pm.currentTransaction();
			try
			{
				tx.begin();

				long tuplasInsertadas = sqlClienteNatural.registrarClienteNatural(pm, cedula, nombre, email);
				tx.commit();

				log.trace ("Inserción de cliente natural " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");


				if(tuplasInsertadas == 0){
					return null;
				}
				else 
					return new ClienteNatural(cedula, nombre,email);
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

				darClientesNaturales();

			}
		}
		else {

			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx=pm.currentTransaction();

			tx.begin();
			long tuplasInsertadas = sqlClienteNatural.registrarClienteNatural(pm, 1000, nombre, email);
			tx.commit();

			log.trace ("Inserción de cliente Natural: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			if(tuplasInsertadas == 0){
				return null;
			}
			else{
				return new ClienteNatural(1000, nombre, email);
			}

		}

	}

	public ClienteEmpresa registrarClienteEmpresa ( String NIT, String direccion, int i)
	{

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		if (i == 1) {


			try
			{
				tx.begin();
				long tuplasInsertadas = sqlClienteEmpresa.registrarClienteEmpresa(pm, NIT, direccion);
				tx.commit();

				log.trace ("Inserción de cliente empresa " + NIT + ": " + tuplasInsertadas + " tuplas insertadas");

				if(tuplasInsertadas == 0){
					return null;
				}
				else
					return new ClienteEmpresa(NIT,direccion);


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

				darClientesEmpresa();
			}
		}

		else 
		{
			tx.begin();
			long tuplasInsertadas = sqlClienteEmpresa.registrarClienteEmpresa(pm, "00001", direccion);
			tx.commit();

			log.trace ("Inserción de cliente Empresa: " + "00001" + ": " + tuplasInsertadas + " tuplas insertadas");
			if(tuplasInsertadas == 0){
				return null;
			}
			else{
				return new ClienteEmpresa("00001", direccion);
			}


		}

	}

	public CarritoDeCompras registrarCarritoDeCompras ( int idCarrito, int usado, String NITProveedor, int cedula)
	{

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		int ido = (int)nextval ();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCarritoDeCompras.registrarCarritoDeCompras(pm, ido, usado, NITProveedor, cedula);
			tx.commit();

			log.trace ("Inserción de carrito de compras " + ido + ": " + tuplasInsertadas + " tuplas insertadas");

			return new CarritoDeCompras(ido,usado, NITProveedor,cedula);
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

			darCarritosDeCompras();
		}

	}



	private List<CarritoDeCompras> darCarritosDeCompras() 
	{
		return sqlCarritoDeCompras.darCarritosDeCompra(pmf.getPersistenceManager());
	}

	public List<Cliente> darClientes ()
	{
		return sqlCliente.darClientes(pmf.getPersistenceManager());
	}
	public List<ClienteNatural> darClientesNaturales ()
	{
		return sqlClienteNatural.darClientesNaturales(pmf.getPersistenceManager());
	}
	public List<Sucursal> darSucursales ()
	{
		return sqlSucursal.darSucursales(pmf.getPersistenceManager());
	}

	public List<Contenedor> darContenedores ()
	{
		return sqlContenedor.darContenedores(pmf.getPersistenceManager());
	}
	public List<ClienteEmpresa> darClientesEmpresa ()
	{
		return sqlClienteEmpresa.darClientesEmpresa(pmf.getPersistenceManager());
	}
	public List<Promocion> darPromociones ()
	{
		return sqlPromocion.darPromociones(pmf.getPersistenceManager());
	}
	public List<ClienteEmpresa> darClientesEmpresariales ()
	{
		return sqlClienteEmpresa.darClientesEmpresariales(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			RF4
	 *****************************************************************/

	public Sucursal registrarSucursal(int id, String nombre, String ciudad, String direccion, String segmentacionDeMercado, String tamanioInstalacion, int NITSupermercado, int a )
	{
		if (a == 1)
		{

			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx=pm.currentTransaction();
			try
			{
				tx.begin();
				long ido = nextval ();
				long tuplasInsertadas = sqlSucursal.adicionarSucursal(pm, (int) ido, nombre, ciudad, direccion, segmentacionDeMercado, tamanioInstalacion, NITSupermercado);
				tx.commit();

				log.trace ("Inserción de sucursal " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

				return new Sucursal(id,nombre,ciudad,direccion,segmentacionDeMercado,tamanioInstalacion, NITSupermercado);
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
		else 
		{
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx=pm.currentTransaction();

			tx.begin();
			long tuplasInsertadas = sqlSucursal.adicionarSucursal(pm, id, nombre, ciudad, direccion, segmentacionDeMercado, tamanioInstalacion, NITSupermercado);
			tx.commit();

			log.trace ("Inserción de Sucursal: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			if(tuplasInsertadas == 0){
				return null;
			}
			else{
				return new Sucursal(5, nombre, ciudad, tamanioInstalacion, tamanioInstalacion, tamanioInstalacion, tuplasInsertadas);
			}
		}
	}
	public Contenedor registrarContenedor(int id, int capacidadVolumen,
			int capacidadPeso, String unidadesPeso, String unidadesVolumen,
			int idBodegaSucursal, int a) 
	{
		if (a == 1)
		{

			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx=pm.currentTransaction();
			try
			{
				tx.begin();
				long ido = nextval ();
				long tuplasInsertadas = sqlContenedor.adicionarContenedor(pm, (int) ido, capacidadVolumen, capacidadPeso, unidadesPeso, unidadesVolumen, idBodegaSucursal);
				tx.commit();

				log.trace ("Inserción del contenedor " + id + ": " + tuplasInsertadas + " tuplas insertadas");

				return new Contenedor(id,capacidadVolumen,capacidadPeso,unidadesPeso,unidadesVolumen,idBodegaSucursal);
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

		else {
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx=pm.currentTransaction();

			tx.begin();
			long tuplasInsertadas = sqlContenedor.adicionarContenedor(pm, id, capacidadVolumen, capacidadPeso, unidadesPeso, unidadesVolumen, idBodegaSucursal);
			tx.commit();

			log.trace ("Inserción de contenedor: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
			if(tuplasInsertadas == 0){
				return null;
			}
			else{
				return new Contenedor(10, capacidadVolumen, capacidadPeso, unidadesPeso, unidadesVolumen, idBodegaSucursal);
			}

		}
	}

	public Factura registrarFactura(String numeroDeFactura, java.util.Date fecha, int idCliente) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();

			long tuplasInsertadas = sqlFactura.adicionarFactura(pm,numeroDeFactura,fecha,idCliente);
			tx.commit();

			log.trace ("Inserción de la factura " + numeroDeFactura + ": " + tuplasInsertadas + " tuplas insertadas");

			if(tuplasInsertadas == 0)
			{
				return null;
			}
			else
			{
				return new Factura(numeroDeFactura,fecha,idCliente);
			}

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

	public FacturaProducto registrarFacturaProd(String numeroDeFactura, String codigoDeBarras) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();

			long tuplasInsertadas = sqlFactura.adicionarFacturaProd(pm,numeroDeFactura,codigoDeBarras);
			tx.commit();

			log.trace ("Inserción del contenedor " + numeroDeFactura + ": " + tuplasInsertadas + " tuplas insertadas");

			return new FacturaProducto(numeroDeFactura,codigoDeBarras);
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

	public Pedido registrarPedido(int id, java.util.Date fechaEsperada,java.util.Date fechaEntrega, String evaluacionCantidad, String evaluacionCalidad,int calificacion, int finalizado, int NITProveedor, int a) 
	{
		if (a== 1)
		{

			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx=pm.currentTransaction();
			try
			{
				tx.begin();
				long ido = nextval ();
				long tuplasInsertadas = sqlPedido.registrarPedido(pm, (int) ido, fechaEsperada, fechaEntrega, evaluacionCantidad, evaluacionCalidad, calificacion,finalizado,NITProveedor);
				tx.commit();

				log.trace ("Inserción del contenedor " + id + ": " + tuplasInsertadas + " tuplas insertadas");

				return new Pedido(id,fechaEsperada,fechaEntrega,evaluacionCantidad,evaluacionCalidad,calificacion, finalizado,NITProveedor);
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

		else
		{
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx=pm.currentTransaction();

			tx.begin();
			long tuplasInsertadas = sqlPedido.registrarPedido(pm, id, fechaEsperada, fechaEntrega, evaluacionCantidad, evaluacionCalidad, calificacion, finalizado, NITProveedor);
			tx.commit();

			log.trace ("Inserción de contenedor: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
			if(tuplasInsertadas == 0){
				return null;
			}
			else{
				return new Pedido(10, fechaEsperada, fechaEntrega, evaluacionCantidad, evaluacionCalidad, calificacion, finalizado, NITProveedor);
			}
		}

	}

	public Pedido actualizarPedido(int id, java.util.Date fechaEsperada,java.util.Date fechaEntrega, String evaluacionCantidad, String evaluacionCalidad,int calificacion, int finalizado, int NITProveedor) 
	{PersistenceManager pm = pmf.getPersistenceManager();
	Transaction tx=pm.currentTransaction();
	try
	{
		tx.begin();
		long tuplasInsertadas = sqlPedido.actualizarPedido(pm,id , fechaEsperada, fechaEntrega, evaluacionCantidad, evaluacionCalidad, calificacion,finalizado,NITProveedor);
		tx.commit();

		log.trace ("Inserción del contenedor " + id + ": " + tuplasInsertadas + " tuplas insertadas");

		return new Pedido(id,fechaEsperada,fechaEntrega,evaluacionCantidad,evaluacionCalidad,calificacion, finalizado,NITProveedor);
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






	public List<Sucursal> darSucursal ()
	{
		return sqlSucursal.darSucursales(pmf.getPersistenceManager());
	}

	public List<Contenedor> darContenedor ()
	{
		return sqlContenedor.darContenedores(pmf.getPersistenceManager());
	}
	
	public List<Pedido> darPedido ()
	{
		return sqlPedido.darPedidos(pmf.getPersistenceManager());
	}



	/* ****************************************************************
	 * 			Requerimiento 6
	 *****************************************************************/


	public VODescuentodelxporciento registrarPromocionDDX(int porcentaje) {

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextval ();
			long tuplasInsertadas = sqlPromocionPorcentaje.registrarPromocionDDX(pm,id,porcentaje);
			tx.commit();

			log.trace ("Inserción de Promocion " + id +": " + tuplasInsertadas + " tuplas insertadas");

			return new Descuentodelxporciento(id, porcentaje);
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



	//---------------------------------------------------------------------------------------------------
	public Pague1llevesegundoaxporciento registrarPromocionP1L2AX(int porcentaje) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextval ();
			long tuplasInsertadas = sqlPromocionPorcentaje.registrarPromocionP1L2AX(pm,id,porcentaje);
			tx.commit();

			log.trace ("Inserción de Promocion " + id +": " + tuplasInsertadas + " tuplas insertadas");

			return new Pague1llevesegundoaxporciento(id, porcentaje);
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
	//------------------------------


	public Estante registrarEstante(int idEstante, int nivelReabastecimiento, int idSucursal) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();

			long tuplasInsertadas = sqlEstante.registrarEstante(pm, idEstante, nivelReabastecimiento,idSucursal);
			tx.commit();

			log.trace ("Inserción de estante: " + idEstante + ": " + tuplasInsertadas + " tuplas insertadas");


			if(tuplasInsertadas == 0){
				return null;
			}
			else{
				return new Estante(idEstante, nivelReabastecimiento,idSucursal);
			}
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

	public Promocion registrarPromocion(int idPromocion, String descr, String Precio , int idSucursal) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();

			long tuplasInsertadas = sqlPromocion.registrarPromocion(pm, idPromocion, descr,Precio,idSucursal);
			tx.commit();

			log.trace ("Inserción de Promocion: " + idPromocion + ": " + tuplasInsertadas + " tuplas insertadas");


			if(tuplasInsertadas == 0){
				return null;
			}
			else{
				return new Promocion(idPromocion, descr,Precio,idSucursal);
			}
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

	//---------------------------------------------------------------------------------------------------
	public Paguexcantidadllevey registrarPromocionPXCLY(int x, int y) {

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextval ();
			long tuplasInsertadas = sqlPromocionUnidadProducto.registrarPromocionPXCLY(pm,id,x,y);
			tx.commit();

			log.trace ("Inserción de Promocion " + id +": " + tuplasInsertadas + " tuplas insertadas");

			return new Paguexcantidadllevey(id, x,y);
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
	public Paguexunidadesllevey registrarPromocionPXULY(int x, int y) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextval ();
			long tuplasInsertadas = sqlPromocionUnidadProducto.registrarPromocionPXULY(pm,id,x,y);
			tx.commit();

			log.trace ("Inserción de Promocion " + id +": " + tuplasInsertadas + " tuplas insertadas");

			return new Paguexunidadesllevey(id, x,y);
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


	public long eliminarPromocion(long idPromocion) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlPromocion.eliminarPromocion(pm,idPromocion);
			tx.commit();

			log.trace ("Inserción de Promocion " + idPromocion+": " + tuplasInsertadas + " tuplas insertadas");
			return tuplasInsertadas;
		}
		catch (Exception e)
		{

			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return 0;
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
	public  long quitarProductosDeEstante(int volumenNuevo,String codigoDeBarras){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasElim =sqlProducto.quitarProductosDeEstante(pm,volumenNuevo,codigoDeBarras);
			tx.commit();

			log.trace ("Eliminando el numero de productos del estante: " + codigoDeBarras );

			return tuplasElim;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return 0;
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

	public void devolverProducto(String codigoDeBarras)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlProducto.devolverProducto(pm,codigoDeBarras);
			tx.commit();

			log.trace ("Devolviendo El producto :  " + codigoDeBarras );


		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

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
	public List<Producto> busquedaProducto(String codigoDeBarras,String nombre,int opcion) {

		//Estante
		if(opcion == 0)
		{
			return sqlProducto.buscarCodigoEstante(pmf.getPersistenceManager(),nombre);
		}
		//En algun Carrito
		else{
			return sqlProducto.buscarCodigo(pmf.getPersistenceManager(),codigoDeBarras);
		}

	}

	public List<Producto> busquedaCarrito(long idCarrito) {
		return sqlProducto.buscarCarrito(pmf.getPersistenceManager(),idCarrito);

	}


	public void pagarCarrito(long idCarrito) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlProducto.pagarCarrito(pm,idCarrito);
			tx.commit();

			log.trace ("pagando el carrito :  " + idCarrito );


		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

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


	public void recogerProductos() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlProducto.recogerProductos(pm,1); // los productos abandonados tienen idCarrito 1
			tx.commit();

			log.trace ("recogiendo los productos" );


		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

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
	public CarritoDeCompras abandonarCarrito(int idCarrito)
	{
		String codigoDeBarras = "";
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long p = sqlCarritoDeCompras.devolverProducto(pm,idCarrito);
			sqlProducto.abandonarCarrito(pm, idCarrito, codigoDeBarras);
			tx.commit();

			log.trace ("Abandonando el carrito :  " + idCarrito );
			if(p == 0)
			{
				return null;
				
			}
			else{
				return new CarritoDeCompras( idCarrito , 0 , "1" ,1);
			}

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
	public long consolidacionPedidosProveedor( int id, java.util.Date fechaEsperada,java.util.Date fechaEntrega,String evaluacionCantidad, String evaluacionCalidad, int calificacion, int finalizado,int NITProveedor) {

		return sqlPedido.consolidacionPedidosProveedor(pmf.getPersistenceManager(),id, fechaEsperada, fechaEntrega, evaluacionCantidad, evaluacionCalidad, calificacion, finalizado, NITProveedor);


	}


	public List<Pedido> darPedidos() {
		return sqlPedido.darPedidos(pmf.getPersistenceManager());
	}
	public List<uniandes.isis2304.superAndes.negocio.Consulta1> Consulta1() {

		return sqlRFC1.consulta1(pmf.getPersistenceManager());

	}
	public List<uniandes.isis2304.superAndes.negocio.Consulta2> Consulta2() {

		return sqlRFC2.consulta2(pmf.getPersistenceManager());

	}
	public List<uniandes.isis2304.superAndes.negocio.Consulta3> Consulta3() {
		return sqlRFC3.consulta3(pmf.getPersistenceManager());
	}

	public List<Producto> Consulta4(String r) {
		// TODO Auto-generated method stub
		return sqlRFC4.consulta4(pmf.getPersistenceManager(),r);
	}
	public List<uniandes.isis2304.superAndes.negocio.Consulta5> Consulta5() {
		return sqlRFC5.consulta5(pmf.getPersistenceManager());
	}
	public List<uniandes.isis2304.superAndes.negocio.Consulta6> Consulta6(String fechaInicial, String fechaFinal) throws ParseException {
		return sqlRFC6.consulta6(pmf.getPersistenceManager(), fechaInicial,fechaFinal);
	}
	public List<uniandes.isis2304.superAndes.negocio.Consulta7> Consulta7(String unidadTiempo, String tipoProducto) {
		return sqlRFC7.consulta7(pmf.getPersistenceManager(), unidadTiempo, tipoProducto);
	}
	public List<uniandes.isis2304.superAndes.negocio.Consulta8> Consulta8(String idSucursal) {
		return sqlRFC8.consulta8(pmf.getPersistenceManager(), idSucursal);
	}


	public List<Proveedor> darProveedor(String nombre) {
		return sqlProveedor.darProveedor(pmf.getPersistenceManager(),nombre);
	}

	public long eliminarProveedor(int nit) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		long r = 0;
		try
		{
			tx.begin();		
			long tuplasEliminadas = sqlProveedor.eliminarProveedor(pm,nit);
			tx.commit();

			log.trace ("Eliminado Proveedor" + nit +": " );
			r = tuplasEliminadas;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();

			}

			pm.close();

		}
		return r;

	}
	//Producto
	public long eliminarProducto(String codigoDeBarras) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		long r = 0;
		try
		{
			tx.begin();		
			long tuplasEliminadas = sqlProducto.eliminarProducto(pm,codigoDeBarras);
			tx.commit();

			log.trace ("Eliminado producto: " + codigoDeBarras +": " );
			r = tuplasEliminadas;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();

			}

			pm.close();

		}
		return r;



	}

	public long eliminarFactura(String NumFactura) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		long r = 0;
		try
		{
			tx.begin();		
			long tuplasEliminadas = sqlFactura.eliminarFactura(pm,NumFactura);
			tx.commit();

			log.trace ("Eliminado Factura: " + NumFactura +": " );
			r = tuplasEliminadas;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();

			}

			pm.close();

		}
		return r;



	}

	public long eliminarClienteNatural(int cedula) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		long r = 0;
		try
		{
			tx.begin();		
			long tuplasEliminadas = sqlClienteNatural.eliminarClienteNatural(pm,cedula);
			tx.commit();

			log.trace ("Eliminado cliente" + cedula +": " );
			r = tuplasEliminadas;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();

			}

			pm.close();

		}
		return r;

	}
	public long eliminarSucursal(int id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		long r = 0;
		try
		{
			tx.begin();		
			long tuplasEliminadas = sqlSucursal.eliminarSucursal(pm, id);
			tx.commit();

			log.trace ("Eliminado sucursal" + id +": " );
			r = tuplasEliminadas;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();

			}

			pm.close();

		}
		return r;

	}

	public long eliminarContenedor(int id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		long r = 0;
		try
		{
			tx.begin();		
			long tuplasEliminadas = sqlContenedor.eliminarContenedor(pm, id);
			tx.commit();

			log.trace ("Eliminado contenedor" + id +": " );
			r = tuplasEliminadas;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();

			}

			pm.close();

		}
		return r;

	}
	public long eliminarPedido(int id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		long r = 0;
		try
		{
			tx.begin();		
			long tuplasEliminadas = sqlPedido.eliminarPedido(pm, id);
			tx.commit();

			log.trace ("Eliminado pedido" + id +": " );
			r = tuplasEliminadas;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();

			}

			pm.close();

		}
		return r;

	}


	public long eliminarClienteEmpresa(String NIT) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		long r = 0;
		try
		{
			tx.begin();		
			long tuplasEliminadas = sqlClienteEmpresa.eliminarClienteEmpresa(pm,NIT);
			tx.commit();

			log.trace ("Eliminado cliente" + NIT +": " );
			r = tuplasEliminadas;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();

			}

			pm.close();

		}
		return r;

	}



	public List<Estante> darEstantes()
	{
		return sqlEstante.darEstantes(pmf.getPersistenceManager());
	}

	public long eliminarEstante(long id) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		long r = 0;
		try
		{
			tx.begin();		
			long tuplasEliminadas = sqlEstante.eliminarEstante(pm,id);
			tx.commit();

			log.trace ("Eliminado Estante: " + id +": " );
			r = tuplasEliminadas;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();			
			}			
			pm.close();			
		}
		return r;	
	}

	/* ****************************************************************
	 * 			Limpiar Super Andes
	 *****************************************************************/


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



















}
