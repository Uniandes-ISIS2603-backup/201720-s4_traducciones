/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.PagoEntity;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ne.ortega
 */
@Stateless
public class PagoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(PagoPersistence.class.getName());

    @PersistenceContext(unitName = "traduccionesPU")
    
    protected EntityManager em;
    
    public PagoEntity create(PagoEntity entity){
        em.persist(entity);
        return entity;
    }
    
    public List<PagoEntity> findAll(){
        TypedQuery query = em.createQuery("select u from PagoEntity u", PagoEntity.class);
        return query.getResultList();
    }
    
    public PagoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Pago con id={0}", id);
        return em.find(PagoEntity.class, id);
    }
    
    public PagoEntity update(PagoEntity entity){
        return em.merge(entity);
    }
    
    public void delete(Long id){
        PagoEntity entity = em.find(PagoEntity.class, id);
        em.remove(entity);
    }
    
}
