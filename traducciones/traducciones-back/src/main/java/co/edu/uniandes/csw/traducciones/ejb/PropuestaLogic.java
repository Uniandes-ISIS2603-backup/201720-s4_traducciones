/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.PropuestaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author av.perezb
 */
@Stateless
public class PropuestaLogic {
    
    
    private static final Logger LOGGER = Logger.getLogger(OfertaLogic.class.getName());
    
    @Inject
    private PropuestaPersistence persistence;
    
    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public PropuestaEntity createPropuesta(PropuestaEntity entity)throws BusinessLogicException {
        
        LOGGER.info("Inicia el proceso de creación de una propuesta");
        
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de una propuesta");
        return entity;
        
    }
    
    /**
     * 
     * Obtener todas las Ofertas existentes en la base de datos.
     *
     * @return una lista de Ofertas.
     */
    public List<PropuestaEntity> getPropuestas() throws Exception {
        
        LOGGER.info("Inicia proceso de consultar todas las Ofertas");
         // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
         
        List<PropuestaEntity> listaPropuestas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Ofertas");
        
        if (listaPropuestas.size()== 0)
        {
                        
             throw new Exception("No hay ofertas existentes por mostrar.");
           
        }
        
        return listaPropuestas;
                
    } 

    /**
     * Obtiene los datos de una oferta a partir de su ID.
     * @param id Identificador de la oferta a consultar.
     * @return una OfertaEntity con los datos de la oferta consultada.
     */
    public PropuestaEntity getPropuesta(Long id) {
        
        LOGGER.info("Inicia proceso de consultar una Propuesta según el id recibido por parámetro");
        PropuestaEntity propuesta = persistence.find(id);
        LOGGER.info("Termina proceso de consultar una Propuesta");
        
        return propuesta;    
        
    }
    
    /**
     * Actualiza la información de una Oferta.
     *
     * @param entity Propuesta con los nuevos datos.
     * @return Instancia de PropuestaEntity con los datos actualizados.
     * @throws BusinessLogicException si la oferta ya fue utilizada.
     **/
    public PropuestaEntity updatePropuesta (PropuestaEntity entity) throws BusinessLogicException {
        
        LOGGER.info("Inicia la modificaión de una oferta");
        
        if(entity.getEstado().equals("ACEPTADA")) {
            
            throw new BusinessLogicException("No puede modificarse la propuesta porque ya fue aceptada");
      
        }
                  
        LOGGER.info("Finaliza la modificación");
        return persistence.update(entity);
        
    }
  
    public void deletePropuesta (Long id) throws BusinessLogicException{
        
        if (persistence.find(id) == null) {
            
            throw new BusinessLogicException("No se encontró ninguna Propuesta con ese id.");
        }
        persistence.delete(id);      
       
    }
       

}
