/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

    @PodamExclude
    @ManyToOne
    private EmpleadoEntity empleado;
    
    @PodamExclude
    @ManyToOne()
    private TrabajoEntity trabajo;

    /**
     * Estado de la propuesta, puede ser ACEPTADA, RECHAZADA o EN_REVISION
     */
    private String estado;

    /**
     * Costo de la propuesta
     */
    private Double costo;

    /**
     * Obtiene el atributo estado.
     *
     * @return estado de la propuesta.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el valor del atributo estado.
     *
     * @param estado nuevo valor del atributo
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el atributo oferta.
     *
     * @return oferta aplicada a la propuesta.
     */
    public OfertaEntity getOferta() {
        return oferta;
    }

    /**
     * Establece el valor del atributo oferta.
     *
     * @param oferta nuevo valor del atributo
     */
    public void setOferta(OfertaEntity oferta) {
        this.oferta = oferta;
    }

    /**
     * Obtiene el atributo costo.
     *
     * @return costo de la propuesta.
     */
    public Double getCosto() {
        return costo;
    }

    /**
     * Establece el valor del atributo costo.
     *
     * @param costo nuevo valor del atributo
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }

    /**
     * Obtiene el atributo empleado.
     *
     * @return empleado asociado a la propuesta.
     */
    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    /**
     * Establece el valor del atributo empleado.
     *
     * @param empleado nuevo valor del atributo
     */
    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }

    /**
     * @return the trabajo
     */
    public TrabajoEntity getTrabajo() {
        return trabajo;
    }

    /**
     * @param trabajo the trabajo to set
     */
    public void setTrabajo(TrabajoEntity trabajo) {
        this.trabajo = trabajo;
    }

}
