package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;
import co.edu.uniandes.csw.traducciones.enums.Estado;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
   public class PropuestaDetailDTO extends PropuestaDTO {
     
     private double costo;
     @Enumerated(EnumType.STRING)
     private Estado estado;
     
     private OfertaDTO oferta;

        
     /**
      * Constructor por defecto
      */
     public PropuestaDetailDTO() {
         
     }
     
      /**
      * Conviertir Entity a DetailDTO
      * (Crea un nuevo DetailDTO con los valores que recibe en la entidad que viene de argumento.
      * @param propuesta: Es la entidad que se va a convertir a DTO 
      */
     public PropuestaDetailDTO(PropuestaEntity propuesta) {
      
         this.costo = propuesta.getCosto();
         this.estado = propuesta.getEstado();
         this.oferta = new OfertaDTO(propuesta.getOferta());
         
     } 
     
    public OfertaDTO getOferta() {
        return oferta;
    }

    public void setOferta(OfertaDTO oferta) {
        this.oferta = oferta;
    }
     
     public double getCosto() {
         return costo;
     }
 
     public void setCosto(double costo) {
         this.costo = costo;
     }
 
     public Estado getEstado() {
        return estado;
    }

     public void setEstado(Estado estado) {
        this.estado = estado;
     }
      
     /**
      * Convertir DetailDTO a Entity
      * @return Un Entity con los valores del DTO 
      */
     public PropuestaEntity toEntity() {
         
         PropuestaEntity entity = new PropuestaEntity();
         entity.setCosto(this.costo);
         entity.setEstado(this.estado);
         
         return entity;
     }
     
     
 }
