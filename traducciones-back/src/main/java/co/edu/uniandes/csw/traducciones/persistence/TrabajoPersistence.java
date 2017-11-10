/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.TrabajoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ra.forero11
 */
@Stateless
public class TrabajoPersistence {

    private static final Logger LOGGER = Logger.getLogger(TrabajoPersistence.class.getName());

    @PersistenceContext(unitName = "traduccionesPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto trabajo que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public TrabajoEntity create(TrabajoEntity entity) {
        LOGGER.info("Creando una trabajo nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la trabajo en la base de datos.
	        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando una trabajo nueva");
        return entity;
    }

    /**
     * Actualiza una trabajo.
     *
     * @param entity: la trabajo que viene con los nuevos cambios. Por
     * ejemplo el codigo pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una trabajo con los cambios aplicados.
     */
    public TrabajoEntity update(TrabajoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando trabajo con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
	        la trabajo con los cambios, esto es similar a 
	        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra una trabajo de la base de datos recibiendo como argumento el
     * id de la trabajo
     *
     * @param id: id correspondiente a la trabajo a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando trabajo con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public trabajoEntity find(Long id) para obtener la trabajo a borrar.
        TrabajoEntity entity = em.find(TrabajoEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
	         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
	         Es similar a "delete from trabajoEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay alguna trabajo con el id que se envía de argumento
     *
     * @param id: id correspondiente a la trabajo buscada.
     * @return una trabajo.
     */
    public TrabajoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando trabajo con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
	        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
	        Suponga que es algo similar a "select * from trabajoEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(TrabajoEntity.class, id);
    }

    /**
     * Devuelve todas las trabajos de la base de datos.
     *
     * @return una lista con todas las trabajos que encuentre en la base de
     * datos, "select u from trabajoEntity u" es como un "select * from
     * trabajoEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<TrabajoEntity> findAll() {
        LOGGER.info("Consultando todas las trabajoes");
        // Se crea un query para buscar todas las trabajos en la base de datos.
        TypedQuery query = em.createQuery("select u from TrabajoEntity u", TrabajoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de trabajos.
        return query.getResultList();
    }

    

}

