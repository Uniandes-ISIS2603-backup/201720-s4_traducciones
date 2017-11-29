/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.SolicitudEntity;
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
public class SolicitudPersistence {

    private static final Logger LOGGER = Logger.getLogger(SolicitudPersistence.class.getName());

    @PersistenceContext(unitName = "traduccionesPU")
    protected EntityManager em;

    /**
     * @param entity objeto solicitud que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public SolicitudEntity create(SolicitudEntity entity) {
        LOGGER.info("Creando una solicitud nueva");
        em.persist(entity);
        LOGGER.info("Creando una solicitud nueva");
        return entity;
    }

    /**
     * Actualiza una solicitud.
     * @param entity: la solicitud que viene con los nuevos cambios. Por
     * ejemplo el codigo pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una solicitud con los cambios aplicados.
     */
    public SolicitudEntity update(SolicitudEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando solicitud con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * Borra una solicitud de la base de datos recibiendo como argumento el
     * id de la solicitud
     * @param id: id correspondiente de la solicitud a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando solicitud con id={0}", id);
        SolicitudEntity entity = em.find(SolicitudEntity.class, id);
        em.remove(entity);
    }

    /**
     * Busca si hay alguna solicitud con el id que se envía de argumento
     * @param id: id correspondiente a la solicitud buscada.
     * @return una solicitud.
     */
    public SolicitudEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando solicitud con id={0}", id);
        return em.find(SolicitudEntity.class, id);
    }

    /**
     * Devuelve todas las solicitudes de la base de datos.
     * @return una lista con todas las solicitudes que encuentre en la base de
     * datos
     */
    public List<SolicitudEntity> findAll() {
        LOGGER.info("Consultando todas las solicitudes");
        TypedQuery query = em.createQuery("select u from SolicitudEntity u", SolicitudEntity.class);
        return query.getResultList(); 
    }
}