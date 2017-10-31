/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.AreaDeConocimientoEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.AreaDeConocimientoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author jc.gloria
 */
public class AreaDeConocimientoLogic {
    
    
    private static final Logger LOGGER = Logger.getLogger(AreaDeConocimientoLogic.class.getName());

    @Inject
    private AreaDeConocimientoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    public AreaDeConocimientoEntity createAreaDeConocimiento(AreaDeConocimientoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de area de conocimiento");
        // Verifica la regla de negocio que dice que no puede haber dos empleados con el mismo nombre
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe un AreaDeConocimiento con el nombre \"" + entity.getName() + "\"");
        }
        // Invoca la persistencia para crear el area de conocimiento
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación del area de conocimiento");
        return entity;
    }

    public List<AreaDeConocimientoEntity> getAreasDeConocimientos() {
        LOGGER.info("Inicia proceso de consultar todas las areas de conocimiento");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<AreaDeConocimientoEntity> empleados = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las areas de conocimiento");
        return empleados;
    }
    
    public AreaDeConocimientoEntity getAreaDeConocimiento(Long id) throws BusinessLogicException {
        AreaDeConocimientoEntity area = persistence.find(id);
        if(area == null) {
            throw new BusinessLogicException("No existe un area de conocimiento con el id \"" + id + "\"");
        }
        return area;
    }

    public AreaDeConocimientoEntity updateAreaDeConocimiento(Long id, AreaDeConocimientoEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar area de conocimiento con id={0}", id);
        if (persistence.existAreaDeConocimientoWithSameNameandDifferentId(id, entity.getName())) {
            throw new BusinessLogicException("Ya existe un area de conocimiento con ese nombre "+entity.getName());
        }
        entity.setId(id);
        if(entity.getDescripcion() == null){
            entity.setDescripcion(persistence.find(id).getDescripcion());
        }
        if(entity.getEmpleado() == null){
            entity.setEmpleado(persistence.find(id).getEmpleado());
        }
        if(entity.getName() == null){
            entity.setName(persistence.find(id).getName());
        }
        
        AreaDeConocimientoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar area de conocimiento con id={0}", entity.getId());
        return newEntity;
    }

    public void deleteAreaDeConocimiento(AreaDeConocimientoEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar area de conocimiento con id={0}", entity.getId());
        if(persistence.find(entity.getId()) == null){
            throw new BusinessLogicException("No existe un area de conocimiento con este id");
        }
        persistence.delete(entity.getId());
         LOGGER.log(Level.INFO, "Termina proceso de borrar area de conocimientp con id={0}", entity.getId());
        
    }
    
}
