/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.superAndes.negocio;

import java.sql.Date;

/**
 * Interfaz para los métodos get de SIRVEN.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Germán Bravo
 */
public interface VOPedido 
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	public int getId();

	
	public java.util.Date getFechaEsperada();

	
	public java.util.Date getFechaEntrega();
	
	public String getEvaluacionCantidad();
	
	public String getEvaluacionCalidad();
	
	public int getCalificacion();
	
	public int getFinalizado();
	
	public int getNITProveedor() ;

	@Override
	public String toString();

}
