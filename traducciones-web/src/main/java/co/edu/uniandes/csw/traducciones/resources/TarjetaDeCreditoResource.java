/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.TarjetaDTO;
import co.edu.uniandes.csw.traducciones.ejb.TarjetaDeCreditoLogic;
import co.edu.uniandes.csw.traducciones.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ne.ortega
 */
@Path("/tarjetas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarjetaDeCreditoResource {
    
    @Inject
    private TarjetaDeCreditoLogic tarjetaLogic;
    
    private List<TarjetaDTO> tarjetasEntity2DTO(List<TarjetaDeCreditoEntity> list){
        List<TarjetaDTO> tarjetasDtos = new ArrayList<>();
        for(TarjetaDeCreditoEntity entity : list){
            tarjetasDtos.add(new TarjetaDTO(entity));
        }
        return tarjetasDtos;
    }
    
    
    @GET
    public List<TarjetaDTO> getTarjetas(){
        return tarjetasEntity2DTO(tarjetaLogic.getTarjetas());
    }
    
    @GET
    @Path("{tarjetaId: \\d+}")
    public TarjetaDTO getTarjeta(@PathParam("tarjetaId")Long tarjetaId){
        return new TarjetaDTO(tarjetaLogic.getTarjeta(tarjetaId));
    }
    
    @POST
    public TarjetaDTO createTarjeta(TarjetaDTO dto) throws BusinessLogicException{
        TarjetaDeCreditoEntity entity = dto.toEntity();
        tarjetaLogic.createTarjeta(entity);
        return dto;
    }
    
    @PUT
    @Path("{tarjetaId: \\d+}")
    public TarjetaDTO updateTarjeta(@PathParam("tarjetaId")Long tarjetaId, TarjetaDTO dto) throws BusinessLogicException{
        dto.setId(tarjetaId);
        TarjetaDeCreditoEntity entity = tarjetaLogic.getTarjeta(tarjetaId);
        if(entity == null)
            throw new WebApplicationException("El recurso /tarjetas/" + tarjetaId + " no existe", 404);
        
        return new TarjetaDTO(tarjetaLogic.updateTarjeta(tarjetaId, entity));
    }
    
    @DELETE
    @Path("{tarjetaId: \\d+}")
    public void deleteTarjeta(@PathParam("tarjetaId")Long tarjetaId){
        tarjetaLogic.deleteTarjeta(tarjetaId);
    }
}
