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
 
    private Integer cantidadInicial;
    private Integer cantidadActual;
    private EmpleadoDTO empleado;
    
    public OfertaDetailDTO() {
  }
    
     public OfertaDetailDTO (OfertaEntity entity) {
        super(entity);
        if (entity.getCantidadActual()!= null && entity.getCantidadInicial()!= null && entity.getEmpleado()!=null)
        {
            empleado = new EmpleadoDTO(entity.getEmpleado());
            cantidadInicial = entity.getCantidadInicial();
            cantidadActual = entity.getCantidadActual();
        }
        
        
    }
      /**
      * @return la cantidad
      */
     public Integer getCantidadInicial() {
         return cantidadInicial;
     }
 
     /**
      * @param cantidad la cantidad para modificar
      */
     public void setCantidadInicial(Integer cantidad) {
         this.cantidadInicial = cantidad;
     }
    
     /**
      * @return la cantidad
      */
     public Integer getCantidadActual() {
         return cantidadActual;
     }
     
         public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }
     
     /**
      * @param cantidad la cantidad para modificar
      */
     public void setCantidadActual(Integer cantidad) {
         this.cantidadActual = cantidad;
  }
     
     public OfertaEntity toEntity() 
     {
         OfertaEntity o = super.toEntity();
         
         if (empleado != null && cantidadInicial != null && cantidadActual!=null)
        {
            o.setCantidadActual(cantidadActual);
            o.setCantidadInicial(cantidadInicial);
            o.setEmpleado(empleado.toEntity());
        }
        
        return o;
     }
}
