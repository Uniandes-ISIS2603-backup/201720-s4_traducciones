/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.TarjetaDeCreditoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author ne.ortega
 */
public class TarjetaDeCreditoLogic {

    private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoLogic.class.getName());

    @Inject
    private TarjetaDeCreditoPersistence persistence;

    public List<TarjetaDeCreditoEntity> getTarjetas() {
        LOGGER.info("Inicia proceso de consultar todas las tarjetas");
        List<TarjetaDeCreditoEntity> tarjetas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las tarjetas");
        return tarjetas;
    }

    public TarjetaDeCreditoEntity getTarjeta(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar tarjeta con id={0}", id);
        TarjetaDeCreditoEntity tarjeta = persistence.find(id);
        if (tarjeta == null) {
            LOGGER.log(Level.SEVERE, "La tarjeta con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar tarjeta con id={0}", id);
        return tarjeta;
    }

    public TarjetaDeCreditoEntity createTarjeta(TarjetaDeCreditoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de tarjeta");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de tarjeta");
        return entity;
    }

    public TarjetaDeCreditoEntity updateTarjeta(Long id, TarjetaDeCreditoEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar tarjeta con id={0}", id);
        TarjetaDeCreditoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar tarjeta con id={0}", entity.getId());
        return newEntity;
    }

    public void deleteTarjeta(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar tarjeta con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar tarjeta con id={0}", id);
    }    
}
