/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import com.gs.collections.impl.list.fixed.AbstractArrayAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ra.forero11
 */
@Entity
public class TrabajoEntity extends BaseEntity implements Serializable {

    
    
    private boolean terminado;
    
    @PodamExclude
    @OneToMany(mappedBy = "trabajo", cascade = CascadeType.ALL, orphanRemoval = true)
    private  List<CalificacionEntity> calificaciones= new ArrayList<CalificacionEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy = "trabajo")
    private List<PropuestaEntity> propuesta= new ArrayList<PropuestaEntity>();

    
    /**
     * @return the propuesta
     */
    public List<PropuestaEntity> getPropuesta() {
        return propuesta;
    }

    /**
     * @param propuesta the propuesta to set
     */
    public void setPropuesta(List<PropuestaEntity> propuesta) {
        this.propuesta = propuesta;
    } 
    
    /**
     * @return the terminado
     */
    public boolean isTerminado() {
        return terminado;
    }

    /**
     * @param terminado the terminado to set
     */
    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    /**
     * @return the calificaciones
     */
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

   

}
