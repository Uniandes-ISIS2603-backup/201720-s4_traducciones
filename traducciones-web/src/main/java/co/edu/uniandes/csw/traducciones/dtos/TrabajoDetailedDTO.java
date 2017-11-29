/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.CalificacionEntity;
import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;
import co.edu.uniandes.csw.traducciones.entities.TrabajoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ra.forero11
 */
public class TrabajoDetailedDTO extends TrabajoDTO {
    
     private List<CalificacionDTO> calificaciones;
     private List<PropuestaDTO> propuestas;
     

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
        calificaciones = new ArrayList<>();
        propuestas = new ArrayList<>();
        if (entity.getCalificaciones() != null) {
            for (CalificacionEntity entityCalificaciones : entity.getCalificaciones()) {
                calificaciones.add(new CalificacionDTO(entityCalificaciones));
            }
            
        }
        if (entity.getPropuesta() != null) {
            for (PropuestaEntity entityPropuestaa : entity.getPropuesta()) {
                propuestas.add(new PropuestaDTO(entityPropuestaa));
            }
            
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
       if (calificaciones != null) {
            List<CalificacionEntity> calificacionesEntity = new ArrayList<>();
            for (CalificacionDTO dtoCalificaciones : calificaciones) {
                calificacionesEntity.add(dtoCalificaciones.toEntity());
            }
            entity.setCalificaciones(calificacionesEntity);
        }
        else{
            entity.setCalificaciones(new ArrayList<CalificacionEntity>());
        }
       
       if (propuestas != null) {
            List<PropuestaEntity> propuestasEntity = new ArrayList<>();
            for (PropuestaDTO dtoPropuestas : propuestas) {
                propuestasEntity.add(dtoPropuestas.toEntity());
            }
            entity.setPropuesta(propuestasEntity);
        }
        else{
            entity.setPropuesta(new ArrayList<PropuestaEntity>());
        }
       

        return entity;
    }

    /**
     * @return the calificaciones
     */
    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * @return the propuestas
     */
    public List<PropuestaDTO> getPropuestas() {
        return propuestas;
    }

    /**
     * @param propuestas the propuestas to set
     */
    public void setPropuestas(List<PropuestaDTO> propuestas) {
        this.propuestas = propuestas;
    }
    
}
