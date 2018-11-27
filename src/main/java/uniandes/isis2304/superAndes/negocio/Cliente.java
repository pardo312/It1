

package uniandes.isis2304.superAndes.negocio;


public class Cliente implements VOCliente
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private long idCliente;	
	
	private int puntosDeCompra;
	
	private long cedulaCliente;
	
	private String NITCliente;


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 * @param cedulaCliente2 
	 * @param nitCliente2 
	 * @param puntosDeCompra2 
	 * @param idCliente 
	 */
	public Cliente(int idCliente, int puntosDeCompra, String nitCliente, int cedulaCliente) 
	{
		this.idCliente = 0;
		this.puntosDeCompra = 0;
		this.cedulaCliente = 0;
		this.NITCliente = "";
	}

	public Cliente(long idCliente, String NITCliente, int cedulaCliente, int puntosDeCompra) 
	{
		this.idCliente = idCliente;
		this.NITCliente = NITCliente;
		this.cedulaCliente = cedulaCliente;
		this.puntosDeCompra = puntosDeCompra;
	}

	@Override
	public long getId() 
	{
		return idCliente;
	}

	public void setId(long id) 
	{
		this.idCliente = id;
	}
	

	@Override
	public String getNITCliente() 
	{
		return NITCliente;
	}

	public void setNITCliente(String NITCliente) 
	{
		this.NITCliente = NITCliente;
	}
	
	
	@Override
	public int getPuntosDeCompra() 
	{
		return puntosDeCompra;
	}
	
	public void setPuntosDeCompra(int puntosDeCompra) 
	{
		this.puntosDeCompra = puntosDeCompra;
	}
	
	
	@Override
	public long getCedulaCliente() 
	{
		return cedulaCliente;
	}
	
	
	public void setCedulaCliente(long cedulaCliente) 
	{
		this.cedulaCliente = cedulaCliente;
	}
	
	

	@Override
	public String toString() 
	{
		String resp = "Cliente [id=" + idCliente + ", puntosDeCompra=" + puntosDeCompra + ", NITCliente=" + NITCliente + ", cedulaCliente=" + cedulaCliente
				 + "]";
		
		return resp;
		
	}

}
