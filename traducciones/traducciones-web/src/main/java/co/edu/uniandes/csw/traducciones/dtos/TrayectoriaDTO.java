/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.TrayectoriaEntity;
import java.sql.Date;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ra.forero11
 */
public class TrayectoriaDTO {
    
    private Long id;
    private String name;
    private String descripcion;
    
    @PodamExclude
    private Date fechaInicio;
    
    @PodamExclude
    private Date fechaFin;

    /**
     * Constructor por defecto
     */
    public TrayectoriaDTO() {
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param trayectoria: Es la entidad que se va a convertir a DTO
     */
    public TrayectoriaDTO(TrayectoriaEntity trayectoria) {
        this.id = trayectoria.getId();
        this.name = trayectoria.getName();
        this.descripcion = trayectoria.getDescripcion();
        this.fechaFin = trayectoria.getFechaFin();
        this.fechaInicio = trayectoria.getFechaInicio();
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
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public TrayectoriaEntity toEntity() {
        TrayectoriaEntity entity = new TrayectoriaEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setDescripcion(this.descripcion);
        entity.setFechaFin(this.fechaFin);
        entity.setFechaInicio(this.fechaInicio);
        return entity;
    }
}
