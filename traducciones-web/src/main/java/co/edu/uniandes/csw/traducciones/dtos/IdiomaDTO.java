/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.IdiomaEntity;

/**
 *
 * @author Dxhl
 */
public class IdiomaDTO {
    
    
    private long id;
    private String nombre;
    private String acronimo;
    private String region;
    
    
     public IdiomaDTO() {
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param trayectoria: Es la entidad que se va a convertir a DTO
     */
    public IdiomaDTO(IdiomaEntity idioma) {
        this.id = idioma.getId();
        this.nombre = idioma.getName();
        this.acronimo = idioma.getAcronimo();
        this.region = idioma.getRegion();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public IdiomaEntity toEntity() {
        IdiomaEntity entity = new IdiomaEntity();
        entity.setId(this.id);
        entity.setName(this.nombre);
        entity.setAcronimo(this.acronimo);
        entity.setRegion(this.region);
        return entity;
    }
}
