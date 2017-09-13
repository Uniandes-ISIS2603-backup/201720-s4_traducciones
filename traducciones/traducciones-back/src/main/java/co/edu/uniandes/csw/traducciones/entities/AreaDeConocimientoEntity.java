/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author jc.gloria
 */
@Entity
public class AreaDeConocimientoEntity extends BaseEntity implements Serializable{
    
    private String descripcion;
    
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String pDescripcion){
        descripcion = pDescripcion;
    }
}
