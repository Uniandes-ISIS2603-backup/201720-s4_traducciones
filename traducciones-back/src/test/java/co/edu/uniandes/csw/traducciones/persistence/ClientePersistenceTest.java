/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.ClienteEntity;
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
 * @author ne.ortega
 */
@RunWith(Arquillian.class)
public class ClientePersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private ClientePersistence persistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

     /**
     *
     */
    private List<ClienteEntity> data = new ArrayList<ClienteEntity>();
    
    public ClientePersistenceTest() {
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
    
    private void clearData() {
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }


    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class ClientePersistence.
     */
    @Test
    public void testCreate(){
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        ClienteEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        ClienteEntity entity = em.find(ClienteEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of findAll method, of class ClientePersistence.
     */
    @Test
    public void testFindAll(){
        List<ClienteEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ClienteEntity ent : list) {
            boolean found = false;
            for (ClienteEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of find method, of class ClientePersistence.
     */
    @Test
    public void testFind(){
        ClienteEntity entity = data.get(0);
        ClienteEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of update method, of class ClientePersistence.
     */
    @Test
    public void testUpdate(){
        ClienteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        ClienteEntity resp = em.find(ClienteEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class ClientePersistence.
     */
    @Test
    public void testDelete(){
        ClienteEntity entity = data.get(0);
        persistence.delete(entity.getId());
        ClienteEntity deleted = em.find(ClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
