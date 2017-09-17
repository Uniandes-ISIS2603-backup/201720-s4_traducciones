/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.TrayectoriaDTO;
import co.edu.uniandes.csw.traducciones.ejb.TrayectoriaLogic;
import co.edu.uniandes.csw.traducciones.entities.TrayectoriaEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.mappers.WebApplicationExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
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
 * @author ra.forero11
 */
@Path("trayectorias")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class TrayectoriaResource {
    
     @Inject
    TrayectoriaLogic trayectoriaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
     
     /**
     * POST http://localhost:8080/traducciones-web/api/trayectorias Ejemplo
     * json: { "name":"Diego", "codigo":"123"}
     *
     * @param trayectoria correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
 la base de datos y el tipo del objeto java. 
     * @throws BusinessLogicException
     */
    @POST
    public TrayectoriaDTO createTrayectoria(TrayectoriaDTO trayectoria) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        TrayectoriaEntity trayectoriaEntity = trayectoria.toEntity();
        // Invoca la lógica para crear la trayectoria nueva
        TrayectoriaEntity nuevaTrayectoria = trayectoriaLogic.createTrayectoria(trayectoriaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new TrayectoriaDTO(nuevaTrayectoria);
    }
    
    /**
     * GET para todas las trayectorias
     * http://localhost:8080/traducciones-web/api/trayectorias
     *
     * @return la lista de todas las trayectorias en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<TrayectoriaDTO> getTrayectorias() throws BusinessLogicException {
        return listEntity2DetailDTO(trayectoriaLogic.getTrayectorias());
    }
    
    /**
     * GET para una trayectoria
     * http://localhost:8080/traducciones-web/api/trayectorias/1
     *
     * @param id corresponde al id de la trayectoria buscada.
     * @return La trayectoria encontrada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la trayectoria buscada se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public TrayectoriaDTO getTrayectoria(@PathParam("id") Long id) throws BusinessLogicException {
       if(!trayectoriaLogic.existeTrayectoriaId(id))
       {
           WebApplicationExceptionMapper ex=new WebApplicationExceptionMapper();
           ex.toResponse(new WebApplicationException("No existe la trayectoria con el id:"+id+" especidicado"));
       }
       return new TrayectoriaDTO(trayectoriaLogic.getTrayectoriaId(id));
    }
    
    /**
     * PUT http://localhost:8080/traducciones-web/api/trayectorias/1 Ejemplo
     * json { "id": 1, "name": "cambio de nombre" }
     *
     * @param id corresponde a la trayectoria a actualizar.
     * @param trayectoria corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La trayectoria actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la trayectoria a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public TrayectoriaDTO updateTrayectoria(@PathParam("id") Long id, TrayectoriaDTO trayectoria) throws BusinessLogicException {
        
        if(!trayectoriaLogic.existeTrayectoriaId(id))
       {
           WebApplicationExceptionMapper ex=new WebApplicationExceptionMapper();
           ex.toResponse(new WebApplicationException("No existe la trayectoria con el id:"+id+" especidicado"));
       }
        return new TrayectoriaDTO(trayectoriaLogic.updateTrayectoria(id,trayectoria.toEntity()));
    }

    /**
     * DELETE http://localhost:8080/traducciones-web/api/trayectorias/1
     *
     * @param id corresponde a la trayectoia a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la trayectoria a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTrayectoria(@PathParam("id") Long id) throws BusinessLogicException {
        if(!trayectoriaLogic.existeTrayectoriaId(id))
       {
           WebApplicationExceptionMapper ex=new WebApplicationExceptionMapper();
           ex.toResponse(new WebApplicationException("No existe la trayectoria con el id:"+id+" especidicado"));
       }
       trayectoriaLogic.deleteTrayectoriaId(id);
    }
    
     /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos TrayectoriaEntity a una lista de
 objetos TrayectoriaDTO (json)
     *
     * @param entityList corresponde a la lista de trayectorias de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de trayectoria en forma DTO (json)
     */
    private List<TrayectoriaDTO> listEntity2DetailDTO(List<TrayectoriaEntity> entityList) {
        List<TrayectoriaDTO> list = new ArrayList<>();
        for (TrayectoriaEntity entity : entityList) {
            list.add(new TrayectoriaDTO(entity));
        }
        return list;
    }
}
