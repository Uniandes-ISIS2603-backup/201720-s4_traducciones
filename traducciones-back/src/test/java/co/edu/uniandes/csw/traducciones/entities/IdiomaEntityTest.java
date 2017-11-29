/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ra.forero11
 */
public class IdiomaEntityTest {
    
    IdiomaEntity entity = new IdiomaEntity();
    
    public IdiomaEntityTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        entity.setAcronimo("ACR");
        entity.setHojaDeVida(new HojaDeVidaEntity());
        entity.setRegion("REG");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAcronimo method, of class IdiomaEntity.
     */
    @Test
    public void testGetAcronimo() {
        assertNotNull(entity.getAcronimo());
    }

    /**
     * Test of setAcronimo method, of class IdiomaEntity.
     */
    @Test
    public void testSetAcronimo() {
        entity.setAcronimo("REG2");
        assertEquals(entity.getAcronimo(),"REG2");
    }

    /**
     * Test of getRegion method, of class IdiomaEntity.
     */
    @Test
    public void testGetRegion() {
        assertNotNull(entity.getRegion());
    }

    /**
     * Test of setRegion method, of class IdiomaEntity.
     */
    @Test
    public void testSetRegion() {
        entity.setAcronimo("REG2");
        assertEquals(entity.getAcronimo(),"REG2");
    }

    /**
     * Test of getHojaDeVida method, of class IdiomaEntity.
     */
    @Test
    public void testGetHojaDeVida() {
        assertNotNull(entity.getHojaDeVida());
    }

    /**
     * Test of setHojaDeVida method, of class IdiomaEntity.
     */
    @Test
    public void testSetHojaDeVida() {
        HojaDeVidaEntity hoja = new HojaDeVidaEntity();
        hoja.setDescripcion("xyz");
        entity.setHojaDeVida(hoja);
        assertEquals("xyz", entity.getHojaDeVida().getDescripcion());
    }
    
}
