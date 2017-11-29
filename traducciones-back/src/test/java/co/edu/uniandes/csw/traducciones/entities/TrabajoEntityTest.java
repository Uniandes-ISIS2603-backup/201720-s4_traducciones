/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import java.util.ArrayList;
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
public class TrabajoEntityTest {
    
    TrabajoEntity entity= new TrabajoEntity();
    
    public TrabajoEntityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        entity.setName("name");
        entity.setTerminado(true);
        ArrayList array = new ArrayList();
        array.add(new CalificacionEntity());
        entity.setCalificaciones(array);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isTerminado method, of class TrabajoEntity.
     */
    @Test
    public void testIsTerminado() {
        assertTrue(entity.isTerminado());
    }

    /**
     * Test of setTerminado method, of class TrabajoEntity.
     */
    @Test
    public void testSetTerminado() {
        entity.setTerminado(false);
        assertFalse(entity.isTerminado());
    }

    /**
     * Test of getCalificaciones method, of class TrabajoEntity.
     */
    @Test
    public void testGetCalificaciones() {
        assertNotNull(entity.getCalificaciones());
    }

    /**
     * Test of setCalificaciones method, of class TrabajoEntity.
     */
    @Test
    public void testSetCalificaciones() {
        assertNotNull(entity.getCalificaciones());
    }
    
}
