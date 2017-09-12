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
 * @author ga.garcia90
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
        // Verifica la regla de negocio que dice que no puede haber un trabajo con el mismo nombre
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe un Trabajo con el nombre \"" + entity.getName()+ "\"");
        }
        // Invoca la persistencia para crear la HojaDeVida
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Trabajo");
        return entity;
    }

    /**
     * 
     * Obtener todas los Trabajos existentes en la base de datos.
     *
     * @return una lista de Trabajos.
     */
    public List<TrabajoEntity> getTrabajos() {
        LOGGER.info("Inicia proceso de consultar todos los trabajos");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<TrabajoEntity> Trabajo = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los Trabajos");
        return Trabajo;
    }
    
    public TrabajoEntity getTrabajoId(Long id)
    {
        return persistence.find(id);
    }
    
     public void deleteTrabajoId(Long id)
    {
        persistence.delete(id);
    }
     
     public TrabajoEntity updateTrabajo(Long id,TrabajoEntity entrada)
    {
        entrada.setId(id);
        return persistence.update(entrada);
    }
     
     public boolean existeTraabajoId(Long id)
     {
     if(persistence.find(id)!=null)
     {
         return true;
     }
     else
     {
     return false;
     }
     }
    
}
