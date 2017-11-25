/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.ejb;
import co.edu.uniandes.csw.traducciones.entities.TrabajoEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.TrabajoPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ra.forero11
 */
@Stateless
public class TrabajoLogic {

    private static final Logger LOGGER = Logger.getLogger(TrabajoLogic.class.getName());

    @Inject
    private TrabajoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public TrabajoEntity createTrabajo(TrabajoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Trabajo");
        // Verifica la regla de negocio que dice que no puede haber dos Trabajo con la misma canción
        // Invoca la persistencia para crear la Trabajo
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Trabajo");
        return entity;
    }

    /**
     *
     * Obtener todas las Trabajo existentes en la base de datos.
     *
     * @return una lista de Trabajo.
     */
    public List<TrabajoEntity> getTrabajos() {
        LOGGER.info("Inicia proceso de consultar todas las Trabajos");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<TrabajoEntity> trabajos = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Trabajos");
        return trabajos;
    }
    
    /**
     * 
     * Obtiene un trabajo de la base de datos
     * @param id del trabajo a obtener  
     * @return entidad del trabajo
     */
    public TrabajoEntity getTrabajoId(Long id) {
        return persistence.find(id);
    }

    /**
     * 
     * Elimina un trabajo por id
     * @param id id del trabajo a eliminar
     */
    public void deleteTrabajoId(Long id) {
        persistence.delete(id);
    }
    
    /**
     * 
     * Atualiza la informacion de un trabajo
     * @param id id del trabajo a actualizar
     * @param entrada informaion nueva del trabajo
     * @return entidad trabajo actualizada
     */
    public TrabajoEntity updateTrabajo(Long id, TrabajoEntity entrada) {
        TrabajoEntity trabajo=getTrabajoId(id);
        trabajo.setName(entrada.getName());
        trabajo.setTerminado(entrada.isTerminado());
        entrada.setId(id);
        return persistence.update(trabajo);
    }

    /**
     * Dice si existe o no un trabajo en la base de datos
     * @param id id del trabajo a validar
     * @return True si existe, false de lo contrario
     */
    public boolean existeTrabajoId(Long id) {
        return persistence.find(id) != null;
    }

}

