/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.TarjetaDTO;
import co.edu.uniandes.csw.traducciones.ejb.ClienteLogic;
import co.edu.uniandes.csw.traducciones.entities.TarjetaDeCreditoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author ne.ortega
 */
public class ClienteTarjetasResource {
    
        
    @Inject
    private ClienteLogic clienteLogic;
    
    
    private List<TarjetaDTO> tarjetasEntity2DTO(List<TarjetaDeCreditoEntity> lista){
        List<TarjetaDTO> dtos = new ArrayList<>();
        for(TarjetaDeCreditoEntity pago : lista){
            dtos.add(new TarjetaDTO(pago));
        }
        return dtos;
    }
    
    @GET
    public List<TarjetaDTO> getPagos(@PathParam("clienteId")Long clienteId){
        return tarjetasEntity2DTO(clienteLogic.listTarjetas(clienteId));
    }
    
    @GET
    @Path("{tarjetaId: \\d+}")
    public TarjetaDTO getPago(@PathParam("clienteId")Long clienteId, @PathParam("tarjetaId")Long tarjetaId){
        return new TarjetaDTO(clienteLogic.getTarjeta(clienteId, tarjetaId));
    }
    
    @POST
    @Path("{tarjetaId: \\d+}")
    public TarjetaDTO addTarjeta(@PathParam("clienteId")Long clienteid, @PathParam("tarjetaId")Long tarjetaId){
        return new TarjetaDTO(clienteLogic.addTarjeta(clienteid, tarjetaId));
    }
    
    @DELETE
    @Path("{tarjetaId: \\d+}")
    public void deleteTarjeta(@PathParam("clienteId")Long clienteId, @PathParam("tarjetaId")Long tarjetaId){
        clienteLogic.removeTarjeta(clienteId, tarjetaId);
    }
    
}
