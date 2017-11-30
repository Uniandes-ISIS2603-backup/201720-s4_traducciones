/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.OfertaEntityTest;
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
 * @author av.perezb
 */
@RunWith(Arquillian.class)
public class OfertaPersistenceTest {

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Oferta, el descriptor de la base
     * de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OfertaEntityTest.class.getPackage())
                .addPackage(OfertaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    public OfertaPersistenceTest() {
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

    /**
     * Test of create method, of class OfertaPersistence.
     */
    @Test
    public void testCreate() throws Exception {

        PodamFactory factory = new PodamFactoryImpl();
        OfertaEntityTest newEntity = factory.manufacturePojo(OfertaEntityTest.class);
        OfertaEntityTest result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        OfertaEntityTest entity = em.find(OfertaEntityTest.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of update method, of class OfertaPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        OfertaEntityTest entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        OfertaEntityTest newEntity = factory.manufacturePojo(OfertaEntityTest.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        OfertaEntityTest resp = em.find(OfertaEntityTest.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class OfertaPersistence.
     */
    @Test
    public void testDelete() throws Exception {

        OfertaEntityTest entity = data.get(0);
        persistence.delete(entity.getId());
        OfertaEntityTest deleted = em.find(OfertaEntityTest.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of find method, of class OfertaPersistence.
     */
    @Test
    public void testFind() throws Exception {

        OfertaEntityTest entity = data.get(0);
        OfertaEntityTest newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    /**
     * Test of find method, of class OfertaPersistence.
     */
    @Test
    public void testFindByName () throws Exception {

        OfertaEntityTest entity = data.get(0);
        List<OfertaEntityTest> newEntity = persistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.get(0).getName());
    }

    /**
     * Test of findAll method, of class OfertaPersistence.
     */
    @Test
    public void testFindAll() throws Exception {

        List<OfertaEntityTest> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (OfertaEntityTest ent : list) {
            boolean found = false;
            for (OfertaEntityTest entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Inyección de la dependencia a la clase OfertaPersistence cuyos métodos se
     * van a probar.
     */
    @Inject
    private OfertaPersistence persistence;

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
    private List<OfertaEntityTest> data = new ArrayList<OfertaEntityTest>();

    private void clearData() {
        em.createQuery("delete from OfertaEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            OfertaEntityTest entity = factory.manufacturePojo(OfertaEntityTest.class);

            em.persist(entity);
            data.add(entity);
        }
    }

}
