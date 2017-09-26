/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.ClienteEntity;
import co.edu.uniandes.csw.traducciones.entities.PagoEntity;
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
            pagos = new ArrayList<>();
            for(PagoEntity pago : entity.getPagos()){
                pagos.add(new PagoDTO(pago));
            }
            tarjetas = new ArrayList<>();
            for(TarjetaDeCreditoEntity tarjeta : entity.getTarjetas()){
                tarjetas.add(new TarjetaDTO(tarjeta));
            }
        }
    }
    
    @Override
    public ClienteEntity toEntity(){
        ClienteEntity entity = super.toEntity();
        List<PagoEntity> pagosEnt = new ArrayList<>();
        if(!pagos.isEmpty()){
            for(PagoDTO dto : pagos){
                pagosEnt.add(dto.toEntity());
            }
            entity.setPagos(pagosEnt);
        }
        List<TarjetaDeCreditoEntity> tarjetasEnt = new ArrayList<>();
        if(!tarjetas.isEmpty()){
            for(TarjetaDTO dto : tarjetas){
                tarjetasEnt.add(dto.toEntity());
            }
            entity.setTarjetas(tarjetasEnt);
        }
        return entity;
    }
}
