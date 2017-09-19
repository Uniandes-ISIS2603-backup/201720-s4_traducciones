/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.TrayectoriaEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.TrayectoriaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ra.forero11
 */
@Stateless
public class TrayectoriaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(TrayectoriaLogic.class.getName());

    @Inject
    private TrayectoriaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public TrayectoriaEntity createTrayectoria(TrayectoriaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Trayectoria");
        // Invoca la persistencia para crear la Trayectoria
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Trayectoria");
        return entity;
    }

    /**
     * 
     * Obtener todas las Trayectoria existentes en la base de datos.
     *
     * @return una lista de Trayectoria.
     */
    public List<TrayectoriaEntity> getTrayectorias() {
        LOGGER.info("Inicia proceso de consultar todas las Trayectorias");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<TrayectoriaEntity> trayectorias = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Trayectorias");
        return trayectorias;
    }
    
    public TrayectoriaEntity getTrayectoriaId(Long id)
    {
        return persistence.find(id);
    }
    
     public void deleteTrayectoriaId(Long id)
    {
        persistence.delete(id);
    }
     
     public TrayectoriaEntity updateTrayectoria(Long id,TrayectoriaEntity entrada)
    {
        entrada.setId(id);
        return persistence.update(entrada);
    }
     
     public boolean existeTrayectoriaId(Long id)
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
