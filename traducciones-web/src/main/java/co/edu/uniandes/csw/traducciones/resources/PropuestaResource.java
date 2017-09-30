/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.PropuestaDTO;
 import co.edu.uniandes.csw.traducciones.ejb.PropuestaLogic;
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
 import javax.ws.rs.Path;
 import javax.ws.rs.PathParam;
 import javax.ws.rs.Produces;
 
 /**
  *Clase que implementa el recurso REST correspondiente a Propuesta
  * @author av.perezb
  */
 @Path("propuestas")
 @Produces("application/json")
 @Consumes("application/json")
 public class PropuestaResource {
     
     @Inject
     PropuestaLogic logic;
       
     private static final Logger LOGGER = Logger.getLogger(PropuestaPersistence.class.getName());
     
  
     /**
      * GET para todas las propuestas.
      * http://localhost:8080/traducciones-web/api/propuestas
      *
      * @return la lista de todas las propuestas en objetos json DTO.
      * @throws Exception
      */
     @GET
     public List<PropuestaDTO> getPropuestas() throws Exception {
         
         return listEntity2DetailDTO(logic.getPropuestas());
     
     }
    
     @DELETE
     @Path("{id: \\d+}")
     public void deletePropuesta(@PathParam("id") Long id) throws BusinessLogicException  {
         
          LOGGER.log(Level.INFO, "Inicia proceso de borrar una oferta con id {0}", id);
         
          logic.deletePropuesta(id);
               
     }
     
    
     
 /**
  * http://localhost:8080/traducciones-web/api/propuestas
  * @param propuesta correponde a la representación java del objeto json
  * enviado en el llamado.
  * @return Devuelve el objeto json de entrada que contiene el id creado por
  * la base de datos y el tipo del objeto java. Ejemplo: { "type":
  * "PropuestaDTO", "id": 1, "nombre": "traduccion inglesfrances",  "costo": "230.00",
    "estado": "EN_REVISION"}
  * @throws BusinessLogicException
  */
 @POST
  public PropuestaDTO createPropuesta(PropuestaDTO propuesta) throws BusinessLogicException {
      
         // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
         PropuestaEntity propuestaEntity = propuesta.toEntity();
         // Invoca la lógica para crear la nueva oferta
         PropuestaEntity nuevaOferta = logic.createPropuesta(propuestaEntity);
         // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
         return new PropuestaDTO(nuevaOferta);
  }
  /**
      * GET para una oferta
      * http://localhost:8080/traducciones-web/api/propuestas/1
      *
      * @param id corresponde al id de la oferta buscada.
      * @return La oferta encontrada. Ejemplo: { "type":
      * "PropuestaDTO", "id": 1, "nombre": "traduccion inglesfrances",  "costo": "230.00",
        "estado": "EN_REVISION"}
      * @throws BusinessLogicException
      *
      * En caso de no existir el id de la Propuesta buscada se retorna un 404 con
      * el mensaje.
      */
     @GET
     @Path("{id: \\d+}")
     public PropuestaDTO getOferta(@PathParam("id") Long id) throws BusinessLogicException, Exception {
         
         PropuestaEntity ofertaEntity = logic.getPropuesta(id);
       
         return new PropuestaDTO(ofertaEntity);
         
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
     private List<PropuestaDTO> listEntity2DetailDTO(List<PropuestaEntity> entityList) {
         
         List<PropuestaDTO> list = new ArrayList<>();
         for (PropuestaEntity entity : entityList) {
             list.add(new PropuestaDTO(entity));
        }
         return list;
     }
     
     
     
     
 }
