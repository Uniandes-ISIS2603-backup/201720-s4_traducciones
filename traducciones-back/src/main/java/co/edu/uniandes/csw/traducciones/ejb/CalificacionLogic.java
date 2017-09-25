/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.CalificacionEntity;
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

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public CalificacionEntity createCalificacion(CalificacionEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Calificacion");
        // Verifica la regla de negocio que dice que no puede haber dos Calificacion con la misma canción
        // Invoca la persistencia para crear la Calificacion
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Calificacion");
        return entity;
    }

    /**
     *
     * Obtener todas las Calificacion existentes en la base de datos.
     *
     * @return una lista de Calificacion.
     */
    public List<CalificacionEntity> getCalificacions() {
        LOGGER.info("Inicia proceso de consultar todas las Calificacions");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<CalificacionEntity> calificacions = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Calificacions");
        return calificacions;
    }

    public CalificacionEntity getCalificacionId(Long id) {
        return persistence.find(id);
    }

    public void deleteCalificacionId(Long id) {
        persistence.delete(id);
    }

    public CalificacionEntity updateCalificacion(Long id, CalificacionEntity entrada) {
        entrada.setId(id);
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
