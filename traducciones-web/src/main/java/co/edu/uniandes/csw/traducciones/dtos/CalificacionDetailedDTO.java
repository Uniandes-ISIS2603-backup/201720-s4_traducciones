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
public class CalificacionDetailedDTO extends CalificacionDTO{
    
    
     private TrabajoDTO trabajo;

    /**
     *
     */
    public CalificacionDetailedDTO() {
        super();
    }

    /**
     * Crea un objeto CalificacionDetailedDTO a partir de un objeto CalificacionEntity
     * incluyendo los atributos de HojaDeVidaDetailedDTO.
     *
     * @param entity Entidad CalificacionEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public CalificacionDetailedDTO(CalificacionEntity entity) {
        super(entity);
        if (entity != null) {
           this.trabajo=new TrabajoDTO(entity.getTrabajo());
        }

    }

    /**
     * Convierte un objeto CalificacionDetailedDTO a CalificacionEntity incluyendo los
     * atributos de CalificacionDTO.
     *
     * @return Nueva objeto CalificacionEntity.
     *
     */
    @Override
    public CalificacionEntity toEntity() {
        CalificacionEntity entity = super.toEntity();
        if (trabajo != null) {
          
            entity.setTrabajo(trabajo.toEntity());
        }

        return entity;
    }
}
