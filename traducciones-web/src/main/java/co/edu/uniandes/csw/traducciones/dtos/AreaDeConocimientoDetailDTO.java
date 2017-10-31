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
public class AreaDeConocimientoDetailDTO extends AreaDeConocimientoDTO{
    
   private String nombreEmpleado;
   private Long idEmpleado;
   private EmpleadoDTO empleado;
    
    public AreaDeConocimientoDetailDTO(){
        //constructor default
    }

    public AreaDeConocimientoDetailDTO(AreaDeConocimientoEntity entity){
        super(entity);
       if(entity.getEmpleado() != null){
        nombreEmpleado = entity.getEmpleado().getName();
        idEmpleado = entity.getEmpleado().getId();
        empleado = new EmpleadoDTO(entity.getEmpleado());
        }
    }
    
    @Override
    public AreaDeConocimientoEntity toEntity(){
        AreaDeConocimientoEntity e = super.toEntity();
        if(empleado != null){
        e.setEmpleado(empleado.toEntity());
        }
        return e;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    
    
}
