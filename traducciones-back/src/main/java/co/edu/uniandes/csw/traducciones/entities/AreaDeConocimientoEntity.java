/*
Esta clase tiene los atributos basicos de los objetos de tipo AreaDeConocimiento
*/
package co.edu.uniandes.csw.traducciones.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jc.gloria
 */
@Entity
public class AreaDeConocimientoEntity extends BaseEntity implements Serializable{
    
    private String descripcion;
    
    @PodamExclude
    @ManyToOne 
    private EmpleadoEntity empleado;
    
    /**
     * Devuelve la descripcion de la area de conocimiento
     * @return una cadena de caracteres con la descripcion
     */
    public String getDescripcion(){
        return descripcion;
    }
    
    /**
     * Asigna la descripcion de la area de conocimiento
     * @param pDescripcion una cadena de caracteres con la descripcion
     */
    public void setDescripcion(String pDescripcion){
        descripcion = pDescripcion;
    }
    
    /**
     * Devuelve el objeto empleado que tiene asignado esta area de conocimiento.
     * @return objeto Empleado.
     */
    public EmpleadoEntity getEmpleado() {
        return empleado;
    }
    
    /**
     * Se asigna un empleado a esta area de conocimiento.
     * @param empleado objeto Empleado que sera a quien le pertenece esta area de conocimiento.
     */
    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }   
    
}
