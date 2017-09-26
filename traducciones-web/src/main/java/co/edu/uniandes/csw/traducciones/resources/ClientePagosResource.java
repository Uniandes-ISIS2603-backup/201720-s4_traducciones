/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.PagoDTO;
import co.edu.uniandes.csw.traducciones.ejb.ClienteLogic;
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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientePagosResource {
    
    @Inject
    private ClienteLogic clienteLogic;
    
    
    private List<PagoDTO> pagosEntity2DTO(List<PagoEntity> lista){
        List<PagoDTO> dtos = new ArrayList<>();
        for(PagoEntity pago : lista){
            dtos.add(new PagoDTO(pago));
        }
        return dtos;
    }
    
    @GET
    public List<PagoDTO> getPagos(@PathParam("clienteId")Long clienteId){
        return pagosEntity2DTO(clienteLogic.listPagos(clienteId));
    }
    
    @GET
    @Path("{pagoId: \\d+}")
    public PagoDTO getPago(@PathParam("clienteId")Long clienteId, @PathParam("pagoId")Long pagoId){
        return new PagoDTO(clienteLogic.getPago(clienteId, pagoId));
    }
    
    @POST
    @Path("{pagoId: \\d+}")
    public PagoDTO addPago(@PathParam("clienteId")Long clienteid, @PathParam("pagoId")Long pagoId){
        return new PagoDTO(clienteLogic.addPago(clienteid, pagoId));
    }
    
    @DELETE
    @Path("{pagoId: \\d+}")
    public void deletePago(@PathParam("clienteId")Long clienteId, @PathParam("pagoId")Long pagoId){
        clienteLogic.removePago(clienteId, pagoId);
    }
    
}
