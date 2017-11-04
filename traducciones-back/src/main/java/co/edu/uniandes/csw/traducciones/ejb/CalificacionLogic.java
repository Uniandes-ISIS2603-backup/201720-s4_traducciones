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

    private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());

    @Inject
        private CalificacionPersistence persistence;

    @Inject
        private TrabajoLogic trabajoLogic;

    /**
     *
     * @param trabajoId id de la hoja de vida a la que se le agrega calificacion
     * @param entity calificacion a agregar a la lista de la hoja de vida
     * @return entidad calificacion creada
     */
    public CalificacionEntity createCalificacion(Long trabajoId,CalificacionEntity entity) {
        LOGGER.info("Inicia proceso de creación de Calificacion");
        TrabajoEntity trabajo = trabajoLogic.getTrabajoId(trabajoId);
        entity.setTrabajo(trabajo);
        LOGGER.info("Termina proceso de creación de Calificacion");
        return persistence.create(entity);
    }

    /**
     * 
     * Obtener todas las Calificacion pertenecientes a una hoja de vida.
     * @param trabajoId id de la hoja de vida
     * @return una lista de Calificacion.
     * @throws co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException
     */
    public List<CalificacionEntity> getCalificacions(Long trabajoId) throws BusinessLogicException{
        LOGGER.info("Inicia proceso de consultar todas las Calificacions");
        TrabajoEntity trabajo = trabajoLogic.getTrabajoId(trabajoId);
        if (trabajo.getCalificaciones()== null) {
            throw new BusinessLogicException("El libro que consulta aún no tiene reviews");
        }
        if (trabajo.getCalificaciones().isEmpty()) {
            throw new BusinessLogicException("El libro que consulta aún no tiene reviews");
        }        
        LOGGER.info("Termina proceso de consultar todas las Calificacions");
        return trabajo.getCalificaciones();
    }
    
    
    /**
     * Obtiene los datos de una instancia de Calificacion a partir de su ID.
     * @param trabajoId
     * @param calificacionId
     * @pre La existencia del elemento padre Trabajo se debe garantizar.
     * @return Instancia de ReviewEntity con los datos del Review consultado.
     * 
     */
    public CalificacionEntity getCalificacionId(Long trabajoId,Long calificacionId)
    {
        return persistence.find(trabajoId, calificacionId);
    }
    
    
    /**
     * Elimina una instancia de Review de la base de datos.
     *
     * @param trabajoId id de la hoja de vida
     * @param id Identificador de la instancia a eliminar. 
     */
     public void deleteCalificacionId(Long trabajoId, Long id){
        LOGGER.info("Inicia proceso de borrar calificacion");
        CalificacionEntity old = getCalificacionId(trabajoId, id);
        LOGGER.info("Termina el proceso de borrar calificacion");
        persistence.delete(old.getId());
    }
     
     
     /**
     * Actualiza la información de una instancia de Review.
     * @param trabajoId id de la hoja de vida
     * @param entity Instancia de CalificacionEntity con los nuevos datos.
     * @return Instancia de CalificacionEntity con los datos actualizados.
     * 
     */
     public CalificacionEntity updateCalificacion(Long trabajoId, CalificacionEntity entity) {
        LOGGER.info("Inicia proceso de actualizar calificacion");
        TrabajoEntity trabajo = trabajoLogic.getTrabajoId(trabajoId);
        entity.setTrabajo(trabajo);
        LOGGER.info("Termina el proceso de actualizar calificacion");
        return persistence.update(entity);
    }}
