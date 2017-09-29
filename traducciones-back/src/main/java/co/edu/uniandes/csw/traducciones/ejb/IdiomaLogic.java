/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.IdiomaEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.IdiomaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author aj.ayte  
 */
public class IdiomaLogic {
    private static final Logger LOGGER = Logger.getLogger(IdiomaLogic.class.getName());

    @Inject
    private IdiomaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public IdiomaEntity createIdioma(IdiomaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Idioma");
        // Invoca la persistencia para crear la Idioma
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Idioma");
        return entity;
    }

    /**
     * 
     * Obtener todas las Idiomaes existentes en la base de datos.
     *
     * @return una lista de Idiomaes.
     */
    public List<IdiomaEntity> getIdiomas() {
        LOGGER.info("Inicia proceso de consultar todas las Idiomas");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<IdiomaEntity> Idiomas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Idiomas");
        return Idiomas;
    }
    
    public IdiomaEntity getIdiomaId(Long id)
    {
        return persistence.find(id);
    }
    
     public void deleteIdiomaId(Long id)
    {
        persistence.delete(id);
    }
     
     public IdiomaEntity updateIdioma(Long id,IdiomaEntity entrada)
    {
        entrada.setId(id);
        return persistence.update(entrada);
    }
     
     public boolean existeIdiomaId(Long id)
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
