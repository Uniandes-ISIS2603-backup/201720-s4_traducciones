/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.logic;

import co.edu.uniandes.csw.traducciones.ejb.AreaDeConocimientoLogic;
import co.edu.uniandes.csw.traducciones.entities.AreaDeConocimientoEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.AreaDeConocimientoPersistence;
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
public class AreaDeConocimientoLogicTest {
    
    @Inject
    private AreaDeConocimientoLogic logic;

   @Inject
    private AreaDeConocimientoPersistence persistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<AreaDeConocimientoEntity> data = new ArrayList<AreaDeConocimientoEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AreaDeConocimientoEntity.class.getPackage())
                .addPackage(AreaDeConocimientoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
    private void clearData() {
        em.createQuery("delete from AreaDeConocimientoEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AreaDeConocimientoEntity entity = factory.manufacturePojo(AreaDeConocimientoEntity.class);
            
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
    public void createAreaDeConocimientoTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        AreaDeConocimientoEntity newEntity = factory.manufacturePojo(AreaDeConocimientoEntity.class);
        AreaDeConocimientoEntity result = logic.createAreaDeConocimiento(newEntity);
        
        Assert.assertNotNull(result);
        AreaDeConocimientoEntity entity = em.find(AreaDeConocimientoEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    @Test
    public void getAreasDeConocimientosTest() {
        List<AreaDeConocimientoEntity> list = logic.getAreasDeConocimientos();
        Assert.assertEquals(data.size(), list.size());
        for (AreaDeConocimientoEntity ent : list) {
            boolean found = false;
            for (AreaDeConocimientoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getAreaDeConocimientoTest() throws BusinessLogicException {
        AreaDeConocimientoEntity entity = data.get(0);
        AreaDeConocimientoEntity newEntity = logic.getAreaDeConocimiento(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    
    @Test
    public void updateAreaDeConocimientoTest() throws BusinessLogicException {
        AreaDeConocimientoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AreaDeConocimientoEntity newEntity = factory.manufacturePojo(AreaDeConocimientoEntity.class);
        
        newEntity.setId(entity.getId());
        
        logic.updateAreaDeConocimiento(newEntity.getId(), newEntity);
        
        AreaDeConocimientoEntity resp = em.find(AreaDeConocimientoEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
    @Test
    public void deleteAreaDeConocimientoTest() throws BusinessLogicException {
        AreaDeConocimientoEntity entity = data.get(0);
        //el parametro de persistence.delete es un AreaDeConocimientoEntity. Asi que no se puede entity.getID()
        logic.deleteAreaDeConocimiento(entity);
        AreaDeConocimientoEntity deleted = em.find(AreaDeConocimientoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
