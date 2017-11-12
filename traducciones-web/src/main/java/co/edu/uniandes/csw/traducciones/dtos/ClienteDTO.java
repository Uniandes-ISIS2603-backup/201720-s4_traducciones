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
    
    private Long id;
    
    private String name;
    
    private String correo;
    
    private String contraseña;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    

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
        // Constructor vacío.
    }
    
    public ClienteDTO(ClienteEntity entity){
        this.name = entity.getName();
        this.correo = entity.getCorreo();
        this.contraseña = entity.getContraseña();
        this.id = entity.getId();
    }
    
    public ClienteEntity toEntity(){
        ClienteEntity entity = new ClienteEntity();
        entity.setName(this.name);
        entity.setCorreo(this.correo);
        entity.setContraseña(this.contraseña);
        entity.setId(this.id);
        return entity;
    }
}
