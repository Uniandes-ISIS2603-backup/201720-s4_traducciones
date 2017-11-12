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
    
     private Long id;
     
     private String nombre;
     
     private double costo;
        
     private String estado;
    
    /**
    * Constructor por defecto
    */
    public PropuestaDTO() {
         
     }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     
     public double getCosto() {
         return costo;
     }
 
     public void setCosto(double costo) {
         this.costo = costo;
     }
 
     public String getEstado() {
        return estado;
    }

     public void setEstado(String estado) {
        this.estado = estado;
     }
      
    
       /**
      * Conviertir Entity a DTO
      * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
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
