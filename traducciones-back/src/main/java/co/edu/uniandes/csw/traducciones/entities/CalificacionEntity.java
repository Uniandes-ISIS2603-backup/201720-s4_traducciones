/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ra.forero11
 */
@Entity
public class CalificacionEntity extends BaseEntity{

    private double valor;
    private String comentario;
    
    @PodamExclude
    @OneToOne
    private TrabajoEntity trabajo;

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
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
