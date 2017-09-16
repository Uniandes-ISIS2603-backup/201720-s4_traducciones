package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.EmpleadoEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.EmpleadoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.List;

/**
 *
 * @author jc.gloria
 */

@Stateless
public class EmpleadoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(EmpleadoLogic.class.getName());

    @Inject
    private EmpleadoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    public EmpleadoEntity createEmpleado(EmpleadoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de empleado");
        // Verifica la regla de negocio que dice que no puede haber dos empleados con el mismo nombre
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe un Empleado con el nombre \"" + entity.getName() + "\"");
        }
        // Invoca la persistencia para crear la city
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación del empleado");
        return entity;
    }

    public List<EmpleadoEntity> getEmpleados() {
        LOGGER.info("Inicia proceso de consultar todos los empleados");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<EmpleadoEntity> empleados = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los empleados");
        return empleados;
    }

    public EmpleadoEntity getEmpleado(Long id) {
        EmpleadoEntity city = persistence.find(id);
        return city;
    }

    public EmpleadoEntity updateEmpleado(Long id, EmpleadoEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar city con id={0}", id);
        if (persistence.existEmpleadoWithSameNameandDifferentId(id, entity.getName())) {
            throw new BusinessLogicException("Ya existe una ciudad con ese nombre "+entity.getName());
        }
        EmpleadoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar city con id={0}", entity.getId());
        return newEntity;
    }

    public void deleteEmpleado(EmpleadoEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar city con id={0}", entity.getId());
        persistence.delete(entity.getId());
         LOGGER.log(Level.INFO, "Termina proceso de borrar city con id={0}", entity.getId());
        
    }
    
}
