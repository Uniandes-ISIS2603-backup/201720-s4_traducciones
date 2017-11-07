/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.OfertaEntity;

/**
 *
 * @author av.perezb
 */
public class OfertaDetailDTO extends OfertaDTO {
 
    private EmpleadoDTO empleado;
    
    public OfertaDetailDTO() {
  }
    
     public OfertaDetailDTO (OfertaEntity entity) {
      
        super(entity);
        if (entity.getEmpleado()!=null)
        {
            this.empleado = new EmpleadoDTO(entity.getEmpleado());
        }
        
        
    }   
    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }
  
     public OfertaEntity toEntity() 
     {
         OfertaEntity o = super.toEntity();
         
         if (empleado != null )
        {
            o.setEmpleado(empleado.toEntity());
        }
        
        return o;
     }
}
