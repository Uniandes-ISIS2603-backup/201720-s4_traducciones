/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.IdiomaEntity;
import co.edu.uniandes.csw.traducciones.entities.SolicitudEntity;
import java.util.Date;

/**
 *
 * @author aj.ayte
 */
public class SolicitudDTO {
    
    private Long id;
    
    private Date fechaEntrega;
    
    private Date fechaInicio;
    
    private int tipo;
    
    private int numeroDePalabras;
    
    private String descripcion;
    
    /**
     * 
     */
    public SolicitudDTO() {
       // Constructor vacío. 
    }
    
    public SolicitudDTO(SolicitudEntity entity) {
        this.id = entity.getId();
        this.fechaInicio = entity.getFechaInicio();
        this.fechaEntrega = entity.getFechaEntrega();
        this.tipo = entity.getTipo();
        this.numeroDePalabras = entity.getNumPalabras();
        this.descripcion= entity.getDescripcion();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getNumeroDePalabras() {
        return numeroDePalabras;
    }

    public void setNumeroDePalabras(int numeroDePalabras) {
        this.numeroDePalabras = numeroDePalabras;
    }
    
    public SolicitudEntity toEntity() {
        SolicitudEntity entity = new SolicitudEntity();
        entity.setId(this.id);
        entity.setFechaInicio(this.fechaInicio);
        entity.setFechaEntrega(this.fechaEntrega);
        entity.setTipo(this.tipo);
        entity.setNumPalabras(numeroDePalabras);
        entity.setDescripcion(this.descripcion);
        return entity;
    }
}
