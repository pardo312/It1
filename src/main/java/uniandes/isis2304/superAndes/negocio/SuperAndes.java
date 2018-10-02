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

package uniandes.isis2304.superAndes.negocio;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

/**
 * Clase principal del negocio
 * Sarisface todos los requerimientos funcionales del negocio
 *
 * @author Germán Bravo
 */
public class SuperAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(SuperAndes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaSuperAndes pp;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public SuperAndes ()
	{
		pp = PersistenciaSuperAndes.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public SuperAndes (JsonObject tableConfig)
	{
		pp = PersistenciaSuperAndes.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}
	
	//Metodos de Proveedor
	
	public Proveedor registrarProveedor (String nombre)
	{
        log.info ("Adicionando el proveedor: " + nombre);
        Proveedor proveedor = pp.registrarProveedor (nombre);		
        log.info ("Adicionando el proveedor: " + proveedor);
        return proveedor;
	}
	
	public List<VOProveedor> darVOProveedor ()
	{
		log.info ("Generando los VO de Proveedor");        
        List<VOProveedor> voTipos = new LinkedList<VOProveedor> ();
        for (Proveedor tb : pp.darProveedores ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de Tipos de bebida: " + voTipos.size() + " existentes");
        return voTipos;
	}

//	//Metodos de Productos
	public Producto registrarProducto (String codigoDeBarras,
			
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
        log.info ("Adicionando el Producto: " + nombre);
        Producto Producto = pp.registrarProducto (codigoDeBarras,nombre,
marca,precioUnitario,presentacion,precioPorUnidad,cantidadEnLaPresentacion,
unidadesDeMedida,especificacionesDeEmpacado,nivelDeReorden,
 IDPedido, IDSucursal, IDContenedor,EnStock);		
        log.info ("Adicionando el Producto: " + Producto);
        return Producto;
	}

	//Metodos Categoria
	
	public List<VOCategoria> darVOCategoria ()
	{
		log.info ("Generando los VO de Categoria");        
        List<VOCategoria> voTipos = new LinkedList<VOCategoria> ();
        for (Categoria tb : pp.darCategorias ())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de Categorias: " + voTipos.size() + " existentes");
        return voTipos;
	}
	
	
	public Categoria registrarCategoria (String nombreCategoria, char perecedero, String codigoDeBarras)
	{
        log.info ("Adicionando la categoria: " + nombreCategoria);
        Categoria categoria = pp.registrarCategoria (nombreCategoria,perecedero,codigoDeBarras);		
        log.info ("Adicionando la categoria : " + categoria);
        return categoria;
	}
	
	public TipoProducto registrarTipo(String nombreTipo, String metodoAlmac, long idCategoria, long idContenedor) {
		 log.info ("Adicionando el tipo de producto: " + nombreTipo);
	        TipoProducto tipoProd = pp.registrarTipoProducto (nombreTipo,metodoAlmac,idCategoria,idContenedor);		
	        log.info ("Adicionando el tipo de producto : " + tipoProd);
	        return tipoProd;
	}
	
	
	
	public VOPaguexunidadesllevey registrarPromocionCantidad(int x, int y, int p) {
		 log.info ("Adicionando la promocion ");
		 
		 if(p == 1)
		 {
			 Paguexunidadesllevey promocion = pp.registrarPromocionPXULY (x,y);	
			 log.info ("Adicionando el tipo de producto : " + promocion);
		        return promocion;
		 }
		 else
		 {
			 Paguexcantidadllevey promocion = pp.registrarPromocionPXCLY (x,y);	
			 log.info ("Adicionando el tipo de producto : " + promocion);
		        return promocion;
		 }
		 	
	       
			
	}
	public VODescuentodelxporciento registrarPromocionPorcentaje(int porcentaje, int p) {
		log.info ("Adicionando la promocion ");
		 
		 if(p == 2)
		 {
			 VODescuentodelxporciento promocion = pp.registrarPromocionDDX (porcentaje);	
			 log.info ("Adicionando el tipo de producto : " + promocion);
		        return promocion;
		 }
		 else
		 {
			 Pague1llevesegundoaxporciento promocion = pp.registrarPromocionP1L2AX (porcentaje);	
			 log.info ("Adicionando el tipo de producto : " + promocion);
		        return promocion;
		 }
	}
	//Limpiar SuperAndes
	public long [] limpiarSuperAndes ()
	{
        log.info ("Limpiando la BD de SuperAndes");
        long [] borrrados = pp.limpiarSuperAndes();	
        log.info ("Limpiando la BD de SuperAndes: Listo!");
        return borrrados;
	}

	

	

	

	
}
