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
public class TrayectoriaEntityTest {
    
    TrayectoriaEntity tray = new TrayectoriaEntity();
    Date date1;
    Date date2;
    public TrayectoriaEntityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        HojaDeVidaEntity entity = new HojaDeVidaEntity();
        entity.setDescripcion("des");
        entity.setFormacionAcademica("for");
        entity.setPerfilProfesional("per");
        ArrayList<TrayectoriaEntity> trayectorias = new ArrayList();
        
        tray.setDescripcion("desT");
        long z=System.currentTimeMillis();
        long x=System.currentTimeMillis()+10L;
        date1=new Date(z);
        date2=new Date(x);
        tray.setFechaInicio(date1);
        tray.setFechaFin(date2);
        tray.setHojaDeVida(entity);
        trayectorias.add(tray);
        entity.setTrayectorias(trayectorias);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDescripcion method, of class TrayectoriaEntity.
     */
    @Test
    public void testGetDescripcion() {
        assertEquals("desT", tray.getDescripcion());
    }

    /**
     * Test of setDescripcion method, of class TrayectoriaEntity.
     */
    @Test
    public void testSetDescripcion() {
        tray.setDescripcion("des2");
        assertEquals("des2", tray.getDescripcion());
    }

    /**
     * Test of getFechaInicio method, of class TrayectoriaEntity.
     */
    @Test
    public void testGetFechaInicio() {
        assertEquals(date1, tray.getFechaInicio());
    }

    /**
     * Test of setFechaInicio method, of class TrayectoriaEntity.
     */
    @Test
    public void testSetFechaInicio() {
        tray.setFechaInicio(new Date(System.currentTimeMillis()+1000));
        assertNotEquals(date1, tray.getFechaInicio());
    }

    /**
     * Test of getFechaFin method, of class TrayectoriaEntity.
     */
    @Test
    public void testGetFechaFin() {
        assertEquals(date2, tray.getFechaFin());
    }

    /**
     * Test of setFechaFin method, of class TrayectoriaEntity.
     */
    @Test
    public void testSetFechaFin() {
        tray.setFechaFin(new Date(System.currentTimeMillis()+1000));
        assertNotEquals(date2, tray.getFechaFin());
    }

    /**
     * Test of getHojaDeVida method, of class TrayectoriaEntity.
     */
    @Test
    public void testGetHojaDeVida() {
        assertNotNull(tray.getHojaDeVida());
    }

    /**
     * Test of setHojaDeVida method, of class TrayectoriaEntity.
     */
    @Test
    public void testSetHojaDeVida() {
        HojaDeVidaEntity hoja = new HojaDeVidaEntity();
        hoja.setId(2L);
        tray.setHojaDeVida(hoja);
        assertSame(tray.getHojaDeVida().getId(),2L);
    }
    
}
