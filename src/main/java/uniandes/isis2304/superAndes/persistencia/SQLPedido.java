package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Categoria;
import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.Pedido;
import uniandes.isis2304.superAndes.negocio.Proveedor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBEDOR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLPedido 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLPedido (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un BEBEDOR a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idBebedor - El identificador del bebedor
	 * @param nombre - El nombre del bebedor
	 * @param ciudad - La ciudad del bebedor
	 * @param presupuesto - El presupuesto del bebedor (ALTO, MEDIO, BAJO)
	 * @return EL número de tuplas insertadas
	 */
	public long registrarPedido (PersistenceManager pm,int id, java.util.Date fechaEsperada,java.util.Date fechaEntrega,String evaluacionCantidad, String evaluacionCalidad, int calificacion, int finalizado,int NITProveedor) 
	{
		
		
		java.sql.Date fecha1 = convertUtilToSql(fechaEsperada);
		java.sql.Date fecha2 = convertUtilToSql(fechaEntrega);

		try {
		
		Query q = pm.newQuery(SQL, "INSERT INTO " + "PEDIDO" + "(id,fechaEsperada,fechaEntrega,evaluacionCantidad,evaluacionCalidad,calificacion,finalizado,NITProveedor) values ("+id+", TO_DATE('"+fecha1+"', 'YYYY/MM/DD'), TO_DATE('"+fecha2+"', 'YYYY/MM/DD'), '"+evaluacionCantidad+"', '"+evaluacionCalidad+"', "+calificacion+", "+finalizado+", "+NITProveedor+" )");
		q.setParameters( id, fechaEsperada, fechaEntrega,evaluacionCantidad,evaluacionCalidad,calificacion, finalizado,NITProveedor);
		return (long) q.executeUnique();
		}
		catch (Exception e)
		{
			return 0;
		}
		

	}
	
	public long actualizarPedido (PersistenceManager pm,int id, java.util.Date fechaEsperada,java.util.Date fechaEntrega,String evaluacionCantidad, String evaluacionCalidad, int calificacion, int finalizado,int NITProveedor) 
	{
		java.sql.Date fecha1 = convertUtilToSql(fechaEsperada);
		java.sql.Date fecha2 = convertUtilToSql(fechaEntrega);

		Query q = pm.newQuery(SQL, "Update PEDIDO Set FINALIZADO = 1,evaluacionCalidad = '"+evaluacionCalidad+"',evaluacionCantidad = '"+evaluacionCantidad+"', CALIFICACION = "+calificacion+" ,fechaEntrega = TO_DATE('"+fecha2+"', 'YYYY/MM/DD') WHERE ID ="+ id );
		q.setParameters( id, fechaEsperada, fechaEntrega,evaluacionCantidad,evaluacionCalidad,calificacion, finalizado,NITProveedor);
		return (long) q.executeUnique();
		

	}

	private static java.sql.Date convertUtilToSql(java.util.Date fecha) {

		java.sql.Date sDate = new java.sql.Date(fecha.getTime());

		return sDate;

	}

	/**
	 * lista de todos los clientes
	 * @param pm
	 * @return la lista de todos los clientes
	 */
	public List<Pedido> darPedidos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT MAX(ID)AS ID,MIN(fechaesperada) AS fechaesperada,MAX(fechaentrega)AS fechaentrega,MIN(evaluacioncantidad) AS evaluacioncantidad,MIN(evaluacioncalidad)AS  evaluacioncalidad, MAX(calificacion) AS calificacion, NITPROVEEDOR FROM PEDIDO group by NITPROVEEDOR");
		q.setResultClass(Pedido.class);
		List<Pedido> w =  (List<Pedido>) q.executeList();
		return w;
	}


	public long consolidacionPedidosProveedor (PersistenceManager pm,int id, java.util.Date fechaEsperada,java.util.Date fechaEntrega,String evaluacionCantidad, String evaluacionCalidad, int calificacion, int finalizado,int NITProveedor)
	{
		java.sql.Date fecha1 = convertUtilToSql(fechaEsperada);
		java.sql.Date fecha2 = convertUtilToSql(fechaEntrega);
		
		Query e= pm.newQuery(SQL, "SELECT ID,fechaEsperada,FECHAENTREGA,EVALUACIONCANTIDAD,EVALUACIONCALIDAD,CALIFICACION,NITPROVEEDOR FROM PEDIDO WHERE NITPROVEEDOR = " + NITProveedor);
		e.setResultClass(Pedido.class);
		List<Pedido> w =  (List<Pedido>) e.executeList();
		
		for(Pedido tb: w)
		{
			Query z = pm.newQuery(SQL, "UPDATE PRODUCTO Set IDPEDIDO = "+ id +" where IDPEDIDO= "+ tb.getId()+ " AND IDCARRITO != 0");
			z.executeUnique();
		}
		
		
		Query q = pm.newQuery(SQL, "DELETE FROM PEDIDO where NITPROVEEDOR= "+NITProveedor +"AND ID !=" + id );

		return (long) q.executeUnique() ;
	}

	public long eliminarPedido(PersistenceManager pm, int id) {
		 Query q = pm.newQuery(SQL, "DELETE FROM " + "PEDIDO "+ "WHERE ID = "+id);
	        return (long) q.executeUnique();  
	}



}
