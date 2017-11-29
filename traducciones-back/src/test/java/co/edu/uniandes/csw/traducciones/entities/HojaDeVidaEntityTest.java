/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import java.util.ArrayList;
import java.util.Date;
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
public class HojaDeVidaEntityTest {
    
    HojaDeVidaEntity entity = new HojaDeVidaEntity();
    
    public HojaDeVidaEntityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        entity.setDescripcion("des");
        entity.setFormacionAcademica("for");
        entity.setPerfilProfesional("per");
        entity.setEmpleado(new EmpleadoEntity());
        
        ArrayList<TrayectoriaEntity> trayectorias = new ArrayList();
        TrayectoriaEntity tray= new TrayectoriaEntity();
        tray.setDescripcion("desT");
        long z=System.currentTimeMillis();
        long x=System.currentTimeMillis()+10L;
        tray.setFechaFin(new Date(z));
        tray.setFechaFin(new Date(x));
        tray.setHojaDeVida(entity);
        trayectorias.add(tray);
        entity.setTrayectorias(trayectorias);
        
        ArrayList<IdiomaEntity> idiomas = new ArrayList();
        IdiomaEntity idioma= new IdiomaEntity();
        idioma.setId(1L);
        idiomas.add(idioma);
        entity.setIdiomas(idiomas);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDescripcion method, of class HojaDeVidaEntity.
     */
    @Test
    public void testGetDescripcion() {
        assertEquals("des", entity.getDescripcion());
    }

    /**
     * Test of setDescripcion method, of class HojaDeVidaEntity.
     */
    @Test
    public void testSetDescripcion() {
        entity.setDescripcion("des2");
        assertEquals("des2", entity.getDescripcion());
    }

    /**
     * Test of getPerfilProfesional method, of class HojaDeVidaEntity.
     */
    @Test
    public void testGetPerfilProfesional() {
        assertEquals("per", entity.getPerfilProfesional());
    }

    /**
     * Test of setPerfilProfesional method, of class HojaDeVidaEntity.
     */
    @Test
    public void testSetPerfilProfesional() {
        entity.setPerfilProfesional("per2");
        assertEquals("per2", entity.getPerfilProfesional());
    }

    /**
     * Test of getFormacionAcademica method, of class HojaDeVidaEntity.
     */
    @Test
    public void testGetFormacionAcademica() {
        assertEquals("for", entity.getFormacionAcademica());
    }

    /**
     * Test of setFormacionAcademica method, of class HojaDeVidaEntity.
     */
    @Test
    public void testSetFormacionAcademica() {
        entity.setFormacionAcademica("for2");
        assertEquals("for2", entity.getFormacionAcademica());
    }

    /**
     * Test of getTrayectorias method, of class HojaDeVidaEntity.
     */
    @Test
    public void testGetTrayectorias() {
        assertNotNull(entity.getTrayectorias());
    }

    /**
     * Test of setTrayectorias method, of class HojaDeVidaEntity.
     */
    @Test
    public void testSetTrayectorias() {
        ArrayList<TrayectoriaEntity> trayectorias2 = new ArrayList();
        trayectorias2=(ArrayList)entity.getTrayectorias();
        
        TrayectoriaEntity tray= new TrayectoriaEntity();
        tray.setDescripcion("desT2");
        long z=System.currentTimeMillis();
        long x=System.currentTimeMillis()+10L;
        tray.setFechaFin(new Date(z));
        tray.setFechaFin(new Date(x));
        tray.setHojaDeVida(entity);
        trayectorias2.add(tray);
        entity.setTrayectorias(trayectorias2);
        assertSame(entity.getTrayectorias().size(), 2);
    }

    /**
     * Test of getEmpleado method, of class HojaDeVidaEntity.
     */
    @Test
    public void testGetEmpleado() {
        assertNotNull(entity.getEmpleado());
    }

    /**
     * Test of setEmpleado method, of class HojaDeVidaEntity.
     */
    @Test
    public void testSetEmpleado() {
        EmpleadoEntity emp2 = new EmpleadoEntity();
        emp2.setId(1L);
        entity.setEmpleado(emp2);
        assertEquals(emp2, entity.getEmpleado());
    }

    /**
     * Test of getIdiomas method, of class HojaDeVidaEntity.
     */
    @Test
    public void testGetIdiomas() {
        assertNotNull(entity.getIdiomas());
    }

    /**
     * Test of setIdiomas method, of class HojaDeVidaEntity.
     */
    @Test
    public void testSetIdiomas() {
        ArrayList<IdiomaEntity> idiomas = new ArrayList();
        IdiomaEntity idioma= new IdiomaEntity();
        idioma.setId(2L);
        idiomas.add(idioma);
        idioma.setId(3L);
        idiomas.add(idioma);
        entity.setIdiomas(idiomas);
        assertSame(entity.getIdiomas().size(), 2);
    }
    
}
