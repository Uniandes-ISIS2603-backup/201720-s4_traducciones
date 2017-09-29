/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.AreaDeConocimientoDTO;
import co.edu.uniandes.csw.traducciones.dtos.AreaDeConocimientoDetailDTO;
import co.edu.uniandes.csw.traducciones.ejb.AreaDeConocimientoLogic;
import co.edu.uniandes.csw.traducciones.entities.AreaDeConocimientoEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author jc.gloria
 */
@Path("areasdeconocimiento")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AreaDeConocimientoResource {
    
    @Inject
    private AreaDeConocimientoLogic areaDeConocimientoLogic;
    
    @GET
    public List<AreaDeConocimientoDetailDTO> getAreasDeConocimiento() throws BusinessLogicException {
        return listEntity2DetailDTO(areaDeConocimientoLogic.getAreasDeConocimientos());
    }
    
    @GET
    @Path("{id: \\d+}")
    public AreaDeConocimientoDTO getAreaDeConocimiento(@PathParam("id") Long id) throws BusinessLogicException {
        AreaDeConocimientoEntity entity = areaDeConocimientoLogic.getAreaDeConocimiento(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso area de conocimiento: " + id + " no existe.", 404);
        }
        return new AreaDeConocimientoDetailDTO(entity);
    }
    
   @POST
    public AreaDeConocimientoDetailDTO createAreaDeConocimiento(AreaDeConocimientoDetailDTO areaDeConocimiento) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        AreaDeConocimientoEntity areaDeConocimientoEntity = areaDeConocimiento.toEntity();
        // Invoca la lógica para crear el Area De Conocimiento nuevo
        AreaDeConocimientoEntity nuevaAreaDeConocimiento = areaDeConocimientoLogic.createAreaDeConocimiento(areaDeConocimientoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new AreaDeConocimientoDetailDTO(nuevaAreaDeConocimiento);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public AreaDeConocimientoDetailDTO updateAreaDeConocimiento(@PathParam("id") Long id, AreaDeConocimientoDetailDTO areaDeConocimiento) throws BusinessLogicException {
        areaDeConocimiento.setId(id);
        AreaDeConocimientoEntity entity = areaDeConocimientoLogic.getAreaDeConocimiento(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso AreaDeConocimiento: " + id + " no existe.", 404);
        }
        return new AreaDeConocimientoDetailDTO(areaDeConocimientoLogic.updateAreaDeConocimiento(id, areaDeConocimiento.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEmpleado(@PathParam("id") Long id) throws BusinessLogicException {
        AreaDeConocimientoEntity entity = areaDeConocimientoLogic.getAreaDeConocimiento(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso AreaDeConocimiento: " + id + " no existe.", 404);
        }
        areaDeConocimientoLogic.deleteAreaDeConocimiento(entity);
    }
    
     private List<AreaDeConocimientoDetailDTO> listEntity2DetailDTO(List<AreaDeConocimientoEntity> entityList) {
        List<AreaDeConocimientoDetailDTO> list = new ArrayList<>();
        for (AreaDeConocimientoEntity entity : entityList) {
            list.add(new AreaDeConocimientoDetailDTO(entity));
        }
        return list;
    }  
    
}
