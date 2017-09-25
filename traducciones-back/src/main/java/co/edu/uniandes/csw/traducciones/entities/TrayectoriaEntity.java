/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ra.forero11
 */
@Entity
public class TrayectoriaEntity extends BaseEntity implements Serializable{
    
    private String descripcion;
    
    @Temporal(TemporalType.DATE)   
    private Date fechaInicio;
    
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    
    @PodamExclude
    @ManyToOne
    private HojaDeVidaEntity hojaDeVida;
    
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the hojaDeVida
     */
    public HojaDeVidaEntity getHojaDeVida() {
        return hojaDeVida;
    }

    /**
     * @param hojaDeVida the hojaDeVida to set
     */
    public void setHojaDeVida(HojaDeVidaEntity hojaDeVida) {
        this.hojaDeVida = hojaDeVida;
    }
    
    
}
