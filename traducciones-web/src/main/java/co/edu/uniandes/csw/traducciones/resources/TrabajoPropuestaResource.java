/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.PropuestaDTO;
import co.edu.uniandes.csw.traducciones.ejb.TrabajoLogic;
import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ra.forero11
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrabajoPropuestaResource {
    @Inject
    private TrabajoLogic trabajoLogic;
    
    
    private List<PropuestaDTO> propuestasEntity2DTO(List<PropuestaEntity> lista){
        List<PropuestaDTO> dtos = new ArrayList<>();
        for(PropuestaEntity propuesta : lista){
            dtos.add(new PropuestaDTO(propuesta));
        }
        return dtos;
    }
    
    @GET
    public List<PropuestaDTO> getPropuesta(@PathParam("trabajoId")Long trabajoId) throws BusinessLogicException{
        return propuestasEntity2DTO(trabajoLogic.getPropuestas(trabajoId));
    }    
   
    
    @POST
    @Path("{propuestaId: \\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public PropuestaDTO addPropuesta(@PathParam("trabajoId")Long trabajoid,PropuestaDTO propuesta,@PathParam("propuestaId")Long propuestaid) throws BusinessLogicException{
        System.out.println("eEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEsnull="+propuesta.getNombre()==null);
                
        return new PropuestaDTO(trabajoLogic.addPropuesta(propuesta.toEntity(),trabajoid,propuestaid));
    }
    
    @DELETE
    public void deletePropuesta(@PathParam("trabajoId")Long trabajoId) throws BusinessLogicException{
        if(getPropuesta(trabajoId) == null){
            throw new WebApplicationException("La propuesta no existe o no está asociado"
                    + " al trabajo con el id " + trabajoId, 404);
        }
        trabajoLogic.removePropuesta(trabajoId);
    }
    
}
