/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ra.forero11
 */
@Entity
public class TrabajoEntity extends BaseEntity implements Serializable {
    
    private boolean terminado;
    
    @PodamExclude
    @OneToOne(mappedBy = "trabajo", cascade = CascadeType.ALL, orphanRemoval = true)
    private CalificacionEntity calificacion;
    
    //@PodamExclude
    //@OneToOne
    //private PagoEntity pago;

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
     * @return the calificacion
     */
    public CalificacionEntity getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(CalificacionEntity calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the pago
     */
    //public PagoEntity getPago() {
      //  return pago;
    //}

    /**
     * @param pago the pago to set
     */
    //public void setPago(PagoEntity pago) {
      //  this.pago = pago;
    //}
}
