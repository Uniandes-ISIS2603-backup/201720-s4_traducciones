/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.OfertaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author av.perezb
 */
 @RunWith(Arquillian.class)
public class OfertaPersistenceTest {
    
    public OfertaPersistenceTest() {
    }
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Oferta, el descriptor de la base de
     * datos y el archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OfertaEntity.class.getPackage())
                .addPackage(OfertaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * InyecciÃ³n de la dependencia a la clase OfertaPersistence cuyos
     * mÃ©todos se van a probar.
     */
    @Inject
    private OfertaPersistence persistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los mÃ©todos que se estÃ¡n probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    /**
     *
     */
    private List<OfertaEntity> data = new ArrayList<OfertaEntity>();

    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private void clearData() {
        em.createQuery("delete from OfertaEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            OfertaEntity entity = factory.manufacturePojo(OfertaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    

     /**
     * Test of create method, of class OfertaPersistence.
     */
    @Test
    public void createOfertaEntityTest() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        OfertaEntity newEntity = factory.manufacturePojo(OfertaEntity.class);
        OfertaEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        OfertaEntity entity = em.find(OfertaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    @Test
    public void findAllTest() {
        List<OfertaEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (OfertaEntity ent : list) {
            boolean found = false;
            for (OfertaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void findTest() {
        OfertaEntity entity = data.get(0);
        OfertaEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    @Test 
    public void findByNameTest() {
        
        OfertaEntity entity = data.get(0);
        List<OfertaEntity> newEntity = persistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.get(0).getName());
        
    }

    
    @Test
    public void updateOfertaTest() {
        OfertaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        OfertaEntity newEntity = factory.manufacturePojo(OfertaEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        OfertaEntity resp = em.find(OfertaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
  
    @Test
    public void deleteOfertaTest() {
        OfertaEntity entity = data.get(0);
        persistence.delete(entity.getId());
        OfertaEntity deleted = em.find(OfertaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
