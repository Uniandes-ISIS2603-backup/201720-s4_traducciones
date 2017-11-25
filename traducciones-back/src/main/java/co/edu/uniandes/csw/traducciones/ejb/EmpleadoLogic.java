package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.AreaDeConocimientoEntity;
import co.edu.uniandes.csw.traducciones.entities.EmpleadoEntity;
import co.edu.uniandes.csw.traducciones.entities.HojaDeVidaEntity;
import co.edu.uniandes.csw.traducciones.entities.OfertaEntity;
import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.EmpleadoPersistence;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jc.gloria
 */

@Stateless
public class EmpleadoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(EmpleadoLogic.class.getName());
    
    @Inject
    private EmpleadoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    @Inject
    private AreaDeConocimientoLogic areaDeConocimientoLogic;
    
    @Inject
    private HojaDeVidaLogic hojaDeVidaLogic;
    
    @Inject
    private OfertaLogic ofertaLogic;
    
    @Inject
    private PropuestaLogic propuestaLogic;
    
    
    /**
     * Agrega un empleado al sistema
     * @param entity objeto que se quiere agregar
     * @return empleado que se agrego
     * @throws BusinessLogicException si ya existe un empleado con el nombre deseado
     */
    public EmpleadoEntity createEmpleado(EmpleadoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de empleado");
        // Verifica la regla de negocio que dice que no puede haber dos empleados con el mismo nombre
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe un Empleado con el nombre \"" + entity.getName() + "\"");
        }
        // Invoca la persistencia para crear la city
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación del empleado");
        return entity;
    }
    
    /**
     * Devuelve todos los empleados en el sistema
     * @return lista de todos los empleados
     */
    public List<EmpleadoEntity> getEmpleados() {
        LOGGER.info("Inicia proceso de consultar todos los empleados");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<EmpleadoEntity> empleados = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los empleados");
        return empleados;
    }
    
    /**
     * Devuelve un empleado especifico
     * @param id id del empleado que se quiere borrar
     * @return objeto empleado deseado.
     */
    public EmpleadoEntity getEmpleado(Long id) {
        return persistence.find(id);
    }
    
    /**
     * Actualiza un empleado en el sistema
     * @param id id del empleado que se quiere actualizar
     * @param entity entidad por la que se quiere reemplazar
     * @return objeto empleado actualizado
     * @throws BusinessLogicException si se intento ponerle un nombre al empleado que ya existe.
     */
    public EmpleadoEntity updateEmpleado(Long id, EmpleadoEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar empleado con id={0}", id);
        if (persistence.existEmpleadoWithSameNameandDifferentId(id, entity.getName())) {
            throw new BusinessLogicException("Ya existe un empleado con ese nombre "+entity.getName());
        }
        entity.setId(id);
        if(entity.getCalificacionPromedio() == null){
            entity.setCalificacionPromedio(persistence.find(id).getCalificacionPromedio());
        }
        if(entity.getTipo() == null){
            entity.setTipo(persistence.find(id).getTipo());
        }
        if(entity.getName() == null){
            entity.setName(persistence.find(id).getName());
        }
        
        EmpleadoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar empleado con id={0}", entity.getId());
        return newEntity;
    }
    
    /**
     * Borra un empleado del sistema
     * @param entity entidad que se quiere borrar
     */
    public void deleteEmpleado(EmpleadoEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar empleado con id={0}", entity.getId());
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar empleado con id={0}", entity.getId());
        
    }
    //Metodos AreaDeConocimiento
    
    
    /**
     * Devuelve una area de conocimiento a partir de un id
     * @param areadeconocimientoId de la area que se quiere buscar
     * @param empleadoId id del empleado
     * @return la entidad que concuerda con el id.
     * @throws BusinessLogicException  Si no existe una area con el id pasado por parametro.
     */
    public AreaDeConocimientoEntity getAreaDeConocimiento(Long empleadoId, Long areadeconocimientoId) throws BusinessLogicException {
        List<AreaDeConocimientoEntity> areas = getEmpleado(empleadoId).getAreasdeconocimiento();
        for(int i = 0; i<areas.size(); i++){
            if(Objects.equals(areas.get(i).getId(), areadeconocimientoId)){
                return areas.get(i);
            }
        }
        throw new BusinessLogicException("El empleado no tiene un area de conocimiento con el id: " + areadeconocimientoId);
    }
    
    /**
     * Devuelve todas las areas de conocimiento en el sistema
     * @param empleadoId id del empleado
     * @return una lista con objetos de tipo entidad.
     * @throws BusinessLogicException si el empleado no existe
     */
    public List<AreaDeConocimientoEntity> getAreasDeConocimiento(Long empleadoId) throws BusinessLogicException {
        if(getEmpleado(empleadoId) == null){
            throw new BusinessLogicException("El empleado no existe");
        }
        else{
            return getEmpleado(empleadoId).getAreasdeconocimiento();
        }
    }
    /**
     * Agrega una area de conocimiento a un empleado
     * 
     */
    public AreaDeConocimientoEntity addAreaDeConocimiento(AreaDeConocimientoEntity area, Long empleadoId) throws BusinessLogicException {
        area.setEmpleado(getEmpleado(empleadoId));
        areaDeConocimientoLogic.createAreaDeConocimiento(area);
        getEmpleado(empleadoId).getAreasdeconocimiento().add(area);
        return area;
    }
    
    /**
     * Actualiza un area de conocimiento
     * @param areaId objeto area que se quiere reemplazar
     * @param empleadoId id del empleado al que se le quiere reemplazar un area
     * @param area area por la cual se quiere reemplazar
     * @return objeto entidad que se agrego
     * @throws BusinessLogicException si no existe un area con ese id
     */
    public AreaDeConocimientoEntity updateAreaDeConocimiento(Long areaId, Long empleadoId, AreaDeConocimientoEntity area) throws BusinessLogicException {
        List<AreaDeConocimientoEntity> areas = getEmpleado(empleadoId).getAreasdeconocimiento();
        for(int i = 0; i<areas.size(); i++){
            if(Objects.equals(areas.get(i).getId(), areaId)){
                areaDeConocimientoLogic.updateAreaDeConocimiento(areaId, area);
                return areas.get(i);
            }
        }
        throw new BusinessLogicException("El empleado no tiene un area de conocimiento con el id: " + areaId);
    }
    
    /**
     * Borra un area de conocimiento del sistema
     * @param areaDeConocimientoId id del area que se desea borrar
     * @param empleadoId id del empleado que tiene esa area
     * @throws BusinessLogicException si no existe un area con ese id.
     */
    public void removeAreaDeConocimiento(Long areaDeConocimientoId, Long empleadoId) throws BusinessLogicException {
        List<AreaDeConocimientoEntity> areas = getEmpleado(empleadoId).getAreasdeconocimiento();
        boolean seBorro = false;
        for(int i = 0; i<areas.size() && !seBorro; i++){
            if(Objects.equals(areas.get(i).getId(), areaDeConocimientoId)){
                areaDeConocimientoLogic.deleteAreaDeConocimiento(areas.get(i));
                getEmpleado(empleadoId).getAreasdeconocimiento().remove(i);
                seBorro = true;
            }
        }
        if(!seBorro){
            throw new BusinessLogicException("Este empleado no tiene un area de conocimiento con el id:" + areaDeConocimientoId);
        }
    }
    
    //Metodos HojaDeVida
    
    /**
     * Se agrega una hoja de vida
     * @param hoja la hoja de vida que se agregara
     * @param empleadoId id del empleado al que se le agregara la hoja de vida
     * @return entidad e la hoja de vida que se agreggo
     * @throws BusinessLogicException si el empleado ya tiene una hoja de vida
     */
    public HojaDeVidaEntity addHojaDeVida(HojaDeVidaEntity hoja, Long empleadoId) throws BusinessLogicException {
        if(!getEmpleado(empleadoId).getHojadevida().isEmpty()){
            throw new BusinessLogicException("Este empleado ya tiene una hoja de vida asociada");
        }
        hoja.setEmpleado(getEmpleado(empleadoId));
        hojaDeVidaLogic.createHojaDeVida(hoja);
        List<HojaDeVidaEntity> hojadevida = new ArrayList<>();
        hojadevida.add(hoja);
        getEmpleado(empleadoId).setHojadevida(hojadevida);
        return hoja;
    }
    
    /**
     * Actualiza una hoja de vida
     * @param empleadoId id del empleado al que se le quiere actualziar la hoja de vida.
     * @param entity entidad por la cual se va a reemplazar
     * @return la hojda de vida actualizada
     * @throws BusinessLogicException si el empelado no tiene hoja de vida.
     */
    public HojaDeVidaEntity updateHojaDeVida(Long empleadoId, HojaDeVidaEntity entity) throws BusinessLogicException {
        if(getEmpleado(empleadoId).getHojadevida() == null){
            throw new BusinessLogicException("El empleado no tiene una hoja de vida asociada");
        }
        entity.setEmpleado(getEmpleado(empleadoId));
        return hojaDeVidaLogic.updateHojaDeVida(getEmpleado(empleadoId).getHojadevida().get(0).getId(), entity);
    }
    
    /**
     * Borrar una hoja de vida
     * @param empleadoId id del empleado al que se le quiere borrar la hoja de vida
     * @throws BusinessLogicException si el empleado no tiene una hoja de vida asociada
     */
    public void removeHojaDeVida(Long empleadoId) throws BusinessLogicException {
        if(getEmpleado(empleadoId).getHojadevida().isEmpty() || getEmpleado(empleadoId) == null){
            throw new BusinessLogicException("El empleado no tiene una hoja de vida asociada");
        }
        hojaDeVidaLogic.deleteHojaDeVidaId(getEmpleado(empleadoId).getHojadevida().get(0).getId());
        getEmpleado(empleadoId).setHojadevida(null);
    }
    
    //Metodos Oferta
    
    /**
     * Obtiene una oferta del empleado
     * @param empleadoId id del empleado con la oferta deseada
     * @param ofertaId id de la oferta deseada
     * @return objeto oferta
     * @throws BusinessLogicException si no existe una oferta con el id deseado
     */
    public OfertaEntity getOferta(Long empleadoId, Long ofertaId) throws BusinessLogicException {
        List<OfertaEntity> ofertas = getEmpleado(empleadoId).getOfertas();
        for(int i = 0; i<ofertas.size(); i++){
            if(Objects.equals(ofertas.get(i).getId(), ofertaId)){
                return ofertas.get(i);
            }
        }
        throw new BusinessLogicException("El empleado no tiene una oferta con el id: " + ofertaId);
    }
    
    /**
     * Devuelve todas las ofertas de un empleado
     * @param empleadoId id del empleado
     * @return una lista con todas las ofertas que tiene el empleado deseado.
     * @throws BusinessLogicException  si el empleado con el id dado no existe.
     */
    public List<OfertaEntity> getOfertas(Long empleadoId) throws BusinessLogicException {
        if(getEmpleado(empleadoId) == null){
            throw new BusinessLogicException("El empleado no existe");
        }
        else{
            return getEmpleado(empleadoId).getOfertas();
        }
    }
    
    /**
     * Agrega una oferta a un empelado
     * @param oferta Oferta que se quiere agregar
     * @param empleadoId id del empleado al que se le quiere agregar la ofeta
     * @return objeto oferta que se agrego
     * @throws BusinessLogicException si el empleado dado no existe.
     */
    public OfertaEntity addOferta(OfertaEntity oferta, Long empleadoId) throws BusinessLogicException {
        oferta.setEmpleado(getEmpleado(empleadoId));
        ofertaLogic.createOferta(oferta);
        getEmpleado(empleadoId).getOfertas().add(oferta);
        return oferta;
    }
    
    /**
     * Actualiza una oferta de un empleado
     * @param ofertaId oferta que se quiere reemplazar
     * @param empleadoId id del empleado que posee esa oferta
     * @param oferta objeto oferta por el cual se quiere reemplazar la actual.
     * @return oferta ya reemplazada
     * @throws BusinessLogicException Si no existe un empleado y/o una oferta dado el id.
     */
    public OfertaEntity updateOferta(Long ofertaId, Long empleadoId, OfertaEntity oferta) throws BusinessLogicException {
        List<OfertaEntity> ofertas = getEmpleado(empleadoId).getOfertas();
        for(int i = 0; i<ofertas.size(); i++){
            if(Objects.equals(ofertas.get(i).getId(), ofertaId)){
                ofertaLogic.updateOferta(ofertaId,oferta);
                return ofertas.get(i);
            }
        }
        throw new BusinessLogicException("El empleado no tiene una oferta con el id: " + ofertaId);
    }
    
    /**
     * Borra una oferta del sistema
     * @param ofertaId id de la oferta deseada.
     * @param empleadoId id del empleado que tiene la oferta
     * @throws BusinessLogicException si el id de la oferta y/o el empleado no existen
     */
    public void removeOferta(Long ofertaId, Long empleadoId) throws BusinessLogicException {
        List<OfertaEntity> ofertas = getEmpleado(empleadoId).getOfertas();
        boolean seBorro = false;
        for(int i = 0; i<ofertas.size() && !seBorro; i++){
            if(Objects.equals(ofertas.get(i).getId(), ofertaId)){
                ofertaLogic.deleteOferta(ofertaId);
                getEmpleado(empleadoId).getOfertas().remove(i);
                seBorro = true;
            }
        }
        if(!seBorro){
            throw new BusinessLogicException("Este empleado no tiene una oferta con el id:" + ofertaId);
        }
    }
    
    //Metodos Propuesta
    
    /**
     * Devuelve una propuesta de un empleado
     * @param empleadoId id del empleado
     * @param propuestaId id de la propuesta
     * @return propuesta deseada
     * @throws BusinessLogicException si el empleado o la propuesta no existen dado los id.
     */
    public PropuestaEntity getPropuesta(Long empleadoId, Long propuestaId) throws BusinessLogicException {
        List<PropuestaEntity> propuestas = getEmpleado(empleadoId).getPropuestas();
        for(int i = 0; i<propuestas.size(); i++){
            if(Objects.equals(propuestas.get(i).getId(), propuestaId)){
                return propuestas.get(i);
            }
        }
        throw new BusinessLogicException("El empleado no tiene una Propuesta con el id: " + propuestaId);
    }
    
    /**
     * Devuelve las propeustas de un empleado
     * @param empleadoId id del empleado
     * @return una lista con todas las propuestas que tiene el empleado
     * @throws BusinessLogicException si el empleado no existe
     */
    public List<PropuestaEntity> getPropuestas(Long empleadoId) throws BusinessLogicException {
        if(getEmpleado(empleadoId) == null){
            throw new BusinessLogicException("El empleado no existe");
        }
        else{
            return getEmpleado(empleadoId).getPropuestas();
        }
    }
    
    /**
     * Agregar una propuesta a un empleado
     * @param propuesta propuesta ques e queire agregar
     * @param empleadoId id del empleado al que se le quiere agregar la propuesta
     * @return la propuesta que se agrego
     * @throws BusinessLogicException
     */
    public PropuestaEntity addPropuesta(PropuestaEntity propuesta, Long empleadoId) throws BusinessLogicException {
        propuesta.setEmpleado(getEmpleado(empleadoId));
        propuestaLogic.createPropuesta(propuesta);
        getEmpleado(empleadoId).getPropuestas().add(propuesta);
        return propuesta;
    }
    
    /**
     * Actualiza una propuesta de un empleado
     * @param propuestaId id de la propuesta
     * @param empleadoId id del empleado
     * @param propuesta propeusta por la cual se quiere reemplazar la actual
     * @return la propuesta actualizada
     * @throws BusinessLogicException si no existe la propusta dado el id que se paso o el empleado
     */
    public PropuestaEntity updatePropuesta(Long propuestaId, Long empleadoId, PropuestaEntity propuesta) throws BusinessLogicException {
        List<PropuestaEntity> propuestas = getEmpleado(empleadoId).getPropuestas();
        for(int i = 0; i<propuestas.size(); i++){
            if(Objects.equals(propuestas.get(i).getId(), propuestaId)){
                propuestaLogic.updatePropuesta(propuesta);
                return propuestas.get(i);
            }
        }
        throw new BusinessLogicException("El empleado no tiene una Propuesta con el id: " + propuestaId);
    }
    
    /**
     * Borrar una propuesta del sistema
     * @param propuestaId id de la propuesta
     * @param empleadoId id de el empleado
     * @throws BusinessLogicException Si no existe una propuesta o un empleado dado el id.
     */
    public void removePropuesta(Long propuestaId, Long empleadoId) throws BusinessLogicException {
        List<PropuestaEntity> propuestas = getEmpleado(empleadoId).getPropuestas();
        boolean seBorro = false;
        for(int i = 0; i<propuestas.size() && !seBorro; i++){
            if(Objects.equals(propuestas.get(i).getId(), propuestaId)){
                propuestaLogic.deletePropuesta(propuestaId);
                getEmpleado(empleadoId).getPropuestas().remove(i);
                seBorro = true;
            }
        }
        if(!seBorro){
            throw new BusinessLogicException("Este empleado no tiene una Propuesta con el id:" + propuestaId);
        }
    }
    
}
