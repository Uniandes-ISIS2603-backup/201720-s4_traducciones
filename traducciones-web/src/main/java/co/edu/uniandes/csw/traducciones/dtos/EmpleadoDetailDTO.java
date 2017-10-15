package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.AreaDeConocimientoEntity;
import co.edu.uniandes.csw.traducciones.entities.EmpleadoEntity;
import co.edu.uniandes.csw.traducciones.entities.HojaDeVidaEntity;
import co.edu.uniandes.csw.traducciones.entities.OfertaEntity;
import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;
import java.util.ArrayList;
import java.util.List;


/**
 *
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
    
    
    public List<AreaDeConocimientoDTO> getAreasdeconocimiento() {
        return areasdeconocimiento;
    }
    
    public void setAreasdeconocimiento(List<AreaDeConocimientoDTO> areasdeconocimiento) {
        this.areasdeconocimiento = areasdeconocimiento;
    }
    
    public List<HojaDeVidaDTO> getHojadevida() {
        return hojadevida;
    }
    
    public void setHojadevida(List<HojaDeVidaDTO> hojadevida) {
        this.hojadevida = hojadevida;
    }
    
    public List<OfertaDTO> getOfertas() {
        return ofertas;
    }
    
    public void setOfertas(List<OfertaDTO> ofertas) {
        this.ofertas = ofertas;
    }
    
    public List<PropuestaDTO> getPropuestas() {
        return propuestas;
    }
    
    public void setPropuestas(List<PropuestaDTO> propuestas) {
        this.propuestas = propuestas;
    }
    
}