package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.AreaDeConocimientoDetailDTO;
import co.edu.uniandes.csw.traducciones.dtos.EmpleadoDetailDTO;
import co.edu.uniandes.csw.traducciones.dtos.HojaDeVidaDetailedDTO;
import co.edu.uniandes.csw.traducciones.ejb.EmpleadoLogic;
import co.edu.uniandes.csw.traducciones.entities.AreaDeConocimientoEntity;
import co.edu.uniandes.csw.traducciones.entities.EmpleadoEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.EmpleadoPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
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

/**
 *
 * @author jc.gloria
 */

@Path("empleados")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EmpleadoResource {
    
    @Inject
    EmpleadoLogic empleadoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    private static final Logger LOGGER = Logger.getLogger(EmpleadoPersistence.class.getName());
    
    @GET
    public List<EmpleadoDetailDTO> getEmpleados() {
        return listEntity2DetailDTO(empleadoLogic.getEmpleados());
    }
    
    @GET
    @Path("{id: \\d+}")
    public EmpleadoDetailDTO getEmpleado(@PathParam("id") Long id) {
        System.out.println("entro al get");
        EmpleadoEntity entity = empleadoLogic.getEmpleado(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso empleado: " + id + " no existe.", 404);
        }
        return new EmpleadoDetailDTO(entity);
    }
    
    @POST
    public EmpleadoDetailDTO createEmpleado(EmpleadoDetailDTO empleado ) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        EmpleadoEntity empleadoEntity = empleado.toEntity();
        // Invoca la lógica para crear el empleado nuevo
        EmpleadoEntity nuevoEmpleado = empleadoLogic.createEmpleado(empleadoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new EmpleadoDetailDTO(nuevoEmpleado);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public EmpleadoDetailDTO updateEmpleado(@PathParam("id") Long id, EmpleadoDetailDTO empleado) throws BusinessLogicException {
        empleado.setId(id);
        EmpleadoEntity entity = empleadoLogic.getEmpleado(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso empleado: " + id + " no existe.", 404);
        }
        return new EmpleadoDetailDTO(empleadoLogic.updateEmpleado(id, empleado.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEmpleado(@PathParam("id") Long id) {
        System.out.println("entro a delete");
        EmpleadoEntity entity = empleadoLogic.getEmpleado(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso empleado: " + id + " no existe.", 404);
        }
        empleadoLogic.deleteEmpleado(entity);
    }
    
    //SECCION DE AREAS DE CONOCIMIENTO
    
    private List<AreaDeConocimientoDetailDTO> areasdeconocimientoListEntity2DTO(List<AreaDeConocimientoEntity> entityList) {
        List<AreaDeConocimientoDetailDTO> list = new ArrayList<AreaDeConocimientoDetailDTO>();
        for (AreaDeConocimientoEntity entity : entityList) {
            list.add(new AreaDeConocimientoDetailDTO(entity));
        }
        return list;
    }
    
    private List<AreaDeConocimientoEntity> areasdeconocimientoListDTO2Entity(List<AreaDeConocimientoDetailDTO> dtos) {
        List<AreaDeConocimientoEntity> list = new ArrayList<>();
        for (AreaDeConocimientoDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    //AREA DE CONOCIMIENTO
    
    
    @GET
    @Path("{empleadoId: \\d+}/areasdeconocimiento")
    public List<AreaDeConocimientoDetailDTO> listAreasDeConocimiento(@PathParam("empleadoId") Long empleadoId) {
        if(empleadoLogic.getAreasDeConocimiento(empleadoId).isEmpty()){
            throw new WebApplicationException("Este empleado no tiene areas de conocimiento asociadas", 404);
        }
        return areasdeconocimientoListEntity2DTO(empleadoLogic.getAreasDeConocimiento(empleadoId));
    }
    
    @GET
    @Path("{empleadoId: \\d+}/areasdeconocimiento/{areasdeconocimientoId: \\d+}")
    public AreaDeConocimientoDetailDTO getAreaDeConocimiento(@PathParam("empleadoId") Long empleadoId, @PathParam("areasdeconocimientoId") Long areasdeconocimientoId) throws BusinessLogicException {
        return new AreaDeConocimientoDetailDTO(empleadoLogic.getAreaDeConocimiento(empleadoId, areasdeconocimientoId));
    }
    
    @POST
    @Path("{empleadoId: \\d+}/areasdeconocimiento")
    public AreaDeConocimientoDetailDTO addAreaDeConocimiento(AreaDeConocimientoDetailDTO area, @PathParam("empleadoId") Long empleadoId ) throws BusinessLogicException {
        empleadoLogic.addAreaDeConocimiento(area.toEntity(),empleadoId);
        return area;
    }
    
    @PUT
    @Path("{empleadoId: \\d+}/areasdeconocimiento/{areasdeconocimientoId: \\d+}")
    public AreaDeConocimientoDetailDTO replaceAreaDeConocimiento(@PathParam("empleadoId") Long empleadoId, @PathParam("areasdeconocimientoId") Long areasdeconocimientoId, AreaDeConocimientoDetailDTO area) throws BusinessLogicException {
         empleadoLogic.updateAreaDeConocimiento(areasdeconocimientoId, empleadoId, area.toEntity());
         return area;
    }
    
    @DELETE
    @Path("{empleadoId: \\d+}/areasdeconocimiento/{areasdeconocimientoId: \\d+}")
    public void removeAreaDeconocimiento(@PathParam("empleadoId") Long empleadoId, @PathParam("areasdeconocimientoId") Long areasdeconocimientoId) throws BusinessLogicException {
        empleadoLogic.removeAreaDeConocimiento(areasdeconocimientoId,empleadoId);
    }
    
    private List<EmpleadoDetailDTO> listEntity2DetailDTO(List<EmpleadoEntity> entityList) {
        List<EmpleadoDetailDTO> list = new ArrayList<>();
        for (EmpleadoEntity entity : entityList) {
            list.add(new EmpleadoDetailDTO(entity));
        }
        return list;
    }
    
    //HOJA DE VIDA
    
    @GET
    @Path("{empleadoId: \\d+}/hojadevida}")
    public HojaDeVidaDetailedDTO getHojaDeVida(@PathParam("empleadoId") Long empleadoId) throws BusinessLogicException{
       return new HojaDeVidaDetailedDTO(empleadoLogic.getHojaDeVida(empleadoId));
    }
    
    @PUT
    @Path("{empleadoId: \\d+}/hojadevida}")
    public HojaDeVidaDetailedDTO replaceHojaDeVida(@PathParam("empleadoId") Long empleadoId, HojaDeVidaDetailedDTO hoja) throws BusinessLogicException{
        return new HojaDeVidaDetailedDTO(empleadoLogic.updateHojaDeVida(empleadoId, hoja.toEntity()));
    }
    @DELETE
    @Path("{empleadoId: \\d+}/hojadevida}")
    public void removeHojaDeVida(@PathParam("empleadoId") Long empleadoId) throws BusinessLogicException{
        empleadoLogic.removeHojaDeVida(empleadoId);
    }
}