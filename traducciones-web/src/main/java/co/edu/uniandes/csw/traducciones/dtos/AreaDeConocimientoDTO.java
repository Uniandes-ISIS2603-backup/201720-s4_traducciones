/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.AreaDeConocimientoEntity;

/**
 *
 * @author jc.gloria
 */
public class AreaDeConocimientoDTO {
    
    private Long id;
    private String name;
    private String descripcion;
    
    public AreaDeConocimientoDTO(){
    }
    
    public AreaDeConocimientoDTO(AreaDeConocimientoEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.descripcion = entity.getDescripcion();
    }
    
    public AreaDeConocimientoEntity toEntity(){
        AreaDeConocimientoEntity entity = new AreaDeConocimientoEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setDescripcion(this.descripcion);
        return entity;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        
    }
}
