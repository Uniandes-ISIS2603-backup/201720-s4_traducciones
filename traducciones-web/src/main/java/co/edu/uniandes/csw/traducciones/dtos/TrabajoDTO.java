/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.TrabajoEntity;

/**
 *
 * @author ra.forero11
 */
public class TrabajoDTO {
    
    private Long id;
    private String name;
    private boolean terminado;
    
    /**
     * Constructor por defecto
     */
    public TrabajoDTO() {
         //Constructor necesario
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param trabajo: Es la entidad que se va a convertir a DTO
     */
    public TrabajoDTO(TrabajoEntity trabajo) {
        this.id = trabajo.getId();
        this.name = trabajo.getName();
        this.terminado = trabajo.isTerminado();
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public TrabajoEntity toEntity() {
        TrabajoEntity entity = new TrabajoEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setTerminado(this.isTerminado());
        return entity;
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
     * @return the terminado
     */
    public boolean isTerminado() {
        return terminado;
    }

    /**
     * @param terminado the terminado to set
     */
    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }
}
