/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.CalificacionEntity;
import co.edu.uniandes.csw.traducciones.entities.TrabajoEntity;

/**
 *
 * @author ra.forero11
 */
public class TrabajoDetailedDTO extends TrabajoDTO {
    
     private CalificacionDTO calificacion;

    /**
     *
     */
    public TrabajoDetailedDTO() {
        //metodo obligatorio
    }

    /**
     * Crea un objeto TrabajoDetailedDTO a partir de un objeto TrabajoEntity
     * incluyendo los atributos de TrabajoDTO.
     *
     * @param entity Entidad TrabajoEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public TrabajoDetailedDTO(TrabajoEntity entity) {
        super(entity);
        calificacion=new CalificacionDTO();
        if (entity.getCalificacion() != null) {
           this.calificacion=new CalificacionDTO(entity.getCalificacion());
        }
    }

    /**
     * Convierte un objeto TrabajoDetailedDTO a TrabajoEntity incluyendo los
     * atributos de TrabajoDTO.
     *
     * @return Nueva objeto CalificacionEntity.
     *
     */
    @Override
    public TrabajoEntity toEntity() {
        TrabajoEntity entity = super.toEntity();
        if (getCalificacion() != null) {
          
            entity.setCalificacion(getCalificacion().toEntity());
        }
        else
        {
            entity.setCalificacion(new CalificacionEntity());
        }

        return entity;
    }

    /**
     * @return the calificacion
     */
    public CalificacionDTO getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(CalificacionDTO calificacion) {
        this.calificacion = calificacion;
    }
    
}
