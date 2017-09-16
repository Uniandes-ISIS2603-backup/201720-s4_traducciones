package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.EmpleadoDetailDTO;
import co.edu.uniandes.csw.traducciones.ejb.EmpleadoLogic;
import co.edu.uniandes.csw.traducciones.entities.EmpleadoEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
 * @author jc.gloria
 */

@Path("empleados")
@Produces("application/json")
@Stateless
public class EmpleadoResource {
    
    @Inject
    EmpleadoLogic empleadoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    @GET
    public List<EmpleadoDetailDTO> getEmpleados() throws BusinessLogicException {
        return listEntity2DetailDTO(empleadoLogic.getEmpleados());
    }
    
    @GET
    @Path("{id: \\d+}")
    public EmpleadoDetailDTO getEmpleado(@PathParam("id") Long id) {
        EmpleadoEntity entity = empleadoLogic.getEmpleado(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso empleado: " + id + " no existe.", 404);
        }
        return new EmpleadoDetailDTO(entity);
    }
    
    @POST
    public EmpleadoDetailDTO createEmpleado(EmpleadoDetailDTO empleado) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        EmpleadoEntity empleadoEntity = empleado.toEntity();
        // Invoca la lógica para crear la city nueva
        EmpleadoEntity nuevoEmpleado = empleadoLogic.createEmpleado(empleadoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new EmpleadoDetailDTO(nuevoEmpleado);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public EmpleadoDetailDTO updateCity(@PathParam("id") Long id, EmpleadoDetailDTO empleado) throws BusinessLogicException {
        empleado.setId(id);
        EmpleadoEntity entity = empleadoLogic.getEmpleado(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso empleado: " + id + " no existe.", 404);
        }
        return new EmpleadoDetailDTO(empleadoLogic.updateEmpleado(id, empleado.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCity(@PathParam("id") Long id) {
        EmpleadoEntity entity = empleadoLogic.getEmpleado(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso empleado: " + id + " no existe.", 404);
        }
        empleadoLogic.deleteEmpleado(entity);
    }
    
    private List<EmpleadoDetailDTO> listEntity2DetailDTO(List<EmpleadoEntity> entityList) {
        List<EmpleadoDetailDTO> list = new ArrayList<>();
        for (EmpleadoEntity entity : entityList) {
            list.add(new EmpleadoDetailDTO(entity));
        }
        return list;
    }
}