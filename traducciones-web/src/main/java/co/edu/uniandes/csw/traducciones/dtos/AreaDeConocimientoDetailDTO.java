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
    
    /**
     * Crea un DTO a partir de una entidad
     * @param entity objeto entidad el cual se va a convertir en DTO.
     */
    public AreaDeConocimientoDetailDTO(AreaDeConocimientoEntity entity){
        super(entity);
        if(entity.getEmpleado() != null){
            nombreEmpleado = entity.getEmpleado().getName();
            idEmpleado = entity.getEmpleado().getId();
            empleado = new EmpleadoDTO(entity.getEmpleado());
        }
    }
    
    /**
     * Convierte el DTO actual en una entidad
     * @return entidad con los atributos del DTO actual.
     */
    @Override
    public AreaDeConocimientoEntity toEntity(){
        AreaDeConocimientoEntity e = super.toEntity();
        if(empleado != null){
            e.setEmpleado(empleado.toEntity());
        }
        return e;
    }
    
    /**
     * Devuelve el nombre del empleado
     * @return nombre del empleado
     */ 
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }
    
    /**
     * Asigna el nombre del empleado
     * @param nombreEmpleado nombre que sera asignado.
     */
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }
    
    /**
     * Devuelve el id del empleado
     * @return id del empleado
     */
    public Long getIdEmpleado() {
        return idEmpleado;
    }
    
    /**
     * Asigna el id del empleado
     * @param idEmpleado id que sera asignado
     */
    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}
