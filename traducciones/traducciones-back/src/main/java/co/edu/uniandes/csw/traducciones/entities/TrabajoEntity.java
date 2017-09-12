/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;

/**
 *
 * @author ga.garcia90
 */
@Entity
public class TrabajoEntity extends BaseEntity implements Serializable{
    
    private Boolean terminado;
    
    /**
     * @return the descripcion
     */
    public Boolean getEstado() {
        return terminado;
    }

    /**
     * @param terminado the descripcion to set
     */
    public void setEstado(Boolean terminado) {
        this.terminado = terminado;
    }
}
