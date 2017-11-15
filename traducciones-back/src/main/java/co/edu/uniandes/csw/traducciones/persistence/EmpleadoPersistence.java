package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.EmpleadoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author jc.gloria
 */
@Stateless
public class EmpleadoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(EmpleadoPersistence.class.getName());
    @PersistenceContext(unitName = "traduccionesPU")
    protected EntityManager em;
    
    /**
     *Crea un empeado nuevo en la base de datos
     * @param entity objeto empleado que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public EmpleadoEntity create(EmpleadoEntity entity) {
        LOGGER.info("Creando un Empleado nuevo");
        em.persist(entity);
        LOGGER.info("Creando un Empleado nuevo");
        return entity;
    }
    
    /**
     * Encuentra un empleado especifico con el nombre.
     * @param name el nombre del empleado que se va a buscar
     * @return el objeto Empleado que tiene el nombre deseado. Null si no lo encuentra.
     */
    public EmpleadoEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando empleado por nombre ", name);
        
        // Se crea un query para buscar empleados con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From EmpleadoEntity e where e.name = :name", EmpleadoEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<EmpleadoEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
    
    /**
     * Encuentra un empleado especifico con el id.
     * @param id del empleado que se quiere buscar.
     * @return el objeto Empleado que tiene el id deseado.
     */
    public EmpleadoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando empleado con id={0}", id);
        return em.find(EmpleadoEntity.class, id);
    }
    
    /**
     * Devuelve todos los empleados en la base de datos.
     * @return una lista con todos los empleados.
     */
    public List<EmpleadoEntity> findAll() {
        LOGGER.info("Consultando todas los empleados");
        TypedQuery query = em.createQuery("select u from EmpleadoEntity u", EmpleadoEntity.class);
        return query.getResultList();
    }
    
    /**
     * Actualiza la informacion de un empleado.
     * @param entity el empleado que se quiere actualizar con los datos nuevos deseados
     * @return el empleado con los datos actualizados.
     */
    public EmpleadoEntity update(EmpleadoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando empleado con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Borra un empleado dado un id
     * @param id del empleado que se quiere borrar
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando empleado con id={0}", id);
        EmpleadoEntity entity = em.find(EmpleadoEntity.class, id);
        em.remove(entity);
    }
    
    /**
     * Ver si existen dos empleados con el mismo nombre
     * @param id id que se esta buscando
     * @param name nombre que se esta buscando
     * @return true si hay dos empleados con el mismo nombre e Id diferente, false si no.
     */
    public boolean existEmpleadoWithSameNameandDifferentId(Long id, String name) {
        LOGGER.log(Level.INFO, "Consultando empleado por nombre y id", name);
        
        // Se crea un query para buscar empleados con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From EmpleadoEntity e where e.name = :name and e.id <> :id" , EmpleadoEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento
        query = query.setParameter("name", name);
        query = query.setParameter("id", id);
        // Se invoca el query se obtiene la lista resultado
        List<EmpleadoEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }  
}