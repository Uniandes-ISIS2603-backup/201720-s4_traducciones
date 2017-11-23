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
    
    /**
     * Devuelve todas las areas de conocimiento en el sistema
     * @return una lista con objetos de tipo DTO.
     */
    @GET
    public List<AreaDeConocimientoDetailDTO> getAreasDeConocimiento() throws BusinessLogicException {
        return listEntity2DetailDTO(areaDeConocimientoLogic.getAreasDeConocimientos());
    }
    
    /**
     * Devuelve una area de conocimiento a partir de un id
     * @param id id de la area que se quiere buscar
     * @return DTO que concuerda con el id.
     */
    @GET
    @Path("{id: \\d+}")
    public AreaDeConocimientoDetailDTO getAreaDeConocimiento(@PathParam("id") Long id) throws BusinessLogicException {
        return new AreaDeConocimientoDetailDTO(areaDeConocimientoLogic.getAreaDeConocimiento(id));
    }
    
    /**
     * Se agrega una area de conocimiento a partir de un DTO.
     * @param areaDeConocimiento objeto DTO que se agregara a la base de datos
     * @return el mismo DTO pasado por parametro
     */
   @POST
    public AreaDeConocimientoDetailDTO createAreaDeConocimiento(AreaDeConocimientoDetailDTO areaDeConocimiento) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        AreaDeConocimientoEntity areaDeConocimientoEntity = areaDeConocimiento.toEntity();
        // Invoca la lógica para crear el Area De Conocimiento nuevo
        AreaDeConocimientoEntity nuevaAreaDeConocimiento = areaDeConocimientoLogic.createAreaDeConocimiento(areaDeConocimientoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new AreaDeConocimientoDetailDTO(nuevaAreaDeConocimiento);
    }
    
    /**
     * Actualiza una area de conocimiento
     * @param id id que se quiere actualizar
     * @param areaDeConocimiento DTO por el que se va a reemplazar.
     * @return DTO actualizado
     * @throws BusinessLogicException si no existe un area con el id deseado
     */
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
    
    /**
     * Borra un Area de Conocimiento
     * @param id id del area que se quiere eliminar
     * @throws BusinessLogicException si no existe un area con el id deseado.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteAreaDeConocimiento(@PathParam("id") Long id) throws BusinessLogicException {
        AreaDeConocimientoEntity entity = areaDeConocimientoLogic.getAreaDeConocimiento(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso AreaDeConocimiento: " + id + " no existe.", 404);
        }
        areaDeConocimientoLogic.deleteAreaDeConocimiento(entity);
    }
    
    /**
     * Convierte una lista de areas de conocimiento en forma de entidades a DTO.
     * @param entityList lista en forma de entidad que se queire convertir
     * @return la lsita en forma de dto
     */
     private List<AreaDeConocimientoDetailDTO> listEntity2DetailDTO(List<AreaDeConocimientoEntity> entityList) {
        List<AreaDeConocimientoDetailDTO> list = new ArrayList<>();
        for (AreaDeConocimientoEntity entity : entityList) {
            list.add(new AreaDeConocimientoDetailDTO(entity));
        }
        return list;
    }  
    
}
