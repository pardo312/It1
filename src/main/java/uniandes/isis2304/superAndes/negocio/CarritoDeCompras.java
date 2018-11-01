package uniandes.isis2304.superAndes.negocio;


public class CarritoDeCompras implements VOCarritoDeCompras
{/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * usado es 0 si no esta en uso
	 */
	private int usado;
	
	private int cedula;
	
	private String NITProveedor;
	
	private long IDCarrito;
	


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public CarritoDeCompras() 
	{
		this.IDCarrito = 0;
		this.usado = 0;
		this.NITProveedor = "0";
		this.cedula = 0;
	}

	public CarritoDeCompras( long IDCarrito, int usado, String NITProveedor, int cedula) 
	{
		this.IDCarrito = IDCarrito;
		this.usado = 0;
		this.NITProveedor = NITProveedor;
		this.cedula = cedula;
	}

	public long getID() 
	{
		return IDCarrito;
	}

	public void setId(long id) 
	{
		this.IDCarrito = id;
	}
	
	public String getNITProveedor() 
	{
		return NITProveedor;
	}

	public void setUsado(String NIT) 
	{
		this.NITProveedor = NIT;
	}
	
	public int getCedula() 
	{
		return cedula;
	}

	public void setCedula(int cedula) 
	{
		this.cedula = cedula;
	}
	
	public int getUsado() 
	{
		return usado;
	}

	public void setUsado(int usado) 
	{
		this.usado = usado;
	}
	

// TODO XD 
	@Override
	public String toString() 
	{
		String resp = "CarritoDeCompras [usado=" + usado +  "]";
		
		return resp;
		
	}

}
