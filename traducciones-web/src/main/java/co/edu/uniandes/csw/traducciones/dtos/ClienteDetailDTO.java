/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.ClienteEntity;
import co.edu.uniandes.csw.traducciones.entities.PagoEntity;
import co.edu.uniandes.csw.traducciones.entities.SolicitudEntity;
import co.edu.uniandes.csw.traducciones.entities.TarjetaDeCreditoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ne.ortega
 */
public class ClienteDetailDTO extends ClienteDTO {
    
    private List<PagoDTO> pagos;
    
    private List<TarjetaDTO> tarjetas;
    
    private List<SolicitudDTO> solicitudes;

    public List<SolicitudDTO> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudDTO> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public List<PagoDTO> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoDTO> pagos) {
        this.pagos = pagos;
    }

    public List<TarjetaDTO> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<TarjetaDTO> tarjetas) {
        this.tarjetas = tarjetas;
    }
    
    /**
     * 
     */
    public ClienteDetailDTO(){
        super();
    }
    
    public ClienteDetailDTO(ClienteEntity entity){
        super(entity);
        if(entity!=null){
            if(entity.getPagos() != null){
                pagos = new ArrayList<>();
                for(PagoEntity pago : entity.getPagos()){
                pagos.add(new PagoDTO(pago));
                }
            }     
            if(entity.getTarjetas() != null){
                tarjetas = new ArrayList<>();
                for(TarjetaDeCreditoEntity tarjeta : entity.getTarjetas()){
                tarjetas.add(new TarjetaDTO(tarjeta));
                }
            }            
            if(entity.getSolicitudes() != null){
                solicitudes = new ArrayList<>();
                for(SolicitudEntity solicitud : entity.getSolicitudes()){
                solicitudes.add(new SolicitudDTO(solicitud));
                }
            }
        }
    }
    
    @Override
    public ClienteEntity toEntity(){
        ClienteEntity entity = super.toEntity();
        if(pagos != null){
            List<PagoEntity> pagosEnt = new ArrayList<>();
            for(PagoDTO dto : pagos){
                pagosEnt.add(dto.toEntity());
            }
            entity.setPagos(pagosEnt);
        }
        if(tarjetas != null){
            List<TarjetaDeCreditoEntity> tarjetasEnt = new ArrayList<>();
            for(TarjetaDTO dto : tarjetas){
                tarjetasEnt.add(dto.toEntity());
            }
            entity.setTarjetas(tarjetasEnt);
        }
        if(solicitudes != null){
            List<SolicitudEntity> solicitudesEnt = new ArrayList<>();
            for(SolicitudDTO dto : solicitudes){
                solicitudesEnt.add(dto.toEntity());
            }
            entity.setSolicitudes(solicitudesEnt);
        }
        return entity;
    }
}
