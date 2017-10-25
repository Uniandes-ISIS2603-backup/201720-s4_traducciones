/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.SolicitudDTO;
import co.edu.uniandes.csw.traducciones.ejb.ClienteLogic;
import co.edu.uniandes.csw.traducciones.entities.SolicitudEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
public class ClienteSolicitudesResource {
   @Inject
    private ClienteLogic clienteLogic;
    
    
    private List<SolicitudDTO> solicitudesEntity2DTO(List<SolicitudEntity> lista){
        List<SolicitudDTO> dtos = new ArrayList<>();
        for(SolicitudEntity solicitud : lista){
            dtos.add(new SolicitudDTO(solicitud));
        }
        return dtos;
    }
    
    @GET
    public List<SolicitudDTO> getSolicitud(@PathParam("clienteId")Long clienteId){
        return solicitudesEntity2DTO(clienteLogic.listSolicitudes(clienteId));
    }
    
    @GET
    @Path("{solicitudId: \\d+}")
    public SolicitudDTO getSolicitud(@PathParam("clienteId")Long clienteId, @PathParam("solicitudId")Long solicitudId){
        return new SolicitudDTO(clienteLogic.getSolicitud(clienteId, solicitudId));
    }
    
    @POST
    @Path("{solicitudId: \\d+}")
    public SolicitudDTO addSolicitud(@PathParam("clienteId")Long clienteid, @PathParam("solicitudId")Long solicitudId){
        return new SolicitudDTO(clienteLogic.addSolicitud(clienteid, solicitudId));
    }
    
    @DELETE
    @Path("{solicitudId: \\d+}")
    public void deleteSolicitud(@PathParam("clienteId")Long clienteId, @PathParam("solicitudId")Long solicitudId){
        if(getSolicitud(clienteId, solicitudId) == null){
            throw new WebApplicationException("La solicitud con el id " + solicitudId + " no existe o no está asociado"
                    + " al cliente con el id " + clienteId, 404);
        }
        clienteLogic.removePago(clienteId, solicitudId);
    }
     
}
