/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.TrayectoriaEntity;
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
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author ra.forero11
 */
@RunWith(Arquillian.class)
public class TrayectoriaPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TrayectoriaEntity.class.getPackage())
                .addPackage(TrayectoriaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private TrayectoriaPersistence persistence;

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
    private List<TrayectoriaEntity> data = new ArrayList<TrayectoriaEntity>();
   
    
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
        em.createQuery("delete from TrayectoriaEntity").executeUpdate();
    }


    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TrayectoriaEntity entity = factory.manufacturePojo(TrayectoriaEntity.class);

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
        TrayectoriaEntity newEntity = factory.manufacturePojo(TrayectoriaEntity.class);
        TrayectoriaEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        TrayectoriaEntity entity = em.find(TrayectoriaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of findAll method, of class ClientePersistence.
     */
    @Test
    public void testFindAll(){
        List<TrayectoriaEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TrayectoriaEntity ent : list) {
            boolean found = false;
            for (TrayectoriaEntity entity : data) {
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
        TrayectoriaEntity entity = data.get(0);
        TrayectoriaEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of update method, of class ClientePersistence.
     */
    @Test
    public void testUpdate(){
        TrayectoriaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TrayectoriaEntity newEntity = factory.manufacturePojo(TrayectoriaEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        TrayectoriaEntity resp = em.find(TrayectoriaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class ClientePersistence.
     */
    @Test
    public void testDelete(){
        TrayectoriaEntity entity = data.get(0);
        persistence.delete(entity.getId());
        TrayectoriaEntity deleted = em.find(TrayectoriaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
