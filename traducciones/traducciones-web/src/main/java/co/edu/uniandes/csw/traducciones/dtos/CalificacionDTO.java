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
    private double calificacion;
            
   /**
     * Constructor por defecto
     */
    public CalificacionDTO() {
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
        this.calificacion= calificacion.getCalificacion();
    }
    
     /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public CalificacionEntity toEntity() {
        CalificacionEntity entity = new CalificacionEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setComentario(this.comentario);
        entity.setCalificacion(this.calificacion);
        return entity;
    }
}
