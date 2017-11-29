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
public class CalificacionEntityTest {
    
    
    private CalificacionEntity entity;
    
    public CalificacionEntityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        entity= new CalificacionEntity();
        entity.setCalificacion(0);
        entity.setComentario("comentario");
        entity.setCalificacion(0);
        entity.setTrabajo(new TrabajoEntity());
        
    }
    
    @After
    public void tearDown() {
    }

   

    /**
     * Test of getComentario method, of class CalificacionEntity.
     */
    @Test
    public void testGetComentario() {
        assertEquals("comentario", entity.getComentario());
        assertNotNull(entity.getComentario());
    }

    /**
     * Test of setComentario method, of class CalificacionEntity.
     */
    @Test
    public void testSetComentario() {
        entity.setComentario("com");
        assertEquals("com", entity.getComentario());
        assertNotNull(entity.getComentario());
    }

    /**
     * Test of getTrabajo method, of class CalificacionEntity.
     */
    @Test
    public void testGetTrabajo() {
        assertNotNull(entity.getTrabajo());
    }

    /**
     * Test of setTrabajo method, of class CalificacionEntity.
     */
    @Test
    public void testSetTrabajo() {
        assertNotNull(entity.getTrabajo());
    }

    /**
     * Test of getCalificacion method, of class CalificacionEntity.
     */
    @Test
    public void testGetCalificacion() {
        assertSame(0, entity.getCalificacion());
        assertNotNull(entity.getCalificacion());
    }

    /**
     * Test of setCalificacion method, of class CalificacionEntity.
     */
    @Test
    public void testSetCalificacion() {
        entity.setCalificacion(2);
        
        assertSame(2, entity.getCalificacion());
        assertNotNull(entity.getCalificacion());
    }
    
}
