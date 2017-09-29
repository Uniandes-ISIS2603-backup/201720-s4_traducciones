/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author av.perezb
 */
@Entity
public class OfertaEntity extends BaseEntity implements Serializable {

    private Integer cantidadInicial;
    private Integer cantidadActual;
    private String descripcion;
    private Integer codigo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaVigencia;

    public Integer getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(Integer cantidadI) {
        this.cantidadInicial = cantidadI;
    }
    
    public Integer getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(Integer cantidadA) {
        this.cantidadInicial = cantidadA;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }
    
}
