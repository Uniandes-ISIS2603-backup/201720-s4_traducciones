/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import co.edu.uniandes.csw.traducciones.enums.Estado;
import java.io.Serializable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

/**
 *
 * @author av.perezb
 */
public class PropuestaEntity extends BaseEntity implements Serializable {

    @OneToOne
    private OfertaEntity oferta;
    
    private Double costo;
    
    @Enumerated(EnumType.STRING)
    private Estado estado;

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

   

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }
  
    
    
}
