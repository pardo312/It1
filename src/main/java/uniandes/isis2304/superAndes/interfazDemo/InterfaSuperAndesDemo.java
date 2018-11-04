/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: SuperAndes Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.superAndes.interfazDemo;

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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superAndes.negocio.CarritoDeCompras;
import uniandes.isis2304.superAndes.negocio.ClienteEmpresa;
import uniandes.isis2304.superAndes.negocio.ClienteNatural;
import uniandes.isis2304.superAndes.negocio.Contenedor;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.Sucursal;
import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.VOClienteEmpresa;
import uniandes.isis2304.superAndes.negocio.VOClienteNatural;
import uniandes.isis2304.superAndes.negocio.VOContenedor;
import uniandes.isis2304.superAndes.negocio.Estante;
import uniandes.isis2304.superAndes.negocio.Factura;
import uniandes.isis2304.superAndes.negocio.FacturaProducto;
import uniandes.isis2304.superAndes.negocio.Pedido;
import uniandes.isis2304.superAndes.negocio.Producto;
import uniandes.isis2304.superAndes.negocio.Promocion;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.VOEstante;
import uniandes.isis2304.superAndes.negocio.VOPedido;
import uniandes.isis2304.superAndes.negocio.VOPromocion;
import uniandes.isis2304.superAndes.negocio.VOProveedor;
import uniandes.isis2304.superAndes.negocio.VOSucursal;

/**
 * Clase principal de la interfaz
 * 
 * @author Germán Bravo
 */
@SuppressWarnings("serial")

