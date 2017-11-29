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
import java.util.ArrayList;
import java.util.List;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ra.forero11
 */

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrayectoriaResource {
    
     @Inject
    TrayectoriaLogic trayectoriaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
     
     
    /**
     * GET para todas las trayectorias
     * http://localhost:8080/traducciones-web/api/hojadevida/idHojaDeVida/trayectorias
     *
     * @param idHojaDeVida
     * @return la lista de todas las trayectorias en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<TrayectoriaDTO> getTrayectorias(@PathParam("idHojaDeVida") Long idHojaDeVida) throws BusinessLogicException {
        return listEntity2DetailDTO(trayectoriaLogic.getTrayectorias(idHojaDeVida));
    }
    
    /**
     * GET para una trayectoria especifica de una hoja de vida
     * http://localhost:8080/traducciones-web/api/hojadevida/idHojaDeVida/trayectorias/idTrayectoria
     *
     * @param idHojaDeVida
     * @param idTrayectoria
     * @return la trayectoria en objeto json DTO.
     * @throws BusinessLogicException
     */
    @GET
    @Path("{id: \\d+}")
    public TrayectoriaDTO getTrayectoria(@PathParam("idHojaDeVida") Long idHojaDeVida, @PathParam("id") Long idTrayectoria) throws BusinessLogicException {
        TrayectoriaEntity entity = trayectoriaLogic.getTrayectoriaId(idHojaDeVida, idTrayectoria);
        if (entity == null) {
            throw new WebApplicationException("El recurso /hojadevida/" + idHojaDeVida + "/trayectorias/" + idTrayectoria + " no existe.", 404);
        }
        return new TrayectoriaDTO(entity);
    }
    
    /**
     * POST para una trayectoria especifica de una hoja de vida
     * http://localhost:8080/traducciones-web/api/hojadevida/idHojaDeVida/trayectorias
     *
     * @param idHojaDeVida
     * @param trayectoria DTO Trayectoria a agregar a la hoja de vida
     * @return la trayectoria en objeto json DTO.
     * @throws BusinessLogicException
     */
    @POST
    public TrayectoriaDTO createTrayectoria(@PathParam("idHojaDeVida") Long idHojaDeVida, TrayectoriaDTO trayectoria) throws BusinessLogicException {
        return new TrayectoriaDTO(trayectoriaLogic.createTrayectoria(idHojaDeVida, trayectoria.toEntity()));
    }
    
     /**
     * PUT para una trayectoria especifica de una hoja de vida
     * http://localhost:8080/traducciones-web/api/hojadevida/idHojaDeVida/trayectorias/idTrayectoria
     *
     * @param idHojaDeVida
     * @param idTrayectoria
     * @return la trayectoria en objeto json DTO.
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public TrayectoriaDTO updateTrayectoria(@PathParam("idHojaDeVida") Long idHojaDeVida, @PathParam("id") Long idTrayectoria, TrayectoriaDTO trayectoria) throws BusinessLogicException {
        trayectoria.setId(idTrayectoria);
        TrayectoriaEntity entity = trayectoriaLogic.getTrayectoriaId(idHojaDeVida, idTrayectoria);
        if (entity == null) {
            throw new WebApplicationException("El  recurso /hojadevida/" + idHojaDeVida + "/trayectorias/ " + idTrayectoria + "  no existe.", 404);
        }
        return new TrayectoriaDTO(trayectoriaLogic.updateTrayectoria(idHojaDeVida, trayectoria.toEntity()));

    }
    
    /**
     * DELETE para una trayectoria especifica de una hoja de vida
     * http://localhost:8080/traducciones-web/api/hojadevida/idHojaDeVida/trayectorias/idTrayectoria
     * @param idHojaDeVida
     * @param idTrayectoria
     * @throws BusinessLogicException
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTrayectoria(@PathParam("idHojaDeVida") Long idHojaDeVida, @PathParam("id") Long idTrayectoria) throws BusinessLogicException {
        TrayectoriaEntity entity = trayectoriaLogic.getTrayectoriaId(idHojaDeVida, idTrayectoria);
        if (entity == null) {
            throw new WebApplicationException(" El recurso /hojadevida/" + idHojaDeVida + " /trayectoria/" + idTrayectoria + " no  existe.", 404);
        }
        trayectoriaLogic.deleteTrayectoriaId(idHojaDeVida, idTrayectoria);
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
