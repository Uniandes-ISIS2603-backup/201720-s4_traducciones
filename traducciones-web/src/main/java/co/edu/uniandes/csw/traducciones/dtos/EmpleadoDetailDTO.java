package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.AreaDeConocimientoEntity;
import co.edu.uniandes.csw.traducciones.entities.EmpleadoEntity;
import co.edu.uniandes.csw.traducciones.entities.HojaDeVidaEntity;
import co.edu.uniandes.csw.traducciones.entities.OfertaEntity;
import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jc.gloria
 */
public class EmpleadoDetailDTO extends EmpleadoDTO{
    
    private List<HojaDeVidaDTO> hojadevida;
    private List<AreaDeConocimientoDTO> areasdeconocimiento;
    private List<OfertaDTO> ofertas;
    private List<PropuestaDTO> propuestas;
    
    public EmpleadoDetailDTO(){
        //Metodo constructor necesario
    }
    
    /**
     * Crea un DTO a partir de una entidad
     * @param entity objeto entidad el cual se va a convertir en DTO.
     */
    public EmpleadoDetailDTO(EmpleadoEntity entity){
        super(entity);
        if(entity != null){
            if(entity.getAreasdeconocimiento() != null){
                areasdeconocimiento = new ArrayList<>();
                for(int i = 0 ; i<entity.getAreasdeconocimiento().size(); i++){
                    areasdeconocimiento.add(new AreaDeConocimientoDTO(entity.getAreasdeconocimiento().get(i)));
                }
            }
            if(entity.getHojadevida() != null){
                hojadevida = new ArrayList<>();
                for(int i = 0 ; i<entity.getHojadevida().size(); i++){
                    hojadevida.add(new HojaDeVidaDTO(entity.getHojadevida().get(i)));
                }
            }
            if(entity.getOfertas() != null){
                ofertas = new ArrayList<>();
                for(int i = 0 ; i<entity.getOfertas().size(); i++){
                    ofertas.add(new OfertaDTO(entity.getOfertas().get(i)));
                }
            }
            if(entity.getPropuestas() != null){
                propuestas = new ArrayList<>();
                for(int i = 0 ; i<entity.getPropuestas().size(); i++){
                    propuestas.add(new PropuestaDTO(entity.getPropuestas().get(i)));
                }
            }
        }
    }
    
    /**
     * Convierte el DTO actual en una entidad
     * @return entidad con los atributos del DTO actual.
     */
    @Override
    public EmpleadoEntity toEntity() {
        EmpleadoEntity empleado = super.toEntity();
        if(areasdeconocimiento != null){
            List<AreaDeConocimientoEntity> areasEntity = new ArrayList<>();
            for(AreaDeConocimientoDTO dtoAreas : areasdeconocimiento){
                areasEntity.add(dtoAreas.toEntity());
            }
            empleado.setAreasdeconocimiento(areasEntity);
        }
        if(ofertas != null){
            List<OfertaEntity> ofertasEntity = new ArrayList<>();
            for(OfertaDTO dtoOfertas : ofertas){
                ofertasEntity.add(dtoOfertas.toEntity());
            }
            empleado.setOfertas(ofertasEntity);
        }
        if(propuestas != null){
            List<PropuestaEntity> propuestaEntity = new ArrayList<>();
            for(PropuestaDTO dtoPropuestas : propuestas){
                propuestaEntity.add(dtoPropuestas.toEntity());
            }
            empleado.setPropuestas(propuestaEntity);
        }
        if(hojadevida != null){
            List<HojaDeVidaEntity> hojaEntity = new ArrayList<>();
            for(HojaDeVidaDTO dtohoja :hojadevida){
                hojaEntity.add(dtohoja.toEntity());
            }
            empleado.setHojadevida(hojaEntity);
        }
        return empleado;
    }
    
    /**
     * Devuelve las areas de conocimiento del empleado
     * @return lista con las areas de conocimiento
     */
    public List<AreaDeConocimientoDTO> getAreasdeconocimiento() {
        return areasdeconocimiento;
    }
    
    /**
     * Asigna las areas de conocimiento del empleado
     * @param areasdeconocimiento que se van a asignar.
     */
    public void setAreasdeconocimiento(List<AreaDeConocimientoDTO> areasdeconocimiento) {
        this.areasdeconocimiento = areasdeconocimiento;
    }
    
    /**
     * Devuelve la hoja de vida del empleado en una lista.
     * @return lista con la hoja de vida.
     */
    public List<HojaDeVidaDTO> getHojadevida() {
        return hojadevida;
    }
    
    /**
     * Asigna una lista con la hoja de vida del empleado
     * @param hojadevida lista con la hoja de vida que se va a asignar.
     */
    public void setHojadevida(List<HojaDeVidaDTO> hojadevida) {
        this.hojadevida = hojadevida;
    }
    
    /**
     * Devuelve una lista con las ofertas que tiene el empleado
     * @return lista con las ofertas en forma de DTO.
     */
    public List<OfertaDTO> getOfertas() {
        return ofertas;
    }
    
    /**
     * Asigna ofertas a los empleados
     * @param ofertas lista con las ofertas que seran asignadas.
     */
    public void setOfertas(List<OfertaDTO> ofertas) {
        this.ofertas = ofertas;
    }
    
    /**
     * Devuelve una lista con las propuestas que tiene el empleado
     * @return lista con las propuestas en forma de DTO.
     */
    public List<PropuestaDTO> getPropuestas() {
        return propuestas;
    }
    
    /**
     * Asigna propuestas a los empleados
     * @param propuestas lista con las ofertas que seran asignadas.
     */
    public void setPropuestas(List<PropuestaDTO> propuestas) {
        this.propuestas = propuestas;
    }  
}