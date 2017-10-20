/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.PagoEntity;
import co.edu.uniandes.csw.traducciones.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.PagoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author ne.ortega
 */
public class PagoLogic {

    private static final Logger LOGGER = Logger.getLogger(PagoLogic.class.getName());

    @Inject
    private PagoPersistence persistence;
    
    @Inject
    private TarjetaDeCreditoLogic tarjetaLogic;

    public List<PagoEntity> getPagos() {
        LOGGER.info("Inicia proceso de consultar todos los pagos");
        List<PagoEntity> pagos = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los pagos");
        return pagos;
    }

    public PagoEntity getPago(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar pago con id={0}", id);
        PagoEntity pago = persistence.find(id);
        if (pago == null) {
            LOGGER.log(Level.SEVERE, "El pago con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar pago con id={0}", id);
        return pago;
    }

    public PagoEntity createPago(PagoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de pago");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de pago");
        return entity;
    }

    public PagoEntity updatePago(Long id, PagoEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar pago con id={0}", id);
        PagoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar pago con id={0}", entity.getId());
        return newEntity;
    }

    public void deletePago(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar pago con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar pago con id={0}", id);
    }
    
    public TarjetaDeCreditoEntity getTarjeta(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la tarjeta del pago con id={0}", id);
        PagoEntity pago = persistence.find(id);
        if (pago == null) {
            LOGGER.log(Level.SEVERE, "El pago con el id {0} no existe", id);
        }
        else{
            LOGGER.log(Level.INFO, "Termina proceso de consultar pago con id={0}", id);
            TarjetaDeCreditoEntity tarjeta = pago.getTarjeta();
            if(tarjeta == null){
                LOGGER.log(Level.SEVERE, "El pago con el id {0} no tiene asociada ninguna tarjeta", id);
            }
            return tarjeta;
        }
        return null;
    }
    
    public TarjetaDeCreditoEntity addTarjeta(Long id, TarjetaDeCreditoEntity tarjeta){
        PagoEntity pago = persistence.find(id);
        if(pago == null){
            LOGGER.log(Level.SEVERE, "El pago con el id{0} no existe", id);
        }
        else if(tarjeta != null){
            pago.setTarjeta(tarjeta);
            return tarjeta;
        }
        return null;
    }
}
