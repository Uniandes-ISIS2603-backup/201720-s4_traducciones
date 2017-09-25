/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.OfertaEntity;
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
public class OfertaPersistence {


    private static final Logger LOGGER = Logger.getLogger(OfertaPersistence.class.getName());

    @PersistenceContext(unitName = "traduccionesPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto Oferta que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public OfertaEntity create(OfertaEntity entity) {

        LOGGER.info("Creando una Oferta nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la Oferta 
en la base de datos. Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3,...);" en SQL.
         */
        em.persist(entity);

        LOGGER.info("Creando una Oferta nueva");
        return entity;
    }

    /**
     * Actualiza un Oferta.
     *
     * @param entity: el Oferta que viene con los nuevos cambios. Por ejemplo el
     * codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un Oferta con los cambios aplicados.
     */
    public OfertaEntity update(OfertaEntity entity) {
    
        LOGGER.log(Level.INFO, "Actualizando Oferta con id={0}", entity.getId());

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

        LOGGER.log(Level.INFO, "Borrando Oferta con id={0}", id);

// Se hace uso de mismo método que esta explicado en public OfertaEntity find(Long id) para obtener la Oferta a borrar.
        OfertaEntity entity = em.find(OfertaEntity.class, id);
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
    public OfertaEntity find(Long id) {

        LOGGER.log(Level.INFO, "Consultando Oferta con id={0}", id);

        /* Note que se hace uso del metodo "find" propio del 
EntityManager, el cual recibe como argumento el tipo de la clase y el objeto que nos hara el filtro en la base de datos en 
este caso el "id" Suponga que es algo similar a "select * from OfertaEntity where id=id;" - "SELECT * FROM table_codigo 
WHERE condition; en SQL.
         */
        return em.find(OfertaEntity.class, id);
    }
    
    /**
     * Busca si hay alguna oferta con el nombre que se envía de argumento
     * @param name: Nombre de la oferta que se está buscando
     * @return null si no existe ninguna editorial con el nombre del argumento.
     * Si existen las retorna.
     */
     public List<OfertaEntity> findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando ofertas por nombre ", name);

        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From OfertaEntity e where e.name = :name", OfertaEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<OfertaEntity> sameName = query.getResultList();
        
        if (sameName == null ) {
            return null;
        } else if (sameName.isEmpty()) {
             return null;
        } else {
            return sameName;
        }
        
    }
     
     /**
     * Devuelve todas las Ofertas de la base de datos.
     *
     * @return una lista con todas las Oferta que encuentre en la base de datos,
     * "select u from OfertaEntity u" es como un "select * from OfertaEntity;" -
     * "SELECT * FROM table_codigo" en SQL.
     */
    public List<OfertaEntity> findAll() {
        LOGGER.info("Consultando todas las Ofertas");
        // Se crea un query para buscar todas las Oferta en la base de datos.
        TypedQuery query = em.createQuery("select u from OfertaEntity u",
                OfertaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de Oferta.

        return query.getResultList();
    }

}

