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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
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
public class CalificacionResource {
    
    
    private final String TRABAJOS="E l recurso /trabajos/";
    private final String CALIFICACIONES="/calificacion/";
    private final String NOEXISTE="no existe.";

    @Inject
    CalificacionLogic calificacionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

//    @POST
//    public CalificacionDTO createCalificacion(@PathParam("idTrabajo") Long idTrabajo, CalificacionDTO calificacion) throws BusinessLogicException {
//        return new CalificacionDTO(calificacionLogic.createCalificacion(idTrabajo, calificacion.toEntity()));
//    }
//
//    /**
//     * GET para calificacion
//     * http://localhost:8080/traducciones-web/api/calificacions
//     *
//     * @param idTrabajo
//     * @return la lista de todas las calificacions en objetos json DTO.
//     * @throws BusinessLogicException
//     */
//    @GET
//    public CalificacionDTO getCalificacion(@PathParam("idTrabajo") Long idTrabajo) throws BusinessLogicException {
//        return new CalificacionDTO(calificacionLogic.getCalificacion(idTrabajo));
//    }
//    
//    @PUT
//    @Path("{id: \\d+}")
//    public CalificacionDTO updateCalificacion(@PathParam("idTrabajo") Long idTrabajo, @PathParam("id") Long id, CalificacionDTO calificacion) throws BusinessLogicException {
//        calificacion.setId(id);
//        CalificacionEntity entity = calificacionLogic.getCalificacion(idTrabajo);
//        if (entity == null) {
//            throw new WebApplicationException(TRABAJOS + idTrabajo + CALIFICACIONES + id + NOEXISTE, 404);
//        }
//        return new CalificacionDTO(calificacionLogic.updateCalificacion(idTrabajo, calificacion.toEntity()));
//
//    }
//
//    @DELETE
//    @Path("{id: \\d+}")
//    public void deleteCalificacion(@PathParam("idTrabajo") Long idTrabajo) throws BusinessLogicException {
//        CalificacionEntity entity = calificacionLogic.getCalificacion(idTrabajo);
//        if (entity == null) {
//            throw new WebApplicationException(TRABAJOS + idTrabajo + CALIFICACIONES+NOEXISTE, 404);
//        }
//        calificacionLogic.deleteCalificacion(idTrabajo);
//    }
    
    /**
     * GET para todas las calificacions
     * http://localhost:8080/traducciones-web/api/calificacions
     *
     * @param idTrabajo
     * @return la lista de todas las calificacions en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<CalificacionDTO> getCalificacions(@PathParam("idTrabajo") Long idTrabajo) throws BusinessLogicException {
        return listEntity2DetailDTO(calificacionLogic.getCalificacions(idTrabajo));
    }
    
    @GET
    @Path("{id: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("idTrabajo") Long idTrabajo, @PathParam("id") Long id) throws BusinessLogicException {
        CalificacionEntity entity = calificacionLogic.getCalificacionId(idTrabajo, id);
        if (entity == null) {
            throw new WebApplicationException(TRABAJOS + idTrabajo + CALIFICACIONES + id + NOEXISTE, 404);
        }
        return new CalificacionDTO(entity);
    }
    
    @POST
    public CalificacionDTO createCalificacion(@PathParam("idTrabajo") Long idTrabajo, CalificacionDTO calificacion) throws BusinessLogicException {
        return new CalificacionDTO(calificacionLogic.createCalificacion(idTrabajo, calificacion.toEntity()));
    }
    
    
    @PUT
    @Path("{id: \\d+}")
    public CalificacionDTO updateCalificacion(@PathParam("idTrabajo") Long idTrabajo, @PathParam("id") Long id, CalificacionDTO calificacion) throws BusinessLogicException {
        calificacion.setId(id);
        CalificacionEntity entity = calificacionLogic.getCalificacionId(idTrabajo, id);
        if (entity == null) {
            throw new WebApplicationException(TRABAJOS+ idTrabajo + CALIFICACIONES+ id + NOEXISTE, 404);
        }
        return new CalificacionDTO(calificacionLogic.updateCalificacion(idTrabajo, calificacion.toEntity()));

    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteCalificacion(@PathParam("idTrabajo") Long idTrabajo, @PathParam("id") Long id) throws BusinessLogicException {
        CalificacionEntity entity = calificacionLogic.getCalificacionId(idTrabajo, id);
        if (entity == null) {
            throw new WebApplicationException(TRABAJOS + idTrabajo + CALIFICACIONES + id + NOEXISTE, 404);
        }
        calificacionLogic.deleteCalificacionId(idTrabajo, id);
    }
    
     /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos CalificacionEntity a una lista de
 objetos CalificacionDTO (json)
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
