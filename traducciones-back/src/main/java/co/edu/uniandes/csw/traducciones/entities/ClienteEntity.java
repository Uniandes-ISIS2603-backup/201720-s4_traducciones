/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ne.ortega
 */
@Entity
public class ClienteEntity extends BaseEntity{
    
    @PodamExclude
    @OneToMany
    private List<PagoEntity> pagos;
    
    @PodamExclude
    @OneToMany
    private List<TarjetaDeCreditoEntity> tarjetas;

    public List<PagoEntity> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoEntity> pagos) {
        this.pagos = pagos;
    }

    public List<TarjetaDeCreditoEntity> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<TarjetaDeCreditoEntity> tarjetas) {
        this.tarjetas = tarjetas;
    }
}