public class InterfaSuperAndesDemo extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfaSuperAndesDemo.class.getName());
	
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigDemo.json"; 
	
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

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
    /**
     * Construye la ventana principal de la aplicación. <br>
     * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
     */
    public InterfaSuperAndesDemo( )
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
	 * 			Métodos para la configuración de la interfaz
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
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
	 * 			Demos de Proveedor
	 *****************************************************************/
    public void demoProveedorExitoso( )
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			String nombreProveedor = "Juan Manuel";
			boolean errorProveedor = false;
			Proveedor proveedor = superAndes.registrarProveedor(nombreProveedor,0) ;
			if (proveedor == null)
			{
				proveedor = superAndes.darProveedor(nombreProveedor);
				errorProveedor = true;
			}
			List <VOProveedor> lista = superAndes.darVOProveedor();
			long tbEliminados = superAndes.eliminarProveedor((int) proveedor.getNIT());
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Proveedores\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorProveedor)
			{
				resultado += "*** Exception creando Proveedor !!\n";
				resultado += "*** Es probable que ese proveedor ya existiera y hay restricción de UNICIDAD sobre el nombre del proveedor\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "Adicionado el proveedor con nombre: " + nombreProveedor + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n " + listarProveedores(lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += tbEliminados + " Proveedores eliminados\n";
			
			List <VOProveedor> listaDespues = superAndes.darVOProveedor();
			resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
			resultado +=  "\n " + listarProveedores(listaDespues);
			resultado += "\n Demo terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void demoProveedorNoExitoso( )
    {
    	try 
    	{
    		
			String nombreProveedor = "Pepe Sierra Nevada";
			boolean errorProveedor = false;
			Proveedor proveedor = superAndes.registrarProveedor(nombreProveedor,1) ;
			if (proveedor == null)
			{
				errorProveedor = true;
			}
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Proveedores\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorProveedor)
			{
				resultado += "Adicionado el proveedor con nombre: " + nombreProveedor + "\n";
				resultado += "\n\n************ Estado de la base de datos antes de la operacion ************ \n";
				
				List <VOProveedor> lista = superAndes.darVOProveedor();
				resultado +=  "\n " + listarProveedores(lista)+  "\n\n ";
				
				resultado += "\n\n************ Error Al insertar************ \n\n";				
				
				resultado += "*** Exception creando proveedor !!\n";
				resultado += "*** Es probable que ese proveedor ya existiera y hay restricción de UNICIDAD sobre el nombre del proveedor\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
				
				
				List <VOProveedor> listaDespues = superAndes.darVOProveedor();
				resultado += "\n\n************ Despues de el registro queda asi************ \n";
				resultado +=  "\n " + listarProveedores(listaDespues);
				
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				List <VOProveedor> lista = superAndes.darVOProveedor();
				long tbEliminados = superAndes.eliminarProveedor((int) proveedor.getNIT());
				resultado += "Adicionado el proveedor con nombre: " + nombreProveedor + "\n";
				resultado += "\n\n************ Ejecutando la demo ************ \n";
				resultado +=  "\n " + listarProveedores(lista);
				resultado += "\n\n************ Limpiando la base de datos ************ \n";
				resultado += tbEliminados + " Proveedores eliminados\n";
				
				List <VOProveedor> listaDespues = superAndes.darVOProveedor();
				resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
				resultado +=  "\n " + listarProveedores(listaDespues);
				resultado += "\n Demo terminada";
	   
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
    
    /* ****************************************************************
	 * 			Demos de Clientes
	 *****************************************************************/
    public void demoClienteNaturalExitoso( )
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
    		int cedula = nextvalCedula();		
    		String nombre = "CLIENTE TEST"	;
    		String email = "cliente@test.com";
    		boolean errorCliente = false;
			ClienteNatural cliente = superAndes.registrarClienteNatural(cedula, nombre, email, 1) ;
			if (cliente == null)
			{
				cliente = superAndes.darClienteNatural(cedula);
				errorCliente = true;
			}
			List <VOClienteNatural> lista = superAndes.darVOClienteNatural();
			long tbEliminados = superAndes.eliminarClienteNatural(cliente.getCedula());
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de cliente natural \n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorCliente)
			{
				resultado += "*** Exception creando Cliente !!\n";
				resultado += "*** Es probable que ese cliente ya existiera y hay restricción de UNICIDAD sobre el id del cliente Natural\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "Adicionado el Cliente natural con nombre: " + nombre + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n " + listarClienteNatural(lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += tbEliminados + " Cliente eliminados\n";
			
			List <VOClienteNatural> listaDespues = superAndes.darVOClienteNatural();
			resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
			resultado +=  "\n " + listarClienteNatural(listaDespues);
			resultado += "\n Demo terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    		
    public void demoClienteNaturalNoExitoso( )
    {
    	try 
    	{
    		int cedula = 1000;		
    		String nombre = "CLIENTE TEST"	;
    		String email = "cliente@test.com";
    		boolean errorCliente = false;
    		
			VOClienteNatural ClienteNatural = superAndes.registrarClienteNatural(cedula, nombre, email, 1);
			if (ClienteNatural == null)
			{
				errorCliente = true;
			}
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Clientes\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorCliente)
			{
				resultado += "Adicionado el cliente con cedula: " + cedula + "\n";
				resultado += "\n\n************ Estado de la base de datos antes de la operacion ************ \n";
				
				List <ClienteNatural> lista = superAndes.darClientesNaturales();
				resultado +=  "\n " + listarClientesNaturales(lista)+  "\n\n ";
				
				resultado += "\n\n************ Error Al insertar************ \n\n";				
				
				resultado += "*** Exception creando cliente !!\n";
				resultado += "*** Es probable que ese cliente ya existiera y hay restricción de UNICIDAD sobre el nombre del cliente\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
				
				
				List <ClienteNatural> listaDespues = superAndes.darClientesNaturales();
				resultado += "\n\n************ Despues de el registro queda asi************ \n";
				resultado +=  "\n " + listarClientesNaturales(listaDespues);
				
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				List <ClienteNatural> lista = superAndes.darClientesNaturales();
				long tbEliminados = superAndes.eliminarClienteNatural( ClienteNatural.getCedula());
				resultado += "Adicionado el cliente con cedula: " + cedula + "\n";
				resultado += "\n\n************ Ejecutando la demo ************ \n";
				resultado +=  "\n " + listarClientesNaturales(lista);
				resultado += "\n\n************ Limpiando la base de datos ************ \n";
				resultado += tbEliminados + " clientes eliminados\n";
				
				List <ClienteNatural> listaDespues = superAndes.darClientesNaturales();
				resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
				resultado +=  "\n " + listarClientesNaturales(listaDespues);
				resultado += "\n Demo terminada";
	   
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
    
    public void demoClienteEmpresarialExitoso( )
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
    		String NIT = "10030";
    		String direccion = "direccion test";	
			boolean errorCliente = false;
			ClienteEmpresa cliente = superAndes.registrarClienteEmpresa(NIT, direccion, 1) ;
			if (cliente == null)
			{
				cliente = superAndes.darClienteEmpresa(NIT);
				errorCliente = true;
			}
			List <VOClienteEmpresa> lista = superAndes.darVOClienteEmpresarial();
			long tbEliminados = superAndes.eliminarClienteEmpresa(cliente.getNIT());
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Clientes \n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorCliente)
			{
				resultado += "*** Exception creando cliente !!\n";
				resultado += "*** Es probable que ese cliente ya existiera y hay restricción de UNICIDAD sobre el nit del cliente \n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "Adicionado el cliente con nit: " + NIT + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n " + listarClienteEmpresa(lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += tbEliminados + " cliente eliminados\n";
			
			List <VOClienteEmpresa> listaDespues = superAndes.darVOClienteEmpresarial();
			resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
			resultado +=  "\n " + listarClienteEmpresa(listaDespues);
			resultado += "\n Demo terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void demoClienteEmpresarialNoExitoso( )
    {
    	try 
    	{
    		
    		String NIT = "00001";
    		String direccion = "direccion 1";	
			boolean errorCliente = false;
			ClienteEmpresa cliente = superAndes.registrarClienteEmpresa(NIT, direccion, 1) ;
			if (cliente == null)
			{
				errorCliente = true;
			}
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
						String resultado = "Demo de creación y listado de Clientes\n\n";
						resultado += "\n\n************ Generando datos de prueba ************ \n";
						if (errorCliente)
						{
							resultado += "Adicionado el cliente con nit: " + NIT + "\n";
							resultado += "\n\n************ Estado de la base de datos antes de la operacion ************ \n";
							
							List <ClienteEmpresa> lista = superAndes.darClientesEmpresariales();
							resultado +=  "\n " + listarClientesEmpresariales(lista)+  "\n\n ";
							
							resultado += "\n\n************ Error Al insertar************ \n\n";				
							
							resultado += "*** Exception creando cliente !!\n";
							resultado += "*** Es probable que ese cliente ya existiera y hay restricción de UNICIDAD sobre el nombre del cliente\n";
							resultado += "*** Revise el log de superAndes para más detalles\n";
							
							
							List <ClienteEmpresa> listaDespues = superAndes.darClientesEmpresariales();
							resultado += "\n\n************ Despues de el registro queda asi************ \n";
							resultado +=  "\n " + listarClientesEmpresariales(listaDespues);
							
							panelDatos.actualizarInterfaz(resultado);
						}
						else
						{
							List <ClienteEmpresa> lista = superAndes.darClientesEmpresariales();
							long tbEliminados = superAndes.eliminarClienteEmpresa(NIT);
							resultado += "Adicionado el cliente con nit: " + NIT + "\n";
							resultado += "\n\n************ Ejecutando la demo ************ \n";
							resultado +=  "\n " + listarClientesEmpresariales(lista);
							resultado += "\n\n************ Limpiando la base de datos ************ \n";
							resultado += tbEliminados + " clientes eliminados\n";
							
							List <ClienteEmpresa> listaDespues = superAndes.darClientesEmpresariales();
							resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
							resultado +=  "\n " + listarClientesEmpresariales(listaDespues);
							resultado += "\n Demo terminada";
				   
							panelDatos.actualizarInterfaz(resultado);
							
						}
						
					} 
			    	catch (Exception e) 
			    	{
//						e.printStackTrace();
						String resultado = generarMensajeError(e);
						panelDatos.actualizarInterfaz(resultado);
					}
    }

    /* ****************************************************************
  	 * 			Demos de sucursal
  	 *****************************************************************/
      public void demoSucursalExitoso( )
      {
      	try 
      	{
      		// Ejecución de la demo y recolección de los resultados
  			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
      		int id = 50;
      		String nombre = "TEST ";
      		String ciudad = "TEST";
      		String direccion = "DIRECCION";
      		String segmentacionDeMercado = "JOVENES";
      		String tamanioInstalacion = "GRANDE";
      		int NITSupermercado = 5;
      		boolean errorSucursal = false;
  			Sucursal sucursal = superAndes.registrarSucursal(id, nombre, ciudad, direccion, segmentacionDeMercado, tamanioInstalacion, NITSupermercado, 1);
  			if (sucursal == null)
  			{
  				sucursal = superAndes.darSucursal(id);
  				errorSucursal = true;
  			}
  			List <VOSucursal> lista = superAndes.darVOSucursal();
  			long tbEliminados = superAndes.eliminarSucursal((int)sucursal.getId());
  			
  			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
  			String resultado = "Demo de creación y listado de Sucursales \n\n";
  			resultado += "\n\n************ Generando datos de prueba ************ \n";
  			if (errorSucursal)
  			{
  				resultado += "*** Exception creando sucursal !!\n";
  				resultado += "*** Es probable que esa sucursal ya existiera y hay restricción de UNICIDAD sobre el id de la sucursal\n";
  				resultado += "*** Revise el log de superAndes para más detalles\n";
  			}
  			resultado += "Adicionado la sucursal con nombre: " + nombre + "\n";
  			resultado += "\n\n************ Ejecutando la demo ************ \n";
  			resultado +=  "\n " + listarSucursal(lista);
  			resultado += "\n\n************ Limpiando la base de datos ************ \n";
  			resultado += tbEliminados + " sucursales eliminados\n";
  			
  			List <VOSucursal> listaDespues = superAndes.darVOSucursal();
  			resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
  			resultado +=  "\n " + listarSucursal(listaDespues);
  			resultado += "\n Demo terminada";
     
  			panelDatos.actualizarInterfaz(resultado);
  		} 
      	catch (Exception e) 
      	{
//  			e.printStackTrace();
  			String resultado = generarMensajeError(e);
  			panelDatos.actualizarInterfaz(resultado);
  		}
      }
      
      		
      public void demoSucursalNoExitoso( )
      {
      	try 
        	{
      		int id = 50;
      		String nombre = "TEST ";
      		String ciudad = "TEST";
      		String direccion = "DIRECCION";
      		String segmentacionDeMercado = "JOVENES";
      		String tamanioInstalacion = "GRANDE";
      		int NITSupermercado = 5;
      		boolean errorSucursal = false;
  			
    			VOSucursal Sucursal = superAndes.registrarSucursal(id, nombre, ciudad, direccion, segmentacionDeMercado, tamanioInstalacion, NITSupermercado, 0);
    			if (Sucursal == null)
    			{
    				errorSucursal = true;
    			}
    			
    			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
    			String resultado = "Demo de creación y listado de Sucursales\n\n";
    			resultado += "\n\n************ Generando datos de prueba ************ \n";
    			if (errorSucursal)
    			{
    				resultado += "Adicionado la sucursal con id: " + id + "\n";
    				resultado += "\n\n************ Estado de la base de datos antes de la operacion ************ \n";
    				
    				List <Sucursal> lista = superAndes.darSucursales();
    				resultado +=  "\n " + listarSucursales(lista)+  "\n\n ";
    				
    				resultado += "\n\n************ Error Al insertar************ \n\n";				
    				
    				resultado += "*** Exception creando sucursal !!\n";
    				resultado += "*** Es probable que esa sucursal ya existiera y hay restricción de UNICIDAD sobre el id de la sucursal\n";
    				resultado += "*** Revise el log de superAndes para más detalles\n";
    				
    				
    				List <Sucursal> listaDespues = superAndes.darSucursales();
    				resultado += "\n\n************ Despues de el registro queda asi************ \n";
    				resultado +=  "\n " + listarSucursales(listaDespues);
    				
    				panelDatos.actualizarInterfaz(resultado);
    			}
    			else
    			{
    				List <Sucursal> lista = superAndes.darSucursales();
    				long tbEliminados = superAndes.eliminarSucursal( (int)Sucursal.getId());
    				resultado += "Adicionado la sucursal con id: " + id + "\n";
    				resultado += "\n\n************ Ejecutando la demo ************ \n";
    				resultado +=  "\n " + listarSucursales(lista);
    				resultado += "\n\n************ Limpiando la base de datos ************ \n";
    				resultado += tbEliminados + " sucursales eliminados\n";
    				
    				List <Sucursal> listaDespues = superAndes.darSucursales();
    				resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
    				resultado +=  "\n " + listarSucursales(listaDespues);
    				resultado += "\n Demo terminada";
    	   
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
    	 * 			Demos de contenedor
    	 *****************************************************************/
      
      public void demoContenedorExitoso( )
      {
      	try 
      	{
      		// Ejecución de la demo y recolección de los resultados
  			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
      		int id = 30;
      		int capacidadPeso = 100;
      		int capacidadVolumen = 200;
      		String unidadesPeso = "kg";
      		String unidadesVolumen = "metros";
      		int idBodegaSucursal = 9;
      		boolean errorContenedor = false;
  			Contenedor contenedor = superAndes.registrarContenedor(id, capacidadVolumen, capacidadPeso, unidadesPeso, unidadesVolumen, idBodegaSucursal, 1);
  			if (contenedor == null)
  			{
  				contenedor = superAndes.darContenedor(id);
  				errorContenedor = true;
  			}
  			List <VOContenedor> lista = superAndes.darVOContenedor();
  			long tbEliminados = superAndes.eliminarContenedor((int)contenedor.getId());
  			
  			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
  			String resultado = "Demo de creación y listado de contenedores \n\n";
  			resultado += "\n\n************ Generando datos de prueba ************ \n";
  			if (errorContenedor)
  			{
  				resultado += "*** Exception creando contenedor !!\n";
  				resultado += "*** Es probable que ese contenedor ya existiera y hay restricción de UNICIDAD sobre el id del contenedor \n";
  				resultado += "*** Revise el log de superAndes para más detalles\n";
  			}
  			resultado += "Adicionado el contenedor con id: " + id + "\n";
  			resultado += "\n\n************ Ejecutando la demo ************ \n";
  			resultado +=  "\n " + listarContenedor(lista);
  			resultado += "\n\n************ Limpiando la base de datos ************ \n";
  			resultado += tbEliminados + " contenedores eliminados\n";
  			
  			List <VOContenedor> listaDespues = superAndes.darVOContenedor();
  			resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
  			resultado +=  "\n " + listarContenedor(listaDespues);
  			resultado += "\n Demo terminada";
     
  			panelDatos.actualizarInterfaz(resultado);
  		} 
      	catch (Exception e) 
      	{
//  			e.printStackTrace();
  			String resultado = generarMensajeError(e);
  			panelDatos.actualizarInterfaz(resultado);
  		}
      }
      public void demoContenedorNoExitoso( )
      {
      	try 
        	{
      		int id = 30;
      		int capacidadPeso = 100;
      		int capacidadVolumen = 200;
      		String unidadesPeso = "kg";
      		String unidadesVolumen = "metros";
      		int idBodegaSucursal = 9;
      		boolean errorContenedor = false;
  			
    			VOContenedor Contenedor = superAndes.registrarContenedor(id, capacidadVolumen, capacidadPeso, unidadesPeso, unidadesVolumen, idBodegaSucursal, 0) ;
    			if (Contenedor == null)
    			{
    				errorContenedor = true;
    			}
    			
    			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
    			String resultado = "Demo de creación y listado de Contenedor\n\n";
    			resultado += "\n\n************ Generando datos de prueba ************ \n";
    			if (errorContenedor)
    			{
    				resultado += "Adicionado el contenedor con id: " + id + "\n";
    				resultado += "\n\n************ Estado de la base de datos antes de la operacion ************ \n";
    				
    				List <Contenedor> lista = superAndes.darContenedores();
    				resultado +=  "\n " + listarContenedores(lista)+  "\n\n ";
    				
    				resultado += "\n\n************ Error Al insertar************ \n\n";				
    				
    				resultado += "*** Exception creando contenedor !!\n";
    				resultado += "*** Es probable que ese contenedor ya existiera y hay restricción de UNICIDAD sobre el id del contenedor \n";
    				resultado += "*** Revise el log de superAndes para más detalles\n";
    				
    				
    				List <Contenedor> listaDespues = superAndes.darContenedores();
    				resultado += "\n\n************ Despues de el registro queda asi************ \n";
    				resultado +=  "\n " + listarContenedores(listaDespues);
    				
    				panelDatos.actualizarInterfaz(resultado);
    			}
    			else
    			{
    				List <Contenedor> lista = superAndes.darContenedores();
    				long tbEliminados = superAndes.eliminarContenedor( (int)Contenedor.getId());
    				resultado += "Adicionado el contenedor con id: " + id + "\n";
    				resultado += "\n\n************ Ejecutando la demo ************ \n";
    				resultado +=  "\n " + listarContenedores(lista);
    				resultado += "\n\n************ Limpiando la base de datos ************ \n";
    				resultado += tbEliminados + " contenedores eliminadas\n";
    				
    				List <Contenedor> listaDespues = superAndes.darContenedores();
    				resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
    				resultado +=  "\n " + listarContenedores(listaDespues);
    				resultado += "\n Demo terminada";
    	   
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
    	 * 			Demos de registrar pedido
    	 *****************************************************************/
      
      public void demoRegistrarPedidoExitoso( )
      {
      	try 
      	{
      		// Ejecución de la demo y recolección de los resultados
  			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
      		
      		java.util.Date fechaEsperada =  new Date( 0/1/0001);
      		java.util.Date  fechaEntrega = new Date( 0/1/0001);
			String evaluacionCantidad = "0";
			String evaluacionCalidad = "0";
			int calificacion = 0;
			int finalizado = 0 ;//Integer.parseInt(JOptionPane.showInputDialog (this, "se ha finalizado el pedido?", "Registrar Pedido", JOptionPane.QUESTION_MESSAGE));
			int NITProveedor = 10019;
			int id = 10021;
			
			boolean errorPedido = false;
	  		
			Pedido pedido = superAndes.registrarPedido(id , fechaEsperada, fechaEntrega, evaluacionCantidad, evaluacionCalidad, calificacion, finalizado, NITProveedor,1);
  			
			
			if (pedido == null)
  			{
  				pedido = superAndes.darPedido(id);
  				errorPedido = true;
  			}
  			List <VOPedido> lista = superAndes.darVOPedido();
  			long tbEliminados = superAndes.eliminarPedido((int)pedido.getId());
  			
  			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
  			String resultado = "Demo de creación y listado de pedidos \n\n";
  			resultado += "\n\n************ Generando datos de prueba ************ \n";
  			if (errorPedido)
  			{
  				resultado += "*** Exception creando contenedor !!\n";
  				resultado += "*** Es probable que ese contenedor ya existiera y hay restricción de UNICIDAD sobre el id del contenedor \n";
  				resultado += "*** Revise el log de superAndes para más detalles\n";
  			}
  			resultado += "Adicionado el pedido con id: " + id + "\n";
  			resultado += "\n\n************ Ejecutando la demo ************ \n";
  			resultado +=  "\n " + listarPedido(lista);
  			resultado += "\n\n************ Limpiando la base de datos ************ \n";
  			resultado += tbEliminados + " pedidos eliminados\n";
  			
  			List <VOPedido> listaDespues = superAndes.darVOPedido();
  			resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
  			resultado +=  "\n " + listarPedido(listaDespues);
  			resultado += "\n Demo terminada";
     
  			panelDatos.actualizarInterfaz(resultado);
  		} 
      	catch (Exception e) 
      	{
//  			e.printStackTrace();
  			String resultado = generarMensajeError(e);
  			panelDatos.actualizarInterfaz(resultado);
  		}
      }
      
      public void demoRegistrarPedidoNoExitoso( )
      {
      	try 
        	{
      		java.util.Date fechaEsperada =  new Date( 0/1/0001);
      		java.util.Date  fechaEntrega = new Date( 0/1/0001);
			String evaluacionCantidad = "0";
			String evaluacionCalidad = "0";
			int calificacion = 0;
			int finalizado = 0 ;
			int NITProveedor = 10019;
			int id = 10021;
			boolean errorPedido = false;
  			
    			VOPedido Pedido = superAndes.registrarPedido(id, fechaEsperada, fechaEntrega, evaluacionCantidad, evaluacionCalidad, calificacion, finalizado, NITProveedor, 0) ;
    			if (Pedido == null)
    			{
    				errorPedido = true;
    			}
    			
    			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
    			String resultado = "Demo de creación y listado de Pedido\n\n";
    			resultado += "\n\n************ Generando datos de prueba ************ \n";
    			if (errorPedido)
    			{
    				resultado += "Adicionado el pedido con id: " + id + "\n";
    				resultado += "\n\n************ Estado de la base de datos antes de la operacion ************ \n";
    				
    				List <Pedido> lista = superAndes.darPedidos();
    				resultado +=  "\n " + listarPedidos(lista)+  "\n\n ";
    				
    				resultado += "\n\n************ Error Al insertar************ \n\n";				
    				
    				resultado += "*** Exception creando pedido !!\n";
    				resultado += "*** Es probable que ese pedido ya existiera y hay restricción de UNICIDAD sobre el id del contenedor \n";
    				resultado += "*** Revise el log de superAndes para más detalles\n";
    				
    				
    				List <Pedido> listaDespues = superAndes.darPedidos();
    				resultado += "\n\n************ Despues de el registro queda asi************ \n";
    				resultado +=  "\n " + listarPedidos(listaDespues);
    				
    				panelDatos.actualizarInterfaz(resultado);
    			}
    			else
    			{
    				List <Pedido> lista = superAndes.darPedidos();
    				long tbEliminados = superAndes.eliminarPedido( (int)Pedido.getId());
    				resultado += "Adicionado el pedido con id: " + id + "\n";
    				resultado += "\n\n************ Ejecutando la demo ************ \n";
    				resultado +=  "\n " + listarPedidos(lista);
    				resultado += "\n\n************ Limpiando la base de datos ************ \n";
    				resultado += tbEliminados + " pedidos eliminadas\n";
    				
    				List <Pedido> listaDespues = superAndes.darPedidos();
    				resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
    				resultado +=  "\n " + listarPedidos(listaDespues);
    				resultado += "\n Demo terminada";
    	   
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
	 * 			Demos de Producto
	 *****************************************************************/
    
		    /* ****************************************************************
		   	 * 			Demos Agregar
		   	 *****************************************************************/

    
    public void demoProductoExitoso( )
    {
    	try 
    	{
    		String codigoDeBarras = nextvalCodigoBarras();
			String nombre = "Parlantes";
			String marca = "Omega";
			float precioUnitario = 150000;
			String presentacion = "Individual";
			float precioPorUnidad = 150000;
			float cantidadEnLaPresentacion = 6;
			String unidadesDeMedida = "GR";
			String especificacionesDeEmpacado = "Regular";
			long IDPedido = 5;
			long IDPromocion = 3;
			long IDSucursal = 4;
			long IDContenedor = 16;
			int EnStock = 32;
			int volumen = 123;
			int IDCarrito = 0;
			float nivelDeReorden = EnStock/100;
			
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
						String resultado = "Demo de creación y listado de Productos\n\n";
						
			resultado += "\n\n************ Base de datos antes de Operacion: ************ \n";
			List <Producto> listaAntes = superAndes.darVOProducto();

			resultado +=  "\n " + listarProductos(listaAntes)+  "\n\n ";
			
			boolean errorProducto = false;
			Producto Producto = superAndes.registrarProducto(codigoDeBarras, nombre, marca, precioUnitario, presentacion, precioPorUnidad, cantidadEnLaPresentacion, unidadesDeMedida, especificacionesDeEmpacado, nivelDeReorden, IDPedido, IDSucursal, IDContenedor, IDPromocion, EnStock, volumen, IDCarrito);
			if (Producto == null)
			{
				errorProducto = true;
			}
			List <Producto> lista = superAndes.darVOProducto();
			long tbEliminados = superAndes.eliminarProducto(Producto.getCodigoDeBarras());
			
			
			
			
			
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorProducto)
			{
				resultado += "*** Exception creando Producto !!\n";
				resultado += "*** Es probable que ese Producto ya existiera y hay restricción de UNICIDAD sobre el nombre del Producto\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "Adicionado el Producto con nombre: " + codigoDeBarras + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n " + listarProductos(lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += tbEliminados + " Productos eliminados\n";
			
			List <Producto> listaDespues = superAndes.darVOProducto();
			resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
			resultado +=  "\n " + listarProductos(listaDespues);
			resultado += "\n Demo terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void demoProductoNoExitoso( )
    {
    	try 
    	{
    		String codigoDeBarras = "000005"; // Codigo de barras que ya existe
			String nombre = "Parlantes";
			String marca = "Omega";
			float precioUnitario = 150000;
			String presentacion = "Individual";
			float precioPorUnidad = 150000;
			float cantidadEnLaPresentacion = 6;
			String unidadesDeMedida = "GR";
			String especificacionesDeEmpacado = "Regular";
			long IDPedido = 5;
			long IDPromocion = 3;
			long IDSucursal = 4;
			long IDContenedor = 16;
			int EnStock = 32;
			int volumen = 123;
			int IDCarrito = 0;
			float nivelDeReorden = EnStock/100;
			
			
			boolean errorProducto = false;
			Producto Producto = superAndes.registrarProducto(codigoDeBarras, nombre, marca, precioUnitario, presentacion, precioPorUnidad, cantidadEnLaPresentacion, unidadesDeMedida, especificacionesDeEmpacado, nivelDeReorden, IDPedido, IDSucursal, IDContenedor, IDPromocion, EnStock, volumen, IDCarrito);
			if (Producto == null)
			{
				errorProducto = true;
			}
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Productos\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorProducto)
			{
				resultado += "Adicionado el Producto con nombre: " + codigoDeBarras + "\n";
				resultado += "\n\n************ Estado de la base de datos antes de la operacion ************ \n";
				
				List <Producto> lista = superAndes.darVOProducto();
				resultado +=  "\n " + listarProductos(lista)+  "\n\n ";
				
				resultado += "\n\n************ Error Al insertar************ \n\n";				
				
				resultado += "*** Exception creando Producto !!\n";
				resultado += "*** Es probable que ese Producto ya existiera y hay restricción de UNICIDAD sobre el nombre del Producto\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
				
				
				List <Producto> listaDespues = superAndes.darVOProducto();
				resultado += "\n\n************ Despues de el registro queda asi************ \n";
				resultado +=  "\n " + listarProductos(listaDespues);
				
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				List <Producto> lista = superAndes.darVOProducto();
				long tbEliminados = superAndes.eliminarProducto( Producto.getCodigoDeBarras());
				resultado += "Adicionado el Producto con nombre: " + codigoDeBarras + "\n";
				resultado += "\n\n************ Ejecutando la demo ************ \n";
				resultado +=  "\n " + listarProductos(lista);
				resultado += "\n\n************ Limpiando la base de datos ************ \n";
				resultado += tbEliminados + " Productos eliminados\n";
				
				List <Producto> listaDespues = superAndes.darVOProducto();
				resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
				resultado +=  "\n " + listarProductos(listaDespues);
				resultado += "\n Demo terminada";
	   
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
   
    private String nextvalCodigoBarras()
	{
		long resp =(int) (Math.random() * 100) + 21;;
		
			return "0000"+resp;
		
		
	}
    private int nextvalCedula()
   	{
   		int resp =(int) (Math.random() * 100) + 21;;
   		
   			return Integer.parseInt("10"+resp);
   		
   		
   	}
    private int nextvalID()
  	{
  		int resp =(int) (Math.random() * 100) + 21;;
  		
  			return resp;
  	}
    private int nextvalID2()
  	{
  		int resp =(int) (Math.random() * 20) + 1;;
  		
  			return resp;
  	}
      
    private String nextvalIdFactura()
   	{
   		int resp =(int) (Math.random() * 100) + 21;;
   		
   			return "20"+resp;
   		
   		
   	}
    
    /* ****************************************************************
	 * 			DEMO Estante
	 *****************************************************************/
    public void demoEstanteExitoso( )
    {
    	try 
    	{
    		int idEstante = nextvalID();
    		int nivelDeAbastecimiento = 8;
    		int idSucursal = 6;
			
			
			boolean errorEstante = false;
			VOEstante Estante = superAndes.registrarEstante(idEstante, nivelDeAbastecimiento, idSucursal);
			if (Estante == null)
			{
				errorEstante = true;
			}
			List <Estante> lista = superAndes.darEstantes();
			long tbEliminados = superAndes.eliminarEstante(Estante.getId());
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Estantes\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorEstante)
			{
				resultado += "*** Exception creando Estante !!\n";
				resultado += "*** Es probable que ese Estante ya existiera y hay restricción de UNICIDAD sobre el nombre del Estante\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "Adicionado el Estante con nombre: " + idEstante + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n " + listarEstantes(lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += tbEliminados + " Estantes eliminados\n";
			
			List <Estante> listaDespues = superAndes.darEstantes();
			resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
			resultado +=  "\n " + listarEstantes(listaDespues);
			resultado += "\n Demo terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void demoEstanteNoExitoso( )
    {
    	try 
    	{
    		int idEstante = 6;
    		int nivelDeAbastecimiento = 8;
    		int idSucursal = 5;
			
			
			boolean errorEstante = false;
			VOEstante Estante = superAndes.registrarEstante(idEstante, nivelDeAbastecimiento, idSucursal);
			if (Estante == null)
			{
				errorEstante = true;
			}
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Estantes\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorEstante)
			{
				resultado += "Adicionado el Estante con id: " + idEstante + "\n";
				resultado += "\n\n************ Estado de la base de datos antes de la operacion ************ \n";
				
				List <Estante> lista = superAndes.darEstantes();
				resultado +=  "\n " + listarEstantes(lista)+  "\n\n ";
				
				resultado += "\n\n************ Error Al insertar************ \n\n";				
				
				resultado += "*** Exception creando Estante !!\n";
				resultado += "*** Es probable que ese Estante ya existiera y hay restricción de UNICIDAD sobre el nombre del Estante\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
				
				
				List <Estante> listaDespues = superAndes.darEstantes();
				resultado += "\n\n************ Despues de el registro queda asi************ \n";
				resultado +=  "\n " + listarEstantes(listaDespues);
				
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				List <Estante> lista = superAndes.darEstantes();
				long tbEliminados = superAndes.eliminarEstante( Estante.getId());
				resultado += "Adicionado el Estante con nombre: " + idEstante + "\n";
				resultado += "\n\n************ Ejecutando la demo ************ \n";
				resultado +=  "\n " + listarEstantes(lista);
				resultado += "\n\n************ Limpiando la base de datos ************ \n";
				resultado += tbEliminados + " Estantes eliminados\n";
				
				List <Estante> listaDespues = superAndes.darEstantes();
				resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
				resultado +=  "\n " + listarEstantes(listaDespues);
				resultado += "\n Demo terminada";
	   
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
    
    /* ****************************************************************
   	 * 			DEMO Promocion 
   	 *****************************************************************/
    public void demoPromocionExitoso( )
    {
    	try 
    	{
    		int ID  = nextvalID();
    		String DESCRIPCION ="3 Xbox Por 2";
    		String PRECIOPROMOCION = "10000";
    		int idSucursal = 6;
			
			
			boolean errorPromocion = false;
			VOPromocion Promocion = superAndes.registrarPromocion(ID, DESCRIPCION, PRECIOPROMOCION,idSucursal);
			if (Promocion == null)
			{
				errorPromocion = true;
			}
			List <Promocion> lista = superAndes.darPromociones();
			long tbEliminados = superAndes.eliminarPromocion(Promocion.getId());
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Promociones\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorPromocion)
			{
				resultado += "*** Exception creando Promocion !!\n";
				resultado += "*** Es probable que ese Promocion ya existiera y hay restricción de UNICIDAD sobre el nombre del Promocion\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "Adicionado el Promocion con nombre: " + ID + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n " + listarPromociones(lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += tbEliminados + " Promociones eliminados\n";
			
			List <Promocion> listaDespues = superAndes.darPromociones();
			resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
			resultado +=  "\n " + listarPromociones(listaDespues);
			resultado += "\n Demo terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void demoPromocionNoExitoso( )
    {
    	try 
    	{
    		int ID  = 5;
    		String DESCRIPCION ="3 Xbox Por 2";
    		String PRECIOPROMOCION = "10000";
    		int idSucursal = 5;
			
			
    		boolean errorPromocion = false;
			VOPromocion Promocion = superAndes.registrarPromocion(ID, DESCRIPCION, PRECIOPROMOCION,idSucursal);
			if (Promocion == null)
			{
				errorPromocion = true;
			}
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Promociones\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorPromocion)
			{
				resultado += "Adicionado el Promocion con id: " + ID + "\n";
				resultado += "\n\n************ Estado de la base de datos antes de la operacion ************ \n";
				
				List <Promocion> lista = superAndes.darPromociones();
				resultado +=  "\n " + listarPromociones(lista)+  "\n\n ";
				
				resultado += "\n\n************ Error Al insertar************ \n\n";				
				
				resultado += "*** Exception creando Promocion !!\n";
				resultado += "*** Es probable que ese Promocion ya existiera y hay restricción de UNICIDAD sobre el nombre del Promocion\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
				
				
				List <Promocion> listaDespues = superAndes.darPromociones();
				resultado += "\n\n************ Despues de el registro queda asi************ \n";
				resultado +=  "\n " + listarPromociones(listaDespues);
				
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				List <Promocion> lista = superAndes.darPromociones();
				long tbEliminados = superAndes.eliminarPromocion( Promocion.getId());
				resultado += "Adicionado el Promocion con nombre: " + ID + "\n";
				resultado += "\n\n************ Ejecutando la demo ************ \n";
				resultado +=  "\n " + listarPromociones(lista);
				resultado += "\n\n************ Limpiando la base de datos ************ \n";
				resultado += tbEliminados + " Promociones eliminados\n";
				
				List <Promocion> listaDespues = superAndes.darPromociones();
				resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
				resultado +=  "\n " + listarPromociones(listaDespues);
				resultado += "\n Demo terminada";
	   
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
   
  
    
    
    /* ****************************************************************
	 * 			DEMO registrar Venta
	 *****************************************************************/
    
    public void demoRegistrarVentaDeProducto( )
	{
		try 
		{

			
			String numeroDeFactura = nextvalIdFactura();
			java.util.Date fecha = new Date( 0/1/0001);
			int idCliente = nextvalID2(); 					
		
			
			String resultado = "Demo de creación y listado de Promociones\n\n";
			resultado += "\n\n************ Base de datos antes de Operacion: ************ \n";
	
			
			resultado += "\n\n************ Factura: ************ \n";
			List <Factura> listaFacAntes = superAndes.darFacturas();
			resultado +=  "\n " + listarFactura(listaFacAntes)+  "\n\n ";
			
			
			
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			
			
			Factura tb = superAndes.registrarFactura(numeroDeFactura,fecha,idCliente) ;
			resultado += "\n\n************ Añadiendo Factura "+numeroDeFactura+"************ \n";
			List <Factura> listaFac = superAndes.darFacturas();
			resultado +=  "\n " + listarFactura(listaFac)+  "\n\n ";

			
			
			resultado += "\n\n************ Eliminando ************ \n";
			
			long FacEliminados = superAndes.eliminarFactura(numeroDeFactura);
			
			resultado +=  "\n " + FacEliminados+  " Facturas eliminadas \n\n ";

			resultado += "\n\n************ Base de datos Despues de Operacion: ************ \n";

			
			resultado += "\n\n************ Factura: ************ \n";
			List <Factura> listaFacDesp = superAndes.darFacturas();
			resultado +=  "\n " + listarFactura(listaFacDesp)+  "\n\n ";
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
    public void demoRegistrarVentaDeProductoNoExitoso( )
   	{
   		try 
   		{

   			
   			String numeroDeFactura = nextvalIdFactura();
   			java.util.Date fecha = new Date( 0/1/0001);
   			int idCliente = 39; 					
   	

   			

   			String resultado = "Demo de creación y listado de Promociones\n\n";
   			resultado += "\n\n************ Base de datos antes de Operacion: ************ \n";
   	
   			
   			resultado += "\n\n************ Factura: ************ \n";
   			List <Factura> listaFacAntes = superAndes.darFacturas();
   			resultado +=  "\n " + listarFactura(listaFacAntes)+  "\n\n ";
   			
   			
   			
   			resultado += "\n\n************ Generando datos de prueba ************ \n";
   			
   			
   			Factura tb = superAndes.registrarFactura(numeroDeFactura,fecha,idCliente) ;
   			if(tb == null)
   			{
   				resultado += "\n\n************ Añadiendo Factura "+numeroDeFactura+"************ \n";
   	   			List <Factura> listaFac = superAndes.darFacturas();
   	   			resultado +=  "\n " + listarFactura(listaFac)+  "\n\n ";
				
				resultado += "\n\n************ Error Al insertar************ \n\n";				
				
				resultado += "*** Exception creando Factura !!\n";
				resultado += "*** Es probable que ese Factura ya existiera y hay restricción de UNICIDAD sobre el nombre del Promocion\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
				
				
				
				panelDatos.actualizarInterfaz(resultado);
   			}
   			else
   			{
   				resultado += "\n\n************ Añadiendo Factura "+numeroDeFactura+"************ \n";
   	   			List <Factura> listaFac = superAndes.darFacturas();
   	   			resultado +=  "\n " + listarFactura(listaFac)+  "\n\n ";

   	   			
   	   			
   	   			resultado += "\n\n************ Eliminando ************ \n";
   	   			
   	   			long FacEliminados = superAndes.eliminarFactura(numeroDeFactura);
   	   			
   	   			resultado +=  "\n " + FacEliminados+  " Facturas eliminadas \n\n ";

   	   			resultado += "\n\n************ Base de datos Despues de Operacion: ************ \n";

   	   			
   	   			resultado += "\n\n************ Factura: ************ \n";
   	   			List <Factura> listaFacDesp = superAndes.darFacturas();
   	   			resultado +=  "\n " + listarFactura(listaFacDesp)+  "\n\n ";
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
    
    
    
    /* **********************************************************************************************************************************************
	 * 			DEMOs IT2
	 ***********************************************************************************************************************************************/
    /* ****************************************************************
	 * 			Demo Adicionar y Devolver Prod a Carrito
	 *****************************************************************/
    public void demoAdicionarEliminarProductoCarritoNoExitoso( )
    {
    	try 
    	{
    		String codigoDeBarras1 = "000008";
    		int numProd = 2;
    		long idCarrito = 99999;
			
    		List <Producto> productosConCodigo = superAndes.busquedaProducto(codigoDeBarras1,"",1);
    		Producto prod = productosConCodigo.get(0);
			String resultado = "Demo de creación y listado de Productos\n\n";
						
			resultado += "\n\n************ Base de datos antes de Operacion: ************ \n";
			List <Producto> listaAntes = superAndes.darVOProducto();
			resultado +=  "\n " + listarProductos(listaAntes)+  "\n\n ";

				
			Producto pe=superAndes.registrarProducto(nextvalCodigoBarras(),prod.getNombre(),prod.getMarca(),prod.getPrecioUnitario(),prod.getPresentacion(), prod.getPrecioPorUnidad(),prod.getCantidadEnLaPresentacion(),"gr",prod.getEspecificacionesDeEmpacado(), prod.getNivelDeReorden(), prod.getIDPedido(), prod.getIDSucursal(), prod.getIDContenedor(),1,prod.getEnStock(),numProd, idCarrito) ;
			if(pe == null)
			{
				resultado += "\n\n************ Generando datos de prueba ************ \n";
				
				List <Producto> listaDurante = superAndes.darVOProducto();
				resultado += "Adicionado el Producto con Carrito: " + idCarrito + "\n";
				resultado += "\n\n************ Ejecutando la demo ************ \n";
				resultado +=  "\n " + listarProductos(listaDurante);
				
				
				resultado += "\n\n************ Error Al insertar************ \n\n";				
				
				resultado += "*** Exception creando El producto !!\n";
				resultado += "*** Es probable que el carrito no exista y hay restriccion de llave foranea en Productos con el idCarrito\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
				
				
				List <Producto> listaDespues = superAndes.darVOProducto();
				resultado += "\n\n************ Despues de la operacion la lista queda asi:************ \n";
				resultado +=  "\n " + listarProductos(listaDespues);
				resultado += "\n Demo terminada";
				
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
			int prodMenosDeEstante =prod.getVolumen() - numProd;
			superAndes.quitarProductosDeEstante(prodMenosDeEstante, codigoDeBarras1) ;

			
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			
			List <Producto> listaDurante = superAndes.darVOProducto();
			resultado += "Adicionado el Producto con Carrito: " + idCarrito + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n " + listarProductos(listaDurante);
			
			
			
			//Eliminacion
			List <Producto> productosConCodigoEliminar = superAndes.busquedaProducto(pe.getCodigoDeBarras(),"",1);
			Producto prodElim = productosConCodigoEliminar.get(0);


			List <Producto> estanteDeProductosConNombreEliminar = superAndes.busquedaProducto("",prodElim.getNombre(),0);
			Producto prodEst = estanteDeProductosConNombreEliminar.get(0);

			int prodMasDeEstante =prodEst.getVolumen() + prod.getVolumen();
			long p = superAndes.quitarProductosDeEstante(prodMasDeEstante, prodEst.getCodigoDeBarras()) ;
			superAndes.devolverProducto(pe.getCodigoDeBarras()) ;
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			
			resultado += p + " Productos eliminados\n";			
			List <Producto> listaDespues = superAndes.darVOProducto();
			resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
			resultado +=  "\n " + listarProductos(listaDespues);
			resultado += "\n Demo terminada";
   
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
    
    public void demoAdicionarEliminarProductoCarritoExitoso( )
    {
    	try 
    	{
    		String codigoDeBarras1 = "000008";
    		int numProd = 2;
    		long idCarrito = 280;
			
    		List <Producto> productosConCodigo = superAndes.busquedaProducto(codigoDeBarras1,"",1);
    		Producto prod = productosConCodigo.get(0);
			String resultado = "Demo de creación y listado de Productos\n\n";
						
			resultado += "\n\n************ Base de datos antes de Operacion: ************ \n";
			List <Producto> listaAntes = superAndes.darVOProducto();
			resultado +=  "\n " + listarProductos(listaAntes)+  "\n\n ";
	
			
			Producto pe=superAndes.registrarProducto(nextvalCodigoBarras(),prod.getNombre(),prod.getMarca(),prod.getPrecioUnitario(),prod.getPresentacion(), prod.getPrecioPorUnidad(),prod.getCantidadEnLaPresentacion(),"gr",prod.getEspecificacionesDeEmpacado(), prod.getNivelDeReorden(), prod.getIDPedido(), prod.getIDSucursal(), prod.getIDContenedor(),1,prod.getEnStock(),numProd, idCarrito) ;

			int prodMenosDeEstante =prod.getVolumen() - numProd;
			superAndes.quitarProductosDeEstante(prodMenosDeEstante, codigoDeBarras1) ;

			
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			
			List <Producto> listaDurante = superAndes.darVOProducto();
			resultado += "Adicionado el Producto con Carrito: " + idCarrito + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n " + listarProductos(listaDurante);
			
			
			
			//Eliminacion
			List <Producto> productosConCodigoEliminar = superAndes.busquedaProducto(pe.getCodigoDeBarras(),"",1);
			Producto prodElim = productosConCodigoEliminar.get(0);


			List <Producto> estanteDeProductosConNombreEliminar = superAndes.busquedaProducto("",prodElim.getNombre(),0);
			Producto prodEst = estanteDeProductosConNombreEliminar.get(0);

			int prodMasDeEstante =prodEst.getVolumen() + prod.getVolumen();
			long p = superAndes.quitarProductosDeEstante(prodMasDeEstante, prodEst.getCodigoDeBarras()) ;
			superAndes.devolverProducto(pe.getCodigoDeBarras()) ;
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			
			resultado += p + " Productos eliminados\n";			
			List <Producto> listaDespues = superAndes.darVOProducto();
			resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
			resultado +=  "\n " + listarProductos(listaDespues);
			resultado += "\n Demo terminada";
   
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
	 * 			Demo Abandonar un carrito
	 *****************************************************************/
    public void demoAbandonarUnCarritoExitoso( )
    {
    	try 
    	{   		
    		long idCarrito = 280;
			
    		
			String resultado = "Demo de creación y listado de Productos\n\n";
						
			resultado += "\n\n************ Base de datos antes de Operacion: ************ \n";
			List <Producto> listaAntes = superAndes.darVOProducto();
			resultado +=  "\n " + listarProductos(listaAntes)+  "\n\n ";

			CarritoDeCompras pe = superAndes.abandonarCarrito(idCarrito) ;	
			if(pe == null)
			{
				resultado += "\n\n************ Generando datos de prueba ************ \n";
				
				List <CarritoDeCompras> listaDurante = superAndes.darCarritos();
				resultado += "abandonando el carrito: " + idCarrito + "\n";
				resultado += "\n\n************ Ejecutando la demo ************ \n";
				resultado +=  "\n " + listarCarritos(listaDurante);
				
				
				resultado += "\n\n************ Error Al insertar************ \n\n";				
				
				resultado += "*** Exception abandonando el carrito !!\n";
				resultado += "*** Es probable que el carrito no exista \n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
				
				
				List <CarritoDeCompras> listaDespues = superAndes.darCarritos();
				resultado += "\n\n************ Despues de la operacion la lista queda asi:************ \n";
				resultado +=  "\n " + listarCarritos(listaDespues);
				resultado += "\n Demo terminada";
				
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
			
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			
			List <CarritoDeCompras> listaDurante = superAndes.darCarritos();
			resultado += "abandonando el carrito: " + idCarrito + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n " + listarCarritos(listaDurante);

			
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			
			resultado += pe.getID() + "<----- Carrito Abandonado\n";			
			List <CarritoDeCompras> listaDespues = superAndes.darCarritos();
			resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
			resultado +=  "\n " + listarCarritos(listaDespues);
			resultado += "\n Demo terminada";
   
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
    
    public void demoAbandonarUnCarritoNoExitoso( )
    {
    	try 
    	{   		
    		long idCarrito = 9999;
			
    		
			String resultado = "Demo de Abandono de un carrito \n\n";
						
			resultado += "\n\n************ Base de datos antes de Operacion: ************ \n";
			List <Producto> listaAntes = superAndes.darVOProducto();
			resultado +=  "\n " + listarProductos(listaAntes)+  "\n\n ";

			CarritoDeCompras pe = superAndes.abandonarCarrito(idCarrito) ;	
			if(pe == null)
			{
				resultado += "\n\n************ Generando datos de prueba ************ \n";
				
				List <CarritoDeCompras> listaDurante = superAndes.darCarritos();
				resultado += "Abandonando el carrito: " + idCarrito + "\n";
				resultado += "\n\n************ Ejecutando la demo ************ \n";
				resultado +=  "\n " + listarCarritos(listaDurante);
				
				
				resultado += "\n\n************ Error Al insertar************ \n\n";				
				
				resultado += "*** Exception abandonando el carrito !!\n";
				resultado += "*** Es probable que el carrito no exista \n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
				
				
				List <CarritoDeCompras> listaDespues = superAndes.darCarritos();
				resultado += "\n\n************ Despues de la operacion la lista queda asi:************ \n";
				resultado +=  "\n " + listarCarritos(listaDespues);
				resultado += "\n Demo terminada";
				
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
			
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			
			List <CarritoDeCompras> listaDurante = superAndes.darCarritos();
			resultado += "Abandonando el carrito: " + idCarrito + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n " + listarCarritos(listaDurante);

			
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			
			resultado += pe.getID() + "<----- Carrito Abandonado\n";			
			List <CarritoDeCompras> listaDespues = superAndes.darCarritos();
			resultado += "\n\n************ Despues de eliminar la lista queda asi:************ \n";
			resultado +=  "\n " + listarCarritos(listaDespues);
			resultado += "\n Demo terminada";
   
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
    
    
    /* ****************************************************************
	 * 			Listar De tablas
	 *****************************************************************/
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
    private String listarClienteNatural(List<VOClienteNatural> lista) 
    {
    	String resp = "Los Clientes naturales existentes son:\n";
    	int i = 1;
        for (VOClienteNatural tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
    private String listarSucursal(List<VOSucursal> lista) 
    {
    	String resp = "Las sucursales existentes son:\n";
    	int i = 1;
        for (VOSucursal tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}

    private String listarContenedores(List<Contenedor> lista) 
    {
    	String resp = "Los contenedores existentes son:\n";
    	int i = 1;
        for (Contenedor tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
    
    private String listarPedidos(List<Pedido> lista) 
    {
    	String resp = "Los pedidos existentes son:\n";
    	int i = 1;
        for (Pedido tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}

    
    
    private String listarProductos(List<Producto> lista) 
    {
    	String resp = "Los Productos existentes son:\n";
    	int i = 1;
        for (Producto tb : lista)
       {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	} 
    private String listarCarritos(List<CarritoDeCompras> lista) 
    {
    	String resp = "Los Carritos De Compras existentes son:\n";
    	int i = 1;
        for (CarritoDeCompras tb : lista)
       {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	} 
    private String listarFactura(List<Factura> lista) 
    {
    	String resp = "Las Factura existentes son:\n";
    	int i = 1;
        for (Factura tb : lista)
       {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
   private String listarClienteEmpresa(List<VOClienteEmpresa> lista) 
    {
    	String resp = "Los Clientes empresariales existentes son:\n";
    	int i = 1;
        for (VOClienteEmpresa tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
   
    private String listarEstantes(List<Estante> lista) 
    {
    	String resp = "Los Estante existentes son:\n";
    	int i = 1;
        for (Estante tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
    
    private String listarPromociones(List<Promocion> lista) 
    {
    	String resp = "Las Promocion existentes son:\n";
    	int i = 1;
        for (Promocion tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}

    private String listarClientesNaturales(List<ClienteNatural> lista) 
    {
    	String resp = "Los clientes naturales existentes son:\n";
    	int i = 1;
        for (ClienteNatural tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
    
    private String listarSucursales(List<Sucursal> lista) 
    {
    	String resp = " las sucrusales existentes son:\n";
    	int i = 1;
        for (Sucursal tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
    
    private String listarContenedor(List<VOContenedor> lista) 
    {
    	String resp = " los contenedores existentes son:\n";
    	int i = 1;
        for (VOContenedor tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
    
    private String listarPedido(List<VOPedido> lista) 
    {
    	String resp = " los contenedores existentes son:\n";
    	int i = 1;
        for (VOPedido tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
    
    private String listarClientesEmpresariales(List<ClienteEmpresa> lista) 
    {
    	String resp = "Los clientes empresariales existentes son:\n";
    	int i = 1;
        for (ClienteEmpresa tb : lista)
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
			Method req = InterfaSuperAndesDemo.class.getMethod ( evento );			
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
        	
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
            InterfaSuperAndesDemo interfaz = new InterfaSuperAndesDemo( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}
