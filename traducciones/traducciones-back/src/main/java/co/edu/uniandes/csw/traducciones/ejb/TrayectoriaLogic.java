/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.HojaDeVidaEntity;
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

    
    @Inject
    private HojaDeVidaLogic hojaDeVidaLogic;
    /**
     *
     * @param hojaDeVidaId id de la hoja de vida a la que se le agrega trayectoria
     * @param entity trayectoria a agregar a la lista de la hoja de vida
     * @return entidad trayectoria creada
     */
    public TrayectoriaEntity createTrayectoria(Long hojaDeVidaId,TrayectoriaEntity entity) {
        LOGGER.info("Inicia proceso de creación de Trayectoria");
        HojaDeVidaEntity hojaDeVida = hojaDeVidaLogic.getHojaDeVidaId(hojaDeVidaId);
        entity.setHojaDeVida(hojaDeVida);
        LOGGER.info("Termina proceso de creación de Trayectoria");
        return persistence.create(entity);
    }

    /**
     * 
     * Obtener todas las Trayectoria pertenecientes a una hoja de vida.
     * @param hojaDeVidaId id de la hoja de vida
     * @return una lista de Trayectoria.
     * @throws co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException
     */
    public List<TrayectoriaEntity> getTrayectorias(Long hojaDeVidaId) throws BusinessLogicException{
        LOGGER.info("Inicia proceso de consultar todas las Trayectorias");
        HojaDeVidaEntity hojaDeVida = hojaDeVidaLogic.getHojaDeVidaId(hojaDeVidaId);
        if (hojaDeVida.getTrayectorias()== null) {
            throw new BusinessLogicException("El libro que consulta aún no tiene reviews");
        }
        if (hojaDeVida.getTrayectorias().isEmpty()) {
            throw new BusinessLogicException("El libro que consulta aún no tiene reviews");
        }        
        LOGGER.info("Termina proceso de consultar todas las Trayectorias");
        return hojaDeVida.getTrayectorias();
    }
    
    
    /**
     * Obtiene los datos de una instancia de Trayectoria a partir de su ID.
     * @param hojaDeVidaId
     * @param trayectoriaId
     * @pre La existencia del elemento padre HojaDeVida se debe garantizar.
     * @return Instancia de ReviewEntity con los datos del Review consultado.
     * 
     */
    public TrayectoriaEntity getTrayectoriaId(Long hojaDeVidaId,Long trayectoriaId)
    {
        return persistence.find(hojaDeVidaId, trayectoriaId);
    }
    
    
    /**
     * Elimina una instancia de Review de la base de datos.
     *
     * @param hojaDeVidaId id de la hoja de vida
     * @param id Identificador de la instancia a eliminar. 
     */
     public void deleteTrayectoriaId(Long hojaDeVidaId, Long id){
        LOGGER.info("Inicia proceso de borrar trayectoria");
        TrayectoriaEntity old = getTrayectoriaId(hojaDeVidaId, id);
        LOGGER.info("Termina el proceso de borrar trayectoria");
        persistence.delete(old.getId());
    }
     
     
     /**
     * Actualiza la información de una instancia de Review.
     * @param hojaDeVidaId id de la hoja de vida
     * @param entity Instancia de TrayectoriaEntity con los nuevos datos.
     * @return Instancia de TrayectoriaEntity con los datos actualizados.
     * 
     */
     public TrayectoriaEntity updateTrayectoria(Long hojaDeVidaId, TrayectoriaEntity entity) {
        LOGGER.info("Inicia proceso de actualizar trayectoria");
        HojaDeVidaEntity hojaDeVida = hojaDeVidaLogic.getHojaDeVidaId(hojaDeVidaId);
        entity.setHojaDeVida(hojaDeVida);
        LOGGER.info("Termina el proceso de actualizar trayectoria");
        return persistence.update(entity);
    }
    
}
