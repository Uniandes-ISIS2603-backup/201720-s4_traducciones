/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.ClienteEntity;

/**
 *
 * @author ne.ortega
 */
public class ClienteDTO {
    
    private String name;
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public ClienteDTO(){
        
    }
    
    public ClienteDTO(ClienteEntity entity){
        this.name = entity.getName();
        this.id = entity.getId();
    }
    
    public ClienteEntity toEntity(){
        ClienteEntity entity = new ClienteEntity();
        entity.setName(this.name);
        entity.setId(this.id);
        return entity;
    }
}
