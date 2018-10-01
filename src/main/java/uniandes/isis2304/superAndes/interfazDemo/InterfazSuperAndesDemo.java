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

import uniandes.isis2304.superAndes.interfazApp.PanelDatos;
import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.VOContenedor;
import uniandes.isis2304.superAndes.negocio.VOCategoria;
import uniandes.isis2304.superAndes.negocio.VOCliente;
import uniandes.isis2304.superAndes.negocio.VOEstante;
import uniandes.isis2304.superAndes.negocio.VOPedido;
import uniandes.isis2304.superAndes.negocio.VOTipoProducto;
import uniandes.isis2304.superAndes.negocio.VOProducto;

/**
 * Clase principal de la interfaz
 * 
 * @author Germán Bravo
 */
@SuppressWarnings("serial")

public class InterfazSuperAndesDemo extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazSuperAndesDemo.class.getName());
	
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
    public InterfazSuperAndesDemo( )
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
	 * 			Demos de TipoProducto
	 *****************************************************************/
    /**
     * Demostración de creación, consulta y borrado de Tipos de Bebida
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */
    public void demoTipoProducto( )
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			String nombreTipoProducto = "Vino tinto";
			boolean errorTipoProducto = false;
			VOTipoProducto tipoProducto = superAndes.adicionarTipoProducto (nombreTipoProducto);
			if (tipoProducto == null)
			{
				tipoProducto = superAndes.darTipoProductoPorNombre (nombreTipoProducto);
				errorTipoProducto = true;
			}
			List <VOTipoProducto> lista = superAndes.darVOTiposProducto();
			long tbEliminados = superAndes.eliminarTipoProductoPorId (tipoProducto.getId ());
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de TipoProducto\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorTipoProducto)
			{
				resultado += "*** Exception creando tipo de producto !!\n";
				resultado += "*** Es probable que ese tipo de producto ya existiera y hay restricción de UNICIDAD sobre el nombre del tipo de producto\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "Adicionado el tipo de producto con nombre: " + nombreTipoProducto + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n" + listarTiposProducto (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += tbEliminados + " Tipos de bebida eliminados\n";
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
	 * 			Demos de Bebida
	 *****************************************************************/
    /**
     * Demostración de creación, consulta y borrado de Bebidas.
     * Incluye también los tipos de producto pues el tipo de producto es llave foránea en las bebidas
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */
    @SuppressWarnings ("unused")
	public void demoBebida( )
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			String nombreTipoProducto = "Vino tinto";
			String nombreBebida = "120";
			boolean errorTipoProducto = false;
			VOTipoProducto tipoProducto = superAndes.adicionarTipoProducto (nombreTipoProducto);
			if (tipoProducto == null)
			{
				tipoProducto = superAndes.darTipoProductoPorNombre (nombreTipoProducto);
				errorTipoProducto = true;
			}
			VOCliente bebida = superAndes.adicionarBebida(nombreBebida, tipoProducto.getId (), 10);
			
			List <VOTipoProducto> listaTiposProducto = superAndes.darVOTiposProducto();
			List <VOCliente> listaBebidas = superAndes.darVOBebidas();
			long bebEliminadas = superAndes.eliminarBebidaPorNombre(nombreBebida);
			long tbEliminados = superAndes.eliminarTipoProductoPorId (tipoProducto.getId ());
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Bebidas\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n\n";
			if (errorTipoProducto)
			{
				resultado += "*** Exception creando tipo de producto !!\n";
				resultado += "*** Es probable que ese tipo de producto ya existiera y hay restricción de UNICIDAD sobre el nombre del tipo de producto\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "Adicionado el tipo de producto con nombre: " + nombreTipoProducto + "\n";
			resultado += "Adicionada la bebida con nombre: " + nombreBebida + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n" + listarTiposProducto (listaTiposProducto);
			resultado += "\n" + listarBebidas (listaBebidas);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += bebEliminadas + " Bebidas eliminadas\n";
			resultado += tbEliminados + " Tipos de bebida eliminados\n";
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

    /**
     * Demostración de creación y borrado de bebidas no servidas.
     * Incluye también los tipos de producto pues el tipo de producto es llave foránea en las bebidas
     * Caso 1: Ninguna bebida es servida en ningún bar: La tabla de bebidas queda vacía antes de la fase de limpieza
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */
    public void demoElimNoServidas1 ( )
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			String nombreTipoProducto = "Vino tinto";
			boolean errorTipoProducto = false;
			VOTipoProducto tipoProducto = superAndes.adicionarTipoProducto (nombreTipoProducto);
			if (tipoProducto == null)
			{
				tipoProducto = superAndes.darTipoProductoPorNombre (nombreTipoProducto);
				errorTipoProducto = true;
			}
			VOCliente bebida1 = superAndes.adicionarBebida("120", tipoProducto.getId (), 10);
			VOCliente bebida2 = superAndes.adicionarBebida("Gato Negro", tipoProducto.getId (), 11);
			VOCliente bebida3 = superAndes.adicionarBebida("Don Pedro", tipoProducto.getId (), 12);
			
			List <VOTipoProducto> listaTiposProducto = superAndes.darVOTiposProducto();
			List <VOCliente> listaBebidas1 = superAndes.darVOBebidas();
			List <VOPedido> listaSirven = superAndes.darVOSirven ();
			long noServidasEliminadas = superAndes.eliminarBebidasNoServidas();
			List <VOCliente> listaBebidas2 = superAndes.darVOBebidas();
			
			long bebEliminadas1 = superAndes.eliminarBebidaPorId(bebida1.getId ());
			long bebEliminadas2 = superAndes.eliminarBebidaPorId(bebida2.getId ());
			long bebEliminadas3 = superAndes.eliminarBebidaPorId(bebida3.getId ());
			long tbEliminados = superAndes.eliminarTipoProductoPorNombre (nombreTipoProducto);
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de borrado de las bebidas no servidas 1\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorTipoProducto)
			{
				resultado += "*** Exception creando tipo de producto !!\n";
				resultado += "*** Es probable que ese tipo de producto ya existiera y hay restricción de UNICIDAD sobre el nombre del tipo de producto\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado +=  "\n" + listarTiposProducto (listaTiposProducto);
			resultado += "\n" + listarBebidas (listaBebidas1);
			resultado += "\n" + listarSirven (listaSirven);
			resultado += "\n\n************ Ejecutando la demo: Borrando bebidas no servidas ************ \n";
			resultado += noServidasEliminadas + " Bebidas eliminadas\n";
			resultado += "\n" + listarBebidas (listaBebidas2);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += (bebEliminadas1 + bebEliminadas2 + bebEliminadas3) + " Bebidas eliminadas\n";
			resultado += tbEliminados + " Tipos de bebida eliminados\n";
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

    /**
     * Demostración de creación y borrado de bebidas no servidas.
     * Incluye también los tipos de producto pues el tipo de producto es llave foránea en las bebidas
     * Incluye también los bares pues son llave foránea en la relación Sirven
     * Caso 2: Hay bebidas que son servidas y estas quedan en la tabla de bebidas
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */
    public void demoElimNoServidas2 ( )
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			String nombreTipoProducto = "Vino tinto";
			boolean errorTipoProducto = false;
			VOTipoProducto tipoProducto = superAndes.adicionarTipoProducto (nombreTipoProducto);
			if (tipoProducto == null)
			{
				tipoProducto = superAndes.darTipoProductoPorNombre (nombreTipoProducto);
				errorTipoProducto = true;
			}
			VOCliente bebida1 = superAndes.adicionarBebida("120", tipoProducto.getId (), 10);
			VOCliente bebida2 = superAndes.adicionarBebida("Gato Negro", tipoProducto.getId (), 11);
			VOCliente bebida3 = superAndes.adicionarBebida("Don Pedro", tipoProducto.getId (), 12);
			VOContenedor bar1 = superAndes.adicionarBar ("Los Amigos", "Bogotá", "Bajo", 2);
			superAndes.adicionarSirven (bar1.getId (), bebida1.getId (), "diurno");
			
			List <VOTipoProducto> listaTiposProducto = superAndes.darVOTiposProducto();
			List <VOCliente> listaBebidas1 = superAndes.darVOBebidas();
			List <VOContenedor> bares = superAndes.darVOBares ();
			List <VOPedido> sirven = superAndes.darVOSirven ();
			long noServidasEliminadas = superAndes.eliminarBebidasNoServidas();
			List <VOCliente> listaBebidas2 = superAndes.darVOBebidas();
			
			long sirvenEliminados = superAndes.eliminarSirven(bar1.getId (), bebida1.getId ());
			long bebEliminadas1 = superAndes.eliminarBebidaPorId(bebida1.getId ());
			long bebEliminadas2 = superAndes.eliminarBebidaPorId(bebida2.getId ());
			long bebEliminadas3 = superAndes.eliminarBebidaPorId(bebida3.getId ());
			long tbEliminados = superAndes.eliminarTipoProductoPorNombre (nombreTipoProducto);
			long baresEliminados = superAndes.eliminarBarPorNombre ("Los Amigos");
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de borrado de las bebidas no servidas 2\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorTipoProducto)
			{
				resultado += "*** Exception creando tipo de producto !!\n";
				resultado += "*** Es probable que ese tipo de producto ya existiera y hay restricción de UNICIDAD sobre el nombre del tipo de producto\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "\n" + listarTiposProducto (listaTiposProducto);
			resultado += "\n" + listarBebidas (listaBebidas1);
			resultado += "\n" + listarBares (bares);
			resultado += "\n" + listarSirven (sirven);
			resultado += "\n\n************ Ejecutando la demo: Borrando bebidas no servidas ************ \n";
			resultado += noServidasEliminadas + " Bebidas eliminadas\n";
			resultado += "\n" + listarBebidas (listaBebidas2);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += sirvenEliminados + " Sirven eliminados\n";
			resultado += baresEliminados + " Bares eliminados\n";
			resultado += (bebEliminadas1 + bebEliminadas2 + bebEliminadas3) + " Bebidas eliminadas\n";
			resultado += tbEliminados + " Tipos de bebida eliminados\n";
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
	 * 			Demos de Bar
	 *****************************************************************/
    /**
     * Demostración de creación, consulta y borrado de Bares
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */
    public void demoBar ( )
    {
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOContenedor bar1 = superAndes.adicionarBar ("Los Amigos", "Bogotá", "Bajo", 2);
			
			List <VOContenedor> lista = superAndes.darVOBares ();
			
			long baresEliminados = superAndes.eliminarBarPorNombre("Los Amigos");
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Bares\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado el bar: " + bar1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarBares (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += baresEliminados + " Bares eliminados\n";
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

    /**
     * Demostración de la consulta: Dar el id y el número de bebidas que sirve cada bar, siempre y cuando el bar sirva por los menos una bebida
     * Incluye el manejo de los tipos de producto pues el tipo de producto es llave foránea en las bebidas
     * Incluye el manajo de las bebidas
     * Incluye el manejo de los bares
     * Incluye el manejo de la relación sirven
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */
    public void demoBaresBebidas ( )
    {
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			boolean errorTipoProducto = false;
			VOTipoProducto tipoProducto = superAndes.adicionarTipoProducto ("Vino tinto");
			if (tipoProducto == null)
			{
				tipoProducto = superAndes.darTipoProductoPorNombre ("Vino tinto");
				errorTipoProducto = true;
			}
			VOCliente bebida1 = superAndes.adicionarBebida ("120", tipoProducto.getId (), 10);
			VOCliente bebida2 = superAndes.adicionarBebida ("121", tipoProducto.getId (), 10);
			VOCliente bebida3 = superAndes.adicionarBebida ("122", tipoProducto.getId (), 10);
			VOCliente bebida4 = superAndes.adicionarBebida ("123", tipoProducto.getId (), 10);
			VOCliente bebida5 = superAndes.adicionarBebida ("124", tipoProducto.getId (), 10);
			VOContenedor bar1 = superAndes.adicionarBar ("Los Amigos1", "Bogotá", "Bajo", 2);
			VOContenedor bar2 = superAndes.adicionarBar ("Los Amigos2", "Bogotá", "Bajo", 3);
			VOContenedor bar3 = superAndes.adicionarBar ("Los Amigos3", "Bogotá", "Bajo", 4);
			VOContenedor bar4 = superAndes.adicionarBar ("Los Amigos4", "Medellín", "Bajo", 5);
			superAndes.adicionarSirven (bar1.getId (), bebida1.getId (), "diurno");
			superAndes.adicionarSirven (bar1.getId (), bebida2.getId (), "diurno");
			superAndes.adicionarSirven (bar2.getId (), bebida1.getId (), "diurno");
			superAndes.adicionarSirven (bar2.getId (), bebida2.getId (), "diurno");
			superAndes.adicionarSirven (bar2.getId (), bebida3.getId (), "diurno");
			superAndes.adicionarSirven (bar3.getId (), bebida1.getId (), "diurno");
			superAndes.adicionarSirven (bar3.getId (), bebida2.getId (), "diurno");
			superAndes.adicionarSirven (bar3.getId (), bebida3.getId (), "diurno");
			superAndes.adicionarSirven (bar3.getId (), bebida4.getId (), "diurno");
			superAndes.adicionarSirven (bar3.getId (), bebida5.getId (), "diurno");
			
			List <VOTipoProducto> listaTiposProducto = superAndes.darVOTiposProducto ();
			List <VOCliente> listaBebidas = superAndes.darVOBebidas ();
			List <VOContenedor> listaBares = superAndes.darVOBares ();
			List <VOPedido> listaSirven = superAndes.darVOSirven ();

			List <long []> listaByB = superAndes.darBaresYCantidadBebidasSirven();

			long sirvenEliminados = superAndes.eliminarSirven (bar1.getId (), bebida1.getId ());
			sirvenEliminados += superAndes.eliminarSirven (bar1.getId (), bebida2.getId ());
			sirvenEliminados += superAndes.eliminarSirven (bar2.getId (), bebida1.getId ());
			sirvenEliminados += superAndes.eliminarSirven (bar2.getId (), bebida2.getId ());
			sirvenEliminados += superAndes.eliminarSirven (bar2.getId (), bebida3.getId ());
			sirvenEliminados += superAndes.eliminarSirven (bar3.getId (), bebida1.getId ());
			sirvenEliminados += superAndes.eliminarSirven (bar3.getId (), bebida2.getId ());
			sirvenEliminados += superAndes.eliminarSirven (bar3.getId (), bebida3.getId ());
			sirvenEliminados += superAndes.eliminarSirven (bar3.getId (), bebida4.getId ());
			sirvenEliminados += superAndes.eliminarSirven (bar3.getId (), bebida5.getId ());
			long bebidasEliminadas = superAndes.eliminarBebidaPorNombre ("120");
			bebidasEliminadas += superAndes.eliminarBebidaPorNombre ("121");
			bebidasEliminadas += superAndes.eliminarBebidaPorNombre ("122");
			bebidasEliminadas += superAndes.eliminarBebidaPorNombre ("123");
			bebidasEliminadas += superAndes.eliminarBebidaPorNombre ("124");
			long tbEliminados = superAndes.eliminarTipoProductoPorNombre ("Vino tinto");
			long baresEliminados = superAndes.eliminarBarPorNombre ("Los Amigos1");
			baresEliminados += superAndes.eliminarBarPorNombre ("Los Amigos2");
			baresEliminados += superAndes.eliminarBarPorNombre ("Los Amigos3");
			baresEliminados += superAndes.eliminarBarPorId (bar4.getId ());
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Bares y cantidad de visitas que reciben\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorTipoProducto)
			{
				resultado += "*** Exception creando tipo de producto !!\n";
				resultado += "*** Es probable que ese tipo de producto ya existiera y hay restricción de UNICIDAD sobre el nombre del tipo de producto\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "\n" + listarTiposProducto (listaTiposProducto);
			resultado += "\n" + listarBebidas (listaBebidas);
			resultado += "\n" + listarBares (listaBares);
			resultado += "\n" + listarSirven (listaSirven);
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarBaresYBebidas (listaByB);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += sirvenEliminados + " Sirven eliminados\n";
			resultado += bebidasEliminadas + " Bebidas eliminados\n";
			resultado += tbEliminados + " Tipos de Bebida eliminados\n";
			resultado += baresEliminados + " Bares eliminados\n";
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

    /**
     * Demostración de la modificación: Aumentar en uno el número de sedes de los bares de una ciudad
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */
    public void demoAumentarSedesBaresEnCiudad ( )
    {
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOContenedor bar1 = superAndes.adicionarBar ("Los Amigos1", "Bogotá", "Bajo", 2);
			VOContenedor bar2 = superAndes.adicionarBar ("Los Amigos2", "Bogotá", "Bajo", 3);
			VOContenedor bar3 = superAndes.adicionarBar ("Los Amigos3", "Bogotá", "Bajo", 4);
			VOContenedor bar4 = superAndes.adicionarBar ("Los Amigos4", "Medellín", "Bajo", 5);
			List <VOContenedor> listaBares = superAndes.darVOBares ();
			
			long baresModificados = superAndes.aumentarSedesBaresCiudad("Bogotá");
			List <VOContenedor> listaBares2 = superAndes.darVOBares ();

			long baresEliminados = superAndes.eliminarBarPorId (bar1.getId ());
			baresEliminados += superAndes.eliminarBarPorId (bar2.getId ());
			baresEliminados += superAndes.eliminarBarPorId (bar3.getId ());
			baresEliminados += superAndes.eliminarBarPorId (bar4.getId ());
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo

			String resultado = "Demo de modificación número de sedes de los bares de una ciudad\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "\n" + listarBares (listaBares);
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += baresModificados + " Bares modificados\n";
			resultado += "\n" + listarBares (listaBares2);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += baresEliminados + " Bares eliminados\n";
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
	 * 			Demos de Bebedor
	 *****************************************************************/
    /**
     * Demostración de creación, consulta y borrado de Bebedores
     * Incluye la consulta por nombre y por id
     * Incluye el borrado por nombre
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */
    @SuppressWarnings ("unused")
	public void demoBebedor ( )
    {
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOCategoria bdor1 = superAndes.adicionarBebedor ("Pepito", "Bogotá", "Alto");
			VOCategoria bdor2 = superAndes.adicionarBebedor ("Pepito", "Medellín", "Alto");
			VOCategoria bdor3 = superAndes.adicionarBebedor ("Pedrito", "Cali", "Alto");
			
			List <VOCategoria> bebedores = superAndes.darVOBebedores();
			VOCategoria bdor5 = superAndes.darBebedorPorId(bdor1.getId ());
			VOCategoria bdor6 = superAndes.darBebedorPorId(0);
			List <VOCategoria> pepitos = superAndes.darVOBebedoresPorNombre("Pepito");
			List <VOCategoria> pedritos = superAndes.darVOBebedoresPorNombre("Pedrito");
			
			long pepitosEliminados = superAndes.eliminarBebedorPorNombre ("Pepito");
			long pedritosEliminados = superAndes.eliminarBebedorPorNombre ("Pedrito");

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Bebedores\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "\n" + listarBebedores (bebedores);
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\nBuscando el bebedor con id " + bdor1 + ":\n";
			resultado += bdor5 != null ? "El bebedor es: " + bdor5 + "\n" : "Ese bebedor no existe\n";
			resultado += "\nBuscando el bebedor con id " + 0 + ":\n";
			resultado += bdor6 != null ? "El bebedor es: " + bdor6 + "\n" : "Ese bebedor no existe\n";
			resultado += "\nBuscando los bebedores con nombre Pepito:";
			resultado += "\n" + listarBebedores (pepitos);
			resultado += "\nBuscando los bebedores con nombre Pedrito:";
			resultado += "\n" + listarBebedores (pedritos);
			
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += pepitosEliminados + " Pepitos eliminados\n";
			resultado += pedritosEliminados + " Pedritos eliminados\n";
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

    /**
     * Demostración de creación, consulta de TODA LA INFORMACIÖN de un bebedor y borrado de un bebedor y sus visitas
     * Incluye el manejo de tipos de producto
     * Incluye el manejo de bebidas
     * Incluye el manejo de bares
     * Incluye el manejo de la relación sirven
     * Incluye el manejo de la relación gustan
     * Incluye el borrado de un bebedor y todas sus visitas
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */
    public void demoDarBebedorCompleto ( )
    {
		try 
		{
    		// Ejecución de la demo y recolección de los resultados.
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			boolean errorTipoProducto = false;
			VOTipoProducto tipoProducto = superAndes.adicionarTipoProducto ("Vino tinto");
			if (tipoProducto == null)
			{
				tipoProducto = superAndes.darTipoProductoPorNombre ("Vino tinto");
				errorTipoProducto = true;
			}
			VOCliente bebida1 = superAndes.adicionarBebida ("120", tipoProducto.getId (), 10);
			VOContenedor bar1 = superAndes.adicionarBar ("Los Amigos", "Bogotá", "Bajo", 2);
			VOCategoria bdor1 = superAndes.adicionarBebedor ("Pepito", "Bogotá", "Alto");
			
			superAndes.adicionarVisitan (bdor1.getId (), bar1.getId (), new Timestamp (System.currentTimeMillis()), "diurno");
			superAndes.adicionarVisitan (bdor1.getId (), bar1.getId (), new Timestamp (System.currentTimeMillis()), "nocturno");
			superAndes.adicionarVisitan (bdor1.getId (), bar1.getId (), new Timestamp (System.currentTimeMillis()), "todos");
			superAndes.adicionarGustan (bdor1.getId (), bebida1.getId ());

			List <VOTipoProducto> listaTipos = superAndes.darVOTiposProducto();
			List <VOCliente> listaBebidas = superAndes.darVOBebidas();
			List <VOContenedor> listaBares = superAndes.darVOBares ();
			List <VOCategoria> bebedores = superAndes.darVOBebedores();
			List <VOEstante> listaGustan = superAndes.darVOGustan();
			List <VOProducto> listaVisitan = superAndes.darVOVisitan();

			VOCategoria bdor2 = superAndes.darBebedorCompleto(bdor1.getId ());

			long gustanEliminados = superAndes.eliminarGustan (bdor1.getId (), bebida1.getId ());
			long bebidasEliminadas = superAndes.eliminarBebidaPorNombre ("120");
			long tiposEliminados = superAndes.eliminarTipoProductoPorNombre ("Vino tinto");
			long [] bebedorVisitasEliminados = superAndes.eliminarBebedorYVisitas_v1 (bdor1.getId ());
			long baresEliminados = superAndes.eliminarBarPorNombre ("Los Amigos");

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de toda la información de un bebedor\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorTipoProducto)
			{
				resultado += "*** Exception creando tipo de producto !!\n";
				resultado += "*** Es probable que ese tipo de producto ya existiera y hay restricción de UNICIDAD sobre el nombre del tipo de producto\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "\n" + listarTiposProducto (listaTipos);
			resultado += "\n" + listarBebidas (listaBebidas);
			resultado += "\n" + listarBares (listaBares);
			resultado += "\n" + listarBebedores (bebedores);
			resultado += "\n" + listarGustan (listaGustan);
			resultado += "\n" + listarVisitan (listaVisitan);
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\nBuscando toda la información del bebedor con id " + bdor1 + ":\n";
			resultado += bdor2 != null ? "El bebedor es: " + bdor2.toStringCompleto() + "\n" : "Ese bebedor no existe\n";	
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += gustanEliminados + " Gustan eliminados\n";
			resultado += bebidasEliminadas + " Bebidas eliminadas\n";
			resultado += tiposEliminados + " Tipos de Bebida eliminados\n";
			resultado += bebedorVisitasEliminados [0] + " Bebedores eliminados y " + bebedorVisitasEliminados [1] +" Visitas eliminadas\n";
			resultado += baresEliminados + " Bares eliminados\n";
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

    /**
     * Demostración de creación, consulta de TODA LA INFORMACIÖN de un bebedor y borrado de un bebedor y sus visitas.
     * Si hay posibilidades de alguna incoherencia con esta operación NO SE BORRA NI EL BEBEDOR NI SUS VISITAS
     * Incluye el manejo de tipos de producto
     * Incluye el manejo de bebidas
     * Incluye el manejo de bares
     * Incluye el manejo de la relación sirven
     * Incluye el manejo de la relación gustan
     * Incluye el borrado de un bebedor y todas sus visitas v1
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos queda con las tuplas que no se pudieron borrar: ES COHERENTE DE TODAS MANERAS
     */
    public void demoEliminarBebedorYVisitas_v1 ( )
    {
		try 
		{
    		// Ejecución de la demo y recolección de los resultados.
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOCategoria bdor1 = superAndes.adicionarBebedor ("Pepito", "Bogotá", "Alto");
			VOContenedor bar1 = superAndes.adicionarBar ("Los Amigos", "Bogotá", "Bajo", 2);
			boolean errorTipoProducto = false;
			VOTipoProducto tipoProducto = superAndes.adicionarTipoProducto ("Vino tinto");
			if (tipoProducto == null)
			{
				tipoProducto = superAndes.darTipoProductoPorNombre ("Vino tinto");
				errorTipoProducto = true;
			}
			VOCliente bebida1 = superAndes.adicionarBebida ("120", tipoProducto.getId (), 10);
			
			superAndes.adicionarVisitan (bdor1.getId (), bar1.getId (), new Timestamp (System.currentTimeMillis()), "diurno");
			superAndes.adicionarVisitan (bdor1.getId (), bar1.getId (), new Timestamp (System.currentTimeMillis()), "nocturno");
			superAndes.adicionarVisitan (bdor1.getId (), bar1.getId (), new Timestamp (System.currentTimeMillis()), "todos");
			superAndes.adicionarGustan (bdor1.getId (), bebida1.getId ());

			List <VOTipoProducto> listaTipos = superAndes.darVOTiposProducto();
			List <VOCliente> listaBebidas = superAndes.darVOBebidas();
			List <VOContenedor> listaBares = superAndes.darVOBares ();
			List <VOCategoria> bebedores = superAndes.darVOBebedores();
			List <VOEstante> listaGustan = superAndes.darVOGustan();
			List <VOProducto> listaVisitan = superAndes.darVOVisitan();

			VOCategoria bdor2 = superAndes.darBebedorCompleto(bdor1.getId ());

			// No se elimina la tupla de GUSTAN para estudiar la coherencia de las operaciones en la base de daatos
			long gustanEliminados = 0;
			long bebidasEliminadas = superAndes.eliminarBebidaPorNombre ("120");
			long tiposEliminados = superAndes.eliminarTipoProductoPorNombre ("Vino tinto");
			long [] bebedorVisitasEliminados = superAndes.eliminarBebedorYVisitas_v1 (bdor1.getId ());
			long baresEliminados = superAndes.eliminarBarPorNombre ("Los Amigos");

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de toda la información de un bebedor\n";
			resultado += "Y DE BORRADO DE BEBEDOR Y VISITAS cuando el bebedor aún está referenciado cuando se quiere borrar\n";
			resultado += "v1: No se borra NI el bebedor NI sus visitas";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorTipoProducto)
			{
				resultado += "*** Exception creando tipo de producto !!\n";
				resultado += "*** Es probable que ese tipo de producto ya existiera y hay restricción de UNICIDAD sobre el nombre del tipo de producto\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "\n" + listarTiposProducto (listaTipos);
			resultado += "\n" + listarBebidas (listaBebidas);
			resultado += "\n" + listarBares (listaBares);
			resultado += "\n" + listarBebedores (bebedores);
			resultado += "\n" + listarGustan (listaGustan);
			resultado += "\n" + listarVisitan (listaVisitan);
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\nBuscando toda la información del bebedor con id " + bdor1 + ":\n";
			resultado += bdor2 != null ? "El bebedor es: " + bdor2.toStringCompleto() + "\n" : "Ese bebedor no existe\n";	
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += gustanEliminados + " Gustan eliminados\n";
			resultado += bebidasEliminadas + " Bebidas eliminadas\n";
			resultado += tiposEliminados + " Tipos de Bebida eliminados\n";
			resultado += bebedorVisitasEliminados [0] + " Bebedores eliminados y " + bebedorVisitasEliminados [1] +" Visitas eliminadas\n";
			resultado += baresEliminados + " Bares eliminados\n";
			resultado += "\n\n************ ATENCIÓN - ATENCIÓN - ATENCIÓN - ATENCIÓN ************ \n";
			resultado += "\nRecuerde que -1 registros borrados significa que hubo un problema !! \n";
			resultado += "\nREVISE EL LOG DE PARRANDEROS Y EL DE DATANUCLEUS \n";
			resultado += "\nNO OLVIDE LIMPIAR LA BASE DE DATOS \n";
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

    /**
     * Demostración de creación, consulta de TODA LA INFORMACIÖN de un bebedor y borrado de un bebedor y sus visitas
     * Si hay posibilidades de alguna incoherencia con esta operación SE BORRA LO AQUELLO QUE SEA POSIBLE, 
     * PERO CONSERVANDO LA COHERENCIA DE LA BASE DE DATOS
     * Incluye el manejo de tipos de producto
     * Incluye el manejo de bebidas
     * Incluye el manejo de bares
     * Incluye el manejo de la relación sirven
     * Incluye el manejo de la relación gustan
     * Incluye el borrado de un bebedor y todas sus visitas v2
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos queda con las tuplas que no se pudieron borrar
     */
    public void demoEliminarBebedorYVisitas_v2 ( )
    {
		try 
		{
    		// Ejecución de la demo y recolección de los resultados.
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOCategoria bdor1 = superAndes.adicionarBebedor ("Pepito", "Bogotá", "Alto");
			VOContenedor bar1 = superAndes.adicionarBar ("Los Amigos", "Bogotá", "Bajo", 2);
			boolean errorTipoProducto = false;
			VOTipoProducto tipoProducto = superAndes.adicionarTipoProducto ("Vino tinto");
			if (tipoProducto == null)
			{
				tipoProducto = superAndes.darTipoProductoPorNombre ("Vino tinto");
				errorTipoProducto = true;
			}
			VOCliente bebida1 = superAndes.adicionarBebida ("120", tipoProducto.getId (), 10);
			
			superAndes.adicionarVisitan (bdor1.getId (), bar1.getId (), new Timestamp (System.currentTimeMillis()), "diurno");
			superAndes.adicionarVisitan (bdor1.getId (), bar1.getId (), new Timestamp (System.currentTimeMillis()), "nocturno");
			superAndes.adicionarVisitan (bdor1.getId (), bar1.getId (), new Timestamp (System.currentTimeMillis()), "todos");
			superAndes.adicionarGustan (bdor1.getId (), bebida1.getId ());

			List <VOTipoProducto> listaTipos = superAndes.darVOTiposProducto();
			List <VOCliente> listaBebidas = superAndes.darVOBebidas();
			List <VOContenedor> listaBares = superAndes.darVOBares ();
			List <VOCategoria> bebedores = superAndes.darVOBebedores();
			List <VOEstante> listaGustan = superAndes.darVOGustan();
			List <VOProducto> listaVisitan = superAndes.darVOVisitan();

			VOCategoria bdor2 = superAndes.darBebedorCompleto(bdor1.getId ());

			// No se elimina la tupla de GUSTAN para estudiar la coherencia de las operaciones en la base de daatos
			long gustanEliminados = 0;
			long bebidasEliminadas = superAndes.eliminarBebidaPorNombre ("120");
			long tiposEliminados = superAndes.eliminarTipoProductoPorNombre ("Vino tinto");
			long [] bebedorVisitasEliminados = superAndes.eliminarBebedorYVisitas_v2 (bdor1.getId ());
			long baresEliminados = superAndes.eliminarBarPorNombre ("Los Amigos");

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de toda la información de un bebedor\n";
			resultado += "Y DE BORRADO DE BEBEDOR Y VISITA,S cuando el bebedor aún está referenciado cuando se quiere borrar\n";
			resultado += "v2: El bebedor no se puede borrar, pero sus visitas SÍ";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorTipoProducto)
			{
				resultado += "*** Exception creando tipo de producto !!\n";
				resultado += "*** Es probable que ese tipo de producto ya existiera y hay restricción de UNICIDAD sobre el nombre del tipo de producto\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "\n" + listarTiposProducto (listaTipos);
			resultado += "\n" + listarBebidas (listaBebidas);
			resultado += "\n" + listarBares (listaBares);
			resultado += "\n" + listarBebedores (bebedores);
			resultado += "\n" + listarGustan (listaGustan);
			resultado += "\n" + listarVisitan (listaVisitan);
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\nBuscando toda la información del bebedor con id " + bdor1 + ":\n";
			resultado += bdor2 != null ? "El bebedor es: " + bdor2.toStringCompleto() + "\n" : "Ese bebedor no existe\n";	
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += gustanEliminados + " Gustan eliminados\n";
			resultado += bebidasEliminadas + " Bebidas eliminadas\n";
			resultado += tiposEliminados + " Tipos de Bebida eliminados\n";
			resultado += bebedorVisitasEliminados [0] + " Bebedores eliminados y " + bebedorVisitasEliminados [1] +" Visitas eliminadas\n";
			resultado += baresEliminados + " Bares eliminados\n";
			resultado += "\n\n************ ATENCIÓN - ATENCIÓN - ATENCIÓN - ATENCIÓN ************ \n";
			resultado += "\nRecuerde que -1 registros borrados significa que hubo un problema !! \n";
			resultado += "\nREVISE EL LOG DE PARRANDEROS Y EL DE DATANUCLEUS \n";
			resultado += "\nNO OLVIDE LIMPIAR LA BASE DE DATOS \n";
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

    /**
     * Demostración de la modificación: Cambiar la ciudad de un bebedor
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
    */
    public void demoCambiarCiudadBebedor ( )
    {
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOCategoria bdor1 = superAndes.adicionarBebedor ("Pepito", "Bogotá", "Alto");
			
			List<VOCategoria> bebedores1 = superAndes.darVOBebedores ();
			long bebedoresActualizados = superAndes.cambiarCiudadBebedor (bdor1.getId (), "Medellín");
			List<VOCategoria> bebedores2 = superAndes.darVOBebedores ();
			
			long bebedoresEliminados = superAndes.eliminarBebedorPorNombre ("Pepito");

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de modificación de la ciudad de un bebedor\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "\n" + listarBebedores (bebedores1);
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += bebedoresActualizados + " Bebedores modificados\n";
			resultado += "\n" + listarBebedores (bebedores2);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += bebedoresEliminados + " Bebedores eliminados\n";
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

    /**
     * Demostración de la consulta: Dar la información de los bebedores y del número de bares que visita cada uno
     * Incluye el manejo de los bares
     * Incuye el manejo de la relación visitan
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */
    public void demoBebedoresYNumVisitasRealizadas ( )
    {
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOContenedor bar1 = superAndes.adicionarBar ("Los Amigos1", "Bogotá", "Bajo", 2);
			VOContenedor bar2 = superAndes.adicionarBar ("Los Amigos2", "Bogotá", "Bajo", 3);
			VOContenedor bar3 = superAndes.adicionarBar ("Los Amigos3", "Bogotá", "Bajo", 4);
			VOContenedor bar4 = superAndes.adicionarBar ("Los Amigos4", "Medellín", "Bajo", 5);
			VOCategoria bdor1 = superAndes.adicionarBebedor ("Pepito", "Bogotá", "Alto");
			VOCategoria bdor2 = superAndes.adicionarBebedor ("Juanito", "Bogotá", "Alto");
			VOCategoria bdor3 = superAndes.adicionarBebedor ("Carlitos", "Medellín", "Alto");
			VOCategoria bdor4 = superAndes.adicionarBebedor ("Luis", "Cartagena", "Medio");
			superAndes.adicionarVisitan (bdor1.getId (), bar1.getId (), new Timestamp (System.currentTimeMillis()), "diurno");
			superAndes.adicionarVisitan (bdor1.getId (), bar1.getId (), new Timestamp (System.currentTimeMillis()), "nocturno");
			superAndes.adicionarVisitan (bdor1.getId (), bar1.getId (), new Timestamp (System.currentTimeMillis()), "todos");
			superAndes.adicionarVisitan (bdor1.getId (), bar2.getId (), new Timestamp (System.currentTimeMillis()), "diurno");
			superAndes.adicionarVisitan (bdor1.getId (), bar3.getId (), new Timestamp (System.currentTimeMillis()), "diurno");
			superAndes.adicionarVisitan (bdor2.getId (), bar3.getId (), new Timestamp (System.currentTimeMillis()), "diurno");
			superAndes.adicionarVisitan (bdor2.getId (), bar4.getId (), new Timestamp (System.currentTimeMillis()), "diurno");

			List<VOContenedor> bares = superAndes.darVOBares();
			List<VOCategoria> bebedores = superAndes.darVOBebedores();
			List<VOProducto> visitan = superAndes.darVOVisitan ();
			List<Object []> bebedoresYNumVisitas = superAndes.darBebedoresYNumVisitasRealizadas ();

			long [] elimBdor1 = superAndes.eliminarBebedorYVisitas_v1 (bdor1.getId ());
			long [] elimBdor2 = superAndes.eliminarBebedorYVisitas_v1 (bdor2.getId ());
			long [] elimBdor3 = superAndes.eliminarBebedorYVisitas_v1 (bdor3.getId ());
			long [] elimBdor4 = superAndes.eliminarBebedorYVisitas_v1 (bdor4.getId ());
			long baresEliminados = superAndes.eliminarBarPorNombre ("Los Amigos1");
			baresEliminados += superAndes.eliminarBarPorNombre ("Los Amigos2");
			baresEliminados += superAndes.eliminarBarPorNombre ("Los Amigos3");
			baresEliminados += superAndes.eliminarBarPorNombre ("Los Amigos4");

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de dar bebedores y cuántas visitan han realizado\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "\n" + listarBares (bares);
			resultado += "\n" + listarBebedores (bebedores);
			resultado += "\n" + listarVisitan (visitan);
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarBebedorYNumVisitas (bebedoresYNumVisitas);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += elimBdor1 [0] + " Bebedores eliminados y " + elimBdor1 [1] +" Visitas eliminadas\n";
			resultado += elimBdor2 [0] + " Bebedores eliminados y " + elimBdor2 [1] +" Visitas eliminadas\n";
			resultado += elimBdor3 [0] + " Bebedores eliminados y " + elimBdor3 [1] +" Visitas eliminadas\n";
			resultado += elimBdor4 [0] + " Bebedores eliminados y " + elimBdor4 [1] +" Visitas eliminadas\n";
			resultado += baresEliminados + " Bares eliminados\n";
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

    /**
     * Demostración de la consulta: Para cada ciudad, cuántos bebedores vistan bares
     * Incluye el manejo de bares
     * Incluye el manejo de la relación visitan
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */
    public void demoBebedoresDeCiudad ( )
    {
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOContenedor bar1 = superAndes.adicionarBar ("Los Amigos1", "Bogotá", "Bajo", 2);
			VOContenedor bar2 = superAndes.adicionarBar ("Los Amigos2", "Bogotá", "Bajo", 3);
			VOContenedor bar3 = superAndes.adicionarBar ("Los Amigos3", "Bogotá", "Bajo", 4);
			VOContenedor bar4 = superAndes.adicionarBar ("Los Amigos4", "Medellín", "Bajo", 5);
			VOCategoria bdor1 = superAndes.adicionarBebedor ("Pepito", "Bogotá", "Alto");
			VOCategoria bdor2 = superAndes.adicionarBebedor ("Juanito", "Medellín", "Alto");
			VOCategoria bdor3 = superAndes.adicionarBebedor ("Pedrito", "Medellín", "Alto");
			superAndes.adicionarVisitan (bdor1.getId (), bar1.getId (), new Timestamp (System.currentTimeMillis()), "diurno");
			superAndes.adicionarVisitan (bdor1.getId (), bar2.getId (), new Timestamp (System.currentTimeMillis()), "diurno");
			superAndes.adicionarVisitan (bdor1.getId (), bar3.getId (), new Timestamp (System.currentTimeMillis()), "diurno");
			superAndes.adicionarVisitan (bdor2.getId (), bar3.getId (), new Timestamp (System.currentTimeMillis()), "diurno");
			superAndes.adicionarVisitan (bdor1.getId (), bar4.getId (), new Timestamp (System.currentTimeMillis()), "diurno");

			List<VOContenedor> bares = superAndes.darVOBares();
			List<VOCategoria> bebedores = superAndes.darVOBebedores();
			List<VOProducto> visitan = superAndes.darVOVisitan ();
			long bebedoresBogota = superAndes.darCantidadBebedoresCiudadVisitanBares ("Bogotá");
			long bebedoresMedellin = superAndes.darCantidadBebedoresCiudadVisitanBares ("Medellín");

			long [] elimBdor1 = superAndes.eliminarBebedorYVisitas_v1 (bdor1.getId ());
			long [] elimBdor2 = superAndes.eliminarBebedorYVisitas_v1 (bdor2.getId ());
			long elimBdor3 = superAndes.eliminarBebedorPorId (bdor3.getId ());
			long baresEliminados = superAndes.eliminarBarPorNombre ("Los Amigos1");
			baresEliminados += superAndes.eliminarBarPorNombre ("Los Amigos2");
			baresEliminados += superAndes.eliminarBarPorNombre ("Los Amigos3");
			baresEliminados += superAndes.eliminarBarPorNombre ("Los Amigos4");

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de dar cantidad de bebedores de una ciudad que vistan baresn\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "\n" + listarBares (bares);
			resultado += "\n" + listarBebedores (bebedores);
			resultado += "\n" + listarVisitan (visitan);
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\nBebedores de Bogotá: " + bebedoresBogota;
			resultado += "\nBebedores de Medellín: " + bebedoresMedellin;
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += elimBdor1 [0] + " Bebedores eliminados y " + elimBdor1 [1] +" Visitas eliminadas\n";
			resultado += elimBdor2 [0] + " Bebedores eliminados y " + elimBdor2 [1] +" Visitas eliminadas\n";
			resultado += elimBdor3 + " Bebedores eliminados\n";
			resultado += baresEliminados + " Bares eliminados\n";
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
	 * 			Demos de Gustan
	 *****************************************************************/
    /**
     * Demostración de creación, consulta y borrado de la relación Gustan
     * Incluye el manejo de Bebedores
     * Incluye el manejo de Bebidas
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */
    public void demoGustan ( )
    {
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			boolean errorTipoProducto = false;
			VOTipoProducto tipoProducto = superAndes.adicionarTipoProducto ("Vino tinto");
			if (tipoProducto == null)
			{
				tipoProducto = superAndes.darTipoProductoPorNombre ("Vino tinto");
				errorTipoProducto = true;
			}
			VOCliente bebida1 = superAndes.adicionarBebida ("120", tipoProducto.getId (), 10);
			VOCategoria bdor1 = superAndes.adicionarBebedor ("Pepito", "Bogotá", "Alto");
			superAndes.adicionarGustan (bdor1.getId (), bebida1.getId ());

			List <VOTipoProducto> listaTiposProducto = superAndes.darVOTiposProducto ();
			List <VOCliente> listaBebidas = superAndes.darVOBebidas ();
			List <VOCategoria> listaBebedores = superAndes.darVOBebedores ();
			List <VOEstante> listaGustan = superAndes.darVOGustan();
			long gustanEliminados = superAndes.eliminarGustan (bdor1.getId (), bebida1.getId ());
			long bebidasEliminadas = superAndes.eliminarBebidaPorNombre ("120");
			long tbEliminados = superAndes.eliminarTipoProductoPorNombre ("Vino tinto");
			long bebedoresEliminados = superAndes.eliminarBebedorPorNombre ("Pepito");
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Gustan\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorTipoProducto)
			{
				resultado += "*** Exception creando tipo de producto !!\n";
				resultado += "*** Es probable que ese tipo de producto ya existiera y hay restricción de UNICIDAD sobre el nombre del tipo de producto\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "\n" + listarTiposProducto (listaTiposProducto);
			resultado += "\n" + listarBebidas (listaBebidas);
			resultado += "\n" + listarBebedores (listaBebedores);
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarGustan (listaGustan);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += gustanEliminados + " Gustan eliminados\n";
			resultado += bebidasEliminadas + " Bebidas eliminadas\n";
			resultado += tbEliminados + " Tipos de bebida eliminados\n";
			resultado += bebedoresEliminados + " Bebedores eliminados\n";
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
	 * 			Demos de Sirven
	 *****************************************************************/
    /**
     * Demostración de creación, consulta y borrado de la relación Sirven
     * Incluye el manejo de Bares
     * Incluye el manejo de tipos de producto
     * Incluye el manejo de Bebidas
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */
    public void demoSirven ( )
    {
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			boolean errorTipoProducto = false;
			VOTipoProducto tipoProducto = superAndes.adicionarTipoProducto ("Vino tinto");
			if (tipoProducto == null)
			{
				tipoProducto = superAndes.darTipoProductoPorNombre ("Vino tinto");
				errorTipoProducto = true;
			}
			VOCliente bebida1 = superAndes.adicionarBebida ("120", tipoProducto.getId (), 10);
			VOContenedor bar1 = superAndes.adicionarBar ("Los Amigos1", "Bogotá", "Bajo", 2);
			superAndes.adicionarSirven (bar1.getId (), bebida1.getId (), "diurno");

			List <VOTipoProducto> listaTiposProducto = superAndes.darVOTiposProducto ();
			List <VOCliente> listaBebidas = superAndes.darVOBebidas ();
			List <VOContenedor> listaBares = superAndes.darVOBares ();
			List <VOPedido> listaSirven = superAndes.darVOSirven();
			
			long sirvenEliminados = superAndes.eliminarSirven (bar1.getId (), bebida1.getId ());
			long bebidasEliminadas = superAndes.eliminarBebidaPorNombre ("120");
			long tbEliminados = superAndes.eliminarTipoProductoPorNombre ("Vino tinto");
			long baresEliminados = superAndes.eliminarBarPorNombre ("Los Amigos1");
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Sirven\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorTipoProducto)
			{
				resultado += "*** Exception creando tipo de producto !!\n";
				resultado += "*** Es probable que ese tipo de producto ya existiera y hay restricción de UNICIDAD sobre el nombre del tipo de producto\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "\n" + listarTiposProducto (listaTiposProducto);
			resultado += "\n" + listarBebidas (listaBebidas);
			resultado += "\n" + listarBares (listaBares);
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarSirven (listaSirven);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += sirvenEliminados + " Sirven eliminados\n";
			resultado += bebidasEliminadas + " Bebidas eliminadas\n";
			resultado += tbEliminados + " Tipos de bebida eliminados\n";
			resultado += baresEliminados + " Bares eliminados\n";
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
	 * 			Demos de Visitan
	 *****************************************************************/
    /**
     * Demostración de creación, consulta y borrado de la relación Visitan
     * Incluye el manejo de Bebedores
     * Incluye el manejo de Bares
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */
    public void demoVisitan ( )
    {
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			VOContenedor bar1 = superAndes.adicionarBar ("Los Amigos1", "Bogotá", "Bajo", 2);
			VOCategoria bdor1 = superAndes.adicionarBebedor ("Pepito", "Bogotá", "Alto");
			superAndes.adicionarVisitan (bdor1.getId (), bar1.getId (), new Timestamp (System.currentTimeMillis()), "diurno");

			List <VOContenedor> listaBares = superAndes.darVOBares ();
			List <VOCategoria> listaBebedores = superAndes.darVOBebedores ();
			List <VOProducto> listaVisitan = superAndes.darVOVisitan();
			long visitanEliminados = superAndes.eliminarVisitan (bdor1.getId (), bar1.getId ());
			long baresEliminados = superAndes.eliminarBarPorNombre ("Los Amigos1");
			long bebedoresEliminadas = superAndes.eliminarBebedorPorNombre ("Pepito");
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Visitan\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "\n" + listarBares (listaBares);
			resultado += "\n" + listarBebedores (listaBebedores);
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarVisitan (listaVisitan);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += visitanEliminados + " Visitan eliminados\n";
			resultado += bebedoresEliminadas + " Bebedores eliminadas\n";
			resultado += baresEliminados + " Bares eliminados\n";
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
			resultado += eliminados [0] + " Gustan eliminados\n";
			resultado += eliminados [1] + " Sirven eliminados\n";
			resultado += eliminados [2] + " Visitan eliminados\n";
			resultado += eliminados [3] + " Bebidas eliminadas\n";
			resultado += eliminados [4] + " Tipos de bebida eliminados\n";
			resultado += eliminados [5] + " Bebedores eliminados\n";
			resultado += eliminados [6] + " Bares eliminados\n";
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
		mostrarArchivo ("data/EsquemaSuperAndes.txt");
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
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: SuperAndes Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Germán Bravo\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Claudia Jiménez, Christian Ariza\n";
		resultado += "\n ************************************\n\n";

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
     * Genera una cadena de caracteres con la lista de bebidas recibida: una línea por cada bebida
     * @param lista - La lista con las bebidas
     * @return La cadena con una líea para cada bebida recibida
     */
    private String listarBebidas (List<VOCliente> lista) 
    {
    	String resp = "Las bebidas existentes son:\n";
    	int i = 1;
        for (VOCliente beb : lista)
        {
        	resp += i++ + ". " + beb.toString() + "\n";
        }
        return resp;
	}

    /**
     * Genera una cadena de caracteres con la lista de bebedores recibida: una línea por cada bebedor
     * @param lista - La lista con los bebedores
     * @return La cadena con una líea para cada bebedor recibido
     */
    private String listarBebedores (List<VOCategoria> lista) 
    {
    	String resp = "Los bebedores existentes son:\n";
    	int i = 1;
        for (VOCategoria bdor : lista)
        {
        	resp += i++ + ". " + bdor.toString() + "\n";
        }
        return resp;
	}

    /**
     * Genera una cadena de caracteres con la lista de bares recibida: una línea por cada bar
     * @param lista - La lista con los bares
     * @return La cadena con una líea para cada bar recibido
     */
    private String listarBares (List<VOContenedor> lista) 
    {
    	String resp = "Los bares existentes son:\n";
    	int i = 1;
        for (VOContenedor bar : lista)
        {
        	resp += i++ + ". " + bar.toString() + "\n";
        }
        return resp;
	}

    /**
     * Genera una cadena de caracteres con la lista de gustan recibida: una línea por cada gusta
     * @param lista - La lista con los gustan
     * @return La cadena con una líea para cada gustan recibido
     */
    private String listarGustan (List<VOEstante> lista) 
    {
    	String resp = "Los gustan existentes son:\n";
    	int i = 1;
        for (VOEstante serv : lista)
        {
        	resp += i++ + ". " + serv.toString() + "\n";
        }
        return resp;
	}

    /**
     * Genera una cadena de caracteres con la lista de sirven recibida: una línea por cada sirven
     * @param lista - La lista con los sirven
     * @return La cadena con una líea para cada sirven recibido
     */
    private String listarSirven (List<VOPedido> lista) 
    {
    	String resp = "Los sirven existentes son:\n";
    	int i = 1;
        for (VOPedido serv : lista)
        {
        	resp += i++ + ". " + serv.toString() + "\n";
        }
        return resp;
	}

    /**
     * Genera una cadena de caracteres con la lista de visitan recibida: una línea por cada visitan
     * @param lista - La lista con los visitan
     * @return La cadena con una líea para cada visitan recibido
     */
    private String listarVisitan (List<VOProducto> lista) 
    {
    	String resp = "Los visitan existentes son:\n";
    	int i = 1;
        for (VOProducto vis : lista)
        {
        	resp += i++ + ". " + vis.toString() + "\n";
        }
        return resp;
	}

    /**
     * Genera una cadena de caracteres con la lista de parejas de números recibida: una línea por cada pareja
     * @param lista - La lista con las pareja
     * @return La cadena con una líea para cada pareja recibido
     */
    private String listarBaresYBebidas (List<long[]> lista) 
    {
    	String resp = "Los bares y el número de bebidas que sirven son:\n";
    	int i = 1;
        for ( long [] tupla : lista)
        {
			long [] datos = tupla;
	        String resp1 = i++ + ". " + "[";
			resp1 += "idBar: " + datos [0] + ", ";
			resp1 += "numBebidas: " + datos [1];
	        resp1 += "]";
	        resp += resp1 + "\n";
        }
        return resp;
	}

    /**
     * Genera una cadena de caracteres con la lista de parejas de objetos recibida: una línea por cada pareja
     * @param lista - La lista con las parejas (Bebedor, numero visitas)
     * @return La cadena con una línea para cada pareja recibido
     */
    private String listarBebedorYNumVisitas (List<Object[]> lista) 
    {
    	String resp = "Los bebedores y el número visitas realizadas son:\n";
    	int i = 1;
        for (Object [] tupla : lista)
        {
			VOCategoria bdor = (VOCategoria) tupla [0];
			int numVisitas = (int) tupla [1];
	        String resp1 = i++ + ". " + "[";
			resp1 += bdor + ", ";
			resp1 += "numVisitas: " + numVisitas;
	        resp1 += "]";
	        resp += resp1 + "\n";
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
			Method req = InterfazSuperAndesDemo.class.getMethod ( evento );			
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
            InterfazSuperAndesDemo interfaz = new InterfazSuperAndesDemo( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}
