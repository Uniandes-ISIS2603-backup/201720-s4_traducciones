/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.traducciones.dtos.TarjetaDTO;
import co.edu.uniandes.csw.traducciones.ejb.ClienteLogic;
import co.edu.uniandes.csw.traducciones.dtos.PagoDTO;
import co.edu.uniandes.csw.traducciones.entities.ClienteEntity;
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

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {
    
    @Inject
    private ClienteLogic clienteLogic;
    
    private List<ClienteDetailDTO> clienteEntity2DTO(List<ClienteEntity> lista){
        List<ClienteDetailDTO> dtos = new ArrayList<>();
        for(ClienteEntity cliente : lista){
            dtos.add(new ClienteDetailDTO(cliente));
        }
        return dtos;
    }
    
    @GET
    public List<ClienteDetailDTO> getClientes(){
        return clienteEntity2DTO(clienteLogic.getClientes());
    }
    
    @GET
    @Path("{clienteId: \\d+}")
    public ClienteDetailDTO getCliente(@PathParam("clienteId")Long clienteId)
    {
        return new ClienteDetailDTO(clienteLogic.getCliente(clienteId));
    }
    
    @POST
    public ClienteDetailDTO addCliente(ClienteDetailDTO dto){
        return new ClienteDetailDTO(clienteLogic.createCliente(dto.toEntity()));
    }
    
    @PUT
    @Path("{clienteId: \\d+}")
    public ClienteDetailDTO updateCliente(@PathParam("clienteId")Long clienteId, ClienteDetailDTO dto){
        dto.setId(clienteId);
        ClienteEntity entityNew = dto.toEntity();
        ClienteEntity entityOld = clienteLogic.getCliente(clienteId);
        if(entityOld == null){
            throw new WebApplicationException("El cliente con el id " + clienteId + " no existe", 404);
        }
        if(entityNew.getName().equals("")){
            entityNew.setName(entityOld.getName());
        }
        entityNew.setPagos(entityOld.getPagos());
        entityNew.setTarjetas(entityOld.getTarjetas());
        
        return new ClienteDetailDTO(clienteLogic.updateCliente(entityNew));
    }
    
    @DELETE
    @Path("{clienteId: \\d+}")
    public void deleteCliente(@PathParam("clienteId")Long clienteId){
        clienteLogic.deleteCliente(clienteId);
    }
    
    @Path("{clienteId: \\d+}/pagos")
    public Class<ClientePagosResource> getClientePagosResource(@PathParam("clienteId")Long clienteId){
        ClienteEntity entity = clienteLogic.getCliente(clienteId);
        if(entity == null)
            throw new WebApplicationException("El cliente con el id " + clienteId + " no existe", 404);
        return ClientePagosResource.class;
    }
    
    @Path("{clienteId: \\d+}/tarjetas")
    public Class<ClienteTarjetasResource> getClienteTarjetasResource(@PathParam("clienteId")Long clienteId){
        ClienteEntity entity = clienteLogic.getCliente(clienteId);
        if(entity == null)
            throw new WebApplicationException("El cliente con el id " + clienteId + " no existe", 404);
        return ClienteTarjetasResource.class;
    }
}
