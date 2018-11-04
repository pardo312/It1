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

	public Proveedor registrarProveedor (String nombre, int opcion)
	{
		log.info ("Adicionando el proveedor: " + nombre);
		Proveedor proveedor = pp.registrarProveedor (nombre,opcion);		
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
	
	public List<Factura> darFacturas() {
		log.info ("Generando los VO de Producto");        
		List<Factura> voProd = new LinkedList<Factura> ();
		for (Factura tb : pp.darFacturas ())
		{
			voProd.add (tb);
		}
		log.info ("Generando las facturas: " + voProd.size() + " existentes");
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
	

	public List<VOClienteNatural> darVOClienteNatural() {
		log.info ("Generando los VO de cliente Natural");        
		List<VOClienteNatural> voTipos = new LinkedList<VOClienteNatural> ();
		for (ClienteNatural tb : pp.darClientesNaturales ())
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de Clientes: " + voTipos.size() + " existentes");
		return voTipos;
	}

	public List<VOClienteEmpresa> darVOClienteEmpresarial() {
		log.info ("Generando los VO de cliente Empresarial");        
		List<VOClienteEmpresa> voTipos = new LinkedList<VOClienteEmpresa> ();
		for (ClienteEmpresa tb : pp.darClientesEmpresa ())
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de Clientes Empresa: " + voTipos.size() + " existentes");
		return voTipos;
	}
	public List<ClienteEmpresa> darClientesEmpresariales() {
		log.info ("Generando los VO de Cliente");        
		List<ClienteEmpresa> voProd = new LinkedList<ClienteEmpresa> ();
		for (ClienteEmpresa tb : pp.darClientesEmpresariales())
		{
			voProd.add (tb);
		}
		log.info ("Generando los VO de cliente empresa: " + voProd.size() + " existentes");
		return voProd;

	}

	
	public List<Estante> darEstantes() {
		log.info ("Generando los VO de Producto");        
		List<Estante> voProd = new LinkedList<Estante> ();
		for (Estante tb : pp.darEstantes ())
		{
			voProd.add (tb);
		}
		log.info ("Generando los VO de Productos: " + voProd.size() + " existentes");
		return voProd;

	}
	
	public List<Promocion> darPromociones() {
		log.info ("Generando los VO de Producto");        
		List<Promocion> voProd = new LinkedList<Promocion> ();
		for (Promocion tb : pp.darPromociones ())
		{
			voProd.add (tb);
		}
		log.info ("Generando los VO de Productos: " + voProd.size() + " existentes");
		return voProd;

	}

	public List<ClienteNatural> darClientesNaturales() {
		log.info ("Generando los VO de Cliente");        
		List<ClienteNatural> voProd = new LinkedList<ClienteNatural> ();
		for (ClienteNatural tb : pp.darClientesNaturales ())
		{
			voProd.add (tb);
		}
		log.info ("Generando los VO de cliente natural: " + voProd.size() + " existentes");
		return voProd;

	}

	public List<Sucursal> darSucursales() {
		log.info ("Generando los VO de sucursal");        
		List<Sucursal> voProd = new LinkedList<Sucursal> ();
		for (Sucursal tb : pp.darSucursales())
		{
			voProd.add (tb);
		}
		log.info ("Generando los VO de cliente natural: " + voProd.size() + " existentes");
		return voProd;

	}


	public Cliente registrarCliente(int idCliente, int puntosDeCompra, String nitCliente, int cedulaCliente)
	{
		log.info ("Adicionando el cliente: " + cedulaCliente);
		Cliente cliente = pp.registrarCliente (idCliente,puntosDeCompra,nitCliente,cedulaCliente);		
		log.info ("Adicionando el cliente: " + cliente);
		return cliente;
	}
	public ClienteNatural registrarClienteNatural(int cedula, String nombre, String email, int a)
	{
		log.info ("Adicionando el cliente: " + cedula);
		ClienteNatural clienteNatural = pp.registrarClienteNatural(cedula, nombre, email, a) ;		
		log.info ("Adicionando el cliente: " + cedula);
		return clienteNatural;
	}	

	public ClienteEmpresa registrarClienteEmpresa(String NIT, String direccion, int i)
	{
		log.info ("Adicionando el cliente: " + NIT);
		ClienteEmpresa clienteEmpresa = pp.registrarClienteEmpresa(NIT, direccion , i) ;		
		log.info ("Adicionando el cliente: " + NIT);
		return clienteEmpresa;
	}


public List<VOContenedor> darVOContenedor() {
		log.info ("Generando los VO de contenedor");        
		List<VOContenedor> voTipos = new LinkedList<VOContenedor> ();
		for (Contenedor tb : pp.darContenedor ())
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de sucursal: " + voTipos.size() + " existentes");
		return voTipos;
	}

public Contenedor registrarContenedor(int id, int capacidadVolumen,
			int capacidadPeso, String unidadesPeso, String unidadesVolumen,
			int idBodegaSucursal, int a) {
		log.info ("Adicionando el contenedor: " + id);
		Contenedor contenedor = pp.registrarContenedor(id, capacidadVolumen, capacidadPeso, unidadesPeso, unidadesVolumen, idBodegaSucursal, a);		
		log.info ("Adicionando el contenedor: " + id);
		return contenedor;
	}
public Contenedor darContenedor(int id)
	{
		
			log.info ("Generando consulta");        
	        List<Contenedor> voProv = new LinkedList<Contenedor> ();
	        Contenedor prov = voProv.get(0);
	        log.info ("Generando Consulta: " + voProv.size() + " existentes");
	        return prov;	
	}

	
	public CarritoDeCompras registrarCarritoDeCompras(long idCarrito, int usado, String NIT, int cedula)
	{
		log.info ("Adicionando el carrito: " + idCarrito);
		CarritoDeCompras carritoDeCompras = pp.registrarCarritoDeCompras(idCarrito, usado, NIT, cedula);		
		log.info ("Adicionando el carrito: " + idCarrito);
		return carritoDeCompras;
	}
	
	public Sucursal registrarSucursal(int id,String nombre,String ciudad,String direccion, String segmentacionDeMercado,String tamanioInstalacion,int NITSupermercado, int a)
	{
		log.info ("Adicionando la sucursal: " + nombre);
		Sucursal sucursal = pp.registrarSucursal(id, nombre, ciudad, direccion, segmentacionDeMercado, tamanioInstalacion, NITSupermercado, a);		
		log.info ("Adicionando la sucursal: " + nombre);
		return sucursal;
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

	public VOEstante registrarEstante(int idEstante, int nivelReabastecimiento, int idSucursal ) {
		log.info ("Adicionando el Estante: " + idEstante);
		Estante estante = pp.registrarEstante (idEstante,nivelReabastecimiento,idSucursal);		
		log.info ("Adicionando el proveedor: " + estante);
		return estante;
	}

	
	public Promocion registrarPromocion(int idPromocion, String descr, String Precio , int idSucursal) {
		log.info ("Adicionando el Estante: " + idPromocion);
		Promocion promocion = pp.registrarPromocion (idPromocion,descr,Precio,idSucursal);		
		log.info ("Adicionando el proveedor: " + idPromocion);
		return promocion;
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


	public long eliminarPromocion(long idPromocion) {
		log.info ("Eliminando la promocion: " + idPromocion);
		long w = pp.eliminarPromocion (idPromocion);		
		log.info ("Eliminando la promocion ");
		return w;

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
	public List<uniandes.isis2304.superAndes.negocio.Consulta8> consulta8(String idSucursal) {
		log.info ("Generando consulta");        
        List<uniandes.isis2304.superAndes.negocio.Consulta8> voProd = new LinkedList<Consulta8> ();
        for (Consulta8 tb : pp.Consulta8(idSucursal))
        {
        	voProd.add (tb);
        }
        log.info ("Generando Consulta: " + voProd.size() + " existentes");
        return voProd;
	}
    /* ****************************************************************
	 * 			Metodos Para las pruebas
	 *****************************************************************/
    
	public Proveedor darProveedor(String nombre)
	{
		
			log.info ("Generando consulta");        
	        List<Proveedor> voProv = new LinkedList<Proveedor> ();
	        Proveedor prov = voProv.get(0);
	        log.info ("Generando Consulta: " + voProv.size() + " existentes");
	        return prov;	
	}
	public long eliminarProveedor(int nit)
	{
		
		log.info ("Eliminando el proveedor: " + nit);
		long p = pp.eliminarProveedor (nit);		
		log.info ("Eliminando el proveedor");
		return p;	
	}
	
	public long eliminarProducto(String codigoDeBarras)
	{
		
		log.info ("Eliminando el producto: " + codigoDeBarras);
		long p = pp.eliminarProducto (codigoDeBarras);		
		log.info ("Eliminando el producto ");
		return p;	
	}
	public long eliminarFactura(String NumeroFac)
	{
		
		log.info ("Eliminando el Factura: " + NumeroFac);
		long p = pp.eliminarFactura (NumeroFac);		
		log.info ("Eliminando el Factura ");
		return p;	
	}
	
	public long eliminarEstante(long id)
	{
		
		log.info ("Eliminando el Estante: " + id);
		long p = pp.eliminarEstante (id);		
		log.info ("Eliminando el Estante ");
		return p;	
	}
	
	public ClienteNatural darClienteNatural(int cedula)
	{
		
			log.info ("Generando consulta");        
	        List<ClienteNatural> voProv = new LinkedList<ClienteNatural> ();
	        ClienteNatural prov = voProv.get(0);
	        log.info ("Generando Consulta: " + voProv.size() + " existentes");
	        return prov;	
	}
	public Sucursal darSucursal(int id)
	{
		
			log.info ("Generando consulta");        
	        List<Sucursal> voProv = new LinkedList<Sucursal> ();
	        Sucursal prov = voProv.get(0);
	        log.info ("Generando Consulta: " + voProv.size() + " existentes");
	        return prov;	
	}
	
	public long eliminarClienteNatural (int cedula)
	{
		
		log.info ("Eliminando el cliente: " + cedula);
		long p = pp.eliminarClienteNatural (cedula);		
		log.info ("Eliminando el cliente ");
		return p;	
	}
	
	public long eliminarSucursal (int id)
	{
		
		log.info ("Eliminando la sucursal: " + id);
		long p = pp.eliminarSucursal (id);		
		log.info ("Eliminando la sucursal ");
		return p;	
	}
	
	public long eliminarContenedor (int id)
	{
		
		log.info ("Eliminando la sucursal: " + id);
		long p = pp.eliminarContenedor (id);		
		log.info ("Eliminando la sucursal ");
		return p;	
	}
	public ClienteEmpresa darClienteEmpresa(String NIT)
	{
		
			log.info ("Generando consulta");        
	        List<ClienteEmpresa> voProv = new LinkedList<ClienteEmpresa> ();
	        ClienteEmpresa prov = voProv.get(0);
	        log.info ("Generando Consulta: " + voProv.size() + " existentes");
	        return prov;	
	}
	public long eliminarClienteEmpresa (String NIT)
	{
		
		log.info ("Eliminando el cliente: " + NIT);
		long p = pp.eliminarClienteEmpresa (NIT);		
		log.info ("Eliminando el cliente ");
		return p;	
	}
	
	

    /* ****************************************************************
	 * 			Metodos Extra
	 *****************************************************************/
    
	//Limpiar SuperAndes
	public long [] limpiarSuperAndes ()
	{
		log.info ("Limpiando la BD de SuperAndes");
		long [] borrrados = pp.limpiarSuperAndes();	
		log.info ("Limpiando la BD de SuperAndes: Listo!");
		return borrrados;
	}



	
	

	

	













}
