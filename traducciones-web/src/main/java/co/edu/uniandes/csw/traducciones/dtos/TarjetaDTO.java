/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.TarjetaDeCreditoEntity;
import java.util.Date;

/**
 *
 * @author ne.ortega
 */
public class TarjetaDTO {
    
    private Long id;
    private Integer numero, codigoSeguridad;
    private String compañia, nombres, apellidos;
    private Date fechaExpiracion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(Integer codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public String getCompañia() {
        return compañia;
    }

    public void setCompañia(String compañia) {
        this.compañia = compañia;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
    
    public TarjetaDTO(){
        
    }
    
    public TarjetaDTO(TarjetaDeCreditoEntity entity){
        this.apellidos = entity.getApellidos();
        this.codigoSeguridad = entity.getCodigoSeguridad();
        this.compañia = entity.getCompañia();
        this.fechaExpiracion = entity.getFechaExpiracion();
        this.id = entity.getId();
        this.nombres = entity.getNombres();
        this.numero = entity.getNumero();
    }
    
    public TarjetaDeCreditoEntity toEntity(){
        TarjetaDeCreditoEntity entity = new TarjetaDeCreditoEntity();
        entity.setApellidos(this.apellidos);
        entity.setCodigoSeguridad(this.codigoSeguridad);
        entity.setCompañia(this.compañia);
        entity.setFechaExpiracion(this.fechaExpiracion);
        entity.setId(this.id);
        entity.setNombres(this.nombres);
        entity.setNumero(this.numero);
        return entity;
    }
}
