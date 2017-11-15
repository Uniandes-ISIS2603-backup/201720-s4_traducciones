package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.AreaDeConocimientoEntity;
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
public class AreaDeConocimientoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(AreaDeConocimientoPersistence.class.getName());
    @PersistenceContext(unitName = "traduccionesPU")
    protected EntityManager em;
    
    /**
     *Crea una area de conocimiento nuevo en la base de datos
     * @param entity objeto areadeconocimiento que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public AreaDeConocimientoEntity create(AreaDeConocimientoEntity entity) {
        LOGGER.info("Creando un AreaDeConocimiento nuevo");
        em.persist(entity);
        LOGGER.info("Creando un AreaDeConocimiento nuevo");
        return entity;
    }
    
    /**
     * Encuentra un AreaDeConocimiento especifico con el nombre.
     * @param name el nombre del AreaDeConocimiento que se va a buscar
     * @return el objeto AreaDeConocimiento que tiene el nombre deseado. Null si no lo encuentra.
     */
    public AreaDeConocimientoEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando area de conocimiento por nombre ", name);
        
        // Se crea un query para buscar empleados con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From AreaDeConocimientoEntity e where e.name = :name", AreaDeConocimientoEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<AreaDeConocimientoEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
    
    /**
     * Encuentra un AreaDeConocimiento especifico con el id.
     * @param id del AreaDeConocimiento que se quiere buscar.
     * @return el objeto AreaDeConocimiento que tiene el id deseado.
     */
    public AreaDeConocimientoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando area de conocimiento con id={0}", id);
        return em.find(AreaDeConocimientoEntity.class, id);
    }
    
    /**
     * Devuelve todos las AreaDeConocimiento en la base de datos.
     * @return una lista con todas las AreaDeConocimiento.
     */
    public List<AreaDeConocimientoEntity> findAll() {
        LOGGER.info("Consultando todas las areas de conocimiento");
        TypedQuery query = em.createQuery("select u from AreaDeConocimientoEntity u", AreaDeConocimientoEntity.class);
        return query.getResultList();
    }
    
    /**
     * Actualiza la informacion de una AreaDeConocimiento.
     * @param entity el AreaDeConocimiento que se quiere actualizar con los datos nuevos deseados
     * @return el AreaDeConocimiento con los datos actualizados.
     */
    public AreaDeConocimientoEntity update(AreaDeConocimientoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando area de conocimiento con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Borra un AreaDeConocimiento dado un id
     * @param id del AreaDeConocimiento que se quiere borrar
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando area de conocimiento con id={0}", id);
        AreaDeConocimientoEntity entity = em.find(AreaDeConocimientoEntity.class, id);
        em.remove(entity);
    }
    
    /**
     * Ver si existen dos AreaDeConocimiento con el mismo nombre
     * @param id id que se esta buscando
     * @param name nombre que se esta buscando
     * @return true si hay dos AreaDeConocimientos con el mismo nombre e Id diferente, false si no.
     */
    public boolean existAreaDeConocimientoWithSameNameandDifferentId(Long id, String name) {
        LOGGER.log(Level.INFO, "Consultando area de conocimiento por nombre y id", name);
        
        // Se crea un query para buscar areas de conocimiento con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From AreaDeConocimientoEntity e where e.name = :name and e.id <> :id" , AreaDeConocimientoEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento
        query = query.setParameter("name", name);
        query = query.setParameter("id", id);
        // Se invoca el query se obtiene la lista resultado
        List<AreaDeConocimientoEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
