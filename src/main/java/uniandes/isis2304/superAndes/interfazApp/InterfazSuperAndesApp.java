
package uniandes.isis2304.superAndes.interfazApp;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superAndes.negocio.Factura;
import uniandes.isis2304.superAndes.negocio.FacturaProducto;
import uniandes.isis2304.superAndes.negocio.Pedido;
import uniandes.isis2304.superAndes.negocio.Producto;
import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.VOCarritoDeCompras;
import uniandes.isis2304.superAndes.negocio.VOCategoria;
import uniandes.isis2304.superAndes.negocio.VOCliente;
import uniandes.isis2304.superAndes.negocio.VOClienteEmpresa;
import uniandes.isis2304.superAndes.negocio.VOClienteNatural;
import uniandes.isis2304.superAndes.negocio.VOContenedor;
import uniandes.isis2304.superAndes.negocio.VODescuentodelxporciento;
import uniandes.isis2304.superAndes.negocio.VOEstante;
import uniandes.isis2304.superAndes.negocio.VOPaguexunidadesllevey;
import uniandes.isis2304.superAndes.negocio.VOPedido;
import uniandes.isis2304.superAndes.negocio.VOProducto;
import uniandes.isis2304.superAndes.negocio.VOPromocion;
import uniandes.isis2304.superAndes.negocio.VOProveedor;
import uniandes.isis2304.superAndes.negocio.VOSucursal;
import uniandes.isis2304.superAndes.negocio.VOTipoProducto;


@SuppressWarnings("serial")

