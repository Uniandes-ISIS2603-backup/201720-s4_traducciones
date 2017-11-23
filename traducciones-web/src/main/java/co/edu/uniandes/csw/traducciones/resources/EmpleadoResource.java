package co.edu.uniandes.csw.traducciones.resources;

import co.edu.uniandes.csw.traducciones.dtos.AreaDeConocimientoDetailDTO;
import co.edu.uniandes.csw.traducciones.dtos.EmpleadoDetailDTO;
import co.edu.uniandes.csw.traducciones.dtos.HojaDeVidaDTO;
import co.edu.uniandes.csw.traducciones.dtos.HojaDeVidaDetailedDTO;
import co.edu.uniandes.csw.traducciones.dtos.OfertaDTO;
import co.edu.uniandes.csw.traducciones.dtos.OfertaDetailDTO;
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
    
    /**
     * Devuelve todos los empleados en el sistema
     * @return lista de todos los empleados
     */
    @GET
    public List<EmpleadoDetailDTO> getEmpleados() {
        return listEntity2DetailDTO(empleadoLogic.getEmpleados());
    }
    
    /**
     * Devuelve un empleado especifico
     * @param id id del empleado que se quiere borrar
     * @return objeto empleado deseado.
     */
    @GET
    @Path("{id: \\d+}")
    public EmpleadoDetailDTO getEmpleado(@PathParam("id") Long id) {
        EmpleadoEntity entity = empleadoLogic.getEmpleado(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso empleado: " + id + " no existe.", 404);
        }
        return new EmpleadoDetailDTO(entity);
    }
    
    /**
     * Agrega un empleado al sistema
     * @param empleado objeto que se quiere agregar
     * @return empleado que se agrego
     * @throws BusinessLogicException si ya existe un empleado con el nombre deseado
     */
    @POST
    public EmpleadoDetailDTO createEmpleado(EmpleadoDetailDTO empleado ) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        EmpleadoEntity empleadoEntity = empleado.toEntity();
        // Invoca la lógica para crear el empleado nuevo
        EmpleadoEntity nuevoEmpleado = empleadoLogic.createEmpleado(empleadoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new EmpleadoDetailDTO(nuevoEmpleado);
    }
    /**
     * Actualiza un empleado en el sistema
     * @param id id del empleado que se quiere actualizar
     * @param empleado entidad por la que se quiere reemplazar
     * @return objeto empleado actualizado
     * @throws BusinessLogicException si se intento ponerle un nombre al empleado que ya existe.
     */
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
    /**
     * Borra un empleado del sistema
     * @param id empleado que se quiere borrar
     */
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
    
    /**
     * Convierte una lista de areas de conocimiento de entidad a DTO
     * @param entityList lista en forma de entidad
     * @return la misma lista pasada por parametro en forma de DTO
     */
    private List<AreaDeConocimientoDetailDTO> areasdeconocimientoListEntity2DTO(List<AreaDeConocimientoEntity> entityList) {
        List<AreaDeConocimientoDetailDTO> list = new ArrayList<AreaDeConocimientoDetailDTO>();
        for (AreaDeConocimientoEntity entity : entityList) {
            list.add(new AreaDeConocimientoDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Convierte una lista de areas de conocimiento de DTO a Entidad
     * @param dtos lista en forma de DTO
     * @return la misma lista pasada por parametro en forma de entidad
     */
    private List<AreaDeConocimientoEntity> areasdeconocimientoListDTO2Entity(List<AreaDeConocimientoDetailDTO> dtos) {
        List<AreaDeConocimientoEntity> list = new ArrayList<>();
        for (AreaDeConocimientoDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    /**
     * Convierte una lista de propuestas de entidad a DTO
     * @param entityList lista en forma de entidad
     * @return la misma lista pasada por parametro en forma de DTO
     */
    private List<PropuestaDetailDTO> propuestasListEntity2DTO(List<PropuestaEntity> entityList) {
        List<PropuestaDetailDTO> list = new ArrayList<>();
        for (PropuestaEntity entity : entityList) {
            list.add(new PropuestaDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Convierte una lista de propuestas de DTO a Entidad
     * @param dtos lista en forma de DTO
     * @return la misma lista pasada por parametro en forma de entidad
     */
    private List<PropuestaEntity> propuestasListDTO2Entity(List<PropuestaDetailDTO> dtos) {
        List<PropuestaEntity> list = new ArrayList<>();
        for (PropuestaDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    /**
     * Convierte una lista de ofertas de entidad a DTO
     * @param entityList lista en forma de entidad
     * @return la misma lista pasada por parametro en forma de DTO
     */
    private List<OfertaDTO> ofertasListEntity2DTO(List<OfertaEntity> entityList) {
        List<OfertaDTO> list = new ArrayList<>();
        for (OfertaEntity entity : entityList) {
            list.add(new OfertaDTO(entity));
        }
        return list;
    }
    
    /**
     * Convierte una lista de ofertas de DTO a Entidad
     * @param dtos lista en forma de DTO
     * @return la misma lista pasada por parametro en forma de entidad
     */
    private List<OfertaEntity> ofertasListDTO2Entity(List<OfertaDTO> dtos) {
        List<OfertaEntity> list = new ArrayList<>();
        for (OfertaDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    /**
     * Convierte una lista de empleadosde entidad a DTO
     * @param entityList lista en forma de entidad
     * @return la misma lista pasada por parametro en forma de DTO
     */
    private List<EmpleadoDetailDTO> listEntity2DetailDTO(List<EmpleadoEntity> entityList) {
        List<EmpleadoDetailDTO> list = new ArrayList<>();
        for (EmpleadoEntity entity : entityList) {
            list.add(new EmpleadoDetailDTO(entity));
        }
        return list;
    }
    
    
    //AREA DE CONOCIMIENTO
    
    /**
     * Devuelve una lista con todas las areas de un empleado
     * @param empleadoId id del empleado
     * @return lista con objetos de tipo area que corresponden a un empleado
     * @throws BusinessLogicException si el id pasado no corresponde a algun empleado
     */
    @GET
    @Path("{empleadoId: \\d+}/areasdeconocimiento")
    public List<AreaDeConocimientoDetailDTO> listAreasDeConocimiento(@PathParam("empleadoId") Long empleadoId) throws BusinessLogicException {
        if(empleadoLogic.getAreasDeConocimiento(empleadoId).isEmpty()){
            throw new WebApplicationException("Este empleado no tiene areas de conocimiento asociadas", 404);
        }
        return areasdeconocimientoListEntity2DTO(empleadoLogic.getAreasDeConocimiento(empleadoId));
    }
    
    /**
     * Obtiene un area de algun empleado
     * @param empleadoId id del empleado
     * @param areasdeconocimientoId id del area
     * @return la oferta correspondiente a ese id
     * @throws BusinessLogicException si el id no corresponse a alguna area o empleado
     */
    @GET
    @Path("{empleadoId: \\d+}/areasdeconocimiento/{areasdeconocimientoId: \\d+}")
    public AreaDeConocimientoDetailDTO getAreaDeConocimiento(@PathParam("empleadoId") Long empleadoId, @PathParam("areasdeconocimientoId") Long areasdeconocimientoId) throws BusinessLogicException {
        return new AreaDeConocimientoDetailDTO(empleadoLogic.getAreaDeConocimiento(empleadoId, areasdeconocimientoId));
    }
    
    /**
     * Agrega una area de conocimiento a algun empleado
     * @param empleadoId id del empleado
     * @param area objeto area que se agregara
     * @return objeto area que se agrego
     * @throws BusinessLogicException si el empleado deseado no existe
     */
    @POST
    @Path("{empleadoId: \\d+}/areasdeconocimiento")
    public AreaDeConocimientoDetailDTO addAreaDeConocimiento(@PathParam("empleadoId") Long empleadoId, AreaDeConocimientoDetailDTO area) throws BusinessLogicException {
        return new AreaDeConocimientoDetailDTO(empleadoLogic.addAreaDeConocimiento(area.toEntity(),empleadoId));
        
    }
    
    /**
     * Reemplaza una area de conocimiento de algun empleado
     * @param empleadoId id del empleado
     * @param areasdeconocimientoId id del area
     * @param area objeto area por el cual se reemplazara el actual
     * @return objeto ya reemplazado
     * @throws BusinessLogicException si algun id no corresponde a un empleado o area.
     */
    @PUT
    @Path("{empleadoId: \\d+}/areasdeconocimiento/{areasdeconocimientoId: \\d+}")
    public AreaDeConocimientoDetailDTO replaceAreaDeConocimiento(@PathParam("empleadoId") Long empleadoId, @PathParam("areasdeconocimientoId") Long areasdeconocimientoId, AreaDeConocimientoDetailDTO area) throws BusinessLogicException {
        return new AreaDeConocimientoDetailDTO(empleadoLogic.updateAreaDeConocimiento(areasdeconocimientoId, empleadoId, area.toEntity()));
    }
    
    /**
     * Elimina un area de conocimiento de algun empleado
     * @param empleadoId id del empleado
     * @param areasdeconocimientoId id del area
     * @throws BusinessLogicException si el id no corresponde a un empleado o area
     */
    @DELETE
    @Path("{empleadoId: \\d+}/areasdeconocimiento/{areasdeconocimientoId: \\d+}")
    public void removeAreaDeconocimiento(@PathParam("empleadoId") Long empleadoId, @PathParam("areasdeconocimientoId") Long areasdeconocimientoId) throws BusinessLogicException {
        empleadoLogic.removeAreaDeConocimiento(areasdeconocimientoId,empleadoId);
    }
    
    
    
    //PROPUESTA
    
    /**
     * Devuelve una lista con todas las propuestas de un empleado
     * @param empleadoId id del empleado
     * @return lista con objetos de tipo propuesta que corresponden a un empleado
     * @throws BusinessLogicException si el id pasado no corresponde a algun empleado
     */
    @GET
    @Path("{empleadoId: \\d+}/propuestas")
    public List<PropuestaDetailDTO> listPropuestas(@PathParam("empleadoId") Long empleadoId) throws BusinessLogicException {
        if(empleadoLogic.getPropuestas(empleadoId).isEmpty()){
            throw new WebApplicationException("Este empleado no tiene propuestas asociadas", 404);
        }
        return propuestasListEntity2DTO(empleadoLogic.getPropuestas(empleadoId));
    }
    
    /**
     * Obtiene una propuesta de algun empleado
     * @param empleadoId id del empleado
     * @param propuestasId id de la propuesta
     * @return la propuesta correspondiente a ese id
     * @throws BusinessLogicException si el id no corresponse a alguna propuesta o empleado
     */
    @GET
    @Path("{empleadoId: \\d+}/propuestas/{propuestasId: \\d+}")
    public PropuestaDetailDTO getPropuesta(@PathParam("empleadoId") Long empleadoId, @PathParam("propuestasId") Long propuestasId) throws BusinessLogicException {
        return new PropuestaDetailDTO(empleadoLogic.getPropuesta(empleadoId, propuestasId));
    }
    
    /**
     * Agrega una propuesta a un empleado
     * @param empleadoId id del empleado
     * @param propuesta objeto propuesta que se agregara
     * @return propuesta agregada
     * @throws BusinessLogicException si el id del empleado es invalido.
     */
    @POST
    @Path("{empleadoId: \\d+}/propuestas")
    public PropuestaDetailDTO addPropuesta(@PathParam("empleadoId") Long empleadoId, PropuestaDetailDTO propuesta) throws BusinessLogicException {
        return new PropuestaDetailDTO(empleadoLogic.addPropuesta(propuesta.toEntity(),empleadoId));
        
    }
    
    /**
     * Reemplaza una propuesta de un empleado
     * @param empleadoId id del empleado
     * @param propuestasId id de la propuesta
     * @param propuesta objeto propuesta que se reemplazara
     * @return objeto propuesta ya reemplazado
     * @throws BusinessLogicException si algun id es invalido
     */
    @PUT
    @Path("{empleadoId: \\d+}/propuestas/{propuestasId: \\d+}")
    public PropuestaDetailDTO replacePropuesta(@PathParam("empleadoId") Long empleadoId, @PathParam("propuestasId") Long propuestasId, PropuestaDetailDTO propuesta) throws BusinessLogicException {
        return new PropuestaDetailDTO(empleadoLogic.updatePropuesta(propuestasId, empleadoId, propuesta.toEntity()));
    }
    
    /**
     * Elimina una propuesta de un empleado
     * @param empleadoId id del empleado
     * @param propuestasId id de la propuesta
     * @throws BusinessLogicException si algun id es invalido
     */
    @DELETE
    @Path("{empleadoId: \\d+}/propuestas/{propuestasId: \\d+}")
    public void removePropuesta(@PathParam("empleadoId") Long empleadoId, @PathParam("propuestasId") Long propuestasId) throws BusinessLogicException {
        empleadoLogic.removePropuesta(propuestasId, empleadoId);
    }
    
    //OFERTA
    
    /**
     * Devuelve una lista con todas las ofertas de un empleado
     * @param empleadoId id del empleado
     * @return lista con objetos de tipo oferta que corresponden a un empleado
     * @throws BusinessLogicException si el id pasado no corresponde a algun empleado
     */
    @GET
    @Path("{empleadoId: \\d+}/ofertas")
    public List<OfertaDTO> listOfertas(@PathParam("empleadoId") Long empleadoId) throws BusinessLogicException {
        if(empleadoLogic.getOfertas(empleadoId).isEmpty()){
            throw new WebApplicationException("Este empleado no tiene ofertas asociadas", 404);
        }
        return ofertasListEntity2DTO(empleadoLogic.getOfertas(empleadoId));
    }
    
    /**
     * Obtiene una oferta de algun empleado
     * @param empleadoId id del empleado
     * @param ofertasId id de la oferta
     * @return la oferta correspondiente a ese id
     * @throws BusinessLogicException si el id no corresponse a alguna oferta o empleado
     */
    @GET
    @Path("{empleadoId: \\d+}/ofertas/{ofertasId: \\d+}")
    public OfertaDTO getOferta(@PathParam("empleadoId") Long empleadoId, @PathParam("ofertasId") Long ofertasId) throws BusinessLogicException {
        return new OfertaDTO(empleadoLogic.getOferta(empleadoId, ofertasId));
    }
    
    /**
     * Agrega una oferta a un empleado
     * @param empleadoId id del empleado
     * @param oferta objeto oferta que se agregara
     * @return el objeto oferta que se agrego
     * @throws BusinessLogicException
     */
    @POST
    @Path("{empleadoId: \\d+}/ofertas")
    public OfertaDTO addOferta(@PathParam("empleadoId") Long empleadoId, OfertaDTO oferta) throws BusinessLogicException {
        return new OfertaDetailDTO(empleadoLogic.addOferta(oferta.toEntity(),empleadoId));
        
    }
    
    /**
     * Reemplazar una oferta de un empleado
     * @param empleadoId id del empleado
     * @param ofertasId id de la oferta que se quiere reemplazar
     * @param oferta objeto oferta por el cual se reemplazara la actual
     * @return la oferta reemplazada
     * @throws BusinessLogicException si el id no corresponde a alguna oferta o empleado.
     */
    @PUT
    @Path("{empleadoId: \\d+}/ofertas/{ofertasId: \\d+}")
    public OfertaDTO replaceOferta(@PathParam("empleadoId") Long empleadoId, @PathParam("ofertasId") Long ofertasId, OfertaDTO oferta) throws BusinessLogicException {
        return new OfertaDTO(empleadoLogic.updateOferta(ofertasId, empleadoId, oferta.toEntity()));
    }
    
    /**
     * Elimina una oferta de un empleado
     * @param empleadoId id del empleado al que se le borrara la oferta
     * @param ofertasId id de la oferta que se borrara.
     * @throws BusinessLogicException si el id de la oferta o empleado no corresponde a ninguna oferta o empleado.
     */
    @DELETE
    @Path("{empleadoId: \\d+}/ofertas/{ofertasId: \\d+}")
    public void removeOferta(@PathParam("empleadoId") Long empleadoId, @PathParam("ofertasId") Long ofertasId) throws BusinessLogicException {
        empleadoLogic.removeOferta(ofertasId,empleadoId);
    }
    
    //HOJA DE VIDA
    
    /**
     * Reemplaza la hoja de vida del empleado
     * @param empleadoId id del empleado al que se le va a reemplazar la hoja de vida.
     * @param hoja id de la hoja por la que se reemplazara
     * @return la hoja de vida ya reemplazada
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{empleadoId: \\d+}/hojadevida")
    public HojaDeVidaDetailedDTO replaceHojaDeVida(@PathParam("empleadoId") Long empleadoId, HojaDeVidaDetailedDTO hoja) throws BusinessLogicException{
        return new HojaDeVidaDetailedDTO(empleadoLogic.updateHojaDeVida(empleadoId, hoja.toEntity()));
    }
    
    /**
     * Agrega una hoja de vida al empleado
     * @param empleadoId id del empleado
     * @param hoja objeto hojadevida que se le agregara al empleado
     * @return hoja de vida que se agrego
     * @throws BusinessLogicException si el empleado ya tiene una hoja de vida
     */
    @POST
    @Path("{empleadoId: \\d+}/hojadevida")
    public HojaDeVidaDetailedDTO addHojaDeVida(@PathParam("empleadoId") Long empleadoId, HojaDeVidaDetailedDTO hoja) throws BusinessLogicException{
        return new HojaDeVidaDetailedDTO(empleadoLogic.addHojaDeVida(hoja.toEntity(), empleadoId));
    }
    
    /**
     * Elimina la hoja de vida del empleado
     * @param empleadoId id del empleado deseado
     * @throws BusinessLogicException si el empleado no tiene hoja de vida.
     */
    @DELETE
    @Path("{empleadoId: \\d+}/hojadevida")
    public void removeHojaDeVida(@PathParam("empleadoId") Long empleadoId) throws BusinessLogicException{
        empleadoLogic.removeHojaDeVida(empleadoId);
    }
    
    /**
     * Devuelve la hoja de vida del empleado
     * @param empleadoId id del empleado deseado
     * @return hoja de vida del empleado
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