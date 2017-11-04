package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
   public class PropuestaDetailDTO extends PropuestaDTO {
     
     
     private OfertaDTO oferta;

        
     /**
      * Constructor por defecto
      */
     public PropuestaDetailDTO() {
     }
     
      /**
      * Conviertir Entity a DetailDTO
      * (Crea un nuevo DetailDTO con los valores que recibe en la entidad que viene de argumento.
     * @param entity Es la entidad que se va a convertir a DTO 
      */
     public PropuestaDetailDTO(PropuestaEntity entity) {
         super(entity);
         this.oferta = new OfertaDTO(entity.getOferta());
         
     } 
     
    public OfertaDTO getOferta() {
        return oferta;
    }

    public void setOferta(OfertaDTO oferta) {
        this.oferta = oferta;
    }
    
     /**
      * Convertir DetailDTO a Entity
      * @return Un Entity con los valores del DTO 
      */
     public PropuestaEntity toEntity() {
         
         PropuestaEntity entity = super.toEntity();
         entity.setOferta(this.getOferta().toEntity());
         
         return entity;
     }
     
     
 }
