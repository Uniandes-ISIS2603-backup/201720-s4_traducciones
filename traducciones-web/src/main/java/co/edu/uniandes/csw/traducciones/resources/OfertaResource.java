/*
  * To change this license header, choose License Headers in Project Properties.
  * To change this template file, choose Tools | Templates
  * and open the template in the editor.
  */
 package co.edu.uniandes.csw.traducciones.resources;
 
 import co.edu.uniandes.csw.traducciones.dtos.OfertaDTO;
import co.edu.uniandes.csw.traducciones.dtos.OfertaDetailDTO;
import co.edu.uniandes.csw.traducciones.dtos.PropuestaDetailDTO;
 import co.edu.uniandes.csw.traducciones.persistence.OfertaPersistence;
 import co.edu.uniandes.csw.traducciones.ejb.OfertaLogic;
 import co.edu.uniandes.csw.traducciones.entities.OfertaEntity;
 import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 import javax.inject.Inject;
 import javax.ws.rs.Consumes;
 import javax.ws.rs.GET;
 import javax.ws.rs.DELETE;
 import javax.ws.rs.POST;
 import javax.ws.rs.PUT;
 import javax.ws.rs.Path;
 import javax.ws.rs.PathParam;
 import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
 /**
  * Clase que implementa el recurso REST correspondiente a "ofertas"
  * @author av.perezb
  */
 @Path("ofertas")
 @Produces("application/json")
 @Consumes("application/json")
 public class OfertaResource {
     
      @Inject 
      OfertaLogic ofertaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
     
      private static final Logger LOGGER = Logger.getLogger(OfertaPersistence.class.getName());
     
 
 /**
  * http://localhost:8080/traducciones-web/api/ofertas
  * @param oferta correponde a la representación java del objeto json
  * enviado en el llamado.
  * @return Devuelve el objeto json de entrada que contiene el id creado por
  * la base de datos y el tipo del objeto java. Ejemplo: { "type":
  * "ofertaDTO", "id": 1, "nombre": "traduccion ingles frances", "cantidad": "30", "descripcion": "Sirve para aplicar un descuento del 30%.",
    "codigo": "AB32SD", "fechaVigencia": "25/09/2017"}
  * @throws BusinessLogicException
  */
 @POST
  public OfertaDetailDTO createOferta(OfertaDetailDTO oferta) throws BusinessLogicException {
      
         // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
         OfertaEntity ofertaEntity = oferta.toEntity();
         // Invoca la lógica para crear la nueva oferta
         OfertaEntity nuevaOferta = null;
         List<OfertaEntity> lista = ofertaLogic.getOfertas();
         for( int i = 0; i < lista.size(); i++)
         { 
             if (lista.get(i).getCodigo().equals(oferta.getCodigo()))
             {
                 throw new WebApplicationException("Ya existe una oferta con el código:" + oferta.getCodigo(), 404);
             }
             else 
             {
                 nuevaOferta = ofertaLogic.createOferta(ofertaEntity);
             }
        }
         // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
         return new OfertaDetailDTO(nuevaOferta);
  }
 
  /**
      * GET para todas las ofertas
      * http://localhost:8080/traducciones-web/api/ofertas
      * @return la lista de todas las ofertas en objetos json DTO.
      * @throws BusinessLogicException
     */
     @GET
     public List<OfertaDetailDTO> getOfertas() throws BusinessLogicException {
         
         return listEntity2DetailDTO(ofertaLogic.getOfertas());
         
     }
     
     /**
      * GET para una oferta
      * http://localhost:8080/traducciones-web/api/ofertas/1
      * 
      * @param id corresponde al id de la oferta buscada.
     * @return La oferta encontrada. Ejemplo: { "type":
      * "ofertaDTO", "id": 1, "nombre": "traduccion inglesfrances", "cantidad": "30", "descripcion": "Sirve para aplicar un descuento del 30% sobre un trabajo.",
        "codigo": "AB32SD", "fechaVigencia": "25/09/2017"}
      * @throws BusinessLogicException
      * 
      * En caso de no existir el id de la oferta buscada se retorna un 404 con
      * el mensaje.
      */
    @GET
    @Path("{id: \\d+}")
     public OfertaDTO getOferta(@PathParam("id") Long id) throws BusinessLogicException {
         
         OfertaEntity ofertaEntity = ofertaLogic.getOferta(id);
       
         return new OfertaDTO(ofertaEntity);
         
     }
     
      /**
      * GET para una oferta
      * http://localhost:8080/traducciones-web/api/ofertas/frances
      *
      * @param nombre corresponde al nombre de la oferta buscada.
      * @return La oferta encontrada. Ejemplo: { "type":
      * "ofertaDTO", "id": 1, "nombre": "traduccion inglesfrances", "cantidad": "30", "descripcion": "Sirve para aplicar un descuento del 30% sobre un trabajo.",
      "codigo": "AB32SD", "fechaVigencia": "25/09/2017"}
      * @throws BusinessLogicException
      *
      * En caso de no existir el nombre de la oferta buscada se retorna un 404 con
      * el mensaje.
      */
     @GET
     @Path("{nombre: [a-zA-Z]+}")
     public List<OfertaDetailDTO> getOfertasNombre(@PathParam("nombre") String nombre) throws BusinessLogicException {
         
        List<OfertaEntity> ofertaEntity = ofertaLogic.getOfertasNombre(nombre);
        List <OfertaDetailDTO> rta = listEntity2DetailDTO(ofertaEntity);
       
        return rta;
         
     }
     
     /**
      * PUT http://localhost:8080/traducciones-web/api/ofertas/1 Ejemplo
      * json { "id": 1, "nombre": "traduccion inglesfrances", "cantidadI": "30", "cantActual": "29", "descripcion": "Sirve para aplicar un descuento del 30% sobre un trabajo.",
      "codigo": "AB32SD", "fechaVigencia": "25/09/2017"}
      * @param id corresponde a la oferta a actualizar.
      * @param oferta corresponde a al objeto con los cambios que se van a
      * realizar.
     * @return La oferta actualizada.
      * @throws BusinessLogicException
      *
      * En caso de no existir el id de la oferta a actualizar.
      * aje.
      */
     @PUT
     @Path("{id: \\d+}")
     public OfertaDTO updateOferta(@PathParam("id") Long id, OfertaDTO oferta) throws BusinessLogicException {
             
     oferta.setId(id);
     
     
       OfertaEntity ofertaUpdated = ofertaLogic.updateOferta(id, oferta.toEntity());
       
       OfertaDTO updated = new OfertaDTO(ofertaUpdated);
    
       return updated;
    }
       
     @DELETE
     @Path("{id: \\d+}")
     public void deleteOferta(@PathParam("id") Long id) throws BusinessLogicException  {
         
          LOGGER.log(Level.INFO, "Inicia proceso de borrar una oferta con id {0}", id);
         
          ofertaLogic.deleteOferta(id);
              
     }
   
    @POST
    @Path("{ofertaId: \\d+}/propuestas")
    public PropuestaDetailDTO addPropuesta(@PathParam("ofertaId") Long ofertaId, PropuestaDetailDTO propuesta) throws BusinessLogicException {
       return new PropuestaDetailDTO(ofertaLogic.addPropuesta(propuesta.toEntity(),ofertaId));
        
    }
    
     /**
      *
      * lista de entidades a DTO.
      *
      * Este método convierte una lista de objetos OfertaEntity a una lista de
     * objetos OfertaDTO (json)
      *
      * @param entityList corresponde a la lista de ofertas de tipo Entity
      * que vamos a convertir a DTO.
      * @return la lista de editoriales en forma DTO (json)
      */
     private List<OfertaDetailDTO> listEntity2DetailDTO(List <OfertaEntity> entityList) {
         List<OfertaDetailDTO> list = new ArrayList<>();
         for (OfertaEntity entity : entityList) {
             list.add(new OfertaDetailDTO(entity));
        }
         return list;
     }
 }
