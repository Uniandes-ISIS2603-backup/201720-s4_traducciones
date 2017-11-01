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

    @POST
    public CalificacionDTO createTrayectoria(@PathParam("idTrabajo") Long idTrabajo, CalificacionDTO calificacion) throws BusinessLogicException {
        return new CalificacionDTO(calificacionLogic.createCalificacion(idTrabajo, calificacion.toEntity()));
    }

    /**
     * GET para calificacion
     * http://localhost:8080/traducciones-web/api/trayectorias
     *
     * @param idTrabajo
     * @return la lista de todas las trayectorias en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public CalificacionDTO getTrayectorias(@PathParam("idTrabajo") Long idTrabajo) throws BusinessLogicException {
        return new CalificacionDTO(calificacionLogic.getCalificacion(idTrabajo));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public CalificacionDTO updateTrayectoria(@PathParam("idTrabajo") Long idTrabajo, @PathParam("id") Long id, CalificacionDTO calificacion) throws BusinessLogicException {
        calificacion.setId(id);
        CalificacionEntity entity = calificacionLogic.getCalificacion(idTrabajo);
        if (entity == null) {
            throw new WebApplicationException(TRABAJOS + idTrabajo + CALIFICACIONES + id + NOEXISTE, 404);
        }
        return new CalificacionDTO(calificacionLogic.updateCalificacion(idTrabajo, calificacion.toEntity()));

    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteTrayectoria(@PathParam("idTrabajo") Long idTrabajo) throws BusinessLogicException {
        CalificacionEntity entity = calificacionLogic.getCalificacion(idTrabajo);
        if (entity == null) {
            throw new WebApplicationException(TRABAJOS + idTrabajo + CALIFICACIONES+NOEXISTE, 404);
        }
        calificacionLogic.deleteCalificacionId(idTrabajo);
    }
}
