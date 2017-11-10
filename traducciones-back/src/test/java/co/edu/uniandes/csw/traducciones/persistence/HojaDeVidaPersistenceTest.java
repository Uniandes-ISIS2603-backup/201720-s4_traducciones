/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.persistence;

import co.edu.uniandes.csw.traducciones.entities.HojaDeVidaEntity;
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
 * @author ra.forero11
 */
@RunWith(Arquillian.class)
public class HojaDeVidaPersistenceTest {
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de HojaDeVida, el descriptor de la base de
     * datos y el archivo beans.xml para resolver la inyecciÃ³n de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HojaDeVidaEntity.class.getPackage())
                .addPackage(HojaDeVidaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * InyecciÃ³n de la dependencia a la clase HojaDeVidaPersistence cuyos
     * mÃ©todos se van a probar.
     */
    @Inject
    private HojaDeVidaPersistence persistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los mÃ©todos que se estÃ¡n probando.
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
    private List<HojaDeVidaEntity> data = new ArrayList<HojaDeVidaEntity>();

    public HojaDeVidaPersistenceTest() {
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
        em.createQuery("delete from HojaDeVidaEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            HojaDeVidaEntity entity = factory.manufacturePojo(HojaDeVidaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    

     /**
     * Test of create method, of class HojaDeVidaPersistence.
     */
    @Test
    public void createHojaDeVidaEntityTest() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        HojaDeVidaEntity newEntity = factory.manufacturePojo(HojaDeVidaEntity.class);
        HojaDeVidaEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        HojaDeVidaEntity entity = em.find(HojaDeVidaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    @Test
    public void getHojasDeVidaTest() {
        List<HojaDeVidaEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (HojaDeVidaEntity ent : list) {
            boolean found = false;
            for (HojaDeVidaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getHojaDeVidaTest() {
        HojaDeVidaEntity entity = data.get(0);
        HojaDeVidaEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    
   
    
    @Test
    public void updateHojaDeVidaTest() {
        HojaDeVidaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        HojaDeVidaEntity newEntity = factory.manufacturePojo(HojaDeVidaEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        HojaDeVidaEntity resp = em.find(HojaDeVidaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
  
    @Test
    public void deleteHojaDeVidaTest() {
        HojaDeVidaEntity entity = data.get(0);
        persistence.delete(entity.getId());
        HojaDeVidaEntity deleted = em.find(HojaDeVidaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
}
