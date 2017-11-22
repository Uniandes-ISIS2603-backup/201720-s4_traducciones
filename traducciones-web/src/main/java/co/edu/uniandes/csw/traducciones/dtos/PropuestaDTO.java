/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;

/**
 *
 * @author av.perezb
 */
public class PropuestaDTO {

    /**
     * Id de la propuesta
     */
    private Long id;

    /**
     * Nombre de la propuesta
     */
    private String nombre;

    /**
     * Costo de la propuesta
     */
    private double costo;

    /**
     * Estado de la propuesta, puede ser ACEPTADA, RECHAZADA, EN_REVISION
     */
    private String estado;

    /**
     * Constructor por defecto
     */
    public PropuestaDTO() {

    }

    /**
     * @return el id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id el id para modificar
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return el nombre de la propuesta
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre de la propuesta
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return el costo de la propuesta
     */
    public double getCosto() {
        return costo;
    }

    /**
     * @param costo de la propuesta
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }

    /**
     * @return el estado de la propuesta
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado para la propuesta
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param propuesta: Es la entidad que se va a convertir a DTO
     */
    public PropuestaDTO(PropuestaEntity propuesta) {

        this.id = propuesta.getId();
        this.nombre = propuesta.getName();
        this.costo = propuesta.getCosto();
        this.estado = propuesta.getEstado();

    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public PropuestaEntity toEntity() {

        PropuestaEntity entity = new PropuestaEntity();
        entity.setName(this.nombre);
        entity.setEstado(this.estado);
        entity.setCosto(this.costo);
        return entity;
    }

}
