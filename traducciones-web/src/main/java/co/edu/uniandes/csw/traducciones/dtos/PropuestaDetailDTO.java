package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class PropuestaDetailDTO extends PropuestaDTO {

    /**
     * Oferta de la propuesta, puede o no ser null
     */
    private OfertaDTO oferta;

    /**
     * Constructor por defecto
     */
    public PropuestaDetailDTO() {
    }
    
     /**
     * Empleado dueño de la propuesta
     */
    private EmpleadoDTO empleado;


    /**
     * Conviertir Entity a DetailDTO (Crea un nuevo DetailDTO con los valores
     * que recibe en la entidad que viene de argumento.
     *
     * @param entity Es la entidad que se va a convertir a DTO
     */
    public PropuestaDetailDTO(PropuestaEntity entity) {

        super(entity);
        if (entity.getOferta() != null) {
            this.oferta = new OfertaDTO(entity.getOferta());
        }
        if (entity.getEmpleado() != null) {
            this.empleado = new EmpleadoDTO(entity.getEmpleado());
        }
    }

    /**
     *
     * @return la oferta agregada a la propuesta
     */
    public OfertaDTO getOferta() {
        return oferta;
    }

    /**
     *
     * @param oferta que se desea agregar a la propuesta
     */
    public void setOferta(OfertaDTO oferta) {
        
        this.oferta = oferta;
    }
    
     /**
     * @return el empleado dueño de la propuesta 
     */
    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    /**
     * 
     * @param empleado dueño de la propuesta 
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
    public PropuestaEntity toEntity() {

        PropuestaEntity entity = super.toEntity();
        if(oferta != null)
        {
        entity.setOferta(this.getOferta().toEntity());
        }
        if (empleado != null) {
            entity.setEmpleado(empleado.toEntity());
        }
        return entity;
    }

}
