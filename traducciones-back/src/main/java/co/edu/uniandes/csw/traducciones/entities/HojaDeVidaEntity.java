/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ra.forero11
 */
@Entity
public class HojaDeVidaEntity extends BaseEntity implements Serializable{

    private String descripcion;
    private String perfilProfesional;
    private String formacionAcademica;
    
    @PodamExclude
    @ManyToOne
    private EmpleadoEntity empleado; 
    
    @PodamExclude
    @OneToMany(mappedBy = "hojaDeVida", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrayectoriaEntity> trayectorias = new ArrayList<TrayectoriaEntity>();

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the perfilProfesional
     */
    public String getPerfilProfesional() {
        return perfilProfesional;
    }

    /**
     * @param perfilProfesional the perfilProfesional to set
     */
    public void setPerfilProfesional(String perfilProfesional) {
        this.perfilProfesional = perfilProfesional;
    }

    /**
     * @return the formacionAcademica
     */
    public String getFormacionAcademica() {
        return formacionAcademica;
    }

    /**
     * @param formacionAcademica the formacionAcademica to set
     */
    public void setFormacionAcademica(String formacionAcademica) {
        this.formacionAcademica = formacionAcademica;
    }

    /**
     * @return the trayectorias
     */
    public List<TrayectoriaEntity> getTrayectorias() {
        return trayectorias;
    }

    /**
     * @param trayectorias the trayectorias to set
     */
    public void setTrayectorias(List<TrayectoriaEntity> trayectorias) {
        this.trayectorias = trayectorias;
    }

    /**
     * @return the empleado
     */
    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }

    
}
