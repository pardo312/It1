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
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hamcrest.core.IsAnything;

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
		log.info ("Generando los VO de Proveedor: " + voTipos.size() + " existentes");
		return voTipos;
	}


	public List<Producto> darVOProducto() {
		log.info ("Generando los VO de Producto");        
		List<Producto> voProd = new LinkedList<Producto> ();
		for (Producto tb : pp.darProductos ())
		{
			voProd.add (tb);
		}
		log.info ("Generando los VO de Productos: " + voProd.size() + " existentes");
		return voProd;
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

			long IDContenedor,long IDPromocion,
			int EnStock,int volumen,long IDCarrito)
	{
		log.info ("Adicionando el Producto: " + nombre);
		Producto Producto = pp.registrarProducto (codigoDeBarras,nombre,
				marca,precioUnitario,presentacion,precioPorUnidad,cantidadEnLaPresentacion,
				unidadesDeMedida,especificacionesDeEmpacado,nivelDeReorden,
				IDPedido, IDSucursal, IDContenedor,EnStock,IDPromocion,volumen,IDCarrito);		
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

	public List<VOSucursal> darVOSucursal() {
		log.info ("Generando los VO de sucursal");        
		List<VOSucursal> voTipos = new LinkedList<VOSucursal> ();
		for (Sucursal tb : pp.darSucursal ())
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de sucursal: " + voTipos.size() + " existentes");
		return voTipos;
	}

	// dar VO cliente
	public List<VOCliente> darVOCliente() {
		log.info ("Generando los VO de cliente");        
		List<VOCliente> voTipos = new LinkedList<VOCliente> ();
		for (Cliente tb : pp.darClientes ())
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de Clientes: " + voTipos.size() + " existentes");
		return voTipos;
	}


	public Cliente registrarCliente(int idCliente, int puntosDeCompra, String nitCliente, int cedulaCliente)
	{
		log.info ("Adicionando el cliente: " + cedulaCliente);
		Cliente cliente = pp.registrarCliente (idCliente,puntosDeCompra,nitCliente,cedulaCliente);		
		log.info ("Adicionando el cliente: " + cliente);
		return cliente;
	}
	public ClienteNatural registrarClienteNatural(int cedula, String nombre, String email)
	{
		log.info ("Adicionando el cliente: " + cedula);
		ClienteNatural clienteNatural = pp.registrarClienteNatural(cedula, nombre, email) ;		
		log.info ("Adicionando el cliente: " + cedula);
		return clienteNatural;
	}	

	public ClienteEmpresa registrarClienteEmpresa(String NIT, String direccion)
	{
		log.info ("Adicionando el cliente: " + NIT);
		ClienteEmpresa clienteEmpresa = pp.registrarClienteEmpresa(NIT, direccion) ;		
		log.info ("Adicionando el cliente: " + NIT);
		return clienteEmpresa;
	}
	
	public CarritoDeCompras registrarCarritoDeCompras(long idCarrito, int usado, String NIT, int cedula)
	{
		log.info ("Adicionando el carrito: " + idCarrito);
		CarritoDeCompras carritoDeCompras = pp.registrarCarritoDeCompras(idCarrito, usado, NIT, cedula);		
		log.info ("Adicionando el carrito: " + idCarrito);
		return carritoDeCompras;
	}
	
	public Sucursal registrarSucursal(int id,String nombre,String ciudad,String direccion, String segmentacionDeMercado,String tamanioInstalacion,int NITSupermercado)
	{
		log.info ("Adicionando la sucursal: " + nombre);
		Sucursal sucursal = pp.registrarSucursal(id, nombre, ciudad, direccion, segmentacionDeMercado, tamanioInstalacion, NITSupermercado);		
		log.info ("Adicionando la sucursal: " + nombre);
		return sucursal;
	}
	public Contenedor registrarContenedor(int id, int capacidadVolumen,
			int capacidadPeso, String unidadesPeso, String unidadesVolumen,
			int idBodegaSucursal) {
		log.info ("Adicionando el contenedor: " + id);
		Contenedor contenedor = pp.registrarContenedor(id, capacidadVolumen, capacidadPeso, unidadesPeso, unidadesVolumen, idBodegaSucursal);		
		log.info ("Adicionando el contenedor: " + id);
		return contenedor;
	}
	public Pedido registrarPedido(int id,java.util.Date fechaEsperada,java.util.Date fechaEntrega,String evaluacionCantidad, String evaluacionCalidad,int calificacion,int finalizado, int NITProveedor)
	{
		log.info ("Adicionando el pedido: " + id);
		Pedido pedido = pp.registrarPedido(id, fechaEsperada, fechaEntrega, evaluacionCantidad, evaluacionCalidad, calificacion, finalizado, NITProveedor);		
		log.info ("Adicionando el pedido: " + id);
		return pedido;
	}
	public Pedido actualizarPedido(int id,java.util.Date fechaEsperada,java.util.Date fechaEntrega,String evaluacionCantidad, String evaluacionCalidad,int calificacion,int finalizado, int NITProveedor)
	{
		log.info ("Actualizando el pedido: " + id);
		Pedido pedido = pp.actualizarPedido(id, fechaEsperada, fechaEntrega, evaluacionCantidad, evaluacionCalidad, calificacion, finalizado, NITProveedor);		
		log.info ("Actualizando el pedido: " + id);
		return pedido;
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
	//Fin producto

	public VOEstante registrarEstante(long idEstante, float nivelReabastecimiento, long idSucursal) {
		log.info ("Adicionando el Estante: " + idEstante);
		Estante estante = pp.registrarEstante (idEstante,nivelReabastecimiento,idSucursal);		
		log.info ("Adicionando el proveedor: " + estante);
		return estante;
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
			log.info ("Adicionando la promocion  : " + promocion);
			return promocion;
		}
		else
		{
			Pague1llevesegundoaxporciento promocion = pp.registrarPromocionP1L2AX (porcentaje);	
			log.info ("Adicionando la promocion : " + promocion);
			return promocion;
		}
	}


	public void eliminarPromocion(long idPromocion) {
		log.info ("Eliminando la promocion: " + idPromocion);
		pp.eliminarPromocion (idPromocion);		
		log.info ("Eliminando la promocion ");

	}
	
	
	public Factura registrarFactura(String numeroDeFactura, java.util.Date fecha, long idCliente) {
		log.info ("Adicionando la factura: " + numeroDeFactura);
		Factura factura = pp.registrarFactura (numeroDeFactura,fecha,idCliente);		
		log.info ("Adicionando la factura:  " + factura);
		return factura;
	}
	
	public FacturaProducto registrarFacturaProd(String numeroDeFactura, String codigoDeBarras) {
		log.info ("Adicionando la factura: " + numeroDeFactura);
		FacturaProducto factura = pp.registrarFacturaProd (numeroDeFactura,codigoDeBarras);		
		log.info ("Adicionando la factura:  " + factura);
		return factura;
	}
	
	public void quitarProductosDeEstante(int volumenNuevo ,String codigoDeBarras )
	{
		
      
        log.info ("quitandoProdDeEstante con codigo de barras: " + codigoDeBarras);
		Producto estante = pp.quitarProductosDeEstante(volumenNuevo,codigoDeBarras);	
		log.info ("quitandoProdDeEstante:  " + estante);
		
	}
	public void devolverProducto(String codigoDeBarras)
	{
		
      
        log.info ("quitandoProdDeEstante con codigo de barras: " + codigoDeBarras);
		pp.devolverProducto(codigoDeBarras);	
		log.info ("quitandoProdDeEstante:  ");
		
	}
	public List<Producto> busquedaProducto(String codigoDeBarras,String nombre,int opcion)
	{
		
			log.info ("Generando consulta");        
	        List<Producto> voProd = new LinkedList<Producto> ();
	        for (Producto tb : pp.busquedaProducto(codigoDeBarras,nombre ,opcion))
	        {
	        	voProd.add (tb);
	        }
	        log.info ("Generando Consulta: " + voProd.size() + " existentes");
	        return voProd;
	
		
	}
	
	
	
	public List<Producto> busaquedaCarrito(long idCarrito)
	{
		
			log.info ("Generando consulta");        
	        List<Producto> voProd = new LinkedList<Producto> ();
	        for (Producto tb : pp.busquedaCarrito(idCarrito))
	        {
	        	voProd.add (tb);
	        }
	        log.info ("Generando Consulta: " + voProd.size() + " existentes");
	        return voProd;
	
	}
	

	public void pagarCarrito(long idCarrito) {
		log.info ("pagando el carrito con idCarrito: " + idCarrito);
		pp.pagarCarrito(idCarrito);	
		log.info ("pagando el carrito con idCarrito  " + idCarrito);		
	}

	public void recogerProductos() 
	{
		log.info ("recogiendo los productos " );
		pp.recogerProductos();	
		log.info ("recogiendo los productos" );		
	}

	
	public void abandonarCarrito(long idCarrito)
	{
		
      
        log.info ("abandonando carrito: " + idCarrito);
		pp.abandonarCarrito(idCarrito);	
		log.info ("Carrito Abandonado ");
		
	}
	
	public long consolidacionPedidosProveedor(int id, java.util.Date fechaEsperada,java.util.Date fechaEntrega,String evaluacionCantidad, String evaluacionCalidad, int calificacion, int finalizado,int NITProveedor )
	{
		
			log.info ("Generando consulta");        
	        long voProd = pp.consolidacionPedidosProveedor(id, fechaEsperada, fechaEntrega, evaluacionCantidad, evaluacionCalidad, calificacion, finalizado, NITProveedor);
	       
	        log.info ("Generando Consulta: " + voProd + " existentes");
	        return voProd;
	
		
	}
	public List<Pedido> darPedidos() {
		log.info ("Generando los Pedidos");        
		List<Pedido> voProd = new LinkedList<Pedido> ();
		
		for (Pedido tb : pp.darPedidos())
		{	
			if(tb != null)
			{
				voProd.add (tb);
			}
			
		}
		log.info ("Generando Pedidos: " + voProd.size() + " existentes");
		return voProd;
	}

	
	public List<uniandes.isis2304.superAndes.negocio.Consulta1> consulta1()
	{
		log.info ("Generando consulta");        
        List<Consulta1> voProd = new LinkedList<Consulta1> ();
        for (Consulta1 tb : pp.Consulta1())
        {
        	voProd.add (tb);
        }
        log.info ("Generando Consulta: " + voProd.size() + " existentes");
        return voProd;
	}
	public List<uniandes.isis2304.superAndes.negocio.Consulta2> consulta2()
	{
		log.info ("Generando consulta");        
        List<Consulta2> voProd = new LinkedList<Consulta2> ();
        for (Consulta2 tb : pp.Consulta2())
        {
        	voProd.add (tb);
        }
        log.info ("Generando Consulta: " + voProd.size() + " existentes");
        return voProd;
	}
	public List<uniandes.isis2304.superAndes.negocio.Consulta3> consulta3() {
		log.info ("Generando consulta");        
        List<Consulta3> voProd = new LinkedList<Consulta3> ();
        for (Consulta3 tb : pp.Consulta3())
        {
        	voProd.add (tb);
        }
        log.info ("Generando Consulta: " + voProd.size() + " existentes");
        return voProd;
	}
	
	
	public List<Producto> consulta4(String r) {
		log.info ("Generando consulta");        
        List<Producto> voProd = new LinkedList<Producto> ();
        for (Producto tb : pp.Consulta4(r))
        {
        	voProd.add (tb);
        }
        log.info ("Generando Consulta: " + voProd.size() + " existentes");
        return voProd;
	}
	public List<uniandes.isis2304.superAndes.negocio.Consulta5> consulta5() {
		log.info ("Generando consulta");        
        List<Consulta5> voProd = new LinkedList<Consulta5> ();
        for (Consulta5 tb : pp.Consulta5())
        {
        	voProd.add (tb);
        }
        log.info ("Generando Consulta: " + voProd.size() + " existentes");
        return voProd;
	}
	public List<uniandes.isis2304.superAndes.negocio.Consulta6> consulta6(String fechaInicial, String fechaFinal) throws ParseException {
		log.info ("Generando consulta");        
        List<Consulta6> voProd = new LinkedList<Consulta6> ();
        for (Consulta6 tb : pp.Consulta6(fechaInicial, fechaFinal))
        {
        	voProd.add (tb);
        }
        log.info ("Generando Consulta: " + voProd.size() + " existentes");
        return voProd;
	}
	
	
	public List<uniandes.isis2304.superAndes.negocio.Consulta7> consulta7(String unidadTiempo, String tipoProducto) {
		log.info ("Generando consulta");        
        List<Consulta7> voProd = new LinkedList<Consulta7> ();
        for (Consulta7 tb : pp.Consulta7(unidadTiempo, tipoProducto))
        {
        	voProd.add (tb);
        }
        log.info ("Generando Consulta: " + voProd.size() + " existentes");
        return voProd;
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
