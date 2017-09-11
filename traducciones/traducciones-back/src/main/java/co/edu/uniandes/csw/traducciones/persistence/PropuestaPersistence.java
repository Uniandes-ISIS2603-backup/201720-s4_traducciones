/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.persistence;
import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author av.perezb
 */
@Stateless
public class PropuestaPersistence {
    
    
    private static final Logger 
LOGGER = Logger.getLogger(DefaultPersistence.class.getName());

    
@PersistenceContext(unitName = "traduccionesPU")
    
protected EntityManager em;

    /**
     *
     * @param entity objeto Propuesta que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PropuestaEntity create(PropuestaEntity entity) {
        
LOGGER.info("Creando un Propuesta nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la Propuesta 
en la base de datos. Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3,...);" en SQL.
        */
        em.persist(entity);
       
LOGGER.info("Creando un Propuesta nuevo");
        return entity;
    }

 
   /**
     * Actualiza un Propuesta.
     *
     * @param entity: el Propuesta que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un Propuesta con los cambios aplicados.
     */
    public PropuestaEntity update(PropuestaEntity entity) {
        LOGGER.log
(Level.INFO, "Actualizando Propuesta con id={0}", entity.getId());
    
    /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la Propuesta con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
    */
      return em.merge(entity);
    }

    /**
     *
     * Borra un biblioteca de la base de datos recibiendo como argumento el id de la Propuesta
     *
     * @param id: id correspondiente a la Propuesta a borrar.
     */
    public void delete(Long id) {
        
LOGGER.log(Level.INFO, "Borrando Propuesta con id={0}", id);
        
// Se hace uso de mismo método que esta explicado en public PropuestaEntity find(Long id) para obtener la Propuesta a borrar.
       
 PropuestaEntity entity = em.find(PropuestaEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado 
"entity", volvemos hacer uso de un método propio del EntityManager para eliminar de la base de datos el objeto que 
encontramos y queremos borrar.
         Es similar a "delete from PropuestaEntity where id=id;" - "DELETE FROM table_codigo WHERE 
condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * 
Busca si hay algun Propuesta con el id que se envía de argumento
     
*
     * @param id: id correspondiente a la Propuesta buscada.
     * 
@return un Propuesta.
     */
    public PropuestaEntity find(Long id) {
  
      LOGGER.log(Level.INFO, "Consultando Propuesta con id={0}", id);
 
       /* Note que se hace uso del metodo "find" propio del 
EntityManager, el cual recibe como argumento el tipo de la clase y el objeto que nos hara el filtro en la base de datos en 
este caso el "id" Suponga que es algo similar a "select * from PropuestaEntity where id=id;" - "SELECT * FROM table_codigo 
WHERE condition; en SQL.
         */
        return em.find
(PropuestaEntity.class, id);
    }

    /**
     * Devuelve todas las Propuesta de la base de datos.
     * @return una lista con todas las Propuesta que encuentre en la base de
     * datos, "select u from PropuestaEntity u" es como un "select * from
     * PropuestaEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<PropuestaEntity> findAll() {
        LOGGER.info
("Consultando todas las Propuesta");
        // Se crea un query para buscar todas las Propuesta en la base de datos.
        TypedQuery 
query = em.createQuery("select u from PropuestaEntity u", 
PropuestaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de Propuesta.
        
return query.getResultList();
    }
    
}
