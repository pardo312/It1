
package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de BEBIDA.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Germán Bravo
 */
public interface VOClienteEmpresa 
{
	public String getNIT() ;
	
	public String getDireccion() ;

	/**
	 * @return Una cadena con la información básica de la bebida
	 */
	@Override
	public String toString();

}
