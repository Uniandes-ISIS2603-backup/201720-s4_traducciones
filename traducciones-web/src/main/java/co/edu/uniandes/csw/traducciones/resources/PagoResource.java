/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.TarjetaDTO;
import co.edu.uniandes.csw.traducciones.ejb.PagoLogic;
import co.edu.uniandes.csw.traducciones.dtos.PagoDTO;
import co.edu.uniandes.csw.traducciones.entities.PagoEntity;
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
@Path("/pagos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagoResource {
    
    @Inject
    private PagoLogic pagoLogic;
    
    private List<PagoDTO> pagosEntity2DTO(List<PagoEntity> lista){
        List<PagoDTO> dtos = new ArrayList<>();
        for(PagoEntity pago : lista){
            dtos.add(new PagoDTO(pago));
        }
        return dtos;
    }
    
    @GET
    public List<PagoDTO> getPagos(){
        return pagosEntity2DTO(pagoLogic.getPagos());
    }
    
    @GET
    @Path("{pagoId: \\d+}")
    public PagoDTO getPago(@PathParam("pagoId")Long pagoId){
        return new PagoDTO(pagoLogic.getPago(pagoId));
    }
    
    @POST
    public PagoDTO addPago(PagoDTO dto) throws BusinessLogicException{
        if(dto != null){
            if(dto.getIdSolicitud() != null){
                boolean noCumpleRegla = false;
                for(PagoDTO pago : getPagos()){
                    if(pago.getIdSolicitud().equals(dto.getIdSolicitud())){
                        noCumpleRegla = true;
                        break;
                    }
                }
                if(noCumpleRegla){
                    throw new BusinessLogicException("No se puede agregar el pago: Hay otro pago asociado a la misma solicitud");
                }
            }
            return new PagoDTO(pagoLogic.createPago(dto.toEntity()));
        }
        return null;
    }
    
    @PUT
    @Path("{pagoId: \\d+}")
    public PagoDTO updatePago(@PathParam("pagoId")Long pagoId, PagoDTO dto) throws BusinessLogicException{
        dto.setId(pagoId);
        PagoEntity pago = pagoLogic.getPago(pagoId);
        PagoEntity pagoN = dto.toEntity();
        if(pago==null){
            throw new WebApplicationException("El pago con el id " + pagoId + " no existe", 404);
        }
        
        pagoN.setIdSolicitud(pago.getIdSolicitud());
        
        if(pagoN.getIdEmpleado() == 0){
            pagoN.setIdEmpleado(pago.getIdEmpleado());
        }
        
        if(pago.getTarjeta() != null){
            pagoN.setTarjeta(pago.getTarjeta());
        }
        
        return new PagoDTO(pagoLogic.updatePago(pagoId, pagoN));
    }
    
    @DELETE
    @Path("{pagoId: \\d+}")
    public void deletePago(@PathParam("pagoId")Long pagoId){
        if(getPago(pagoId) == null){
            throw new WebApplicationException("El pago con el id " + pagoId + " no existe", 404);
        }
        pagoLogic.deletePago(pagoId);
    }
    
    @GET
    @Path("{pagoId: \\d+}/tarjeta")
    public TarjetaDTO getTarjeta(@PathParam("pagoId")Long pagoId){
        if(getPago(pagoId) == null){
            throw new WebApplicationException("El pago con el id " + pagoId + " no existe", 404);
        }
        return new TarjetaDTO(pagoLogic.getTarjeta(pagoId));
    }
    
    @POST
    @Path("{pagoId: \\d+}/tarjeta")
    public TarjetaDTO addTarjeta(@PathParam("pagoId")Long pagoId, TarjetaDTO tarjeta){
        if(getPago(pagoId) == null){
            throw new WebApplicationException("El pago con el id " + pagoId + " no existe", 404);
        }
        if(tarjeta == null){
            throw new WebApplicationException("La tarjeta a agregar no puede ser nula", 412);
        }
        return new TarjetaDTO(pagoLogic.addTarjeta(pagoId, tarjeta.toEntity()));
    }
}
