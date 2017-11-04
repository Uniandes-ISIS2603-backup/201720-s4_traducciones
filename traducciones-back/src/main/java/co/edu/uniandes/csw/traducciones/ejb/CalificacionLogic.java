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
    private CalificacionPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private TrabajoLogic trabajoLogic;
    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public CalificacionEntity createCalificacion(Long trabajoId,CalificacionEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Calificacion");
       TrabajoEntity trabajo = trabajoLogic.getTrabajoId(trabajoId);
        entity.setTrabajo(trabajo);
        LOGGER.info("Termina proceso de creación de Trayectoria");
        return persistence.create(entity);
    }

    /**
     *
     * Obtener todas las Calificacion existentes en la base de datos.
     *
     * @return una lista de Calificacion.
     */
    public CalificacionEntity getCalificacion(Long trabajoId){
        LOGGER.info("Inicia proceso de consultar todas las Calificacions");
        TrabajoEntity trabajo = trabajoLogic.getTrabajoId(trabajoId);
        LOGGER.info("Termina proceso de consultar todas las Trayectorias");
        return trabajo.getCalificacion();
    }

    public void deleteCalificacionId(Long trabajoId) {
        LOGGER.info("Inicia proceso de borrar trayectoria");
        CalificacionEntity old = getCalificacion(trabajoId);
        LOGGER.info("Termina el proceso de borrar trayectoria");
        persistence.delete(old.getId());
    }

    public CalificacionEntity updateCalificacion(Long trabajoId, CalificacionEntity entrada) {
       LOGGER.info("Inicia proceso de actualizar trayectoria");
        TrabajoEntity trabajo = trabajoLogic.getTrabajoId(trabajoId);
        entrada.setTrabajo(trabajo);
        LOGGER.info("Termina el proceso de actualizar trayectoria");
        return persistence.update(entrada);
    }

    public boolean existeCalificacionId(Long id) {
        if (persistence.find(id) != null) {
            return true;
        } else {
            return false;
        }
    }

}
