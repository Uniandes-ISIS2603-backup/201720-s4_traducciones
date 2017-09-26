/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ne.ortega
 */
@Entity
public class PagoEntity extends BaseEntity {
    
    private long idEmpleado, idSolicitud;
    
    @PodamExclude
    @OneToOne
    private TarjetaDeCreditoEntity tarjeta;

    public long getIdEmpleado() {
        return idEmpleado;
    }

    public TarjetaDeCreditoEntity getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaDeCreditoEntity tarjeta) {
        this.tarjeta = tarjeta;
    }

    public void setIdEmpleado(long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    
}
