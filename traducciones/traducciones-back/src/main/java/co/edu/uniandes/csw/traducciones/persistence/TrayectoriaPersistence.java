/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.TrayectoriaEntity;
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
public class TrayectoriaPersistence {
    private static final Logger LOGGER = Logger.getLogger(TrayectoriaPersistence.class.getName());

    @PersistenceContext(unitName = "traduccionesPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto trayectoria que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public TrayectoriaEntity create(TrayectoriaEntity entity) {
        LOGGER.info("Creando una trayectoria nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la trayectoria en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando una trayectoria nueva");
        return entity;
    }

    /**
     * Actualiza una trayectoria.
     *
     * @param entity: la trayectoria que viene con los nuevos cambios. Por
     * ejemplo el codigo pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una trayectoria con los cambios aplicados.
     */
    public TrayectoriaEntity update(TrayectoriaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando trayectoria con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la trayectoria con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra una trayectoria de la base de datos recibiendo como argumento el id
     * de la trayectoria
     *
     * @param id: id correspondiente a la trayectoria a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando trayectoria con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public trayectoriaEntity find(Long id) para obtener la trayectoria a borrar.
        TrayectoriaEntity entity = em.find(TrayectoriaEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from trayectoriaEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay alguna trayectoria con el id que se envía de argumento
     *
     * @param id: id correspondiente a la trayectoria buscada.
     * @return una trayectoria.
     */
    public TrayectoriaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando trayectoria con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from trayectoriaEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(TrayectoriaEntity.class, id);
    }

    /**
     * Devuelve todas las trayectorias de la base de datos.
     *
     * @return una lista con todas las trayectorias que encuentre en la base de
     * datos, "select u from trayectoriaEntity u" es como un "select * from
     * trayectoriaEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<TrayectoriaEntity> findAll() {
        LOGGER.info("Consultando todas las trayectorias");
        // Se crea un query para buscar todas las trayectorias en la base de datos.
        TypedQuery query = em.createQuery("select u from TrayectoriaEntity u", TrayectoriaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de trayectorias.
        return query.getResultList();
    }

    /**
     * Busca si hay alguna trayectoria con el nombre que se envía de argumento
     *
     * @param name: nombre de la trayectoria que se está buscando
     * @return null si no existe ninguna trayectoria con el nombre del
     * argumento. Si existe alguna devuelve la primera.
     */
    public TrayectoriaEntity findByName(String name) {
       LOGGER.log(Level.INFO, "Consultando Trayectoria por nombre ", name);

        // Se crea un query para buscar TrayectoriaEntity con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From TrayectoriaEntity e where e.name = :name", TrayectoriaEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<TrayectoriaEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
}
