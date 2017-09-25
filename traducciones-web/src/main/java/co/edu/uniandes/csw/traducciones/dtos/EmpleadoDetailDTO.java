package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.EmpleadoEntity;

/**
 *
 * @author jc.gloria
 */
public class EmpleadoDetailDTO extends EmpleadoDTO{
    
    public EmpleadoDetailDTO(){
        
    }
    
    public EmpleadoDetailDTO(EmpleadoEntity entity){
        super(entity);
    }
    
    @Override
    public EmpleadoEntity toEntity() {
        EmpleadoEntity e = super.toEntity();
        return e;
    }
}