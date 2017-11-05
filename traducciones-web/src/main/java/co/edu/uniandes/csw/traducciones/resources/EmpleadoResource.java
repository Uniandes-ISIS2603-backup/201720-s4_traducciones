package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.AreaDeConocimientoDetailDTO;
import co.edu.uniandes.csw.traducciones.dtos.EmpleadoDetailDTO;
import co.edu.uniandes.csw.traducciones.dtos.HojaDeVidaDTO;
import co.edu.uniandes.csw.traducciones.dtos.HojaDeVidaDetailedDTO;
import co.edu.uniandes.csw.traducciones.dtos.OfertaDTO;
import co.edu.uniandes.csw.traducciones.dtos.PropuestaDetailDTO;
import co.edu.uniandes.csw.traducciones.ejb.EmpleadoLogic;
import co.edu.uniandes.csw.traducciones.entities.AreaDeConocimientoEntity;
import co.edu.uniandes.csw.traducciones.entities.EmpleadoEntity;
import co.edu.uniandes.csw.traducciones.entities.OfertaEntity;
import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;
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
        EmpleadoEntity entity = empleadoLogic.getEmpleado(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso empleado: " + id + " no existe.", 404);
        }
        empleadoLogic.deleteEmpleado(entity);
    }
    
    //Convertidores de Listas
    
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
    
    private List<PropuestaDetailDTO> propuestasListEntity2DTO(List<PropuestaEntity> entityList) {
        List<PropuestaDetailDTO> list = new ArrayList<>();
        for (PropuestaEntity entity : entityList) {
            list.add(new PropuestaDetailDTO(entity));
        }
        return list;
    }
    
    private List<PropuestaEntity> propuestasListDTO2Entity(List<PropuestaDetailDTO> dtos) {
        List<PropuestaEntity> list = new ArrayList<>();
        for (PropuestaDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    private List<OfertaDTO> ofertasListEntity2DTO(List<OfertaEntity> entityList) {
        List<OfertaDTO> list = new ArrayList<>();
        for (OfertaEntity entity : entityList) {
            list.add(new OfertaDTO(entity));
        }
        return list;
    }
    
    private List<OfertaEntity> ofertasListDTO2Entity(List<OfertaDTO> dtos) {
        List<OfertaEntity> list = new ArrayList<>();
        for (OfertaDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    
    //AREA DE CONOCIMIENTO
    
    
    @GET
    @Path("{empleadoId: \\d+}/areasdeconocimiento")
    public List<AreaDeConocimientoDetailDTO> listAreasDeConocimiento(@PathParam("empleadoId") Long empleadoId) throws BusinessLogicException {
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
    public AreaDeConocimientoDetailDTO addAreaDeConocimiento(@PathParam("empleadoId") Long empleadoId, AreaDeConocimientoDetailDTO area) throws BusinessLogicException {
       return new AreaDeConocimientoDetailDTO(empleadoLogic.addAreaDeConocimiento(area.toEntity(),empleadoId));
        
    }
    
    @PUT
    @Path("{empleadoId: \\d+}/areasdeconocimiento/{areasdeconocimientoId: \\d+}")
    public AreaDeConocimientoDetailDTO replaceAreaDeConocimiento(@PathParam("empleadoId") Long empleadoId, @PathParam("areasdeconocimientoId") Long areasdeconocimientoId, AreaDeConocimientoDetailDTO area) throws BusinessLogicException {
        return new AreaDeConocimientoDetailDTO(empleadoLogic.updateAreaDeConocimiento(areasdeconocimientoId, empleadoId, area.toEntity()));
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
    
    //PROPUESTA
    
    
    @GET
    @Path("{empleadoId: \\d+}/propuestas")
    public List<PropuestaDetailDTO> listPropuestas(@PathParam("empleadoId") Long empleadoId) throws BusinessLogicException {
        if(empleadoLogic.getPropuestas(empleadoId).isEmpty()){
            throw new WebApplicationException("Este empleado no tiene propuestas asociadas", 404);
        }
        return propuestasListEntity2DTO(empleadoLogic.getPropuestas(empleadoId));
    }
    
    @GET
    @Path("{empleadoId: \\d+}/propuestas/{propuestasId: \\d+}")
    public PropuestaDetailDTO getPropuesta(@PathParam("empleadoId") Long empleadoId, @PathParam("propuestasId") Long propuestasId) throws BusinessLogicException {
        return new PropuestaDetailDTO(empleadoLogic.getPropuesta(empleadoId, propuestasId));
    }
    
    @POST
    @Path("{empleadoId: \\d+}/propuestas")
    public PropuestaDetailDTO addPropuesta(@PathParam("empleadoId") Long empleadoId, PropuestaDetailDTO propuesta) throws BusinessLogicException {
       return new PropuestaDetailDTO(empleadoLogic.addPropuesta(propuesta.toEntity(),empleadoId));
        
    }
    
    @PUT
    @Path("{empleadoId: \\d+}/propuestas/{propuestasId: \\d+}")
    public PropuestaDetailDTO replacePropuesta(@PathParam("empleadoId") Long empleadoId, @PathParam("propuestasId") Long propuestasId, PropuestaDetailDTO propuesta) throws BusinessLogicException {
        return new PropuestaDetailDTO(empleadoLogic.updatePropuesta(propuestasId, empleadoId, propuesta.toEntity()));
    }
    
    @DELETE
    @Path("{empleadoId: \\d+}/propuestas/{propuestasId: \\d+}")
    public void removePropuesta(@PathParam("empleadoId") Long empleadoId, @PathParam("propuestasId") Long propuestasId) throws BusinessLogicException {
        empleadoLogic.removePropuesta(propuestasId, empleadoId);
    }
    
    //OFERTA
    
    
    @GET
    @Path("{empleadoId: \\d+}/ofertas")
    public List<OfertaDTO> listOfertas(@PathParam("empleadoId") Long empleadoId) throws BusinessLogicException {
        if(empleadoLogic.getOfertas(empleadoId).isEmpty()){
            throw new WebApplicationException("Este empleado no tiene ofertas asociadas", 404);
        }
        return ofertasListEntity2DTO(empleadoLogic.getOfertas(empleadoId));
    }
    
    @GET
    @Path("{empleadoId: \\d+}/ofertas/{ofertasId: \\d+}")
    public OfertaDTO getOferta(@PathParam("empleadoId") Long empleadoId, @PathParam("ofertasId") Long ofertasId) throws BusinessLogicException {
        return new OfertaDTO(empleadoLogic.getOferta(empleadoId, ofertasId));
    }
    
    @POST
    @Path("{empleadoId: \\d+}/ofertas")
    public OfertaDTO addOferta(@PathParam("empleadoId") Long empleadoId, OfertaDTO oferta) throws BusinessLogicException, Exception {
       return new OfertaDTO(empleadoLogic.addOferta(oferta.toEntity(),empleadoId));
        
    }
    
    @PUT
    @Path("{empleadoId: \\d+}/ofertas/{ofertasId: \\d+}")
    public OfertaDTO replaceOferta(@PathParam("empleadoId") Long empleadoId, @PathParam("ofertasId") Long ofertasId, OfertaDTO oferta) throws BusinessLogicException {
        return new OfertaDTO(empleadoLogic.updateOferta(ofertasId, empleadoId, oferta.toEntity()));
    }
    
    @DELETE
    @Path("{empleadoId: \\d+}/ofertas/{ofertasId: \\d+}")
    public void removeOferta(@PathParam("empleadoId") Long empleadoId, @PathParam("ofertasId") Long ofertasId) throws BusinessLogicException {
        empleadoLogic.removeOferta(ofertasId,empleadoId);
    }

    

    
    //HOJA DE VIDA
    
    
    @PUT
    @Path("{empleadoId: \\d+}/hojadevida")
    public HojaDeVidaDetailedDTO replaceHojaDeVida(@PathParam("empleadoId") Long empleadoId, HojaDeVidaDetailedDTO hoja) throws BusinessLogicException{
        return new HojaDeVidaDetailedDTO(empleadoLogic.updateHojaDeVida(empleadoId, hoja.toEntity()));
    }
    
    @POST
    @Path("{empleadoId: \\d+}/hojadevida")
    public HojaDeVidaDetailedDTO addHojaDeVida(@PathParam("empleadoId") Long empleadoId, HojaDeVidaDetailedDTO hoja) throws BusinessLogicException{
        return new HojaDeVidaDetailedDTO(empleadoLogic.addHojaDeVida(hoja.toEntity(), empleadoId));
    }
    
    @DELETE
    @Path("{empleadoId: \\d+}/hojadevida")
    public void removeHojaDeVida(@PathParam("empleadoId") Long empleadoId) throws BusinessLogicException{
        empleadoLogic.removeHojaDeVida(empleadoId);
    }
    
    /**
     *
     * @param empleadoId
     * @return
     */
    @GET
    @Path("{empleadoId: \\d+}/hojadevida")
    public HojaDeVidaDTO getHojaDeVida(@PathParam("empleadoId") Long empleadoId)
    {
        EmpleadoEntity entity = empleadoLogic.getEmpleado(empleadoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso empleado: " + empleadoId + " no existe.", 404);
        }
        return (new EmpleadoDetailDTO(entity)).getHojadevida().get(0);
    }
}