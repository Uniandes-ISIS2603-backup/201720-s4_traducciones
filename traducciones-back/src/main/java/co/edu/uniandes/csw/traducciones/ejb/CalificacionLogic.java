/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.CalificacionEntity;
import co.edu.uniandes.csw.traducciones.entities.TrabajoEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.CalificacionPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ra.forero11
 */
@Stateless
public class CalificacionLogic {

    private static final Logger 

LOGGER = Logger.getLogger(TrabajoLogic.class.getName());

    @Inject
        private CalificacionPersistence persistence;

    @Inject
        private TrabajoLogic trabajoLogic;

    /**
     * Obtiene la lista de los registros de Calificacion
     * que pertenecen a un Trabajo.
     *
     * @param trabajoid id del Trabajo el cual es padre de los Calificacion
     * s.
     * @return Colección de objetos de Calificacion
     * Entity.
     * @throws co.edu.uniandes.csw.trabajostore.exceptions.BusinessLogicException
     */
    public CalificacionEntity getCalificacion(Long trabajoid) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todos los calificacions");
        TrabajoEntity trabajo = trabajoLogic.getTrabajoId(trabajoid);
        if (trabajo.getCalificacion() == null) {
            throw new BusinessLogicException("El libro que consulta aún no tiene calificacions");
        }
        
        return trabajo.getCalificacion();
    }

    
    /**
     * Se encarga de crear un Calificacion
     * en la base de datos.
     *
     * @param entity Objeto de Calificacion
     * Entity con los datos nuevos
     * @param trabajoid id del Trabajo el cual sera padre del nuevo Calificacion
     * .
     * @return Objeto de Calificacion
     * Entity con los datos nuevos y su ID.
     * 
     */
    public CalificacionEntity createCalificacion(Long trabajoid, CalificacionEntity entity) {
        LOGGER.info("Inicia proceso de crear calificacion");
        TrabajoEntity trabajo = trabajoLogic.getTrabajoId(trabajoid);
        entity.setTrabajo(trabajo);
        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de Calificacion
     * .
     *
     * @param entity Instancia de Calificacion
     * Entity con los nuevos datos.
     * @param trabajoid id del Trabajo el cual sera padre del Calificacion
     * actualizado.
     * @return Instancia de Calificacion
     * Entity con los datos actualizados.
     * 
     */
    public CalificacionEntity updateCalificacion(Long trabajoid, CalificacionEntity entity) {
        LOGGER.info("Inicia proceso de actualizar calificacion");
        TrabajoEntity trabajo = trabajoLogic.getTrabajoId(trabajoid);
        entity.setTrabajo(trabajo);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Calificacion
     * de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param trabajoid id del Trabajo el cual es padre del Calificacion
     * .
     * 
     */
    public void deleteCalificacion(Long trabajoid) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de borrar calificacion");
        CalificacionEntity old = getCalificacion(trabajoid);
        persistence.delete(old.getId());
    }

}
