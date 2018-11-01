package uniandes.isis2304.superAndes.negocio;


public class CarritoDeCompras implements VOCarritoDeCompras
{/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * usado es 0 si no esta en uso
	 */
	private int usado;
	


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public CarritoDeCompras() 
	{
		this.usado = 0;
	}

	public CarritoDeCompras( int usado) 
	{
		this.usado = 0;
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
		String resp = "CarritoDeCompras [usado=" + usado +  "]";
		
		return resp;
		
	}

}
