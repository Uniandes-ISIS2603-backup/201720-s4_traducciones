/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.CalificacionEntity;

/**
 *
 * @author ra.forero11
 */
public class CalificacionDTO {

    private Long id;
    private String name;
    private String comentario;
    
    private Integer calificacion;

    /**
     * Constructor por defecto
     */
    public CalificacionDTO() {
        //metodo obligatorio
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param calificacion: Es la entidad que se va a convertir a DTO
     */
    public CalificacionDTO(CalificacionEntity calificacion) {
        this.id = calificacion.getId();
        this.name = calificacion.getName();
        this.comentario = calificacion.getComentario();
        
        this.calificacion = calificacion.getCalificacion();
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public CalificacionEntity toEntity() {
        CalificacionEntity entity = new CalificacionEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setComentario(this.getComentario());
        
        entity.setCalificacion(this.calificacion);
        return entity;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }
}
