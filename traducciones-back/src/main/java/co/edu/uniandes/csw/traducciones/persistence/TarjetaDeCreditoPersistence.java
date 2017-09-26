/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.TarjetaDeCreditoEntity;
import javax.ejb.Stateless;
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
public class TarjetaDeCreditoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoPersistence.class.getName());

    @PersistenceContext(unitName = "traduccionesPU")
    protected EntityManager em;
    
    public TarjetaDeCreditoEntity create(TarjetaDeCreditoEntity entity){
        em.persist(entity);
        return entity;
    }
    
    public List<TarjetaDeCreditoEntity> findAll(){
        TypedQuery query = em.createQuery("select u from TarjetaDeCreditoEntity u", TarjetaDeCreditoEntity.class);
        return query.getResultList();
    }
    
    public TarjetaDeCreditoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Oferta con id={0}", id);
        return em.find(TarjetaDeCreditoEntity.class, id);
    }
    
    public TarjetaDeCreditoEntity update(TarjetaDeCreditoEntity entity){
        return em.merge(entity);
    }
    
    public void delete(Long id){
        TarjetaDeCreditoEntity entity = em.find(TarjetaDeCreditoEntity.class, id);
        em.remove(entity);
    }
    
}
