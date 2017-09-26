/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.SolicitudDetailedDTO;
import co.edu.uniandes.csw.traducciones.ejb.SolicitudLogic;
import co.edu.uniandes.csw.traducciones.entities.SolicitudEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.mappers.WebApplicationExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author aj.ayte
 */
@Path("/Solicitudes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class SolicitudResource {
    
    @Inject
    SolicitudLogic SolicitudLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
   
     * @param Solicitud correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por la base de datos y el tipo del objeto java. 
     * @throws BusinessLogicException
     */
    @POST
    public SolicitudDetailedDTO createSolicitud(SolicitudDetailedDTO Solicitud) throws BusinessLogicException {
        // Convierte el DetailedDTO (json) en un objeto Entity para ser manejado por la lógica.
        SolicitudEntity SolicitudEntity = Solicitud.toEntity();
        // Invoca la lógica para crear la Solicitud nueva
        SolicitudEntity nuevaSolicitud = SolicitudLogic.createSolicitud(SolicitudEntity);
        // Como debe retornar un DetailedDTO (json) se invoca el constructor del DetailedDTO con argumento el entity nuevo
        return new SolicitudDetailedDTO(nuevaSolicitud);
    }
    
    /**
     *
     * @return la lista de todos los Solicituds en objetos json DetailedDTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<SolicitudDetailedDTO> getSolicitud() throws BusinessLogicException {
        return listEntity2DetailDetailedDTO(SolicitudLogic.getSolicituds());
    }
    
    /**
     * @param id corresponde al id buscada.
     * @returnencontrada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la hoja de vida buscada se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public SolicitudDetailedDTO getSolicitud(@PathParam("id") Long id) throws BusinessLogicException {
        SolicitudEntity entity = SolicitudLogic.getSolicitudId(id);
       if (entity == null) {
            throw new WebApplicationException("El recurso /Solicitud/" + id + " no existe.", 404);
        }
       
       return new SolicitudDetailedDTO(entity);
    }
    
     /**
     * @param id corresponde a la id a actualizar.
     * @param Solicitud corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La Solicitud actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Solicitud a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public SolicitudDetailedDTO updateSolicitud(@PathParam("id") Long id, SolicitudDetailedDTO Solicitud) throws BusinessLogicException {
        
        if(!SolicitudLogic.existeSolicitudId(id))
       {
           throw new WebApplicationException("El recurso /Solicitud/" + id + " no existe.", 404);
       }
        return new SolicitudDetailedDTO(SolicitudLogic.updateSolicitud(id,Solicitud.toEntity()));
    }

    /**
\
     * @param id corresponde a la hoja de vida a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la hoja de vida a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteSolicitud(@PathParam("id") Long id) throws BusinessLogicException {
        if(!SolicitudLogic.existeSolicitudId(id))
       {
          throw new WebApplicationException("El recurso /Solicitud/" + id + " no existe.", 404);
       }
       SolicitudLogic.deleteSolicitudId(id);
    }
    
     /**
     *
     * lista de entidades a DetailedDTO.
     *
     * Este método convierte una lista de objetos SolicitudEntity a una lista de
 objetos SolicitudDetailedDTO (json)
     *
     * @param entityList corresponde a la lista de Hojas de vida de tipo Entity
     * que vamos a convertir a DetailedDTO.
     * @return la lista de Hojas de vida en forma DetailedDTO (json)
     */
    private List<SolicitudDetailedDTO> listEntity2DetailDetailedDTO(List<SolicitudEntity> entityList) {
        List<SolicitudDetailedDTO> list = new ArrayList<>();
        for (SolicitudEntity entity : entityList) {
            list.add(new SolicitudDetailedDTO(entity));
        }
        return list;
    }
}
