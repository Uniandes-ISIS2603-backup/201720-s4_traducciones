/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author av.perezb
 */
 @RunWith(Arquillian.class)
public class PropuestaPersistenceTest {
    
    public PropuestaPersistenceTest() {
    }
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Propuesta, el descriptor de la base de
     * datos y el archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PropuestaEntity.class.getPackage())
                .addPackage(PropuestaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase PropuestaPersistence cuyos
     * métodos que se van a probar.
     */
    @Inject
    private PropuestaPersistence persistence;

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
    private List<PropuestaEntity> data = new ArrayList<PropuestaEntity>();

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
        em.createQuery("delete from PropuestaEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PropuestaEntity entity = factory.manufacturePojo(PropuestaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    

     /**
     * Test of create method, of class PropuestaPersistence.
     */
    @Test
    public void createPropuestaEntityTest() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        PropuestaEntity newEntity = factory.manufacturePojo(PropuestaEntity.class);
        PropuestaEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        PropuestaEntity entity = em.find(PropuestaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    @Test
    public void findAllTest() {
        List<PropuestaEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PropuestaEntity ent : list) {
            boolean found = false;
            for (PropuestaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void findTest() {
        PropuestaEntity entity = data.get(0);
        PropuestaEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    @Test
    public void updatePropuestaTest() {
        PropuestaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PropuestaEntity newEntity = factory.manufacturePojo(PropuestaEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        PropuestaEntity resp = em.find(PropuestaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
  
    @Test
    public void deletePropuestaTest() {
        PropuestaEntity entity = data.get(0);
        persistence.delete(entity.getId());
        PropuestaEntity deleted = em.find(PropuestaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}

