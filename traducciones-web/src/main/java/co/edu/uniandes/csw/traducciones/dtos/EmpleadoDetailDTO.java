package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.AreaDeConocimientoEntity;
import co.edu.uniandes.csw.traducciones.entities.EmpleadoEntity;
import co.edu.uniandes.csw.traducciones.entities.HojaDeVidaEntity;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author jc.gloria
 */
public class EmpleadoDetailDTO extends EmpleadoDTO{
    
    private List<HojaDeVidaDTO> hojadevida;
    private List<AreaDeConocimientoDTO> areasdeconocimiento;
    
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
    
    
    
    
    
}