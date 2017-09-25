/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.CalificacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ra.forero11
 */
public class CalificacionPersistence {

    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());

    @PersistenceContext(unitName = "traduccionesPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto calificacion que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CalificacionEntity create(CalificacionEntity entity) {
        LOGGER.info("Creando una calificacion nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la calificacion en la base de datos.
	        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando una calificacion nueva");
        return entity;
    }

    /**
     * Actualiza una calificacion.
     *
     * @param entity: la calificacion que viene con los nuevos cambios. Por
     * ejemplo el codigo pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una calificacion con los cambios aplicados.
     */
    public CalificacionEntity update(CalificacionEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando calificacion con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
	        la calificacion con los cambios, esto es similar a 
	        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra una calificacion de la base de datos recibiendo como argumento el
     * id de la calificacion
     *
     * @param id: id correspondiente a la calificacion a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando calificacion con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public calificacionEntity find(Long id) para obtener la calificacion a borrar.
        CalificacionEntity entity = em.find(CalificacionEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
	         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
	         Es similar a "delete from calificacionEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay alguna calificacion con el id que se envía de argumento
     *
     * @param id: id correspondiente a la calificacion buscada.
     * @return una calificacion.
     */
    public CalificacionEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando calificacion con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
	        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
	        Suponga que es algo similar a "select * from calificacionEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(CalificacionEntity.class, id);
    }

    /**
     * Devuelve todas las calificacions de la base de datos.
     *
     * @return una lista con todas las calificacions que encuentre en la base de
     * datos, "select u from calificacionEntity u" es como un "select * from
     * calificacionEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<CalificacionEntity> findAll() {
        LOGGER.info("Consultando todas las calificaciones");
        // Se crea un query para buscar todas las calificacions en la base de datos.
        TypedQuery query = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de calificacions.
        return query.getResultList();
    }

    /**
     * Busca si hay alguna calificacion con el nombre que se envía de argumento
     *
     * @param name: nombre de la calificacion que se está buscando
     * @return null si no existe ninguna calificacion con el nombre del
     * argumento. Si existe alguna devuelve la primera.
     */
    public CalificacionEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando Calificacion por nombre ", name);

        // Se crea un query para buscar Calificacion con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From CalificacionEntity e where e.name = :name", CalificacionEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<CalificacionEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }

}
