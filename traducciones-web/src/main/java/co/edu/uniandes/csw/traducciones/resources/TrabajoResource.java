/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;


import co.edu.uniandes.csw.traducciones.dtos.TrabajoDTO;
import co.edu.uniandes.csw.traducciones.ejb.TrabajoLogic;
import co.edu.uniandes.csw.traducciones.entities.TrabajoEntity;
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
 * @author ra.forero11
 */
@Path("trabajos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TrabajoResource {

    private final String TRABAJOS="El recurso /trabajoes/";
    
    private final String NOEXISTE=" no existe";
            
    @Inject
    TrabajoLogic trabajoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * POST http://localhost:8080/traducciones-web/api/trabajos Ejemplo
     * json: { "name":"Diego", "codigo":"123"}
     *
     * @param trabajo correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java.
     * @throws BusinessLogicException
     */
    @POST
    public TrabajoDTO createTrabajo(TrabajoDTO trabajo) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        TrabajoEntity trabajoEntity = trabajo.toEntity();
        // Invoca la lógica para crear la trabajo nueva
        TrabajoEntity nuevaTrabajo = trabajoLogic.createTrabajo(trabajoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new TrabajoDTO(nuevaTrabajo);
    }

    /**
     * GET para todas las trabajos
     * http://localhost:8080/traducciones-web/trabajos
     *
     * @return la lista de todas las trabajos en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<TrabajoDTO> getTrabajos() throws BusinessLogicException {
        return listEntity2DTO(trabajoLogic.getTrabajos());
    }

    /**
     * GET para una trabajo
     * http://localhost:8080/traducciones-web/trabajos/1
     *
     * @param id corresponde al id de la trabajo buscada.
     * @return La trabajo encontrada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la trabajo buscada se retorna un 404
     * con el mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public TrabajoDTO getTrabajo(@PathParam("id") Long id) throws BusinessLogicException {
        if (!trabajoLogic.existeTrabajoId(id)) {
             throw new WebApplicationException(TRABAJOS + id + NOEXISTE, 404);
        }
        return new TrabajoDTO(trabajoLogic.getTrabajoId(id));
    }

    /**
     * PUT http://localhost:8080/traducciones-web/trabajos/1 Ejemplo json {
     * "id": 1, "name": "cambio de nombre" }
     *
     * @param id corresponde a la trabajo a actualizar.
     * @param trabajo corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La trabajo actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la trabajo a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public TrabajoDTO updateTrabajo(@PathParam("id") Long id, TrabajoDTO trabajo) throws BusinessLogicException {

        if (!trabajoLogic.existeTrabajoId(id)) {
             throw new WebApplicationException(TRABAJOS + id + NOEXISTE, 404);
        }
        return new TrabajoDTO(trabajoLogic.updateTrabajo(id, trabajo.toEntity()));
    }

    /**
     * DELETE http://localhost:8080/traducciones-web/trabajos/1
     *
     * @param id corresponde a la trayectoia a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la trabajo a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTrabajo(@PathParam("id") Long id) throws BusinessLogicException {
        if (!trabajoLogic.existeTrabajoId(id)) {
            throw new WebApplicationException(TRABAJOS + id + NOEXISTE, 404);
        }
        trabajoLogic.deleteTrabajoId(id);
    }

    @Path("{idTrabajo: \\d+}/calificacion")
    public Class<CalificacionResource> getCalificacionResource(@PathParam("idTrabajo") Long idTrabajo) {
        TrabajoEntity entity = trabajoLogic.getTrabajoId(idTrabajo);
        if (entity == null) {
            throw new WebApplicationException("El recurso /hojaDeVida/" + idTrabajo + "/trayectorias no existe.", 404);
        }
        return CalificacionResource.class;
    }
    
    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos TrabajoEntity a una lista
     * de objetos TrabajoDTO (json)
     *
     * @param entityList corresponde a la lista de trabajos de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de trabajo en forma DTO (json)
     */
    private List<TrabajoDTO> listEntity2DTO(List<TrabajoEntity> entityList) {
        List<TrabajoDTO> list = new ArrayList<>();
        for (TrabajoEntity entity : entityList) {
            list.add(new TrabajoDTO(entity));
        }
        return list;
    }
}
