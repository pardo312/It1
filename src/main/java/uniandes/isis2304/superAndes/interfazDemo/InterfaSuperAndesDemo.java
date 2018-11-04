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

import uniandes.isis2304.superAndes.negocio.ClienteEmpresa;
import uniandes.isis2304.superAndes.negocio.ClienteNatural;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.Sucursal;
import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.VOClienteEmpresa;
import uniandes.isis2304.superAndes.negocio.VOClienteNatural;

import uniandes.isis2304.superAndes.negocio.Estante;
import uniandes.isis2304.superAndes.negocio.Factura;
import uniandes.isis2304.superAndes.negocio.FacturaProducto;
import uniandes.isis2304.superAndes.negocio.Producto;
import uniandes.isis2304.superAndes.negocio.Promocion;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.VOEstante;
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
    		int idSucursal = 5;
			
			
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
    		int idSucursal = 5;
			
			
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
    
    public void registrarVentaDeProducto( )
	{
		try 
		{

			
			String numeroDeFactura = nextvalIdFactura();
			java.util.Date fecha = new Date( 0/1/0001);
			int idCliente = nextvalID(); 					
			String codigoDeBarras = nextvalCodigoBarras();
			int numProd = 12;

			
//			for (int i = 1; i<numProd;i++)
//			{
//
//
//				FacturaProducto fp = superAndes.registrarFacturaProd(numeroDeFactura,codigoDeBarras) ;
//				if (fp == null)
//				{
//					throw new Exception ("No se pudo crear factura con numero: " + numeroDeFactura);
//				}
//				String resultado = "En adicionarProveedor\n\n";
//				resultado += "proveedor adicionado exitosamente: " + tb;
//				resultado += "\n Operación terminada";
//				panelDatos.actualizarInterfaz(resultado);
//
//
//			}
			
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
