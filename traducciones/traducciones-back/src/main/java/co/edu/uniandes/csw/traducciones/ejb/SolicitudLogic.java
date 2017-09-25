/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.SolicitudEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.SolicitudPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author aj.ayte  
 */
public class SolicitudLogic {
    private static final Logger LOGGER = Logger.getLogger(SolicitudLogic.class.getName());

    @Inject
    private SolicitudPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public SolicitudEntity createSolicitud(SolicitudEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Solicitud");
        // Invoca la persistencia para crear la Solicitud
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Solicitud");
        return entity;
    }

    /**
     * 
     * Obtener todas las Solicitudes existentes en la base de datos.
     *
     * @return una lista de Solicitudes.
     */
    public List<SolicitudEntity> getSolicituds() {
        LOGGER.info("Inicia proceso de consultar todas las Solicituds");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<SolicitudEntity> Solicituds = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Solicituds");
        return Solicituds;
    }
    
    public SolicitudEntity getSolicitudId(Long id)
    {
        return persistence.find(id);
    }
    
     public void deleteSolicitudId(Long id)
    {
        persistence.delete(id);
    }
     
     public SolicitudEntity updateSolicitud(Long id,SolicitudEntity entrada)
    {
        entrada.setId(id);
        return persistence.update(entrada);
    }
     
     public boolean existeSolicitudId(Long id)
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
