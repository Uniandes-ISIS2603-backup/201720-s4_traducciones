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
    
    public List<EmpleadoEntity> getEmpleados() {
        LOGGER.info("Inicia proceso de consultar todos los empleados");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<EmpleadoEntity> empleados = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los empleados");
        return empleados;
    }
    
    public EmpleadoEntity getEmpleado(Long id) {
        return persistence.find(id);
    }
    
    
    
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
    
    public void deleteEmpleado(EmpleadoEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar empleado con id={0}", entity.getId());
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar empleado con id={0}", entity.getId());
        
    }
    //Metodos AreaDeConocimiento
    
    public AreaDeConocimientoEntity getAreaDeConocimiento(Long empleadoId, Long areadeconocimientoId) throws BusinessLogicException {
        List<AreaDeConocimientoEntity> areas = getEmpleado(empleadoId).getAreasdeconocimiento();
        for(int i = 0; i<areas.size(); i++){
            if(Objects.equals(areas.get(i).getId(), areadeconocimientoId)){
                return areas.get(i);
            }
        }
        throw new BusinessLogicException("El empleado no tiene un area de conocimiento con el id: " + areadeconocimientoId);
    }
    
    public List<AreaDeConocimientoEntity> getAreasDeConocimiento(Long empleadoId) throws BusinessLogicException {
        if(getEmpleado(empleadoId) == null){
            throw new BusinessLogicException("El empleado no existe");
        }
        else{
            return getEmpleado(empleadoId).getAreasdeconocimiento();
        }
    }
    
    public AreaDeConocimientoEntity addAreaDeConocimiento(AreaDeConocimientoEntity area, Long empleadoId) throws BusinessLogicException {
        area.setEmpleado(getEmpleado(empleadoId));
        areaDeConocimientoLogic.createAreaDeConocimiento(area);
        getEmpleado(empleadoId).getAreasdeconocimiento().add(area);
        return area;
    }
    
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
    
    public HojaDeVidaEntity updateHojaDeVida(Long empleadoId, HojaDeVidaEntity entity) throws BusinessLogicException {
        if(getEmpleado(empleadoId).getHojadevida() == null){
            throw new BusinessLogicException("El empleado no tiene una hoja de vida asociada");
        }
        return hojaDeVidaLogic.updateHojaDeVida(getEmpleado(empleadoId).getHojadevida().get(0).getId(), entity);
    }
    
    public void removeHojaDeVida(Long empleadoId) throws BusinessLogicException {
        if(getEmpleado(empleadoId).getHojadevida().isEmpty() || getEmpleado(empleadoId) == null){
            throw new BusinessLogicException("El empleado no tiene una hoja de vida asociada");
        }
        hojaDeVidaLogic.deleteHojaDeVidaId(getEmpleado(empleadoId).getHojadevida().get(0).getId());
        getEmpleado(empleadoId).setHojadevida(null);
    }
    
    //Metodos Oferta
    
    public OfertaEntity getOferta(Long empleadoId, Long ofertaId) throws BusinessLogicException {
        List<OfertaEntity> ofertas = getEmpleado(empleadoId).getOfertas();
        for(int i = 0; i<ofertas.size(); i++){
            if(Objects.equals(ofertas.get(i).getId(), ofertaId)){
                return ofertas.get(i);
            }
        }
        throw new BusinessLogicException("El empleado no tiene una oferta con el id: " + ofertaId);
    }
    
    public List<OfertaEntity> getOfertas(Long empleadoId) throws BusinessLogicException {
        if(getEmpleado(empleadoId) == null){
            throw new BusinessLogicException("El empleado no existe");
        }
        else{
            return getEmpleado(empleadoId).getOfertas();
        }
    }
    
    public OfertaEntity addOferta(OfertaEntity oferta, Long empleadoId) throws BusinessLogicException {
        oferta.setEmpleado(getEmpleado(empleadoId));
        ofertaLogic.createOferta(oferta);
        getEmpleado(empleadoId).getOfertas().add(oferta);
        return oferta;
    }
    
    public OfertaEntity updateOferta(Long ofertaId, Long empleadoId, OfertaEntity oferta) throws BusinessLogicException {
        List<OfertaEntity> ofertas = getEmpleado(empleadoId).getOfertas();
        for(int i = 0; i<ofertas.size(); i++){
            if(Objects.equals(ofertas.get(i).getId(), ofertaId)){
                ofertaLogic.updateOferta(oferta);
                return ofertas.get(i);
            }
        }
        throw new BusinessLogicException("El empleado no tiene una oferta con el id: " + ofertaId);
    }
    
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
    
    public PropuestaEntity getPropuesta(Long empleadoId, Long propuestaId) throws BusinessLogicException {
        List<PropuestaEntity> propuestas = getEmpleado(empleadoId).getPropuestas();
        for(int i = 0; i<propuestas.size(); i++){
            if(Objects.equals(propuestas.get(i).getId(), propuestaId)){
                return propuestas.get(i);
            }
        }
        throw new BusinessLogicException("El empleado no tiene una Propuesta con el id: " + propuestaId);
    }
    
    public List<PropuestaEntity> getPropuestas(Long empleadoId) throws BusinessLogicException {
        if(getEmpleado(empleadoId) == null){
            throw new BusinessLogicException("El empleado no existe");
        }
        else{
            return getEmpleado(empleadoId).getPropuestas();
        }
    }
    
    public PropuestaEntity addPropuesta(PropuestaEntity propuesta, Long empleadoId) throws BusinessLogicException {
        propuesta.setEmpleado(getEmpleado(empleadoId));
        propuestaLogic.createPropuesta(propuesta);
        getEmpleado(empleadoId).getPropuestas().add(propuesta);
        return propuesta;
    }
    
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
