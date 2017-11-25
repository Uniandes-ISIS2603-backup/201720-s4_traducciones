/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.OfertaDTO;
import co.edu.uniandes.csw.traducciones.dtos.PropuestaDTO;
import co.edu.uniandes.csw.traducciones.dtos.PropuestaDetailDTO;
import co.edu.uniandes.csw.traducciones.ejb.OfertaLogic;
import co.edu.uniandes.csw.traducciones.ejb.PropuestaLogic;
import co.edu.uniandes.csw.traducciones.entities.OfertaEntity;
import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.PropuestaPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso REST correspondiente a Propuesta
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "propuestas". Al ejecutar la aplicación, el recurso
 * será accesibe a través de la ruta "/api/propuestas"
 * @author av.perezb
 */
@Path("propuestas")
@Produces("application/json")
@Consumes("application/json")
public class PropuestaResource {

    @Inject
    PropuestaLogic logic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    OfertaLogic ofertaLogic; // Variable para acceder a la lógica de Oferta de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(PropuestaPersistence.class.getName()); //Encargado de registrar los mensajes para el sistema.

    /**
     * GET para todas las propuestas.
     * http://localhost:8080/traducciones-web/api/propuestas
     *
     * @return la lista de todas las propuestas en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<PropuestaDetailDTO> getPropuestas() throws BusinessLogicException {

        return listEntity2DetailDTO(logic.getPropuestas());

    }

     /**
     * DELETE para una propuesta
     * http://localhost:8080/traducciones-web/api/propuestas/5
     *
     * @param id corresponde al id de la oferta a eliminar.
     * @throws BusinessLogicException
     * En caso de no existir la oferta buscada se retorna un 404
     * con el mensaje.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePropuesta(@PathParam("id") Long id) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "Inicia proceso de borrar una oferta con id {0}", id);

        logic.deletePropuesta(id);

    }

    /**
     * http://localhost:8080/traducciones-web/api/propuestas
     *
     * @param propuesta corresponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "PropuestaDTO", "id": 1, "nombre": "traduccion inglesfrances", "costo":
     * "230.00", "estado": "EN_REVISION"}
     * @throws BusinessLogicException
     */
    @POST
    public PropuestaDTO createPropuesta(PropuestaDTO propuesta) throws BusinessLogicException {

        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        PropuestaEntity propuestaEntity = propuesta.toEntity();
        // Invoca la lógica para crear la nueva oferta
        PropuestaEntity nuevaPropuesta = logic.createPropuesta(propuestaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new PropuestaDetailDTO(nuevaPropuesta);
    }

    /**
     * http://localhost:8080/traducciones-web/api/propuestas/1
     *
     * @param propuestaId
     * @param idOferta
     * @return Devuelve el objeto json de entrada que contiene las modificaciones en
     * la base de datos y el tipo del objeto java. Ejemplo: { "propuesta": "nombre": "p1",... "oferta": {
     * "codigo": "ASDF"}}
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{propuestaId: \\d+}/ofertas/{idOferta: \\d+}")
    public PropuestaDetailDTO agregarOferta(@PathParam("propuestaId") Long propuestaId, @PathParam("idOferta") Long idOferta) throws BusinessLogicException {

        PropuestaDetailDTO rta = new PropuestaDetailDTO(logic.getPropuesta(propuestaId));
        OfertaEntity oferta = ofertaLogic.getOferta(idOferta);
        rta.setOferta(new OfertaDTO(oferta));
        logic.agregarOferta(propuestaId, oferta);
        ofertaLogic.getOferta(idOferta).addPropuestas(rta.toEntity());

        if (oferta != null) {
            rta.setOferta(new OfertaDTO(oferta));

        }
        return rta;
    }

    /**
     * DELETE para una oferta en una propuesta
     * http://localhost:8080/traducciones-web/api/propuestas/5/ofertas/2
     * @param idProp corresponde al id de la propuesta que tiene la oferta a eliminar.
     * @param idOf corresponde al id de la oferta a eliminar.
     * @throws BusinessLogicException
     * En caso de no existir la oferta buscada se retorna un 404
     * con el mensaje.
     */
    @DELETE
    @Path("{idProp: \\d+}/ofertas/{idOf: \\d+}")
    public void deleteOferta(@PathParam("idOf") Long idOf, @PathParam("idProp") Long idProp) throws BusinessLogicException {

        logic.deleteOferta(idProp, idOf);
    }

    /**
     * GET para una propuesta
     * http://localhost:8080/traducciones-web/api/propuestas/1
     *
     * @param id corresponde al id de la oferta buscada.
     * @return La oferta encontrada. Ejemplo: { "type": "PropuestaDTO", "id": 1,
     * "nombre": "traduccion inglesfrances", "costo": "230.00", "estado":
     * "EN_REVISION"}
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Propuesta buscada se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public PropuestaDetailDTO getPropuesta(@PathParam("id") Long id) throws BusinessLogicException {

        PropuestaEntity propuestaEntity = logic.getPropuesta(id);

        return new PropuestaDetailDTO(propuestaEntity);

    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PropuestaEntity a una lista de
     * objetos PropuestaDTO (json)
     *
     * @param entityList corresponde a la lista de editoriales de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de editoriales en forma DTO (json)
     */
    private List<PropuestaDetailDTO> listEntity2DetailDTO(List<PropuestaEntity> entityList) {

        List<PropuestaDetailDTO> list = new ArrayList<>();
        for (PropuestaEntity entity : entityList) {
            list.add(new PropuestaDetailDTO(entity));
        }
        return list;
    }

}
