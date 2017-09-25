/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import java.util.ArrayList;
import javax.persistence.Entity;

/**
 *
 * @author ne.ortega
 */
@Entity
public class ClienteEntity extends BaseEntity{
    
    ArrayList pagos, tarjetas;

    public ArrayList<PagoEntity> getPagos() {
        return pagos;
    }

    public void setPagos(ArrayList<PagoEntity> pagos) {
        this.pagos = pagos;
    }

    public ArrayList<TarjetaDeCreditoEntity> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(ArrayList<TarjetaDeCreditoEntity> tarjetas) {
        this.tarjetas = tarjetas;
    }
    
    
}
