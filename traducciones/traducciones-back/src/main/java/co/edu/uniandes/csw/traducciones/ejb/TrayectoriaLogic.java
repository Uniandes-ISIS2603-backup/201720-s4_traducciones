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
        LOGGER.info("Inicia proceso de creación de HojaDeVida");
        // Verifica la regla de negocio que dice que no puede haber dos HojaDeVidas con la misma canción
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe un HojaDeVida con el nombre \"" + entity.getName()+ "\"");
        }
        // Invoca la persistencia para crear la HojaDeVida
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de HojaDeVida");
        return entity;
    }

    /**
     * 
     * Obtener todas las HojaDeVidaes existentes en la base de datos.
     *
     * @return una lista de HojaDeVidaes.
     */
    public List<TrayectoriaEntity> getTrayectorias() {
        LOGGER.info("Inicia proceso de consultar todas las HojaDeVidas");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<TrayectoriaEntity> HojaDeVidas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las HojaDeVidas");
        return HojaDeVidas;
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
