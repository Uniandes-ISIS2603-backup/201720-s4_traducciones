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

    /**
     * Empleado dueño de la oferta
     */
    private EmpleadoDTO empleado;

    /**
     * Constructor por defecto
     */
    public OfertaDetailDTO() {
    }

    /**
     * Convertir Entity a DetailDTO (Crea un nuevo DetailDTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param entity: Es la entidad que se va a convertir a DetailDTO
     */
    public OfertaDetailDTO(OfertaEntity entity) {

        super(entity);
        if (entity.getEmpleado() != null) {
            this.empleado = new EmpleadoDTO(entity.getEmpleado());
        }

    }

    /**
     * @return el empleado dueño de la oferta 
     */
    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    /**
     * 
     * @param empleado dueño de la oferta 
     */
    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }

    /**
     * Convertir DetailDTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    @Override
    public OfertaEntity toEntity() {
        OfertaEntity o = super.toEntity();

        if (empleado != null) {
            o.setEmpleado(empleado.toEntity());
        }

        return o;
    }
}
