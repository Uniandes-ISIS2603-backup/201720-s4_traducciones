/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.HojaDeVidaEntity;
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
public class HojaDeVidaPersistence {
    private static final Logger LOGGER = Logger.getLogger(TrayectoriaPersistence.class.getName());

    @PersistenceContext(unitName = "traduccionesPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto hojaDeVida que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public HojaDeVidaEntity create(HojaDeVidaEntity entity) {
        LOGGER.info("Creando una trayectoria nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la cantante en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando una trayectoria nueva");
        return entity;
    }

    /**
     * Actualiza una hojaDeVida.
     *
     * @param entity: la hojaDeVida que viene con los nuevos cambios. Por
     * ejemplo el codigo pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una hojaDeVida con los cambios aplicados.
     */
    public HojaDeVidaEntity update(HojaDeVidaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando HojaDeVida con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la hojaDeVida con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra una hojaDeVida de la base de datos recibiendo como argumento el id
     * de la hojaDeVida
     *
     * @param id: id correspondiente a la hojaDeVida a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando hojaDeVida con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public HojaDeVidaEntity find(Long id) para obtener la trayectoria a borrar.
        HojaDeVidaEntity entity = em.find(HojaDeVidaEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from hojaDeVidaEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay alguna hojaDeVida con el id que se envía de argumento
     *
     * @param id: id correspondiente a la hojaDeVida buscada.
     * @return una hojaDeVida.
     */
    public HojaDeVidaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando hojaDeVida con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from HojaDeVidaEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(HojaDeVidaEntity.class, id);
    }

    /**
     * Devuelve todas las hojasDeVida de la base de datos.
     *
     * @return una lista con todas las trayectorias que encuentre en la base de
     * datos, "select u from hojaDeVidaEntity u" es como un "select * from
     * hojaDeVidaEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<HojaDeVidaEntity> findAll() {
        LOGGER.info("Consultando todas las trayectorias");
        // Se crea un query para buscar todas las HojasDeVida en la base de datos.
        TypedQuery query = em.createQuery("select u from HojaDeVidaEntity u", HojaDeVidaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de HojasDeVida.
        return query.getResultList();
    }

    /**
     * Busca si hay alguna hojaDeVida con el nombre que se envía de argumento
     *
     * @param name: nombre de la hojaDeVida que se está buscando
     * @return null si no existe ninguna hojaDeVida con el nombre del
     * argumento. Si existe alguna devuelve la primera.
     */
    public HojaDeVidaEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando hojaDeVida por nombre ", name);

        // Se crea un query para buscar HojasDeVidaEntity con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From HojaDeVidaEntity e where e.name = :name", HojaDeVidaEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<HojaDeVidaEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
}
