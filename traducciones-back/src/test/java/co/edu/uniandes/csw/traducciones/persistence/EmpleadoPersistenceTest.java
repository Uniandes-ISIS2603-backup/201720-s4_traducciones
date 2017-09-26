/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.EmpleadoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jc.gloria
 */
@RunWith(Arquillian.class)
public class EmpleadoPersistenceTest {
    
    @Inject
    private EmpleadoPersistence persistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<EmpleadoEntity> data = new ArrayList<EmpleadoEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EmpleadoEntity.class.getPackage())
                .addPackage(EmpleadoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public EmpleadoPersistenceTest() {
    }
    
    private void clearData() {
        em.createQuery("delete from EmpleadoEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EmpleadoEntity entity = factory.manufacturePojo(EmpleadoEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
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
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void createEmpleadoEntityTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EmpleadoEntity newEntity = factory.manufacturePojo(EmpleadoEntity.class);
        EmpleadoEntity result = persistence.create(newEntity);
        
        Assert.assertNotNull(result);
        EmpleadoEntity entity = em.find(EmpleadoEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    @Test
    public void getEmpleadosTest() {
        List<EmpleadoEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EmpleadoEntity ent : list) {
            boolean found = false;
            for (EmpleadoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getEmpleadoTest() {
        EmpleadoEntity entity = data.get(0);
        EmpleadoEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    @Test
    public void getEmpleadoByNameTest() {
        EmpleadoEntity entity = data.get(0);
        EmpleadoEntity newEntity = persistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    @Test
    public void updateEmpleadoTest() {
        EmpleadoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EmpleadoEntity newEntity = factory.manufacturePojo(EmpleadoEntity.class);
        
        newEntity.setId(entity.getId());
        
        persistence.update(newEntity);
        
        EmpleadoEntity resp = em.find(EmpleadoEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
    @Test
    public void deleteEntityTest() {
        EmpleadoEntity entity = data.get(0);
        //el parametro de persistence.delete es un EmpleadoEntity. Asi que no se puede entity.getID()
        persistence.delete(entity.getId());
        EmpleadoEntity deleted = em.find(EmpleadoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}