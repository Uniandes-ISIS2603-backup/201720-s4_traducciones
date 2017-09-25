/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.IdiomaDTO;
import co.edu.uniandes.csw.traducciones.ejb.IdiomaLogic;
import co.edu.uniandes.csw.traducciones.entities.IdiomaEntity;
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
@Path("/idiomas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class IdiomaResource {
    
    @Inject
    IdiomaLogic IdiomaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
   
     * @param Idioma correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
 la base de datos y el tipo del objeto java. 
     * @throws BusinessLogicException
     */
    @POST
    public IdiomaDTO createIdioma(IdiomaDTO Idioma) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        IdiomaEntity IdiomaEntity = Idioma.toEntity();
        // Invoca la lógica para crear la Idioma nueva
        IdiomaEntity nuevaIdioma = IdiomaLogic.createIdioma(IdiomaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new IdiomaDTO(nuevaIdioma);
    }
    
    /**
     *
     * @return la lista de todos los idiomas en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<IdiomaDTO> getIdioma() throws BusinessLogicException {
        return listEntity2DetailDTO(IdiomaLogic.getIdiomas());
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
    public IdiomaDTO getIdioma(@PathParam("id") Long id) throws BusinessLogicException {
        IdiomaEntity entity = IdiomaLogic.getIdiomaId(id);
       if (entity == null) {
            throw new WebApplicationException("El recurso /Idioma/" + id + " no existe.", 404);
        }
       
       return new IdiomaDTO(entity);
    }
    
     /**
     * @param id corresponde a la id a actualizar.
     * @param Idioma corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La Idioma actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Idioma a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public IdiomaDTO updateIdioma(@PathParam("id") Long id, IdiomaDTO Idioma) throws BusinessLogicException {
        
        if(!IdiomaLogic.existeIdiomaId(id))
       {
           throw new WebApplicationException("El recurso /Idioma/" + id + " no existe.", 404);
       }
        return new IdiomaDTO(IdiomaLogic.updateIdioma(id,Idioma.toEntity()));
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
    public void deleteIdioma(@PathParam("id") Long id) throws BusinessLogicException {
        if(!IdiomaLogic.existeIdiomaId(id))
       {
          throw new WebApplicationException("El recurso /Idioma/" + id + " no existe.", 404);
       }
       IdiomaLogic.deleteIdiomaId(id);
    }
    
     /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos IdiomaEntity a una lista de
 objetos IdiomaDTO (json)
     *
     * @param entityList corresponde a la lista de Hojas de vida de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Hojas de vida en forma DTO (json)
     */
    private List<IdiomaDTO> listEntity2DetailDTO(List<IdiomaEntity> entityList) {
        List<IdiomaDTO> list = new ArrayList<>();
        for (IdiomaEntity entity : entityList) {
            list.add(new IdiomaDTO(entity));
        }
        return list;
    }
}
