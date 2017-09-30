/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import co.edu.uniandes.csw.traducciones.enums.Estado;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author av.perezb
 */
@Entity
public class PropuestaEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @OneToOne
    private OfertaEntity oferta;
  
    public OfertaEntity getOferta() {
        return oferta;
    }

    public void setOferta(OfertaEntity oferta) {
        this.oferta = oferta;
    }
        
    private String estado;
    
    private Double costo;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

}
