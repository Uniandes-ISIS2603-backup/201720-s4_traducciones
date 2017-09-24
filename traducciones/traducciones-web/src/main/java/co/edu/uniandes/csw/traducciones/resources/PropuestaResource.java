/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.resources;

import antlr.collections.List;
import co.edu.uniandes.csw.traducciones.dtos.PropuestaDTO;
import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import javax.ws.rs.GET;

/**
 *Clase que implementa el recurso REST correspondiente a Propuesta
 * @author av.perezb
 */
public class PropuestaResource {
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * GET para todas las propuestas.
     * http://localhost:8080/traducciones-web/api/propuestas
     *
     * @return la lista de todas las editoriales en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<PropuestaDTO> getEditorials() throws BusinessLogicException {
        return listEntity2DetailDTO(editorialLogic.getEditorials());
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
