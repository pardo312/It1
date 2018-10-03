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

import java.sql.Timestamp;
import java.util.List;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 */
public class Consulta1 implements VOConsulta1
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	
	
	private String nombre;

	private float dineroRecolectado;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
 
	public Consulta1() 
    {
		this.nombre = "";
        this.dineroRecolectado = 0;
	}

	public Consulta1(String nombre, float dineroRecolectado) {
        this.nombre = nombre;
        this.dineroRecolectado = dineroRecolectado;
    }

   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getDineroRecolectado() {
        return dineroRecolectado;
    }
   public void setDineroRecolectado(float dineroRecolectado) {
        this.dineroRecolectado = dineroRecolectado;
    }
	
		
	@Override
	public String toString() 
	{
		String resp = "Consulta1 [nombre=" + nombre + ", dineroRecolectado=" + dineroRecolectado + "]";

		return resp;
		
	}
	

}
