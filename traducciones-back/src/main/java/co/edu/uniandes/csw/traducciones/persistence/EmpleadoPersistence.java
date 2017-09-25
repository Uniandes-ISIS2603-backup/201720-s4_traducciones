/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.EmpleadoEntity;
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
public class EmpleadoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(EmpleadoPersistence.class.getName());
    @PersistenceContext(unitName = "traduccionesPU")
    protected EntityManager em;
    
     public EmpleadoEntity create(EmpleadoEntity entity) {
        LOGGER.info("Creando un Empleado nuevo");
        em.persist(entity);
        LOGGER.info("Creando un Empleado nuevo");
        return entity;
    }
     
     public EmpleadoEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando empleado por nombre ", name);

        // Se crea un query para buscar empleados con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From EmpleadoEntity e where e.name = :name", EmpleadoEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<EmpleadoEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
     
    public EmpleadoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando empleado con id={0}", id);
       return em.find(EmpleadoEntity.class, id);
    }

    public List<EmpleadoEntity> findAll() {
        LOGGER.info("Consultando todas los empleados");
        TypedQuery query = em.createQuery("select u from EmpleadoEntity u", EmpleadoEntity.class);
        return query.getResultList();
    }

    public EmpleadoEntity update(EmpleadoEntity entity) {
       LOGGER.log(Level.INFO, "Actualizando empleado con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
         LOGGER.log(Level.INFO, "Borrando empleado con id={0}", id);
        EmpleadoEntity entity = em.find(EmpleadoEntity.class, id);
        em.remove(entity);
    }
    
    public boolean existEmpleadoWithSameNameandDifferentId(Long id, String name) {
        LOGGER.log(Level.INFO, "Consultando empleado por nombre y id", name);

        // Se crea un query para buscar empleados con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From EmpleadoEntity e where e.name = :name and e.id <> :id" , EmpleadoEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        query = query.setParameter("id", id);
        // Se invoca el query se obtiene la lista resultado
        List<EmpleadoEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
}