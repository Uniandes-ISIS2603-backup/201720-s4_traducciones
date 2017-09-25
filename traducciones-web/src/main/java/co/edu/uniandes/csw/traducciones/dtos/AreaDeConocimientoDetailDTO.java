/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.AreaDeConocimientoEntity;

/**
 *
 * @author jc.gloria
 */
public class AreaDeConocimientoDetailDTO extends AreaDeConocimientoDTO{
    
    public AreaDeConocimientoDetailDTO(){
        
    }
    public AreaDeConocimientoDetailDTO(AreaDeConocimientoEntity entity){
        super(entity);
    }
    
    public AreaDeConocimientoEntity toEntity(){
        AreaDeConocimientoEntity e = super.toEntity();
        return e;
    }

}
