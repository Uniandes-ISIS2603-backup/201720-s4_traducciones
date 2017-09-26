/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.PagoEntity;

/**
 *
 * @author ne.ortega
 */
public class PagoDTO {
    
    private Long id, idEmpleado, idSolicitud;
    private TarjetaDTO tarjeta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public TarjetaDTO getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaDTO tarjeta) {
        this.tarjeta = tarjeta;
    }
    
    public PagoDTO(){
        
    }
    
    public PagoDTO(PagoEntity entity){
        this.id = entity.getId();
        this.idEmpleado = entity.getIdEmpleado();
        this.idSolicitud = entity.getIdSolicitud();
        this.tarjeta = new TarjetaDTO(entity.getTarjeta());
    }
    
    public PagoEntity toEntity(){
        PagoEntity entity = new PagoEntity();
        entity.setId(this.id);
        entity.setIdEmpleado(this.idEmpleado);
        entity.setIdSolicitud(this.idSolicitud);
        entity.setTarjeta(this.tarjeta.toEntity());
        return entity;
    }
}
