/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.HojaDeVidaEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.HojaDeVidaPersistence;
import co.edu.uniandes.csw.traducciones.persistence.HojaDeVidaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ra.forero11
 */
@Stateless
public class HojaDeVidaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(HojaDeVidaLogic.class.getName());

    @Inject
    private HojaDeVidaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public HojaDeVidaEntity createHojaDeVida(HojaDeVidaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de HojaDeVida");
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
    public List<HojaDeVidaEntity> getHojasDeVida() {
        LOGGER.info("Inicia proceso de consultar todas las HojaDeVidas");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<HojaDeVidaEntity> HojaDeVidas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las HojaDeVidas");
        return HojaDeVidas;
    }
    
    public HojaDeVidaEntity getHojaDeVidaId(Long id)
    {
        return persistence.find(id);
    }
    
     public void deleteHojaDeVidaId(Long id)
    {
        persistence.delete(id);
    }
     
     public HojaDeVidaEntity updateHojaDeVida(Long id,HojaDeVidaEntity entrada)
    {
        entrada.setId(id);
        return persistence.update(entrada);
    }
     
     public boolean existeHojaDeVidaId(Long id)
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
