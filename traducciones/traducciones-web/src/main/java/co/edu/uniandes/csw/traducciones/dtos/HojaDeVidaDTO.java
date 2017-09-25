/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.HojaDeVidaEntity;



/**
 *
 * @author ra.forero11
 */
public class HojaDeVidaDTO {
    
    private Long id;
    private String name;
    private String descripcion;
    private String perfilProfesional;
    private String formacionAcademica;

    
    /**
     * Constructor por defecto
     */
    public HojaDeVidaDTO(){
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param hojaDeVida: Es la entidad que se va a convertir a DTO
     */
    public HojaDeVidaDTO(HojaDeVidaEntity hojaDeVida) {
        this.id = hojaDeVida.getId();
        this.name = hojaDeVida.getName();
        this.descripcion = hojaDeVida.getDescripcion();
        this.perfilProfesional = hojaDeVida.getPerfilProfesional();
        this.formacionAcademica = hojaDeVida.getFormacionAcademica();
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

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
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public HojaDeVidaEntity toEntity() {
        HojaDeVidaEntity entity = new HojaDeVidaEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setDescripcion(this.descripcion);
        entity.setPerfilProfesional(this.perfilProfesional);
        entity.setFormacionAcademica(this.formacionAcademica);
        return entity;
    }
}
