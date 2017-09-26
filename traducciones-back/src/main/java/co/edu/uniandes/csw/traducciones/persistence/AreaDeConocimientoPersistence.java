/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author jc.gloria
 */

@Stateless
public class AreaDeConocimientoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(AreaDeConocimientoPersistence.class.getName());
    @PersistenceContext(unitName = "traduccionesPU")
    protected EntityManager em;
    
     public AreaDeConocimientoEntity create(AreaDeConocimientoEntity entity) {
        LOGGER.info("Creando un AreaDeConocimiento nuevo");
        em.persist(entity);
        LOGGER.info("Creando un AreaDeConocimiento nuevo");
        return entity;
    }
     
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
     
    public AreaDeConocimientoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando area de conocimiento con id={0}", id);
       return em.find(AreaDeConocimientoEntity.class, id);
    }

    public List<AreaDeConocimientoEntity> findAll() {
        LOGGER.info("Consultando todas las areas de conocimiento");
        TypedQuery query = em.createQuery("select u from AreaDeConocimientoEntity u", AreaDeConocimientoEntity.class);
        return query.getResultList();
    }

    public AreaDeConocimientoEntity update(AreaDeConocimientoEntity entity) {
       LOGGER.log(Level.INFO, "Actualizando area de conocimiento con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
         LOGGER.log(Level.INFO, "Borrando area de conocimiento con id={0}", id);
         AreaDeConocimientoEntity entity = em.find(AreaDeConocimientoEntity.class, id);
        em.remove(entity);
    }
    
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
