/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author ra.forero11
 */
@Entity
public class HojaDeVidaEntity extends BaseEntity implements Serializable{

    private String descripcion;
    private String perfilProfesional;
    private String formacionAcademica;
    
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

    
}
