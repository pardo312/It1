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
	
	private String NITCliente;
	
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
		this.NITCliente = "0";
		this.cedula = 0;
	}

	public CarritoDeCompras( long IDCarrito, int usado, String NITCliente, int cedula) 
	{
		this.IDCarrito = IDCarrito;
		this.usado = 0;
		this.NITCliente = NITCliente;
		this.cedula = cedula;
	}

	public long getIDCarrito() 
	{
		return IDCarrito;
	}

	public void setIdCarrito(long idCarrito) 
	{
		this.IDCarrito = idCarrito;
	}
	
	public String getNITCliente() 
	{
		return NITCliente;
	}

	public void setNITCliente(String NIT) 
	{
		this.NITCliente = NIT;
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
	
	
	@Override
	public String toString() 
	{
		String resp = "CarritoDeCompras [ IDCarrito ="+ IDCarrito + "Usado=" + usado + " NITCLIENTE=" + NITCliente + ", cedula=" + cedula  + "]";
		
		
		return resp;
		
	}

}
