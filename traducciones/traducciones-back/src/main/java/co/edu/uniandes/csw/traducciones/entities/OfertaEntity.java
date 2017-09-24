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
    

    private Integer cantidadI;
    private Integer cantActual;
    private String descripcion;
    private String codigo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaVigencia;

    public Integer getCantidadI() {
        return cantidadI;
    }

    public void setCantidadI(Integer cantidadI) {
        this.cantidadI = cantidadI;
    }

    public Integer getCantActual() {
        return cantActual;
    }

    public void setCantActual(Integer cantActual) {
        this.cantActual = cantActual;
    }
    
    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

}
