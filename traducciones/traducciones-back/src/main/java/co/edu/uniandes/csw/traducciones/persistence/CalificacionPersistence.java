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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ga.garcia90
 */
@Stateless
public class CalificacionPersistence {

    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());

    @PersistenceContext(unitName = "traduccionesPU")

    protected EntityManager em;

    /**
     *
     * @param entity objeto Oferta que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CalificacionEntity create(CalificacionEntity entity) {

        LOGGER.info("Creando una Calificacion nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la Oferta 
en la base de datos. Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3,...);" en SQL.
         */
        em.persist(entity);

        LOGGER.info("Creando una Calificacion nueva");
        return entity;
    }

    /**
     * Actualiza un Oferta.
     *
     * @param entity: el Oferta que viene con los nuevos cambios. Por ejemplo el
     * codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un Oferta con los cambios aplicados.
     */
    public CalificacionEntity update(CalificacionEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Calificacion con id={0}", entity.getId());

        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la Oferta con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra un biblioteca de la base de datos recibiendo como argumento el id
     * de la Oferta
     *
     * @param id: id correspondiente a la Oferta a borrar.
     */
    public void delete(Long id) {

        LOGGER.log(Level.INFO, "Borrando Calificacion con id={0}", id);

// Se hace uso de mismo método que esta explicado en public OfertaEntity find(Long id) para obtener la Oferta a borrar.
        CalificacionEntity entity = em.find(CalificacionEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado 
"entity", volvemos hacer uso de un método propio del EntityManager para eliminar de la base de datos el objeto que 
encontramos y queremos borrar.
         Es similar a "delete from OfertaEntity where id=id;" - "DELETE FROM table_codigo WHERE 
condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     *
     * Busca si hay algun Oferta con el id que se envía de argumento
     *
     *
     * @param id: id correspondiente a la Oferta buscada.
     *
     * @return un Oferta.
     */
    public CalificacionEntity find(Long id) {

        LOGGER.log(Level.INFO, "Consultando Calificacion con id={0}", id);

        /* Note que se hace uso del metodo "find" propio del 
EntityManager, el cual recibe como argumento el tipo de la clase y el objeto que nos hara el filtro en la base de datos en 
este caso el "id" Suponga que es algo similar a "select * from OfertaEntity where id=id;" - "SELECT * FROM table_codigo 
WHERE condition; en SQL.
         */
        return em.find(CalificacionEntity.class, id);
    }

    /**
     * Devuelve todas las Oferta de la base de datos.
     *
     * @return una lista con todas las Oferta que encuentre en la base de datos,
     * "select u from OfertaEntity u" es como un "select * from OfertaEntity;" -
     * "SELECT * FROM table_codigo" en SQL.
     */
    public List<CalificacionEntity> findAll() {
        LOGGER.info("Consultando todas las calificaciones");
        // Se crea un query para buscar todas las Oferta en la base de datos.
        TypedQuery query = em.createQuery("select u from CalificacionEntity u",
                CalificacionEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de Oferta.

        return query.getResultList();
    }

}
