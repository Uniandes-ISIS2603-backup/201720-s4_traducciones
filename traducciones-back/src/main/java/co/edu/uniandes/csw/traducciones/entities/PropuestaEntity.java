/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
<<<<<<< HEAD
=======
import javax.persistence.OneToOne;
>>>>>>> master
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author av.perezb
 */
@Entity
public class PropuestaEntity extends BaseEntity implements Serializable {

   @PodamExclude
   @ManyToOne
    private OfertaEntity oferta;
<<<<<<< HEAD

=======
    
    @PodamExclude
    @ManyToOne 
    private EmpleadoEntity empleado;
  
>>>>>>> master
    public OfertaEntity getOferta() {
        return oferta;
    }

    public void setOferta(OfertaEntity oferta) {
        this.oferta = oferta;
    }

    private String estado;
    
    private Double costo;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }
    
    

}
