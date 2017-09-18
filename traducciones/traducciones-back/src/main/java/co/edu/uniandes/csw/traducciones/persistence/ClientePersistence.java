/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.ClienteEntity;
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
public class ClientePersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());

    @PersistenceContext(unitName = "traduccionesPU")
    
    protected EntityManager em;
    
    public ClienteEntity create(ClienteEntity entity){
        em.persist(entity);
        return entity;
    }
    
    public List<ClienteEntity> findAll(){
        TypedQuery query = em.createQuery("select u from ClienteEntity u", ClienteEntity.class);
        return query.getResultList();
    }
    
    public ClienteEntity find(Long id) {

        LOGGER.log(Level.INFO, "Consultando Oferta con id={0}", id);
        return em.find(ClienteEntity.class, id);
    }
    
    public ClienteEntity update(ClienteEntity entity){
        return em.merge(entity);
    }
    
    public void delete(Long id){
        ClienteEntity entity = em.find(ClienteEntity.class, id);
        em.remove(entity);
    }
}
