/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.HojaDeVidaDTO;
import co.edu.uniandes.csw.traducciones.dtos.HojaDeVidaDetailedDTO;
import co.edu.uniandes.csw.traducciones.dtos.IdiomaDTO;
import co.edu.uniandes.csw.traducciones.ejb.HojaDeVidaLogic;
import co.edu.uniandes.csw.traducciones.entities.HojaDeVidaEntity;
import co.edu.uniandes.csw.traducciones.entities.IdiomaEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
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
 * @author ra.forero11
 */
@Path("/hojadevida")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class HojaDeVidaResource {
    
    private final static String HOJAS="El recurso /hojadevida/ ";
    private final static String NOEXISTE="no existe.";
    
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
    public HojaDeVidaDetailedDTO createHojaDeVida(HojaDeVidaDTO hojaDeVida) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        HojaDeVidaEntity hojaDeVidaEntity = hojaDeVida.toEntity();
        // Invoca la lógica para crear la hojaDeVida nueva
        HojaDeVidaEntity nuevaHojaDeVida = hojaDeVidaLogic.createHojaDeVida(hojaDeVidaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new HojaDeVidaDetailedDTO(nuevaHojaDeVida);
    }
    
    /**
     * GET para todas las hojas de vida
     * http://localhost:8080/traducciones-web/hojadevida
     *
     * @return la lista de todas las hojas de vida en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<HojaDeVidaDetailedDTO> getHojasDeVida() throws BusinessLogicException {
        return listEntity2DetailDTO(hojaDeVidaLogic.getHojasDeVida());
    }
    
    /**
     * GET para una hoja de vida
     * http://localhost:8080/traducciones-web/hojadevida/1
     * @param id corresponde al id de la hoja de vida buscada.
     * @return La hoja de vida encontrada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la hoja de vida buscada se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public HojaDeVidaDetailedDTO getHojaDeVida(@PathParam("id") Long id) throws BusinessLogicException {
        HojaDeVidaEntity entity = hojaDeVidaLogic.getHojaDeVidaId(id);
       if (entity == null) {
            throw new WebApplicationException(HOJAS + id + NOEXISTE, 404);
        }
       
       return new HojaDeVidaDetailedDTO(entity);
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
    public HojaDeVidaDetailedDTO updateHojaDeVida(@PathParam("id") Long id, HojaDeVidaDTO hojaDeVida) throws BusinessLogicException {
        
        if(!hojaDeVidaLogic.existeHojaDeVidaId(id))
       {
           throw new WebApplicationException(HOJAS + id + NOEXISTE, 404);
       }
        return new HojaDeVidaDetailedDTO(hojaDeVidaLogic.updateHojaDeVida(id,hojaDeVida.toEntity()));
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
          throw new WebApplicationException(HOJAS + id + NOEXISTE, 404);
       }
       hojaDeVidaLogic.deleteHojaDeVidaId(id);
    }
    
    
     @Path("{idHojaDeVida: \\d+}/trayectorias")
    public Class<TrayectoriaResource> gettrayectoriaResource(@PathParam("idHojaDeVida") Long hojaDeVidaId) {
        HojaDeVidaEntity entity = hojaDeVidaLogic.getHojaDeVidaId(hojaDeVidaId);
        if (entity == null) {
            throw new WebApplicationException(HOJAS + hojaDeVidaId + "/trayectorias no existe.", 404);
        }
        return TrayectoriaResource.class;
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
    private List<HojaDeVidaDetailedDTO> listEntity2DetailDTO(List<HojaDeVidaEntity> entityList) {
        List<HojaDeVidaDetailedDTO> list = new ArrayList<>();
        for (HojaDeVidaEntity entity : entityList) {
            list.add(new HojaDeVidaDetailedDTO(entity));
        }
        return list;
    }
    
  private List<IdiomaDTO> idiomasListEntity2DTO(List<IdiomaEntity> entityList) {
        List<IdiomaDTO> list = new ArrayList<>();
        for (IdiomaEntity entity : entityList) {
            list.add(new IdiomaDTO(entity));
        }
        return list;
    }
    
    
    @GET
    @Path("{hojaDeVidaId: \\d+}/idiomas")
    public List<IdiomaDTO> listIdiomas(@PathParam("hojaDeVidaId") Long hojaDeVidaId) throws BusinessLogicException {
        if(hojaDeVidaLogic.getIdiomas(hojaDeVidaId).isEmpty()){
            throw new WebApplicationException("Este hojaDeVida no tiene areas de conocimiento asociadas", 404);
        }
        return idiomasListEntity2DTO(hojaDeVidaLogic.getIdiomas(hojaDeVidaId));
    }
    
    @POST
    @Path("{hojaDeVidaId: \\d+}/idiomas")
    public IdiomaDTO addIdioma(@PathParam("hojaDeVidaId") Long hojaDeVidaId, IdiomaDTO idioma) throws BusinessLogicException {
       return new IdiomaDTO(hojaDeVidaLogic.addIdioma(idioma.toEntity(),hojaDeVidaId));
        
    }
    
    @DELETE
    @Path("{hojaDeVidaId: \\d+}/idiomas/{idiomaId: \\d+}")
    public void removeAreaDeconocimiento(@PathParam("hojaDeVidaId") Long hojaDeVidaId, @PathParam("idiomaId") Long idiomaId) throws BusinessLogicException {
        hojaDeVidaLogic.removeIdioma(idiomaId,hojaDeVidaId);
    }
}
