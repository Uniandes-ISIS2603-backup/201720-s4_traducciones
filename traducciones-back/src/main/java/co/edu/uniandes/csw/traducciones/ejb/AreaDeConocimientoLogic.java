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
    
    /**
     * Se agrega una area de conocimiento a partir de una entidad.
     * @param entity objeto entidad que se agregara a la base de datos
     * @return la misma entidad pasada por parametro
     * @throws BusinessLogicException si ya existe una area de conocimiento con ese nombre
     */
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
    
    /**
     * Devuelve todas las areas de conocimiento en el sistema
     * @return una lista con objetos de tipo entidad.
     */
    public List<AreaDeConocimientoEntity> getAreasDeConocimientos() {
        LOGGER.info("Inicia proceso de consultar todas las areas de conocimiento");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<AreaDeConocimientoEntity> empleados = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las areas de conocimiento");
        return empleados;
    }
    /**
     * Devuelve una area de conocimiento a partir de un id
     * @param id id de la area que se quiere buscar
     * @return la entidad que concuerda con el id.
     * @throws BusinessLogicException  Si no existe una area con el id pasado por parametro.
     */
    public AreaDeConocimientoEntity getAreaDeConocimiento(Long id) throws BusinessLogicException {
        AreaDeConocimientoEntity area = persistence.find(id);
        if(area == null) {
            throw new BusinessLogicException("No existe un area de conocimiento con el id \"" + id + "\"");
        }
        return area;
    }
    
    /**
     * Actualiza una area de conocimiento en la base de datos
     * @param id id de la area que se quiere actualizar
     * @param entity objeto entidad por la cual se va a reemplazar el area deseada.
     * @return la entidad con los datos actualizados
     * @throws BusinessLogicException
     */
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
    
    /**
     * Borra una area de conocimiento de la base de datos
     * @param entidad que se quiere borrar
     * @throws BusinessLogicException si no existe la entidad deseada en el sistema
     */
    public void deleteAreaDeConocimiento(AreaDeConocimientoEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar area de conocimiento con id={0}", entity.getId());
        if(persistence.find(entity.getId()) == null){
            throw new BusinessLogicException("No existe un area de conocimiento con este id");
        }
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar area de conocimientp con id={0}", entity.getId());
        
    }
}
