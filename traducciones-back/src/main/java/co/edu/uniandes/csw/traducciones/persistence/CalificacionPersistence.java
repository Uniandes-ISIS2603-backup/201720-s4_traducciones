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
 * @author ra.forero11
 */
@Stateless
public class CalificacionPersistence {


    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());

    @PersistenceContext(unitName = "traduccionesPU")
    protected EntityManager em;

    public CalificacionEntity create(CalificacionEntity entity) {
        LOGGER.info("Creando un calificacion nuevo");
        em.persist(entity);
        LOGGER.info("Calificacion creado");
        return entity;
    }

    public CalificacionEntity update(CalificacionEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando calificacion con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando calificacion con id={0}", id);
        CalificacionEntity entity = em.find(CalificacionEntity.class, id);
        em.remove(entity);
    }

    public CalificacionEntity find(Long trabajoid, Long calificacionid) {
        TypedQuery<CalificacionEntity> q = em.createQuery("select p from CalificacionEntity p where (p.trabajo.id = :trabajoid) and (p.id = :calificacionid)", CalificacionEntity.class);
        q.setParameter("trabajoid", trabajoid);
        q.setParameter("calificacionid", calificacionid);
        List<CalificacionEntity> results = q.getResultList();
        CalificacionEntity calificacion = null;
        if (results == null) {
            calificacion = null;
        } else if (results.isEmpty()) {
            calificacion = null;
        } else if (results.size() >= 1) {
            calificacion = results.get(0);
        }

        return calificacion;
    }
    
    public List<CalificacionEntity> findAll(){
        TypedQuery query = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        return query.getResultList();
    }
    
     public CalificacionEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Oferta con id={0}", id);
        return em.find(CalificacionEntity.class, id);
    }
}
