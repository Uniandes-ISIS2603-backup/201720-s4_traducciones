/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.OfertaEntity;
import java.util.Date;

/**
 *
 * @author av.perezb
 */
public class OfertaDTO {
    
    private Long id;
    private int cantidad;
    private String descripcion;
    private String codigo;
    private Date fechaVigencia;
    
    /**
     * Constructor por defecto
     */
    public OfertaDTO() {
    
    }
    
    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @param oferta: Es la entidad que se va a convertir a DTO 
     */
    public OfertaDTO(OfertaEntity oferta) {
        
        this.id = oferta.getId();
        this.cantidad = oferta.getCantidad();
        this.descripcion = oferta.getDescripcion();
        this.codigo = oferta.getCodigo();
        this.fechaVigencia = oferta.getFechaVigencia();
    }

    /**
     * @return el id
     */
    public Long getId() {
        return id;
    }
    
    /**
     * @param id el id para modificar
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return la cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad la cantidad para modificar
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return la descripción
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion la descripción para modificar
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return el código
     */    
    public String getCodigo() {
        return codigo;
    }
  
    /**
     * @param codigo el código para modificar
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return la fecha de vigencia
     */ 
    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    /**
     * @param fechaVigencia para modificar
     */ 
    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }
    
    /**
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO 
     */
    public OfertaEntity toEntity() {
        OfertaEntity entity = new OfertaEntity();
        entity.setId(this.id);
        entity.setCantidad(this.cantidad);
        setDescripcion(this.descripcion);
        setCodigo(this.codigo);
        setFechaVigencia(this.fechaVigencia);
        return entity;
    }
    
       
    
}
