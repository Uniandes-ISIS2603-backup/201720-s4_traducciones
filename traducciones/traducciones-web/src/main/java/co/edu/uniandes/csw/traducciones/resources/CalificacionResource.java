/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.CalificacionDTO;
import co.edu.uniandes.csw.traducciones.ejb.CalificacionLogic;
import co.edu.uniandes.csw.traducciones.entities.CalificacionEntity;
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
@Path("calificaciones")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class CalificacionResource {

    @Inject
    CalificacionLogic calificacionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * POST http://localhost:8080/traducciones-web/api/calificacions Ejemplo
     * json: { "name":"Diego", "codigo":"123"}
     *
     * @param calificacion correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java.
     * @throws BusinessLogicException
     */
    @POST
    public CalificacionDTO createCalificacion(CalificacionDTO calificacion) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        CalificacionEntity calificacionEntity = calificacion.toEntity();
        // Invoca la lógica para crear la calificacion nueva
        CalificacionEntity nuevaCalificacion = calificacionLogic.createCalificacion(calificacionEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new CalificacionDTO(nuevaCalificacion);
    }

    /**
     * GET para todas las calificacions
     * http://localhost:8080/traducciones-web/calificacions
     *
     * @return la lista de todas las calificacions en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<CalificacionDTO> getCalificacions() throws BusinessLogicException {
        return listEntity2DetailDTO(calificacionLogic.getCalificacions());
    }

    /**
     * GET para una calificacion
     * http://localhost:8080/traducciones-web/calificacions/1
     *
     * @param id corresponde al id de la calificacion buscada.
     * @return La calificacion encontrada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la calificacion buscada se retorna un 404
     * con el mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("id") Long id) throws BusinessLogicException {
        if (!calificacionLogic.existeCalificacionId(id)) {
             throw new WebApplicationException("El recurso /calificaciones/" + id + " no existe.", 404);
        }
        return new CalificacionDTO(calificacionLogic.getCalificacionId(id));
    }

    /**
     * PUT http://localhost:8080/traducciones-web/calificacions/1 Ejemplo json {
     * "id": 1, "name": "cambio de nombre" }
     *
     * @param id corresponde a la calificacion a actualizar.
     * @param calificacion corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La calificacion actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la calificacion a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public CalificacionDTO updateCalificacion(@PathParam("id") Long id, CalificacionDTO calificacion) throws BusinessLogicException {

        if (!calificacionLogic.existeCalificacionId(id)) {
             throw new WebApplicationException("El recurso /calificaciones/" + id + " no existe.", 404);
        }
        return new CalificacionDTO(calificacionLogic.updateCalificacion(id, calificacion.toEntity()));
    }

    /**
     * DELETE http://localhost:8080/traducciones-web/calificacions/1
     *
     * @param id corresponde a la trayectoia a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la calificacion a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCalificacion(@PathParam("id") Long id) throws BusinessLogicException {
        if (!calificacionLogic.existeCalificacionId(id)) {
            throw new WebApplicationException("El recurso /calificaciones/" + id + " no existe.", 404);
        }
        calificacionLogic.deleteCalificacionId(id);
    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos CalificacionEntity a una lista
     * de objetos CalificacionDTO (json)
     *
     * @param entityList corresponde a la lista de calificacions de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de calificacion en forma DTO (json)
     */
    private List<CalificacionDTO> listEntity2DetailDTO(List<CalificacionEntity> entityList) {
        List<CalificacionDTO> list = new ArrayList<>();
        for (CalificacionEntity entity : entityList) {
            list.add(new CalificacionDTO(entity));
        }
        return list;
    }
}
