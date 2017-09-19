/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.HojaDeVidaDTO;
import co.edu.uniandes.csw.traducciones.ejb.HojaDeVidaLogic;
import co.edu.uniandes.csw.traducciones.entities.HojaDeVidaEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.mappers.WebApplicationExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
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

/**
 *
 * @author ra.forero11
 */
@Path("hojadevida")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class HojaDeVidaResource {
    
    @Inject
    HojaDeVidaLogic hojaDeVidaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * POST http://localhost:8080/traducciones-web/cantantes Ejemplo
     * json: { "name":"Diego", "codigo":"123"}
     *
     * @param hojaDeVida correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
 la base de datos y el tipo del objeto java. 
     * @throws BusinessLogicException
     */
    @POST
    public HojaDeVidaDTO createHojaDeVida(HojaDeVidaDTO hojaDeVida) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        HojaDeVidaEntity hojaDeVidaEntity = hojaDeVida.toEntity();
        // Invoca la lógica para crear la hojaDeVida nueva
        HojaDeVidaEntity nuevaHojaDeVida = hojaDeVidaLogic.createHojaDeVida(hojaDeVidaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new HojaDeVidaDTO(nuevaHojaDeVida);
    }
    
    /**
     * GET para todas las hojas de vida
     * http://localhost:8080/traducciones-web/hojadevida
     *
     * @return la lista de todas las hojas de vida en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<HojaDeVidaDTO> getHojasDeVida() throws BusinessLogicException {
        return listEntity2DetailDTO(hojaDeVidaLogic.getHojasDeVida());
    }
    
    /**
     * GET para una hoja de vida
     * http://localhost:8080/traducciones-web/hojadevida/1
     *
     * @param id corresponde al id de la hoja de vida buscada.
     * @return La hoja de vida encontrada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la hoja de vida buscada se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public HojaDeVidaDTO getHojaDeVida(@PathParam("id") Long id) throws BusinessLogicException {
       if(!hojaDeVidaLogic.existeHojaDeVidaId(id))
       {
           WebApplicationExceptionMapper ex=new WebApplicationExceptionMapper();
           ex.toResponse(new WebApplicationException("No existe la hoja de vida con el id:"+id+" especidicado"));
       }
       return new HojaDeVidaDTO(hojaDeVidaLogic.getHojaDeVidaId(id));
    }
    
     /**
     * PUT http://localhost:8080/traducciones-web/hojadevida/1 Ejemplo
     * json { "id": 1, "name": "cambio de nombre" }
     *
     * @param id corresponde a la trayectoria a actualizar.
     * @param hojaDeVida corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La trayectoria actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la trayectoria a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public HojaDeVidaDTO updateHojaDeVida(@PathParam("id") Long id, HojaDeVidaDTO hojaDeVida) throws BusinessLogicException {
        
        if(!hojaDeVidaLogic.existeHojaDeVidaId(id))
       {
           WebApplicationExceptionMapper ex=new WebApplicationExceptionMapper();
           ex.toResponse(new WebApplicationException("No existe la hoja de vida con el id:"+id+" especidicado"));
       }
        return new HojaDeVidaDTO(hojaDeVidaLogic.updateHojaDeVida(id,hojaDeVida.toEntity()));
    }

    /**
     * DELETE http://localhost:8080/traducciones-web/hojadevida/1
     *
     * @param id corresponde a la hoja de vida a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la hoja de vida a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteHojaDeVida(@PathParam("id") Long id) throws BusinessLogicException {
        if(!hojaDeVidaLogic.existeHojaDeVidaId(id))
       {
           WebApplicationExceptionMapper ex=new WebApplicationExceptionMapper();
           ex.toResponse(new WebApplicationException("No existe la hoja de vida con el id:"+id+" especidicado"));
       }
       hojaDeVidaLogic.deleteHojaDeVidaId(id);
    }
    
     /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos HojaDeVidaEntity a una lista de
 objetos HojaDeVidaDTO (json)
     *
     * @param entityList corresponde a la lista de Hojas de vida de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Hojas de vida en forma DTO (json)
     */
    private List<HojaDeVidaDTO> listEntity2DetailDTO(List<HojaDeVidaEntity> entityList) {
        List<HojaDeVidaDTO> list = new ArrayList<>();
        for (HojaDeVidaEntity entity : entityList) {
            list.add(new HojaDeVidaDTO(entity));
        }
        return list;
    }
}
