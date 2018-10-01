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

//	//Metodos de Productos
//	public Producto adicionarProducto (String codigoDeBarras,
//			
//			 String nombre,
//			
//			 String marca,
//			
//			 float precioUnitario,
//			
//			 String presentacion,
//			
//			 float precioPorUnidad,
//			
//			 float cantidadEnLaPresentacion,
//			
//			 String unidadesDeMedida,
//
//			 String especificacionesDeEmpacado,
//			
//			 float nivelDeReorden,
//			
//			 long IDPedido,
//			
//			 long IDSucursal,
//			
//			 long IDContenedor)
//	{
//        log.info ("Adicionando el Producto: " + nombre);
//        Producto Producto = pp.adicionarProducto (codigoDeBarras,nombre,
//marca,precioUnitario,presentacion,precioPorUnidad,cantidadEnLaPresentacion,
//unidadesDeMedida,especificacionesDeEmpacado,nivelDeReorden,
// IDPedido, IDSucursal, IDContenedor);		
//        log.info ("Adicionando el Producto: " + Producto);
//        return Producto;
//	}

//	//Metodos de cliente
//	
//	public Cliente adicionarCliente (long id,
//			
//			 int puntosDeCompra,
//			
//			 long cedulaCliente,
//			
//			 String NITCliente)
//	{
//        log.info ("Adicionando el Cliente: " + id);
//        Cliente Cliente = pp.adicionarCliente ( id,puntosDeCompra,
//        		cedulaCliente, NITCliente);		
//        log.info ("Adicionando el Cliente: " + Cliente);
//        return Cliente;
//	}

//	//Metodos de Sucursal
//	
//	public Sucursal adicionarSucursal ( String nombre,
//			
//			 String ciudad,
//			
//			 String direccion,
//			
//			 String segmentacionDeMercado,
//			
//			 String tamanioInstalacion,
//			
//			 long NITSupermercado)
//	{
//        log.info ("Adicionando el Sucursal: " + nombre);
//        Sucursal Sucursal = pp.adicionarSucursal (id,
//        		
//        		  nombre,
//        		
//        		  ciudad,
//        		
//        		  direccion,
//        		
//        		  segmentacionDeMercado,
//        		
//        		  tamanioInstalacion,
//        		
//        		  NITSupermercado
//        		);		
//        log.info ("Adicionando el Sucursal: " + Sucursal);
//        return Sucursal;
//	}
	//Metodos Promocion
	
//	public Promocion adicionarPromocion (
//			
//			 String descripcion,
//
//			 String precioPromocion,
//			
//			 long idSucursal)
//	{
//        log.info ("Adicionando la Promocion: " + descripcion);
//        Promocion Promocion = pp.adicionarPromocion (
//        		
//        		  descripcion,
//
//        		  precioPromocion,
//        		
//        		  idSucursal);		
//        log.info ("Adicionando la Promocion: " + Promocion);
//        return Promocion;
//	}
//	
	
	//Limpiar SuperAndes
	public long [] limpiarSuperAndes ()
	{
        log.info ("Limpiando la BD de SuperAndes");
        long [] borrrados = pp.limpiarSuperAndes();	
        log.info ("Limpiando la BD de SuperAndes: Listo!");
        return borrrados;
	}
}