public class InterfazSuperAndesApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazSuperAndesApp.class.getName());

	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociación a la clase principal del negocio.
	 */
	private SuperAndes superAndes;

	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
	/**
	 * Objeto JSON con la configuración de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Menú de la aplicación
	 */
	private JMenuBar menuBar;

	/**
	 * Producto
	 */
	private Producto producto;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazSuperAndesApp( )
	{
		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gráfica
		configurarFrame ( );
		if (guiConfig != null) 	   
		{
			crearMenu( guiConfig.getAsJsonArray("menuBar") );
		}

		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
		superAndes = new SuperAndes (tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos ( );

		setLayout (new BorderLayout());
		add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
		add( panelDatos, BorderLayout.CENTER );        
	}

	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "SuperAndes App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * Método para configurar el frame principal de la aplicación
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			log.info ( "Se aplica configuración por defecto" );			
			titulo = "SuperAndes APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
			titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation (50,50);
		setResizable( true );
		setBackground( Color.WHITE );

		setTitle( titulo );
		setSize ( ancho, alto);        
	}

	/**
	 * Método para crear el menú de la aplicación con base em el objeto JSON leído
	 * Genera una barra de menú y los menús con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los menùs deseados
	 */
	private void crearMenu(  JsonArray jsonMenu )
	{    	
		// Creación de la barra de menús
		menuBar = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	
				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject(); 
				String lb =   jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem( lb );
				mItem.addActionListener( this );
				mItem.setActionCommand(event);

				menu.add(mItem);
			}       
			menuBar.add( menu );
		}        
		setJMenuBar ( menuBar );	
	}

	/* ****************************************************************
	 * 			Requerimiento 1
	 *****************************************************************/

	public void registrarProveedor( )
	{
		try 
		{

			String nombre = JOptionPane.showInputDialog (this, "Nombre del proveedor?", "Registrar Proveedor", JOptionPane.QUESTION_MESSAGE);
			if (nombre != null)
			{
				VOProveedor tb = superAndes.registrarProveedor(nombre) ;
				if (tb == null)
				{
					throw new Exception ("No se pudo crear un proveedor con nombre: " + nombre);
				}
				String resultado = "En adicionarProveedor\n\n";
				resultado += "proveedor adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void listarProveedor( )
	{
		try 
		{
			List <VOProveedor> lista = superAndes.darVOProveedor();

			String resultado = "En listaProveedor";
			resultado +=  "\n" + listarProveedores(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	public List<Producto> listarProducto()
	{
		try 
		{
			List <Producto> lista = superAndes.darVOProducto();

			return lista;

		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);

			return null;
		}
	}

	public Producto buscarPorCodigoDeBarras(String codigo)
	{
		Producto produ = null;
		List <Producto> lista = listarProducto();

		for (int i = 0; i < lista.size(); i++) {
			String codigoProducto = lista.get(i).getCodigoDeBarras();
			if( codigoProducto.equals(codigo))
			{
				produ = lista.get(i);
			}		
		}


		return produ;

	}

	private String listarProveedores(List<VOProveedor> lista) 
	{
		String resp = "Los Proveedores existentes son:\n";
		int i = 1;
		for (VOProveedor tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}
	private String listarProducto(List<VOProducto> lista) 
	{
		String resp = "Los Proveedores existentes son:\n";
		int i = 1;
		for (VOProducto tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}



	/* ****************************************************************
	 * 			Requerimiento 2
	 *****************************************************************/

	//codigo de barras mayor a 30, ids menores a 20

	public void registrarProducto( )
	{
		try 
		{


			String codigoDeBarras = JOptionPane.showInputDialog (this, "Codigo de barras del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE);
			String nombre = JOptionPane.showInputDialog (this, "Nombre del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE);
			String marca = JOptionPane.showInputDialog (this, "Marca del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE);
			float precioUnitario = Float.parseFloat(JOptionPane.showInputDialog (this, "Precio Unitario del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE));
			String presentacion = JOptionPane.showInputDialog (this, "Presentacion del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE);
			float precioPorUnidad = Float.parseFloat(JOptionPane.showInputDialog (this, "Precio Por Unidad del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE));
			float cantidadEnLaPresentacion = Float.parseFloat(JOptionPane.showInputDialog (this, "Cantidad de producto En La Presentacion del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE));
			String unidadesDeMedida = JOptionPane.showInputDialog (this, "Unidades De Medida del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE);
			String especificacionesDeEmpacado = JOptionPane.showInputDialog (this, "Especificaciones De Empacado del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE);
			long IDPedido = Long.parseLong(JOptionPane.showInputDialog (this, "ID del Pedido relacionado con este Producto(Si no tiene ninguno coloque 0)?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE));
			long IDSucursal = Long.parseLong(JOptionPane.showInputDialog (this, "ID de la sucursal relacionada con este Producto(Si no tiene ninguno deje el campo vacio)?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE));
			long IDContenedor = Long.parseLong(JOptionPane.showInputDialog (this, "ID de la Contenedor relacionada con este Producto(Si no tiene ninguno deje el campo vacio)?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE));
			int enStock = Integer.parseInt(JOptionPane.showInputDialog (this, "Producto en stock?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE));
			int volumen = Integer.parseInt(JOptionPane.showInputDialog (this, "Cantidad de Productos?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE));

			float nivelDeReorden = enStock/100;



			if (nombre != null && codigoDeBarras != null && marca != null && presentacion != null  && unidadesDeMedida!= null && especificacionesDeEmpacado != null )
			{ 

				Producto tb = superAndes.registrarProducto(codigoDeBarras,nombre,marca,precioUnitario,presentacion, precioPorUnidad,cantidadEnLaPresentacion,unidadesDeMedida,especificacionesDeEmpacado,nivelDeReorden, IDPedido, IDSucursal, IDContenedor,Long.parseLong("1"),enStock,volumen,Long.parseLong("1")) ;

				if (tb == null)
				{
					throw new Exception ("No se pudo crear un producto : " + nombre);
				}
				String resultado = "En adicionarProducto\n\n";
				resultado += "Producto adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
			String numCat = "si";
			while(numCat == "si")
			{

				String nombreCategoria = JOptionPane.showInputDialog (this, "Categoria a la que pertenece el producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE);
				String perecedero1 = (JOptionPane.showInputDialog (this, "Es la categoria perecedera?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE));
				char perecedero =perecedero1.charAt(0);
				long tp = registrarCategoria( nombreCategoria,  perecedero,  codigoDeBarras );
				String numTip = "si";
				while(numTip == "si")
				{
					String nombreTipo  = JOptionPane.showInputDialog (this, "nombre del Tipo de producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE);
					String metodoAlmac = (JOptionPane.showInputDialog (this, "metodo de almacenamiento?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE));
					long idCategoria = tp;
					long idContenedor = 2;
					registrarTipoProducto( nombreTipo,  metodoAlmac,  idCategoria,idContenedor );
					numTip = JOptionPane.showInputDialog (this, "Quiere añadir un tipo de producto(mas) a esta categoria = (si o no)", "Registrar Producto", JOptionPane.QUESTION_MESSAGE);
				}
				numCat = JOptionPane.showInputDialog (this, "Quiere añadir una categoria(mas) = (si o no)", "Registrar Producto", JOptionPane.QUESTION_MESSAGE);

			}
		} 
		catch (Exception e) 
		{

			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	public long registrarCategoria(String nombreCategoria, char perecedero, String codigoDeBarras )
	{
		VOCategoria tb = superAndes.registrarCategoria(nombreCategoria,perecedero,codigoDeBarras) ;
		try 
		{


			if (tb == null)
			{
				throw new Exception ("No se pudo crear una Categoria con nombre: " + nombreCategoria);
			}
			String resultado = "Categoria adicionada exitosamente: " + tb;
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);



		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
		return tb.getId();
	}
	public void registrarTipoProducto(String nombreTipo, String metodoAlmac, long idCategoria, long idContenedor )
	{
		VOTipoProducto tb = superAndes.registrarTipo(nombreTipo,metodoAlmac,idCategoria,idContenedor) ;
		try 
		{


			if (tb == null)
			{
				throw new Exception ("No se pudo crear un tipo con nombre: " + nombreTipo);
			}
			String resultado = "Categoria adicionada exitosamente: " + tb;
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);



		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}



	}

	/* ****************************************************************
	 * 			RF3
	 *****************************************************************/
	// la cedulo no tiene que estar
	public void registrarCliente( )
	{
		try 
		{

			String tipoCliente = JOptionPane.showInputDialog (this, "Tipo Cliente? 1= natural, 2=empresa ", "Registrar Cliente", JOptionPane.QUESTION_MESSAGE);

			if (tipoCliente.equalsIgnoreCase("1"))
			{ registrarClienteNatural();
			}
			else if (tipoCliente.equalsIgnoreCase("2"))
			{
				registrarClienteEmpresarial();
			} else {
				int idCliente = Integer.parseInt(JOptionPane.showInputDialog (this, "id del cliente?", "Registrar Cliente", JOptionPane.QUESTION_MESSAGE));
				int puntosDeCompra = Integer.parseInt(JOptionPane.showInputDialog (this, "Puntos de compra?", "Registrar Cliente", JOptionPane.QUESTION_MESSAGE));
				String nitCliente = JOptionPane.showInputDialog (this, "nit del cliente?", "Registrar Cliente", JOptionPane.QUESTION_MESSAGE);
				int cedulaCliente = Integer.parseInt(JOptionPane.showInputDialog (this, "cedula cliente?", "Registrar Cliente", JOptionPane.QUESTION_MESSAGE));

				VOCliente tb = superAndes.registrarCliente(idCliente, puntosDeCompra, nitCliente, cedulaCliente);
				if (tb == null)
				{
					throw new Exception ("No se pudo crear un cliente con cedula: " + cedulaCliente);
				}
				String resultado = "En adicionarCliente\n\n";
				resultado += "cliente adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void registrarClienteNatural( )
	{
		try 
		{
			int cedula = Integer.parseInt(JOptionPane.showInputDialog (this, "cedula del cliente?", "Registrar Cliente", JOptionPane.QUESTION_MESSAGE));
			String nombre = JOptionPane.showInputDialog (this, "nombre del cliente?", "Registrar Cliente", JOptionPane.QUESTION_MESSAGE);
			String email = JOptionPane.showInputDialog (this, "email del cliente?", "Registrar Cliente", JOptionPane.QUESTION_MESSAGE);

			if ( nombre != null && email != null) {

				VOClienteNatural tb = superAndes.registrarClienteNatural(cedula, nombre, email);
				if (tb == null)
				{
					throw new Exception ("No se pudo crear un cliente natural con nombre: " + cedula);
				}
				String resultado = "En adicionarClienteNatural\n\n";
				resultado += "cliente natural adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void registrarClienteEmpresarial( )
	{
		try 
		{

			String NIT = JOptionPane.showInputDialog (this, "NIT del cliente?", "Registrar Cliente", JOptionPane.QUESTION_MESSAGE);
			String direccion = JOptionPane.showInputDialog (this, "direccion del cliente?", "Registrar Cliente", JOptionPane.QUESTION_MESSAGE);

			if ( NIT != null && direccion != null) {

				VOClienteEmpresa tb = superAndes.registrarClienteEmpresa(NIT, direccion);
				if (tb == null)
				{
					throw new Exception ("No se pudo crear un cliente empresarial con nombre: " + NIT);
				}
				String resultado = "En adicionarClienteEmpresarial\n\n";
				resultado += "cliente empresarial adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/* ****************************************************************
	 * 			RF4
	 *****************************************************************/

	public void registrarSucursal( )
	{
		try 
		{
			int id = Integer.parseInt(JOptionPane.showInputDialog (this, "id de la sucursal?", "Registrar Sucursal", JOptionPane.QUESTION_MESSAGE));
			String nombre = JOptionPane.showInputDialog (this, "nombre de la sucursal", "Registrar Sucursal", JOptionPane.QUESTION_MESSAGE);
			String ciudad = JOptionPane.showInputDialog (this, "ciudad de la sucursal?", "Registrar Sucursal", JOptionPane.QUESTION_MESSAGE);
			String direccion = JOptionPane.showInputDialog (this, "direccion de la sucursal?", "Registrar Sucursal", JOptionPane.QUESTION_MESSAGE);
			String segmentacionDeMercado = JOptionPane.showInputDialog (this, "segmentacion de mercado de la sucursal?", "Registrar Sucursal", JOptionPane.QUESTION_MESSAGE);
			String tamanioInstalacion = JOptionPane.showInputDialog (this, "tamaño de la sucursal?", JOptionPane.QUESTION_MESSAGE);
			int NITSupermercado = Integer.parseInt(JOptionPane.showInputDialog (this, "nit del supermercado al que pertenece?", "Registrar Sucursal", JOptionPane.QUESTION_MESSAGE));

			if ( nombre != null && ciudad != null&& direccion != null&& segmentacionDeMercado != null&& tamanioInstalacion != null) {

				VOSucursal tb = superAndes.registrarSucursal(id, nombre, ciudad, direccion, segmentacionDeMercado, tamanioInstalacion, NITSupermercado);
				if (tb == null)
				{
					throw new Exception ("No se pudo crear una sucursal de nombre: " + nombre);
				}
				String resultado = "En adicionarSucursal\n\n";
				resultado += "sucursal adicionada exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	/* ****************************************************************
	 * 			RF5
	 *****************************************************************/
	public void registrarContenedor( )
	{
		try 
		{
			int id = Integer.parseInt(JOptionPane.showInputDialog (this, "id del contenedor?", "Registrar Contenedor", JOptionPane.QUESTION_MESSAGE));
			int capacidadPeso = Integer.parseInt(JOptionPane.showInputDialog (this, "capacidad peso del contenedor?", "Registrar Contenedor", JOptionPane.QUESTION_MESSAGE));
			int capacidadVolumen = Integer.parseInt(JOptionPane.showInputDialog (this, "capacidad Volumen del Contenedor", "Registrar Contenedor", JOptionPane.QUESTION_MESSAGE));
			String unidadesPeso = JOptionPane.showInputDialog (this, "unidades peso?", "Registrar Contenedor", JOptionPane.QUESTION_MESSAGE);
			String unidadesVolumen = JOptionPane.showInputDialog (this, "unidades volumen?", "Registrar Contenedor", JOptionPane.QUESTION_MESSAGE);
			int idBodegaSucursal = Integer.parseInt(JOptionPane.showInputDialog (this, "id de la sucursal asociada?", "Registrar Contenedor", JOptionPane.QUESTION_MESSAGE));

			if ( unidadesPeso != null && unidadesVolumen != null) {

				VOContenedor tb = superAndes.registrarContenedor(id, capacidadVolumen, capacidadPeso, unidadesPeso, unidadesVolumen,idBodegaSucursal);
				if (tb == null)
				{
					throw new Exception ("No se pudo crear el contenedor de id: " + id);
				}
				String resultado = "En adicionarContenedor\n\n";
				resultado += "contenedor adicionada exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}



	public void listarCliente( )
	{
		try 
		{
			List <VOCliente> lista = superAndes.darVOCliente();

			String resultado = "En listaProveedor";
			resultado +=  "\n" + listarClientes(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void listarSucursal( )
	{
		try 
		{
			List <VOSucursal> lista = superAndes.darVOSucursal();

			String resultado = "En listaProveedor";
			resultado +=  "\n" + listarSucursales(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarSucursales(List<VOSucursal> lista) 
	{
		String resp = "Las sucursales existentes son:\n";
		int i = 1;
		for (VOSucursal tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	private String listarClientes(List<VOCliente> lista) 
	{
		String resp = "Los Clientes existentes son:\n";
		int i = 1;
		for (VOCliente tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}
	private String listarClientesNaturales(List<VOClienteNatural> lista) 
	{
		String resp = "Los Proveedores existentes son:\n";
		int i = 1;
		for (VOClienteNatural tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	} 
	private String listarClientesEmpresa(List<VOClienteEmpresa> lista) 
	{
		String resp = "Los Proveedores existentes son:\n";
		int i = 1;
		for (VOClienteEmpresa tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}




	/* ****************************************************************
	 * 			Requerimiento 6
	 *****************************************************************/

	//Estante mayor a 30, sucursal menor a 20

	public void registrarEstanteASucursal( )
	{
		try 
		{

			//Nivel reabastecimiento
			long idEstante = Long.parseLong(JOptionPane.showInputDialog (this, "Id del estante?", "Registrar Proveedor", JOptionPane.QUESTION_MESSAGE));

			List<Producto> prodTots = listarProducto();
			List<Producto> prodDeEstante = new LinkedList<Producto>();
			for (Producto tb : prodTots)
			{
				if(tb.getIDContenedor() == idEstante)
				{
					prodDeEstante.add(tb);
				}
			}
			float nivelReabastecimiento = prodDeEstante.size( );
			if(nivelReabastecimiento == 0)
			{
				panelDatos.actualizarInterfaz("Nivel de reabastecimiento es 0, porfavor traiga productos de bodega");
			}
			//Fin nivel abastecimiento
			System.out.println(nivelReabastecimiento);
			long idSucursal = Long.parseLong(JOptionPane.showInputDialog (this, "Sucursal a la cual se asociara el estante?", "Registrar Proveedor", JOptionPane.QUESTION_MESSAGE));

			VOEstante tb = superAndes.registrarEstante(idEstante,nivelReabastecimiento,idSucursal) ;
			if (tb == null)
			{
				throw new Exception ("No se pudo crear estante con id: " + idEstante);
			}
			String resultado = "En adicionarEstante\n\n";
			resultado += "Estante adicionado exitosamente: " + tb;
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);

		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			Requerimiento 7
	 *****************************************************************/

	public void registrarPromocion( )
	{
		try 
		{

			int p = Integer.parseInt(JOptionPane.showInputDialog (this, "Indique el numero del tipo de promocion que desea agregar:"
					+ "\n (1)Pague x unidades lleve y"
					+ "\n (2)Descuento del x porciento"
					+ "\n (3)Pague x cantidad lleve y"
					+ "\n (4)Pague 1 lleve segundo a x porciento"
					//+ "\n (5)Paquete de productos por un precio menor", "Registrar Promocion"
					, JOptionPane.QUESTION_MESSAGE));

			if(p == 1 || p == 3)
			{
				int x= Integer.parseInt(JOptionPane.showInputDialog (this, "Ingese la cantidad de unidades/productos a pagar", "Registrar Promocion", JOptionPane.QUESTION_MESSAGE));
				int y= Integer.parseInt(JOptionPane.showInputDialog (this, "Ingese la cantidad de unidades/productos a recibir", "Registrar Promocion", JOptionPane.QUESTION_MESSAGE));
				VOPaguexunidadesllevey tb =superAndes.registrarPromocionCantidad(x,y,p) ;

				if (tb == null)
				{
					throw new Exception ("No se pudo crear Promocion");
				}
				String resultado = "Promocion adicionada exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else if(p == 2 || p == 4)
			{

				int porcentaje = Integer.parseInt(JOptionPane.showInputDialog (this, "Ingrese el porcentaje(Solo el numero)", "Registrar Promocion", JOptionPane.QUESTION_MESSAGE));

				VODescuentodelxporciento tb =superAndes.registrarPromocionPorcentaje(porcentaje, p) ;	
				if (tb == null)
				{
					throw new Exception ("No se pudo crear promocion");
				}
				String resultado = "Promocion adicionada exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);  			
			}     		
		} 
		catch (Exception e) 
		{
			//    			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			Requerimiento 8
	 *****************************************************************/
	public void eliminarPromocion( )
	{
		try 

		{
			String idPromocionStr = JOptionPane.showInputDialog (this, "Id de la promocion?", "Borrar Promocion", JOptionPane.QUESTION_MESSAGE);
			if (idPromocionStr != null)
			{
				long idPromocion = Long.valueOf (idPromocionStr);
				superAndes.eliminarPromocion (idPromocion);

				String resultado = "Promocion eliminada con exito\n\n";

				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//		e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			RF9
	 *****************************************************************/
	public void registrarPedido( )
	{


		try 
		{
			JOptionPane.showMessageDialog(this, "Recuerda que para hacer un pedido debes conocer el codigo de barras del producto");
			String codigoDeBarras = JOptionPane.showInputDialog (this, "codigo de barras del producto?", "Registrar Pedido", JOptionPane.QUESTION_MESSAGE);
			int id = 0;
			java.util.Date fechaEsperada =  (java.util.Date) new SimpleDateFormat("dd/MM/yyyy").parse(JOptionPane.showInputDialog (this, "fecha esperada de entrega? (DD/MM/YYYY)", "Registrar Pedido", JOptionPane.QUESTION_MESSAGE));
			java.util.Date  fechaEntrega = new Date( 0/1/0001);
			String evaluacionCantidad = "0";
			String evaluacionCalidad = "0";
			int calificacion = 0;
			int finalizado = 0 ;//Integer.parseInt(JOptionPane.showInputDialog (this, "se ha finalizado el pedido?", "Registrar Pedido", JOptionPane.QUESTION_MESSAGE));
			int NITProveedor = Integer.parseInt(JOptionPane.showInputDialog (this, "NIT del proveedor?", "Registrar Pedido", JOptionPane.QUESTION_MESSAGE));

			if ( evaluacionCalidad != null && evaluacionCantidad != null) {

				Producto producto = buscarPorCodigoDeBarras(codigoDeBarras);

				VOPedido tb = superAndes.registrarPedido(id, fechaEsperada, fechaEntrega, evaluacionCantidad, evaluacionCalidad,calificacion,finalizado,NITProveedor);
				//	VOProducto produ = superAndes.registrarProducto(codigoDeBarras, producto.getNombre(), producto.getMarca(), producto.getPrecioUnitario(), producto.getPresentacion(), producto.getPrecioPorUnidad(), producto.getCantidadEnLaPresentacion(), producto.getUnidadesDeMedida(), producto.getEspecificacionesDeEmpacado(), producto.getNivelDeReorden(), id, producto.getIDSucursal(), producto.getIDContenedor(), producto.getEnStock());


				if (tb == null)
				{
					throw new Exception ("No se pudo crear el pedido de id: " + id);
				}
				String resultado = "En adicionarPedido\n\n";
				resultado += "pedido adicionada exitosamente: " + tb;
				//		resultado += "producto adicionada exitosamente: " + produ;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	/* ****************************************************************
	 * 			RF10
	 *****************************************************************/
	public void finalizarPedido( )
	{


		try 
		{
			int id = Integer.parseInt(JOptionPane.showInputDialog (this, "id del pedido que llegó?", "Finalizar Pedido", JOptionPane.QUESTION_MESSAGE));
			java.util.Date fechaEsperada = new Date( 0/1/0001);
			java.util.Date  fechaEntrega =  (java.util.Date ) new SimpleDateFormat("dd/MM/yyyy").parse(JOptionPane.showInputDialog (this, "fecha en la que te entregaron? (DD/MM/YYYY)", "Finalizar Pedido", JOptionPane.QUESTION_MESSAGE));
			String evaluacionCantidad = JOptionPane.showInputDialog (this, "evaluacion cantidad?", "Registrar Pedido", JOptionPane.QUESTION_MESSAGE);
			String evaluacionCalidad = JOptionPane.showInputDialog (this, "evaluacion calidad ?", "Registrar Pedido", JOptionPane.QUESTION_MESSAGE);
			int calificacion = Integer.parseInt(JOptionPane.showInputDialog (this, "calificacion del pedido?", "Registrar Pedido", JOptionPane.QUESTION_MESSAGE));
			int finalizado = 0; //Integer.parseInt(JOptionPane.showInputDialog (this, "se ha finalizado el pedido?", "Registrar Contenedor", JOptionPane.QUESTION_MESSAGE));
			int NITProveedor =0;

			if ( evaluacionCalidad != null && evaluacionCantidad != null) {


				VOPedido tb = superAndes.actualizarPedido(id, fechaEsperada, fechaEntrega, evaluacionCantidad, evaluacionCalidad,calificacion,finalizado,NITProveedor);

				if (tb == null)
				{
					throw new Exception ("No se pudo crear el pedido de id: " + id);
				}
				String resultado = "En EntegarPedido\n\n";
				resultado += "pedido entregado exitosamente: " + tb;
				//		resultado += "producto adicionada exitosamente: " + produ;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			Requerimiento 11
	 *****************************************************************/

	public void registrarVentaDeProducto( )
	{
		try 
		{


			String numeroDeFactura = JOptionPane.showInputDialog (this, "Numero de la factura(nuevo)?", "Registrar Factura", JOptionPane.QUESTION_MESSAGE);
			java.util.Date fecha =  (java.util.Date) new SimpleDateFormat("dd/MM/yyyy").parse(JOptionPane.showInputDialog (this, "fecha de la factura? (DD/MM/YYYY)", "Registrar Pedido", JOptionPane.QUESTION_MESSAGE));   		
			long idCliente = Long.parseLong(JOptionPane.showInputDialog (this, "ID del cliente asociado?", "Registrar Factura", JOptionPane.QUESTION_MESSAGE));
			String codigoDeBarras = JOptionPane.showInputDialog (this, "Codigo de barras de producto ?", "Registrar Factura", JOptionPane.QUESTION_MESSAGE);
			int numProd = Integer.parseInt(JOptionPane.showInputDialog (this, "Numero de productos ?", "Registrar Factura", JOptionPane.QUESTION_MESSAGE));

			Factura tb = superAndes.registrarFactura(numeroDeFactura,fecha,idCliente) ;
			for (int i = 1; i<numProd;i++)
			{


				FacturaProducto fp = superAndes.registrarFacturaProd(numeroDeFactura,codigoDeBarras) ;
				if (fp == null)
				{
					throw new Exception ("No se pudo crear factura con numero: " + numeroDeFactura);
				}
				String resultado = "En adicionarProveedor\n\n";
				resultado += "proveedor adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);


			}
			if (tb == null)
			{
				throw new Exception ("No se pudo crear factura con numero: " + numeroDeFactura);
			}
			String resultado = "En adicionarProveedor\n\n";
			resultado += "proveedor adicionado exitosamente: " + tb;
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);


		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			Requerimiento 12
	 *****************************************************************/

	public void registrarCarritoACliente( )
	{
		try 
		{
			JOptionPane.showMessageDialog(this, "Recuerda que para pedir un carrito debes ser un cliente registrado");
			String NIT = JOptionPane.showInputDialog (this, "NIT del cliente si es empresarial? 0 de lo contrario", "Solicitar Carrito", JOptionPane.QUESTION_MESSAGE);
			int cedula = Integer.parseInt(JOptionPane.showInputDialog (this, "cedula del cliente si es natural? 0 de lo contrario", "Solicitar Carrito", JOptionPane.QUESTION_MESSAGE));
			long idCarrito= 0; // mas adelante se pone uno autogenerado
			int usado = 1; // como se esta registrando se asigna a un cliente y esta en uso 

			if ( NIT != null ) {

				VOCarritoDeCompras tb = superAndes.registrarCarritoDeCompras(idCarrito, usado, NIT, cedula);
				if (tb == null)
				{
					throw new Exception ("No se puede asignar un carrito si no esta registrado como cliente ");
				}
				String resultado = "En adicionarCarritoDeCompras\n\n";
				resultado += "carrito de compras adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}

			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	/* ****************************************************************
	 * 			RF 13
	 *****************************************************************/

	public void adicionarProductoACarrito( )
	{
		try 
		{


			String codigoDeBarras = JOptionPane.showInputDialog (this, "Codigo de barras del Producto?", "Adicionar Producto Carrito", JOptionPane.QUESTION_MESSAGE);
			int numProd = Integer.parseInt(JOptionPane.showInputDialog (this, "Numero de productos ?", "Adicionar Producto Carrito", JOptionPane.QUESTION_MESSAGE));
			long idCarrito = Long.parseLong(JOptionPane.showInputDialog (this, "Id del carrito al cual adicionar producto ?", "Adicionar Producto Carrito", JOptionPane.QUESTION_MESSAGE));


			List <Producto> productosConCodigo = superAndes.busquedaProducto(codigoDeBarras,"",1);

			Producto prod = productosConCodigo.get(0);
			int prodMenosDeEstante =prod.getVolumen() - numProd;

			String cod = nextval ();
			//Añade Producto a carrito
			Producto pd = superAndes.registrarProducto(cod,prod.getNombre(),prod.getMarca(),prod.getPrecioUnitario(),prod.getPresentacion(), prod.getPrecioPorUnidad(),prod.getCantidadEnLaPresentacion(),"gr",prod.getEspecificacionesDeEmpacado(), prod.getNivelDeReorden(), prod.getIDPedido(), prod.getIDSucursal(), prod.getIDContenedor(),1,prod.getEnStock(),numProd, idCarrito) ;
			//Quita el numero de productos del estante
			superAndes.quitarProductosDeEstante(prodMenosDeEstante, codigoDeBarras) ;

			if (pd == null)
			{
				throw new Exception ("No se pudo crear factura con numero: " + codigoDeBarras);
			}
			String resultado = "En adicionarProveedor\n\n";
			resultado += "proveedor adicionado exitosamente: " + pd;
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);






		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String nextval ()
	{
		long resp =(int) (Math.random() * 60) + 20;;
		return "0000"+resp;
	}
	/* ****************************************************************
	 * 			RF 14
	 *****************************************************************/

	public void devolverUnProductoDelCarrito( )
	{
		try 
		{


			String codigoDeBarras = JOptionPane.showInputDialog (this, "Codigo de barras del Producto que desea devolver?", "Devolver Producto a Estante", JOptionPane.QUESTION_MESSAGE);
			long idCarrito = Long.parseLong(JOptionPane.showInputDialog (this, "Id del carrito al cual devolver producto ?", "Devolver Producto a Estante", JOptionPane.QUESTION_MESSAGE));

			List <Producto> productosConCodigo = superAndes.busquedaProducto(codigoDeBarras,"",1);
			Producto prod = productosConCodigo.get(0);


			List <Producto> estanteDeProductosConNombre = superAndes.busquedaProducto("",prod.getNombre(),0);
			Producto prodEst = estanteDeProductosConNombre.get(0);



			int prodMasDeEstante =prodEst.getVolumen() + prod.getVolumen();
			superAndes.quitarProductosDeEstante(prodMasDeEstante, prodEst.getCodigoDeBarras()) ;
			//Añade el numero de productos del estante
			superAndes.devolverProducto(codigoDeBarras) ;

			//Añade Productos al estante correspondiente





			String resultado  = "";
			resultado += "Producto Devuelto Con exito" ;
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);

		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			Requerimiento 15
	 *****************************************************************/

	public void pagarCarrito( )
	{
		try 
		{
			String numeroDeFactura = JOptionPane.showInputDialog (this, "Numero de la factura(nuevo)?", "Pagar carrito", JOptionPane.QUESTION_MESSAGE);
			java.util.Date fecha =  (java.util.Date) new SimpleDateFormat("dd/MM/yyyy").parse(JOptionPane.showInputDialog (this, "fecha de la factura? (DD/MM/YYYY)", "Pagar carrito", JOptionPane.QUESTION_MESSAGE));   		
			long idCliente = Long.parseLong(JOptionPane.showInputDialog (this, "ID del cliente asociado?", "Pagar Carrito", JOptionPane.QUESTION_MESSAGE));
			long idCarrito = Long.parseLong(JOptionPane.showInputDialog (this, "id de tu carrito", "PagarCarrito", JOptionPane.QUESTION_MESSAGE));
			int usado = 0; // como se esta dejando de usar el estado de usado cambia a 0
			List<Producto> productos = superAndes.busaquedaCarrito(idCarrito); // todos los productos de ese carrito


			if ( idCarrito != 0 ) {

				Factura tb = superAndes.registrarFactura(numeroDeFactura,fecha,idCliente) ;
				for (int i = 1; i<productos.size();i++)
				{
					Producto productoActual = productos.get(1);
					FacturaProducto fp = superAndes.registrarFacturaProd(numeroDeFactura,productoActual.getCodigoDeBarras()) ;
					if (fp == null)
					{
						throw new Exception ("No se pudo crear factura con numero: " + numeroDeFactura);
					}
					String resultado = "En pagarCarrito\n\n";
					resultado += "proveedor adicionado exitosamente: " + tb;
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);


				}
				
				superAndes.pagarCarrito(idCarrito);
				
				if (tb == null)
				{
					throw new Exception ("No se pudo crear factura con numero: " + numeroDeFactura);
				}
				
				String resultado = "En p\n\n";
				resultado += "carrito de compras pagado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}

			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			RF 16
	 *****************************************************************/

	public void abandonarCarrito( )
	{
		try 
		{


			long idCarrito = Long.parseLong(JOptionPane.showInputDialog (this, "Id del carrito a abandonar?", "Devolver Producto a Estante", JOptionPane.QUESTION_MESSAGE));

			superAndes.abandonarCarrito(idCarrito) ;

			String resultado  = "";
			resultado += "Carrito Abandonado, ahora esta disponible para otros clientes" ;
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);

		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	/* ****************************************************************
	 * 			RF 17
	 *****************************************************************/

	public void recogerProductos( )
	{
		try 
		{

			JOptionPane.showMessageDialog(this, "se recogeran todos los productos y se pondran en sus estantes correspondientes");
			
			superAndes.recogerProductos() ;

			String resultado  = "";
			resultado += "se han recogido los productos" ;
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);

		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/* ****************************************************************
   	 * 			RF 18
   	 *****************************************************************/

    public void consolidarPedidosAProveedores( )
    {
    	try 
    	{
    		for(Pedido p : superAndes.darPedidos()){
    			
    			List<Pedido> pedidosDeEsteProveedor = superAndes.busquedaPedidosPorProveedor(p.getNITProveedor());
    			if(pedidosDeEsteProveedor.size() > 1)
    			{
    				
    			}
    		}
    		

            		String resultado  = "";
            		resultado += "Pedidos Consolidados Exitosamente" ;
        			resultado += "\n Operación terminada";
        			panelDatos.actualizarInterfaz(resultado);
        			
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
	/* ****************************************************************
	 * 			Consulta 1
	 *****************************************************************/




	/**
	 * RFC1
	 */

	public void Consulta1( )
	{
		try 
		{
			List <uniandes.isis2304.superAndes.negocio.Consulta1> lista = superAndes.consulta1();

			String resultado = "En Consulta1";
			resultado +=  "\n" + listarConsulta1(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarConsulta1(List<uniandes.isis2304.superAndes.negocio.Consulta1> lista) 
	{
		String resp = "Resultado Consulta 1:\n";
		int i = 1;
		for (uniandes.isis2304.superAndes.negocio.Consulta1 tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}
	/* ****************************************************************
	 * 			Consulta 2
	 *****************************************************************/
	public void Consulta2( )
	{
		try 
		{
			List <uniandes.isis2304.superAndes.negocio.Consulta2> lista = superAndes.consulta2();

			String resultado = "Resultado Consulta 2";
			resultado +=  "\n" + listarConsulta2(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarConsulta2(List<uniandes.isis2304.superAndes.negocio.Consulta2> lista) 
	{
		String resp = "Resultado Consulta 2:\n";
		int i = 1;
		for (uniandes.isis2304.superAndes.negocio.Consulta2 tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}
	/* ****************************************************************
	 * 			Consulta 3
	 *****************************************************************/
	public void Consulta3( )
	{
		try 
		{
			List <uniandes.isis2304.superAndes.negocio.Consulta3> lista = superAndes.consulta3();

			String resultado = "En listaProveedor";
			resultado +=  "\n" + listarConsulta3(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarConsulta3(List<uniandes.isis2304.superAndes.negocio.Consulta3> lista) 
	{
		String resp = "Resultado Consulta 3:\n";
		int i = 1;
		for (uniandes.isis2304.superAndes.negocio.Consulta3 tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}
	/* ****************************************************************
	 * 			Consulta 4
	 *****************************************************************/

	public void Consulta4( )
	{
		try 
		{

			JOptionPane.showMessageDialog(this, "A continuacion ponga los valores por los cuales quiere buscar el producto, si no quiere buscar por ese valor coloque 0");
			String codigoDeBarras = JOptionPane.showInputDialog (this, "Codigo de barras del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE);
			String nombre =JOptionPane.showInputDialog (this, "Nombre del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE);
			String marca =JOptionPane.showInputDialog (this, "Marca del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE);
			float precioUnitario =Float.parseFloat(JOptionPane.showInputDialog (this, "Precio Unitario del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE));
			String presentacion =JOptionPane.showInputDialog (this, "Presentacion del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE);
			float precioPorUnidad =Float.parseFloat(JOptionPane.showInputDialog (this, "Precio Por Unidad del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE));
			float cantidadEnLaPresentacion =Float.parseFloat(JOptionPane.showInputDialog (this, "Cantidad de producto En La Presentacion del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE));
			String unidadesDeMedida =JOptionPane.showInputDialog (this, "Unidades De Medida del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE);
			String especificacionesDeEmpacado =JOptionPane.showInputDialog (this, "Especificaciones De Empacado del Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE);
			long IDPedido =Long.parseLong(JOptionPane.showInputDialog (this, "ID del Pedido relacionado con este Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE));
			long IDSucursal =Long.parseLong(JOptionPane.showInputDialog (this, "ID de la sucursal relacionada con este Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE));
			long IDContenedor =Long.parseLong(JOptionPane.showInputDialog (this, "ID de la Contenedor relacionada con este Producto?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE));
			int enStock =Integer.parseInt(JOptionPane.showInputDialog (this, "Producto en stock?", "Registrar Producto", JOptionPane.QUESTION_MESSAGE));
			float nivelDeReorden = enStock/100;

			String r="WHERE ";

			if(!(codigoDeBarras.equals("0")))
			{
				r +="CodigoDeBarras = '"+codigoDeBarras+ "' ";
			}
			if(!nombre.equals("0"))
			{
				r +="nombre = '"+nombre + "' ";
			}
			if(!marca.equals("0"))
			{
				r +="marca = '"+marca+ "' ";
			}
			if(precioUnitario != 0)
			{
				r +="precioUnitario = "+precioUnitario+ " ";
			}
			if(!presentacion.equals("0"))
			{
				r +="presentacion = '"+presentacion+ "' ";
			}
			if(precioPorUnidad != 0)
			{
				r +="precioPorUnidad = "+precioPorUnidad+ " ";
			}
			if(cantidadEnLaPresentacion != 0)
			{
				r +="cantidadEnLaPresentacion = "+cantidadEnLaPresentacion+ " ";
			}
			if(!unidadesDeMedida.equals("0"))
			{
				r +="unidadesDeMedida = '"+unidadesDeMedida+ "' ";
			}
			if(!especificacionesDeEmpacado.equals("0"))
			{
				r +="especificacionesDeEmpacado = '"+especificacionesDeEmpacado+ "' ";
			}
			if(IDPedido != 0)
			{
				r +="IDPedido = "+IDPedido+ " ";
			}
			if(IDSucursal != 0)
			{
				r +="IDSucursal = "+IDSucursal+ " ";
			}
			if(IDContenedor != 0)
			{
				r +="IDContenedor = "+IDContenedor+ " ";
			}
			if(enStock != 0)
			{
				r +="enStock = "+enStock +" nivelDeReorden = " +nivelDeReorden+ " ";
			}

			List <Producto> lista = superAndes.consulta4(r);

			String resultado = "En listaProveedor";
			resultado +=  "\n" + listarConsulta4(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarConsulta4(List<Producto> lista) 
	{
		String resp = "Resultado Consulta 3:\n";
		int i = 1;
		for (Producto tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}
	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de SuperAndes
	 */
	public void mostrarLogSuperAndes ()
	{
		mostrarArchivo ("superAndes.log");
	}

	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}

	/**
	 * Limpia el contenido del log de superAndes
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogSuperAndes ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("superAndes.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de superAndes ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de superAndes
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			long eliminados [] = superAndes.limpiarSuperAndes();

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";

			resultado += eliminados [0] + " Tipos de Productos eliminados\n";

			resultado += "\nLimpieza terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}



	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-SuperAndesJDO.pdf");
	}

	/**
	 * Muestra el modelo conceptual de SuperAndes
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual SuperAndes.pdf");
	}

	/**
	 * Muestra el esquema de la base de datos de SuperAndes
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD SuperAndes.pdf");
	}

	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaSuperAndes.sql");
	}

	/**
	 * Muestra la arquitectura de referencia para SuperAndes
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}

	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}

	/**
	 * Muestra la información acerca del desarrollo de esta apicación
	 */
	public void acercaDe ()
	{
		String resultado = "\n\n ************************************\n\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: SuperAndes Uniandes\n";
		resultado += " * @version 1.0\n";


		panelDatos.actualizarInterfaz(resultado);		
	}


	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/
	/**
	 * Genera una cadena de caracteres con la lista de los tipos de producto recibida: una línea por cada tipo de producto
	 * @param lista - La lista con los tipos de producto
	 * @return La cadena con una líea para cada tipo de producto recibido
	 */
	private String listarTiposProducto(List<VOTipoProducto> lista) 
	{
		String resp = "Los tipos de producto existentes son:\n";
		int i = 1;
		for (VOTipoProducto tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	/**
	 * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
	 * @param e - La excepción recibida
	 * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y superAndes.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
			//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
	/**
	 * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
	 * Invoca al método correspondiente según el evento recibido
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
		try 
		{
			Method req = InterfazSuperAndesApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por línea de comandos
	 */
	public static void main( String[] args )
	{
		try
		{
			String log4jConfPath = "./src/main/resources/log4j.properties";
			PropertyConfigurator.configure(log4jConfPath);
			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			InterfazSuperAndesApp interfaz = new InterfazSuperAndesApp( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}
